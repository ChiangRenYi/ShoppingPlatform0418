package com.example.wmnl_yo.shoppingplatform.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.object.PhotoListObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WMNL-Jimmy on 2017/9/2.
 */

public class UploadPhotoListFragment extends Fragment implements View.OnTouchListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;

    private List<PhotoListObject> photoList = new ArrayList<>();

    public UploadPhotoListFragment() {
        // Required empty public constructor
    }

    public static UploadPhotoListFragment newInstance(String param1, String param2) {
        UploadPhotoListFragment fragment = new UploadPhotoListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
     //   setHasOptionsMenu(true);
        try {
            photoList = (List<PhotoListObject>) getArguments().getSerializable("photoList");
        } catch (Exception e) {
            e.printStackTrace();
            photoList.add(new PhotoListObject( "2017/5/15", "Anthony", "上傳者", "上課活動照片"));
            photoList.add(new PhotoListObject( "2017/4/4", "Jim", "上傳者", "兒童節活動"));
            photoList.add(new PhotoListObject( "2016/12/25", "Simon", "上傳者", "聖誕節活動"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_interactive_photos_list, container, false);
        v.setOnTouchListener(this);

        recyclerView = (RecyclerView) v.findViewById(R.id.lphotolist);

        MyAdapter myAdapter = new MyAdapter(photoList);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public class MyAdapter extends RecyclerView.Adapter<UploadPhotoListFragment.MyAdapter.ViewHolder> {
        private List<PhotoListObject> mPhotoList;

        public class ViewHolder extends RecyclerView.ViewHolder {

            public RelativeLayout ll1;
            public TextView tvpDate, tvpPersonName, tvpPosition, tvpContent;

            public ViewHolder(View v) {
                super(v);
                ll1 = (RelativeLayout) v.findViewById(R.id.rl);
                tvpDate = (TextView) v.findViewById(R.id.list_date);
                tvpPersonName = (TextView) v.findViewById(R.id.list_uploader_name);
                tvpPosition = (TextView) v.findViewById(R.id.list_photo_subtitle);
                tvpContent = (TextView) v.findViewById(R.id.list_photoTitle);
            }
        }

        public MyAdapter(List<PhotoListObject> photoList) {
            mPhotoList = photoList;
        }

        @Override
        public UploadPhotoListFragment.MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cardview_photo_list, parent, false);
           ViewHolder vhp  = new ViewHolder(v);

            return vhp;
        }



        @Override
        public void onBindViewHolder(UploadPhotoListFragment.MyAdapter.ViewHolder holder, final int position) {

            holder.ll1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("courseRecordDetail",mPhotoList.get(position));
//                    CourseRecordDetailFragment fragobj = new CourseRecordDetailFragment();
//                    fragobj.setArguments(bundle);
                    ((MainActivity) getContext()).replaceFragment(PhotoDetailFragment.class, null);




                }
            });

            holder.tvpDate.setText(mPhotoList.get(position).getDate());
            holder.tvpPersonName.setText(mPhotoList.get(position).getPersonName());
            holder.tvpPosition.setText(mPhotoList.get(position).getPosition());
            holder.tvpContent.setText(mPhotoList.get(position).getMessage());
        }

        @Override
        public int getItemCount() {
            return photoList.size();
        }
    }
}
