package com.ard.weather.activity;

import android.os.Handler;
import android.os.Message;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ard.weather.activity.api.Api;
import com.ard.weather.activity.api.Utils;
import com.ard.weather.activity.base.BaseActivity;
import com.ard.weather.activity.ui.activity.WeatherHomeActivity;
import com.ard.weather.activity.uitl.ACache;
import com.ard.weather.activity.uitl.StatusBarUtil;
import com.ard.weather.activity.uitl.UtilFileDB;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements AMapLocationListener {

    @Bind(R.id.activity_main)
    RelativeLayout activityLogoImage;
    private AlphaAnimation alphaAnimation;

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private ACache aCache;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEvents() {
        EventBus.getDefault().register(this);
        aCache = ACache.get(MainActivity.this);
    }

    @Override
    protected void initBeforeData() {
        alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2500);
        activityLogoImage.startAnimation(alphaAnimation);
        if (UtilFileDB.SELETEFile(aCache, "mapcity") == null) {
            UtilFileDB.ADDFile(aCache, "mapcity", Api.cityName);
            Location();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    openActivity(WeatherHomeActivity.class);
                }
            }, 2500);
        }else{
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    openActivity(WeatherHomeActivity.class);
                }
            }, 2500);
        }


    }

    @Subscriber(tag = "finish")
    public void showFinsh(boolean isfinsh) {
        if (isfinsh) {
            finish();
        }
    }

    protected void setStatusBar() {
        StatusBarUtil.setTranslucent(this, 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLocationChanged(AMapLocation loc) {
        if (null != loc) {
            Message msg = mHandler.obtainMessage();
            msg.obj = loc;
            msg.what = Utils.MSG_LOCATION_FINISH;
            mHandler.sendMessage(msg);
        }

    }

    Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                //定位完成
                case Utils.MSG_LOCATION_FINISH:
                    String result = "";
                    try {
                        AMapLocation loc = (AMapLocation) msg.obj;
                        result = Utils.getLocationStr(loc, 1);
                        UtilFileDB.ADDFile(aCache, "mapLocCity", result);
                    } catch (Exception e) {
                        showToastShort("定位失败");
                    }
                    break;
                default:
                    break;
            }
        }

        ;

    };

    public void Location() {
        // TODO Auto-generated method stub
        try {
            locationClient = new AMapLocationClient(this);
            locationOption = new AMapLocationClientOption();
            // 设置定位模式为低功耗模式
            locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            // 设置定位监听
            locationClient.setLocationListener(this);
            locationOption.setOnceLocation(true);//设置为单次定位
            locationClient.setLocationOption(locationOption);// 设置定位参数
            // 启动定位
            locationClient.startLocation();
            mHandler.sendEmptyMessage(Utils.MSG_LOCATION_START);
        } catch (Exception e) {
            showToastShort("定位失败");
        }
    }
}
