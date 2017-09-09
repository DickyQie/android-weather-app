package com.ard.weather.activity.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ard.weather.activity.R;

/**
 * Created by Administrator on 2017/2/28.
 */

public class WeatherTime extends LinearLayout {


    private TextView tv_day;
    private TextView tv_time;
    private ImageView imageView;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public WeatherTime(Context context) {
        this(context, null);
        this.context=context;
    }
    public WeatherTime(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setOrientation(VERTICAL);
        mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayoutInflater.inflate(R.layout.public_weather_tiem, this, true);
        tv_day=(TextView) findViewById(R.id.weathertime_day);
        tv_time=(TextView) findViewById(R.id.weathertime_clander);
        imageView=(ImageView)findViewById(R.id.weathertime_img);
    }

    public ImageView showImage()
    {
        return imageView;
    }

    public void setTextDay(String title) {
        tv_day.setText(title);
    }

    public void setTime(String title) {
        tv_time.setText(title);
    }
    public void setImageView(int img)
    {
        imageView.setImageResource(img);
    }
    public void setImageView(Bitmap bitmap)
    {
        imageView.setImageBitmap(bitmap);
    }


}
