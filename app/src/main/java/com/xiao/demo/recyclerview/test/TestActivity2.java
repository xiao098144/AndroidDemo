package com.xiao.demo.recyclerview.test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiao.demo.recyclerview.R;

public class TestActivity2 extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
    }
}
