package com.ard.weather.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.ard.weather.entity.WeatherInfo;
import com.ard.weather.gps.util.ApplictionURl;
import com.ard.weather.gps.util.ShowTimeDateUtil;
import com.ard.weather.gps.util.ShowURL;
import com.ard.weather.imgview.ImgLoader;
import com.ard.weather.sqlite.AppWidgetHelper;
import com.ard.weather.sqlite.MyHelperDB;
import com.ard.weather.util.HttpConnectionDB;
import com.ard.weather.util.MyAsyncTask;
import com.ard.weather.util.RequestData;
import com.ard.weather.util.UtilInterface;
import com.ard.weather.view.AppWidgetMenu;
import com.ard.weather.view.CustomDialog;
import com.ard.weather.view.CustomProgressDialog;
import com.ard.weather.view.MyChartItem;
import com.ard.weather.view.MyChartView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;


public class MainActivity extends Activity implements OnClickListener {
	LinearLayout cityWeatherBack;
	TextView[] textCity,textCityReminder,textMultiWeather,weatherDay;
	ViewPager viewpager;
	ImageView[] img,weatherImg;
	LinearLayout[] linear;
	MyChartView tu,tu1;
	ArrayList<MyChartItem> list,list1;
	MyHelperDB helper;
	AppWidgetHelper helperWidget;
	LocationClient mLocationClient = null;
	BDLocationListener myListener;
	CustomProgressDialog dialog;
	WeatherInfo.ResbodyInfo textDate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_weather_forecast_main);
		helper = new MyHelperDB(MainActivity.this);
		helperWidget=new AppWidgetHelper(MainActivity.this);
		mLocationClient = new LocationClient(getApplicationContext());
		dialog=new CustomProgressDialog(this, "加载中...", R.anim.mn);
		dialog.show();
		myListener = new MyLocationListener();
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		initLocation();
		try {
			showText();
			showApi();
		} catch (Exception e) {
			showisNetworkAvailableDialog(MainActivity.this,UtilInterface.EXENERWORKAVAILABLE,0);
		}
		
	}

	public void showCityPoint(final double lng, final double lat) {
		String httpArg = "lng=" + lng + "&lat=" + lat
				+ "&from=5&needMoreDay=1&needIndex=1&needAlarm=1&need3HourForcast=0";
		deResult(ApplictionURl.httpUrlPoint, httpArg, new RequestData() {
			public void success(WeatherInfo.ResbodyInfo data) {
				showAddDate(lng,lat);
				showAddDate(data.getCityInfo().getC3(),data.getF1().getDay_air_temperature(),data.getF1().getDay_weather());
				showStrWeatText(data);
				showLinearBackGround(Integer.valueOf(data.getF1().getWeekday()));
				dialog.cancel();
				textDate=data;
			}
			public void failed(String failed) {
				showExepDialog(MainActivity.this, failed);
			}
		});
	}

	protected synchronized void deResult(String url, String httpArg, RequestData requestData) {
		if (url == null) {
			throw new NullPointerException(UtilInterface.EXENERWORKAVAILABLE);
		}
		MyAsyncTask mt = new MyAsyncTask(requestData);
		mt.execute(url, httpArg);
	}

	public void showApi() {
		if (HttpConnectionDB.isNetworkAvailable(MainActivity.this)) {
			if (!(helper.queryDate().size() > 0)) {
				  showGps();
			} else
			{
				List<Map<String, Object>> listMap=helper.queryDate();
				if(listMap.size()>0)
				{
					showCityPoint(Double.valueOf(listMap.get(0).get("cityLng").toString()),Double.valueOf(listMap.get(0).get("cityLat").toString()));
				}
				else
				{
					for (int i = 0; i <listMap.size(); i++) {
						 helper.deleteDate((Integer)listMap.get(i).get("id"));
					}
					showGps();
				}
			}
		} else {
			showisNetworkAvailableDialog(MainActivity.this,UtilInterface.NetworkAvailableException,1);
		}
		
	}
	
	private void showGps() {
		try {
			mLocationClient.start();
		} catch (Exception e) {
			showExepDialog(UtilInterface.LocationException);
		}
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.weather_forecast_addcity:
			addCity();
			break;
		case R.id.weather_forecast_cityDWs:
			showGps();
			break;
		case R.id.weather_forecast_updates:
			showApi();
			break;
		case R.id.weather_forecast_cityFxs:
			if(null!=textDate)
			{
				if(textDate.getF1().getAlarmList().size()>0)
				{
					send(textDate.getCityInfo().getC3()+" 天气  "+textDate.getF1().getDay_air_temperature().toString() + "° "+
				             textDate.getF1().getDay_weather()+
							"\n"+textDate.getF1().getAlarmList().get(0).getIssueContent()
							+"\n"+ShowURL.showTime(textDate.getF1().getDay(), textDate.getF1().getWeekday())
							);
				}
				else
				{
					send(textDate.getCityInfo().getC3()+" 天气  "+textDate.getF1().getDay_air_temperature().toString() + "° "+
				             textDate.getF1().getDay_weather()+
							"\n"+ShowURL.showTime(textDate.getF1().getDay(), textDate.getF1().getWeekday())
							);
				}
			}
			break;
		default:
			break;
		}
	}

	private void addCity() {
		Intent intent = new Intent();
		intent.setClass(this, MainAddCityActivity.class);
		startActivityForResult(intent,UtilInterface.REQUEST_CODE);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==UtilInterface.REQUEST_CODE)
		{
			if(resultCode==UtilInterface.RESULTCODE)
			{
				if(null!=data)
				{
					double lng=data.getDoubleExtra("lng", 117.10);
					double lat=data.getDoubleExtra("lat", 40.13);
					showCityPoint(lng,lat);
				}
			}
		}
	}
	
	private void showAddDate(double lng, double lat) {
		List<Map<String, Object>> listMap=helper.queryDate();
		for (int i = 0; i <listMap.size(); i++) {
			 helper.deleteDate((Integer)listMap.get(i).get("id"));
		}
		helper.insertDate(String.valueOf(lng),String.valueOf(lat));
	}

	private void showAddDate(String lng, String lat,String name) {
		List<Map<String, Object>> listMap=helperWidget.queryDate();
		for (int i = 0; i <listMap.size(); i++) {
			 helperWidget.deleteDate((Integer)listMap.get(i).get("id"));
		}
		helperWidget.insertDate(lng,lat,name);
	}
	private void showStrWeatText(WeatherInfo.ResbodyInfo date) {
		try {
			cityWeatherBack.setBackgroundResource(ShowTimeDateUtil.showCityWeatherBackGroune(date.getF1().getDay_weather().toString()));
			textCity[0].setText(date.getCityInfo().getC3().toString());
			textCity[1].setText(date.getF1().getDay_air_temperature().toString() + "°");
			textCity[2].setText(date.getF1().getDay_weather().toString());
			textCity[3].setText("发布时间:" + (date.getF1().getSun_begin_end().replace("|", "~")));
			try {
				if(date.getF1().getAlarmList().size()>0)
				{
					textCity[4].setText("预警:" + date.getF1().getAlarmList().get(0).getSignalLevel()+"~"+date.getF1().getAlarmList().get(0).getSignalType());
				}
				else
				{
					textCity[4].setText(UtilInterface.WEATHERYJ);
				}
			} catch (Exception e) {
				textCity[4].setText(UtilInterface.WEATHERYJ);
			}
			textCity[5].setText("风度:" + ShowTimeDateUtil.showCityWindPower((date.getF1().getDay_wind_power().toString().substring(0, 2))));

			textCity[6]
					.setText("温度: " + date.getF1().getNight_air_temperature() + "℃~ " + date.getF1().getDay_air_temperature()+"℃ ");
			textCity[7].setText(ShowURL.showTime(date.getF1().getDay(), date.getF1().getWeekday()));
			int[] dateTime=ShowTimeDateUtil.showLunarDateTime(date.getF1().getDay());
			textCity[8].setText("农历"+dateTime[0]+"年"+dateTime[1]+"月"+dateTime[2]+"日");
			textCityReminder[0].setText(date.getF1().getIndex().getClothes().getDesc());
			textCityReminder[1].setText(date.getF1().getIndex().getTravel().getDesc());
			//1衣服
			try {
				textCityReminder[2].setText(date.getF1().getIndex().getLs().getTitle());
			} catch (Exception e) {
				textCityReminder[2].setText(UtilInterface.WEATHERSTRLS);
			}
			//太阳
			try {
				textCityReminder[3].setText(date.getF1().getIndex().getGlass().getTitle());
			} catch (Exception e) {
				textCityReminder[3].setText(UtilInterface.WEATHERSTRGLSASS);
			}
			textCityReminder[4].setText(date.getF1().getIndex().getUv().getTitle());
			//钓鱼
			try {
				textCityReminder[5].setText(date.getF1().getIndex().getDy().getTitle());
			} catch (Exception e) {
				textCityReminder[5].setText(UtilInterface.WEATHERSTRDY);
			}
			ImgLoader.set_ImgLoader(date.getF1().getDay_weather_pic(), weatherImg[0]);
			weatherDay[0].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF1().getWeekday())));
			weatherDay[1].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF2().getWeekday())));
			weatherDay[2].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF3().getWeekday())));
			weatherDay[3].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF4().getWeekday())));
			weatherDay[4].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF5().getWeekday())));
			weatherDay[5].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF6().getWeekday())));
			weatherDay[6].setText("周"+ShowURL.showDay(Integer.valueOf(date.getF7().getWeekday())));
			textMultiWeather[0].setText(new ShowTimeDateUtil().showDateTime(date.getF1().getDay().toString()));
			textMultiWeather[1].setText(new ShowTimeDateUtil().showDateTime(date.getF2().getDay().toString()));
			textMultiWeather[2].setText(new ShowTimeDateUtil().showDateTime(date.getF3().getDay().toString()));
			textMultiWeather[3].setText(new ShowTimeDateUtil().showDateTime(date.getF4().getDay().toString()));
			textMultiWeather[4].setText(new ShowTimeDateUtil().showDateTime(date.getF5().getDay().toString()));
			textMultiWeather[5].setText(new ShowTimeDateUtil().showDateTime(date.getF6().getDay().toString()));
			textMultiWeather[6].setText(new ShowTimeDateUtil().showDateTime(date.getF7().getDay().toString()));
			ImgLoader.set_ImgLoader(date.getF1().getDay_weather_pic(), weatherImg[1]);
			ImgLoader.set_ImgLoader(date.getF2().getDay_weather_pic(), weatherImg[2]);
			ImgLoader.set_ImgLoader(date.getF3().getDay_weather_pic(), weatherImg[3]);
			ImgLoader.set_ImgLoader(date.getF4().getDay_weather_pic(), weatherImg[4]);
			ImgLoader.set_ImgLoader(date.getF5().getDay_weather_pic(), weatherImg[5]);
			ImgLoader.set_ImgLoader(date.getF6().getDay_weather_pic(), weatherImg[6]);
			ImgLoader.set_ImgLoader(date.getF7().getDay_weather_pic(), weatherImg[7]);
			
			try {
				textMultiWeather[7].setText(date.getF1().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[7].setText(date.getF1().getDay_weather());
			}
			try {
				textMultiWeather[8].setText(date.getF2().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[8].setText(date.getF2().getDay_weather());
			}
			try {
				textMultiWeather[9].setText(date.getF3().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[9].setText(date.getF3().getDay_weather());
			}
			try {
				textMultiWeather[10].setText(date.getF4().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[10].setText(date.getF4().getDay_weather());
			}
			try {
				textMultiWeather[11].setText(date.getF5().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[11].setText(date.getF5().getDay_weather());
			}
			try {
				textMultiWeather[12].setText(date.getF6().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[12].setText(date.getF6().getDay_weather());
			}
			try {
				textMultiWeather[13].setText(date.getF7().getNight_weather().toString());
			} catch (Exception e) {
				textMultiWeather[13].setText(date.getF7().getDay_weather());
			}
			ImgLoader.set_ImgLoader(date.getF1().getNight_weather_pic(), weatherImg[8]);
			ImgLoader.set_ImgLoader(date.getF2().getNight_weather_pic(), weatherImg[9]);
			ImgLoader.set_ImgLoader(date.getF3().getNight_weather_pic(), weatherImg[10]);
			ImgLoader.set_ImgLoader(date.getF4().getNight_weather_pic(), weatherImg[11]);
			ImgLoader.set_ImgLoader(date.getF5().getNight_weather_pic(), weatherImg[12]);
			ImgLoader.set_ImgLoader(date.getF6().getNight_weather_pic(), weatherImg[13]);
			ImgLoader.set_ImgLoader(date.getF7().getNight_weather_pic(), weatherImg[14]);
			loadChart(date);
		} catch (Exception e) {
			showExepDialog(MainActivity.this, UtilInterface.EXENERWORKAVAILABLE);
		}
	}
	private void showText() {
		cityWeatherBack=(LinearLayout) findViewById(R.id.cityWeatherBackground);
		img = new ImageView[] { (ImageView) findViewById(R.id.weather_forecast_updates),
				(ImageView) findViewById(R.id.weather_forecast_addcity),
				(ImageView) findViewById(R.id.weather_forecast_cityDWs),
				(ImageView) findViewById(R.id.weather_forecast_cityFxs)};
		img[0].setOnClickListener(this);
		img[1].setOnClickListener(this);
		img[2].setOnClickListener(this);
		img[3].setOnClickListener(this);
		textCity = new TextView[] { (TextView) findViewById(R.id.weather_forecast_cityName),
				(TextView) findViewById(R.id.weather_forecast_main_citywenduzhus),
				(TextView) findViewById(R.id.weather_forecast_main_cityChinaXq),
				(TextView) findViewById(R.id.viewpager_weatherTimeFaBus),
				(TextView) findViewById(R.id.weather_forecast_main_city),
				(TextView) findViewById(R.id.weather_forecast_main_cityfens),
				(TextView) findViewById(R.id.weather_forecast_main_cityshidus),
				(TextView) findViewById(R.id.weather_times_text1), (TextView) findViewById(R.id.weather_times_text2) };
			
		textCityReminder=new TextView[]{
				(TextView) findViewById(R.id.my_layout_chuangyiZhiShu),
				(TextView) findViewById(R.id.my_layout_CheXingZhiShu),	
				(TextView) findViewById(R.id.my_layout_ShanYiZhiShu),	
				(TextView) findViewById(R.id.my_layout_TaiYangJZhiShu),	
				(TextView) findViewById(R.id.my_layout_FuSheZhiShu),	
				(TextView) findViewById(R.id.my_layout_DiaoYuZhiShu)};
		weatherImg=new ImageView[]{(ImageView)findViewById(R.id.weather_xq_tainqiImg),
				(ImageView)findViewById(R.id.timewu_Img1),
				(ImageView)findViewById(R.id.timewu_Img2),
				(ImageView)findViewById(R.id.timewu_Img3),
				(ImageView)findViewById(R.id.timewu_Img4),
				(ImageView)findViewById(R.id.timewu_Img5),
				(ImageView)findViewById(R.id.timewu_Img6),
				(ImageView)findViewById(R.id.timewu_Img7),
				(ImageView)findViewById(R.id.maintime_imgJS1),
				(ImageView)findViewById(R.id.maintime_imgJS2),
				(ImageView)findViewById(R.id.maintime_imgJS3),
				(ImageView)findViewById(R.id.maintime_imgJS4),
				(ImageView)findViewById(R.id.maintime_imgJS5),
				(ImageView)findViewById(R.id.maintime_imgJS6),
				(ImageView)findViewById(R.id.maintime_imgJS7),
		};
		linear=new LinearLayout[]{(LinearLayout) findViewById(R.id.linear1),(LinearLayout) findViewById(R.id.linear2),(LinearLayout) findViewById(R.id.linear3),(LinearLayout) findViewById(R.id.linear4),(LinearLayout) findViewById(R.id.linear5),(LinearLayout) findViewById(R.id.linear6),(LinearLayout) findViewById(R.id.linear7)};
		textMultiWeather=new TextView[]{(TextView) findViewById(R.id.timewu_rli1),
				(TextView) findViewById(R.id.timewu_rli2),(TextView) findViewById(R.id.timewu_rli3),(TextView) findViewById(R.id.timewu_rli4),
				(TextView) findViewById(R.id.timewu_rli5),(TextView) findViewById(R.id.timewu_rli6),
				(TextView) findViewById(R.id.timewu_rli7),(TextView) findViewById(R.id.maintime_textJS1),
				(TextView) findViewById(R.id.maintime_textJS2),(TextView) findViewById(R.id.maintime_textJS3),
				(TextView) findViewById(R.id.maintime_textJS4),(TextView) findViewById(R.id.maintime_textJS5),
				(TextView) findViewById(R.id.maintime_textJS6),(TextView) findViewById(R.id.maintime_textJS7),
		};
		tu = (MyChartView) findViewById(R.id.chart1);
		tu1 = (MyChartView) findViewById(R.id.chart2);
		weatherDay=new TextView[]{(TextView) findViewById(R.id.time_weatherday1),(TextView) findViewById(R.id.time_weatherday2),
				(TextView) findViewById(R.id.time_weatherday3),(TextView) findViewById(R.id.time_weatherday4),
				(TextView) findViewById(R.id.time_weatherday5),(TextView) findViewById(R.id.time_weatherday6),
				(TextView) findViewById(R.id.time_weatherday7)
		};
	}
	  private void loadChart(WeatherInfo.ResbodyInfo date) {
		    list= new ArrayList<MyChartItem>();
	        list.add(new MyChartItem("",Float.valueOf( date.getF1().getDay_air_temperature())));
	        list.add(new MyChartItem("",Float.valueOf( date.getF2().getDay_air_temperature())));
	        list.add(new MyChartItem("", Float.valueOf( date.getF3().getDay_air_temperature())));
	        list.add(new MyChartItem("",Float.valueOf( date.getF4().getDay_air_temperature())));
	        list.add(new MyChartItem("", Float.valueOf( date.getF5().getDay_air_temperature())));
	        list.add(new MyChartItem("",Float.valueOf( date.getF6().getDay_air_temperature())));
	        list.add(new MyChartItem("", Float.valueOf( date.getF7().getDay_air_temperature())));
	        tu.SetTuView(list, "最高最低温度：");//单位: 摄氏度
	        tu.invalidate();
	        list1= new ArrayList<MyChartItem>();
	        list1.add(new MyChartItem("", Float.valueOf( date.getF1().getNight_air_temperature())));
	        list1.add(new MyChartItem("",Float.valueOf( date.getF2().getNight_air_temperature())));
	        list1.add(new MyChartItem("", Float.valueOf( date.getF3().getNight_air_temperature())));
	        list1.add(new MyChartItem("",Float.valueOf( date.getF4().getNight_air_temperature())));
	        list1.add(new MyChartItem("", Float.valueOf( date.getF5().getNight_air_temperature())));
	        list1.add(new MyChartItem("",Float.valueOf( date.getF6().getNight_air_temperature())));
	        list1.add(new MyChartItem("",Float.valueOf( date.getF7().getNight_air_temperature())));
	        tu1.SetTuView(list1, "");
	        tu1.invalidate();//更新新数据
	       
	    }
	private void showLinearBackGround(int item)
	{
		linear[0].setBackgroundResource(R.color.transparent_background1);
		for (int i = 2; i <=linear.length; i++) {
			linear[i-1].setBackgroundResource(R.color.transparent_background);
		}
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLocationClient.stop();
	}
	

	public class MyLocationListener implements BDLocationListener {

		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());// 单位：公里每小时
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\nheight : ");
				sb.append(location.getAltitude());// 单位：米
				sb.append("\ndirection : ");
				sb.append(location.getDirection());// 单位度
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\ndescribe : ");
				sb.append("gps定位成功");

			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
				sb.append("\ndescribe : ");
				sb.append("网络定位成功");
			} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
				sb.append("\ndescribe : ");
				sb.append("离线定位成功，离线定位结果也是有效的");
			} else if (location.getLocType() == BDLocation.TypeServerError) {
				sb.append("\ndescribe : ");
				sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
			} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
				sb.append("\ndescribe : ");
				sb.append("网络不同导致定位失败，请检查网络是否通畅");
			} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
				sb.append("\ndescribe : ");
				sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
			}
			sb.append("\nlocationdescribe : ");
			sb.append(location.getLocationDescribe());// 位置语义化信息
			List<Poi> list = location.getPoiList();// POI数据
			if (list != null) {
				sb.append("\npoilist size = : ");
				sb.append(list.size());
				for (Poi p : list) {
					sb.append("\npoi= : ");
					sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
				}
			}
			String strLongitude=String.valueOf(location.getLongitude());
			String stratitude=String.valueOf(location.getLatitude());
			if(strLongitude.indexOf("E")>-1 || stratitude.indexOf("E")>-1)
			{
				showExepDialog(UtilInterface.LocationException);
			}
			else
			{
				showCityPoint(location.getLongitude(), location.getLatitude());
			}
			mLocationClient.stop();
			
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mLocationClient.stop();
	}
	public void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		// 多长时间定位(1000*3600)//一个小时定位一次
		int span = (0);
		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(true);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		mLocationClient.setLocOption(option);
	}

	
	public void showExepDialog(Context context,String str)
	{
		final CustomDialog.Builder builder = new CustomDialog.Builder(context);  
        builder.setMessage(str);  
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss(); 
                addCity();
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
	
	public void showisNetworkAvailableDialog(Context context,String str,int i)
	{
		final CustomDialog.Builder builder = new CustomDialog.Builder(context);  
        builder.setMessage(str);  
        builder.setTitle("温馨提示");
        if(i==0)
        {
        	 builder.setNegativeButton("取消",  
                     new android.content.DialogInterface.OnClickListener() {  
                         public void onClick(DialogInterface dialog, int which) {  
                             dialog.dismiss();  
                             finish();
                         }  
                     });  
        }
        else
        {
        	 builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                 public void onClick(DialogInterface dialog, int which) {  
                     dialog.dismiss(); 
                     Intent wifiSettingsIntent = new Intent("android.settings.WIFI_SETTINGS");   
                     startActivity(wifiSettingsIntent); 
                     System.exit(0);
                 }  
             });  
             builder.setNegativeButton("取消",  
                     new android.content.DialogInterface.OnClickListener() {  
                         public void onClick(DialogInterface dialog, int which) {  
                             dialog.dismiss();  
                             finish();
                             System.exit(0);
                         }  
                     });  
        }
       
        
        builder.create().show();  
	}
	public void showExepDialog(String str)
	{
		final CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);  
        builder.setMessage(str);  
        builder.setTitle("温馨提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                dialog.dismiss(); 
                addCity();
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
	
	
	private void send(String text) {
		Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
		intent.putExtra(Intent.EXTRA_TITLE, "天气预报");
		intent.putExtra(Intent.EXTRA_TEXT,text); 
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, "分享"));
		}
	
	public void showDate(Context context, AppWidgetManager appWidgetManager){
		Timer timer = new Timer();
		MyTime my=new MyTime(context, appWidgetManager);
		timer.scheduleAtFixedRate(my, 2000,
				2000);
	}
	 public class MyTime extends TimerTask {
		RemoteViews remoteViews;
		AppWidgetManager appWidgetManager;
		ComponentName thisWidget;
			public MyTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetManager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget_activity);
			thisWidget = new ComponentName(context, AppWidgetMenu.class);
		}
		public void run() {
			if(textDate!=null)
			{
				List<Map<String, Object>> listMap=helperWidget.queryDate();
				if(listMap.size()>0)
				{
					remoteViews.setTextViewText(R.id.widget_timesssss, listMap.get(0).get("cityLat1").toString());
				}
			}
			else
				{remoteViews.setTextViewText(R.id.widget_timesssss, "时间");}
			appWidgetManager.updateAppWidget(thisWidget, remoteViews);
		}
	 }
}
