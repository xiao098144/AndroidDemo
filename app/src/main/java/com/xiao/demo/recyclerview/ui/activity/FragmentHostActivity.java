package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.widget.Fragment1;
import com.xiao.demo.recyclerview.widget.Fragment2;
import com.xiao.demo.recyclerview.widget.Fragment3;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName:com.xiao.demo.recyclerview.ui.activity.FragmentHostActivity.java
 * 操作简单 不需要关心Fragment替换
 * 缺陷  Fragment切换时会重复初始化UI
 * Created on 2017/2/16
 * Version V1.0
 */

public class FragmentHostActivity extends FragmentActivity {

    protected FragmentTabHost mTabHost;

    @BindView(R.id.img_home)
    protected ImageView img_home;

    private View view0, view1, view2;
    private ImageView img0, img1, img2;
    private TextView tv0, tv1, tv2;

    private List<Fragment> mFragmentList;

    private LayoutInflater inflater;

    private int mCurrentTabId = 1;

    private static final String TAG = "FragmentHostActivity";

    String s1 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_oc_off.png";
    String s2 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_oc_on.png";
    String s3 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_xt_off.png";
    String s4 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_xt_on.png";

    List<String> urlList = new ArrayList<>();

    public static void start(Context context) {
        Intent starter = new Intent(context, FragmentHostActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenthost);
        ButterKnife.bind(this);
        urlList.add(s1);
        urlList.add(s2);
        urlList.add(s3);
        urlList.add(s4);
        initFragment();
        initHost();
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        Fragment1 fr1 = new Fragment1();
        Fragment1.class.getCanonicalName();
        mFragmentList.add(fr1);
        Fragment2 fr2 = new Fragment2();
        mFragmentList.add(fr2);
        Fragment3 fr3 = new Fragment3();
        mFragmentList.add(fr3);
    }

    private void initHost() {
        inflater = LayoutInflater.from(this);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        view0 = inflater.inflate(R.layout.layout_tab_item, null);
        img0 = (ImageView) view0.findViewById(R.id.icon);
        tv0 = (TextView) view0.findViewById(R.id.title);
        img0.setBackgroundResource(R.drawable.t_home_vip_nor);
        tv0.setText("VIP");
        TabHost.TabSpec tabSpec0 = mTabHost.newTabSpec(String.valueOf(0)).setIndicator(view0);
        mTabHost.addTab(tabSpec0, Fragment1.class, null);

        view1 = inflater.inflate(R.layout.layout_tab_item, null);
        img1 = (ImageView) view1.findViewById(R.id.icon);
        tv1 = (TextView) view1.findViewById(R.id.title);
        TabHost.TabSpec tabSpec1 = mTabHost.newTabSpec(String.valueOf(1)).setIndicator(view1);
        mTabHost.addTab(tabSpec1, Fragment2.class, null);
        img_home.setBackgroundResource(R.mipmap.qian_icon);

        view2 = inflater.inflate(R.layout.layout_tab_item, null);
        img2 = (ImageView) view2.findViewById(R.id.icon);
        tv2 = (TextView) view2.findViewById(R.id.title);
        img2.setBackgroundResource(R.drawable.t_home_user_nor);
        tv2.setText("我的");
        TabHost.TabSpec tabSpec2 = mTabHost.newTabSpec(String.valueOf(2)).setIndicator(view2);
        mTabHost.addTab(tabSpec2, Fragment3.class, null);

        for (int i = 0; i < mFragmentList.size(); i++) {
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentTabId = 1;
                mTabHost.setCurrentTab(1);
                Log.e(TAG, "onClick: img_home onclick");
            }
        });

        mTabHost.setOnTabChangedListener(tabId -> {
            Log.e(TAG, "initHost:onTabChange  tabId = " + tabId + " mCurrentTabId = " + mCurrentTabId + " mTabHost.getCurrentTabTag() = " + mTabHost.getCurrentTabTag());
            mCurrentTabId = Integer.valueOf(tabId);
        });

    }

}
