package com.ard.weather.activity.entity;

/**
 * Created by Administrator on 2017/3/1.
 */

public class WeatherHome {


    private String title;

    private String content;

    public WeatherHome(String title,String content)
    {
        this.title=title;
        this.content=content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}
