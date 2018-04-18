package com.example.wmnl_yo.shoppingplatform.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;

/**
 * Created by WMNL-Jimmy on 2017/9/12.
 */

public class NonmemberLoginActivity extends Activity{

    private EditText edAccount,edPassword,edMail,edCheckPassword;
    private Button btnRegisterOK;
    private RadioButton rbMemberRegister,rbBasicLevelRegister;
    private String account ,email;
    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        preferences = getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);
        edAccount = (EditText)findViewById(R.id.editText_nonmember_account);
        edMail = (EditText)findViewById(R.id.editText_nonmember_mail);
//        edPassword = (EditText)findViewById(R.id.editText_register_password);
//        edCheckPassword = (EditText)findViewById(R.id.editText_register_check_password);

        btnRegisterOK = (Button)findViewById(R.id.Button_nonmember_ok);


        btnRegisterOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                account = edAccount.getText().toString();
                email = edMail.getText().toString();

                if(!account.equals("") && !email.equals("")){
                    preferences.edit().putString("PREFERENCE_ACCOUNT",account).commit();
                    preferences.edit().putString("PREFERENCE_PASSWORD",email).commit();

                    loginActivity.userPeople = "nonmember";
                    Intent intent = new Intent();
                    intent.setClass(NonmemberLoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

        });

    }
}
