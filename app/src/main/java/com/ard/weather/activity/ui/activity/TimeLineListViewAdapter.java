package com.ard.weather.activity.ui.activity;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.entity.WeatherHome;
import com.ard.weather.activity.widget.CircleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */

public class TimeLineListViewAdapter extends BaseAdapter {

    private static final int ALPHA = 100;
    private int[] img = new int[]{R.mipmap.yifu, R.mipmap.yundong, R.mipmap.saiyi, R.mipmap.ganmao, R.mipmap.zhongshu, R.mipmap.diaoyu};
    private List<WeatherHome> listDate;

    private Context context;
    private LayoutInflater inflate;

    public TimeLineListViewAdapter(Context context, List<WeatherHome> data) {
        this.listDate = data;
        this.context = context;
        this.inflate=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return listDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView =inflate.inflate(R.layout.item_timeline, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int color = context.getResources().getColor(R.color.colorPrimary);
        holder.civ.setFillColor(color);
        holder.civ.setBorderColor(ColorUtils.setAlphaComponent(color, ALPHA));
        holder.img.setBackgroundResource(img[position]);
        holder.item_timeline_view.setBackgroundResource(R.color.colorAccent);
       /* if(position==5)
        {
            holder.item_timeline_view.setVisibility(View.GONE);
        }*/
        holder.tv_title.setText(listDate.get(position).getTitle());
        holder.tv_content.setText(listDate.get(position).getContent());
        return convertView;
    }

    class ViewHolder {
        CircleImageView civ;
        ImageView img;
        View item_timeline_view;
        public TextView tv_title;
        public TextView tv_content;
        public ViewHolder(View v) {
            civ= (CircleImageView) v.findViewById(R.id.item_timeline_icon_bg);
            img= (ImageView) v.findViewById(R.id.item_timeline_icon);
            item_timeline_view=v.findViewById(R.id.item_timeline_view);
            tv_title=(TextView)v.findViewById(R.id.item_title);
            tv_content=(TextView)v.findViewById(R.id.item_content);
        }
    }







}
