package com.example.net;

import java.util.Map;

import retrofit2.Call;

/**
 * Description：
 * Created on 2017/5/20
 * Author : 萧
 */
public class RetrofitTestUtil {

    IHttpCallBack callBack = new IHttpCallBack() {
        @Override
        public void onSuccessCallBack(Object o, String tag) {
            System.out.println("onSuccessCallBack,  [o, tag] tag = " + tag + " " + o);
        }

        @Override
        public void onFail(String failureMsg, String tag) {
            System.out.println("onFailure,  [failureMsg, tag] tag = " + tag + " failureMsg = " + failureMsg);
        }
    };

    public static void main(String[] args) {
        RetrofitTestUtil util = new RetrofitTestUtil();
        util.getSugarStatictics();
        util.startGetChartData();

    }

    private void startGetChartData() {

        SugarValueListRequestBean requestBean = new SugarValueListRequestBean(10000039, "2017-05-01", "2017-05-20", 1, 0);
        // Fragment 中 应如下调用 而不是 RequestApiUtil.request()
        APIUtil restAPI = RestAdapterUtils.getRestAPI(APIUtil.class);
        Call<String> callback = restAPI.request2(requestBean);
        callback.enqueue(callBack.getCallBack(Action.GET_SUGARVALUE_LIST, String.class));
//        com.squareup.okhttp.Response response = callback.execute();

//        restAPI.request(requestBean, callBack.getCallBack(Action.GET_SUGARVALUE_LIST, Map.class));
    }

    /**
     * 获取血糖统计信息
     */
    private void getSugarStatictics() {
        GetSugarStaticsRequestBean requestBean = new GetSugarStaticsRequestBean("2017-05-01", "2017-05-20");
        APIUtil restAPI = RestAdapterUtils.getRestAPI(APIUtil.class);
        Call<String> call = restAPI.request(requestBean);
        call.enqueue(callBack.getCallBack(Action.GET_SUGAR_STATISTICS_VALUE, String.class));
//        restAPI.request(requestBean, callBack.getCallBack(Action.GET_SUGAR_STATISTICS_VALUE, Map.class));
    }

}
