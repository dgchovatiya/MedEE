package com.example.mitul.hospitalfinder.Adapter.Hospital;

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

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Medicine_DescriptionActivity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Medicine1_Update_Activity;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Denish on 15-01-2019.
 */

public class Hospital_Login1_Medicine_Adapter extends RecyclerView.Adapter<Hospital_Login1_Medicine_Adapter.Holder> {
    Context context;
    ArrayList<Item_Medicine> list;
    LayoutInflater inflater;
    List<Item_Medicine> modalclasssearchviewList = null;

    String hospital_id,medicine_id, name, stock,description,d1;

    public Hospital_Login1_Medicine_Adapter(Context context, ArrayList<Item_Medicine> list) {
        this.context = context;
        this.modalclasssearchviewList = list;
        this.list = new ArrayList<Item_Medicine>();
        this.list.addAll(list);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public Hospital_Login1_Medicine_Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.hospital_medicine_details_adpter_layout, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Hospital_Login1_Medicine_Adapter.Holder holder, final int position) {

        holder.txt_hs_lg1_med_name.setText(modalclasssearchviewList.get(position).getMedicine_name().toString().trim());
        holder.txt_hs_lg1_med_stock.setText(modalclasssearchviewList.get(position).getStock().toString().trim());
        holder.hs_lg1_med_edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id=modalclasssearchviewList.get(position).getHospital_id().toString().trim();
                medicine_id=modalclasssearchviewList.get(position).getMedicine_id().toString().trim();
                name=modalclasssearchviewList.get(position).getMedicine_name().toString().trim();
                stock=modalclasssearchviewList.get(position).getStock().toString().trim();

                Intent intent=new Intent(context, Hospital_Medicine1_Update_Activity.class);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("medicine_id",medicine_id);
                intent.putExtra("name",name);
                intent.putExtra("stock",stock);
                context.startActivity(intent);

            }
        });
        holder.hs_lg1_med_des.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id=modalclasssearchviewList.get(position).getHospital_id().toString().trim();
                medicine_id=modalclasssearchviewList.get(position).getMedicine_id().toString().trim();
                description = modalclasssearchviewList.get(position).getDescription().toString().trim();
                Intent intent=new Intent(context, Hospital_Login1_Medicine_DescriptionActivity.class);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("medicine_id",medicine_id);
                intent.putExtra("description",description);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modalclasssearchviewList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txt_hs_lg1_med_name, txt_hs_lg1_med_stock,hs_lg1_med_des;
        LinearLayout hs_lg1_med_edit_item;

        public Holder(View itemView) {
            super(itemView);

            txt_hs_lg1_med_name = (TextView) itemView.findViewById(R.id.txt_hs_lg1_med_name);
            txt_hs_lg1_med_stock = (TextView) itemView.findViewById(R.id.txt_hs_lg1_med_stock);
            hs_lg1_med_edit_item = (LinearLayout) itemView.findViewById(R.id.hs_lg1_med_edit_item);
            hs_lg1_med_des=(TextView)itemView.findViewById(R.id.hs_lg1_med_des);

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