package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Appointment;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Patient_Login1_Appointment_reject_Adapter extends RecyclerView.Adapter<Patient_Login1_Appointment_reject_Adapter.Holder> {
    Context context;
    ArrayList<Patient_Appointment> patient_appointments_reject_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;

    public Patient_Login1_Appointment_reject_Adapter(Context context, ArrayList<Patient_Appointment> patient_appointments_reject_list) {

        this.context = context;
        this.patient_appointments_reject_list = patient_appointments_reject_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reject_appointment_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.pt_ap_rej_hname.setText(patient_appointments_reject_list.get(position).getName().toString());
        holder.pt_ap_rej_hmo.setText(patient_appointments_reject_list.get(position).getMobile_number().toString());
        holder.pt_ap_rej_dname.setText(patient_appointments_reject_list.get(position).getDocter_name().toString());
        holder.pt_ap_rej_date.setText(patient_appointments_reject_list.get(position).getDate().toString());
        holder.pt_ap_rej_time_.setText(patient_appointments_reject_list.get(position).getTime().toString());
        holder.pt_ap_rej_reason.setText(patient_appointments_reject_list.get(position).getReason().toString());


    }

    @Override
    public int getItemCount() {
        return patient_appointments_reject_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_ap_rej_hname, pt_ap_rej_hmo, pt_ap_rej_dname, pt_ap_rej_date, pt_ap_rej_time_, pt_ap_rej_reason;

        public Holder(View itemView) {
            super(itemView);
            pt_ap_rej_hname = (TextView) itemView.findViewById(R.id.pt_ap_rej_hname);
            pt_ap_rej_hmo = (TextView) itemView.findViewById(R.id.pt_ap_rej_hmo);
            pt_ap_rej_dname = (TextView) itemView.findViewById(R.id.pt_ap_rej_dname);
            pt_ap_rej_date = (TextView) itemView.findViewById(R.id.pt_ap_rej_date);
            pt_ap_rej_time_ = (TextView) itemView.findViewById(R.id.pt_ap_rej_time_);
            pt_ap_rej_reason = (TextView) itemView.findViewById(R.id.pt_ap_rej_reason);

        }
    }

}
