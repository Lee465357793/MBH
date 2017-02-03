package com.meibohui.mbh.utils;

import android.os.Handler;

import com.meibohui.mbh.manager.ThreadPoolManager;


/**
 * Created by wangx on 0017.
 * 1. 可以在子线程执行任务 2.可以切换到主线程执行任务
 */
public class ThreadUtils {

    /***
     * 在子线程执行的方法
     *
     * @param runnable
     */
    public static void runOnBackThread(Runnable runnable) {
        ThreadPoolManager
                .getInstance()
                .createThreadProxy()
                .execute(runnable);
    }

    private static Handler handler = new Handler();

    /**
     * 在主线程执行方法
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
