package com.xiao.demo.recyclerview.design;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingToolBarLayoutActivity extends AppCompatActivity {

    @BindView(R.id.collapse_coordinate)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.collapse_appbar)
    AppBarLayout appBarLayout;

//    @BindView(R.id.collapse_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    public static void start(Context context) {
        Intent starter = new Intent(context, CollapsingToolBarLayoutActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_tool_bar_layout);
        ButterKnife.bind(this);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_layout);
    }
}
