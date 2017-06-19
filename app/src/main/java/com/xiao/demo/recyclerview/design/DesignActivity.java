package com.xiao.demo.recyclerview.design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.ui.activity.SimplePagerAdapter;

public class DesignActivity extends FragmentActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    SimplePagerAdapter adapter;

    private static final String TAG = "DesignActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, DesignActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.design_tablayout);

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        adapter = new SimplePagerAdapter(getSupportFragmentManager(), DesignActivity.this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: tabLayout");
            }
        });

        for (int i = 0; i < tabLayout.getChildCount(); i++) {

            int finalI = i;
            tabLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: tabLayoutChild onclick i = " + finalI);
                }
            });

        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabSelected: tab.getid " + tab.getPosition() + " tabLayout.getSelectedTabPosition() = " + tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabUnselected: tab.position = " + tab.getPosition() + " tabLayout.getSelectedTabPosition() = " + tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e(TAG, "onTabReselected: tab.position = " + tab.getPosition() + " tabLayout.getSelectedTabPosition() = " + tabLayout.getSelectedTabPosition());
            }
        });
    }
}
