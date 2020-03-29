package com.example.mitul.hospitalfinder.Adapter.Admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item1;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item2;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 08-01-2019.
 */

public class Admin_docter_Adpter extends RecyclerView.Adapter<Admin_docter_Adpter.Holder> {

    Context context;
    ArrayList<Admin_item2> list;
    LayoutInflater inflater;

    public Admin_docter_Adpter(Context context, ArrayList<Admin_item2> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.admin_docter_rs_inner_view, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.ad_hs_ds_fname.setText(list.get(position).getFirst_name().toString().trim());
        holder.ad_hs_ds_mname.setText(list.get(position).getMiddle_name().toString().trim());
        holder.ad_hs_ds_lname.setText(list.get(position).getLast_name().toString().trim());
        holder.ad_hs_ds_qualification.setText(list.get(position).getQualification().toString().trim());
        holder.ad_hs_ds_mo.setText(list.get(position).getMobilenumber().toString().trim());
        holder.ad_hs_ds_category.setText(list.get(position).getQualification().toString().trim());
        String image=list.get(position).getDocter_image().toString();
        Log.e("image",image);
        Glide.with(context).load(image).into(holder.iv_ad_docteriv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView ad_hs_ds_fname, ad_hs_ds_mname, ad_hs_ds_lname, ad_hs_ds_mo, ad_hs_ds_qualification,
                ad_hs_ds_category;
        ImageView iv_ad_docteriv;

        public Holder(View itemView) {
            super(itemView);

            ad_hs_ds_fname = (TextView) itemView.findViewById(R.id.ad_hs_ds_fname);

            ad_hs_ds_mname = (TextView) itemView.findViewById(R.id.ad_hs_ds_mname);
            ad_hs_ds_lname = (TextView) itemView.findViewById(R.id.ad_hs_ds_lname);
            ad_hs_ds_qualification = (TextView) itemView.findViewById(R.id.ad_hs_ds_qualification);
            ad_hs_ds_mo = (TextView) itemView.findViewById(R.id.ad_hs_ds_mo);
            ad_hs_ds_category = (TextView) itemView.findViewById(R.id.ad_hs_ds_category);
            iv_ad_docteriv=(ImageView)itemView.findViewById(R.id.iv_ad_docteriv);
        }
    }
}
