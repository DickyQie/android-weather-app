package com.ard.weather.activity.model;

/**
 * Created by Administrator on 2017/2/20.
 */

public interface Model<L> {

    void attachModel(L listener);

    void detachModel();

}
