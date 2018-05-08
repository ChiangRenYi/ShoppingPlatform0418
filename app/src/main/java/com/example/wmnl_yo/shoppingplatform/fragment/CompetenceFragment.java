package com.example.wmnl_yo.shoppingplatform.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wmnl_yo.shoppingplatform.R;
import com.example.wmnl_yo.shoppingplatform.adapter.ListDropDownAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by WMNL-Jimmy on 2018/5/5.
 */

public class CompetenceFragment extends Fragment{

    public   @InjectView (R.id.dropDownMenu)  DropDownMenu mDropDownMenu;
    private String headers[] = {"城市", "年龄"};
    private List<View> popupViews = new ArrayList<>();


    private ListDropDownAdapter ageAdapter;
    private ListDropDownAdapter sexAdapter;
    private View view;
    private String ages[] = {"不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"不限", "男", "女"};

    private int constellationPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_competence, container, false);
        ButterKnife.inject(getActivity());
        initView();


        return v;
    }

    private void initView() {

        //init age menu
        final ListView ageView = new ListView(getActivity());
        ageView.setDividerHeight(0);
        ageAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(ages));
        ageView.setAdapter(ageAdapter);

        //init sex menu
        final ListView sexView = new ListView(getActivity());
        sexView.setDividerHeight(0);
        sexAdapter = new ListDropDownAdapter(getActivity(), Arrays.asList(sexs));
        sexView.setAdapter(sexAdapter);

        //init popupViews
        popupViews.add(ageView);
        popupViews.add(sexView);


        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ageAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : ages[position]);
                mDropDownMenu.closeMenu();
            }
        });

        sexView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sexAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : sexs[position]);
                mDropDownMenu.closeMenu();
            }
        });


        //init context view
//        TextView contentView = new TextView(getActivity());
        TextView contentView = new TextView(getActivity());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentView.setText("内容显示区域");
        contentView.setGravity(Gravity.CENTER);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

//    @Override
//    public void onBackPressed() {
//        //退出activity前关闭菜单
//        if (mDropDownMenu.isShowing()) {
//            mDropDownMenu.closeMenu();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
