package com.ard.weather.imgview;

import java.util.concurrent.ExecutorService;

import android.content.Context;

public class GDUtils {

	private GDUtils() {
	}
	public static AndroidApplication getGDApplication(Context context) {
		return (AndroidApplication) context.getApplicationContext();
	}

	public static ImageCache getImageCache(Context context) {
		return getGDApplication(context).getImageCache();
	}

	public static ExecutorService getExecutor(Context context) {
		return getGDApplication(context).getExecutor();
	}

}
