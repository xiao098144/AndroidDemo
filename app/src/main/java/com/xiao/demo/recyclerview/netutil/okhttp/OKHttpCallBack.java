package com.xiao.demo.recyclerview.netutil.okhttp;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * FileName:com.xiao.demo.recyclerview.netutil.okhttp.OKHttpCallBack.java
 * Created on 2016/8/29
 * Version V1.0
 */
public abstract class OKHttpCallBack implements Callback {

    @Override
    public void onFailure(Call call, IOException e) {
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
    }

    public abstract void onFailure(int code, Exception e, String message,Object tag);

    public abstract <T> void onSuccess(T t, Object tag);

}
