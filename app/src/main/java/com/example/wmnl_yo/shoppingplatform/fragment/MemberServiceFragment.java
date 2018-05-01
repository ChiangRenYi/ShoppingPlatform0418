package com.example.wmnl_yo.shoppingplatform.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.activity.loginActivity;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.GetEmployInfo;
import com.example.wmnl_yo.shoppingplatform.database.GetPersonalInfo;

/**
 * Created by WMNL-YO on 2017/2/20.
 */

public class MemberServiceFragment extends Fragment {

    public static String PIaccount,PIname,PIgender,PIcareer,PInationality,PIID,PIbirthday,PImail,PIaddress,PIcontact_phone,PIphone;
    public static String EMNO,EMjob,EMRdate,EMlicense,EMname,EMEname,EMbirthday,EMID,EMgender,EMcontact_phone,EMphone,EMnationality,EMmail,EMaddress,EMNaddress,EMeducation,EMhome,EMchildren,EMschool,EMdepartment,EM_Emer_people,EM_Emer_phone;
    private SharedPreferences preferences;
    private View view;
    private Button btnPersonalInfo,btnPasswordReset,btnCompetence;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view==null){
            preferences = getActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);
            view = inflater.inflate(R.layout.fragment_member_service, container, false);
            PIaccount = preferences.getString("PREFERENCE_ACCOUNT", "");
        }
        ((MainActivity) getActivity()).setSubTitle(" > 會員管理");
        btnPersonalInfo = (Button)view.findViewById(R.id.Button_personal_info);
        btnPasswordReset = (Button)view.findViewById(R.id.Button_password_reset);
        btnCompetence = (Button)view.findViewById(R.id.Button_competence);

        try {
            btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    if (loginActivity.userPeople == "student") {
                        GetPersonalInfo getPersonalInfo = new GetPersonalInfo();
                        getPersonalInfo.execute();

                        Toast.makeText(getActivity(), "資料更新中...", Toast.LENGTH_SHORT).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                preferences.edit().putString("PREFERENCE_NAME", PIname).commit();
                                preferences.edit().putString("PREFERENCE_GENDER", PIgender).commit();
                                preferences.edit().putString("PREFERENCE_CAREER", PIcareer).commit();
                                preferences.edit().putString("PREFERENCE_NATIONALITY", PInationality).commit();
                                preferences.edit().putString("PREFERENCE_ID", PIID).commit();
                                preferences.edit().putString("PREFERENCE_BIRTHDAY", PIbirthday).commit();
                                preferences.edit().putString("PREFERENCE_MAIL", PImail).commit();
                                preferences.edit().putString("PREFERENCE_ADDRESS", PIaddress).commit();
                                preferences.edit().putString("PREFERENCE_CONTACT_PHONE", PIcontact_phone).commit();
                                preferences.edit().putString("PREFERENCE_PHONE", PIphone).commit();

                                ((MainActivity) getContext()).replaceFragment(PersonalInfoFragment.class, null);
                            }
                        }, 2000);
                    } else if (loginActivity.userPeople == "teacher") {

                        Log.e("55123-GetEmploy", PIaccount);
                        GetEmployInfo getEmployInfo = new GetEmployInfo();
                        getEmployInfo.execute();

                        Toast.makeText(getActivity(), "資料更新中...", Toast.LENGTH_SHORT).show();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                preferences.edit().putString("PREFERENCE_EMNO", EMNO).commit();
                                preferences.edit().putString("PREFERENCE_EMjob", EMjob).commit();
                                preferences.edit().putString("PREFERENCE_EMRdate", EMRdate).commit();
                                preferences.edit().putString("PREFERENCE_EMlicense", EMlicense).commit();
                                preferences.edit().putString("PREFERENCE_EMname", EMname).commit();
                                preferences.edit().putString("PREFERENCE_EMEname", EMEname).commit();
                                preferences.edit().putString("PREFERENCE_EMbirthday", EMbirthday).commit();
                                preferences.edit().putString("PREFERENCE_EMID", EMID).commit();
                                preferences.edit().putString("PREFERENCE_EMgender", EMgender).commit();
                                preferences.edit().putString("PREFERENCE_EMcontact_phone", EMcontact_phone).commit();
                                preferences.edit().putString("PREFERENCE_EMphone", EMphone).commit();
                                preferences.edit().putString("PREFERENCE_EMnationality", EMnationality).commit();
                                preferences.edit().putString("PREFERENCE_EMmail", EMmail).commit();
                                preferences.edit().putString("PREFERENCE_EMaddress", EMaddress).commit();
                                preferences.edit().putString("PREFERENCE_EMNaddress", EMNaddress).commit();
                                preferences.edit().putString("PREFERENCE_EMeducation", EMeducation).commit();
                                preferences.edit().putString("PREFERENCE_EMhome", EMhome).commit();
                                preferences.edit().putString("PREFERENCE_EMchildren", EMchildren).commit();
                                preferences.edit().putString("PREFERENCE_EMschool", EMschool).commit();
                                preferences.edit().putString("PREFERENCE_EMdepartment", EMdepartment).commit();
                                preferences.edit().putString("PREFERENCE_EM_Emer_people", EM_Emer_people).commit();
                                preferences.edit().putString("PREFERENCE_EM_Emer_phone", EM_Emer_phone).commit();

                                ((MainActivity) getContext()).replaceFragment(EmployInfoFragment.class, null);
                            }
                        }, 2000);


                    }
                }
            });

        }catch (Exception e){
            Toast.makeText(getActivity(),"請檢查網路狀態!",Toast.LENGTH_SHORT).show();
        }
        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ((MainActivity)getContext()).replaceFragment( PasswordResetFragment.class, null);

            }
        });

        btnCompetence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"功能開發中",Toast.LENGTH_SHORT).show();



            }
        });
        return view;

    }
}

