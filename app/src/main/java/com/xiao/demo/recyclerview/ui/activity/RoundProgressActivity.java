package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.widget.RoundProgressBar2;
import com.xiao.demo.recyclerview.util.CharsequencePharse;

import java.util.Random;

public class RoundProgressActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, RoundProgressActivity.class);
        context.startActivity(starter);
    }

    TextView tv;

    private SparseArray<String> contents = new SparseArray<>();

    private SparseArray<Integer> colors = new SparseArray<>();

    private SparseArray<Integer> textSizes = new SparseArray<>();

    RoundProgressBar2 roundProgressBar2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tv = (TextView) findViewById(R.id.tv);
        roundProgressBar2 = (RoundProgressBar2) findViewById(R.id.roundprogress);
        ViewGroup.LayoutParams params = roundProgressBar2.getLayoutParams();
        roundProgressBar2.setLayoutParams(params);
        roundProgressBar2.setIntervalAngle(70);
        roundProgressBar2.setProgress(75);

        for (int i = 0 ;i<5;i++){
            contents.put(i,"valueAt"+i+"\n");
            if (i%3==0) {
                Random random = new Random();
                int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16 + i + random.nextInt(20), getResources().getDisplayMetrics());
                Log.e("", "onCreate: siez "+size);
                textSizes.put(i, size);
            }
        }
        colors.put(0,getResources().getColor(R.color.blue));
        colors.put(3,getResources().getColor(R.color.yellow));
        colors.put(6, Color.GREEN);
        tv.setText(CharsequencePharse.init().setColors(colors).setContents(contents).setTextSizes(textSizes).format());
    }
}
