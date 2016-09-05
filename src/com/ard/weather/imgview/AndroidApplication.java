package com.ard.weather.imgview;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class AndroidApplication extends Application {
	private ImageCache mImageCache;
	private ExecutorService mExecutorService;
	private final int CORE_POOL_SIZE = 5;
	private ArrayList<WeakReference<OnLowMemoryListener>> mLowMemoryListeners;

	@Override
	public void onCreate() {
		super.onCreate();
		mLowMemoryListeners = new ArrayList<WeakReference<OnLowMemoryListener>>();
	    ImageLoaderConfiguration configuration = ImageLoaderConfiguration  
	            .createDefault(this);  
	      
	    //Initialize ImageLoader with configuration.  
	    ImageLoader.getInstance().init(configuration);  
	}

	
	public interface OnLowMemoryListener {
		public void onLowMemoryReceived();
	}

	public ImageCache getImageCache() {
		if (mImageCache == null) {
			mImageCache = new ImageCache(this);
		}
		return mImageCache;
	}

	private final ThreadFactory sThreadFactory = new ThreadFactory() {

		private final AtomicInteger mCount = new AtomicInteger(1);

		public Thread newThread(Runnable r) {
			return new Thread(r, "GreenDroid thread #"
					+ mCount.getAndIncrement());
		}
	};

	public ExecutorService getExecutor() {
		if (mExecutorService == null) {
			mExecutorService = Executors.newFixedThreadPool(CORE_POOL_SIZE,
					sThreadFactory);
		}
		return mExecutorService;
	}

	public void registerOnLowMemoryListener(OnLowMemoryListener listener) {
		if (listener != null) {
			mLowMemoryListeners.add(new WeakReference<OnLowMemoryListener>(
					listener));
		}
	}

	public void unregisterOnLowMemoryListener(OnLowMemoryListener listener) {
		if (listener != null) {
			int i = 0;
			while (i < mLowMemoryListeners.size()) {
				final OnLowMemoryListener l = mLowMemoryListeners.get(i).get();
				if (l == null || l == listener) {
					mLowMemoryListeners.remove(i);
				} else {
					i++;
				}
			}
		}
	}}
