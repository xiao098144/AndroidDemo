package com.xiao.demo.recyclerview.widget.customView;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description：
 * Created on 2017/3/9
 * Author : 萧
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ChooseScreenView> mListViews;

    public ViewPagerAdapter(List<ChooseScreenView> mListViews) {
        this.mListViews = mListViews;
    }

    @Override
    public int getCount() {
        return mListViews == null ? 0 : mListViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mListViews.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup arg0, int arg1) {
        arg0.addView(mListViews.get(arg1), 0);
        return mListViews.get(arg1);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == (arg1);
    }

}
