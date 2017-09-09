package com.ard.weather.activity.presenter;

/**
 * Created by Administrator on 2017/2/20.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();

}
