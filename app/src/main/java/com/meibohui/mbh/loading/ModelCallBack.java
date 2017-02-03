package com.meibohui.mbh.loading;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;
import com.meibohui.mbh.utils.SystemUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 自定义的一个callBack类  联网后的异步回调
 */
public  abstract class ModelCallBack<T> extends AbsCallback<T> {

    private final Activity activity;

    private Dialog dialog;


    public ModelCallBack(Activity activity) {

        this.activity = activity;
        dialog = new ProgressAlertDialog(activity);//创建自定义Dialog对象
    }

    @Override
    public T convertSuccess(Response response) throws Exception {

        Type type = getClass().getGenericSuperclass();
        Type type1 = ((ParameterizedType) type).getActualTypeArguments()[0];
        Gson gson = new Gson();
        String string = response.body().string();
        response.close();
        T Result = gson.fromJson(string, type1);

        return Result;
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
    /**
     * 缓存成功的回调,UI线程  实现他,可以做缓存
     */
    @Override
    public  void  onCacheSuccess(T s, Call call){

    }


    /** 请求网络结束后，UI线程
     */
    @Override
    public void onAfter(@Nullable T s, @Nullable Exception e) {
        super.onAfter(s, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {

            dialog.dismiss();

        }

    }

}
