package com.ard.weather.gps.util;

import com.ard.weather.entity.WeatherAreaIdInfo;

public interface RequestData {
	
	  void success(WeatherAreaIdInfo.RetherInfo data);
	   
	  void failed(String failed);

}
