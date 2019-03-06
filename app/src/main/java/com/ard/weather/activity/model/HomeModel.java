package com.ard.weather.activity.model;


import android.util.Log;

import com.ard.weather.activity.api.ApiManager;
import com.ard.weather.activity.api.ApiService;
import com.ard.weather.activity.entity.WeatherBean;
import com.ard.weather.activity.ui.view.IView;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;

import rx.Subscriber;

/**
 * Created by zhangqie on 2017/2/20.
 */

public class HomeModel extends BaseModel<IView.IMvpWeatherListener> {

    public ApiService strApiService = ApiManager.getInstance().getStrApiService();
    public ApiService jsonApiService = ApiManager.getInstance().getJsonApiService();


    public HomeModel(IView.IMvpWeatherListener listener) {
        attachModel(listener);
    }

    public void showWeatherDataSubscription(String id) {
        if (UtilFileDB.ISSELETEDATA("zqweatherdata")) {
            if (UtilFileDB.SELECTSHAREDDATA("zqweatherdatatime").equals(Utils.dateTime())) {
                WeatherBean weatherBean = (WeatherBean) UtilFileDB.SELETEDATA("zqweatherdata");
                listener.onDataCallBackListenter(weatherBean);
            }else {
                showRequestWeatherHttp(id);
            }
        }else {
            showRequestWeatherHttp(id);
        }
    }


    private void showRequestWeatherHttp(String id){
        addSubscription(jsonApiService.getWeatherContent(id), new Subscriber<WeatherBean>() {
            @Override
            public void onCompleted() {
                Log.i("aaa","onCompleted");
            }

            @Override
            public void onNext(WeatherBean result) {
                Log.i("aaa",result.getCode());
                UtilFileDB.ADDDATA("zqweatherdata",result);
                listener.onDataCallBackListenter(result);
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onError(Throwable e) {
                listener.onError("数据加载失败");
            }
        });
    }

}
