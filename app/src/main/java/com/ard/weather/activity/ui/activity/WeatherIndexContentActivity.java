package com.ard.weather.activity.ui.activity;

import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangqie on 2019/3/6
 * Describe:
 */
public class WeatherIndexContentActivity extends BaseActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_level_content)
    TextView tvLevelContent;

    @Override
    protected int setMainLayout() {
        return R.layout.weather_index_content;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initBeforeData() {
        tvContent.setText(getIntent().getStringExtra("name"));
        tvLevel.setText(getIntent().getStringExtra("level"));
        tvLevelContent.setText(getIntent().getStringExtra("content"));
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
