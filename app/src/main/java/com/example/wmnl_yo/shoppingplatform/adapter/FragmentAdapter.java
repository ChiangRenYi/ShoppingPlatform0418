package com.example.wmnl_yo.shoppingplatform.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.wmnl_yo.shoppingplatform.activity.loginActivity;
import com.example.wmnl_yo.shoppingplatform.fragment.CategoryFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.MainFragmentNonmember;
import com.example.wmnl_yo.shoppingplatform.fragment.MainFragmentStudent;
import com.example.wmnl_yo.shoppingplatform.fragment.MainFragmentTeacher;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingCarFragment;
import com.example.wmnl_yo.shoppingplatform.fragment.ShoppingListFragment;

/**
 * Created by WMNL-YO on 2017/1/10.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {

    int TabCount;

    public FragmentAdapter(FragmentManager fragmentManager, int CountTabs) {

        super(fragmentManager);

        this.TabCount = CountTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ShoppingCarFragment tab1 = new ShoppingCarFragment();
            return tab1;

            case 1:
                CategoryFragment tab2 = new CategoryFragment();
                return tab2;

            case 2:
                if(loginActivity.userPeople == "student") {
                    Log.e("FragmentTest",loginActivity.userPeople);
                    MainFragmentStudent tab3 = new MainFragmentStudent();

                    return tab3;
                }else if(loginActivity.userPeople == "teacher") {
                    Log.e("FragmentTest",loginActivity.userPeople);
                    MainFragmentTeacher tab3 = new MainFragmentTeacher();

                    return tab3;
                }else if(loginActivity.userPeople == "nonmember") {
                    Log.e("FragmentTest",loginActivity.userPeople);
                    MainFragmentNonmember tab3 = new MainFragmentNonmember();

                    return tab3;
                }
            case 3:
                final ShoppingListFragment tab4 = new ShoppingListFragment();

                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}