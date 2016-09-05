package com.ard.weather.activity;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ard.weather.entity.UserRequest;
import com.ard.weather.entity.WeatherAreaIdInfo.RetherInfo;
import com.ard.weather.gps.util.AcyncTaskWeatherLng;
import com.ard.weather.gps.util.ApplictionURl;
import com.ard.weather.gps.util.ShowURL;
import com.ard.weather.show.api.Constants;
import com.ard.weather.sqlite.MyHelperDB;
import com.ard.weather.util.UtilInterface;
import com.ard.weather.view.CustomDialog;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainAddCityActivity extends Activity implements OnClickListener{
	
	private EditText textCityName;
	GridView mGridViewS,mGridViewCity;
	ListView mListViewS;
	ExpandableListView expList;
	MyHelperDB helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(getIntent()!=null)
		{
			setContentView(R.layout.activity_weather_addcityselect);
			initView();
		}
	}
	private void initView()
	{
		helper=new MyHelperDB(MainAddCityActivity.this);
		findViewById(R.id.addcityselect_imgClose).setOnClickListener(this);
		findViewById(R.id.addcityselect_stop).setOnClickListener(this);
		expList=(ExpandableListView) findViewById(R.id.expandableListView);
		textCityName=(EditText) findViewById(R.id.weather_addcityselect_textCityNamesSelect);
		initDate();
	}
	private void initDate()
	{
		expList.setGroupIndicator(null);
		final MyAdapter adapter=new MyAdapter(UtilInterface.STRVIEWNAME,UtilInterface.STRCITYNAME);
		expList.setAdapter(adapter);
		try {
			expList.setOnChildClickListener(new OnChildClickListener() {
				
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					     showIntent(adapter.getChild(groupPosition, childPosition).toString(),adapter.getGroup(groupPosition).toString());
					   return false;
				}
			});
		} catch (Exception e) {
			showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
		}
	}
	void showIntent(double x,double y)
	{
		Intent intent=new Intent();
		intent.putExtra("lng", x);
		intent.putExtra("lat", y);
		setResult(UtilInterface.RESULTCODE, intent);
		finish();
	}
	void showIntent(final String strAddress,final String strCity)
	{
		 String httpArg="area="+new ShowURL().showEncoder(strAddress);
		 deResult(ApplictionURl.HttpUrlAreaId,httpArg,new com.ard.weather.gps.util.RequestData() {
			
			public void success(RetherInfo data) {
				try {
					if(data.getList().size()>0)
					{
						showIntent(data.getList().get(0).getCityInfo().getLongitude(),data.getList().get(0).getCityInfo().getLatitude());
					}
					else
					{
						ShowURL.ShowDate(resHandler, strAddress, strCity);
					}
				} catch (Exception e) {
					showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
				}
				
			}
			public void failed(String failed) {
				new MainActivity().showExepDialog(MainAddCityActivity.this, failed+",是否重新查询");
			}
		});
		
	}
	
	protected synchronized void deResult(String url, String httpArg, com.ard.weather.gps.util.RequestData requestData) {
		if (url == null) {
			throw new NullPointerException(UtilInterface.EXENERWORKAVAILABLE);
		}
		AcyncTaskWeatherLng mt = new AcyncTaskWeatherLng(requestData);
		mt.execute(url, httpArg);
	}
	
	public final AsyncHttpResponseHandler resHandler=new AsyncHttpResponseHandler(){
		public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable e) {
			e.printStackTrace();
		}
		public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
			try {
				showIntent(responseBody);
			} catch (Exception e) {
				showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
			}
		}
		};
	
	void showIntent(byte[] responseBody)
	{
		Gson gson=new Gson();
		try {
			System.out.println("Show2"+new String(responseBody,Constants.CHARSET_UTF8));
			UserRequest user=gson.fromJson(new String(responseBody,Constants.CHARSET_UTF8), UserRequest.class);
			if(user!=null)
			{
				try {
					showIntent(user.getShowapi_res_body().getLocation().getLng(), user.getShowapi_res_body().getLocation().getLat());
				} catch (Exception e) {
					showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
				}
			}
		} catch (JsonSyntaxException e) {
			showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
		} catch (UnsupportedEncodingException e) {
			showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
		}
	}
	

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addcityselect_imgClose:
			finish();
			break;
		case R.id.addcityselect_stop:
			if(textCityName.getText().length()>1)
			{
				showIntent(textCityName.getText().toString(), null);
			}
			else
			{
				showisNetworkAvailableDialog(UtilInterface.SELECTCITY);
			}
			break;

		default:
			break;
		}
	}
	class MyAdapter extends BaseExpandableListAdapter{
		
		String[] strGroup;
		String[][] strChild=UtilInterface.STRCITYNAME;
		public MyAdapter(String[] strGroup,String[][] strChild)
		{
			this.strGroup=strGroup;
		}
		public int getGroupCount() {
			return strGroup.length;
		}

		public int getChildrenCount(int groupPosition) {
			return strChild[groupPosition].length;
		}

		public Object getGroup(int groupPosition) {
			return strGroup[groupPosition];
		}

		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return strChild[groupPosition][childPosition];
		}

		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			ViewHolders holders;
			if(convertView==null)
			{
				holders=new ViewHolders();
			    convertView=LayoutInflater.from(MainAddCityActivity.this).inflate(R.layout.weather_expandarlistview_cityitem, null);
			    holders.mTextView=(TextView) convertView.findViewById(R.id.expandlistview_txt);
			    holders.mImageView=(ImageView) convertView.findViewById(R.id.expandlistview_img);
			    convertView.setTag(holders);
			}
			else
			{
				holders=(ViewHolders) convertView.getTag();
			}
			holders.mImageView.setImageResource(UtilInterface.strImg[groupPosition]);
			holders.mTextView.setText(getGroup(groupPosition).toString());
            return convertView;
            
		}
		class ViewHolders{TextView mTextView;ImageView mImageView;}

		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			 LinearLayout ll = new LinearLayout(
					 MainAddCityActivity.this);
	            ll.setOrientation(0);
	            TextView textView =new TextView(MainAddCityActivity.this);
	            textView.setPadding(155, 5, 1, 5);
	            textView.setTextSize(22);
	            textView.setTextColor(Color.YELLOW);
	            textView.setText(getChild(groupPosition, childPosition)
	                    .toString());
	           ll.addView(textView);
	            return ll;
		}

		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
	}

	public void showisNetworkAvailableDialog(String str)
	{
		final CustomDialog.Builder builder = new CustomDialog.Builder(MainAddCityActivity.this);  
        builder.setMessage(str);  
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss(); 
                textCityName.setText("");
            }  
        });  
        builder.setNegativeButton("取消",  
                new android.content.DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int which) {  
                        dialog.dismiss();  
                        finish();
                    }  
                });  
        
        builder.create().show();  
	}

}
