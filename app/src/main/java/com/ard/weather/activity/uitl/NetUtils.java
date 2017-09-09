package com.ard.weather.activity.uitl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

/***
 * 判断网络是否连接
 * @author zq
 *
 */
public class NetUtils {
	
	/**
	 * 检查当前网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkAvailable(Context activity) {
		ConnectivityManager connectivity = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected())
			{
				// 当前网络是连接的
				if (info.getState() == State.CONNECTED)
				{
					// 当前所连接的网络可用
					return true;
				}
			}
		}
		return false;
	}

}
