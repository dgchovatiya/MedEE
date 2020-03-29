package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_Pending_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_reject_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_reject_Order_Blood_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_sucess_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Patient.Patient_sucess_Order_Blood_Request;

/**
 * Created by Denish on 06-03-2019.
 */

public class Patient_Appointment_ViewPage_Adapter extends FragmentStatePagerAdapter {

    int tabcount;
    Context context;
    String patient_id;

    public Patient_Appointment_ViewPage_Adapter(FragmentManager fm, int tabcount, Context context, String patient_id) {

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
                Patient_Pending_Appointment_Request patient_pending_appointment_request =new Patient_Pending_Appointment_Request(context,patient_id);
                return patient_pending_appointment_request;


            case 1:
                Patient_sucess_Appointment_Request patient_sucess_appointment_request=new Patient_sucess_Appointment_Request(context,patient_id);
                return patient_sucess_appointment_request;



            case 2:
                Patient_reject_Appointment_Request patient_reject_appointment_request=new Patient_reject_Appointment_Request(context,patient_id);
                return patient_reject_appointment_request;


        }

        return null;
    }
}
