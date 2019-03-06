package com.ard.weather.activity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.ard.weather.activity.base.BaseActivity;
import com.ard.weather.activity.ui.activity.WeatherActivity;
import com.ard.weather.activity.uitl.StatusBarUtil;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity  {

    @BindView(R.id.activity_main)
    RelativeLayout activityLogoImage;

    private AlphaAnimation alphaAnimation;
    private Handler handler;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            showPermissions();
        }else {
            startActivityLnt();
        }
    }

    @Override
    protected void initBeforeData() {

    }


    private void startActivityLnt(){
        alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2500);
        activityLogoImage.startAnimation(alphaAnimation);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UtilFileDB.ADDSHAREDDATA("zqweatherdatatime", Utils.dateTime());
                Intent intent = new Intent(MainActivity.this,WeatherActivity.class);
                startActivityForResult(intent,1);
            }
        }, 2500);
    }


    /***
     * 递归实现 权限申请
     */
    protected void showPermissions() {
        AndPermission.with(this)
                .requestCode(101)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .send();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, listener);
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode) {
            if (requestCode == 101) {
                startActivityLnt();
            }
        }

        @Override
        public void onFailed(int requestCode) {
            if (requestCode == 101) {
                showPermissions();
            }
        }
    };

    protected void setStatusBar() {
        StatusBarUtil.setTranslucent(this, 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                manager.killBackgroundProcesses(getPackageName());
                finish();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
        handler = null;
    }



}
