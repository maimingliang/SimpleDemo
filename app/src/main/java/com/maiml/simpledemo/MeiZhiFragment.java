package com.maiml.simpledemo;

import android.support.v4.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maiml.common.base.mvp.FragmentPresenter;
import com.maiml.common.http.HttpResult;
import com.maiml.common.http.rx.callback.SubscriberOnNextListener;
import com.maiml.common.http.rx.exception.ErrorHandler;
import com.maiml.common.http.rx.subscribe.NormalSubscriber;
import com.maiml.simpledemo.di.ComponentHolder;
import com.maiml.simpledemo.di.DataManager;


import java.util.List;

import javax.inject.Inject;

import me.fangx.haorefresh.LoadMoreListener;


public class MeiZhiFragment extends FragmentPresenter<MeiZhiDelegate> {





    int page = 0;

    @Inject
    DataManager dataManager;



    public MeiZhiFragment() {
        // Required empty public constructor
    }





    @Override
    protected void initDataABindEvenListener() {
        super.initDataABindEvenListener();

        ComponentHolder.getAppComponent().inject(this);
        findList(SearchType.NEW);
        viewDelegate.getRecycleview().setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                findList(SearchType.OLD);
            }
        });

        viewDelegate.getSwiperefresh().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewDelegate.getRecycleview().loadMoreComplete();
                findList(SearchType.NEW);
            }
        });
    }

    public void notifyDataChange(List<GankIoBean> list, SearchType searchType) {
        if(searchType == SearchType.NEW){

            viewDelegate.getRecycleview().refreshComplete();
            viewDelegate.getRecycleview().loadMoreComplete();
            viewDelegate.getSwiperefresh().setRefreshing(false);
            viewDelegate.reFreshDataChange(list);

        }else{

            if (list == null || list.size() <= 0) {
                viewDelegate.getRecycleview().loadMoreEnd();
            } else {
                viewDelegate.loadMoreDataChange(list);
                viewDelegate.getRecycleview().loadMoreComplete();
            }
        }
    }

    public void findList(final SearchType searchType){


        if(SearchType.NEW == searchType){
            page = 0;
        }
        page++;
        SubscriberOnNextListener<List<GankIoBean>> s = new SubscriberOnNextListener<List<GankIoBean>>() {
            @Override
            public void onNext(List<GankIoBean> gankIoBeen) {
              notifyDataChange(gankIoBeen,searchType);
            }

            @Override
            public void onCacheNext(String string) {

                Gson gson = new Gson();
                HttpResult<List<GankIoBean>> list = gson.fromJson(string, new TypeToken<HttpResult<List<GankIoBean>>>() {
                }.getType());
              notifyDataChange(list.getResults(),searchType);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if(ErrorHandler.isNetWorkError(e)){

                }else{

                }
            }
        };


        dataManager.findList(getCategory(),page * 10,new NormalSubscriber<List<GankIoBean>>(getActivity(),"random/data",s));

    }
    public String getCategory() {
        return "福利";
    }


    @Override
    protected Class<MeiZhiDelegate> getDelegateClass() {
        return MeiZhiDelegate.class;
    }
}
