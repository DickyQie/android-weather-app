package com.ard.weather.activity.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.entity.CityBean;
import com.ard.weather.activity.ui.view.OnItemClickListenter;

import java.util.List;

/**
 * Created by zhangqie on 2019/3/5
 * Describe:
 */
public class CityListRecyclerAdapter extends RecyclerView.Adapter<CityListRecyclerAdapter.ViewHolder>{


    private Context context;
    private List<CityBean.CitiesBean> citiesBeanList;

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    private OnItemClickListenter onItemClickListenter;

    public CityListRecyclerAdapter(Context context, List<CityBean.CitiesBean> citiesBeanList){
        this.context = context;
        this.citiesBeanList = citiesBeanList;
    }

    @Override
    public int getItemCount() {
        return citiesBeanList != null && citiesBeanList.size() > 0 ? citiesBeanList.size() : 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityBean.CitiesBean citiesBean = citiesBeanList.get(position);
        holder.tvName.setText(citiesBean.getCity());
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;

        public ViewHolder(View view){
            super(view);
            tvName = view.findViewById(R.id.item_tv_city_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListenter){
                        onItemClickListenter.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }

    }

}
