package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Appointment;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Patient_Login1_Appointment_sucess_Adapter extends RecyclerView.Adapter<Patient_Login1_Appointment_sucess_Adapter.Holder>  {
    Context context;
    ArrayList<Patient_Appointment> patient_appointments_sucess_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;

    public Patient_Login1_Appointment_sucess_Adapter(Context context, ArrayList<Patient_Appointment>patient_appointments_sucess_list) {

        this.context = context;
        this.patient_appointments_sucess_list = patient_appointments_sucess_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sucess_appointment_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.pt_ap_suc_hname.setText(patient_appointments_sucess_list.get(position).getName().toString());
        holder.pt_ap_suc_hmo.setText(patient_appointments_sucess_list.get(position).getMobile_number().toString());
        holder.pt_ap_suc_dname.setText(patient_appointments_sucess_list.get(position).getDocter_name().toString());
        holder.pt_ap_suc_date.setText(patient_appointments_sucess_list.get(position).getDate().toString());
        holder.pt_ap_suc_time_.setText(patient_appointments_sucess_list.get(position).getTime().toString());



    }

    @Override
    public int getItemCount() {
        return patient_appointments_sucess_list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_ap_suc_hname, pt_ap_suc_hmo, pt_ap_suc_dname, pt_ap_suc_date, pt_ap_suc_time_;

        public Holder(View itemView) {
            super(itemView);
            pt_ap_suc_hname = (TextView) itemView.findViewById(R.id.pt_ap_suc_hname);
            pt_ap_suc_hmo = (TextView) itemView.findViewById(R.id.pt_ap_suc_hmo);
            pt_ap_suc_dname = (TextView) itemView.findViewById(R.id.pt_ap_suc_dname);
            pt_ap_suc_date = (TextView) itemView.findViewById(R.id.pt_ap_suc_date);
            pt_ap_suc_time_ = (TextView) itemView.findViewById(R.id.pt_ap_suc_time_);


        }
    }

}
