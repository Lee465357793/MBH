package com.meibohui.mbh.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.meibohui.mbh.R;
import com.meibohui.mbh.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2016/12/2.
 */

public class MineFragment extends BaseFragment
{

    @BindView(R.id.image1)
    SimpleDraweeView mImage1;
    private Unbinder mUnbinder;

    @Override
    public int getLayoutId()
    {
        return R.layout.fragment_mine;
    }

    @Override
    public void initVariables()
    {

    }

    @Override
    public void initViews(View view, Bundle savedInstanceState)
    {
        mUnbinder = ButterKnife.bind(this, view);
        Uri uri = Uri.parse("res://" +
                getActivity().getPackageName() +
                "/" + R.drawable.mylove);
        mImage1.setImageURI(uri);
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
