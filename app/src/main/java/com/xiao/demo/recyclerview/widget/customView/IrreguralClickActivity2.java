package com.xiao.demo.recyclerview.widget.customView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiao.demo.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：
 * Created on 2017/3/6
 * Author : 萧
 */
public class IrreguralClickActivity2 extends Activity implements View.OnClickListener, ChooseScreenView.ScreenSelectedListener {

    @BindView(R.id.a)
    FrameLayout layout_a;

    @BindView(R.id.aa)
    ImageView img_a;

    @BindView(R.id.bb)
    ImageView img_b;

    @BindView(R.id.cc)
    ImageView img_c;

    @BindView(R.id.dd)
    ImageView img_d;

    @BindView(R.id.e)
    ImageView img_e;

    @BindView(R.id.choosescrrenview)
    ChooseScreenView chooseScreenView;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.layout_indicator)
    LinearLayout layout_indicator;

    List<ChooseScreenView> list;

    int selectPosition = 0;

    public static void start(Context context) {
        Intent starter = new Intent(context, IrreguralClickActivity2.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irreguralclick2);
        ButterKnife.bind(this);
        layout_a.setOnClickListener(this);
        img_b.setOnClickListener(this);
        img_c.setOnClickListener(this);
        img_d.setOnClickListener(this);
        img_e.setOnClickListener(this);

        ChooseScreenView chooseScreenView1 = new ChooseScreenView(this);
        ChooseScreenView chooseScreenView2 = new ChooseScreenView(this);
        ChooseScreenView chooseScreenView3 = new ChooseScreenView(this);
        ChooseScreenView chooseScreenView4 = new ChooseScreenView(this);
        ChooseScreenView chooseScreenView5 = new ChooseScreenView(this);
        chooseScreenView1.setOnScreenSelectedListener(this);
        chooseScreenView2.setOnScreenSelectedListener(this);
        chooseScreenView3.setOnScreenSelectedListener(this);
        chooseScreenView4.setOnScreenSelectedListener(this);
        chooseScreenView5.setOnScreenSelectedListener(this);
        list = new ArrayList<>();
        list.add(chooseScreenView1);
        list.add(chooseScreenView2);
        list.add(chooseScreenView3);
        list.add(chooseScreenView4);
        list.add(chooseScreenView5);
        ViewPagerAdapter adapter = new ViewPagerAdapter(list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(selectPosition);

        for (int i = 0; i < 5; i++) {
            ImageView img = new ImageView(IrreguralClickActivity2.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(9, 9);
            params.setMargins(0, 3, 7, 3);
            img.setLayoutParams(params);
            img.setImageResource(R.drawable.circle_unselected);
            layout_indicator.addView(img);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "onPageSelected: position ");
                layout_indicator.getChildAt(selectPosition);
                View view = layout_indicator.getChildAt(position);
                if (view != null && view instanceof ImageView) {
                    ImageView img = (ImageView) view;
                    img.setImageResource(0);
                    img.setImageResource(R.drawable.circle_selected);
                }
                selectPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.a:
                chooseScreenView.updateLeftSeats(12, 23, 100, 100, 78);
                Toast.makeText(IrreguralClickActivity2.this, "点击图片A", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bb:
                Random random = new Random();
                for (int i = 0; i < viewPager.getChildCount(); i++) {
                    View view = viewPager.getChildAt(i);
                    if (view instanceof ChooseScreenView) {
                        ChooseScreenView chooseScreenView = (ChooseScreenView) view;
                        chooseScreenView.updateLeftSeats(random.nextInt(30) + 1, random.nextInt(50) + 1, random.nextInt(100) + 1, random.nextInt(100) + 1, 100);
                    }
                }
                Toast.makeText(IrreguralClickActivity2.this, "点击图片B", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cc:
                Toast.makeText(IrreguralClickActivity2.this, "点击图片C", Toast.LENGTH_SHORT).show();
                break;
            case R.id.dd:
                Toast.makeText(IrreguralClickActivity2.this, "点击图片D", Toast.LENGTH_SHORT).show();
                break;
            case R.id.e:
                Toast.makeText(IrreguralClickActivity2.this, "点击图片E", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private static final String TAG = "IrreguralClickActivity2";

    @Override
    public void onScreenSelected(int position) {
        Log.e(TAG, "onScreenSelected: position = " + position);
    }
}
