package com.meibohui.mbh.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.meibohui.mbh.R;
import com.meibohui.mbh.manager.UpdateManager;
import com.meibohui.mbh.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DefaultFragment extends Fragment {
	private String mParam1;
	
	@BindView(R.id.tv_content)
	TextView tv_content;

	public DefaultFragment() {
		// Required empty public constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {

			mParam1 = getArguments().getString("key");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.fragment_default, container, false);
        //TODO 软件更新
        Button update = new Button(getActivity());
        Button login = new Button(getActivity());
        update.setText("检查更新");
        login.setText("登录注册");
        view.addView(update);
        view.addView(login);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateManager up = new UpdateManager(getActivity());
                up.checkUpdate();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
            }
        });
		return view;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		initView(view);
	}

	private void initView(View view) {
		if(TextUtils.isEmpty(mParam1)){
			tv_content.setText("我是一个测试用的Fragment, 我创建的时候没有传进来Bundle, 所以显示这个内容.");
		}else {
			tv_content.setText(mParam1);
		}
				
	}

}
