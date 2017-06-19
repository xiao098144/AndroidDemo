package com.xiao.demo.recyclerview.architectture.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.design.CardViewActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description：
 * Created on 2017/4/14
 * Author : 萧
 */
public class MVPActivity extends Activity {

    @BindView(R.id.mvp1_tv_1)
    TextView tv1;

    @BindView(R.id.mvp1_tv_2)
    TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp1);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.mvp1_tv_1)
    public void onClick() {
        CardViewActivity.startActivityForResult(this);
    }
}
