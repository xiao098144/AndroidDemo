package com.example.util;

/**
 * 黑白名单信息
 * FileName:com.wps140.bean.Devicebean.java
 * Created on 2016/10/7
 * Version V1.0
 */

public class Devicebean {

    private String imei;

    private String imsi;

    private String name;
    // 属性  1 黑名单  2 白名单 默认 0
    private int property;

    public Devicebean() {
    }

    public Devicebean(String imei, String imsi, String name, int property) {
        this.imei = imei;
        this.imsi = imsi;
        this.name = name;
        this.property = property;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }
}



