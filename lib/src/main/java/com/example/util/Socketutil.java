package com.example.util;

/**
 * FileName:com.wps140.util.Socketutil.java
 * Created on 2016/10/5
 * Version V1.0
 */

public class Socketutil {

    private volatile static Socketutil instance;

    private Socketutil() {
    }

    public static Socketutil getInstance() {
        synchronized (Socketutil.class) {
            if (instance == null)
                instance = new Socketutil();
        }
        return instance;
    }

    public void doConnect(){

    }

}
