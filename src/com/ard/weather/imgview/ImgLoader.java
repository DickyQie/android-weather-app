package com.ard.weather.imgview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.ard.weather.activity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImgLoader {

	public static void set_ImgLoader(String uri,ImageView imageView){
		
		ImageLoadingListener imageLoadingListener=new  Animate_FirstDisplayListener();
	DisplayImageOptions options=new DisplayImageOptions.Builder().showStubImage(R.drawable.w2).showImageOnFail(R.drawable.w2).showImageOnFail(R.drawable.w2)
			.cacheOnDisc(true)
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.resetViewBeforeLoading(true)
				.displayer(new FadeInBitmapDisplayer(100)).build();
	
	ImageLoader.getInstance().displayImage(uri, imageView, options,
			imageLoadingListener);

	}

	private static class Animate_FirstDisplayListener extends
			SimpleImageLoadingListener

	{
		static final List<String> show_Imaglist = Collections
				.synchronizedList(new LinkedList<String>());
		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean first_show = !show_Imaglist.contains(imageUri);
				if (first_show) {
FadeInBitmapDisplayer.animate(imageView, 100);
show_Imaglist.add(imageUri);
				}
			}

		}
	}
}
