package com.xiao.demo.recyclerview.lazyload;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description：
 * Created on 2017/4/6
 * Author : 萧
 */
public class LazyViewPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "LazyViewPagerAdapter";

    List<FragmentHolder> fragmentList;

    public LazyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<FragmentHolder> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<FragmentHolder> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.e(TAG, "instantiateItem: position = " + position);
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.e(TAG, "destroyItem: position = " + position);
        super.destroyItem(container, position, object);
    }

}
