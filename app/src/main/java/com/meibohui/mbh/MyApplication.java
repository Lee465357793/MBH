package com.meibohui.mbh;

import android.app.Application;
import android.os.Build;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.meibohui.mbh.utils.SystemUtils;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyApplication extends Application
{
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
        Fresco.initialize(this);
        initOkGo();
    }


    private void initOkGo() {
        //设置公共数据参数
        HttpHeaders headers = new HttpHeaders();
        headers.put("app_device", SystemUtils.getDeviceModel());//手机型号
        headers.put("app_software", SystemUtils.getOSVersion());//系统版本
        headers.put("app_version", SystemUtils.getVersionName(this));//获取app版本的名字
        headers.put("app_serial", SystemUtils.getAndroidId(this));//获取Android的id
        headers.put("User-Agent", String.format("%s/Android_%s (%s; %s Build/%s)", "JINKEGUAN",  SystemUtils.getVersionName(this), SystemUtils.getOSVersion(), SystemUtils.getDeviceModel(), Build.ID));

        //必须调用初始化
        OkGo.init(this);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {


            OkGo.getInstance()
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
//                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(1)
                    .addCommonHeaders(headers);  //设置全局公共头


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
