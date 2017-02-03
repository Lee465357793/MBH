package com.meibohui.mbh.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.meibohui.mbh.R;
import com.meibohui.mbh.utils.SharePrefUtil;
import com.meibohui.mbh.utils.ToastUtil;

import cn.bmob.v3.Bmob;


/**
 * Created by lx on 2016/10/24.
 */
public class SplashActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
        Bmob.initialize(this, "8bb92dd756a7cb323953bbf431f7370c");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Handler handler = new Handler();

        boolean isFirst = SharePrefUtil.getBoolean(SplashActivity.this, "isFirst", true);
        if(isFirst){ //是 第一次使用
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
            finish(); //关闭当前界面
        }else{ //不是
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); //关闭当前界面
                }
            }, 2000);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.showTextToast(this, "guanbile");
    }
}







