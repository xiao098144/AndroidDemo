package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.util.AppUtil;

/**
 * FileName:IntegralRecordView.java
 * Created on 2016/8/23
 * Version V1.0
 */
public class IntegralRecordView extends View {

    private static final String TAG = "IntegralRecordView";

    private Bitmap calendar, title, bg;

    private Bitmap cloud_left, cloud_right;

    // 边框线宽度
    private float mFrameBoderWidth = 1f;

    private int mFrameBoderColor;
    private int mFrameBgColor;

    private int mDefaultTextColor;

    private int mCountIntegralTextColor;

    private int mCountLoginDaysTextColor;

    private int mFutureIntegralTextColor;

    private int screentWidth;

    // 设计尺寸   1920 x 1080  凡是DESIGN开头均代表设计图数据
    private static final int DESIGN_FRAME_WIDTH = 700;
    private int frameWidth;
    private int frameHeight;
    // 底部半圆个数
    private static final int DESIGN_FRAME_HALFCIRCLE_COUNT = 14;
    // 底部半圆半径
    private float bottom_circle_radius;

    private static final int DESIGN_TOP_DEVIDE_HEIGHT = 140;
    private int topDevide_height;
    private static final int DESIGN_TOP_DEVIDE_WIDTH = 98;
    private int topDevide_width;
    private static final int DESIGN_CALENDARBG_DEVIDE_WIDTH = 30;
    private float mCalendarDevideWidth;

    private static final int DESIGN_TITLE_HEIGHT = 36;
    private float mTitleHeight;
    private static final int DESIGN_TITLE_WIDTH = 218;
    private float mTitleWidth;

    private int DESIGN_ARC_WIDTH = 60;
    private int arc_width;

    // 四个顶点坐标 X 坐标  pair.first  Y坐标 pair.second  从左到右  从上到下
    private SparseArray<Pair<Integer, Integer>> points;

    public IntegralRecordView(Context context) {
        super(context);
        Log.e(TAG, "IntegralRecordView: (Context context)");
        init();
    }

