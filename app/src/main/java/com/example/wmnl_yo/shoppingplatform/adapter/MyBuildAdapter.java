package com.example.wmnl_yo.shoppingplatform.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.object.BuildingObject;

import java.util.List;

/**
 * Created by Yang on 2017/5/8.
 */

public class MyBuildAdapter extends RecyclerView.Adapter<MyBuildAdapter.ViewHolder> {
    private final List<BuildingObject.BuildingObjectItem> mValues;


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvHome.setText(mValues.get(position).home);
        holder.tvPhone.setText(mValues.get(position).phone);
        holder.tvTime.setText(mValues.get(position).time);
        holder.tvAddress.setText(mValues.get(position).address);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHome, tvPhone, tvTime, tvAddress;
        public  View mView;
        public BuildingObject.BuildingObjectItem mItem;

        public ViewHolder(View v) {
            super(v);
            mView = v;
            tvHome = (TextView) v.findViewById(R.id.home);
            tvPhone = (TextView) v.findViewById(R.id.phone);
            tvTime = (TextView) v.findViewById(R.id.time);
            tvAddress = (TextView) v.findViewById(R.id.address);
        }
    }

    public MyBuildAdapter(List<BuildingObject.BuildingObjectItem> items) {
        mValues = items;
    }

    @Override
    public MyBuildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_listview_building, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
