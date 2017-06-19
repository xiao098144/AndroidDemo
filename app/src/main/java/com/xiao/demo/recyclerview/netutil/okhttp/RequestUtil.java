package com.xiao.demo.recyclerview.netutil.okhttp;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * FileName:com.xiao.demo.recyclerview.netutil.okhttp.RequestUtil.java
 * Created on 2016/8/26
 * Version V1.0
 */
public class RequestUtil {

    private static final String TAG = "RequestUtil";

    private static RequestUtil requestUtil;

    public static RequestUtil getInstance() {
        if (requestUtil == null) requestUtil = new RequestUtil();
        return requestUtil;
    }

    public String doRequest(String url, String json, Callback callback) {

//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.getBytes());
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.url(url).post(body).build();
//        OkHttpClient client = new OkHttpClient();
//        client.connectTimeoutMillis();


//        try {
//            client.newCall(request).enqueue(callback);
//            Log.e(TAG, "doRequest: " + response + " body() " + response.body());
//            if (response.isSuccessful()) {
//                return "doRequest: " + response + " body() " + response.body();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "";
    }
}
