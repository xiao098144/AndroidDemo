package com.xiao.demo.recyclerview.netutil.okhttp;

import android.os.Environment;
import android.util.Log;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * FileName:com.xiao.demo.recyclerview.netutil.okhttp.OKHttpUtil.java
 * Created on 2016/8/28
 * Version V1.0
 */
public class OKHttpUtil {

    private static final String TAG = "OKHttpUtil";

    // 读取Response IO 异常
    public static final int ERROR_CODE_IO = -1;

    // Gson 解析 BaseResponse  JSON 异常
    public static final int ERROR_CODE_JSONEXCEPTION = -2;

    // Gson 解析得到对应ResponseBean JSON 异常
    public static final int ERROR_CODE_JSONEXCEPTION2 = -3;


    private volatile static OKHttpUtil mOKhttpUtil;

    private OkHttpClient mClient;

    private Platform mPlatform;

    private Gson mGson;

    private static final int READTIMEOUT = 30;

    private static final int WRITETIMEOUT = 30;

    private static final int CONNECTTIMEOUT = 30;

    public static OKHttpUtil getInstance() {
        if (mOKhttpUtil == null) {
            synchronized (OKHttpUtil.class) {
                if (mOKhttpUtil == null) mOKhttpUtil = new OKHttpUtil();
            }
        }
        return mOKhttpUtil;
    }

    private OKHttpUtil() {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "AndroidDemo" + File.separator + "OKHttp" + File.separator + "Cache");
        if (!file.exists()) file.mkdirs();
        OkLogInterceptor logInterceptor = OkLogInterceptor.builder().useAndroidLog(true).build();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        mClient = builder.readTimeout(READTIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITETIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(file, 100 * 1024 * 1024l))
                .retryOnConnectionFailure(true)
                .addInterceptor(logInterceptor)
                .build();
        mPlatform = Platform.get();
        mGson = new Gson();
    }

    public void uploadFile(OKHttpCallBack callback, File file, int type, Class<?> clasz, Object tag, boolean showDialog) {
//        MultipartBody.Builder builder = new MultipartBody.Builder();
//        builder.setType(MultipartBody.FORM);
//        builder.addFormDataPart("actId", String.valueOf(Action.UPLOAD_FILE));
//        builder.addFormDataPart("patientId",String.valueOf(10000039));
//        builder.addFormDataPart("userType",String.valueOf(1));
//        builder.addFormDataPart("file",file.getName(),RequestBody.create(null,file));
//        MultipartBody body = builder.build();
//        Request.Builder builder1 = new Request.Builder();
//        Request request = builder1.url(WAPI.WAPI_URL_TEST).tag(tag).post(body).build();
//        OKHttpCallBack finalCallBack = callback;
//
//        mClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure: cancelDialog ");
//                sendFailureResult(finalCallBack, 0, e, tag);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: cancelDialog");
//                if (call.isCanceled())
//                    sendFailureResult(finalCallBack, -1, new IOException("request is canceled"), tag);
//                parseResponse(response, finalCallBack, clasz, tag);
//            }
//        });
    }

    public <T> void doRequest(OKHttpCallBack callback, T t, Class<?> clasz, Object tag, boolean showDialog) {
        if (showDialog) Log.e(TAG, "doRequest: showDialog ");
        doRequest(callback, mGson.toJson(t), clasz, tag);
    }

    private void doRequest(OKHttpCallBack callback, String requestStr, Class<?> clasz, Object tag) {
//        Log.e(TAG, "doRequest: request = " + requestStr.length());
//        Request.Builder builder = new Request.Builder();
////        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),requestStr);
//        RequestBody body = RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), requestStr);
//        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//        CacheControl cacheControl = cacheBuilder.maxAge(3, TimeUnit.DAYS).minFresh(READTIMEOUT, TimeUnit.SECONDS).noTransform().build();
//        Request request = builder.url(WAPI.WAPI_URL_TEST).cacheControl(cacheControl).tag(tag).post(body).build();
//        OKHttpCallBack finalCallBack = callback;
//        OkHttpClient client = mClient.newBuilder().writeTimeout(2 * WRITETIMEOUT, TimeUnit.SECONDS).connectTimeout(2 * CONNECTTIMEOUT, TimeUnit.SECONDS).readTimeout(2 * READTIMEOUT, TimeUnit.SECONDS).build();
//        Call call = null;
//        try {
//            call = body.contentLength()>10000l?client.newCall(request):mClient.newCall(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, "onFailure: cancelDialog ");
//                sendFailureResult(finalCallBack, 0, e, tag);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG, "onResponse: cancelDialog");
//                if (call.isCanceled())
//                    sendFailureResult(finalCallBack, -1, new IOException("request is canceled"), tag);
//                parseResponse(response, finalCallBack, clasz, tag);
//            }
//        });
    }

    /*
        解析Response

     */
    private void parseResponse(Response response, OKHttpCallBack callBack, Class<?> clasz, Object tag) {
//        if (response.isSuccessful()) {
//            try {
//                String string1 = response.body().string();
//                Log.e(TAG, "parseResponse: response = " + string1);
//                BaseRespone respone = mGson.fromJson(string1, BaseRespone.class);
//                if (respone.getCode() == 1) {
//                    if (clasz == null)
//                        sendFailureResult(callBack, -3, new IllegalArgumentException("responseClass can not be null"), tag);
//                    try {
//                        Object o = mGson.fromJson(string1, clasz);
//                        onSuccess(callBack, o, tag);
//                    } catch (JsonSyntaxException e) {
//                        sendFailureResult(callBack, ERROR_CODE_JSONEXCEPTION2, e, tag);
//                        e.printStackTrace();
//                    }
//                } else
//                    sendFailureResult(callBack, respone.getCode(), new Exception(respone.getErrMsg()), tag);
//            } catch (JsonSyntaxException e) {
//                sendFailureResult(callBack, ERROR_CODE_JSONEXCEPTION, e, tag);
//                e.printStackTrace();
//            } catch (IOException e) {
//                sendFailureResult(callBack, ERROR_CODE_IO, e, tag);
//                e.printStackTrace();
//            }
//        } else {
//            sendFailureResult(callBack, response.code(), new Exception(response.message()), tag);
//        }
    }

    private void sendFailureResult(OKHttpCallBack callback, int code, Exception e, Object tag) {
        if (callback == null) return;
        mPlatform.execute(() -> callback.onFailure(code, e, e.getMessage(), tag));
//        mPlatform.execute(new Runnable() {
//            @Override
//            public void run() {
//                callback.onFailure(code, e, e.getMessage());
//            }
//        });
    }

    private <T> void onSuccess(OKHttpCallBack callback, T t, Object tag) {
        if (callback == null) return;
        mPlatform.execute(() -> callback.onSuccess(t, tag));
    }


    static class MyInterceptor implements Interceptor {

        private static final String TAG = "MyInterceptor";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e(TAG, "intercept: send request " + request + " " + request.body().toString());
            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();

            MediaType mediaType = response.body().contentType();
            String string1 = response.body().string();

            Log.e(TAG, "intercept: received response 消耗时长 = " + (t2 - t1) / 1e6d + " message = " + response.message() + " code = " + response.code() + " body().string() = " + string1);

//            return response.newBuilder().body(ResponseBody.create(mediaType, string1)).build();
            return response;
        }

    }

}
