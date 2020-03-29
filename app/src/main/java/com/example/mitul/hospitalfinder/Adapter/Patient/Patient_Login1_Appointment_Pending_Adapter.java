package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by Denish on 13-03-2019.
 */

public class Patient_Login1_Appointment_Pending_Adapter extends RecyclerView.Adapter<Patient_Login1_Appointment_Pending_Adapter.Holder> {
    Context context;
    ArrayList<Patient_Appointment> patient_appointments_pending_list;
    LayoutInflater inflater;
    String apointment_id;
    DeleteCancleRqeuest deleteCancleRqeuest;

    public Patient_Login1_Appointment_Pending_Adapter(Context context, ArrayList<Patient_Appointment> patient_appointments_pending_list, DeleteCancleRqeuest deleteCancleRqeuest) {
        this.context = context;
        this.patient_appointments_pending_list = patient_appointments_pending_list;
        inflater = LayoutInflater.from(context);
        this.deleteCancleRqeuest = deleteCancleRqeuest;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pending_appointment_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.pt_ap_pen_hname.setText(patient_appointments_pending_list.get(position).getName().toString());
        holder.pt_ap_pen_hmo.setText(patient_appointments_pending_list.get(position).getMobile_number().toString());
        holder.pt_ap_pen_dname.setText(patient_appointments_pending_list.get(position).getDocter_name().toString());
        holder.pt_ap_pen_date.setText(patient_appointments_pending_list.get(position).getDate().toString());
        holder.pt_ap_pen_time_.setText(patient_appointments_pending_list.get(position).getTime().toString());

        holder.pt_op_pen_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointment_id = patient_appointments_pending_list.get(position).getAppointment_id().toString().trim();

                Log.e("order_id", apointment_id);

                deleteCancleRqeuest.deletedata(position, apointment_id);

            }
        });


    }

    @Override
    public int getItemCount() {
        return patient_appointments_pending_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_ap_pen_hname, pt_ap_pen_hmo, pt_ap_pen_dname, pt_ap_pen_date, pt_ap_pen_time_;
        Button pt_op_pen_cancle;

        public Holder(View itemView) {
            super(itemView);
            pt_ap_pen_hname = (TextView) itemView.findViewById(R.id.pt_ap_pen_hname);
            pt_ap_pen_hmo = (TextView) itemView.findViewById(R.id.pt_ap_pen_hmo);
            pt_ap_pen_dname = (TextView) itemView.findViewById(R.id.pt_ap_pen_dname);
            pt_ap_pen_date = (TextView) itemView.findViewById(R.id.pt_ap_pen_date);
            pt_ap_pen_time_ = (TextView) itemView.findViewById(R.id.pt_ap_pen_time_);

            pt_op_pen_cancle = (Button) itemView.findViewById(R.id.pt_op_pen_cancle);


        }
    }

    public interface DeleteCancleRqeuest {

        public void deletedata(int position, String apointment_id);


    }
}
