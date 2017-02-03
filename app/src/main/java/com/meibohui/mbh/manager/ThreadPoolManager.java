package com.meibohui.mbh.manager;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangx on 0017.
 * 线程池管理器 单例
 */
public class ThreadPoolManager {

    private static final ThreadPoolManager instance = new ThreadPoolManager();
    private ThreadProxy proxy;
    private ThreadProxy shortProxy;

    //1. 懒汉式  2.饿汉式(线程安全)
    private ThreadPoolManager() {
    }

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    /***
     * 1. 联网 2. 读写文件
     * cpu * 2 + 1
     *
     * @return
     */
    public ThreadProxy createThreadProxy() {
        if (proxy == null) {
            proxy = new ThreadProxy(5, 5, 5000);
        }
        return proxy;
    }

    public ThreadProxy createShortThreadProxy() {
        if (shortProxy == null) {
            shortProxy = new ThreadProxy(3, 3, 5000);
        }
        return shortProxy;
    }

    public class ThreadProxy {
        ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 执行任务
         *
         * @param runnable
         */
        public void execute(Runnable runnable) {

            if (threadPool == null) {
                //参数1: corePoolSize 初始化的线程数量
                //参数2:maximumPoolSize 额外开辟的线程最大数量

                //参数 5:  数据结构
                threadPool = new ThreadPoolExecutor(
                        corePoolSize,
                        maximumPoolSize,
                        keepAliveTime,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(10));
            }

            threadPool.execute(runnable);
        }


        /**
         * 取消任务
         *
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (threadPool != null && !threadPool.isTerminated() && !threadPool.isShutdown()) {
                threadPool.remove(runnable);
            }
        }
    }
}
