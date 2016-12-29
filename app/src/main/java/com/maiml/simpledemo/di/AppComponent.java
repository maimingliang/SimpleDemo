package com.maiml.simpledemo.di;

import android.content.Context;

import com.maiml.simpledemo.MeiZhiFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by maimingliang on 16/6/15.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    Context context();  // 提供Applicaiton的Context

    void inject(MeiZhiFragment fragment);
 }
