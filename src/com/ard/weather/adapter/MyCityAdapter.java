package com.ard.weather.adapter;

import com.ard.weather.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyCityAdapter extends BaseAdapter {
	LayoutInflater inflater;
	String[] strCity;
	public MyCityAdapter(String[] city,Context context) {
		this.strCity=city;
		this.inflater=LayoutInflater.from(context);
	}
	public int getCount() {
		// TODO Auto-generated method stub
		return strCity.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strCity[position];
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null)
		{
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.weatherselect_cityitem, null);
			holder.mTextView=(TextView) convertView.findViewById(R.id.weatherselect_cityitemName);
			convertView.setTag(holder);
		}
		else
		{
			holder=(ViewHolder) convertView.getTag();
		}
		holder.mTextView.setText(strCity[position]);
		return convertView;
	}
	
	class ViewHolder{
		TextView mTextView;
	}
}
