package com.xiao.demo.recyclerview.util;

/**
 * FileName:com.xiao.demo.recyclerview.util.UrlStatusBean.java
 * Created on 2016/7/28
 * Version V1.0
 */
public class UrlStatusBean {
    private  String url;
    private  String parentUrl;
    private  int index;
    private  boolean isRedirect;

    public UrlStatusBean() {
    }

    public UrlStatusBean(String url, String parentUrl, int index, boolean isRedirect) {
        this.url = url;
        this.parentUrl = parentUrl;
        this.index = index;
        this.isRedirect = isRedirect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(String parentUrl) {
        this.parentUrl = parentUrl;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    @Override
    public String toString() {
        return "UrlStatusBean{" +
                "url='" + url + '\'' +
                ", parentUrl='" + parentUrl + '\'' +
                ", index=" + index +
                ", isRedirect=" + isRedirect +
                '}';
    }
}
