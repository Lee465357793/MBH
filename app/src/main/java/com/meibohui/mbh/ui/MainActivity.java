package com.meibohui.mbh.ui;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.meibohui.mbh.R;
import com.meibohui.mbh.utils.SharePrefUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener, TabHost.OnTabChangeListener, View.OnTouchListener {
    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */

    @BindView(android.R.id.tabhost)
    public FragmentTabHost mTabHost;
    @BindView(R.id.quick_option_iv)
    View mAddBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {

        // 初始化底部FragmentTabHost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.rl_container);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        initTabs();

        //添加标记。是否第一次开启应用。
        SharePrefUtil.saveBoolean(this, "isFirst", false);

        // 中间按键图片触发
        mAddBt.setOnClickListener(this);
        mAddBt.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);




        // httpClient  HttpUrlconnection
//        try {
//         URL uri = new URL("http://192.168.12.55/oschina/list/news/page0.xml");
//            HttpURLConnection  conn = (HttpURLConnection) uri.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5000);
//            int responseCode = conn.getResponseCode();
//            if (responseCode == 200){
//                InputStream is = conn.getInputStream();
//                NewsList newsList = XmlUtils.toBean(NewsList.class, is);
//                List<News> list = newsList.list;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            // 找到每一个枚举的Fragment对象
            MainTab mainTab = tabs[i];

            // 1. 创建一个新的选项卡
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));

            // ------------------------------------------------- 自定义选项卡 ↓
            View indicator = LayoutInflater.from(getApplicationContext())
                    .inflate(R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            Drawable drawable = this.getResources().getDrawable(
                    mainTab.getResIcon());
            title.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null,
                    null);
            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
            }
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            // ------------------------------------------------- 以上 ↑


            Bundle bundle = new Bundle();
            bundle.putString("key", "content: " + getString(mainTab.getResName()));
            // 2. 把新的选项卡添加到TabHost中
            mTabHost.addTab(tab, mainTab.getClz(), bundle);

            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onTabChanged(String tabId) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
