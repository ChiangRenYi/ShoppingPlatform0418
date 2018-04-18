package com.example.wmnl_yo.shoppingplatform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by WMNL-Jimmy on 2017/10/16.
 */

public class PushDialog extends DialogFragment {

    public static PushDialog newInstance(String msg){
        PushDialog f = new PushDialog();
        Bundle args = new Bundle();
        args.putString("msg",msg);
        f.setArguments(args);//透過setArguments傳值
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//取消Dialog title列
        getDialog().setCanceledOnTouchOutside(false);//不能點擊Dialog以外區域
        View v = inflater.inflate(R.layout.activity_pushtext, container, false);//選擇自定義的layout
        Button btn = (Button) v.findViewById(R.id.btn_cancel) ;
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();//Dialog關閉
            }
        } );
        TextView txt = (TextView) v.findViewById(R.id.tvPush);
        String msg = getArguments().getString("msg");
        txt.setText(msg);
        return v;
    }


}
