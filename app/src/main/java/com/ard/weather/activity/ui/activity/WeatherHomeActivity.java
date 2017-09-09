package com.ard.weather.activity.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.ard.weather.activity.R;
import com.ard.weather.activity.base.BasenActivity;
import com.ard.weather.activity.entity.A;
import com.ard.weather.activity.entity.B;
import com.ard.weather.activity.entity.LocationInfo;
import com.ard.weather.activity.entity.WeatherHome;
import com.ard.weather.activity.presenter.HomePresenter;
import com.ard.weather.activity.ui.view.IView;
import com.ard.weather.activity.uitl.ACache;
import com.ard.weather.activity.uitl.NetUtils;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;
import com.ard.weather.activity.widget.LinearLayoutForListView;
import com.ard.weather.activity.widget.MyChartItem;
import com.ard.weather.activity.widget.MyChartView;
import com.ard.weather.activity.widget.WeatherNighView;
import com.ard.weather.activity.widget.WeatherTime;
import com.squareup.picasso.Picasso;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/27.
 * http://avatardata.cn/Docs/Api/dc4394f5-9f45-46aa-91c4-03ace20ec347
 *
 * http://wthrcdn.etouch.cn/weather_mini?city=%E5%8C%97%E4%BA%AC
 *
 * bb1d15ab7ce646ec87cc89d684ca4bcb
 *
 * http://route.showapi.com/9-5?showapi_appid=11548&showapi_sign=bb1d15ab7ce646ec87cc89d684ca4bcb&from=1&lng=116.2278&lat=40.242265&needMoreDay=1&needIndex=1&needAlarm=1
 */

public class WeatherHomeActivity extends BasenActivity<IView.IMvpListener, HomePresenter> implements IView.IMvpListener {


    @Bind(R.id.weather_cityname)
    TextView weatherCityname;
    @Bind(R.id.weather_windspeed)
    TextView weatherWindspeed;
    @Bind(R.id.weather_img)
    ImageView weatherImg;
    @Bind(R.id.weather_content)
    TextView weatherContent;
    @Bind(R.id.weather_time)
    TextView weatherTime;
    @Bind(R.id.weather_yuj)
    TextView weatherYuj;
    @Bind(R.id.weather_direct)
    TextView weatherDirect;
    @Bind(R.id.weather_humidity)
    TextView weatherHumidity;
    @Bind(R.id.weather_time_rl)
    TextView weatherTimeRl;
    @Bind(R.id.weather_time_nl)
    TextView weatherTimeNl;
    @Bind(R.id.chart1)
    MyChartView chart1;
    @Bind(R.id.chart2)
    MyChartView chart2;
    @Bind(R.id.weather_time1)
    WeatherTime weatherTime1;
    @Bind(R.id.weather_time2)
    WeatherTime weatherTime2;
    @Bind(R.id.weather_time3)
    WeatherTime weatherTime3;
    @Bind(R.id.weather_time4)
    WeatherTime weatherTime4;
    @Bind(R.id.weather_time5)
    WeatherTime weatherTime5;
    @Bind(R.id.weather_time6)
    WeatherTime weatherTime6;
    @Bind(R.id.weather_time7)
    WeatherTime weatherTime7;
    @Bind(R.id.weather_nigh1)
    WeatherNighView weatherNigh1;
    @Bind(R.id.weather_nigh2)
    WeatherNighView weatherNigh2;
    @Bind(R.id.weather_nigh3)
    WeatherNighView weatherNigh3;
    @Bind(R.id.weather_nigh4)
    WeatherNighView weatherNigh4;
    @Bind(R.id.weather_nigh5)
    WeatherNighView weatherNigh5;
    @Bind(R.id.weather_nigh6)
    WeatherNighView weatherNigh6;
    @Bind(R.id.weather_nigh7)
    WeatherNighView weatherNigh7;
   /* @Bind(R.id.recyclerView)
    RecyclerView recyclerView;*/

    @Bind(R.id.listview)
    LinearLayoutForListView listView;

    ACache aCache;

