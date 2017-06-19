package com.xiao.demo.recyclerview.netutil.retrofit;

import android.app.Activity;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Description：
 * Created on 2017/5/18
 * Author : 萧
 */
public class RetrofitActivity<T> extends Activity implements Callback<T>{

    @Override
    public void success(T t, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }
}
