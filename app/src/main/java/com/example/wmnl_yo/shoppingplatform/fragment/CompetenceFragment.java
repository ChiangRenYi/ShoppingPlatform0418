package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.adapter.ListDropDownAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WMNL-Jimmy on 2018/5/5.
 */

public class CompetenceFragment extends Fragment implements View.OnTouchListener{
    private View view;
    private CheckBox cb1,cb2,cb3,cb4,cb5;
    private String com1,com2,com3,com4,com5;
    private Button submit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_competence, container, false);
        }
        cb1 = (CheckBox)view.findViewById(R.id.checkBox1);
        cb2 = (CheckBox)view.findViewById(R.id.checkBox2);
        cb3 = (CheckBox)view.findViewById(R.id.checkBox3);
        cb4 = (CheckBox)view.findViewById(R.id.checkBox4);
        cb5 = (CheckBox)view.findViewById(R.id.checkBox5);
        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cb1.isChecked()){
                    com1 = "1";
                }else{
                    com1 = "0";
                }

            }
        });
        submit = (Button)view.findViewById(R.id.button);


        if(cb2.isChecked()){
            com2 = "1";
        }else{
            com2 = "0";
        }

        if(cb3.isChecked()){
            com3 = "1";
        }else{
            com3 = "0";
        }

        if(cb4.isChecked()){
            com4 = "1";
        }else{
            com4 = "0";
        }

        if(cb5.isChecked()){
            com5 = "1";
        }else{
            com5 = "0";
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),com1+com2+com3+com4+com5,Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnTouchListener(this);
        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}