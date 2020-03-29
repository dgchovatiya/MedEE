package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem1;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Hospital_Order_Medicine_reject_Adapter extends RecyclerView.Adapter<Hospital_Order_Medicine_reject_Adapter.Holder>   {
    Context context;
    ArrayList<OrderMedicineitem1> hospital_medicine_order_reject_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;
    public Hospital_Order_Medicine_reject_Adapter(Context context, ArrayList<OrderMedicineitem1> hospital_medicine_order_reject_list) {

        this.context = context;
        this.hospital_medicine_order_reject_list = hospital_medicine_order_reject_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.reject_order_medicine_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.hs_od_md_rej_pname.setText(hospital_medicine_order_reject_list.get(position).getPatientname().toString());
        holder.hs_od_md_rej_pmo.setText(hospital_medicine_order_reject_list.get(position).getPatientmob().toString());
        holder.hs_od_md_rej_pdob.setText(hospital_medicine_order_reject_list.get(position).getPatientdob().toString());
        holder.hs_od_md_rej_mg.setText(hospital_medicine_order_reject_list.get(position).getMedicine_name().toString());
        holder.hs_od_md_rej_mq.setText(hospital_medicine_order_reject_list.get(position).getQuantity().toString());
        holder.hs_od_md_rej_date.setText(hospital_medicine_order_reject_list.get(position).getBookingdate().toString());
        holder.hs_od_md_rej_reason.setText(hospital_medicine_order_reject_list.get(position).getReason().toString());
    }

    @Override
    public int getItemCount() {
        return hospital_medicine_order_reject_list.size() ;
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_od_md_rej_pname, hs_od_md_rej_pmo, hs_od_md_rej_pdob, hs_od_md_rej_mg,
                hs_od_md_rej_mq, hs_od_md_rej_date,hs_od_md_rej_reason;

        public Holder(View itemView) {
            super(itemView);
            hs_od_md_rej_pname = (TextView) itemView.findViewById(R.id.hs_od_md_rej_pname);
            hs_od_md_rej_pmo = (TextView) itemView.findViewById(R.id.hs_od_md_rej_pmo);
            hs_od_md_rej_pdob = (TextView) itemView.findViewById(R.id.hs_od_md_rej_pdob);
            hs_od_md_rej_mg = (TextView) itemView.findViewById(R.id.hs_od_md_rej_mg);
            hs_od_md_rej_mq = (TextView) itemView.findViewById(R.id.hs_od_md_rej_mq);
            hs_od_md_rej_date = (TextView) itemView.findViewById(R.id.hs_od_md_rej_date);
            hs_od_md_rej_reason=(TextView)itemView.findViewById(R.id.hs_od_md_rej_reason);


        }
    }
}
