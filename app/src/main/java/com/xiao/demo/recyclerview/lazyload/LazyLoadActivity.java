package com.xiao.demo.recyclerview.lazyload;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.xiao.demo.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 * Created on 2017/4/5
 * Author : 萧
 */
public class LazyLoadActivity extends FragmentActivity {

    private static final String TAG = "LazyLoadActivity";

    /*@BindView(R.id.drawerlayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.drawer_menu)
    FrameLayout layout_menu;

    @BindView(R.id.lazyload_img)
    ImageView img;*/

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<FragmentHolder> list = new ArrayList<>();

    LazyViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazyload);
        ButterKnife.bind(this);
        for (int i = 0; i < 20; i++) {
            FragmentHolder holder = new FragmentHolder();
            holder.setTitle("TAB" + i);
            LazyLoadFragment fragment = LazyLoadFragment.newInstance("TAB" + i, i);
            if (i == 0) fragment.setForceLoad(true);
            holder.setFragment(fragment);
            list.add(holder);
        }
        adapter = new LazyViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentList(list);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabSelected: tab.getPosition() = " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabUnselected: tab.getPosition() = " + tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabReselected: tab.getPosition() " + tab.getPosition());
            }
        });

    }

    public static void start(Context context) {
        Intent starter = new Intent(context, LazyLoadActivity.class);
        context.startActivity(starter);
    }

    //    @OnClick(R.id.lazyload_img)
//    public void onclick() {
//        drawerLayout.openDrawer(GravityCompat.START, true);
//    }


}
