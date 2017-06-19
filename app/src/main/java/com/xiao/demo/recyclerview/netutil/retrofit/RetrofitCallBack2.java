package com.xiao.demo.recyclerview.netutil.retrofit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Description：
 * Created on 2017/5/18
 * Author : 萧
 */
public abstract class RetrofitCallBack2<V> implements Callback<V> {

    @Override
    public void success(V v, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }
}
