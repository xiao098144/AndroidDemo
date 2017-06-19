package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
//import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;

import com.xiao.demo.recyclerview.util.AppUtil;
import com.xiao.demo.recyclerview.util.TimeUtil;

import java.util.Calendar;
import java.util.List;

/**
 * FileName:CalendarRecordView.java
 * Created on 2016/8/22
 * Version V1.0
 */
public class CalendarRecordView extends View {

    private static final String TAG = "CalendarRecordView";

    private Paint paint;

    // 背景色
    int COLOR_BG_DISABLE;

    int COLOR_BG_HISTORY;

    int COLOR_BG_TODAY;

    int COLOR_BG_FUTURE;

    // 圆环边框色
    int COLOR_FRAME_WHITE;

    int COLOR_FRAME_RED;

    int COLOR_TEXT_WHITE;

    int COLOR_TEXT_RED;

    float frameboderWidth = 0.8f;

    int screenWidth;

    float marginRatio = 0.4f;
    float deviderRatio = 0.2f;
    int itemRatio = 1;
    int middleRatio = 2;

    SparseArray<String> dates;

    SparseArray<Integer> values;

    public CalendarRecordView(Context context) {
        super(context);
//        Log.e(TAG, "CalendarRecordView: context ");
        init();
    }

    public CalendarRecordView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.e(TAG, "CalendarRecordView: (Context context, AttributeSet attrs)");
        init();
    }

    public CalendarRecordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        Log.e(TAG, "CalendarRecordView: (context, attrs, defStyleAttr)");
        init();
    }

    private void init() {
//        Log.e(TAG, "init: ");
        COLOR_BG_DISABLE = Color.GRAY;
        COLOR_BG_HISTORY = Color.CYAN;
        COLOR_BG_TODAY = Color.WHITE;
        COLOR_BG_FUTURE = Color.RED;
        COLOR_FRAME_RED = Color.RED;
        COLOR_FRAME_WHITE = Color.WHITE;
        COLOR_TEXT_RED = Color.RED;
        COLOR_TEXT_WHITE = Color.WHITE;

        screenWidth = AppUtil.getScreenWidth(getContext());
        dates = new SparseArray<>();
        String today = TimeUtil.getInstance().getStandardDate("MM.dd");

        for (int i = 0; i < 7; i++) {
            if (i != 3)
                dates.put(i, format(TimeUtil.getInstance().dateAdd(i - 3, today, "MM.dd", Calendar.DATE)));
            else dates.put(3, format(TimeUtil.getInstance().getStandardDate("MM月dd日")));
        }
    }

    private String format(String source) {
        if (source.startsWith("0"))
            return source.replaceFirst("0", "");
        return source;
    }

    public void setValue(List<Integer> list) {
//        Log.e(TAG, "setValue: list " + list);
        if (list == null || list.size() != 4) return;
        values = new SparseArray<>();
        for (int i = 0; i < 7; i++) {
            if (i < 4) values.put(i, list.get(i));
            else values.put(i, list.get(3) + 5 * (i - 3));
        }
        invalidate();
//        values.put(0,list.get(0));
//        values.put(1,list.get(1));
//        values.put(2,list.get(2));
//        values.put(3,list.get(3));
//        values.put(4,list.get(3)+5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e(TAG, "onDraw: getWidth " + getWidth() + " getHeight " + getHeight() + " screenWidth " + screenWidth);
        float radio = screenWidth / (middleRatio + itemRatio * 6 + deviderRatio * 6 + marginRatio * 2);
        float y = getHeight() / 2;
//        Log.e(TAG, "onDraw: radio = " + radio + " y = " + y + " margin " + marginRatio * radio + " deviderWidth " + deviderRatio * radio);
        SparseArray<Pair<Float, Float>> points = new SparseArray<>();
        for (int i = 0; i < 7; i++) {
            float x = marginRatio * radio + i * deviderRatio * radio;
            if (i < 3) {
                x += (0.5 + i) * itemRatio * radio;
            } else if (i == 3) {
                x += 3 * itemRatio * radio + 0.5 * middleRatio * radio;
            } else {
                x += (i - 0.5) * itemRatio * radio + middleRatio * radio;
            }
//            Log.e(TAG, "onDraw: i " + i + " x = " + x);
            points.put(i, new Pair(x, y));
        }
        float radius = (float) (0.5 * itemRatio * radio);
        for (int i = 0; i < 7; i++) {
            int frameColor = i == 3 ? COLOR_FRAME_RED : COLOR_FRAME_WHITE;
            int color;
            String content = "+"+values.get(i);
            if (i == 3) {  // 中间项 单独处理
                color = COLOR_BG_TODAY;
                // 绘制背景红色边框白色背景圆
                drawCircle(canvas, color, frameColor, points.get(i), middleRatio / 2 * radio, null,null);
                // 绘制中间内容
                Paint paint = new Paint();
                paint.setColor(COLOR_TEXT_RED);
                paint.setAntiAlias(true);
                paint.setStyle(Paint.Style.FILL);
                paint.setTextSize(19);
                Pair<Integer, Integer> pair = measureTextWidthHeight(paint, dates.get(3));
//                Log.e(TAG, "onDraw: pair.first = "+pair.first+" second = "+pair.second);
                paint.setTextSize(36);
                Pair<Integer, Integer> pair2 = measureTextWidthHeight(paint, content);
//                Log.e(TAG, "onDraw: pair2.first = "+pair2.first+" second = "+pair2.second);
                double width = 2*Math.sqrt(Math.pow(middleRatio / 2 * radio, 2) - Math.pow(y / 6, 2));
                double width2 = 150 * width/ 190 ;
                float startX = (float) (points.get(i).first-width2/2);
                float stopX = (float) (startX+width2);

//                Log.e(TAG, "onDraw: width = "+width+" width2 = "+width2+" startX = "+startX+" stopX = "+stopX);

                paint.setStrokeWidth(1);
                canvas.drawLine(startX,y*5/6,stopX,y*5/6,paint);

                paint.setTextSize(19);
                canvas.drawText(dates.get(3),points.get(i).first-pair.first/2,y-2*y*20/197-1-pair.second/2,paint);
//                Log.e(TAG, "onDraw: points.get(i).first-pair.first/2 = "+(points.get(i).first-pair.first/2)+" y-2*y*23/197-1-pair.second/2 = "+(y-2*y*23/197-1-pair.second/2));
                paint.setTextSize(36);
                canvas.drawText(content,points.get(i).first-pair2.first/2,y+2*y*23/197+pair2.second/2,paint);
//                Log.e(TAG, "onDraw: points.get(i).first-pair2.first/2 = "+(points.get(i).first-pair2.first/2)+" y+2*y*23/197+pair2.second/2 = "+(y+2*y*23/197+pair2.second/2));
//                canvas.drawText(dates.get(3),points.get(i).first-pair.first,0,);

            } else {
                if (i < 3) {
                    color = values.get(i) == 0 ? COLOR_BG_DISABLE : COLOR_BG_HISTORY;
                } else color = COLOR_BG_FUTURE;
                drawCircle(canvas, color, frameColor, points.get(i), i == 3 ? middleRatio / 2 * radio : radius, content, dates.get(i));
            }
        }


    }

    private void drawCircle(Canvas canvas, int color, int frameColor, Pair<Float, Float> pair, float radius, String values, String date) {
        float x = pair.first;
        float y = pair.second;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(frameColor);
        paint.setStrokeWidth(frameboderWidth);
        canvas.drawCircle(x, y, radius + frameboderWidth, paint);

        if (values != null) {
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(23);
            Rect rect = new Rect();
            paint.getTextBounds(values, 0, values.length(), rect);
            canvas.drawText(values, x - rect.width() / 2, y + rect.height() / 2, paint);

            paint.setColor(Color.BLACK);
            paint.setTextSize(16);
            paint.getTextBounds(date, 0, date.length(), rect);
            canvas.drawText(date, x - rect.width() / 2, y + radius * 2, paint);
        }
    }

    private Pair<Integer,Integer> measureTextWidthHeight(Paint paint, String text){
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return  new Pair<>(rect.width(),rect.height());
    }


    //    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        Log.e(TAG, "onLayout: changed " + changed + " left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom);
//    }
//
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e(TAG, "onMeasure: widthMeasureSpec " + widthMeasureSpec + " heightMeasureSpec = " + heightMeasureSpec);
    }
}
