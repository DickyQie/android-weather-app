package com.ard.weather.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppWidgetHelper extends SQLiteOpenHelper {

	static String DATABASE_NAME="UserDB";
	static String TABLE_NAME="userInfo";
	static String _ID="_id";
	static String WEARTHER_LNGNAME="cityName";
	static String WEARTHER_LATNAME="cityWeather";
	static String WEARTHER_LATNAMES="cityWeatherPower";
	static int VERSION=1;
	private int identitys=0;
	SQLiteDatabase sqlData;
	public AppWidgetHelper(Context context) {
		super(context, DATABASE_NAME, null,VERSION);
		sqlData=this.getWritableDatabase();
	}

	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table "+TABLE_NAME+"("+_ID+" integer primary key autoincrement,"+WEARTHER_LATNAME+" text,"+WEARTHER_LNGNAME+" text,"+WEARTHER_LATNAMES+" text)";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}
	
	public boolean insertDate(String strLng,String strLat,String strName)
	{
		ContentValues  contentValues=new ContentValues();
		contentValues.put(WEARTHER_LNGNAME, strLng);
		contentValues.put(WEARTHER_LATNAME, strLat);
		contentValues.put(WEARTHER_LATNAMES, strName);
		return sqlData.insert(TABLE_NAME, null, contentValues)>0;
	}
	public boolean deleteDate(int id)
	{
		final String str=_ID+"=?";//条件
		final String[] deleteValues=new String[]{String.valueOf(id)};//条件对于的值
		return sqlData.delete(TABLE_NAME, str, deleteValues)>0;
	}
	public List<Map<String, Object>> queryDate()
	{
		List<Map<String, Object>> str=new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> str1=new ArrayList<Map<String, Object>>();
		Cursor c=sqlData.query(TABLE_NAME, null, null, null, null, null, null);
		if(null!=c)
		{
			while(c.moveToNext())
			{
				Map<String, Object> map=new HashMap<String, Object>();
				String cityNameLng=c.getString(c.getColumnIndex(WEARTHER_LNGNAME));
				String cityNameLat=c.getString(c.getColumnIndex(WEARTHER_LATNAME));
				String cityName=c.getString(c.getColumnIndex(WEARTHER_LATNAMES));
				int cityId=c.getInt(c.getColumnIndex(_ID));
				map.put("id", cityId);
				map.put("cityLat1", cityNameLng);
				map.put("cityLat2", cityNameLat);
				map.put("cityLat3", cityName);
				str.add(map);
			}
		}
		return str;
	}
}
