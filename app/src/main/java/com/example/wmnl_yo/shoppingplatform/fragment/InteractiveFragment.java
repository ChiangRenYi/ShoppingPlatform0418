package com.example.wmnl_yo.shoppingplatform.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;

/**
 * Created by WMNL-YO on 2017/3/14.
 */

public class InteractiveFragment extends Fragment implements View.OnTouchListener {

    private View view;
    private Button btnUploadPhoto ,btnUploadVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_interactive, container, false);
        }
        ((MainActivity) getActivity()).setSubTitle(" > 互動日誌");
        view.setOnTouchListener(this);

        btnUploadPhoto = (Button)view.findViewById(R.id.Button_update_photo);
        btnUploadVideo = (Button)view.findViewById(R.id.Button_update_video);

        btnUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = null;
//                Class fragmentClass = null;
//                fragmentClass = UploadPhotoListFragment.class;
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                fragment.setTargetFragment(getTargetFragment(), 0);
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.flContent, fragment)
//                        .addToBackStack(null)
//                        .commit();
                ((MainActivity)getContext()).replaceFragment(UploadPhotoListFragment.class, null);
            }
        });

        btnUploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getContext()).replaceFragment(UploadVideoFragment.class, null);
            }
        });

        return view;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
