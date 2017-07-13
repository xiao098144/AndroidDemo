package com.xiao.demo.recyclerview.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.xiao.demo.recyclerview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：
 * Created on 2017/7/11
 * Author : 萧
 */
public class AnimationTestActivity extends Activity {

    private static final String TAG = "AnimationTestActivity";
    private ValueAnimator animator;

    public static void start(Context context) {
        Intent starter = new Intent(context, AnimationTestActivity.class);
        context.startActivity(starter);
    }

    @BindView(R.id.animationtest_img)
    ImageView img;

    @BindView(R.id.animationtest_btn_start)
    Button btn_start;

    @BindView(R.id.animationtest_btn_end)
    Button btn_end;

    @BindView(R.id.animationtest_btn_pause)
    Button btn_pause;

    @BindView(R.id.animationtest_btn_cancel)
    Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationtest);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.animationtest_btn_start)
    public void startAnaimation() {
        if (animator == null) {
            animator = ValueAnimator.ofFloat(0, 360);
            animator.setDuration(3000);
//            animator.setInterpolator(new LinearInterpolator());
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setRepeatCount(10);
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    Log.e(TAG, "onAnimationUpdate:  getAnimatedValue = " + value);
//                    img.setRotation(value);
                    img.setRotationX(value);
                    img.setAlpha(value / 180);
//                    img.setScaleY(value);
                }
            });
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e(TAG, "onAnimationStart: " + animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e(TAG, "onAnimationEnd: " + animation);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e(TAG, "onAnimationCancel: " + animation);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e(TAG, "onAnimationRepeat: " + animation);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                animator.addPauseListener(new Animator.AnimatorPauseListener() {
                    @Override
                    public void onAnimationPause(Animator animation) {
                        Log.e(TAG, "onAnimationPause: " + animation);
                    }

                    @Override
                    public void onAnimationResume(Animator animation) {
                        Log.e(TAG, "onAnimationResume: " + animation);
                    }
                });
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (animator.isPaused()) animator.resume();
            else
                animator.start();
        } else animator.start();
    }

    @OnClick(R.id.animationtest_btn_cancel)
    public void cancelAnimation() {
        if (animator != null) {
            animator.cancel();
        }
    }

    @OnClick(R.id.animationtest_btn_pause)
    public void pauseAnimation() {
        if (animator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                animator.pause();
            }
        }
    }

    @OnClick(R.id.animationtest_btn_end)
    public void endAnimation() {
        if (animator != null) {
            animator.end();
        }
    }


}
