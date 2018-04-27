package com.example.wmnl_yo.shoppingplatform.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.SendRegisterInfo;

import java.util.Calendar;

/**
 * Created by WMNL-YO on 2017/2/9.
 */

public class RegisterActivity extends Activity {

    private EditText edAccount,edName,edID,edBirthday,edMail,edAddress,edContactPhone,edPhone,edPassword,edPasswordCheck;
    private Button btnRegisterOK;
    public static String Register_Account,Register_Name,Register_Gender,Register_Career,Register_Nationality,Register_ID,Register_Birthday,Register_Mail,Register_Address,Register_ContactPhone,Register_Phone,Register_Password,Register_PasswordCheck;
    private int mYear,mMonth,mDay;
    public static String SendResponse ;
    public String Fail = "repeat";
    public String Sucess = "noRepeat";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout_member);

        final Spinner genderSpinner = (Spinner)findViewById(R.id.spinner_register_gender);
        ArrayAdapter<CharSequence> genderList = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                R.layout.spinner_style);

        genderSpinner.setAdapter(genderList);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Register_Gender = genderSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final Spinner careerSpinner = (Spinner)findViewById(R.id.spinner_register_career);
        ArrayAdapter<CharSequence> careerList = ArrayAdapter.createFromResource(
                this,
                R.array.career,
                R.layout.spinner_style);

        careerSpinner.setAdapter(careerList);
        careerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Register_Career = careerSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        final Spinner nationalitySpinner = (Spinner)findViewById(R.id.spinner_register_nationality);
        ArrayAdapter<CharSequence> nationalityList = ArrayAdapter.createFromResource(
                this,
                R.array.nationality,
                R.layout.spinner_style);

        nationalitySpinner.setAdapter(nationalityList);
        nationalitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Register_Nationality = nationalitySpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        edAccount = (EditText)findViewById(R.id.editText_register_account);
        edName = (EditText)findViewById(R.id.editText_register_username);
        edID = (EditText)findViewById(R.id.editText_register_ID);
        edBirthday = (EditText)findViewById(R.id.editText_register_birthday);
        edMail = (EditText)findViewById(R.id.editText_register_mail);
        edAddress = (EditText)findViewById(R.id.editText_register_address);
        edContactPhone = (EditText)findViewById(R.id.editText_register_contact_phone);
        edPhone = (EditText)findViewById(R.id.editText_register_phone);

        edPassword = (EditText)findViewById(R.id.editText_register_password);
        edPasswordCheck = (EditText)findViewById(R.id.editText_register_check_password);

        edBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        edBirthday.setText(setDateFormat(year,monthOfYear,dayOfMonth));
                    }
                },mYear, mMonth, mDay).show();
            }
        });

        btnRegisterOK = (Button)findViewById(R.id.register_Button_ok);
        btnRegisterOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Register_Account = edAccount.getText().toString();
                Register_Name = edName.getText().toString();
                Register_ID = edID.getText().toString();
                Register_Birthday = edBirthday.getText().toString();
                Register_Mail = edMail.getText().toString();
                Register_Address = edAddress.getText().toString();
                Register_ContactPhone = edContactPhone.getText().toString();
                Register_Phone = edPhone.getText().toString();

                Register_Password = edPassword.getText().toString();
                Register_PasswordCheck = edPasswordCheck.getText().toString();

                if(Register_Account.matches("")||Register_Name.matches("")||Register_Career.matches("")||Register_Nationality.matches("")|Register_ID.matches("")||Register_Birthday.matches("")||Register_Address.matches("")||Register_ContactPhone.matches("")||Register_Phone.matches("")) {
                    Toast.makeText(RegisterActivity.this, "欄位不能為空白！", Toast.LENGTH_SHORT).show();
                }else if (!Register_Password.matches(Register_PasswordCheck)){
                    Toast.makeText(RegisterActivity.this, "兩次輸入密碼不一致，請重新輸入", Toast.LENGTH_SHORT).show();
                    edPassword.setText("");
                    edPasswordCheck.setText("");
                }else{
                    //傳進資料庫
                    Log.e("55886","準備傳資料庫");
//                    Runnable r1 = new Runnable() {
//                        @Override
//                        public void run() {
//                            SendRegisterInfo sendRegisterInfo = new SendRegisterInfo();
//                            sendRegisterInfo.execute();
//                        }
//                    };
//                    Thread t1 = new Thread(r1);
//                    t1.start();
                    SendRegisterInfo sendRegisterInfo = new SendRegisterInfo();
                    sendRegisterInfo.execute();
                    Log.e("55886","傳完資料庫");

                    Toast.makeText(RegisterActivity.this,"請稍後...",Toast.LENGTH_SHORT).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms

                            if(SendResponse == "repeat"){
                                Log.e("55886","重複了!");
                                Toast.makeText(RegisterActivity.this,"帳號已存在，請更改帳號",Toast.LENGTH_SHORT).show();
                                Log.e("55886","RegisterInfo："+ Register_Account + "," + Register_Password + "," + Register_Career + "," + Register_Nationality + "," +Register_Gender);
                                edAccount.setText("");
                                SendResponse ="noRepeat";
                            }else if(SendResponse == "repeat_id"){
                                Toast.makeText(RegisterActivity.this,"此身分證已註冊帳號",Toast.LENGTH_SHORT).show();
                                edID.setText("");
                                SendResponse ="noRepeat";
                            }else{
                                Log.e("55886","沒有重複");
                                Toast.makeText(RegisterActivity.this,"申請成功！",Toast.LENGTH_SHORT).show();
                                Log.e("55886","RegisterInfo："+ Register_Account + "," + Register_Password + "," + Register_Career + "," + Register_Nationality);
                                finish();
                            }
                        }
                    }, 1500);

//                    if(SendResponse == "repeat"){
//                        Log.e("55886","重複了!");
//                        Toast.makeText(RegisterActivity.this,"帳號已存在，請更改帳號",Toast.LENGTH_SHORT).show();
//                        edAccount.setText("");
//                       SendResponse ="noRepeat";
//                    }else{
//                        Log.e("55886","沒有重複");
//                        Toast.makeText(RegisterActivity.this,"申請成功！",Toast.LENGTH_SHORT).show();
//                    }
//                    Log.e("55454","success");
                }


                //Log.e("55555",Account+"\n"+Name+"\n"+Career+"\n"+Nationality+"\n"+ID+"\n"+Birthday+"\n"+Mail+"\n"+Address+"\n"+ContactPhone+"\n"+Phone+"\n"+Password+"\n"+PasswordCheck);


            }
        });



    }


    private String setDateFormat(int year, int monthOfYear, int dayOfMonth) {

        if(monthOfYear < 10){
            return String.valueOf(year) + "-"
                    + "0"+ String.valueOf(monthOfYear + 1) + "-"
                    + String.valueOf(dayOfMonth);
        }else{
            return String.valueOf(year) + "-"
                    + String.valueOf(monthOfYear + 1) + "-"
                    + String.valueOf(dayOfMonth);

        }

    }
}

