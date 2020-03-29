package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Hospital.Hospital_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Appointment;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Hospital_Appointment_sucess_Adapter extends RecyclerView.Adapter<Hospital_Appointment_sucess_Adapter.Holder>  {
    Context context;
    ArrayList<Hospital_Appointment> hospital_appointments_sucess_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;

    public Hospital_Appointment_sucess_Adapter(Context context, ArrayList<Hospital_Appointment>hospital_appointments_sucess_list) {

        this.context = context;
        this.hospital_appointments_sucess_list = hospital_appointments_sucess_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sucess_appointment_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.hs_ap_suc_hname.setText(hospital_appointments_sucess_list.get(position).getPatinet_name().toString());
        holder.hs_ap_suc_hmo.setText(hospital_appointments_sucess_list.get(position).getMobile_number().toString());
        holder.hs_ap_suc_dname.setText(hospital_appointments_sucess_list.get(position).getDocter_name().toString());
        holder.hs_ap_suc_date.setText(hospital_appointments_sucess_list.get(position).getDate().toString());
        holder.hs_ap_suc_time_.setText(hospital_appointments_sucess_list.get(position).getTime().toString());



    }

    @Override
    public int getItemCount() {
        return hospital_appointments_sucess_list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_ap_suc_hname, hs_ap_suc_hmo, hs_ap_suc_dname, hs_ap_suc_date, hs_ap_suc_time_;
        public Holder(View itemView) {
            super(itemView);
            hs_ap_suc_hname = (TextView) itemView.findViewById(R.id.hs_ap_suc_hname);
            hs_ap_suc_hmo = (TextView) itemView.findViewById(R.id.hs_ap_suc_hmo);
            hs_ap_suc_dname = (TextView) itemView.findViewById(R.id.hs_ap_suc_dname);
            hs_ap_suc_date = (TextView) itemView.findViewById(R.id.hs_ap_suc_date);
            hs_ap_suc_time_ = (TextView) itemView.findViewById(R.id.hs_ap_suc_time_);


        }
    }

}
