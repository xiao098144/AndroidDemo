package com.xiao.demo.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xiao.demo.recyclerview.R;

/**
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度 康
 */
public class RoundProgressBar2 extends View {
    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 圆环的颜色
     */
    private int roundColor;

    /**
     * 圆环进度的颜色
     */
    private int roundProgressColor;

    /**
     * 圆环进度条的宽度
     */
    private float roundWidth;

    /**
     * 最大进度
     */
    private int max;

    /**
     * 当前进度
     */
    private int progress;

    /**
     * 起始角度 顺时针方向 最右侧为0度
     */
    private float startAngle;

    private float intervalAngle;

    private int mDefaultRoundColor;

    private int mDefaultRoundProgressColor;

    private int mDefaultTextColor;

    private int mDefaultTextSize = 16;

    private int mDefaultMax = 100;

    private int mIntervalAngle = 45;

    private int mDefaultRoundWidth = 10;

    /**
     * 圆环内风格 实心或者空心
     */
    private int style;

    public static final int STROKE = 0; // 空心

    public static final int FILL = 1; // 实心

    public static final int STROKE_FILL = 2; // 空心但不做显示

    /**
     * 是否绘制环形进度小圆珠或对应图片
     */
    public boolean isDrawBitmapOrCircle = true;

    public RoundProgressBar2(Context context) {
        this(context, null);
    }

    public RoundProgressBar2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        paint = new Paint();
        mDefaultRoundColor = Color.parseColor("#c2c2c2");
        mDefaultRoundProgressColor = Color.YELLOW;
        mDefaultTextColor = Color.GREEN;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar2);

        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar2_roundColor, mDefaultRoundColor);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar2_roundProgressColor, mDefaultRoundProgressColor);
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar2_roundWidth, mDefaultRoundWidth);
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar2_max, mDefaultMax);

        style = mTypedArray.getInt(R.styleable.RoundProgressBar2_rpb_style, STROKE);

        isDrawBitmapOrCircle = mTypedArray.getBoolean(R.styleable.RoundProgressBar2_rpb_bitmaporcircle, true);

        mTypedArray.recycle();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画最外层的大圆环
         */
        int center = getWidth() / 2; // 获取圆心的x坐标
        int radius;
        radius = (int) (center - roundWidth / 2); // 圆环的半径

        paint.setColor(roundColor); // 设置圆环的颜色
        paint.setStyle(Paint.Style.STROKE); // 设置空心
        paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
        paint.setAntiAlias(true); // 消除锯齿

        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval,startAngle,360-intervalAngle,false,paint);

        float x, y;// 圆环上的圆x，y坐标
        // 已知圆心，半径，角度，求圆上的点坐标
        x = (float) (center + (radius) * Math.cos(startAngle * Math.PI / 180));
        y = (float) (center + (radius) * Math.sin(startAngle * Math.PI / 180));

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(roundProgressColor);
        canvas.drawCircle(x, y, roundWidth/2, paint);

        x = getWidth()-x;
        paint.setColor(roundColor);
        canvas.drawCircle(x, y, roundWidth/2, paint);



//        canvas.drawCircle(center, center, radius, paint); // 画出圆环


        /**
         * 画进度百分比
         */
        paint.setColor(roundProgressColor);
        paint.setTextSize(20);
        paint.setStyle(Paint.Style.FILL);

        if (progress > 0) {
            /**
             * 画圆弧 ，画圆环的进度
             */
            // 设置进度是实心还是空心
            paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
            paint.setColor(roundProgressColor); // 设置进度的颜色

//            RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限
            switch (style) {
                case STROKE_FILL:
                case STROKE:
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(oval, startAngle, (360-intervalAngle) * progress / max, false, paint); // 根据进度画圆弧
                    if (isDrawBitmapOrCircle) {
                        double degree = (360-intervalAngle) * progress / max + startAngle;// 圆环角度

                        // 已知圆心，半径，角度，求圆上的点坐标
                        x = (float) (center + (radius) * Math.cos(degree * Math.PI / 180));
                        y = (float) (center + (radius) * Math.sin(degree * Math.PI / 180));

                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawCircle(x, y, roundWidth/2, paint);
                    }
                    break;
                case FILL:
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    if (progress != 0)
                        canvas.drawArc(oval, startAngle, (360-intervalAngle) * progress / max, true, paint); // 根据进度画圆弧
                    break;
                default:
                    break;
            }
        }

    }

    private void drawText(Canvas canvas, String text, int center, float y, Paint paint) {
        float x = center - paint.measureText(text) / 2;
        canvas.drawText(text, x, y, paint);
    }

    public synchronized int getMax() {
        return max;
    }

    /**
     * 设置进度的最大值
     *
     * @param max
     */
    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
        postInvalidate();
    }

    /**
     * 获取进度.需要同步
     *
     * @return
     */
    public synchronized int getProgress() {
        return progress;
    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 刷新界面调用postInvalidate()能在非UI线程刷新
     *
     * @param progress
     */
    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public float getIntervalAngle() {
        return intervalAngle;
    }

    public void setIntervalAngle(float intervalAngle) {
        this.intervalAngle = intervalAngle;
        startAngle = 90+intervalAngle/2;
    }

}
