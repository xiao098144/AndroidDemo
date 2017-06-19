package com.xiao.demo.recyclerview.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.widget.FlexboxLayout;
import com.xiao.demo.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlexBoxActivity extends AppCompatActivity {

    FlexboxLayout flexboxLayout;

    Button btn_add, btn_minus;

    private List<String> list = new ArrayList<>();


    public static void start(Context context) {
        Intent starter = new Intent(context, FlexBoxActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box);
        list.add("血糖值偏低，请注意饮食，保持生活规律，常测血糖血糖值偏低，请注意饮食，保持生活规律，常测血糖");
        list.add("备注");
        list.add("系统夏天天天\n夏天天天天天");
        list.add("立即咨询");
        list.add("病历内容不能为空");
        list.add("意见修改");
        list.add("正常");
        list.add("请写下关于病历的简单描述");
        list.add("请坐");
        list.add("sit down");
        list.add("shutdown");
        list.add("shutup");
        list.add("偏高");
        list.add("I Love you ");
        list.add("I'm fine , and you ?");
        list.add("hello");
        list.add("Nice to meet tou");
        list.add("你好");
        list.add("我很好");

        flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_add = (Button) findViewById(R.id.btn_add);
        if (btn_add != null) {
            btn_add.setOnClickListener((v) -> {
                TextView textView = createTextView();
                textView.setLayoutParams(createLayoutParams());
                flexboxLayout.addView(textView);
            });
        }
        btn_minus.setOnClickListener((v) -> {
            if (flexboxLayout.getChildCount() > 0) {
                flexboxLayout.removeViewAt(flexboxLayout.getChildCount() - 1);
            }
        });
//        for (int i = 0; i <5;i++){
//            TextView textView = new TextView(FlexBoxActivity.this);
//            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
//            textView.setText("text-"+i);
//            textView.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));
//            textView.setLayoutParams(params);
//            flexboxLayout.addView(textView);
//        }
    }

    private TextView createTextView() {
        TextView tv = new TextView(FlexBoxActivity.this);
        tv.setPadding(20,10,20,10);
        tv.setBackgroundResource(R.drawable.shape);
        tv.setGravity(Gravity.CENTER);
        tv.setText(list.get(new Random().nextInt(list.size())));
        tv.setOnClickListener((v) -> {
            Toast.makeText(FlexBoxActivity.this,tv.getText(),Toast.LENGTH_SHORT).show();
        });
        return tv;
    }

    private FlexboxLayout.LayoutParams createLayoutParams() {
        FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT, FlexboxLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(5,5,5,5);
        return params;
    }

}
