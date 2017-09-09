package com.ard.weather.activity.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/1/4.
 */

public abstract class BasePresenter<T> implements Presenter<T> {

    WeakReference<T> weakReference;

    @Override
    public void attachView(T view) {
        weakReference=new WeakReference<T>(view);
    }
    @Override
    public void detachView() {
        if(weakReference!=null)
        {
            weakReference.clear();
            weakReference=null;
        }
    }

    public T getView() {
        return weakReference.get();
    }

}
