package com.xiao.demo.recyclerview.design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends AppCompatActivity {

    public static final String TAG = "ToolBarActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, ToolBarActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.toolbar)Toolbar toolbar;

//    @BindView(R.id.toolbar_tv1)TextView tv1;
//    @BindView(R.id.toolbar_tv2)TextView tv2;
//    @BindView(R.id.toolbar_tv3)TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
//        toolbar.setLogo(R.mipmap.ic_launcher);
//        toolbar.setLogoDescription("LogoDescription");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_drop_down_circle_grey600_24dp);
        toolbar.setNavigationOnClickListener((v)->{
            Log.e(TAG, "onCreate: setNavigationOnClickListener");
//            tv1.setText("toolbar.NavigationOnClick");
        });
        toolbar.setCollapsible(true);
        toolbar.setTitle("Title");
        toolbar.setSubtitle("subTitle");
    }
}
