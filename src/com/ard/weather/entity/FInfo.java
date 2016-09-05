package com.ard.weather.entity;

import java.util.List;

public class FInfo {
	private String air_press;
	List<SignalTypeInfo> alarmList;
	private String day;//当前时间
	private String day_air_temperature;//温度
	private String day_weather;//天气描述晴
	private String day_weather_pic;//天气图片
	private String day_wind_direction;//风向
	private String day_wind_power;//风的级数
	private IndexInfo index;
	private String jiangshui;
	private String night_air_temperature;
	private String night_weather;//"多云",
	private String night_weather_pic;//
	private String night_wind_direction;//"北风",
	private String night_wind_power;//"3-4级10~17m/h",
	private String sun_begin_end;
	private int weekday;//
	private String ziwaixian;
	public String getAir_press() {
		return air_press;
	}
	public void setAir_press(String air_press) {
		this.air_press = air_press;
	}
	public List<SignalTypeInfo> getAlarmList() {
		return alarmList;
	}
	public void setAlarmList(List<SignalTypeInfo> alarmList) {
		this.alarmList = alarmList;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDay_air_temperature() {
		return day_air_temperature;
	}
	public void setDay_air_temperature(String day_air_temperature) {
		this.day_air_temperature = day_air_temperature;
	}
	public String getDay_weather() {
		return day_weather;
	}
	public void setDay_weather(String day_weather) {
		this.day_weather = day_weather;
	}
	public String getDay_weather_pic() {
		return day_weather_pic;
	}
	public void setDay_weather_pic(String day_weather_pic) {
		this.day_weather_pic = day_weather_pic;
	}
	public String getDay_wind_direction() {
		return day_wind_direction;
	}
	public void setDay_wind_direction(String day_wind_direction) {
		this.day_wind_direction = day_wind_direction;
	}
	public String getDay_wind_power() {
		return day_wind_power;
	}
	public void setDay_wind_power(String day_wind_power) {
		this.day_wind_power = day_wind_power;
	}
	public IndexInfo getIndex() {
		return index;
	}
	public void setIndex(IndexInfo index) {
		this.index = index;
	}
	public String getJiangshui() {
		return jiangshui;
	}
	public void setJiangshui(String jiangshui) {
		this.jiangshui = jiangshui;
	}
	public String getNight_air_temperature() {
		return night_air_temperature;
	}
	public void setNight_air_temperature(String night_air_temperature) {
		this.night_air_temperature = night_air_temperature;
	}
	public String getNight_weather() {
		return night_weather;
	}
	public void setNight_weather(String night_weather) {
		this.night_weather = night_weather;
	}
	public String getNight_weather_pic() {
		return night_weather_pic;
	}
	public void setNight_weather_pic(String night_weather_pic) {
		this.night_weather_pic = night_weather_pic;
	}
	public String getNight_wind_direction() {
		return night_wind_direction;
	}
	public void setNight_wind_direction(String night_wind_direction) {
		this.night_wind_direction = night_wind_direction;
	}
	public String getNight_wind_power() {
		return night_wind_power;
	}
	public void setNight_wind_power(String night_wind_power) {
		this.night_wind_power = night_wind_power;
	}
	public String getSun_begin_end() {
		return sun_begin_end;
	}
	public void setSun_begin_end(String sun_begin_end) {
		this.sun_begin_end = sun_begin_end;
	}
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	public String getZiwaixian() {
		return ziwaixian;
	}
	public void setZiwaixian(String ziwaixian) {
		this.ziwaixian = ziwaixian;
	}
	
	
	
}
