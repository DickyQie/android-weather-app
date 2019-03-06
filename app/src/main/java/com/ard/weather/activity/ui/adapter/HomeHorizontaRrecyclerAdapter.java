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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangqie on 2019/3/4
 * Describe:
 */
public class HomeHorizontaRrecyclerAdapter extends RecyclerView.Adapter<HomeHorizontaRrecyclerAdapter.ViewHolder> {


    private Context context;
    private WeatherBean.ValueBean.WeatherDetailsInfoBean weatherDetailsInfoBean;

    public HomeHorizontaRrecyclerAdapter(Context context,WeatherBean.ValueBean.WeatherDetailsInfoBean weatherDetailsInfoBean) {
        this.context = context;
        this.weatherDetailsInfoBean = weatherDetailsInfoBean;

    }

    @Override
    public int getItemCount() {
        return weatherDetailsInfoBean != null &&  weatherDetailsInfoBean.getWeather3HoursDetailsInfos().size() > 0 ?
                weatherDetailsInfoBean.getWeather3HoursDetailsInfos().size() : 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherBean.ValueBean.WeatherDetailsInfoBean.Weather3HoursDetailsInfosBean weather3HoursDetailsInfosBean = weatherDetailsInfoBean.getWeather3HoursDetailsInfos().get(position);
        holder.itemTextTime.setText(Utils.dateTimeStamp(weather3HoursDetailsInfosBean.getStartTime(),"yyyy-MM-dd HH:mm:ss","HH:mm"));
        holder.itemTextType.setText(weather3HoursDetailsInfosBean.getWeather());
        holder.itemTextTemperature.setText(weather3HoursDetailsInfosBean.getHighestTemperature()+"°");
        holder.itemIvLogo.setImageResource(Utils.showWeatherStatusStyleGray(weather3HoursDetailsInfosBean.getWeather()));
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text_time)
        TextView itemTextTime;
        @BindView(R.id.item_iv_logo)
        ImageView itemIvLogo;
        @BindView(R.id.item_text_type)
        TextView itemTextType;
        @BindView(R.id.item_text_temperature)
        TextView itemTextTemperature;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
