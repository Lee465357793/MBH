package com.meibohui.mbh.loading;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.meibohui.mbh.utils.SystemUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 自定义的一个callBack类  联网后的异步回调
 */
public  abstract class StringCallBack extends StringCallback {

    private final Activity activity;

    private ProgressAlertDialog dialog;



    public StringCallBack(Activity activity) {
        this.activity = activity;
        dialog = new ProgressAlertDialog(activity);//创建自定义Dialog对象


    }





    /**
     * 请求前的回调
     * @param request
     */
        @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();//打开加载的对话框

        }

    }

    /**
     * 失败的回调
     * @param call
     * @param response
     * @param e
     */
    @Override
    public void onError(Call call, Response response, Exception e) {

        boolean b = SystemUtils.checkNetWork(activity);//判断是否是因为网络问题导致的错误

        if (b){
            Toast.makeText(activity, "刷新失败!!!", Toast.LENGTH_SHORT).show();
            return;
        }
            Toast.makeText(activity, "请检查网络!!!", Toast.LENGTH_SHORT).show();
    }


       /** 请求网络结束后，UI线程
     */
       @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {

            dialog.dismiss();

        }

    }



}
