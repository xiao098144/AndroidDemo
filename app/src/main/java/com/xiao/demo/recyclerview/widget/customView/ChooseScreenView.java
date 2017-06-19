package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.CharsequencePharse;
import com.xiao.demo.recyclerview.util.ResLoader;

/**
 * Description：
 * Created on 2017/3/9
 * Author : 萧
 */
public class ChooseScreenView extends LinearLayout {

    private static final String TAG = "ChooseScreenView";


    private FrameLayout layout_center;
    private ImageView img_center;
    private TextView tv_center;

    private ImageView img_top;
    private TextView tv_top;

    private ImageView img_left;
    private TextView tv_left;

    private ImageView img_right;
    private TextView tv_right;

    private ImageView img_bottom;
    private TextView tv_bottom;

    private int areaColor;

    private int selectColor;

    private int defColor;

    private int seatStateUnNormalRes;

    private int seatStateNormalRes;

    private int areaCenter = 100, areaLeft = 100, areaRight = 100, areaBottom = 100, areaTop = 100;

    private int countCenter = 100, countLeft = 100, countRight = 100, countBottom = 100, countTop = 100;
    private SparseArray<Integer> colors;
    private SparseArray<Integer> textsizes;

    public ChooseScreenView(Context context) {
        super(context);
        Log.e(TAG, "ChooseScreenView: ");
        initView(context);
    }

    public ChooseScreenView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ChooseScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        Log.e(TAG, "initView: begin");
        View view = LayoutInflater.from(context).inflate(R.layout.layout_choosescreen, this);
        layout_center = (FrameLayout) view.findViewById(R.id.choosescreen_layout_center);
        img_center = (ImageView) view.findViewById(R.id.choosescreen_img_center);
        tv_center = (TextView) view.findViewById(R.id.choosescreen_tv_center);

        img_left = (ImageView) view.findViewById(R.id.choosescreen_img_left);
        tv_left = (TextView) view.findViewById(R.id.choosescreen_tv_left);

        img_right = (ImageView) view.findViewById(R.id.choosescreen_img_right);
        tv_right = (TextView) view.findViewById(R.id.choosescreen_tv_right);

        img_top = (ImageView) view.findViewById(R.id.choosescreen_img_top);
        tv_top = (TextView) view.findViewById(R.id.choosescreen_tv_top);

        img_bottom = (ImageView) view.findViewById(R.id.choosescreen_img_bottom);
        tv_bottom = (TextView) view.findViewById(R.id.choosescreen_tv_bottom);

