package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.AppUtil;
import com.xiao.demo.recyclerview.widget.Fragment1;
import com.xiao.demo.recyclerview.widget.Fragment2;
import com.xiao.demo.recyclerview.widget.Fragment3;
import com.xiao.demo.recyclerview.widget.model.TabBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FileName:com.xiao.demo.recyclerview.ui.activity.DynamicTabActivity.java
 * Created on 2017/2/16
 * Version V1.0
 */

public class DynamicTabActivity extends FragmentActivity implements View.OnClickListener {

    @BindView(R.id.dynamic_tab_content)
    protected FrameLayout tabcontent;

    @BindView(R.id.dynamic_layout_tab)
    protected LinearLayout layout_tab;

    @BindView(R.id.dynamic_layout_tab0)
    protected FrameLayout layout_tab0;

    @BindView(R.id.dynamic_layout_tab2)
    protected FrameLayout layout_tab2;

    @BindView(R.id.dynamic_img_home)
    protected ImageView img_home;

    @BindView(R.id.btn_changetab)
    protected Button btn_changetab;

    private ImageView img0, img2;
    private TextView tv0, tv2;

    private ImageView[] imgs;

    private TextView[] tvs;

    private List<Fragment> mFragmentList;

    private LayoutInflater inflater;

    private int mCurrentTabId = 1;

    private Fragment1 fr1;

    private final String FR1_TAG = "Fragment1";

    private Fragment2 fr2;

    private final String FR2_TAG = "Fragment2";

    private Fragment3 fr3;

    private final String FR3_TAG = "Fragment3";

    /**
     * 是否有显示的fragment
     */
    private String showFragment = "";

    private static final String TAG = "FragmentHostActivity";

    String s1 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_oc_off.png";
    String s2 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_oc_on.png";
    String s3 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_xt_off.png";
    String s4 = "http://jzjx.ddoctor.cn/jzjx/h5/test/tab_xt_on.png";

    private List<TabBean> tabList = new ArrayList<>();

    private int screen_width;

    public static void start(Context context) {
        Intent starter = new Intent(context, DynamicTabActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_tab);
        ButterKnife.bind(this);
        screen_width = AppUtil.getScreenWidth(this);
        if (savedInstanceState != null) {// 判断是恢复状态情况，还是重新建立情况
            FragmentManager fragmentManager = getSupportFragmentManager();
            fr1 = (Fragment1) fragmentManager.findFragmentByTag(FR1_TAG);
            fr2 = (Fragment2) fragmentManager.findFragmentByTag(FR2_TAG);
            fr3 = (Fragment3) fragmentManager.findFragmentByTag(FR3_TAG);
            showFragment = savedInstanceState.getString("showFragment");
        }
        generateDefTabList();
        initFragment();
        initHost();
    }


    private void generateDefTabList() {
        TabBean tab0 = new TabBean(R.drawable.t_home_vip_nor, R.drawable.t_home_vip_sel, "VIP", getResources().getColor(R.color.blue), getResources().getColor(R.color.red));
        TabBean tab1 = new TabBean();
        tab1.setNorRes(R.mipmap.qian_icon);
        TabBean tab2 = new TabBean(R.drawable.t_home_user_nor, R.drawable.t_home_user_sel, "我的", getResources().getColor(R.color.blue), getResources().getColor(R.color.red));
        tabList.add(tab0);
        tabList.add(tab1);
        tabList.add(tab2);
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        if (fr1 == null) fr1 = new Fragment1();
        mFragmentList.add(fr1);
        if (fr2 == null) fr2 = new Fragment2();
        mFragmentList.add(fr2);
        if (fr3 == null) fr3 = new Fragment3();
        mFragmentList.add(fr3);

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        if (showFragment != null && !"".equals(showFragment)) {// 是恢复的，判断是否是已经添加过fragment
            for (Fragment f : mFragmentList) {
                ft.hide(f);
            }
            switch (showFragment) {
                case FR1_TAG:
                    ft.show(fr1).commit();
                    break;
                case FR2_TAG:
                    ft.show(fr2).commit();
                    break;
                case FR3_TAG:
                    ft.show(fr3).commit();
                    break;
            }
        } else {// 是新建的
            if (!fr2.isAdded()) ft.add(R.id.dynamic_tab_content, fr2, FR2_TAG).commit();
        }

    }

    private void initHost() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View view0 = inflater.inflate(R.layout.layout_tab_item, null, false);
        img0 = (ImageView) view0.findViewById(R.id.icon);
        tv0 = (TextView) view0.findViewById(R.id.title);
        layout_tab0.addView(view0);

        View view2 = inflater.inflate(R.layout.layout_tab_item, null, false);
        img2 = (ImageView) view2.findViewById(R.id.icon);
        tv2 = (TextView) view2.findViewById(R.id.title);
        layout_tab2.addView(view2);

        layout_tab0.setOnClickListener(this);
        layout_tab2.setOnClickListener(this);
        img_home.setOnClickListener(this);

