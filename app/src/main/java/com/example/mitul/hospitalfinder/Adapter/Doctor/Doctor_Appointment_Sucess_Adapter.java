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

public class Doctor_Appointment_Sucess_Adapter extends RecyclerView.Adapter<Doctor_Appointment_Sucess_Adapter.Holder> {
    Context context;
    ArrayList<Doctor_Appointment> docter_appointments_sucess_list;
    LayoutInflater inflater;
    String apointment_id;
    DeleteRejectedRqeuest deleteRejectedRqeuest;

    String reason;

    public Doctor_Appointment_Sucess_Adapter(Context context, ArrayList<Doctor_Appointment> docter_appointments_sucess_list, DeleteRejectedRqeuest deleteRejectedRqeuest) {
        this.context = context;
        this.docter_appointments_sucess_list = docter_appointments_sucess_list;
        inflater = LayoutInflater.from(context);
        this.deleteRejectedRqeuest = deleteRejectedRqeuest;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sucess_appointment_request_inner_design3, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.d_suc_hname.setText(docter_appointments_sucess_list.get(position).getHosptial_name().toString());
        holder.d_suc_pname.setText(docter_appointments_sucess_list.get(position).getPatient_name().toString());
        holder.d_suc_date.setText(docter_appointments_sucess_list.get(position).getDate().toString());
        holder.d_suc_time_.setText(docter_appointments_sucess_list.get(position).getTime().toString());

        holder.d_suc_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointment_id = docter_appointments_sucess_list.get(position).getAppointment_id().toString().trim();


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



    }

    @Override
    public int getItemCount() {
        return docter_appointments_sucess_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView d_suc_hname, d_suc_pname, d_suc_date, d_suc_time_;
        Button d_suc_cancle;

        public Holder(View itemView) {
            super(itemView);
            d_suc_hname = (TextView) itemView.findViewById(R.id.d_suc_hname);
            d_suc_pname = (TextView) itemView.findViewById(R.id.d_suc_pname);
            d_suc_date = (TextView) itemView.findViewById(R.id.d_suc_date);
            d_suc_time_ = (TextView) itemView.findViewById(R.id.d_suc_time_);

            d_suc_cancle = (Button) itemView.findViewById(R.id.d_suc_cancle);


        }
    }

    public interface DeleteRejectedRqeuest {

        public void deletedata1(String apointment_id, String reason);


    }


}
