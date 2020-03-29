package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem1;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem1;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Patient_Order_Blood_reject_Adapter extends RecyclerView.Adapter<Patient_Order_Blood_reject_Adapter.Holder>   {
    Context context;
    ArrayList<PatientOrderBlooditem1> patient_blood_order_reject_list;
    LayoutInflater inflater;
    public Patient_Order_Blood_reject_Adapter(Context context, ArrayList<PatientOrderBlooditem1> patient_blood_order_reject_list) {

        this.context = context;
        this.patient_blood_order_reject_list = patient_blood_order_reject_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.reject_order_blood_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.pt_od_bd_rej_hname.setText(patient_blood_order_reject_list.get(position).getHospital_name().toString());
        holder.pt_od_bd_rej_hmo.setText(patient_blood_order_reject_list.get(position).getHospitalmob().toString());
        holder.pt_od_bd_rej_bg.setText(patient_blood_order_reject_list.get(position).getBloodgroup().toString());
        holder.pt_od_bd_rej_bq.setText(patient_blood_order_reject_list.get(position).getQuantity().toString());
        holder.pt_od_bd_rej_date.setText(patient_blood_order_reject_list.get(position).getBookingdate().toString());
        holder.pt_od_bd_rej_reason.setText(patient_blood_order_reject_list.get(position).getReason().toString());
    }

    @Override
    public int getItemCount() {
        return patient_blood_order_reject_list.size() ;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_od_bd_rej_hname, pt_od_bd_rej_hmo, pt_od_bd_rej_bg,
                pt_od_bd_rej_bq, pt_od_bd_rej_date,pt_od_bd_rej_reason;

        public Holder(View itemView) {
            super(itemView);
            pt_od_bd_rej_hname = (TextView) itemView.findViewById(R.id.pt_od_bd_rej_hname);
            pt_od_bd_rej_hmo = (TextView) itemView.findViewById(R.id.pt_od_bd_rej_hmo);
            pt_od_bd_rej_bg = (TextView) itemView.findViewById(R.id.pt_od_bd_rej_bg);
            pt_od_bd_rej_bq = (TextView) itemView.findViewById(R.id.pt_od_bd_rej_bq);
            pt_od_bd_rej_date = (TextView) itemView.findViewById(R.id.pt_od_bd_rej_date);
            pt_od_bd_rej_reason=(TextView)itemView.findViewById(R.id.pt_od_bd_rej_reason);


        }
    }
}
