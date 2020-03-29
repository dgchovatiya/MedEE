package com.example.mitul.hospitalfinder.Adapter.Patient;

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

import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Patient_Order_Blood_pending_Adapter extends RecyclerView.Adapter<Patient_Order_Blood_pending_Adapter.Holder> {
    Context context;
    ArrayList<PatientOrderBlooditem> patient_blood_order_pending_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;
    DeleteCancleRqeuest deleteCancleRqeuest;


    public Patient_Order_Blood_pending_Adapter(Context context, ArrayList<PatientOrderBlooditem> patient_blood_order_pending_list, DeleteCancleRqeuest deleteCancleRqeuest) {
        this.context = context;
        this.patient_blood_order_pending_list = patient_blood_order_pending_list;
        inflater = LayoutInflater.from(context);
        this.deleteCancleRqeuest = deleteCancleRqeuest;

    }

    @Override
    public Patient_Order_Blood_pending_Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pending_order_blood_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Patient_Order_Blood_pending_Adapter.Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.pt_od_bd_pen_hname.setText(patient_blood_order_pending_list.get(position).getHospital_name().toString());
        holder.pt_od_bd_pen_hmo.setText(patient_blood_order_pending_list.get(position).getHospitalmob().toString());
        holder.pt_od_bd_pen_bg.setText(patient_blood_order_pending_list.get(position).getBloodgroup().toString());
        holder.pt_od_bd_pen_bq.setText(patient_blood_order_pending_list.get(position).getQuantity().toString());
        holder.pt_od_bd_pen_date.setText(patient_blood_order_pending_list.get(position).getBookingdate().toString());
        holder.pt_od_bd_pen_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_id = patient_blood_order_pending_list.get(position).getOrder_id().toString().trim();
                blood_id = patient_blood_order_pending_list.get(position).getBlood_id().toString().trim();
                stock = patient_blood_order_pending_list.get(position).getQuantity().toString().trim();
                Log.e("order_id", order_id);

                deleteCancleRqeuest.deletedata(position, order_id, blood_id, stock);

            }
        });



    }

    @Override
    public int getItemCount() {
        return patient_blood_order_pending_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_od_bd_pen_hname, pt_od_bd_pen_hmo, pt_od_bd_pen_bg, pt_od_bd_pen_bq,pt_od_bd_pen_date;
        Button  pt_od_bd_pen_cancle;

        public Holder(View itemView) {
            super(itemView);
            pt_od_bd_pen_hname = (TextView) itemView.findViewById(R.id.pt_od_bd_pen_hname);
            pt_od_bd_pen_hmo = (TextView) itemView.findViewById(R.id.pt_od_bd_pen_hmo);
            pt_od_bd_pen_bg = (TextView) itemView.findViewById(R.id.pt_od_bd_pen_bg);
            pt_od_bd_pen_bq = (TextView) itemView.findViewById(R.id.pt_od_bd_pen_bq);
            pt_od_bd_pen_date = (TextView) itemView.findViewById(R.id.pt_od_bd_pen_date);

            pt_od_bd_pen_cancle = (Button) itemView.findViewById(R.id.pt_od_bd_pen_cancle);


        }
    }

    public interface DeleteCancleRqeuest {

        public void deletedata(int position, String order_id, String blood_id, String stock);


    }


}
