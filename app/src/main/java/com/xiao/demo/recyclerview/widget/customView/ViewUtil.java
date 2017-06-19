package com.xiao.demo.recyclerview.widget.customView;

import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;

/**
 * FileName:ViewUtil.java
 * Created on 2016/8/24
 * Version V1.0
 */
public class ViewUtil {

    /**
     * 测量显示内容所需宽高
     * @param textSize
     * @param text
     * @return pair.first 宽  pair.second 高
     */
    public static Pair<Integer,Integer> measureTextWidthHeight(int textSize, String text){
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return  new Pair<>(rect.width(),rect.height());
    }

}
