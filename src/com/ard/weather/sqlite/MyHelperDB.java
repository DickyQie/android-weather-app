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

public class MyHelperDB extends SQLiteOpenHelper {

	static String DATABASE_NAME="WEARDB";
	static String TABLE_NAME="WeartherInfo";
	static String _ID="_id";
	static String WEARTHER_LNGNAME="cityLng";
	static String WEARTHER_LATNAME="cityLat";
	static int VERSION=1;
	private int identitys=0;
	SQLiteDatabase sqlData;
	public MyHelperDB(Context context) {
		super(context, DATABASE_NAME, null,VERSION);
		// TODO Auto-generated constructor stub
		sqlData=this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql="create table "+TABLE_NAME+"("+_ID+" integer primary key autoincrement,"+WEARTHER_LATNAME+" text,"+WEARTHER_LNGNAME+" text)";
		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		
	}
	
	public boolean insertDate(String strLng,String strLat)
	{
		ContentValues  contentValues=new ContentValues();
		contentValues.put(WEARTHER_LNGNAME, strLng);
		contentValues.put(WEARTHER_LATNAME, strLat);
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
				int cityId=c.getInt(c.getColumnIndex(_ID));
				map.put("id", cityId);
				map.put("cityLng", cityNameLng);
				map.put("cityLat", cityNameLat);
				str.add(map);
			}
		}
		return str;
	}
}
