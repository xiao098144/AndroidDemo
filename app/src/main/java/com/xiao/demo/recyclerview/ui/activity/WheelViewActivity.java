package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.widget.wheelview.NumericWheelAdapter;
import com.xiao.demo.recyclerview.widget.wheelview.WheelView;

public class WheelViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, WheelViewActivity.class);
        context.startActivity(starter);
    }

    private  static  final  String TAG = "WheelViewActivity";

    Button btn_result;
    TextView tv_result;

    WheelView wheelview1, wheelview2;

    NumericWheelAdapter adapter1,adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_view);
        btn_result = (Button) findViewById(R.id.btn_result);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_result.setOnClickListener((v) -> {
            tv_result.setText("");
            Toast.makeText(WheelViewActivity.this,"wheel1 "+adapter1.getItemText(wheelview1.getCurrentItem())+"\n"+"wheel2 "+adapter2.getItemText(wheelview2.getCurrentItem()),Toast.LENGTH_SHORT).show();
            Log.e(TAG, "onCreate: "+"wheel1 "+adapter1.getItemText(wheelview1.getCurrentItem())+"\n"+"wheel2 "+adapter2.getItemText(wheelview2.getCurrentItem()));
            tv_result.setText("wheel1 "+adapter1.getItemText(wheelview1.getCurrentItem())+"\n"+"wheel2 "+adapter2.getItemText(wheelview2.getCurrentItem()));
        });
        wheelview1 = (WheelView) findViewById(R.id.wheelview1);
        wheelview2 = (WheelView) findViewById(R.id.wheelview2);
        adapter1 = new NumericWheelAdapter(WheelViewActivity.this,30,120,"%d kg");
        adapter2 = new NumericWheelAdapter(WheelViewActivity.this,60,270,"%d cm");
        wheelview1.setCurrentItem(5,true);
        wheelview2.setCurrentItem(5,true);
        wheelview1.setVisibleItems(7);
        wheelview2.setVisibleItems(7);
        wheelview1.setViewAdapter(adapter1);
        wheelview2.setViewAdapter(adapter2);

    }
}