    @Override
    protected int setMainLayout() {
        return R.layout.weather_main;
    }


    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this,WeatherHomeActivity.this);
    }

    @Override
    protected void initEvents() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initBeforeData() {
        aCache=ACache.get(WeatherHomeActivity.this);
        String response = UtilFileDB.SELETEFile(aCache, "citydata");
        if (!NetUtils.isNetworkAvailable(WeatherHomeActivity.this)) {

            if (response == null) {
                try {
                    A info = JSON.parseObject(B.str, A.class);
                    if (info.getShowapi_res_body().getF1() != null) {
                        showTop(info);
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            }else {
                try {
                    A info = JSON.parseObject(response, A.class);
                    if (info.getShowapi_res_body().getF1() != null) {
                        showTop(info);
                    } else {
                        return;
                    }
                } catch (Exception e) {
                    return;
                }
            }
        }else {
            p.showData(WeatherHomeActivity.this);
        }
    }



    @Subscriber(tag = "location")
    public void showLocation(LocationInfo locationInfo) {
        p.showData(WeatherHomeActivity.this);

    }
    @Override
    public void onDataCallBackListenter(A list) {
        showTop(list);
    }


    private void showTop(A list){

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF1().getDay_weather_pic())
                .into(weatherImg);
        weatherWindspeed.setText(Utils.SHOWTEMPERATURE(list.getShowapi_res_body().getF1().getDay_air_temperature(),list.getShowapi_res_body().getF1().getNight_air_temperature())+"°");
        weatherCityname.setText(list.getShowapi_res_body().getCityInfo().getC3());
        weatherYuj.setText("紫外线："+list.getShowapi_res_body().getF1().getZiwaixian());
        weatherTime.setText("发布时间：" + (list.getShowapi_res_body().getF1().getSun_begin_end().replace("|", "~")));
        weatherContent.setText(list.getShowapi_res_body().getF1().getDay_weather());
        weatherDirect.setText("风度：" + Utils.showCityWindPower((list.getShowapi_res_body().getF1().getDay_wind_power().toString().substring(0, 2))));
        weatherHumidity.setText("温度： " + list.getShowapi_res_body().getF1().getNight_air_temperature() + "℃~ " + list.getShowapi_res_body().getF1().getDay_air_temperature()+"℃ ");
        weatherTimeRl.setText(Utils.showTime(list.getShowapi_res_body().getF1().getWeekday()));
        int[] dateTime=Utils.showLunarDateTime(list.getShowapi_res_body().getF1().getDay());
        weatherTimeNl.setText("农历"+dateTime[0]+"年"+dateTime[1]+"月"+dateTime[2]+"日");

        /***
         * 中间部分
         */
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF1().getDay_weather_pic())
                .into(weatherTime1.showImage());
        weatherTime1.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF1().getWeekday())));
        weatherTime1.setTime(Utils.showDateTime(list.getShowapi_res_body().getF1().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF2().getDay_weather_pic())
                .into(weatherTime2.showImage());
        weatherTime2.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF2().getWeekday())));
        weatherTime2.setTime(Utils.showDateTime(list.getShowapi_res_body().getF2().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF3().getDay_weather_pic())
                .into(weatherTime3.showImage());
        weatherTime3.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF3().getWeekday())));
        weatherTime3.setTime(Utils.showDateTime(list.getShowapi_res_body().getF3().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF4().getDay_weather_pic())
                .into(weatherTime4.showImage());
        weatherTime4.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF4().getWeekday())));
        weatherTime4.setTime(Utils.showDateTime(list.getShowapi_res_body().getF4().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF5().getDay_weather_pic())
                .into(weatherTime5.showImage());
        weatherTime5.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF5().getWeekday())));
        weatherTime5.setTime(Utils.showDateTime(list.getShowapi_res_body().getF5().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF6().getDay_weather_pic())
                .into(weatherTime6.showImage());
        weatherTime6.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF6().getWeekday())));
        weatherTime6.setTime(Utils.showDateTime(list.getShowapi_res_body().getF6().getDay()));

        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF7().getDay_weather_pic())
                .into(weatherTime7.showImage());
        weatherTime7.setTextDay("周"+Utils.showDay(Integer.valueOf(list.getShowapi_res_body().getF7().getWeekday())));
        weatherTime7.setTime(Utils.showDateTime(list.getShowapi_res_body().getF7().getDay()));

        try {
            weatherNigh1.setTextDay(list.getShowapi_res_body().getF1().getNight_weather());
        } catch (Exception e) {
            weatherNigh1.setTextDay(list.getShowapi_res_body().getF1().getDay_weather());
        }
        try {
            weatherNigh2.setTextDay(list.getShowapi_res_body().getF2().getNight_weather());
        } catch (Exception e) {
            weatherNigh2.setTextDay(list.getShowapi_res_body().getF2().getDay_weather());
        }
        try {
            weatherNigh3.setTextDay(list.getShowapi_res_body().getF3().getNight_weather());
        } catch (Exception e) {
            weatherNigh3.setTextDay(list.getShowapi_res_body().getF3().getDay_weather());
        }
        try {
            weatherNigh4.setTextDay(list.getShowapi_res_body().getF4().getNight_weather());
        } catch (Exception e) {
            weatherNigh4.setTextDay(list.getShowapi_res_body().getF4().getDay_weather());
        }   try {
            weatherNigh5.setTextDay(list.getShowapi_res_body().getF5().getNight_weather());
        } catch (Exception e) {
            weatherNigh5.setTextDay(list.getShowapi_res_body().getF5().getDay_weather());
        }
        try {
            weatherNigh6.setTextDay(list.getShowapi_res_body().getF6().getNight_weather());
        } catch (Exception e) {
            weatherNigh6.setTextDay(list.getShowapi_res_body().getF6().getDay_weather());
        }
        try {
            weatherNigh7.setTextDay(list.getShowapi_res_body().getF7().getNight_weather());
        } catch (Exception e) {
            weatherNigh7.setTextDay(list.getShowapi_res_body().getF7().getDay_weather());
        }
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF1().getNight_weather_pic())
                .into(weatherNigh1.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF2().getNight_weather_pic())
                .into(weatherNigh2.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF3().getNight_weather_pic())
                .into(weatherNigh3.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF4().getNight_weather_pic())
                .into(weatherNigh4.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF5().getNight_weather_pic())
                .into(weatherNigh5.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF6().getNight_weather_pic())
                .into(weatherNigh6.showImage());
        Picasso.with(WeatherHomeActivity.this)
                .load(list.getShowapi_res_body().getF7().getNight_weather_pic())
                .into(weatherNigh7.showImage());


        showWeatherXths(list);


      /*  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);*/
        initData(list);

    }

    private void initData(A data) {

        List<WeatherHome>  list=new ArrayList<WeatherHome>();
        list.add(new WeatherHome("穿衣指数",data.getShowapi_res_body().getF1().getIndex().getClothes().getDesc()));
        list.add(new WeatherHome("运动指数",data.getShowapi_res_body().getF1().getIndex().getSports().getDesc()));
        list.add(new WeatherHome("晾晒指数",data.getShowapi_res_body().getF1().getIndex().getLs().getDesc()));
        list.add(new WeatherHome("感冒指数",data.getShowapi_res_body().getF1().getIndex().getCold().getDesc()));
        list.add(new WeatherHome("中暑指数",data.getShowapi_res_body().getF1().getIndex().getZs().getDesc()));
        list.add(new WeatherHome("钓鱼指数",data.getShowapi_res_body().getF1().getIndex().getDy().getDesc()));

        listView.setAdapter(new TimeLineListViewAdapter(this,list));

      //  TimelineAdapter mAdapter = new TimelineAdapter(this, list);
       // recyclerView.setAdapter(mAdapter);
    }

    private void showWeatherXths(A data){
        ArrayList<MyChartItem> list= new ArrayList<MyChartItem>();
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF1().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF2().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF3().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF4().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF5().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF6().getDay_air_temperature())));
        list.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF7().getDay_air_temperature())));
        chart1.SetTuView(list, "最高最低温度：");//单位: 摄氏度
        chart1.invalidate();
        ArrayList<MyChartItem> list1= new ArrayList<MyChartItem>();
        list1.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF1().getNight_air_temperature())));
        list1.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF2().getNight_air_temperature())));
        list1.add(new MyChartItem("", Float.valueOf(data.getShowapi_res_body().getF3().getNight_air_temperature())));
        list1.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF4().getNight_air_temperature())));
        list1.add(new MyChartItem("", Float.valueOf(data.getShowapi_res_body().getF5().getNight_air_temperature())));
        list1.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF6().getNight_air_temperature())));
        list1.add(new MyChartItem("",Float.valueOf(data.getShowapi_res_body().getF7().getNight_air_temperature())));
        chart2.SetTuView(list1, "");
        chart2.invalidate();//更新新数据
    }



    /****
     * 键盘监听
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                EventBus.getDefault().post(true, "finish");
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    public void onError(String error) {
        showToastShort(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.weather_addcity, R.id.weather_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.weather_addcity:
                openActivity(AddCityMapActivity.class);
                break;
            case R.id.weather_share:
                break;
        }
    }
}


