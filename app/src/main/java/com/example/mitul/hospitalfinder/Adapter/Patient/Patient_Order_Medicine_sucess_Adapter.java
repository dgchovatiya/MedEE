package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderMedicineitem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Patient_Order_Medicine_sucess_Adapter extends RecyclerView.Adapter<Patient_Order_Medicine_sucess_Adapter.Holder>  {
    Context context;
    ArrayList<PatientOrderMedicineitem> patient_medicine_order_sucess_list;
    LayoutInflater inflater;
    String order_id, blood_id, stock, reason;

    public Patient_Order_Medicine_sucess_Adapter(Context context, ArrayList<PatientOrderMedicineitem>patient_medicine_order_sucess_list) {

        this.context = context;
        this.patient_medicine_order_sucess_list = patient_medicine_order_sucess_list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.sucess_order_medicine_request_inner_design1, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.pt_od_md_suc_hname.setText(patient_medicine_order_sucess_list.get(position).getHospital_name().toString());
        holder.pt_od_md_suc_hmo.setText(patient_medicine_order_sucess_list.get(position).getHospitalmob().toString());
        holder.pt_od_md_suc_mname.setText(patient_medicine_order_sucess_list.get(position).getMedcine_name().toString());
        holder.pt_od_md_suc_mq.setText(patient_medicine_order_sucess_list.get(position).getQuantity().toString());
        holder.pt_od_md_suc_date.setText(patient_medicine_order_sucess_list.get(position).getBookingdate().toString());
       /* holder.hs_od_bd_suc_reject.setOnClickListener(new View.OnClickListener() {
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
        });*/

    }

    @Override
    public int getItemCount() {
        return patient_medicine_order_sucess_list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_od_md_suc_hname, pt_od_md_suc_hmo, pt_od_md_suc_mname, pt_od_md_suc_mq, pt_od_md_suc_date;
        Button pt_od_bd_suc_cancle;

        public Holder(View itemView) {
            super(itemView);
            pt_od_md_suc_hname = (TextView) itemView.findViewById(R.id.pt_od_md_suc_hname);
            pt_od_md_suc_hmo = (TextView) itemView.findViewById(R.id.pt_od_md_suc_hmo);
            pt_od_md_suc_mname = (TextView) itemView.findViewById(R.id.pt_od_md_suc_mname);
            pt_od_md_suc_mq = (TextView) itemView.findViewById(R.id.pt_od_md_suc_mq);
            pt_od_md_suc_date = (TextView) itemView.findViewById(R.id.pt_od_md_suc_date);

            pt_od_bd_suc_cancle = (Button) itemView.findViewById(R.id.pt_od_md_suc_cancle);

        }
    }
    public interface DeleteRejectedRqeuest {

        public void deletedata1(int position, String order_id, String blood_id, String stock, String Reason);


    }
}