        imgs = new ImageView[3];
        imgs[0] = img0;
        imgs[1] = img_home;
        imgs[2] = img2;
        tvs = new TextView[3];
        tvs[0] = tv0;
        tvs[2] = tv2;
        showDefaultTab();
        setCurrentTab(mCurrentTabId);
        btn_changetab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_changetab:
                changeTab();
                break;
            case R.id.dynamic_layout_tab0:
                Log.e(TAG, "onClick: 切换至Fragnment1");
                setCurrentTab(0);
                break;
            case R.id.dynamic_layout_tab2:
                Log.e(TAG, "onClick: 切换至Fragment3");
                setCurrentTab(2);
                break;
            case R.id.dynamic_img_home:
                Log.e(TAG, "onClick: 切换至Fragment2");
                setCurrentTab(1);
                break;
        }
    }

    private void setCurrentTab(int idx) {
        Log.e(TAG, "setCurrentTab: idx = " + idx + " mCurrentTabId = " + mCurrentTabId);
        if ((idx < 0 || idx > 2) || mCurrentTabId == idx) return;
        if (mCurrentTabId != 1) {
            TabBean tabBean = tabList.get(mCurrentTabId);
            if (tabBean != null) {
                ImageView img = imgs[mCurrentTabId];
                if (img != null) {
                    img.setBackgroundResource(tabBean.getNorRes());
                }
                TextView tv = tvs[mCurrentTabId];
                if (tv != null) tv.setTextColor(tabBean.getNorColor());
            }
        }
        if (idx != 1) {
            TabBean tabBean = tabList.get(idx);
            if (tabBean != null) {
                ImageView img = imgs[idx];
                if (img != null) {
                    img.setBackgroundResource(tabBean.getSelectRes());
                }
                TextView tv = tvs[idx];
                if (tv != null) tv.setTextColor(tabBean.getSelectColor());
            }
        }
        changeFragment(idx);
    }

    private void showDefaultTab() {
        TabBean tabBean0 = tabList.get(0);
        img0.setBackgroundResource(tabBean0.getNorRes());
        tv0.setText(tabBean0.getTitle());
        tv0.setTextColor(tabBean0.getNorColor());
        Log.e(TAG, "showDefaultTab: " + tabBean0 + " tv0.getText() " + tv0.getText());

        TabBean tabBean2 = tabList.get(2);
        img2.setBackgroundResource(tabBean2.getNorRes());
        tv2.setText(tabBean2.getTitle());
        tv2.setTextColor(tabBean2.getNorColor());
        Log.e(TAG, "showDefaultTab: " + tabBean2 + " tv2.getText = " + tv2.getText());

        img_home.setBackgroundResource(tabList.get(1).getNorRes());
    }

    private void changeTab() {
        if (tabList == null) tabList = new ArrayList<>();
        tabList.clear();
        TabBean tab0 = new TabBean(s1, s2, "VIPDyna", getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_dark));
        TabBean tab1 = new TabBean();
        tab1.setNorRes(R.mipmap.qian_icon);
        TabBean tab2 = new TabBean(s3, s4, "我的Dyna", getResources().getColor(android.R.color.black), getResources().getColor(android.R.color.holo_red_dark));
        tabList.add(tab0);
        tabList.add(tab1);
        tabList.add(tab2);
        showTab();
    }

    private void showTab() {
        TabBean tabbean0 = tabList.get(0);

        if (img0 != null) {
            img0.setBackgroundResource(0);
            img0.setBackground(null);
            Glide.with(this).load(mCurrentTabId == 0 ? tabbean0.getNorResUrl() : tabbean0.getSelectResUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (resource != null) {
                        int x = 63 * screen_width / 1080;
                        int y = x * resource.getHeight() / resource.getWidth();
                        ViewGroup.LayoutParams params = img0.getLayoutParams();
                        params.width = x;
                        params.height = y;
                        img0.setLayoutParams(params);
                        img0.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        img0.setImageBitmap(resource);
                    }
                }
            });
        }
        if (tv0 != null) {
            tv0.setText(tabbean0.getTitle());
            if (mCurrentTabId == 0) {
                tv0.setTextColor(tabbean0.getSelectColor());
            } else {
                tv0.setTextColor(tabbean0.getNorColor());
            }
        }

        TabBean tabbean2 = tabList.get(2);

        if (img2 != null) {
            img2.setBackgroundResource(0);
//            img2.setBackground(null);
            Glide.with(this).load(mCurrentTabId == 2 ? tabbean2.getNorResUrl() : tabbean2.getSelectResUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                    if (resource != null) {
                        int x = 63 * screen_width / 1080;
                        int y = x * resource.getHeight() / resource.getWidth();
                        ViewGroup.LayoutParams params = img2.getLayoutParams();
                        params.width = x;
                        params.height = y;
                        img2.setLayoutParams(params);
                        img2.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        img2.setImageBitmap(resource);
                    }
                }
            });
        }
        if (tv2 != null) {
            tv2.setText(tabbean2.getTitle());
            if (mCurrentTabId == 2) {
                tv2.setTextColor(tabbean2.getSelectColor());
            } else {
                tv2.setTextColor(tabbean2.getNorColor());
            }
        }

    }

    private void changeFragment(int idx) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        mFragmentList.get(mCurrentTabId).onPause();
        ft.hide(mFragmentList.get(mCurrentTabId));

        Fragment fragment;

        String fragmentTag;

        switch (idx) {
            case 0:// 选择血糖
                fragment = fr1;
                fragmentTag = FR1_TAG;
                break;
            case 1:// 选择问医
                fragment = fr2;
                fragmentTag = FR2_TAG;
                break;
            case 2:// 选择公开课
                fragment = fr3;
                fragmentTag = FR3_TAG;
                break;
            default:
                fragment = fr1;
                fragmentTag = FR1_TAG;
                break;
        }
        if (fragment.isAdded()) {
            fragment.onResume();
        } else {
            ft.add(R.id.dynamic_tab_content, fragment, fragmentTag);
        }
        ft.show(fragment);
        ft.commit();
        mCurrentTabId = idx;
    }

}
