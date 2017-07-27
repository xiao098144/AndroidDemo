package com.xiao.demo.recyclerview.animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectAnimationActivity extends AppCompatActivity {

    private static final String TAG = "ObjectAnimationActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, ObjectAnimationActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.animation_cb_system)
    CheckBox cb_system;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.animationtest_img)
    ImageView img;
    @BindView(R.id.animationtest_tv)
    TextView tv;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    /**
     * 系统动画
     */
    ObjectAnimator animator;

    /**
     * 系统动画
     */
    ObjectAnimator animator_tv;

    /**
     * nineoldandroid animation
     */
    com.nineoldandroids.animation.ObjectAnimator animator9;
    com.nineoldandroids.animation.ObjectAnimator animator9_tv;

    long duration = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @OnClick(R.id.animationtest_btn_start)
    public void animationtest_btn_start() {
        Log.e(TAG, "animationtest_btn_start: " + cb_system.isChecked());
        if (cb_system.isChecked()) {//使用系统动画
            if (animator == null) {
                animator = ObjectAnimator.ofFloat(img, "rotationY", 0, 360);
                animator.setRepeatMode(ObjectAnimator.RESTART);
                animator.setRepeatCount(ObjectAnimator.INFINITE);
                animator.setDuration(duration);
//                animator.setInterpolator(input -> 500);
            }
            animator.start();
            if (animator_tv == null) {

                animator_tv = ObjectAnimator.ofFloat(tv, "text", 0, 5000);
                animator_tv.setRepeatCount(ObjectAnimator.INFINITE);
                animator_tv.setRepeatMode(ObjectAnimator.REVERSE);
                animator_tv.setDuration(duration);
                Log.e(TAG, "animationtest_btn_start: " + animator_tv.getDuration());
//                animator_tv.setInterpolator(new AccelerateDecelerateInterpolator());
                animator_tv.addUpdateListener(animation -> {
//                            Log.e(TAG, "onAnimationUpdate: animation.getAnimatedValue() = " + animation.getAnimatedValue() + " animation.getAnimatedFraction() = " + animation.getAnimatedFraction());
                            tv.setText(String.valueOf(animation.getAnimatedValue()));
                        }
                );

            }
            animator_tv.start();
        } else {//使用NineOldAndroid Animation
            if (animator9 == null) {
                animator9 = com.nineoldandroids.animation.ObjectAnimator.ofFloat(img, "rotationY", 0, 360);
                animator9.setRepeatMode(com.nineoldandroids.animation.ObjectAnimator.RESTART);
                animator9.setRepeatCount(com.nineoldandroids.animation.ObjectAnimator.INFINITE);
                animator9.setDuration(duration);
            }
            animator9.start();
            if (animator9_tv == null) {
                animator9_tv = com.nineoldandroids.animation.ObjectAnimator.ofFloat(tv, "rotation", 0, 360);
                animator9_tv.setRepeatMode(ObjectAnimator.REVERSE);
                animator9_tv.setRepeatCount(com.nineoldandroids.animation.ObjectAnimator.INFINITE);
                animator9_tv.setDuration(duration);
            }
            animator9_tv.start();
        }
    }

    @OnClick(R.id.animationtest_btn_end)
    public void animationtest_btn_end() {
        if (cb_system.isChecked()) {//使用系统动画
            if (animator_tv != null) {
                animator_tv.end();
            }
            if (animator != null) {
                animator.end();
            }
        } else {//使用NineOldAndroid Animation
            if (animator9 != null) {
                animator9.end();
            }
            if (animator9_tv != null) {
                animator9_tv.end();
            }
        }
    }

    @OnClick(R.id.animationtest_btn_pause)
    public void animationtest_btn_pause() {
        if (cb_system.isChecked()) {//使用系统动画
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (animator_tv != null) {
                    animator_tv.pause();
                }
                if (animator != null) {
                    animator.pause();
                }
            }
        } else {//使用NineOldAndroid Animation
            if (animator9 != null) {
//                animator9.
            }
            if (animator9_tv != null) {
                Toast.makeText(this, "nine old android animation no pause method ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.animationtest_btn_resume)
    public void animationtest_btn_resume() {
        if (cb_system.isChecked()) {//使用系统动画
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (animator != null) {
                    animator.resume();
                }
                if (animator_tv != null) {
                    animator_tv.resume();
                }
            }
        } else {//使用NineOldAndroid Animation
            Toast.makeText(this, "nine old android animation no resume method ", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.animationtest_btn_cancel)
    public void animationtest_btn_cancel() {
        if (cb_system.isChecked()) {//使用系统动画
            if (animator_tv != null) {
                animator_tv.cancel();
            }
            if (animator != null) {
                animator.cancel();
            }
        } else {//使用NineOldAndroid Animation
            if (animator9 != null) {
                animator9.cancel();
            }
            if (animator9_tv != null) {
                animator9_tv.cancel();
            }
        }
    }


}
