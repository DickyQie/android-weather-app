package com.ard.weather.activity.model;


import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.ard.weather.activity.api.ApiManager;
import com.ard.weather.activity.api.ApiService;
import com.ard.weather.activity.entity.A;
import com.ard.weather.activity.ui.view.IView;
import com.ard.weather.activity.uitl.ACache;
import com.ard.weather.activity.uitl.UtilFileDB;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/2/20.
 */

public class HomeModel extends BaseModel<IView.IMvpListener> {

    public ApiService strApiService = ApiManager.getInstance().getStrApiService();
    private ACache aCache;

    private Context context;
    public HomeModel(IView.IMvpListener listener, Context context) {
        aCache = ACache.get(context);
        this.context=context;
        attachModel(listener);
    }

    public void addOrUpdateGoods(Map<String, Object> map, final int index) {

        String response = UtilFileDB.SELETEFile(aCache, "citydata");
        if (response != null) {
            try {
                A info = JSON.parseObject(response, A.class);
                if (info.getShowapi_res_body().getF1()!=null)
                {
                    listener.onDataCallBackListenter(info);
                }else{
                    return;
                }
            } catch (Exception e) {
                return;
            }
        } else {

            addSubscription(strApiService.getDetailContent(map), new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onNext(String result) {
                    UtilFileDB.ADDFile(aCache, "citydata", result, 82811);
                    try {
                        A info = JSON.parseObject(result, A.class);
                        if (info.getShowapi_res_body().getF1()!=null)
                        {
                            listener.onDataCallBackListenter(info);
                        }else{
                            return;
                        }
                    } catch (Exception e) {
                         return;
                    }

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


}
