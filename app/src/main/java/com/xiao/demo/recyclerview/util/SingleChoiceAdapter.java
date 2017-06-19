package com.xiao.demo.recyclerview.util;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * 单选适配器基类 TODO:TODO Date:2015年9月11日下午5:17:50 Author:萧
 */
public class SingleChoiceAdapter<T> extends BaseAdapter<T> {

    public SparseBooleanArray isSelected;

    public Map<String, String> selectedData = new HashMap<String, String>();

    public SingleChoiceAdapter(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public Map<String, String> getSelectedData() {
        return selectedData;
    }

    public SparseBooleanArray getIsSelected() {
        return isSelected;
    }

    public void setSelected(int position) {
        if (isSelected == null) isSelected = new SparseBooleanArray();
        for (int i = 0; i < isSelected.size(); i++) {
            isSelected.put(i, false);
        }
        if (position >= 0 && position < getCount()) {
            // 再将当前选择CB的实际状态
            isSelected.put(position, true);
        } else {
            selectedData.clear();
        }
        notifyDataSetChanged();
    }

    public void setCancelSelected(int position) {
        for (int i = 0; i < isSelected.size(); i++) {
            isSelected.put(i, false);
        }
        notifyDataSetChanged();
    }

    public void setIsSelected(SparseBooleanArray isSelected) {
        this.isSelected = isSelected;
    }

}
