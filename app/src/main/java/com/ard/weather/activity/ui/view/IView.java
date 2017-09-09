package com.ard.weather.activity.ui.view;

import com.ard.weather.activity.entity.A;


/**
 * Created by Administrator on 2017/2/20.
 */

public interface IView {

    interface IMvpListener{

       // void onDataCallBackListenter(WeatherInfo list);
        void onDataCallBackListenter(A list);
        void onError(String error);
    }




}
