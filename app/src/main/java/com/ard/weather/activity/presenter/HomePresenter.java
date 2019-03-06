package com.ard.weather.activity.presenter;

import com.ard.weather.activity.model.HomeModel;
import com.ard.weather.activity.ui.view.IView;


/**
 * Created by zhangqie on 2017/2/20.
 */

public class HomePresenter extends BasePresenter<IView.IMvpWeatherListener> {


    private HomeModel baseModel;

    public HomePresenter(IView.IMvpWeatherListener listener){
        baseModel=new HomeModel(listener);
    }
    public void showData(String id)
    {
        baseModel.showWeatherDataSubscription(id);
    }


    @Override
    public void detachView() {
        super.detachView();
        if (baseModel!=null)
        {
            baseModel.detachModel();
            baseModel=null;
        }
    }
}
