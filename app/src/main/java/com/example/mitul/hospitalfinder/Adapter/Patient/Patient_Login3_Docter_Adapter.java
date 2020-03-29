package com.example.mitul.hospitalfinder.Adapter.Patient;

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
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login3_Appointment_Book_Activity;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Login1_Docter1_Adapter;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item2;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 06-03-2019.
 */

public class Patient_Login3_Docter_Adapter  extends RecyclerView.Adapter<Patient_Login3_Docter_Adapter.Holder>  {

    Context context;
    ArrayList<Admin_item2> list;
    LayoutInflater inflater;
    String fname,mname,lname,qualification,mo,category,image,image1;
    String hospital_id,docter_id,patient_id;

    public Patient_Login3_Docter_Adapter(Context context, ArrayList<Admin_item2> list, String hospital_id,String patient_id) {

        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.hospital_id=hospital_id;
        this.patient_id=patient_id;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.patient_login3_docter1_adapterlayout, parent, false);

  Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.pt_lg3_ds1_fname.setText(list.get(position).getFirst_name().toString().trim());
        holder.pt_lg3_ds1_mname.setText(list.get(position).getMiddle_name().toString().trim());
        holder.pt_lg3_ds1_lname.setText(list.get(position).getLast_name().toString().trim());
        holder.pt_lg3_ds1_qualification.setText(list.get(position).getQualification().toString().trim());
        holder.pt_lg3_ds1_mo.setText(list.get(position).getMobilenumber().toString().trim());
        holder.pt_lg3_ds1_category.setText(list.get(position).getCategory().toString().trim());
        image = list.get(position).getDocter_image().toString();
        Log.e("image", image);
        Glide.with(context).load(image).into(holder.iv_pt_lg3_docter1);
        holder.pt_lg3_ds1_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docter_id=list.get(position).getDocter_id().toString().trim();
                fname=holder.pt_lg3_ds1_fname.getText().toString().trim();
                mname=holder.pt_lg3_ds1_mname.getText().toString().trim();
                lname=holder.pt_lg3_ds1_lname.getText().toString().trim();
                qualification=holder.pt_lg3_ds1_qualification.getText().toString().trim();
                mo=holder.pt_lg3_ds1_mo.getText().toString().trim();
                category=holder.pt_lg3_ds1_category.getText().toString().trim();
                image1=list.get(position).getDocter_image().toString();
                Intent intent=new Intent(context, Patient_Login3_Appointment_Book_Activity.class);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("patient_id",patient_id);
                intent.putExtra("docter_id",docter_id);

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView pt_lg3_ds1_fname, pt_lg3_ds1_mname, pt_lg3_ds1_lname, pt_lg3_ds1_mo, pt_lg3_ds1_qualification,
                pt_lg3_ds1_category, pt_lg3_ds1_book;
        ImageView iv_pt_lg3_docter1;

        public Holder(View itemView) {
            super(itemView);

            pt_lg3_ds1_fname = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_fname);

            pt_lg3_ds1_mname = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_mname);
            pt_lg3_ds1_lname = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_lname);
            pt_lg3_ds1_qualification = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_qualification);
            pt_lg3_ds1_mo = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_mo);
            pt_lg3_ds1_category = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_category);
            pt_lg3_ds1_book = (TextView) itemView.findViewById(R.id.pt_lg3_ds1_book);

            iv_pt_lg3_docter1 = (ImageView) itemView.findViewById(R.id.iv_pt_lg3_docter1);
        }
    }
}
