package com.example.net;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description：
 * Created on 2017/5/20
 * Author : 萧
 */
public interface APIUtil {

    @POST("/s")
    Call<String> request(@Body BaesRequest request);

    @POST("/s")
    Call<String> request2(@Body BaesRequest request);


}
