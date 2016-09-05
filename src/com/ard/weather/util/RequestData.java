package com.ard.weather.util;

import com.ard.weather.entity.WeatherInfo;

public interface RequestData {
	
	  void success(WeatherInfo.ResbodyInfo data);
	   
	  void failed(String failed);

}
