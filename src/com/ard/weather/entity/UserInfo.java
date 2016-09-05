package com.ard.weather.entity;

public class UserInfo {

	private int aqi;
	private AqiDetailInfo aqiDetail;
	private String sd;
	private String temperature;
	private String temperature_time;
	private String weather;
	private String weather_pic;
	private String wind_direction;
	private String wind_power;
	public final class AqiDetailInfo{
		private int aqi;
		private String area;
		private String area_code;
		private double co;
		private int no2;
		private int o3;
		private int o3_8h;
		private int pm10;
		private int pm2_5;
		private String primary_pollutant;
		private String quality;
		private int so2;
		public int getAqi() {
			return aqi;
		}
		public void setAqi(int aqi) {
			this.aqi = aqi;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getArea_code() {
			return area_code;
		}
		public void setArea_code(String area_code) {
			this.area_code = area_code;
		}
		public double getCo() {
			return co;
		}
		public void setCo(double co) {
			this.co = co;
		}
		public int getNo2() {
			return no2;
		}
		public void setNo2(int no2) {
			this.no2 = no2;
		}
		public int getO3() {
			return o3;
		}
		public void setO3(int o3) {
			this.o3 = o3;
		}
		public int getO3_8h() {
			return o3_8h;
		}
		public void setO3_8h(int o3_8h) {
			this.o3_8h = o3_8h;
		}
		public int getPm10() {
			return pm10;
		}
		public void setPm10(int pm10) {
			this.pm10 = pm10;
		}
		public int getPm2_5() {
			return pm2_5;
		}
		public void setPm2_5(int pm2_5) {
			this.pm2_5 = pm2_5;
		}
		public String getPrimary_pollutant() {
			return primary_pollutant;
		}
		public void setPrimary_pollutant(String primary_pollutant) {
			this.primary_pollutant = primary_pollutant;
		}
		public String getQuality() {
			return quality;
		}
		public void setQuality(String quality) {
			this.quality = quality;
		}
		public int getSo2() {
			return so2;
		}
		public void setSo2(int so2) {
			this.so2 = so2;
		}
	}
	public int getAqi() {
		return aqi;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public AqiDetailInfo getAqiDetail() {
		return aqiDetail;
	}
	public void setAqiDetail(AqiDetailInfo aqiDetail) {
		this.aqiDetail = aqiDetail;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getTemperature_time() {
		return temperature_time;
	}
	public void setTemperature_time(String temperature_time) {
		this.temperature_time = temperature_time;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getWeather_pic() {
		return weather_pic;
	}
	public void setWeather_pic(String weather_pic) {
		this.weather_pic = weather_pic;
	}
	public String getWind_direction() {
		return wind_direction;
	}
	public void setWind_direction(String wind_direction) {
		this.wind_direction = wind_direction;
	}
	public String getWind_power() {
		return wind_power;
	}
	public void setWind_power(String wind_power) {
		this.wind_power = wind_power;
	}
	
	
}