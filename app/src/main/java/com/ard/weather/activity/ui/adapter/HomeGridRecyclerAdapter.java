package com.ard.weather.activity.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ard.weather.activity.R;
import com.ard.weather.activity.entity.WeatherBean;
import com.ard.weather.activity.ui.view.OnItemClickListenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangqie on 2019/3/5
 * Describe:
 */
public class HomeGridRecyclerAdapter extends RecyclerView.Adapter<HomeGridRecyclerAdapter.ViewHolder> {


    private Context context;
    private List<WeatherBean.ValueBean.IndexesBean> indexesBeanList;

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    private OnItemClickListenter onItemClickListenter;



    public HomeGridRecyclerAdapter(Context context, List<WeatherBean.ValueBean.IndexesBean> indexesBeanList) {
        this.context = context;
        this.indexesBeanList = indexesBeanList;
    }

    @Override
    public int getItemCount() {
        return indexesBeanList != null && indexesBeanList.size() > 0 ? indexesBeanList.size() : 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherBean.ValueBean.IndexesBean indexesBean = indexesBeanList.get(position);
        holder.itemIvGridCt.setText(indexesBean.getName());
        holder.itemIvGridCtStatus.setText(indexesBean.getLevel());
        if (indexesBean.getAbbreviation().equals("xc")) {
            holder.itemIvGridLnt.setImageResource(R.drawable.xc);
        }else if(indexesBean.getAbbreviation().equals("ct")){
            holder.itemIvGridLnt.setImageResource(R.drawable.ct);
        }else if(indexesBean.getAbbreviation().equals("gm")){
            holder.itemIvGridLnt.setImageResource(R.drawable.gm);
        }else if(indexesBean.getAbbreviation().equals("pp")){
            holder.itemIvGridLnt.setImageResource(R.drawable.pp);
        }else if(indexesBean.getAbbreviation().equals("yd")){
            holder.itemIvGridLnt.setImageResource(R.drawable.yd);
        }else if(indexesBean.getAbbreviation().equals("uv")){
            holder.itemIvGridLnt.setImageResource(R.drawable.uv);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_iv_grid_lnt)
        ImageView itemIvGridLnt;
        @BindView(R.id.item_iv_grid_ct)
        TextView itemIvGridCt;
        @BindView(R.id.item_iv_grid_ct_status)
        TextView itemIvGridCtStatus;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
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
