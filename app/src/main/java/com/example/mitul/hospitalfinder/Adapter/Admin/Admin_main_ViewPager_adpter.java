package com.example.mitul.hospitalfinder.Adapter.Admin;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.mitul.hospitalfinder.Fragment.Admin.Admin_Pending_Request;
import com.example.mitul.hospitalfinder.Fragment.Admin.Admin_Sucess_list;

/**
 * Created by Denish on 07-01-2019.
 */

public class Admin_main_ViewPager_adpter extends FragmentStatePagerAdapter {
    int tabcount;
    Context context;

    public Admin_main_ViewPager_adpter(FragmentManager fm, int tabcount, Context context) {

        super(fm);
        this.tabcount=tabcount;
        this.context=context;
    }


    @Override
    public int getCount() {

        return tabcount;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                Admin_Pending_Request admin_pending_request =new Admin_Pending_Request(context);
                return admin_pending_request;

            case 1:
                Admin_Sucess_list admin_sucess_list=new Admin_Sucess_list(context);
                return admin_sucess_list;


        }

        return null;
    }


}
