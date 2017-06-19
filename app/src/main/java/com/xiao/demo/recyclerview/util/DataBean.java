package com.xiao.demo.recyclerview.util;

/**
 * Created on 2016/7/21
 * Version V1.0
 */

public class DataBean {
    private static final String TAG = "DataBean";

    private String name;
    private String name2;
    private String name3;
    private  int age;
    private int res;

    public DataBean(String name) {
        this.name = name;
    }

    public DataBean(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public DataBean(String name, int age, int res) {
        this.name = name;
        this.age = age;
        this.res = res;
    }

    public DataBean(String name, int res) {
        this.name = name;
        this.res = res;
    }

    public DataBean() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }
}