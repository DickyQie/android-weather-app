package com.ard.weather.view;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.ard.weather.activity.MainActivity;
import com.ard.weather.activity.R;
import com.ard.weather.entity.WeatherInfo;
import com.ard.weather.gps.util.ApplictionURl;
import com.ard.weather.util.MyAsyncTask;
import com.ard.weather.util.RequestData;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.Time;
import android.widget.RemoteViews;

public class AppWidgetMenu extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		new MainActivity().showDate(context, appWidgetManager);
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
	}
	
}
