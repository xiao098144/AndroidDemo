package com.xiao.demo.recyclerview.widget.customView;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiao.demo.recyclerview.R;
import com.xiao.demo.recyclerview.animation.base.BaseDialog;
import com.xiao.demo.recyclerview.util.CharsequencePharse;

/**
 * FileName:IntegralDialog.java
 * Created on 2016/8/25
 * Version V1.0
 */
public class IntegralDialog extends BaseDialog<IntegralDialog> {

    ImageView img_scale;
    TextView tv_value;
    ImageView img_close;
    private SparseArray<Integer> colors;
    private SparseArray<Integer> textsizes;
    private SparseArray<String> contents;

    public IntegralDialog(Context context) {
        super(context);

    }


    public void setColors(SparseArray<Integer> colors) {
        this.colors = colors;
    }

    public void setTextsizes(SparseArray<Integer> textsizes) {
        this.textsizes = textsizes;
    }

    public void setContents(SparseArray<String> contents) {
        this.contents = contents;
    }

    @Override
    public View onCreateView() {
        heightScale(1f);

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_integral, null);
        img_scale = (ImageView) view.findViewById(R.id.integral_img_scale);
        tv_value = (TextView) view.findViewById(R.id.integral_tv_value);
        img_close = (ImageView) view.findViewById(R.id.integral_img_close);
        return view;
    }

    @Override
    public void setUiBeforShow() {
        tv_value.setText(CharsequencePharse.init().setColors(colors).setTextSizes(textsizes).setContents(contents).format());
        img_close.setOnClickListener((v) -> {
            dismiss();
        });
    }
}
