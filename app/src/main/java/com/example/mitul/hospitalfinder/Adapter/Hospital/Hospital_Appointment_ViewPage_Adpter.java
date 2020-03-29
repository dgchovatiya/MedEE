package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_Pending_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_Pending_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_reject_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_reject_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_sucess_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_sucess_Order_Blood_Request;

/**
 * Created by Denish on 29-01-2019.
 */

public class Hospital_Appointment_ViewPage_Adpter extends FragmentStatePagerAdapter {


    int tabcount;
    Context context;
    String hospital_id;

    public Hospital_Appointment_ViewPage_Adpter(FragmentManager fm, int tabcount, Context context, String hospital_id) {

        super(fm);
        this.tabcount=tabcount;
        this.context=context;
        this.hospital_id=hospital_id;
    }


    @Override
    public int getCount() {

        return tabcount;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                Hospital_Pending_Appointment_Request hospital_pending_appointment_request =new Hospital_Pending_Appointment_Request(context,hospital_id);
                return hospital_pending_appointment_request;

            case 1:
                Hospital_sucess_Appointment_Request hospital_sucess_appointment_request=new Hospital_sucess_Appointment_Request(context,hospital_id);
                return hospital_sucess_appointment_request;


            case 2:
                Hospital_reject_Appointment_Request hospital_reject_appointment_request=new Hospital_reject_Appointment_Request(context,hospital_id);
                return hospital_reject_appointment_request;


        }

        return null;
    }
}
