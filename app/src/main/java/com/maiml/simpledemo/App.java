package com.maiml.simpledemo;

import android.app.Application;
import android.content.Context;

import com.maiml.common.utils.CrashHandler;
import com.maiml.common.utils.LogUtil;
import com.maiml.simpledemo.di.AppComponent;
import com.maiml.simpledemo.di.AppModule;
import com.maiml.simpledemo.di.ComponentHolder;
import com.maiml.simpledemo.di.DaggerAppComponent;

/**
 * Created by maimingliang on 2016/12/29.
 */

public class App extends Application {


    private static Context instance;

    public final static boolean IS_CACHE = false;
    public final static String  SERVER_ADDRESS = "http://gank.io/api/";
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        LogUtil.init();
        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        ComponentHolder.setAppComponent(appComponent);
        //捕获全局异常
        CrashHandler.getInstance().init(this);

    }


    public static Context getInstance(){
        return instance;
    }

}
