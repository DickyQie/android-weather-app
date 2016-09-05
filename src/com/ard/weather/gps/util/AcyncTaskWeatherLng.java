package com.ard.weather.gps.util;

import org.apache.http.protocol.RequestDate;

import android.os.AsyncTask;

import com.ard.weather.entity.WeatherAreaIdInfo;
import com.ard.weather.util.HttpConnectionDB;
import com.ard.weather.util.UtilInterface;
import com.google.gson.Gson;

public class AcyncTaskWeatherLng extends AsyncTask<String, Integer, String> {
	RequestData requestData;
	public AcyncTaskWeatherLng(RequestData requestData) {
		super();
		this.requestData =requestData;
	}
	public AcyncTaskWeatherLng(RequestDate requestDate) {
		
	}
	protected String doInBackground(String... params) {
		String data=null;
		data=HttpConnectionDB.request(params[0],params[1]);
		return data;
	}
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Gson gson=new Gson();
		WeatherAreaIdInfo w = gson.fromJson(result, WeatherAreaIdInfo.class);
		if(w!=null && w.getShowapi_res_body().getRet_code()==0)
		{
			requestData.success(w.getShowapi_res_body());
		}
		else{
			requestData.failed(UtilInterface.EXENERWORKAVAILABLE);
		}
	}


}
