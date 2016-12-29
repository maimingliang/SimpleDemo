package com.maiml.simpledemo.di;

/**
 * Created by maimingliang
 */
public class ComponentHolder {
    private static AppComponent sAppComponent;

    public static void setAppComponent(AppComponent appComponent) {
        sAppComponent = appComponent;
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

}