    public IntegralRecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e(TAG, "IntegralRecordView: (context, attrs)");
        init();
    }

    public IntegralRecordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e(TAG, "IntegralRecordView: (context, attrs, defStyleAttr)");
        init();
    }

    private void init() {
        Log.e(TAG, "init: ");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = null;
        options.inScaled = false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            options.inPurgeable = true;
            options.inInputShareable = true;
        }
        bg = BitmapFactory.decodeResource(getResources(), R.mipmap.integral_bg, options);
        title = BitmapFactory.decodeResource(getResources(), R.mipmap.integral_title, options);
        calendar = BitmapFactory.decodeResource(getResources(), R.mipmap.integral_calendar_bg, options);
        cloud_left = BitmapFactory.decodeResource(getResources(), R.mipmap.integral_cloud_left, options);
        cloud_right = BitmapFactory.decodeResource(getResources(), R.mipmap.integral_cloud_right, options);

        mFrameBgColor = Color.WHITE;
        mFrameBoderColor = Color.parseColor("#999999");
        mDefaultTextColor = Color.parseColor("#666666");
        mCountIntegralTextColor = Color.parseColor("#FFC109");
        mCountLoginDaysTextColor = Color.parseColor("#FF0000");
        mFutureIntegralTextColor = Color.parseColor("#FF8686");

        screentWidth = AppUtil.getScreenWidth(getContext());
        float ratio = ((float) screentWidth) / 1080;
        frameWidth = (int) (DESIGN_FRAME_WIDTH * ratio);
        frameHeight = frameWidth;
        bottom_circle_radius = ((float) frameWidth) / (2 * DESIGN_FRAME_HALFCIRCLE_COUNT);
        frameHeight = (int) (frameHeight + bottom_circle_radius);

        topDevide_height = (int) (DESIGN_TOP_DEVIDE_HEIGHT * ratio);
        topDevide_width = (int) (DESIGN_TOP_DEVIDE_WIDTH * ratio);
        mTitleHeight = DESIGN_TITLE_HEIGHT * ratio;
        mTitleWidth = DESIGN_TITLE_WIDTH * ratio;
        mCalendarDevideWidth = DESIGN_CALENDARBG_DEVIDE_WIDTH * ratio;

        arc_width = (int) (DESIGN_ARC_WIDTH * ratio);
        int xleft = (screentWidth - frameWidth) / 2;
        int xRight = xleft + frameWidth;
        points = new SparseArray<>();
        points.put(0, new Pair<>(xleft, 0));
        points.put(1, new Pair<>(xRight, 0));
        points.put(2, new Pair<>(xleft, frameHeight));
        points.put(3, new Pair<>(xRight, frameHeight));

        Log.e(TAG, "init: screenWidth = " + screentWidth + " ratio = " + ratio + " frameWidth = " + frameWidth + " frameHeight = " + frameHeight + " bottom_circle_radius = " + bottom_circle_radius + " topDevide_height = " + topDevide_height + " topDevide_width = " + topDevide_width + " xleft = " + xleft + " xright = " + xRight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: getWidth = " + getWidth() + " getHeight = " + getHeight() + " getX() = " + getX() + " getY = " + getY() + " getTop = " + getTop() + " getLeft = " + getLeft() + " getRight = " + getRight() + " getBottom = " + getBottom());
        drawFrame(canvas);
        drawContent(canvas);
    }

    private void drawFrame(Canvas canvas) {
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
//        canvas.drawBitmap(bg, points.get(0).first, points.get(0).second, paint);
        drawBitmap(canvas, bg, points.get(0).first, points.get(0).second);
//        paint.setStrokeWidth(mFrameBoderWidth);
//
//        // 绘制
//        paint.setColor(mFrameBgColor);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setPathEffect(new CornerPathEffect(2));
//        RectF roundRect = new RectF();
//        roundRect.left = points.get(0).first-mFrameBoderWidth/2;
//        roundRect.top = points.get(0).second + mFrameBoderWidth;
//        roundRect.right = points.get(3).first+mFrameBoderWidth/2;
//        roundRect.bottom = points.get(3).second - mFrameBoderWidth;
//        Log.e(TAG, "drawFrame: 绘制白色背景 roundRect = " + roundRect);
////        paint.setShadowLayer(12,6,6,Color.BLACK);
////        canvas.drawRoundRect(points.get(0).first,points.get(0).second,points.get(3).first,points.get(3).second,10,10,paint);
//        canvas.drawRoundRect(roundRect, 10, 10, paint);
//
//        paint.setColor(mFrameBoderColor);
//        paint.setStyle(Paint.Style.STROKE);
//        roundRect.left = points.get(0).first - mFrameBoderWidth;
//        roundRect.top = points.get(0).second;
//        roundRect.right = points.get(3).first + mFrameBoderWidth;
//        roundRect.bottom = points.get(3).second;
//        Log.e(TAG, "drawFrame: 绘制黑色边框  roundRect " + roundRect);
//        canvas.drawRoundRect(roundRect, 13, 13, paint);
//
//        float bottom = frameHeight + bottom_circle_radius;
//        float top = frameHeight - bottom_circle_radius;

//        RectF oval = new RectF(center - radius, top, center + radius, bottom);
//        canvas.drawArc(oval,0,180,false,paint);


//
//        int left = points.get(0).first;
//        Path path = new Path();
//        path.moveTo(left, frameHeight);
//        path.lineTo(left, arc_width);
//
//        RectF rect = new RectF();
//        rect.left = left;
//        rect.right = rect.left + arc_width;
//        rect.top = 0;
//        rect.bottom = rect.top + arc_width;
//        path.arcTo(rect, 180, 90);
//        path.lineTo(left + frameWidth - arc_width, 0);
//        RectF rect2 = new RectF();
//        rect2.left = left + frameWidth - arc_width;
//        rect2.right = rect2.left + arc_width;
//        rect2.top = 0;
//        rect2.bottom = rect2.top + arc_width;
//        path.arcTo(rect2, 270, 90);
//        path.lineTo(rect2.right, frameHeight);
//
////        Path circlePath = new Path();
////        circlePath.moveTo(rect2.right, frameHeight);
//        float bottom = frameHeight + bottom_circle_radius;
//        float top = frameHeight - bottom_circle_radius;
//        float lastRight = rect2.right;
//        for (int i = DESIGN_FRAME_HALFCIRCLE_COUNT - 1; i >= 0; i--) {
//            RectF circle1 = new RectF();
//            circle1.top = top;
//            circle1.bottom = bottom;
//
//            circle1.right = lastRight;
//            if (i > 0) {
//                circle1.left = (i + 1) * 2 * bottom_circle_radius;
//                lastRight = circle1.left;
//            } else circle1.left = left;
//            Log.e(TAG, "drawFrame: circle1.left = " + circle1.left + " circle1.right = " + circle1.right);
//            path.arcTo(circle1, 360, 180);
//        }
////        for (int i = 0; i < DESIGN_FRAME_HALFCIRCLE_COUNT; i++) {
////            RectF circle1 = new RectF();
////            circle1.left = left;
////            circle1.top = top;
////            circle1.bottom = bottom;
////            if (i < DESIGN_FRAME_HALFCIRCLE_COUNT - 1) {
////                circle1.right = (i + 1) * 2 * bottom_circle_radius;
////            } else circle1.right = rect2.right;
////            circlePath.arcTo(circle1, 0, 180);
////        }
//
////        path.addPath(circlePath);
//
//
//        Log.e(TAG, "drawFrame: rect.left = " + rect.left + " rect.right = " + rect.right + " rect.top = " + rect.top + " rect.bottom = " + rect.bottom + " rect2.left = " + rect2.left + " rect2.right = " + rect2.right + " rect2.top = " + rect2.top + " rect2.bottom = " + rect2.bottom);

//        canvas.drawPath(path, paint);

    }

    private void drawContent(Canvas canvas) {
        int x = points.get(0).first;
        int y = points.get(0).second;
        drawBitmap(canvas, title, x + mTitleWidth, y + mTitleHeight);

        drawBitmap(canvas, calendar, x + topDevide_width, y + topDevide_height);
        drawBitmap(canvas, calendar, x + topDevide_width + mCalendarDevideWidth + calendar.getWidth(), y + topDevide_height);
        drawBitmap(canvas, calendar, x + topDevide_width + 2 * mCalendarDevideWidth + 2 * calendar.getWidth(), y + topDevide_height);
        Log.e(TAG, "drawContent: calendar.getWidth "+calendar.getWidth()+" calendar.getheight "+calendar.getHeight());
        drawText(canvas, "0", mCountLoginDaysTextColor, 25, x + topDevide_width + calendar.getWidth() / 2, y + topDevide_height + calendar.getHeight() / 2, Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        drawText(canvas, "0", mCountLoginDaysTextColor, 25, x + topDevide_width + mCalendarDevideWidth + calendar.getWidth() + calendar.getWidth() / 2, y + topDevide_height + calendar.getHeight() / 2, Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        drawText(canvas, "3", mCountLoginDaysTextColor, 25, x + topDevide_width + 2 * mCalendarDevideWidth + 2 * calendar.getWidth() + calendar.getWidth() / 2, y + topDevide_height + calendar.getHeight() / 2, Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        drawText(canvas, "天", mDefaultTextColor, 20, points.get(1).first - 30, y + topDevide_height + calendar.getHeight() / 2, null);


    }

    private void drawText(Canvas canvas, String text, int textColor, int textSize, float x, float y, Typeface typeface) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setPathEffect(new CornerPathEffect(2));
        if (typeface != null) {
            paint.setFakeBoldText(true);
            paint.setTypeface(typeface);
        }
        Pair<Integer, Integer> pair = ViewUtil.measureTextWidthHeight(textSize, text);
        canvas.drawText(text, x - pair.first / 2, y - pair.second / 2, paint);
    }

    private void drawBitmap(Canvas canvas, Bitmap bitmap, float x, float y) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(title, x, y, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        Log.e(TAG, "onMeasure: heightSize = " + heightSize + " widthSize = " + widthSize + " frameHeight = " + frameHeight + " screentWidth = " + screentWidth);
        if (heightMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
            heightSize = frameHeight == 0 ? DESIGN_FRAME_WIDTH : frameHeight;
        }
        if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
            widthSize = screentWidth == 0 ? 1080 : screentWidth;
        }
//        Log.e(TAG, "onMeasure: " + " MeasureSpec.AT_MOST " + MeasureSpec.AT_MOST + " MeasureSpec.UNSPECIFIED = " + MeasureSpec.UNSPECIFIED + " MeasureSpec.EXACTLY = " + MeasureSpec.EXACTLY);
//        Log.e(TAG, "onMeasure: heightMode = " + heightMode + " widthMode = " + widthMode + " heightSize = " + heightSize + " widthSize = " + widthSize);
        setMeasuredDimension(widthSize, heightSize);
    }

//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        Log.e(TAG, "onLayout: changed = " + changed + " left = " + left + " right = " + right + " top = " + top + " bottom = " + bottom);
//    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onAttachedToWindow: ");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "onDetachedFromWindow: ");
    }
}
