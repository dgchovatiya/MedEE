package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_Pending_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_reject_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_sucess_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_Pending_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_reject_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_sucess_Order_Blood_Request;

/**
 * Created by Denish on 29-01-2019.
 */

public class Patient_Order_Blood_ViewPage_Adpter extends FragmentStatePagerAdapter {


    int tabcount;
    Context context;
    String patient_id;

    public Patient_Order_Blood_ViewPage_Adpter(FragmentManager fm, int tabcount, Context context, String patient_id) {

        super(fm);
        this.tabcount=tabcount;
        this.context=context;
        this.patient_id=patient_id;
    }


    @Override
    public int getCount() {

        return tabcount;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                Patient_Pending_Order_Blood_Request patient_pending_order_blood_request =new Patient_Pending_Order_Blood_Request(context,patient_id);
                return patient_pending_order_blood_request;

            case 1:
                Patient_sucess_Order_Blood_Request patient_sucess_order_blood_request=new Patient_sucess_Order_Blood_Request(context,patient_id);
                return patient_sucess_order_blood_request;


            case 2:
                Patient_reject_Order_Blood_Request patient_reject_order_blood_request=new Patient_reject_Order_Blood_Request(context,patient_id);
                return patient_reject_order_blood_request;


        }

        return null;
    }
}
