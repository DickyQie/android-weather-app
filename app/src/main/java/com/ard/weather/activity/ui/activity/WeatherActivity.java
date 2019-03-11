package com.ard.weather.activity.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.base.BaseMvpActivity;
import com.ard.weather.activity.entity.WeatherBean;
import com.ard.weather.activity.presenter.HomePresenter;
import com.ard.weather.activity.ui.adapter.HomeGridRecyclerAdapter;
import com.ard.weather.activity.ui.adapter.HomeHorizontaRrecyclerAdapter;
import com.ard.weather.activity.ui.adapter.HomeVerticalRecyclerAdapter;
import com.ard.weather.activity.ui.view.AppBarStateChangeListener;
import com.ard.weather.activity.ui.view.IView;
import com.ard.weather.activity.ui.view.OnItemClickListenter;
import com.ard.weather.activity.ui.view.State;
import com.ard.weather.activity.uitl.NetUtils;
import com.ard.weather.activity.uitl.UtilFileDB;
import com.ard.weather.activity.uitl.Utils;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;

/**
 * Created by zhangqie on 2019/2/28
 * Describe: 打包信息，别名：weather  密码:zhangqie
 */
public class WeatherActivity extends BaseMvpActivity<IView.IMvpWeatherListener, HomePresenter> implements IView.IMvpWeatherListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_weather_back)
    LinearLayout llWeatherBack;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.weather_windspeed)
    TextView weatherWindspeed;
    @BindView(R.id.weather_pm)
    TextView weatherPm;
    @BindView(R.id.weather_logo)
    ImageView weatherLogo;
    @BindView(R.id.weather_direct)
    TextView weatherDirect;
    @BindView(R.id.weather_content)
    TextView weatherContent;
    @BindView(R.id.weather_humidity)
    TextView weatherHumidity;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.recycler_horizontal)
    RecyclerView recyclerHorizontal;
    @BindView(R.id.recycler_vertical)
    RecyclerView recyclerVertical;
    @BindView(R.id.recycler_grid)
    RecyclerView recyclerGrid;
    @BindView(R.id.btn_fab)
    FloatingActionButton btnFab;


    private long mExitTime;
    private final int INTERVAL = 2000;
    private String cityName;
    private String temperature;
    private String dateWeek;
    private HomeHorizontaRrecyclerAdapter homeHorizontaRrecyclerAdapter;
    private HomeVerticalRecyclerAdapter homeVerticalRecyclerAdapter;
    private HomeGridRecyclerAdapter homeGridRecyclerAdapter;

    @Override
    protected int setMainLayout() {
        return R.layout.weather_main;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initView() {
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    toolbar.setTitle("");
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.setTitle(cityName + " " + temperature);
                } else {
                    //中间状态
                }
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerHorizontal.setLayoutManager(linearLayoutManager);
        recyclerHorizontal.setNestedScrollingEnabled(false);

        recyclerVertical.setLayoutManager(new LinearLayoutManager(this));
        recyclerVertical.setNestedScrollingEnabled(false);

        //第三部分
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        recyclerGrid.setLayoutManager(gridLayoutManager);
        //  recyclerGrid.addItemDecoration(new GridDividerItemDecoration(this));
        recyclerGrid.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initBeforeData() {
        cityName = UtilFileDB.SELECTSHAREDDATA("mapcity");
        if (NetUtils.isNetworkAvailable(this)) {
            String cityId = UtilFileDB.SELECTSHAREDDATA("city_id");
            p.showData(cityId.equals("") ? getString(R.string.city_id) : cityId);
        } else {
            showToast(R.string.no_network);
        }
    }

    @Override
    public void onDataCallBackListenter(WeatherBean weatherBean) {
        if (weatherBean.getCode().equals("200")) {
            showHeaderData(weatherBean);
        }

    }

    /***
     *  heander 数据绑定
     */
    private void showHeaderData(final WeatherBean weatherBean) {
        try {
            llWeatherBack.setBackgroundResource(Utils.showWeatherBackStyle(Integer.valueOf(Utils.dateTimeHH()), weatherBean.getValue().get(0).getWeathers().get(0).getWeather()));
            temperature = weatherBean.getValue().get(0).getWeathers().get(0).getTemp_day_c() + "℃";
            weatherWindspeed.setText(temperature);
            weatherPm.setText("Pm值：" + weatherBean.getValue().get(0).getPm25().getQuality());
            weatherContent.setText(weatherBean.getValue().get(0).getWeathers().get(0).getWeather());
            weatherDirect.setText("风度：" + weatherBean.getValue().get(0).getRealtime().getWD() + "/" +
                    weatherBean.getValue().get(0).getRealtime().getWS());
            weatherHumidity.setText("温度：" + weatherBean.getValue().get(0).getWeathers().get(0).getTemp_night_c()
                    + "℃~" + weatherBean.getValue().get(0).getWeathers().get(0).getTemp_day_c() + "℃");
            cityName = weatherBean.getValue().get(0).getCity();
            tvCityName.setText(weatherBean.getValue().get(0).getCity());

            //weatherLogo.setImageResource(Utils.showWeatherStatusLogo(weatherBean.getValue().get(0).getWeathers().get(0).getWeather()));

            homeHorizontaRrecyclerAdapter = new HomeHorizontaRrecyclerAdapter(this, weatherBean.getValue().get(0).getWeatherDetailsInfo());
            recyclerHorizontal.setAdapter(homeHorizontaRrecyclerAdapter);
            homeVerticalRecyclerAdapter = new HomeVerticalRecyclerAdapter(this, weatherBean.getValue().get(0).getWeathers());
            recyclerVertical.setAdapter(homeVerticalRecyclerAdapter);

            homeGridRecyclerAdapter = new HomeGridRecyclerAdapter(this, weatherBean.getValue().get(0).getIndexes());
            recyclerGrid.setAdapter(homeGridRecyclerAdapter);


            dateWeek = weatherBean.getValue().get(0).getWeathers().get(0).getDate() + "   " + weatherBean.getValue().get(0).getWeathers().get(0).getWeek();

            btnFab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, dateWeek + "   " + temperature, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(WeatherActivity.this, CityListActivity.class);
                            openForResultActivity(intent, 1);
                        }
                    }, 1000);
                }
            });

            homeGridRecyclerAdapter.setOnItemClickListenter(new OnItemClickListenter() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(WeatherActivity.this, WeatherIndexContentActivity.class);
                    intent.putExtra("name", weatherBean.getValue().get(0).getIndexes().get(position).getName());
                    intent.putExtra("level", weatherBean.getValue().get(0).getIndexes().get(position).getLevel());
                    intent.putExtra("content", weatherBean.getValue().get(0).getIndexes().get(position).getContent());
                    openIntent(intent);
                }
            });
        } catch (Exception e) {
            showToast("数据加载失败");
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == 1) {
                UtilFileDB.ADDSHAREDDATA("cityId", data.getStringExtra("cityId"));
                p.showData(data.getStringExtra("cityId"));
            }
        }
    }

    @Override
    public void onError(String error) {
        showToast(error);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        ImmersionBar.with(this).titleBar(toolbar).init();
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
                if (System.currentTimeMillis() - mExitTime > INTERVAL) {
                    showToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    setResult(1);
                    finish();
                }
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
