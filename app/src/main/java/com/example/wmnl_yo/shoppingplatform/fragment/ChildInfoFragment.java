package com.example.wmnl_yo.shoppingplatform.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wmnl_yo.shoppingplatform.KinshipContent;
import com.example.wmnl_yo.shoppingplatform.R;

/**
 * Created by WMNL-YO on 2017/3/30.
 */

public class ChildInfoFragment extends Fragment{

    private View view;
    private Button btnInfoConfirm;
    private EditText edChildName;
    private String childName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_child_info, container, false);
        }
        edChildName = (EditText)view.findViewById(R.id.editText_childname);
        btnInfoConfirm = (Button)view.findViewById(R.id.Button_child_confirm);

        btnInfoConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                childName = edChildName.getText().toString();
                Log.d("55123-childName",childName);
                if(!childName.equals("")) {
                    KinshipContent dim = new KinshipContent();
                    dim.addItem(new KinshipContent.KinshipItem(childName));
                    KinshipManageFragment.adapter.notifyDataSetChanged();

                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

        return view;




    }
}

