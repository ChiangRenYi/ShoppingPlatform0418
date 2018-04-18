package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;

/**
 * Created by WMNL-Jimmy on 2018/4/13.
 */

public class MessageFragment extends Fragment {
ImageView img;
RelativeLayout rlmessage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_message, container, false);;
        ((MainActivity) getActivity()).setSubTitle(" > 互動訊息");

        rlmessage = (RelativeLayout)root.findViewById(R.id.rlmessage);

        root.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                Resources resources = getResources();
                Drawable btnDrawable = resources.getDrawable(R.drawable.message2);
                rlmessage.setBackgroundDrawable(btnDrawable);
                return true;
            }
        });

        return root;
    }

}
