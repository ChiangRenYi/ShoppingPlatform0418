package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.activity.MainActivity;
import com.example.wmnl_yo.shoppingplatform.activity.loginActivity;

public class ShoppingCarFragment extends Fragment {

    private TextView SignOutText,EmailText,NameText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_car, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        SignOutText = (TextView) view.findViewById(R.id.text_SignOut);
        EmailText = (TextView)view.findViewById(R.id.tvEmail);
        NameText = (TextView)view.findViewById(R.id.tvName);

        NameText.setText(MainActivity.account);
        EmailText.setText("歡迎");

        SignOutText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                View view = getView();
                if (null != view) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),loginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
    }


}
