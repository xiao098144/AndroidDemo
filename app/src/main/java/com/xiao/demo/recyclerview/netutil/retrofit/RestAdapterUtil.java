package com.xiao.demo.recyclerview.netutil.retrofit;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit.Endpoint;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Description：
 * Created on 2017/4/7
 * Author : 萧
 */
public class RestAdapterUtil {

    private void init() {
        RestAdapter adapter = new RestAdapter.Builder().setClient(new Client() {
            @Override
            public Response execute(Request request) throws IOException {
                return null;
            }
        }).setEndpoint(new Endpoint() {
            @Override
            public String getUrl() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }
        }).setErrorHandler(new ErrorHandler() {
            @Override
            public Throwable handleError(RetrofitError cause) {
                return null;
            }
        }).setConverter(new GsonConverter(new Gson())).build();
    }

}
