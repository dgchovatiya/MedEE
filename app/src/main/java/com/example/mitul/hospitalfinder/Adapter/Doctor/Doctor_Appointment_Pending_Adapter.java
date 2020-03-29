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

import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Doctor.Doctor_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Appointment;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 13-03-2019.
 */

public class Doctor_Appointment_Pending_Adapter extends RecyclerView.Adapter<Doctor_Appointment_Pending_Adapter.Holder> {
    Context context;
    ArrayList<Doctor_Appointment> docter_appointments_pending_list;
    LayoutInflater inflater;
    String apointment_id;
    DeleteRejectedRqeuest deleteRejectedRqeuest;
    DeleteSucessRqeuest deleteSucessRqeuest;

    String reason;

    public Doctor_Appointment_Pending_Adapter(Context context, ArrayList<Doctor_Appointment> docter_appointments_pending_list, DeleteRejectedRqeuest deleteRejectedRqeuest, DeleteSucessRqeuest deleteSucessRqeuest) {
        this.context = context;
        this.docter_appointments_pending_list = docter_appointments_pending_list;
        inflater = LayoutInflater.from(context);
        this.deleteRejectedRqeuest = deleteRejectedRqeuest;
        this.deleteSucessRqeuest = deleteSucessRqeuest;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pending_appointment_request_inner_design3, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.d_pen_hname.setText(docter_appointments_pending_list.get(position).getHosptial_name().toString());
        holder.d_pen_pname.setText(docter_appointments_pending_list.get(position).getPatient_name().toString());
        holder.d_pen_date.setText(docter_appointments_pending_list.get(position).getDate().toString());
        holder.d_pen_time_.setText(docter_appointments_pending_list.get(position).getTime().toString());

        holder.d_pen_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointment_id = docter_appointments_pending_list.get(position).getAppointment_id().toString().trim();


                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_reject_order_reason);
                dialog.setCancelable(true);

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                final Button dialog_btn_rej = (Button) dialog.findViewById(R.id.dialog_btn_rej);
                final EditText dialog_et_rej = (EditText) dialog.findViewById(R.id.dialog_et_rej);
                dialog_btn_rej.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reason = dialog_et_rej.getText().toString().trim();
                        Log.e("reason", reason);
                        deleteRejectedRqeuest.deletedata1(apointment_id, reason);

                        dialog.dismiss();

                    }
                });
                dialog.show();

                Log.e("order_id", apointment_id);


            }
        });
        holder.d_pen_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointment_id = docter_appointments_pending_list.get(position).getAppointment_id().toString().trim();
                deleteSucessRqeuest.deletedata(apointment_id);

            }
        });


    }

    @Override
    public int getItemCount() {
        return docter_appointments_pending_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView d_pen_hname, d_pen_pname, d_pen_date, d_pen_time_;
        Button d_pen_cancle, d_pen_Confirm;

        public Holder(View itemView) {
            super(itemView);
            d_pen_hname = (TextView) itemView.findViewById(R.id.d_pen_hname);
            d_pen_pname = (TextView) itemView.findViewById(R.id.d_pen_pname);
            d_pen_date = (TextView) itemView.findViewById(R.id.d_pen_date);
            d_pen_time_ = (TextView) itemView.findViewById(R.id.d_pen_time_);

            d_pen_cancle = (Button) itemView.findViewById(R.id.d_pen_cancle);
            d_pen_Confirm = (Button) itemView.findViewById(R.id.d_pen_Confirm);


        }
    }

    public interface DeleteRejectedRqeuest {

        public void deletedata1(String apointment_id, String reason);


    }

    public interface DeleteSucessRqeuest {

        public void deletedata(String apointment_id);


    }
}
