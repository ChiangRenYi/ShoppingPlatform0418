package com.example.wmnl_yo.shoppingplatform.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wmnl_yo.shoppingplatform.Constants;
import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.adapter.ListDropDownAdapter;
import com.example.wmnl_yo.shoppingplatform.object.CompetenceObject;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WMNL-Jimmy on 2018/5/5.
 */

public class CompetenceFragment extends Fragment implements View.OnTouchListener{
    private String com1,com2,com3,com4,com5;
    private Button submit;
    private RecyclerView mRecyclerView;
    public static MyAdapter competenceAdapter;
    private List<CompetenceObject.CompetenceObjectItem> mCompetenceObjectList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        CompetenceObject.ITEMS.clear();
        CompetenceObject dim = new CompetenceObject();

        dim.addItem(new CompetenceObject.CompetenceObjectItem(
                "三峽光明公共托育中心",
                "1"
        ));
        dim.addItem(new CompetenceObject.CompetenceObjectItem(
                "三峽中園公共托育中心",
                "1"
        ));
        dim.addItem(new CompetenceObject.CompetenceObjectItem(
                "樹林三多公共托育中心",
                "0"
        ));
        dim.addItem(new CompetenceObject.CompetenceObjectItem(
                "林口麗林公共托育中心",
                "0"
        ));
        dim.addItem(new CompetenceObject.CompetenceObjectItem(
                "樹林武林公共托育中心",
                "1"
        ));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if (view == null) {
//            view = inflater.inflate(R.layout.fragment_competence, container, false);
//        }
        View v = inflater.inflate(R.layout.fragment_competence, container, false);
        v.setOnTouchListener(this);
        mRecyclerView = (RecyclerView)v.findViewById(R.id.com_recyclerview);
        competenceAdapter = new MyAdapter(CompetenceObject.ITEMS);
        submit = (Button)v.findViewById(R.id.button);
//

//        if(cb2.isChecked()){
//            com2 = "1";
//        }else{
//            com2 = "0";
//        }
//
//        if(cb3.isChecked()){
//            com3 = "1";
//        }else{
//            com3 = "0";
//        }
//
//        if(cb4.isChecked()){
//            com4 = "1";
//        }else{
//            com4 = "0";
//        }
//
//        if(cb5.isChecked()){
//            com5 = "1";
//        }else{
//            com5 = "0";
//        }

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),com1+com2+com3+com4+com5,Toast.LENGTH_SHORT).show();
//            }
//        });
//        view.setOnTouchListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(competenceAdapter);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        return v;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RecyclerView ll;
            public CheckBox checkBox;
            public TextView tvName;
//            public Button btnplus, btnminus;
            public CompetenceObject.CompetenceObjectItem mItem;

            public ViewHolder(View v) {
                super(v);
                ll = (RecyclerView) v.findViewById(R.id.com_recyclerview);
                checkBox = (CheckBox) v.findViewById(R.id.com_checkBox);
                tvName = (TextView) v.findViewById(R.id.com_name);
            }

        }
    public MyAdapter(List<CompetenceObject.CompetenceObjectItem> CompetenceObjectList) {
        mCompetenceObjectList = CompetenceObjectList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_recycleview_competence, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.tvName.setText(mCompetenceObjectList.get(position).competenceName.trim());


//        holder.checkBox.setChecked(false);
//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (holder.checkBox.isChecked()) {
//                    goods = goods + mShoppingCarObjectList.get(position).goodsCount.toString() + ",";
////                        Log.e("55125", goods);
//                } else {
//                    String[] goodsarray = goods.split(",");
//                    goods = "";
//                    ArrayList goodsarraylist = new ArrayList();
//                    for (int t = 0; t < goodsarray.length; t++) {
//                        goodsarraylist.add(goodsarray[t]);
//                    }
//                    goodsarraylist.remove(mShoppingCarObjectList.get(position).goodsCount.toString());
//                    for (int t = 0; t < goodsarraylist.size(); t++) {
//                        goods = goods + goodsarraylist.get(t).toString() + ",";
//                    }
//                }
////
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mCompetenceObjectList.size();
    }
}
}