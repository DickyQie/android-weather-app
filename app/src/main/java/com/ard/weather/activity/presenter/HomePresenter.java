package com.ard.weather.activity.presenter;

import android.content.Context;

import com.ard.weather.activity.api.Api;
import com.ard.weather.activity.model.HomeModel;
import com.ard.weather.activity.ui.view.IView;
import com.ard.weather.activity.uitl.ACache;


/**
 * Created by Administrator on 2017/2/20.
 */

public class HomePresenter  extends BasePresenter<IView.IMvpListener> {


    private HomeModel baseModel;
    private ACache aCache;

    public HomePresenter(IView.IMvpListener listener,Context context){
        baseModel=new HomeModel(listener,context);
    }
    public void showData(Context context)
    {
        aCache=ACache.get(context);
        baseModel.addOrUpdateGoods(Api.showHomeTourShowApi(aCache),1);
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
