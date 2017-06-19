package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.xiao.demo.recyclerview.util.AppUtil;

/**
 * FileName:com.xiao.demo.recyclerview.customView.BeiSaiErLine.java
 * Created on 2016/9/5
 * Version V1.0
 */
public class BezierLine extends View {

    private Paint mPaint;

    public BezierLine(Context context) {
        super(context);
    }

    public BezierLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = AppUtil.getScreenWidth(getContext());
        int height = AppUtil.getScreenHeight(getContext());
        int x = width / 20;
        int y = height / 20;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.RED);
        Path path = new Path();
        path.moveTo(x, y);
        path.quadTo(x*3.5f,y*5f,x*6,y);
        canvas.drawPath(path, mPaint);
        mPaint.setColor(Color.LTGRAY);
        path.moveTo(x, y);
        path.rQuadTo(x*3.5f,y*5f,x*6,y);
        canvas.drawPath(path, mPaint);
//        path.moveTo(x*6,y);
//        path.rQuadTo(x*7f,y*5f,x*12,y);
//        canvas.drawPath(path,mPaint);

        mPaint.setColor(Color.BLUE);
        Path path2 = new Path();
        path2.moveTo(x,y*6f);
        path2.cubicTo(x*2.5f,y*1.2f,x*6f,y*3f,x*6,y*6);
        canvas.drawPath(path2,mPaint);
        mPaint.setColor(Color.LTGRAY);
        path2.moveTo(x,y*6f);
        path2.rCubicTo(x*2.5f,y*1.2f,x*6f,y*3f,x*6,y*6);
        canvas.drawPath(path2,mPaint);
//        path2.moveTo(x*6,y*6);
//        path2.rCubicTo(x*5f,y*1.2f,x*12f,y*3,x*12,y*6);
//        canvas.drawPath(path2,mPaint);
    }
}
