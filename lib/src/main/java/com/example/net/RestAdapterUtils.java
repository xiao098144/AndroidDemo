package com.example.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Description：
 * Created on 2017/4/27
 * Author : 萧
 */
public class RestAdapterUtils {


    private static final int CONNECTTION_TIMEOUTE = 30;

    private static final int READ_TIMEOUT = 30;
    private static final String WAPI_URL_TEST = "http://test.ddoctor.cn";

    /**
     * 默认API 生成  地址 {@link#Configs.URL_SERVER}
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getRestAPI(final Class<T> service) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECTTION_TIMEOUTE, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //这个chain里面包含了request和response，所以你要什么都可以从这里拿
                Request request = chain.request();
                long t1 = System.nanoTime();//请求发起的时间

                String method = request.method();
                if ("POST".equals(method)) {
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        System.out.println("intercept,  [chain] "+String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                                request.url(), chain.connection(), request.headers(), sb.toString()));
                    }
                } else {
                    System.out.println("intercept,  [chain]  "+String.format("发送请求 %s on %s%n%s",
                            request.url(), chain.connection(), request.headers()));
                }
                Response response = chain.proceed(request);
                long t2 = System.nanoTime();//收到响应的时间
                //这里不能直接使用response.body().string()的方式输出日志
                //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
                //个新的response给应用层处理
                ResponseBody responseBody = response.peekBody(1024 * 1024);
                System.out.println("intercept,  [chain] "+String.format("接收响应: [%s] %n返回json:%s %.1fms %n%s",
                        response.request().url(), responseBody.string(),(t2 - t1) / 1e6d,response.headers()));
                return response;
            }
        }).addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        OkHttpClient client = builder.retryOnConnectionFailure(true).build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(WAPI_URL_TEST).client(client).build();

        return retrofit.create(service);
    }


}
