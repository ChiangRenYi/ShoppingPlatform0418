package com.example.wmnl_yo.shoppingplatform.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.activity.loginActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetAbsentNoteEntryFragmentTeacherNumber;

/**
 * Created by WMNL-YO on 2017/3/14.
 */

public class AttendenceManageFragment extends Fragment implements View.OnTouchListener {

    private View view;

    private Button btnAbsentNoteEntry,btnAbsentNoteRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_attend_manage, container, false);
            view.setOnTouchListener(this);
            btnAbsentNoteEntry = (Button)view.findViewById(R.id.Button_attend_input);
            btnAbsentNoteRecord = (Button)view.findViewById(R.id.Button_attend_record);
        }

        ((MainActivity) getActivity()).setSubTitle(" > 出缺勤管理");

        if(loginActivity.userPeople == "student") {
            btnAbsentNoteEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getContext()).replaceFragment(AbsentNoteEntryFragment.class, null);
                }
            });

            btnAbsentNoteRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getContext()).replaceFragment(AbsentNoteRecordFragment.class, null);
                }
            });
        }else if(loginActivity.userPeople == "teacher"){
            btnAbsentNoteEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GetAbsentNoteEntryFragmentTeacherNumber getAbsentNoteEntryFragmentTeacherNumber = new GetAbsentNoteEntryFragmentTeacherNumber();
                    getAbsentNoteEntryFragmentTeacherNumber.execute();
                    Toast.makeText(getActivity(), "請稍後...", Toast.LENGTH_SHORT).show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((MainActivity) getContext()).replaceFragment(AbsentNoteEntryFragmentTeacher.class, null);
                        }
                    },300);
                }
            });

            btnAbsentNoteRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getContext()).replaceFragment(AbsentNoteRecordFragmentTeacher.class, null);
                }
            });
        }
        return view;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }
}
