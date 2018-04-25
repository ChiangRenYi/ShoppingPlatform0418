package com.example.wmnl_yo.shoppingplatform.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;

public class MainFragmentTeacher extends Fragment implements View.OnClickListener {
    private View view;
    LinearLayout btnMemberService, btnCourseManage, btnBuilding, btnOrderManage, btnAttendenceManage, btnRollcall, btnHealthManage, btnInteractive, btnOpinion, btnChat;
    TextView tvName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main_teacher, container, false);
        }

        tvName = (TextView)view.findViewById(R.id.textView25);
        btnMemberService = (LinearLayout) view.findViewById(R.id.layout_member_service_button);
        btnCourseManage = (LinearLayout) view.findViewById(R.id.layout_course_manage_button);
        btnBuilding = (LinearLayout) view.findViewById(R.id.layout_building_button);
        btnAttendenceManage = (LinearLayout) view.findViewById(R.id.layout_attendence_manage_button);
        btnRollcall = (LinearLayout) view.findViewById(R.id.layout_course_rollcall);
        btnOpinion = (LinearLayout) view.findViewById(R.id.layout_opinion_button);

        btnMemberService.setOnClickListener(this);
        btnCourseManage.setOnClickListener(this);
        btnBuilding.setOnClickListener(this);
        btnAttendenceManage.setOnClickListener(this);
        btnRollcall.setOnClickListener(this);
        btnOpinion.setOnClickListener(this);
        tvName.setText(Constants.ACCOUNT);

        return view;
    }


    public void onClick(View v) {

        Fragment fragment = null;
        Class fragmentClass = null;

        switch (v.getId()) {

            case R.id.layout_member_service_button:
                fragmentClass = MemberServiceFragment.class;
                break;

            case R.id.layout_course_manage_button:
                fragmentClass = CourseQueryFragment.class;
                break;

            case R.id.layout_building_button:
                fragmentClass = BuildingFragment.class;
                break;

            case R.id.layout_attendence_manage_button:
                fragmentClass = AttendenceManageFragment.class;
                break;

            case R.id.layout_course_rollcall:
                fragmentClass = RollCallFragment.class;
                break;

            case R.id.layout_interactive_button:
                fragmentClass = NoShitFragment.class;
                break;

            case R.id.layout_opinion_button:
                fragmentClass = SelectMemberFragment.class;
                break;
//
//            case R.id.layout_chat_button:
//                fragmentClass = ChatFragment.class;
//                break;
            default:
                break;
        }

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fragment.setTargetFragment(getTargetFragment(), 0);
        getFragmentManager()
                .beginTransaction()
                .add(R.id.flContent, fragment)
                .addToBackStack(null)
                .commit();

//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//        Log.d("55124",String.valueOf(getFragmentManager().getBackStackEntryCount()));

    }

}
//            progressDialog = ProgressDialog.show(MainActivity.this,
//                    null, getString(R.string.alert_loading), true);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1500);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        progressDialog.dismiss();
//                    }
//                }
//            }).start();