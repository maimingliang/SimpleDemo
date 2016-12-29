package com.maiml.simpledemo.di;




import com.maiml.common.http.cache.CookieDbUtil;
import com.maiml.common.http.intercepter.CookieResult;
import com.maiml.common.http.rx.HttpResultFunc;
import com.maiml.common.http.rx.exception.RetryWhenNetworkException;
import com.maiml.common.http.rx.subscribe.BaseSubscriber;
import com.maiml.common.utils.LogUtil;
import com.maiml.simpledemo.App;
import com.maiml.simpledemo.GankIoBean;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author maimingliang
 */
public class DataManager {

  private static final String TAG = "DataManager";

  private final ApiService mApiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.mApiService = apiService;
    }


    public void findList(String category, int num, final BaseSubscriber<List<GankIoBean>> s){

        LogUtil.d("findList params");
        LogUtil.d(category);
        LogUtil.d(num);

           /*获取缓存数据*/
        final CookieResult cookieResulte = CookieDbUtil.getInstance(App.getInstance()).queryCookieBy("http://maimingliang.me/api");
        if (cookieResulte != null) {
            long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
            if (time < 60) {

                Observable.just(cookieResulte.getResulte()).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        s.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        s.onError(e);
                        CookieDbUtil.getInstance(App.getInstance()).deleteCookie(cookieResulte);
                    }

                    @Override
                    public void onNext(String str) {

                        LogUtil.d("读取缓存 = " + str);
                        s.onCacheNext(str);
                    }
                });

                return;

            }


        }

        Observable<List<GankIoBean>> observable = mApiService.findList(category, num)
                 .map(new HttpResultFunc<List<GankIoBean>>());

        toSubscribe(observable,s);

    }

    private <T> void toSubscribe(Observable<T> o, BaseSubscriber<T> s) {

               o.retryWhen(new RetryWhenNetworkException())  /*失败后的retry配置*/
                 .compose(s.getRxAppCompatActivity().<T>bindToLifecycle())     /*生命周期管理*/
                .subscribeOn(Schedulers.io())  /*http请求线程*/
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

}




