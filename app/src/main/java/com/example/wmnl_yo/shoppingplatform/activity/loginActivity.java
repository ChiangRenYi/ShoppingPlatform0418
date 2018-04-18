package com.example.wmnl_yo.shoppingplatform.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.database.LoginInfoCheck;

import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Created by WMNL-YO on 2017/2/7.
 */

public class loginActivity extends Activity {

    private EditText AccountEditText ,PasswordEditText;
    private Button LoginButton ,RegisterButton,NonmemberBotton;
    public static String account ,password,loginResult;
    private SegmentedGroup sg;
    private RadioButton rbMember,rbTeacher,rbBasicLevel,rbManager,rbDepartmentOfSocial,rbAdmin;
    private SharedPreferences preferences;
    public static String userPeople ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        preferences = getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);

        AccountEditText = (EditText)findViewById(R.id.editText_account);
        PasswordEditText = (EditText)findViewById(R.id.editText_password);
        LoginButton = (Button)findViewById(R.id.Button_Login);
        RegisterButton = (Button)findViewById(R.id.Button_register);
        NonmemberBotton = (Button)findViewById(R.id.Button_nonmember_login);

        sg = (SegmentedGroup)findViewById(R.id.radioGroup_user_indentity);
        rbMember = (RadioButton)findViewById(R.id.radioButton_indentity_member);
        rbTeacher = (RadioButton)findViewById(R.id.radioButton_indentity_teacher);
//        rbBasicLevel = (RadioButton)findViewById(R.id.radioButton_indentity_basic_level);
//        rbManager = (RadioButton)findViewById(R.id.radioButton_indentity_manager);
//        rbDepartmentOfSocial = (RadioButton)findViewById(R.id.radioButton_indentity_department_of_Social);
//        rbAdmin = (RadioButton)findViewById(R.id.radioButton_indentity_admin);


        rbMember.setChecked(true);


            LoginButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info=connManager.getActiveNetworkInfo();

                    if (info == null || !info.isConnected())
                    {
                        Toast.makeText(loginActivity.this,"請檢查網路",Toast.LENGTH_LONG).show();
                    }
                    else {

                        account = AccountEditText.getText().toString();
                        password = PasswordEditText.getText().toString();
                        if (rbMember.isChecked() == true) {
                            userPeople = "student";

                        } else {
                            userPeople = "teacher";
                        }
                        if (!account.equals("") && !password.equals("")) {

                            preferences.edit().putString("PREFERENCE_ACCOUNT", account).commit();
                            preferences.edit().putString("PREFERENCE_PASSWORD", password).commit();
//                    Toast.makeText(loginActivity.this,"請稍後...",Toast.LENGTH_SHORT).show();

                            LoginInfoCheck loginInfoCheck = new LoginInfoCheck();
                            loginInfoCheck.execute();

                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Do something after 5s = 5000ms
                                    switch (loginResult) {
                                        case "noAccount":
                                            Toast.makeText(loginActivity.this, "查無此帳號，請重新輸入", Toast.LENGTH_SHORT).show();
                                            AccountEditText.setText("");
                                            PasswordEditText.setText("");
                                            Log.e("55886L", "查無此帳號");
                                            break;

                                        case "wrongPassword":
                                            Toast.makeText(loginActivity.this, "密碼錯誤，請重新輸入", Toast.LENGTH_SHORT).show();
                                            PasswordEditText.setText("");

                                            Log.e("55886L", "密碼錯誤");
                                            break;

                                        case "success":
                                            Toast.makeText(loginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                                            Log.e("55886L", "登入成功");
                                            Constants.ACCOUNT = account;
                                            Intent intent = new Intent();
                                            intent.setClass(loginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }, 500);


                        }
                    }
                }
            });

            RegisterButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info=connManager.getActiveNetworkInfo();

                    if (info == null || !info.isConnected())
                    {
                        Toast.makeText(loginActivity.this,"請檢查網路",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent = new Intent();
                        intent.setClass(loginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                    }
            });

            NonmemberBotton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info=connManager.getActiveNetworkInfo();

                    if (info == null || !info.isConnected())
                    {
                        Toast.makeText(loginActivity.this,"請檢查網路",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Intent intent2 = new Intent();
                        intent2.setClass(loginActivity.this, NonmemberLoginActivity.class);
                        userPeople = "nonmember";
                        startActivity(intent2);
                    }
                }
            });



    }

    void testOriginalUsercxtrzteztz() {
        Log.d("test","測試用原本的使用者上傳程式!!!!!!!!!!~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


    void testOriginalUsez() {
        Log.d("test","測試");
    }

    void testOriginalUse123z() {
        Log.d("test","測試");
    }
}
