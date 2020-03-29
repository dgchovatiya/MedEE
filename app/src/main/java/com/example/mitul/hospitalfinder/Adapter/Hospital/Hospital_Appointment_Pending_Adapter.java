package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Hospital.Hospital_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 13-03-2019.
 */

public class Hospital_Appointment_Pending_Adapter extends RecyclerView.Adapter<Hospital_Appointment_Pending_Adapter.Holder> {
    Context context;
    ArrayList<Hospital_Appointment> hospital_appointments_pending_list;
    LayoutInflater inflater;
    String apointment_id;
    DeleteCancleRqeuest deleteCancleRqeuest;

    public Hospital_Appointment_Pending_Adapter(Context context, ArrayList<Hospital_Appointment> hospital_appointments_pending_list, DeleteCancleRqeuest deleteCancleRqeuest) {
        this.context = context;
        this.hospital_appointments_pending_list = hospital_appointments_pending_list;
        inflater = LayoutInflater.from(context);
        this.deleteCancleRqeuest = deleteCancleRqeuest;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pending_appointment_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();
        Log.e("set", "Adapter");
        holder.hs_ap_pen_hname.setText(hospital_appointments_pending_list.get(position).getPatinet_name().toString());
        holder.hs_ap_pen_hmo.setText(hospital_appointments_pending_list.get(position).getMobile_number().toString());
        holder.hs_ap_pen_dname.setText(hospital_appointments_pending_list.get(position).getDocter_name().toString());
        holder.hs_ap_pen_date.setText(hospital_appointments_pending_list.get(position).getDate().toString());
        holder.hs_ap_pen_time_.setText(hospital_appointments_pending_list.get(position).getTime().toString());

        holder.hs_op_send_docter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointment_id = hospital_appointments_pending_list.get(position).getAppointment_id().toString().trim();

                Log.e("order_id", apointment_id);

                deleteCancleRqeuest.deletedata(position, apointment_id);

            }
        });


    }

    @Override
    public int getItemCount() {
        return hospital_appointments_pending_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_ap_pen_hname, hs_ap_pen_hmo, hs_ap_pen_dname, hs_ap_pen_date, hs_ap_pen_time_;
        Button hs_op_send_docter;

        public Holder(View itemView) {
            super(itemView);
            hs_ap_pen_hname = (TextView) itemView.findViewById(R.id.hs_ap_pen_hname);
            hs_ap_pen_hmo = (TextView) itemView.findViewById(R.id.hs_ap_pen_hmo);
            hs_ap_pen_dname = (TextView) itemView.findViewById(R.id.hs_ap_pen_dname);
            hs_ap_pen_date = (TextView) itemView.findViewById(R.id.hs_ap_pen_date);
            hs_ap_pen_time_ = (TextView) itemView.findViewById(R.id.hs_ap_pen_time_);

            hs_op_send_docter = (Button) itemView.findViewById(R.id.hs_op_send_docter);


        }
    }

    public interface DeleteCancleRqeuest {

        public void deletedata(int position, String apointment_id);


    }
}
