package com.example.net;

import com.google.gson.Gson;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Description：
 * Created on 2017/4/28
 * Author : 萧
 */
public abstract class IHttpCallBack<T> {

    public abstract void onSuccessCallBack(T t, String tag);

    public abstract void onFail(String failureMsg, String tag);

    private HashMap<Integer, Callback> callbacks;

    public Callback getCallBack(int tag, Class clasz) {
        if (callbacks == null) callbacks = new HashMap<>();
        Callback callback = callbacks.get(tag);
        if (callback == null) {
            callback = new Callback();
            callback.setTag(String.valueOf(tag));
            callback.setClasz(clasz);
            callbacks.put(tag, callback);
        }
        return callback;
    }

    public void onDestroy() {
        if (callbacks != null) {
            callbacks.clear();
            callbacks = null;
        }
    }

    public class Callback implements retrofit2.Callback {

        Class clasz;

        String tag;

        private void setTag(String tag) {
            this.tag = tag;
        }

        private void setClasz(Class clasz) {
            this.clasz = clasz;
        }

//        @Override
//        public void onResponse(Call<Map> call, Response<Map> response) {
//            System.out.println("onResponse,  [call, response] "+response);
//            Map map = response.body();
//        }
//
//        @Override
//        public void onFailure(Call<Map> call, Throwable t) {
//            t.printStackTrace();
//        }

//        @Override
//        public void success(Map<String,Object> t, Response response2) {
//            if (callbacks == null || callbacks.size() == 0) {
//                System.out.println("success,  [t, response2]  callbacks is empty return ");
//                return;
//            }
//            System.out.println("success,  [t, response2] t = " + t);
//            try {
//                Gson gson = new Gson();
//                String s = gson.toJson(t);
////                GsonConverter converter = new GsonConverter(gson);
////                if (clasz != null) {
////                    BaseRespone res = (BaseRespone) converter.fromBody(t.getBody(), BaseRespone.class);
//                if ("1".equals(t.get("code"))) {
//                    T tt = (T) gson.fromJson(s, clasz);
////                    T tt = (clasz)t;
//                    if (tt != null) {
//                        onSuccessCallBack(tt, tag);
//                    } else onFailure("no data", tag);
//                } else {
//                    onFailure(String.valueOf(t.get("errMsg")), tag);
//                }
////                } else {
//////                    JSONObject obj = new JSONObject(jsonString);
////                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                onFailure(e.getMessage(), tag);
//            }
//        }
//
//        @Override
//        public void failure(RetrofitError error) {
//            if (callbacks == null || callbacks.size() == 0) {
////                MyUtil.showLog("Callback", "failure.91.[error] callbacks is empty return ");
//                return;
//            }
//            onFailure(error.getMessage(), tag);
//        }

        @Override
        public void onResponse(Call call, Response response) {
            Object body = response.body();
            System.out.println("onResponse,  [call, response]  call.isCanceled() = " + call.isCanceled() + " " + body);
            if (call.isCanceled()) return;
            if (callbacks == null || callbacks.size() == 0) {
                System.out.println("success,  [t, response2]  callbacks is empty return ");
                return;
            }
            try {
//                Gson gson = new Gson();
//                String s = gson.toJson(body);
//                BaseRespone res = (BaseRespone) body;
//                if (1 == res.getCode()) {
//                    T tt = (T) body;
//                    if (tt != null) {
//                        onSuccessCallBack(tt, tag);
//                    } else onFail("no data", tag);
//                } else {
//                    onFail(res.getErrMsg(), tag);
//                }
            } catch (Exception e) {
                e.printStackTrace();
                onFail(e.getMessage(), tag);
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            onFail(t.getMessage(), tag);
            t.printStackTrace();
        }
    }

}
