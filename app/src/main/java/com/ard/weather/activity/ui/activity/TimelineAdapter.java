package com.ard.weather.activity.ui.activity;

import android.content.Context;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.entity.WeatherHome;
import com.ard.weather.activity.widget.CircleImageView;

import java.util.List;


public class TimelineAdapter extends  RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private static final int ALPHA = 100;

    private int[] img=new int[]{R.mipmap.yifu,R.mipmap.yundong,R.mipmap.saiyi,R.mipmap.ganmao,R.mipmap.zhongshu,R.mipmap.diaoyu};

    private  List<WeatherHome> data;

    private Context context;
    public TimelineAdapter(Context context, List<WeatherHome> data) {
        this.data=data;
        this.context=context;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timeline, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int color = context.getResources().getColor(R.color.colorPrimary);
        holder.civ.setFillColor(color);
        holder.civ.setBorderColor(ColorUtils.setAlphaComponent(color, ALPHA));
        holder.img.setBackgroundResource(img[position]);
        holder.item_timeline_view.setBackgroundResource(R.color.colorAccent);
        if(position==5)
        {
            holder.item_timeline_view.setVisibility(View.GONE);
        }
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_content.setText(data.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return 6;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civ;
        ImageView img;
        View item_timeline_view;
        public TextView tv_title;
        public TextView tv_content;
        public ViewHolder(View v) {
            super(v);
            civ= (CircleImageView) v.findViewById(R.id.item_timeline_icon_bg);
            img= (ImageView) v.findViewById(R.id.item_timeline_icon);
            item_timeline_view=v.findViewById(R.id.item_timeline_view);
            tv_title=(TextView)v.findViewById(R.id.item_title);
            tv_content=(TextView)v.findViewById(R.id.item_content);
        }
    }

}
