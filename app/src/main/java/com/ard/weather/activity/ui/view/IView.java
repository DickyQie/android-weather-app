package com.ard.weather.activity.ui.view;

import com.ard.weather.activity.entity.WeatherBean;


/**
 * Created by zhangqie on 2017/2/20.
 */

public interface IView {


    interface IMvpWeatherListener{

        void onDataCallBackListenter(WeatherBean weatherBean);
        void onError(String error);
    }



}
