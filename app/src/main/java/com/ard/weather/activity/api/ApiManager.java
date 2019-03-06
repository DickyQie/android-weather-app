package com.ard.weather.activity.api;



import com.ard.weather.activity.api.converter.FastJsonConverterFactory;
import com.ard.weather.activity.api.converter.StringConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by zhangqie on 2017/2/22.
 */

public class ApiManager {
    /**
     * 这个是返回json数据(是对象）时请求所用的对象
     */
    private ApiService jsonApiService;

    /**
     * //这个是返回json是string数据时请求所用的对象
     */
    private ApiService strApiService;

    private static volatile ApiManager instance=null;
    public static ApiManager getInstance(){
        if(instance==null){
            synchronized(ApiManager .class){
                if(instance==null){
                    instance=new ApiManager ();
                }
            }
        }
        return instance;
    }


    public ApiManager() {
        jsonApiService = getJsonServiceInstance();
        strApiService = getStrServiceInstance();
    }
    public ApiService getJsonApiService() {
        return jsonApiService;
    }

    public ApiService getStrApiService() {
        return strApiService;
    }


    private ApiService getJsonServiceInstance() {
        if (null == jsonApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain
                            .request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.URL)
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            jsonApiService = retrofit.create(ApiService.class);
        }

        return jsonApiService;
    }


    private ApiService getStrServiceInstance() {
        if (null == strApiService) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain
                            .request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request);
                }
            }).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.URL)
                    .addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            strApiService = retrofit.create(ApiService.class);
        }

        return strApiService;
    }

}
