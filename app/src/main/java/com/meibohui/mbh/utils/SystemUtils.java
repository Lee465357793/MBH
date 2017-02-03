package com.meibohui.mbh.utils;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Log;

/**获取手机系统的一些工具类
 * Created by Sunshine on 2016/12/30.
 */
public class SystemUtils {


    /**
     * 获取手机型号    正确
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL;
    }


    /**
     * 获取android系统版本号   正确
     */
    public static String getOSVersion() {
        String release = android.os.Build.VERSION.RELEASE; // android系统版本号
        release = "Android " + release;
        return release;
    }

    /**
     * 获取版本号    正确
     * @return
     */
    public static String  getVersionCode(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        int version=0;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);

           version =  info.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
                return version+"";

    }

    /**
     * 获取版本名字    正确
     * @return
     */
    public static String getVersionName(Context context){
        PackageManager manager = context.getPackageManager();
        try {
            //第二个参数代表额外的信息，例如获取当前应用中的所有的Activity
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES
            );
            ActivityInfo[] activities = packageInfo.activities;
            showActivities(activities);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }



    public  static void showActivities(ActivityInfo[] activities){
        for(ActivityInfo activity : activities) {
            Log.i("activity=========", activity.name);
        }
    }



    /**
     * 获取系统序列号   正确
     */
    public static String getAndroidId(Context context) {
        String pValue = null;
        try {
            pValue = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
        } catch (Exception e) {

        }
        return pValue;
    }


    /**
     * 检测网络是否连接
     * @return
     */
    public static boolean checkNetWork(Context context) {
        boolean flag = false;
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cwjManager.getActiveNetworkInfo() != null)
            flag = cwjManager.getActiveNetworkInfo().isAvailable();
        return flag;
    }



}
