package com.ard.weather.activity.model;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/2/20.
 */

public class BaseModel<L> implements Model<L> {

    protected L listener;
    private CompositeSubscription compositeSubscription;
    @Override
    public void attachModel(L listener) {
        this.listener=listener;
    }

    @Override
    public void detachModel() {
        onUnsubscribe();
        listener=null;
    }
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
    /***
     * 注册
     * @param subscription
     */
    public void addSubscription(Subscription subscription)
    {
        if (compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }
    /***
     * 注册
     */
    public void addSubscription(Observable observable, Subscriber subscriber)
    {
        if (compositeSubscription==null)
        {
            compositeSubscription=new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /***
     * 注册
     */
    public void addSubscription(Observable observable, Func1 func1, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .map(func1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /***
     * 注册
     */
    public void addSubscriptionFlatMap(Observable observable, Func1 func1, Subscriber subscriber) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .flatMap(func1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

}
