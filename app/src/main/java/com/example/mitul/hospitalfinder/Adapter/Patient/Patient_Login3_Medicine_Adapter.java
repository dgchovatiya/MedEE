package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login3_Blood_Book_Activity;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login3_Medicine;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login3_Medicine_Book_Activity;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Login1_Medicine_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Denish on 20-01-2019.
 */

public class Patient_Login3_Medicine_Adapter extends RecyclerView.Adapter<Patient_Login3_Medicine_Adapter.Holder> {

    Context context;
    ArrayList<Item_Medicine> list;
    List<Item_Medicine> modalclasssearchviewList = null;
    LayoutInflater inflater;
    String hospital_id,patient_id,medicine_id, name, stock,description;

    public Patient_Login3_Medicine_Adapter(Context context, ArrayList<Item_Medicine> list, String patient_id) {

        this.context = context;
        // this.list = list;
        this.modalclasssearchviewList = list;
        this.list = new ArrayList<Item_Medicine>();
        this.list.addAll(list);
        this.patient_id=patient_id;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.patient_medicine_details_adpter_layout, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.txt_pt_lg3_med_name.setText(modalclasssearchviewList.get(position).getMedicine_name().toString().trim());
        holder.txt_pt_lg3_med_stock.setText(modalclasssearchviewList.get(position).getStock().toString().trim());

        holder.pt_lg3_medicine_book_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id = modalclasssearchviewList.get(position).getHospital_id().toString().trim();
                medicine_id = modalclasssearchviewList.get(position).getMedicine_id().toString().trim();
                name = modalclasssearchviewList.get(position).getMedicine_name().toString().trim();
                stock = modalclasssearchviewList.get(position).getStock().toString().trim();

                Intent intent = new Intent(context, Patient_Login3_Medicine_Book_Activity.class);
                intent.putExtra("patient_id", patient_id);
                intent.putExtra("hospital_id", hospital_id);
                intent.putExtra("medicine_id", medicine_id);
                intent.putExtra("medicine_name", name);
                intent.putExtra("stock", stock);
                context.startActivity(intent);

            }
        });
        holder.pt_lg3_med_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description=modalclasssearchviewList.get(position).getDescription().toString().trim();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_med_des);
                dialog.setCancelable(true);

                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                final Button dialog_btn_done = (Button) dialog.findViewById(R.id.dialog_btn_done);
                final TextView dialog_txt_des = (TextView) dialog.findViewById(R.id.dialog_txt_des);
                dialog_txt_des.setText(description);
                dialog_btn_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        dialog.dismiss();

                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modalclasssearchviewList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView txt_pt_lg3_med_name, txt_pt_lg3_med_stock,pt_lg3_med_des;
        LinearLayout pt_lg3_medicine_book_item;

        public Holder(View itemView) {
            super(itemView);

            txt_pt_lg3_med_name = (TextView) itemView.findViewById(R.id.txt_pt_lg3_med_name);
            txt_pt_lg3_med_stock = (TextView) itemView.findViewById(R.id.txt_pt_lg3_med_stock);
            pt_lg3_med_des=(TextView)itemView.findViewById(R.id.pt_lg3_med_des);
            pt_lg3_medicine_book_item = (LinearLayout) itemView.findViewById(R.id.pt_lg3_medicine_book_item);

        }
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        modalclasssearchviewList.clear();
        if (charText.length() == 0) {
            modalclasssearchviewList.addAll(list);
        } else {
            for (Item_Medicine wp : list) {
                if (wp.getMedicine_name().toLowerCase(Locale.getDefault()).contains(charText)) {

                    modalclasssearchviewList.add(wp);

                }
            }
        }
        notifyDataSetChanged();
    }
}
