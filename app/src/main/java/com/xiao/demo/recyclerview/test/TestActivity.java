package com.xiao.demo.recyclerview.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.animation.BounceTopEnter;
import com.xiao.demo.recyclerview.widget.customView.CalendarRecordView;
import com.xiao.demo.recyclerview.widget.customView.IntegralDialog;
import com.xiao.demo.recyclerview.widget.customView.IntegralRecordView;
import com.xiao.demo.recyclerview.util.AppUtil;
import com.xiao.demo.recyclerview.util.CharsequencePharse;
import com.xiao.demo.recyclerview.util.ColorPhrase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @BindView(R.id.button)
    Button btn;

    @BindView(R.id.test_root)
    RelativeLayout root;

    @BindView(R.id.integral_tv_rules)
    TextView tv_rules;

    @BindView(R.id.test_integralrecordview)
    IntegralRecordView integralRecordView;

    @BindView(R.id.test_calendarrecordview)
    CalendarRecordView calendarRecordView;

    @BindView(R.id.integral_bg_layout)
    LinearLayout layout_bg;

    @BindView(R.id.integral_tv_left)
    TextView tv_left;
    @BindView(R.id.integral_tv_middle)
    TextView tv_middle;
    @BindView(R.id.integral_tv_right)
    TextView tv_right;
    @BindView(R.id.integral_tv_content)
    TextView tv_content;
    @BindView(R.id.layout)
    LinearLayout layout;

    @BindView(R.id.cut)
    View cut;

    String rules = "1.积分专属糖医生，仅限糖医生APP内部使用;\n2.在购物商城可以{100积分=1元}抵扣现金;"
            + "\n3.签到可以获取积分，每个用户一天可以签到一次;" + "\n4.每次签到获取{5积分}，连续签到可以获取积分翻倍加成，最高为{40积分};" +
            "\n5.积分有效期为一年，每年(自然年)最后一天要扣除剩余积分的{50%};";

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        btn.setOnClickListener((v) -> {
            showPopupWindow();
        });
        tv_rules.setText(ColorPhrase.from(rules).innerColor(Color.parseColor("#FF7373")).format());
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(5);
        list.add(10);
        list.add(15);
        calendarRecordView.setValue(list);
        layout_bg.setBackgroundResource(R.mipmap.integral_bg);
//        ViewGroup.LayoutParams params = layout_bg.getLayoutParams();
//        params.width = 700 * AppUtil.getScreenWidth(TestActivity.this) / 1080;
//        params.height = 732 * AppUtil.getScreenWidth(TestActivity.this) / 1080;

        tv_left.setText(String.valueOf(0));
        tv_middle.setText(String.valueOf(0));
        tv_right.setText(String.valueOf(3));
        SparseArray<String> contents = new SparseArray<>();
        SparseArray<Integer> colors = new SparseArray<>();
        SparseArray<Integer> textSizes = new SparseArray<>();
        contents.put(0, "当前总积分");
        contents.put(1, "\n3675");
        contents.put(2, "\n明日登录可领");
        contents.put(3, "20");
        contents.put(4, "积分");
        contents.put(5, "\n请继续保持哦");
        colors.put(1, Color.YELLOW);
        colors.put(3, Color.RED);
        textSizes.put(1, 35);
        tv_content.setText(CharsequencePharse.init().setTextSizes(textSizes).setColors(colors).setContents(contents).format());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int width = layout.getWidth();
        ViewGroup.LayoutParams params1 = cut.getLayoutParams();
        params1.width = width;
        ViewGroup.LayoutParams params = layout_bg.getLayoutParams();
        int i = 700 * AppUtil.getScreenWidth(TestActivity.this) / 1080;
        Log.e(TAG, "onWindowFocusChanged: width = " + width + " i = " + i);
//        params.width = i < width ? width + 60 : i;
//        params.width = 700 * AppUtil.getScreenWidth(TestActivity.this) / 1080;
    }

    private void showPopupWindow() {
//        View view = LayoutInflater.from(TestActivity.this).inflate(R.layout.layout_integral, null);
//        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,AppUtil.getScreenHeight(TestActivity.this)/2);

//        Dialog dialog = new Dialog(TestActivity.this,R.style.NoTitleDialog);
//        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(false);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setAnimationStyle(R.style.popup_in);
//        popupWindow.showAtLocation(root, Gravity.CENTER_HORIZONTAL, 0, 0);
//        setAlpha(TestActivity.this, WINDOW_ALPHA_DARK);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//            @Override
//            public void onDismiss() {
//                setAlpha(TestActivity.this, 1f);
//            }
//        });
//        ImageView img_scale = (ImageView) view.findViewById(R.id.integral_img_scale);
//        TextView tv_value = (TextView) view.findViewById(R.id.integral_tv_value);
        SparseArray<Integer> colors = new SparseArray<>();
        SparseArray<Integer> textsizes = new SparseArray<>();
        SparseArray<String> contents = new SparseArray<>();
        contents.put(1, "您已连续登录");
        contents.put(2, "30");
        contents.put(3, "天");
        contents.put(4, "\n+");
        contents.put(5, "20");
        contents.put(6, "积分");
        colors.put(5, Color.parseColor("#FFFF00"));
        textsizes.put(1, 15);
        textsizes.put(3, 15);
        textsizes.put(4, 32);
        textsizes.put(2, 28);
        textsizes.put(5, 64);
        textsizes.put(6, 28);
        IntegralDialog dialog = new IntegralDialog(TestActivity.this);
        dialog.setColors(colors);
        dialog.setTextsizes(textsizes);
        dialog.setContents(contents);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.autoDismiss(true).autoDismissDelay(1800).showAnim(new BounceTopEnter()).show();


//        ImageView img_close = (ImageView) view.findViewById(R.id.integral_img_close);
//
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.width = WindowManager.LayoutParams.MATCH_PARENT;
//        params.height = AppUtil.getScreenHeight(TestActivity.this)/2;
//        dialog.getWindow().setAttributes(params);
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, AppUtil.getScreenHeight(TestActivity.this)/2);
//        window.setWindowAnimations(R.style.popup_in); //设置窗口弹出动画
//        dialog.show();

//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                setAlpha(TestActivity.this, 1f);
//            }
//        });
//        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            @Override
//            public void onShow(DialogInterface dialog) {
//                Animation myAnimation = AnimationUtils.loadAnimation(TestActivity.this, R.anim.img_scale);
//                myAnimation.setFillAfter(true);
//                img_scale.startAnimation(myAnimation);
//                Log.e(TAG, "onShow: startAnimation ");
//
//            }
//        });
//        dialog.setContentView(view);
//        dialog.setCancelable(false);
    }

    /**
     * 弹框时设置背景 阴影度 50%
     */
    private static final float WINDOW_ALPHA_DARK = 0.5f;

    private static void setAlpha(Activity context, float alpha) {
        WindowManager.LayoutParams params = context.getWindow().getAttributes();
        params.alpha = alpha;
        context.getWindow().setAttributes(params);
    }

}
