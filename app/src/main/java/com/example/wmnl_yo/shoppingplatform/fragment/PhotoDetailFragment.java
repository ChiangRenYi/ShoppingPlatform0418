package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wmnl_yo.shoppingplatform.PhotoItem;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.adapter.MyPhotoAdapter;

/**
 * Created by WMNL-Jimmy on 2017/9/2.
 */

public class PhotoDetailFragment extends Fragment {

//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//    private String mParam1;
//    private String mParam2;
//
//    private PhotoObject photoObject;
//
//    private OnFragmentInteractionListener mListener;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        photoObject = (PhotoObject) getArguments().getSerializable("photoDetail");
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View v = inflater.inflate(R.layout.fragment_interactive_photos_detail, container, false);
//        v.setOnTouchListener(this);
//
//
//        return  v;
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
//
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
    //--------------------------------------NEW HERE---------------------------------------------
    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    public static MyPhotoAdapter adapter;

    private int mColumnCount = 1;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private View view;

    private String[] list = {"老師講故事給小朋友聽", "小朋友在玩推沙", "小朋友們積極參與課上活動", "外國交換學生互相學習", "小朋友上音樂課"};

    private int Pic[] = {R.drawable.ip1, R.drawable.ip2, R.drawable.ip3, R.drawable.ip4, R.drawable.ip5};

    public static PhotoDetailFragment newInstance(int columnCount) {
        PhotoDetailFragment fragment = new PhotoDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_interactive_photos_details, container, false);

        final Context context = view.getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.list1);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        adapter = new MyPhotoAdapter(PhotoItem.ITEM, mListener);
        recyclerView.setAdapter(adapter);

        PhotoItem dims = new PhotoItem();
        dims.addItem(new PhotoItem.PhotoPic(Pic[0], list[0], R.drawable.ic_close_search));
        dims.addItem(new PhotoItem.PhotoPic(Pic[1], list[1], R.drawable.ic_close_search));
        dims.addItem(new PhotoItem.PhotoPic(Pic[2], list[2], R.drawable.ic_close_search));
        dims.addItem(new PhotoItem.PhotoPic(Pic[3], list[3], R.drawable.ic_close_search));
        dims.addItem(new PhotoItem.PhotoPic(Pic[4], list[4], R.drawable.ic_close_search));


        adapter.notifyDataSetChanged();
        return view;

    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(PhotoItem.PhotoPic item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
