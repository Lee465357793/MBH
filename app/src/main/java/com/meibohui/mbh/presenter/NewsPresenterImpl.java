package com.meibohui.mbh.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.meibohui.mbh.Config;
import com.meibohui.mbh.model.Datas;
import com.meibohui.mbh.constract.INews;
import com.meibohui.mbh.utils.CacheUtil;


/**
 * Created by Administrator on 2016/12/5.
 */

public class NewsPresenterImpl implements INews.presenter
{
    private INews.Views mViews;
    private Gson mGson;
    private CacheUtil mUtil;

    public NewsPresenterImpl(Context context, INews.Views views)
    {
        mViews = views;
        mUtil = CacheUtil.get(context);
        mGson = new Gson();
    }

    @Override
    public void loadingDatasFromNet()
    {
//        Subscription subscription = ApiManage.getInstances().getNewService()
//                .getNewsService()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Datas>()
//                {
//                    @Override
//                    public void onCompleted()
//                    {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e)
//                    {
//                        mViews.showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Datas datas)
//                    {
//                        mViews.showInfo(datas.getResult().getData());
//                        mUtil.put(Config.NEWS, mGson.toJson(datas));
//                    }
//                });
//        addSubcrible(subscription);
    }

    @Override
    public void loadingDatasFromCache()
    {
        if (mUtil.getAsJSONObject(Config.NEWS) != null)
        {
            Datas datas = mGson.fromJson(mUtil.getAsJSONObject(Config.NEWS).toString(), Datas.class);
            mViews.showInfo(datas.getResult().getData());
        }
    }
}
