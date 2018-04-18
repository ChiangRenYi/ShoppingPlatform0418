package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.loginActivity;
import com.example.wmnl_yo.shoppingplatform.database.PasswordReset;
import com.pusher.client.channel.PrivateChannel;

/**
 * Created by WMNL-YO on 2017/2/22.
 */

public class PasswordResetFragment extends Fragment {

    private View view;
    private EditText OldPasswprd,NewPassword,PasswordCheck;
    private SharedPreferences preferences;
    public static String pw1,pw2,pw3,PasswordChange,oldpass;
    private Button btok;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_password_change, container, false);
            preferences = getActivity().getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE);


        }

        oldpass = preferences.getString("PREFERENCE_PASSWORD","");



        btok = (Button)view.findViewById(R.id.PassowrdOK);





        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                OldPasswprd = (EditText)view.findViewById(R.id.oldPassword);
                NewPassword = (EditText)view.findViewById(R.id.newPassword);
                PasswordCheck =(EditText)view.findViewById(R.id.newPasswordCheck);

                pw1 = OldPasswprd.getText().toString();
                pw2 = NewPassword.getText().toString();
                pw3 = PasswordCheck.getText().toString();
                PasswordChange = pw3;

                Log.e("55555",oldpass+"7"+pw1);
                if( !pw2.equals(pw3)){
                    Toast.makeText(getActivity(),"兩次輸入密碼不一致，請重新輸入",Toast.LENGTH_SHORT).show();
                    OldPasswprd.setText("");
                    NewPassword.setText("");
                    PasswordCheck.setText("");
                }else if(!pw1.equals(oldpass)){
                    Toast.makeText(getActivity(),"舊密碼輸入錯誤，請重新輸入",Toast.LENGTH_SHORT).show();
                    OldPasswprd.setText("");
                    NewPassword.setText("");
                    PasswordCheck.setText("");
                }else if(pw1.equals(oldpass) && pw2.equals(pw3)){

                    PasswordReset passwordReset = new PasswordReset();
                    passwordReset.execute();

                    Toast.makeText(getActivity(),"密碼更新成功，請重新登入",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),loginActivity.class);
                    startActivity(intent);
                    getActivity().finish();

                }

            }
        });

        return view;
    }
}
