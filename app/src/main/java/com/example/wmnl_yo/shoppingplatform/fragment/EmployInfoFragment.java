package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.UpdatePersonalInfo;

/**
 * Created by WMNL-Jimmy on 2017/12/7.
 */

public class EmployInfoFragment extends Fragment {
    private String EMNO,EMjob,EMRdate,EMlicense,EMname,EMEname,EMbirthday,EMID,EMgender,EMcontact_phone,EMphone,EMnationality,EMmail,EMaddress,EMNaddress,EMeducation,EMhome,EMchildren,EMschool,EMdepartment,EM_Emer_people,EM_Emer_phone;
    public static String ChangeName,ChangeEname,ChangeContactPhone,ChangePhone,ChangeMail,ChangeAddress,ChangeNaddress,ChangeEducation,Changehome,ChangeChildren,ChangeSchool,ChangeDepartment,ChangeEmerPeople,ChangeEmerPhone;
    private TextView tvNO,tvJob,tvRdate,tvLicense,tvID,tvBirthday,tvGender,tvNationality;
    private EditText edName,edEname,edContactPhone,edPhone,edMail,edAddress,edNaddress,edEducation,edHome,edChildren,edSchool,edDepartment,edEmerPeople,edEmerPhone;
    private View view;
    private ImageView imgProfilePic;

    private Button btnok;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            preferences = getActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);

            EMNO = preferences.getString("PREFERENCE_EMNO","");
            EMjob = preferences.getString("PREFERENCE_EMjob","");
            EMRdate = preferences.getString("PREFERENCE_EMRdate","");
            EMlicense = preferences.getString("PREFERENCE_EMlicense","");
            EMname = preferences.getString("PREFERENCE_EMname","");
            EMEname = preferences.getString("PREFERENCE_EMEname","");
            EMbirthday = preferences.getString("PREFERENCE_EMbirthday","");
            EMID = preferences.getString("PREFERENCE_EMID","");
            EMgender = preferences.getString("PREFERENCE_EMgender","");
            EMcontact_phone = preferences.getString("PREFERENCE_EMcontact_phone","");
            EMphone = preferences.getString("PREFERENCE_EMphone","");
            EMnationality = preferences.getString("PREFERENCE_EMnationality","");
            EMmail = preferences.getString("PREFERENCE_EMmail","");
            EMaddress = preferences.getString("PREFERENCE_EMaddress","");
            EMNaddress = preferences.getString("PREFERENCE_EMNaddress","");
            EMeducation = preferences.getString("PREFERENCE_EMeducation","");
            EMhome = preferences.getString("PREFERENCE_EMhome","");
            EMchildren = preferences.getString("PREFERENCE_EMchildren","");
            EMschool = preferences.getString("PREFERENCE_EMschool","");
            EMdepartment = preferences.getString("PREFERENCE_EMdepartment","");
            EM_Emer_people = preferences.getString("PREFERENCE_EM_Emer_people","");
            EM_Emer_phone = preferences.getString("PREFERENCE_EM_Emer_phone","");


            view = inflater.inflate(R.layout.fragment_personal_employ_info, container, false);
            btnok = (Button) view.findViewById(R.id.Button_EM_ok);

//            final Spinner familySpinner = (Spinner)view.findViewById(R.id.spinner_register_gender);
//            ArrayAdapter<CharSequence> familyList = ArrayAdapter.createFromResource(
//                    getActivity(),
//                    R.array.family,
//                    R.layout.spinner_style);
//
//            familySpinner.setAdapter(familyList);
//            familySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    EMhome = familySpinner.getSelectedItem().toString();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });

            tvNO = (TextView)view.findViewById(R.id.textView_EM_NO_show);
            tvJob = (TextView)view.findViewById(R.id.textView_job_show);
            tvRdate = (TextView)view.findViewById(R.id.textView_Rdate_show);
            tvLicense = (TextView)view.findViewById(R.id.textView_license_show);
            tvBirthday = (TextView)view.findViewById(R.id.textView_EM_birthday_show);
            tvID = (TextView)view.findViewById(R.id.textView_EM_ID_show);
            tvGender = (TextView)view.findViewById(R.id.textView_EM_gender_show);
            tvNationality = (TextView)view.findViewById(R.id.textView_EM_nationality_show);

            edName = (EditText)view.findViewById(R.id.editText_EM_username);
            edEname = (EditText)view.findViewById(R.id.editText_EM_Ename);
            edContactPhone = (EditText)view.findViewById(R.id.editText_EM_contact_phone);
            edPhone = (EditText)view.findViewById(R.id.editText_EM_phone);
            edMail = (EditText)view.findViewById(R.id.editText_EM_mail);
            edAddress = (EditText)view.findViewById(R.id.editText_EM_address);
            edNaddress = (EditText)view.findViewById(R.id.editText_EM_Naddress);
            edEducation = (EditText)view.findViewById(R.id.editText_EM_edu);
            edHome = (EditText)view.findViewById(R.id.editText_EM_family);
            edChildren = (EditText)view.findViewById(R.id.editText_EM_children);
            edSchool = (EditText)view.findViewById(R.id.editText_EM_sch);
            edDepartment = (EditText)view.findViewById(R.id.editText_EM_dep);
            edEmerPeople = (EditText)view.findViewById(R.id.editText_emer_people);
            edEmerPhone = (EditText)view.findViewById(R.id.editText_emer_phone);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    tvNO.setText(EMNO);
                    tvJob.setText(EMjob);
                    tvRdate.setText(EMRdate);
                    tvLicense.setText(EMlicense);

                    edName.setText(EMname);
                    edEname.setText(EMEname);

                    tvBirthday.setText(EMbirthday);
                    tvID.setText(EMID);
                    tvGender.setText(EMgender);

                    edContactPhone.setText(EMcontact_phone);
                    edPhone.setText(EMphone);

                    tvNationality.setText(EMnationality);

                    edMail.setText(EMmail);
                    edAddress.setText(EMaddress);
                    edNaddress.setText(EMNaddress);
                    edEducation.setText(EMeducation);
                    edChildren.setText(EMchildren);
                    edSchool.setText(EMschool);
                    edDepartment.setText(EMdepartment);
                    edEmerPeople.setText(EM_Emer_people);
                    edEmerPhone.setText(EM_Emer_phone);


                }
            }, 1500);


            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeName = edName.getText().toString();
                    ChangeEname = edEname.getText().toString();
                    ChangeAddress = edAddress.getText().toString();
                    ChangeContactPhone = edContactPhone.getText().toString();
                    ChangePhone = edPhone.getText().toString();
                    ChangeMail = edMail.getText().toString();
                    ChangeAddress = edAddress.getText().toString();
                    ChangeNaddress = edNaddress.getText().toString();
                    ChangeEducation = edEducation.getText().toString();
                    ChangeChildren = edChildren.getText().toString();
                    ChangeSchool = edSchool.getText().toString();
                    ChangeDepartment = edDepartment.getText().toString();
                    ChangeEmerPeople = edEmerPeople.getText().toString();
                    ChangeEmerPhone = edEmerPhone.getText().toString();
                    Changehome = edHome.getText().toString();

                    UpdatePersonalInfo updatePersonalInfo = new UpdatePersonalInfo();
                    updatePersonalInfo.execute();

                    Toast.makeText(getActivity(),"更新成功",Toast.LENGTH_SHORT).show();


                }
            });
        }

        return view;
        }

}
