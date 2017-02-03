package com.meibohui.mbh.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.meibohui.mbh.R;


/**
 * Created by lx on 2016/10/24.
 */
public class GuideActivity extends Activity implements View.OnClickListener {
    /** viewPager */
    private ViewPager viewPager;
    /** 开始体验：跳转主界面 */
    private Button btn_begin;
    /** viewPager 页面滑动指示 */
    private RadioButton rb_point;
    /** 页面指示View 的宽度 */
    private int rb_pointWidth;
    /** 处理页面改变事件 */
    private ViewPager.OnPageChangeListener dealOnPagerChangeListener = new ViewPager.OnPageChangeListener() {
        /**
         * 当界面滚动
         * @param position 当前界面的索引
         * @param positionOffset 当前界面滚动距离的百分比
         * @param positionOffsetPixels 当前界面滚动距离的像素值
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            rb_pointWidth = rb_point.getMeasuredWidth();
            Log.i("rb_pointWidth", rb_pointWidth+"rb_pointWidth=============");
            float disX = rb_pointWidth * positionOffset; //获取滑动改变的距离
            float startX = disX + rb_pointWidth * position; //获得开始位移的位置
            float endX = disX + startX; //获取动画结束位置坐标
            Log.i("rb_pointWidth", "dis====" + disX + ",start==" + startX + ",end==" + endX);
            //设置rb_point位移位置
            rb_point.setTranslationX(startX);
        }

        /** 当界面改变后 */
        @Override
        public void onPageSelected(int position) {
            if (position == 2) {
                btn_begin.setVisibility(View.VISIBLE);
            } else {
                btn_begin.setVisibility(View.GONE);
            }
        }

        /** 当界面滚动状态改变 */
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //开始体验
        btn_begin = (Button) findViewById(R.id.btn_begin);
        btn_begin.setOnClickListener(this);
        //页面指示点
        rb_point = (RadioButton) findViewById(R.id.rb_point);
//        //获取指示点测量后的宽
//        rb_pointWidth = rb_point.getMeasuredWidth();
//        Log.i("rb_pointWidth", rb_pointWidth+"rb_pointWidth=============");
        MyPagerAdapter adapter = new MyPagerAdapter(); //viewPager适配器
        viewPager.setAdapter(adapter);//给viewpager设置适配器
        //添加页面改变事件监听
        viewPager.setOnPageChangeListener(dealOnPagerChangeListener);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 3;
        }
        /** 创建viewpager条目 */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(GuideActivity.this);
            textView.setText("我是界面"+ position);
            viewPager.addView(textView); //添加viewpager 显示条目
            return textView;
        }
        /** 销毁条目 */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            viewPager.removeView((TextView)object);  //销毁过时条目
            object = null; //清空
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }
    }
}