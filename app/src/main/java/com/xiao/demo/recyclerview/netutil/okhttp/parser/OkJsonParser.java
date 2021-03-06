package com.xiao.demo.recyclerview.netutil.okhttp.parser;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.lang.reflect.Modifier;

import okhttp3.Response;

/**
 * Created by zhaokaiqiang on 15/11/22.
 */
public abstract class OkJsonParser<T> extends OkBaseJsonParser<T> {

    private static final String TAG = "OkJsonParser";

    protected Gson mGson;

    public OkJsonParser() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .excludeFieldsWithModifiers(
                            Modifier.FINAL,
                            Modifier.TRANSIENT,
                            Modifier.STATIC);
            mGson = gsonBuilder.create();
        } else {
            mGson = new Gson();
        }
    }

    @Override
    public T parse(Response response) throws IOException {
        String body = response.body().string();

//        Log.e(TAG, "parse: body "+body +"   mType "+mType+" "+ ClientAboutResponseBean.class);
//        Log.e(TAG, "parse: "+mGson.fromJson(body, mType)+"  "+mGson.fromJson(body, ClientAboutResponseBean.class));
        return mGson.fromJson(body, mType);
    }
}