package com.ard.weather.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;


import com.ard.weather.activity.R;
import com.ard.weather.activity.presenter.BasePresenter;
import com.ard.weather.activity.uitl.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/20.
 */

public abstract class BasenActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setMainLayout());
        ButterKnife.bind(this);
        p=createPresenter();
        p.attachView((V)this);
        setStatusBar();
        try {
            initEvents();
            initBeforeData();
        } catch (Exception e) {
            showToastShort("");
        }
    }

    public abstract T createPresenter();
    /**
     * 初始化布局
     */
    protected abstract int setMainLayout();

    /**
     * 初始化先前数据
     */
    protected abstract void initBeforeData();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

    /**
     * 弹出toast 显示时长short
     *
     * @param msg
     */
    protected void showToastShort(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 弹出toast 显示时长long
     *
     * @param msg
     */
    protected void showToastLong(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /***
     * 跳转Activity
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        openIntent(new Intent(this, mClass));
    }

    protected void openIntent(Intent intent) {
        startActivity(intent);
    }

    /***
     *状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        p.detachView();
        p=null;
    }
}

