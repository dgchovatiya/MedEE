package com.example.mitul.hospitalfinder.Adapter.Doctor;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Doctor.Doctor_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 13-03-2019.
 */

public class Doctor_Appointment_Reject_Adapter extends RecyclerView.Adapter<Doctor_Appointment_Reject_Adapter.Holder> {
    Context context;
    ArrayList<Doctor_Appointment> docter_appointments_reject_list;
    LayoutInflater inflater;

    String reason;

    public Doctor_Appointment_Reject_Adapter(Context context, ArrayList<Doctor_Appointment> docter_appointments_reject_list) {
        this.context = context;
        this.docter_appointments_reject_list = docter_appointments_reject_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.reject_appointment_request_inner_design3, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.d_rej_hname.setText(docter_appointments_reject_list.get(position).getHosptial_name().toString());
        holder.d_rej_pname.setText(docter_appointments_reject_list.get(position).getPatient_name().toString());
        holder.d_rej_date.setText(docter_appointments_reject_list.get(position).getDate().toString());
        holder.d_rej_time.setText(docter_appointments_reject_list.get(position).getTime().toString());
        holder.d_rej_reason.setText(docter_appointments_reject_list.get(position).getReason().toString());




    }

    @Override
    public int getItemCount() {
        return docter_appointments_reject_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView d_rej_hname, d_rej_pname, d_rej_date, d_rej_time,d_rej_reason;

        public Holder(View itemView) {
            super(itemView);
            d_rej_hname = (TextView) itemView.findViewById(R.id.d_rej_hname);
            d_rej_pname = (TextView) itemView.findViewById(R.id.d_rej_pname);
            d_rej_date = (TextView) itemView.findViewById(R.id.d_rej_date);
            d_rej_time = (TextView) itemView.findViewById(R.id.d_rej_time_);
            d_rej_reason=(TextView)itemView.findViewById(R.id.d_rej_reason);


        }
    }




}
