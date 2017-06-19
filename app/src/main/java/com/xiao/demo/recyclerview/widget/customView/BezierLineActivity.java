package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiao.demo.recyclerview.R;

public class BezierLineActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, BezierLineActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezierline);
    }
}
