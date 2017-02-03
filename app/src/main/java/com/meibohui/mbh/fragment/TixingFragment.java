package com.meibohui.mbh.fragment;

import android.os.Bundle;
import android.view.View;


import com.meibohui.mbh.R;
import com.meibohui.mbh.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2016/12/2.
 */

public class TixingFragment extends BaseFragment
{

    private Unbinder mUnbinder;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_tixing;
    }

    @Override
    public void initVariables()
    {

    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void initLoadData()
    {

    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
