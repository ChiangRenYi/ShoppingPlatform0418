package com.example.wmnl_yo.shoppingplatform.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.wmnl_yo.shoppingplatform.R;

/**
 * Created by WMNL-YO on 2017/4/20.
 */

public class InteractivePhotosInfoFragment extends Fragment{
    private View view;
    private EditText edInteractivePhotosInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_interactive_photos_info, container, false);
        }
        edInteractivePhotosInfo = (EditText)view.findViewById(R.id.editText_interactive_photos_info);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return view;

    }
}
