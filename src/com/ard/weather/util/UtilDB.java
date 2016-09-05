package com.ard.weather.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ard.weather.activity.MainActivity;
import com.ard.weather.activity.R;
import com.ard.weather.gps.util.ShowURL;
import com.ard.weather.view.CustomDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


public class UtilDB {
	
	
	public static final int[] expandableList_itemimg = new int[] {
		R.drawable.beijing};
	public static List<View> showView(Context context)
	{
		List<View> list=new ArrayList<View>();
		list.add(LayoutInflater.from(context).inflate(R.layout.weatheritme_xq, null));
		return list;
	}
	public static List<View> showView(Context context,int ids)
	{
		List<View> list=new ArrayList<View>();
		list.add(LayoutInflater.from(context).inflate(R.layout.acitvity_weather_citynulls, null));
		return list;
		
	}


}
