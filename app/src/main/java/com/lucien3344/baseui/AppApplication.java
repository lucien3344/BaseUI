package com.lucien3344.baseui;

import android.app.Application;


public class AppApplication extends Application {

    private static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

    }


    public static AppApplication getInstance() {
        return application;
    }

}
