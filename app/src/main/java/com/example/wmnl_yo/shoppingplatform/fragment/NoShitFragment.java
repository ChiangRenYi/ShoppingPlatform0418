package com.example.wmnl_yo.shoppingplatform.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoShitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoShitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoShitFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_no_shit, container, false);;
        ((MainActivity) getActivity()).setSubTitle(" > 開發中");

        root.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        return root;
    }

}
