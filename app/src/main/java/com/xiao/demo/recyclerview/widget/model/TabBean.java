package com.xiao.demo.recyclerview.widget.model;

/**
 * FileName:com.xiao.demo.recyclerview.widget.model.TabBean.java
 * Created on 2017/2/17
 * Version V1.0
 */

public class TabBean {

    private int norRes;

    private int selectRes;

    private String norResUrl;

    private String selectResUrl;

    private String title;

    private int norColor;

    private int selectColor;

    public TabBean() {
    }

    public TabBean(int norRes, int selectRes, String title, int norColor, int selectColor) {
        this.norRes = norRes;
        this.selectRes = selectRes;
        this.title = title;
        this.norColor = norColor;
        this.selectColor = selectColor;
    }

    public TabBean(String norResUrl, String selectResUrl, String title, int norColor, int selectColor) {
        this.norResUrl = norResUrl;
        this.selectResUrl = selectResUrl;
        this.title = title;
        this.norColor = norColor;
        this.selectColor = selectColor;
    }

    @Override
    public String toString() {
        return "TabBean{" +
                "norRes=" + norRes +
                ", selectRes=" + selectRes +
                ", norResUrl='" + norResUrl + '\'' +
                ", selectResUrl='" + selectResUrl + '\'' +
                ", title='" + title + '\'' +
                ", norColor=" + norColor +
                ", selectColor=" + selectColor +
                '}';
    }

    public String getNorResUrl() {
        return norResUrl;
    }

    public void setNorResUrl(String norResUrl) {
        this.norResUrl = norResUrl;
    }

    public String getSelectResUrl() {
        return selectResUrl;
    }

    public void setSelectResUrl(String selectResUrl) {
        this.selectResUrl = selectResUrl;
    }

    public int getNorRes() {
        return norRes;
    }

    public void setNorRes(int norRes) {
        this.norRes = norRes;
    }

    public int getSelectRes() {
        return selectRes;
    }

    public void setSelectRes(int selectRes) {
        this.selectRes = selectRes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNorColor() {
        return norColor;
    }

    public void setNorColor(int norColor) {
        this.norColor = norColor;
    }

    public int getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(int selectColor) {
        this.selectColor = selectColor;
    }

}
