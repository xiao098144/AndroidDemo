package com.xiao.demo.recyclerview.design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedScrollActivity extends AppCompatActivity {

    private static final String TAG = "NestedScrollActivity";

    @BindView(R.id.nested_scroll_title)
    Toolbar titleBar;

    @BindView(R.id.nested_scroll_coordinator)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.nested_scroll_appbarlayout)
    AppBarLayout appBarLayout;

    @BindView(R.id.nested_scroll_img)
    ImageView img;

    @BindView(R.id.nested_scroll_toolbar_scroll)
    Toolbar toolbar_scroll;
    @BindView(R.id.nested_scroll_toolbar_enteralways)
    Toolbar toolbar_enteralways;
    @BindView(R.id.nested_scroll_toolbar_eac)
    Toolbar toolbar_eac;
    @BindView(R.id.nested_scroll_toolbar_euc)
    Toolbar toolbar_euc;
    @BindView(R.id.nested_scroll_toolbar_snap)
    Toolbar toolbar_snap;
    @BindView(R.id.nested_scroll_toolbar_invisible)
    Toolbar toolbar_invisible;

    @BindView(R.id.nested_scroll_scrollview)
    NestedScrollView nestedScrollView;

    public static void start(Context context) {
        Intent starter = new Intent(context, NestedScrollActivity.class);
        context.startActivity(starter);
    }

    int lastVerticalOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll);
        ButterKnife.bind(this);
        titleBar.setTitle("NestedScrollTitle");
        setSupportActionBar(titleBar);
        titleBar.setNavigationIcon(R.mipmap.redpacket_navigationbar_back);
        toolbar_eac.setOnClickListener((v) -> {
//            ToolBarActivity.start(NestedScrollActivity.this);
        });
        titleBar.setNavigationOnClickListener((v) -> {
            Toast.makeText(NestedScrollActivity.this, "titleBar Navigation Clicked ", Toast.LENGTH_SHORT).show();
        });
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            Log.e(TAG, "onCreate: appBarLayout.addOnOffsetChangedListener  verticalOffset " + verticalOffset + " img.getHeight " + img.getHeight() + " titleBar.getHeight " + titleBar.getHeight() + " " + (verticalOffset + img.getHeight()));
//            if (verticalOffset <= 0) {
                boolean isScrollUp = lastVerticalOffset < verticalOffset;
                if (verticalOffset + img.getHeight() <= titleBar.getHeight()) {
                    toolbar_invisible.setVisibility(isScrollUp ? View.INVISIBLE : View.GONE);
                    titleBar.setBackgroundColor(getResources().getColor(android.R.color.white));
                    titleBar.setTitleTextColor(getResources().getColor(android.R.color.darker_gray));
                    titleBar.setNavigationIcon(R.mipmap.navigationbar_back_highlighted);
                } else {
                    toolbar_invisible.setVisibility(View.GONE);
                    if (verticalOffset >0) {
                        titleBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        titleBar.setTitleTextColor(getResources().getColor(android.R.color.darker_gray));
                        titleBar.setNavigationIcon(R.mipmap.navigationbar_back_highlighted);
                    } else {
                        titleBar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                        titleBar.setTitleTextColor(getResources().getColor(android.R.color.white));
                        titleBar.setNavigationIcon(R.mipmap.redpacket_navigationbar_back);
                    }
                }
                lastVerticalOffset = verticalOffset;
//            }
        });

    }
}
