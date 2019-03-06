package com.ard.weather.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;


import com.ard.weather.activity.R;
import com.ard.weather.activity.presenter.BasePresenter;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.ButterKnife;

/**
 * Created by zhangqie on 2017/2/20.
 */

public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    protected T p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setMainLayout());
        ButterKnife.bind(this);
        p=createPresenter();
        p.attachView((V)this);
        setStatusBar();
        initImmersionBar();
        initView();
        initBeforeData();
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
    protected abstract void initView();

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    protected void showToast(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    protected void openForResultActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    /***
     *状态栏
     */
    protected void setStatusBar() {
      //  StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
        p.detachView();
        p=null;
    }

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        ImmersionBar.with(this).navigationBarColor(R.color.colorPrimary).init();
    }
}

