package com.ard.weather.gps.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;




import android.content.Context;
import android.content.DialogInterface;

import com.ard.menu.inter.URLEncoderShow;
import com.ard.weather.activity.MainActivity;
import com.ard.weather.show.api.Constants;
import com.ard.weather.show.api.ShowApiRequest;
import com.ard.weather.util.HttpConnectionDB;
import com.ard.weather.view.CustomDialog;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class ShowURL implements URLEncoderShow {

	 final static SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMddHHmmss");
	 final static Date curDate = new Date(System.currentTimeMillis());//获取当前时间
	 public static final void ShowDate(AsyncHttpResponseHandler resHandler,String address,String city)
	 {
		  new ShowApiRequest( ApplictionURl.NEW, "14252", "db3766f7ac564f41b974302269c25e56")
		  .setResponseHandler(resHandler)
		  .addTextPara("showapi_timestamp", formatter.format(curDate))
		  .addTextPara("address",address)
		  .addTextPara("city", city)
		  .post();
	 }
	
	
	public String strCity;

	public  String showEncoder(String cityName) {
		try {
			strCity = URLEncoder.encode(cityName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strCity;
	}

	public String showGsonWearther() {

		return null;
	}

	public static String showTime(String strTime, int strDay) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return (sdf.format(date).toString()+"  周"+showDay(strDay));
	}

	public static String showDay(int day) {
		switch (day) {
		case 1:
			return "一";
		case 2:
			return "二";
		case 3:
			return "三";
		case 4:
			return "四";
		case 5:
			return "五";
		case 6:
			return "六";
		case 7:
			return "日";
		default:
			break;
		}
		return "";
	}


}
