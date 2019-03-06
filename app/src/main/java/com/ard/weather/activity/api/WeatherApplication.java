package com.ard.weather.activity.api;

import android.app.Application;
import android.content.SharedPreferences;

import com.orhanobut.hawk.Hawk;

/**
 * Created by zhangqie on 2019/3/1
 * Describe:
 */
public class WeatherApplication extends Application {

    public static SharedPreferences.Editor editor;
    public static SharedPreferences preferences;


    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences("zqweather", 0);
        editor = preferences.edit();
        Hawk.init(this).build();
    }

}
