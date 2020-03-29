package com.example.mitul.hospitalfinder.Adapter.Patient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login3_Activity;
import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_Sucess_RviewAdpter;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item1;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 12-01-2019.
 */

public class Patient_search_Hospital_listAdpter extends RecyclerView.Adapter<Patient_search_Hospital_listAdpter.Holder> {

    Context context;
    ArrayList<Admin_item1> patient_hospital_list;
    LayoutInflater inflater;
    String hospital_id,category;
    String email_id;
    String patient_id,pcategory;

    public Patient_search_Hospital_listAdpter(Context context, ArrayList<Admin_item1> patient_hospital_list, String emailId, String patient_id,String pcategory) {

        this.context = context;
        this.patient_hospital_list = patient_hospital_list;
        inflater = LayoutInflater.from(context);
        this.email_id=email_id;
        this.patient_id=patient_id;
        this.pcategory=pcategory;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.sucess_admin_innerpage, parent, false);

       Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        AppConstants.dissmissDialog();

        holder.txt_admin_1_hsname.setText(patient_hospital_list.get(position).getHospitalname().toString());
        String image=patient_hospital_list.get(position).getImage().toString();
        holder.txt_admin_1_Category.setText(patient_hospital_list.get(position).getCategory().toString());
        holder.txt_admin_1_Type.setText(patient_hospital_list.get(position).getType().toString());
        Log.e("image",image);
        Glide.with(context).load(image).into(holder.iv_admin_1_hsimage);

        holder.succes_and_patient_Ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id=patient_hospital_list.get(position).getHospital_id().toString().trim();
                category=patient_hospital_list.get(position).getCategory().toString().trim();
                Intent intent=new Intent(context, Patient_Login3_Activity.class);
                intent.putExtra("patient_id",patient_id);
                intent.putExtra("hospital_id",hospital_id);
                intent.putExtra("email_id",email_id);
                intent.putExtra("category",category);
                intent.putExtra("pcategory",pcategory);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return patient_hospital_list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        ImageView iv_admin_1_hsimage;
        TextView txt_admin_1_hsname,txt_admin_1_Category,txt_admin_1_Type;
        LinearLayout succes_and_patient_Ll1;

        public Holder(View itemView) {
            super(itemView);
            iv_admin_1_hsimage = (ImageView) itemView.findViewById(R.id.iv_admin_1_hsimage1);
            txt_admin_1_hsname = (TextView) itemView.findViewById(R.id.txt_admin_1_hsname1);
            succes_and_patient_Ll1=(LinearLayout)itemView.findViewById(R.id.succes_and_patient_Ll1);
            txt_admin_1_Category=(TextView)itemView.findViewById(R.id.txt_admin_1_Category);
            txt_admin_1_Type=(TextView)itemView.findViewById(R.id.txt_admin_1_Type);

        }
    }
}
