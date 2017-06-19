package com.xiao.demo.recyclerview.widget;

import com.xiao.demo.recyclerview.ui.activity.StickyRecyclerViewActivity;

/**
 * Description：
 * Created on 2017/3/1
 * Author : 萧
 */
public class TestBean implements Comparable<TestBean> {
    String date;
    String time;
    String title;
    String des;
    int online;
    String url;
    String tag;


    public TestBean() {
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", online=" + online +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTime() {
        return time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TestBean(String time, String date, String title) {

        this.time = time;
        this.date = date;
        this.title = title;
    }

    public TestBean(String time, String date, String title, String tag) {
        this.time = time;
        this.date = date;
        this.title = title;
        this.tag = tag;
    }

    @Override
    public int compareTo(TestBean o) {
        int i = this.date.compareTo(o.date);
        if (i == 0) {
            int j = this.time.compareTo(o.time);
            if (j == 0) {
                return this.title.compareTo(o.title);
            }
            return j;
        }
        return i;
    }
}