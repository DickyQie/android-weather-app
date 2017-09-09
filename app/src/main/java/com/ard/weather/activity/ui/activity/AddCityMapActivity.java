package com.ard.weather.activity.ui.activity;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.ard.weather.activity.R;
import com.ard.weather.activity.api.AMapUtil;
import com.ard.weather.activity.api.Api;
import com.ard.weather.activity.api.ApiService;
import com.ard.weather.activity.entity.LocationInfo;
import com.ard.weather.activity.uitl.ACache;
import com.ard.weather.activity.uitl.StatusBarUtil;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;
import com.ard.weather.activity.widget.CityPickerView;

import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/1.
 */

public class AddCityMapActivity extends AppCompatActivity implements GeocodeSearch.OnGeocodeSearchListener {


    @Bind(R.id.city_name)
    TextView ciryName;
    @Bind(R.id.citypicker)
    CityPickerView citypicker;
    private AMap aMap;
    private MapView mapView;
    private GeocodeSearch geocoderSearch;
    private Marker regeoMarker;
    private LatLonPoint latLonPoint = new LatLonPoint(26.600586, 104.818260);
    private ACache aCache;
    private String[] strArray;
    private String[] strCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        ButterKnife.bind(this);
        aCache = ACache.get(AddCityMapActivity.this);
        setStatusBar();
        initBeforeData();

    }

    protected void initBeforeData() {
        aMap = mapView.getMap();
        if (aMap != null) {
            regeoMarker = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.punch_dw))));
            //aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        }
        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        getAddress(latLonPoint);
    }


    @OnClick({R.id.home_tour_close, R.id.btn,R.id.city_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_tour_close:
                if (strCity != null) {
                    if (strCity.length == 1) {
                        showLocation(strCity[0], strCity[0]);
                    } else {
                        showLocation(strCity[0], strCity[1]);
                    }
                }else {
                    finish();
                }
                break;
            case R.id.btn:
                strCity = Utils.showLocation(citypicker.getCity_string()).split(",");
                if (strCity.length == 1) {
                    ciryName.setText(strCity[0]);
                } else {
                    ciryName.setText(strCity[0] + strCity[1]);
                }
                break;
            case R.id.city_ok:
                if (strArray.length>2){
                    UtilFileDB.DELETEFile(aCache, "mapcity");
                    UtilFileDB.ADDFile(aCache, "mapcity", strArray[0] + "," + strArray[1]  + "," + strArray[2] );
                    LocationInfo info=new LocationInfo();
                    UtilFileDB.DELETEFile(aCache, "citydata");
                    EventBus.getDefault().post(info, "location");
                    finish();
                }

                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                if (strCity != null) {
                    if (strCity.length == 1) {
                        showLocation(strCity[0], strCity[0]);
                    } else {
                        showLocation(strCity[0], strCity[1]);
                    }
                } else {
                    finish();
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private void showLocation(String city, final String address) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<LocationInfo> call = service.getMapLocation(Api.showLocation(city, address));
        call.enqueue(new Callback<LocationInfo>() {
            @Override
            public void onResponse(Call<LocationInfo> call, Response<LocationInfo> response) {
                try {
                    LocationInfo info = response.body();
                    UtilFileDB.DELETEFile(aCache, "mapcity");
                    UtilFileDB.ADDFile(aCache, "mapcity", address + "," + info.getShowapi_res_body().getLocation().getLng() + "," + info.getShowapi_res_body().getLocation().getLat());
                    strCity = null;
                    UtilFileDB.DELETEFile(aCache, "citydata");
                    EventBus.getDefault().post(info, "location");
                    finish();
                } catch (Exception e) {
                   Toast.makeText(AddCityMapActivity.this,"地区查询失败",Toast.LENGTH_LONG).show();
                    return;
                }
            }
            @Override
            public void onFailure(Call<LocationInfo> call, Throwable t) {
                finish();
            }
        });
    }


    /***
     * 状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimaryDark));
        strArray = UtilFileDB.SELETEFile(aCache, "mapLocCity").split(",");
        latLonPoint = new LatLonPoint(Double.valueOf(strArray[2]), Double.valueOf(strArray[1]));
        ciryName.setText(strArray[0]);
    }

    /**
     * 响应逆地理编码
     */
    public void getAddress(final LatLonPoint latLonPoint) {
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,
                GeocodeSearch.AMAP);// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        geocoderSearch.getFromLocationAsyn(query);// 设置同步逆地理编码请求
    }


    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {

    }

    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int rCode) {
        if (rCode == 1000) {
            if (result != null && result.getRegeocodeAddress() != null
                    && result.getRegeocodeAddress().getFormatAddress() != null) {
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        AMapUtil.convertToLatLng(latLonPoint), 15));
                regeoMarker.setPosition(AMapUtil.convertToLatLng(latLonPoint));
            } else {

            }
        } else {
        }
    }


    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}
