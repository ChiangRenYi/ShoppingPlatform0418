package com.example.wmnl_yo.shoppingplatform.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.PhotoItem.PhotoPic;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.fragment.PhotoDetailFragment;

import java.util.List;

public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.ViewHolder> {

    private final List<PhotoPic> mValues;
    private final PhotoDetailFragment.OnListFragmentInteractionListener mListener;

    public MyPhotoAdapter(List<PhotoPic> items, PhotoDetailFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_view_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setImageResource(mValues.get(position).content);
        holder.mtitle.setText((mValues.get(position).title));


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mContentView;
        public final TextView mtitle;
        public PhotoPic mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (ImageView) view.findViewById(R.id.inter_photo);
            mtitle = (TextView) view.findViewById(R.id.inter_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mtitle.getText() + "'";
        }
    }


}