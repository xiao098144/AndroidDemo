package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.ui.activity.BaseActivity;
import com.xiao.demo.recyclerview.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 * Created on 2017/3/2
 * Author : 萧
 */
public class IrreguralClickActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, IrreguralClickActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.img1)
    ImageView img1;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.img2)
    ImageView img2;

    @BindView(R.id.tv2)
    TextView tv2;

    @BindView(R.id.img3)
    ImageView img3;

    @BindView(R.id.tv3)
    TextView tv3;

    @BindView(R.id.img4)
    ImageView img4;

    @BindView(R.id.tv4)
    TextView tv4;

    @BindView(R.id.img5)
    ImageView img5;

    @BindView(R.id.tv5)
    TextView tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irreguralclick);
        ButterKnife.bind(this);
        Random random = new Random();
        img1.setOnClickListener(v -> {
            int i = random.nextInt(2) + 1;
            tv1.setText("A区\n" + (i * 10) + "/" + (i * 100));
            Toast.makeText(IrreguralClickActivity.this, "img1 Click ", Toast.LENGTH_SHORT).show();
        });

        img2.setOnClickListener(v -> {
            int i = random.nextInt(2) + 1;
            tv2.setText("B区\n" + (i * 10) + "/" + (i * 100));
            Toast.makeText(IrreguralClickActivity.this, "img2 Click ", Toast.LENGTH_SHORT).show();
        });

        img3.setOnClickListener(v -> {
            int i = random.nextInt(2) + 1;
            tv3.setText("C区\n" + (i * 10) + "/" + (i * 100));
            Toast.makeText(IrreguralClickActivity.this, "img3 Click ", Toast.LENGTH_SHORT).show();
        });

        img4.setOnClickListener(v -> {
            int i = random.nextInt(2) + 1;
            tv4.setText("D区 " + (i * 10) + "/" + (i * 100));
            Toast.makeText(IrreguralClickActivity.this, "img4 Click ", Toast.LENGTH_SHORT).show();
        });

        img5.setOnClickListener(v -> {
            int i = random.nextInt(2) + 1;
            tv5.setText("E区 " + (i * 10) + "/" + (i * 100));
            Toast.makeText(IrreguralClickActivity.this, "img5 Click ", Toast.LENGTH_SHORT).show();
        });


    }
}
