package com.ard.weather.util;

import java.util.List;
import java.util.Map;

import org.apache.http.protocol.RequestDate;

import android.os.AsyncTask;
import android.util.Log;

import com.ard.weather.activity.MainAddCityActivity;
import com.ard.weather.entity.WeatherInfo;
import com.ard.weather.sqlite.MyHelperDB;
import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;

public class MyAsyncTask extends AsyncTask<String, Integer, String> {
	RequestData requestData;
	public MyAsyncTask(RequestData requestData) {
		super();
		this.requestData = requestData;
	}
	public MyAsyncTask(RequestDate requestDate) {
		
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
		WeatherInfo w = gson.fromJson(result, WeatherInfo.class);
		if(w!=null && w.getShowapi_res_body().getRet_code()==0)
		{
			requestData.success(w.getShowapi_res_body());
		}
		else{
			requestData.failed(UtilInterface.EXENERWORKAVAILABLE);
		}
		
	}





	
	
}
