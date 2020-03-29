package com.example.mitul.hospitalfinder.Adapter.Doctor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mitul.hospitalfinder.Fragment.Doctor.Doctor_Pending_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Doctor.Doctor_Reject_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Doctor.Doctor_Sucess_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_Pending_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_reject_Appointment_Request;
import com.example.mitul.hospitalfinder.Fragment.Hospital.Hospital_sucess_Appointment_Request;

/**
 * Created by Denish on 29-01-2019.
 */

public class Doctor_Appointment_ViewPage_Adpter extends FragmentStatePagerAdapter {


    int tabcount;
    Context context;
    String doctor_id;

    public Doctor_Appointment_ViewPage_Adpter(FragmentManager fm, int tabcount, Context context, String doctor_id) {

        super(fm);
        this.tabcount=tabcount;
        this.context=context;
        this.doctor_id=doctor_id;
    }


    @Override
    public int getCount() {

        return tabcount;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:
                Doctor_Pending_Appointment_Request doctor_pending_appointment_request =new Doctor_Pending_Appointment_Request(context,doctor_id);
                return doctor_pending_appointment_request;

            case 1:
                Doctor_Sucess_Appointment_Request doctor_sucess_appointment_request=new Doctor_Sucess_Appointment_Request(context,doctor_id);
                return doctor_sucess_appointment_request;


            case 2:
                Doctor_Reject_Appointment_Request doctor_reject_appointment_request=new Doctor_Reject_Appointment_Request(context,doctor_id);
                return doctor_reject_appointment_request;


        }

        return null;
    }
}