        OnClickListener onClickListener = new OnClickListener();
        layout_center.setOnClickListener(onClickListener);
        img_top.setOnClickListener(onClickListener);
        img_bottom.setOnClickListener(onClickListener);
        img_left.setOnClickListener(onClickListener);
        img_right.setOnClickListener(onClickListener);
        Log.e(TAG, "initView: end");
        initDefContent();

    }

    private void initDefContent() {
        Log.e(TAG, "initDefContent: begin");
        seatStateNormalRes = R.mipmap.onlive_chose_seat;
        seatStateUnNormalRes = R.mipmap.onlive_chose_seat_unselected;
        areaColor = Color.parseColor("#8dc670");
        defColor = Color.parseColor("#7d7d7d");
        selectColor = Color.parseColor("#ff584b");
        colors = new SparseArray<>();
        colors.put(1, areaColor);
        colors.put(2, selectColor);
        colors.put(3, defColor);
        textsizes = new SparseArray<>();
        textsizes.put(1, 13);
        tv_center.setText(initTitle("A区\n", areaCenter, countCenter));
        tv_top.setText(initTitle("E区", areaTop, countTop));
        tv_left.setText(initTitle("B区\n", areaLeft, countLeft));
        tv_right.setText(initTitle("C区\n", areaRight, countRight));
        tv_bottom.setText(initTitle("D区", areaBottom, countBottom));
        tv_top.setCompoundDrawablesWithIntrinsicBounds(null, null, ResLoader.Drawable(getContext(), seatStateNormalRes), null);
        tv_bottom.setCompoundDrawablesWithIntrinsicBounds(null, null, ResLoader.Drawable(getContext(), seatStateNormalRes), null);
        tv_center.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), seatStateNormalRes));
        tv_left.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), seatStateNormalRes));
        tv_right.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), seatStateNormalRes));
        Log.e(TAG, "initDefContent: end");
    }

    private CharSequence initTitle(String title, int currentValue, int count) {
        SparseArray<String> contents = new SparseArray<>();
        contents.put(1, title);
        contents.put(2, String.valueOf(currentValue));
        contents.put(3, "/" + String.valueOf(count));
        return CharsequencePharse.init().setColors(colors).setContents(contents).setTextSizes(textsizes).format();
    }

    public interface ScreenSelectedListener {
        void onScreenSelected(int position);
    }

    public ScreenSelectedListener onScreenSelectedListener;

    public void setOnScreenSelectedListener(ScreenSelectedListener onScreenSelectedListener) {
        this.onScreenSelectedListener = onScreenSelectedListener;
    }

    class OnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.choosescreen_layout_center:
                    if (onScreenSelectedListener != null) {
                        onScreenSelectedListener.onScreenSelected(0);
                    }
                    Toast.makeText(getContext(), "选择A区", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.choosescreen_img_bottom:
                    if (onScreenSelectedListener != null) {
                        onScreenSelectedListener.onScreenSelected(3);
                    }
                    Toast.makeText(getContext(), "选择D区", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.choosescreen_img_top:
                    if (onScreenSelectedListener != null) {
                        onScreenSelectedListener.onScreenSelected(4);
                    }
                    Toast.makeText(getContext(), "选择E区", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.choosescreen_img_left:
                    if (onScreenSelectedListener != null) {
                        onScreenSelectedListener.onScreenSelected(1);
                    }
                    Toast.makeText(getContext(), "选择B区", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.choosescreen_img_right:
                    if (onScreenSelectedListener != null) {
                        onScreenSelectedListener.onScreenSelected(2);
                    }
                    Toast.makeText(getContext(), "选择C区", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    public void updateLeftSeats(int areaCenter, int areaLeft, int areaRight, int areaBottom, int areaTop) {
        this.areaCenter = areaCenter;
        this.areaLeft = areaLeft;
        this.areaRight = areaRight;
        this.areaBottom = areaBottom;
        this.areaTop = areaTop;
        tv_center.setText(initTitle("A区", areaCenter, countCenter));
        tv_top.setText(initTitle("E区", areaTop, countTop));
        tv_left.setText(initTitle("B区", areaLeft, countLeft));
        tv_right.setText(initTitle("C区", areaRight, countRight));
        tv_bottom.setText(initTitle("D区", areaBottom, countBottom));
        tv_top.setCompoundDrawablesWithIntrinsicBounds(null, null, ResLoader.Drawable(getContext(), areaTop == countTop ? seatStateUnNormalRes : seatStateNormalRes), null);
        tv_bottom.setCompoundDrawablesWithIntrinsicBounds(null, null, ResLoader.Drawable(getContext(), areaBottom == countBottom ? seatStateUnNormalRes : seatStateNormalRes), null);
        tv_center.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), areaCenter == countCenter ? seatStateUnNormalRes : seatStateNormalRes));
        tv_left.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), areaLeft == countLeft ? seatStateUnNormalRes : seatStateNormalRes));
        tv_right.setCompoundDrawablesWithIntrinsicBounds(null, null, null, ResLoader.Drawable(getContext(), areaRight == countRight ? seatStateUnNormalRes : seatStateNormalRes));
    }

    public int getCountCenter() {
        return countCenter;
    }

    public void setCountCenter(int countCenter) {
        this.countCenter = countCenter;
    }

    public int getCountLeft() {
        return countLeft;
    }

    public void setCountLeft(int countLeft) {
        this.countLeft = countLeft;
    }

    public int getCountRight() {
        return countRight;
    }

    public void setCountRight(int countRight) {
        this.countRight = countRight;
    }

    public int getCountBottom() {
        return countBottom;
    }

    public void setCountBottom(int countBottom) {
        this.countBottom = countBottom;
    }

    public int getCountTop() {
        return countTop;
    }

    public void setCountTop(int countTop) {
        this.countTop = countTop;
    }

    public int getAreaColor() {
        return areaColor;
    }

    public void setAreaColor(int areaColor) {
        this.areaColor = areaColor;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

    public int getDefColor() {
        return defColor;
    }

    public void setDefColor(int defColor) {
        this.defColor = defColor;
    }

    public int getSeatStateUnNormalRes() {
        return seatStateUnNormalRes;
    }

    public void setSeatStateUnNormalRes(int seatStateUnNormalRes) {
        this.seatStateUnNormalRes = seatStateUnNormalRes;
    }

    public int getSeatStateNormalRes() {
        return seatStateNormalRes;
    }

    public void setSeatStateNormalRes(int seatStateNormalRes) {
        this.seatStateNormalRes = seatStateNormalRes;
    }

    public int getAreaCenter() {
        return areaCenter;
    }

    public void setAreaCenter(int areaCenter) {
        this.areaCenter = areaCenter;
    }

    public int getAreaLeft() {
        return areaLeft;
    }

    public void setAreaLeft(int areaLeft) {
        this.areaLeft = areaLeft;
    }

    public int getAreaRight() {
        return areaRight;
    }

    public void setAreaRight(int areaRight) {
        this.areaRight = areaRight;
    }

    public int getAreaBottom() {
        return areaBottom;
    }

    public void setAreaBottom(int areaBottom) {
        this.areaBottom = areaBottom;
    }

    public int getAreaTop() {
        return areaTop;
    }

    public void setAreaTop(int areaTop) {
        this.areaTop = areaTop;
    }

}
