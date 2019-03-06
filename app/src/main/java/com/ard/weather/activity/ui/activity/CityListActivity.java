package com.ard.weather.activity.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.ard.weather.activity.R;
import com.ard.weather.activity.base.BaseActivity;
import com.ard.weather.activity.entity.CityBean;
import com.ard.weather.activity.ui.adapter.CityListRecyclerAdapter;
import com.ard.weather.activity.ui.view.OnItemClickListenter;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zhangqie on 2019/2/28
 * Describe:
 */
public class CityListActivity extends BaseActivity {


    @BindView(R.id.recycler_city_list)
    RecyclerView recyclerCityList;

    CityListRecyclerAdapter cityListRecyclerAdapter;

    @Override
    protected int setMainLayout() {
        return R.layout.city_list_layout;
    }


    @Override
    protected void initView() {
        //第三部分
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerCityList.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void initBeforeData() {
        final CityBean cityBean = JSON.parseObject(Utils.ReadDayDayString(this), CityBean.class);
        cityListRecyclerAdapter = new CityListRecyclerAdapter(this, cityBean.getCities());
        recyclerCityList.setAdapter(cityListRecyclerAdapter);

        cityListRecyclerAdapter.setOnItemClickListenter(new OnItemClickListenter() {
            @Override
            public void onItemClick(View view, int position) {
                UtilFileDB.DELETEDATA("zqweatherdata");
                showToast(cityBean.getCities().get(position).getCity());
                Intent intent = new Intent();
                intent.putExtra("cityId", cityBean.getCities().get(position).getCityid());
                setResult(1, intent);
                finish();

            }
        });
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
