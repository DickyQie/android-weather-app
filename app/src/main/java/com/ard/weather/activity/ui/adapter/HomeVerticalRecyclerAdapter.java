package com.ard.weather.activity.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.entity.WeatherBean;
import com.ard.weather.activity.uitl.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangqie on 2019/3/4
 * Describe:
 */
public class HomeVerticalRecyclerAdapter extends RecyclerView.Adapter<HomeVerticalRecyclerAdapter.ViewHolder> {


    private Context context;
    private List<WeatherBean.ValueBean.WeathersBean> weathersBeanList;

    public HomeVerticalRecyclerAdapter(Context context, List<WeatherBean.ValueBean.WeathersBean> weathersBeanList) {
        this.context = context;
        this.weathersBeanList = weathersBeanList;
    }

    @Override
    public int getItemCount() {
        return weathersBeanList != null && weathersBeanList.size() > 0 ? weathersBeanList.size() : 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_vertical_layout, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherBean.ValueBean.WeathersBean weathersBean;
        if (position == 0){
            weathersBean = weathersBeanList.get(6);
        }else {
            weathersBean = weathersBeanList.get(position-1);
        }
        //weathersBean = weathersBeanList.get(position);
        holder.tvWeek.setText(weathersBean.getWeek());
        holder.tvDate.setText(Utils.dateTimeStamp(weathersBean.getDate(),"yyyy-MM-dd","MM-dd"));
        holder.itemTvTemperature1.setText(weathersBean.getTemp_day_c()+"℃");
        holder.itemTvTemperature2.setText(weathersBean.getTemp_night_c()+"℃");
        holder.itemTvWeather.setText(weathersBean.getWeather());
        holder.itemTvLogo.setImageResource(Utils.showWeatherStatusStyleBlue(weathersBean.getWeather()));

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_week)
        TextView tvWeek;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.item_tv_logo)
        ImageView itemTvLogo;
        @BindView(R.id.item_tv_weather)
        TextView itemTvWeather;
        @BindView(R.id.item_tv_temperature1)
        TextView itemTvTemperature1;
        @BindView(R.id.item_tv_temperature2)
        TextView itemTvTemperature2;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);

        }


    }

}
