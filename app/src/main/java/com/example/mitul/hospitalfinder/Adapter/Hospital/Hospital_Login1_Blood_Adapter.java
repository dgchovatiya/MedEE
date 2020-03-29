package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Blood_Update_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Medicine1_Update_Activity;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_blood;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 19-01-2019.
 */

public class Hospital_Login1_Blood_Adapter extends RecyclerView.Adapter<Hospital_Login1_Blood_Adapter.Holder> {
    Context context;
    ArrayList<Item_blood> list;
    LayoutInflater inflater;
    String hospital_id,blood_id,blood_group,stock;

    public Hospital_Login1_Blood_Adapter(Context context, ArrayList<Item_blood> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.hospital_blood_details_adpter_layout, parent, false);

       Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.txt_hs_lg1_blood_name.setText(list.get(position).getBlood_group().toString().trim());
        holder.txt_hs_lg1_med_stock.setText(list.get(position).getStock().toString().trim());
        holder.hs_lg1_med_edit_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id=list.get(position).getHospital_id().toString().trim();
                blood_id=list.get(position).getBlood_id().toString().trim();
                blood_group=list.get(position).getBlood_group().toString().trim();
                stock=list.get(position).getStock().toString().trim();

                Intent intent=new Intent(context, Hospital_Login1_Blood_Update_Activity.class);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("blood_id",blood_id);
                intent.putExtra("blood_group",blood_group);
                intent.putExtra("stock",stock);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txt_hs_lg1_blood_name, txt_hs_lg1_med_stock;
        LinearLayout hs_lg1_med_edit_item;

        public Holder(View itemView) {
            super(itemView);

            txt_hs_lg1_blood_name = (TextView) itemView.findViewById(R.id.txt_hs_lg1_blood_name);
            txt_hs_lg1_med_stock = (TextView) itemView.findViewById(R.id.txt_hs_lg1_blood_stock);
            hs_lg1_med_edit_item = (LinearLayout) itemView.findViewById(R.id.hs_lg1_blood_edit_item);

        }
    }
}
