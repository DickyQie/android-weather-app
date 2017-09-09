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

public class WeatherNighView extends LinearLayout {
    private TextView tv_title;
    private ImageView imageView;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public WeatherNighView(Context context) {
        this(context, null);
        this.context=context;
    }
    public WeatherNighView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setOrientation(VERTICAL);
        mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayoutInflater.inflate(R.layout.public_weather_nigh, this, true);
        tv_title=(TextView) findViewById(R.id.weather_nigh_name);
        imageView=(ImageView)findViewById(R.id.weather_nigh_img);
    }
    public ImageView showImage()
    {
        return imageView;
    }

    public void setTextDay(String title) {
        tv_title.setText(title);
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
