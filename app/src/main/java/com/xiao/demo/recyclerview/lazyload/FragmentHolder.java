package com.xiao.demo.recyclerview.lazyload;

import android.support.v4.app.Fragment;

/**
 * Description：
 * Created on 2017/4/6
 * Author : 萧
 */
public class FragmentHolder {

    String title;
    Fragment fragment;

    public FragmentHolder() {
    }

    public FragmentHolder(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
