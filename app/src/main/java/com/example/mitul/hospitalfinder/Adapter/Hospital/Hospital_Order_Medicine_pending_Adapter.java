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
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 02-02-2019.
 */

public class Hospital_Order_Medicine_pending_Adapter extends RecyclerView.Adapter<Hospital_Order_Medicine_pending_Adapter.Holder> {
    Context context;
    ArrayList<OrderMedicineitem> hospital_medicine_order_pending_list;
    LayoutInflater inflater;
    String order_medicine_id, medicine_id, stock, reason;
    DeleteSucessRqeuest deleteSucessRqeuest;
    DeleteRejectedRqeuest deleteRejectedRqeuest;


    public Hospital_Order_Medicine_pending_Adapter(Context context, ArrayList<OrderMedicineitem> hospital_medicine_order_pending_list, DeleteSucessRqeuest deleteSucessRqeuest, DeleteRejectedRqeuest deleteRejectedRqeuest) {
        this.context = context;
        this.hospital_medicine_order_pending_list = hospital_medicine_order_pending_list;
        inflater = LayoutInflater.from(context);
        this.deleteSucessRqeuest = deleteSucessRqeuest;
        this.deleteRejectedRqeuest = deleteRejectedRqeuest;
    }

    @Override
    public Hospital_Order_Medicine_pending_Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pending_order_medicine_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Hospital_Order_Medicine_pending_Adapter.Holder holder, final int position) {
        AppConstants.dissmissDialog();
        holder.hs_od_md_pen_pname.setText(hospital_medicine_order_pending_list.get(position).getPatientname().toString());
        holder.hs_od_md_pen_pmo.setText(hospital_medicine_order_pending_list.get(position).getPatientmob().toString());
        holder.hs_od_md_pen_pdob.setText(hospital_medicine_order_pending_list.get(position).getPatientdob().toString());
        holder.hs_od_md_pen_mg.setText(hospital_medicine_order_pending_list.get(position).getMedicine_name().toString());
        holder.hs_od_md_pen_mq.setText(hospital_medicine_order_pending_list.get(position).getQuantity().toString());
        holder.hs_od_md_pen_date.setText(hospital_medicine_order_pending_list.get(position).getBookingdate().toString());
        holder.hs_od_md_pen_sucess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_medicine_id = hospital_medicine_order_pending_list.get(position).getOrder_medicine_id().toString().trim();
                medicine_id = hospital_medicine_order_pending_list.get(position).getMedicine_id().toString().trim();
                stock = hospital_medicine_order_pending_list.get(position).getQuantity().toString().trim();
                Log.e("order_id", order_medicine_id);
                Log.e("blood_id", medicine_id);
                Log.e("stock", stock);
                deleteSucessRqeuest.deletedata(position, order_medicine_id, medicine_id, stock);

            }
        });
        holder.hs_od_md_pen_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                order_medicine_id = hospital_medicine_order_pending_list.get(position).getOrder_medicine_id().toString().trim();
                medicine_id = hospital_medicine_order_pending_list.get(position).getMedicine_id().toString().trim();
                stock = hospital_medicine_order_pending_list.get(position).getQuantity().toString().trim();


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
                        deleteRejectedRqeuest.deletedata1(position, order_medicine_id, medicine_id, reason);

                        dialog.dismiss();

                    }
                });
                dialog.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return hospital_medicine_order_pending_list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_od_md_pen_pname, hs_od_md_pen_pmo, hs_od_md_pen_pdob, hs_od_md_pen_mg, hs_od_md_pen_mq, hs_od_md_pen_date;
        Button hs_od_md_pen_sucess, hs_od_md_pen_reject;

        public Holder(View itemView) {
            super(itemView);
            hs_od_md_pen_pname = (TextView) itemView.findViewById(R.id.hs_od_md_pen_pname);
            hs_od_md_pen_pmo = (TextView) itemView.findViewById(R.id.hs_od_md_pen_pmo);
            hs_od_md_pen_pdob = (TextView) itemView.findViewById(R.id.hs_od_md_pen_pdob);
            hs_od_md_pen_mg = (TextView) itemView.findViewById(R.id.hs_od_md_pen_mg);
            hs_od_md_pen_mq = (TextView) itemView.findViewById(R.id.hs_od_md_pen_mq);
            hs_od_md_pen_date = (TextView) itemView.findViewById(R.id.hs_od_md_pen_date);

            hs_od_md_pen_sucess = (Button) itemView.findViewById(R.id.hs_od_md_pen_sucess);
            hs_od_md_pen_reject = (Button) itemView.findViewById(R.id.hs_od_md_pen_reject);


        }
    }

    public interface DeleteSucessRqeuest {

        public void deletedata(int position, String order_medicine_id, String medicine_id, String stock);


    }

    public interface DeleteRejectedRqeuest {

        public void deletedata1(int position, String order_medicine_id, String medicine_id, String Reason);


    }
}
