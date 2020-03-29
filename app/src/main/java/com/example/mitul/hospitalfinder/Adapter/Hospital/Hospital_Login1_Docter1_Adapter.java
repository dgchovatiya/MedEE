package com.example.mitul.hospitalfinder.Adapter.Hospital;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Docter1_UpdateActivity;
import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_docter_Adpter;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item2;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 15-01-2019.
 */

public class Hospital_Login1_Docter1_Adapter extends RecyclerView.Adapter<Hospital_Login1_Docter1_Adapter.Holder> {
    Context context;
    ArrayList<Admin_item2> list;
    LayoutInflater inflater;
    String fname,mname,lname,qualification,mo,category,image,image1;
    String hospital_id,docter_id;

    public Hospital_Login1_Docter1_Adapter(Context context, ArrayList<Admin_item2> list, String hospital_id) {

        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.hospital_id=hospital_id;
    }


    @Override
    public Hospital_Login1_Docter1_Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.hospital_login1_docter1_adapterlayout, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Hospital_Login1_Docter1_Adapter.Holder holder, final int position) {
        holder.hs_lg1_ds1_fname.setText(list.get(position).getFirst_name().toString().trim());
        holder.hs_lg1_ds1_mname.setText(list.get(position).getMiddle_name().toString().trim());
        holder.hs_lg1_ds1_lname.setText(list.get(position).getLast_name().toString().trim());
        holder.hs_lg1_ds1_qualification.setText(list.get(position).getQualification().toString().trim());
        holder.hs_lg1_ds1_mo.setText(list.get(position).getMobilenumber().toString().trim());
        holder.hs_lg1_ds1_category.setText(list.get(position).getCategory().toString().trim());
        image = list.get(position).getDocter_image().toString();
        Log.e("image", image);
        Glide.with(context).load(image).into(holder.iv_hs_lg1_docter1);
        holder.hs_lg1_ds1_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docter_id=list.get(position).getDocter_id().toString().trim();
                fname=holder.hs_lg1_ds1_fname.getText().toString().trim();
                mname=holder.hs_lg1_ds1_mname.getText().toString().trim();
                lname=holder.hs_lg1_ds1_lname.getText().toString().trim();
                qualification=holder.hs_lg1_ds1_qualification.getText().toString().trim();
                mo=holder.hs_lg1_ds1_mo.getText().toString().trim();
                category=holder.hs_lg1_ds1_category.getText().toString().trim();
                image1=list.get(position).getDocter_image().toString();
                Intent intent=new Intent(context, Hospital_Login1_Docter1_UpdateActivity.class);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("docter_id",docter_id);
                intent.putExtra("first_name",fname);
                intent.putExtra("middle_name",mname);
                intent.putExtra("last_name",lname);
                intent.putExtra("qualification",qualification);
                intent.putExtra("mobile_number",mo);
                intent.putExtra("image",image1);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView hs_lg1_ds1_fname, hs_lg1_ds1_mname, hs_lg1_ds1_lname, hs_lg1_ds1_mo, hs_lg1_ds1_qualification,
                hs_lg1_ds1_category, hs_lg1_ds1_edit;
        ImageView iv_hs_lg1_docter1;

        public Holder(View itemView) {
            super(itemView);

            hs_lg1_ds1_fname = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_fname);

            hs_lg1_ds1_mname = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_mname);
            hs_lg1_ds1_lname = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_lname);
            hs_lg1_ds1_qualification = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_qualification);
            hs_lg1_ds1_mo = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_mo);
            hs_lg1_ds1_category = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_category);
            hs_lg1_ds1_edit = (TextView) itemView.findViewById(R.id.hs_lg1_ds1_edit);

            iv_hs_lg1_docter1 = (ImageView) itemView.findViewById(R.id.iv_hs_lg1_docter1);
        }
    }
}
