package com.ard.weather.activity.api;


import com.ard.weather.activity.entity.LocationInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {


    @FormUrlEncoded
    @POST("app.php")
    Observable<String> getDetailContents(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("9-5")
    Observable<String> getDetailContent(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("238-1")
    Call<LocationInfo> getMapLocation(@FieldMap Map<String, Object> params);

}
