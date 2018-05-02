package com.example.wmnl_yo.shoppingplatform.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.database.GetSelectBuildingFragment;
import com.example.wmnl_yo.shoppingplatform.database.GetSelectStudentFragment;
import com.example.wmnl_yo.shoppingplatform.database.GetShoppingMallAll;

public class MainFragmentStudent extends Fragment implements View.OnClickListener {
    private View view;
    LinearLayout btnMemberService, btnCourseManage, btnBuilding,btnShoppingmall, btnOrderManage, btnAttendenceManage, btnHealthManage, btnInteractive, btnOpinion, btnChat,btnSatisfaction;
    TextView tvName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
        }

        tvName = (TextView)view.findViewById(R.id.textView25);
        btnMemberService = (LinearLayout) view.findViewById(R.id.layout_member_service_button);
        btnCourseManage = (LinearLayout) view.findViewById(R.id.layout_course_manage_button);
        btnBuilding = (LinearLayout) view.findViewById(R.id.layout_building_button);
        btnOrderManage = (LinearLayout) view.findViewById(R.id.layout_order_manage_button);
        btnShoppingmall = (LinearLayout) view.findViewById(R.id.layout_shoppingmall_button);
        btnAttendenceManage = (LinearLayout) view.findViewById(R.id.layout_attendence_manage_button);
        btnHealthManage = (LinearLayout) view.findViewById(R.id.layout_health_manage_button);
        btnInteractive = (LinearLayout) view.findViewById(R.id.layout_interactive_button);
//        btnOpinion = (LinearLayout) view.findViewById(R.id.layout_opinion_button);
        btnSatisfaction=(LinearLayout) view.findViewById(R.id.layout_satisfaction_button);

        btnMemberService.setOnClickListener(this);
        btnCourseManage.setOnClickListener(this);
        btnOrderManage.setOnClickListener(this);
        btnShoppingmall.setOnClickListener(this);
        btnAttendenceManage.setOnClickListener(this);
        btnHealthManage.setOnClickListener(this);
        btnInteractive.setOnClickListener(this);
//        btnOpinion.setOnClickListener(this);
        btnBuilding.setOnClickListener(this);
        tvName.setText(Constants.ACCOUNT);
        btnSatisfaction.setOnClickListener(this);

        return view;
    }


    public void onClick(View v) {

        Fragment fragment = null;
        Class fragmentClass = null;
//        ConnectivityManager connManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo info = connManager.getActiveNetworkInfo();

        switch (v.getId()) {

            case R.id.layout_member_service_button:
                fragmentClass = MemberServiceFragment.class;
                break;

            case R.id.layout_course_manage_button:
                fragmentClass = CourseManageFragment.class;
                break;
            case R.id.layout_building_button:
                fragmentClass = BuildingFragment.class;
                break;

            case R.id.layout_attendence_manage_button:
                fragmentClass = AttendenceManageFragment.class;
                break;

            case R.id.layout_calendar_button:
                fragmentClass = CalendarFragment.class;
                break;

            case R.id.layout_satisfaction_button:
                fragmentClass = SatisfacationChildFragment.class;
                break;

            case R.id.layout_interactive_button:

                GetSelectBuildingFragment getSelectBuildingFragment = new GetSelectBuildingFragment();
                getSelectBuildingFragment.execute();

                fragmentClass = SelectBuildingFragment.class;
                break;

            case R.id.layout_health_manage_button:

                GetSelectStudentFragment getSelectStudentFragment = new GetSelectStudentFragment();
                getSelectStudentFragment.execute();

                fragmentClass = SelectStudentFragment.class;
                break;
            case R.id.layout_shoppingmall_button:

                GetShoppingMallAll getShoppingMallAll = new GetShoppingMallAll();
                getShoppingMallAll.execute();

                fragmentClass = ShoppingObjectFragment.class;

                break;
            case R.id.layout_order_manage_button:

                fragmentClass = OrderResultFragment.class;
                break;
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