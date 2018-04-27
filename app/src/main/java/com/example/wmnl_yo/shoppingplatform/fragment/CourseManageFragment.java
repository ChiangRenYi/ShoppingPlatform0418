package com.example.wmnl_yo.shoppingplatform.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;

/**
 * Created by WMNL-YO on 2017/2/24.
 */

public class CourseManageFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_course_manage, container, false);
        }
        ((MainActivity) getActivity()).setSubTitle(" > 課程管理與查詢");
        Button btnCourseQuery = (Button) view.findViewById(R.id.Button_course_query);
        Button btnCourseRecord = (Button) view.findViewById(R.id.Button_course_record);

        btnCourseQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getContext()).replaceFragment(CourseQueryFragment.class, null);
            }
        });

        btnCourseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CourseQueryFragment.cCountry="";
                CourseQueryFragment.cCity="";
                CourseQueryFragment.cBuilding="";
                CourseQueryFragment.cType="";
                CourseQueryFragment.cClass="";
                CourseQueryFragment.cTeacher="";
                CourseQueryFragment.cMonth="";
                CourseQueryFragment.cTimeS="";
                CourseQueryFragment.cTimeE="";
                CourseQueryFragment.cPrice="";
                CourseQueryFragment.stringBuilding = null;
                CourseQueryFragment.stringType= null;
                CourseQueryFragment.stringClass= null;
                CourseQueryFragment.stringMonth= null;
                CourseQueryFragment.stringTeacher= null;
                CourseQueryFragment.stringTime= null;
                CourseQueryFragment.stringPrice= null;
                ((MainActivity) getContext()).replaceFragment(CourseRecordFragment.class, null);


            }
        });


        return view;
    }
}
