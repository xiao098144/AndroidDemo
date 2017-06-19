package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;

public class DrawerLayoutActivity extends AppCompatActivity {

    @BindView(R.id.drawerlayout_content)
    FrameLayout layout_content;

    @BindView(R.id.drawerlayout_drawerlayout)
    DrawerLayout drawerLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, DrawerLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
    }
}
