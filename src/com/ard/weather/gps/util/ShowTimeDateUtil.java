package com.ard.weather.gps.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ard.menu.inter.EntDateUtil;
import com.ard.weather.activity.R;

import android.content.Intent;
import android.nfc.tech.IsoDep;
import android.util.Log;
import android.widget.Toast;

public class ShowTimeDateUtil implements EntDateUtil {

	public String showDateTime(String strTime) {
		String time = strTime.substring((strTime.length() - 4), strTime.length());
		if ((time.substring(0, 1).equals("0"))) {
			return (time.subSequence(1, 2) + "/" + time.substring(2, 4));
		} else {
			return (time.subSequence(0, 2) + "/" + time.substring(2, 4));
		}

	}
	public static String showCityWindPower(String strPower)
	{
		if(isPower(strPower.substring(0, 1)))
		{
			return "微风";
		}
		return strPower;
		
	}

	static boolean isPower(String spower) {
		if (spower.equals("0") || spower.equals("1") || spower.equals("2") || spower.equals("3") || spower.equals("4")
				|| spower.equals("5") || spower.equals("6") || spower.equals("7") || spower.equals("8") || spower.equals("9") || spower.equals("-")) {
			return true;
		}
		return false;
	}
	
	public static int showCityWeatherBackGroune(String power)
	{
		 Date date=new Date();
		 SimpleDateFormat sdf=new SimpleDateFormat("HH");
		int time=Integer.valueOf(sdf.format(date));
		if(power.indexOf("晴")>-1)
		{
			return R.drawable.static_01;
		}
		else if(power.indexOf("雨")>-1)
		{
			if(time>6 && time<=17)
			{
			  return R.drawable.staticweather_xiayus;
			}
			else
			{
			  return R.drawable.staticback_xiayus;
			}
		}
		else if(power.indexOf("云")>-1)
		{
			if((time>0 && time<=6) || (time>=18  && time<24))
			{
				return R.drawable.staticbackgduoyun;
			}
			else
			{
			  return R.drawable.staticback_duoyun2;
			}
		}
		else if(power.indexOf("雪")>-1)
		{
			return R.drawable.staticback_xiaxue;
		}
		else if(power.indexOf("阴")>-1)
		{
			return R.drawable.staticbackying;
		}
		else
		{
			return R.drawable.static_01;
		}
	}
	
	public static final int[] showLunarDateTime(String date) {
		return (new LunarsCalendar().solarToLunar(Integer.valueOf(date.substring(0, 4)), Integer.valueOf(date.substring(4, 6)), Integer.valueOf(date.substring(6, 8))));
	}
	
}
