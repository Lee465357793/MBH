package com.meibohui.mbh.utils;

import android.app.Application;


/**
 * Created by Administrator on 2016/12/5.
 */

public class MyApplication extends Application {
    public static MyApplication application;
    public static MyApplication getContext()
    {
        return application;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        application = this;
    }

}
