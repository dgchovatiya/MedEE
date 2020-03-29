package com.example.mitul.hospitalfinder.Adapter.Hospital;

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
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Hospital_Order_Blood_sucess_Adapter extends RecyclerView.Adapter<Hospital_Order_Blood_sucess_Adapter.Holder>  {
    Context context;
    ArrayList<OrderBlooditem> hospital_blood_order_sucess_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;
    DeleteRejectedRqeuest deleteRejectedRqeuest;

    public Hospital_Order_Blood_sucess_Adapter(Context context, ArrayList<OrderBlooditem> hospital_blood_order_sucess_list, DeleteRejectedRqeuest deleteRejectedRqeuest) {

        this.context = context;
        this.hospital_blood_order_sucess_list = hospital_blood_order_sucess_list;
        inflater = LayoutInflater.from(context);
        this.deleteRejectedRqeuest=deleteRejectedRqeuest;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sucess_order_blood_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.hs_od_bd_suc_pname.setText(hospital_blood_order_sucess_list.get(position).getPatientname().toString());
        holder.hs_od_bd_suc_pmo.setText(hospital_blood_order_sucess_list.get(position).getPatientmob().toString());
        holder.hs_od_bd_suc_pdob.setText(hospital_blood_order_sucess_list.get(position).getPatientdob().toString());
        holder.hs_od_bd_suc_bg.setText(hospital_blood_order_sucess_list.get(position).getBloodgroup().toString());
        holder.hs_od_bd_suc_bq.setText(hospital_blood_order_sucess_list.get(position).getQuantity().toString());
        holder.hs_od_bd_suc_date.setText(hospital_blood_order_sucess_list.get(position).getBookingdate().toString());
        holder.hs_od_bd_suc_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                order_id = hospital_blood_order_sucess_list.get(position).getOrder_id().toString().trim();
                blood_id = hospital_blood_order_sucess_list.get(position).getBlood_id().toString().trim();
                stock = hospital_blood_order_sucess_list.get(position).getQuantity().toString().trim();


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
                        reason=dialog_et_rej.getText().toString().trim();
                        Log.e("reason",reason);
                        deleteRejectedRqeuest.deletedata1(position, order_id, blood_id,stock, reason);

                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return hospital_blood_order_sucess_list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_od_bd_suc_pname, hs_od_bd_suc_pmo, hs_od_bd_suc_pdob, hs_od_bd_suc_bg, hs_od_bd_suc_bq, hs_od_bd_suc_date;
        Button hs_od_bd_suc_reject;

        public Holder(View itemView) {
            super(itemView);
            hs_od_bd_suc_pname = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_pname);
            hs_od_bd_suc_pmo = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_pmo);
            hs_od_bd_suc_pdob = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_pdob);
            hs_od_bd_suc_bg = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_bg);
            hs_od_bd_suc_bq = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_bq);
            hs_od_bd_suc_date = (TextView) itemView.findViewById(R.id.hs_od_bd_sec_date);

            hs_od_bd_suc_reject = (Button) itemView.findViewById(R.id.hs_od_bd_sec_reject);


        }
    }
    public interface DeleteRejectedRqeuest {

        public void deletedata1(int position, String order_id,String blood_id,String stock, String Reason);


    }
}
