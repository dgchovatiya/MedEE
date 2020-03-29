package com.example.mitul.hospitalfinder.Adapter.Admin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Activity.Admin.Admin_sep_hospital_details;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Fragment.Admin.Admin_Pending_Request;
import com.example.mitul.hospitalfinder.Fragment.Admin.Admin_Sucess_list;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item1;
import com.example.mitul.hospitalfinder.R;

import java.util.ArrayList;

/**
 * Created by Denish on 07-01-2019.
 */

public class Admin_Pending_RviewAdpter extends RecyclerView.Adapter<Admin_Pending_RviewAdpter.Holder> {
    Context context;
    ArrayList<Admin_item1> admin_hospital_first_list;
    LayoutInflater inflater;
    String hospital_id;
    Admin_Pending_Request admin_pending_request;
    Admin_Sucess_list admin_sucess_list;
    DeleteHospital deleteHospital;


    public Admin_Pending_RviewAdpter(Context context, ArrayList<Admin_item1> admin_hospital_first_list,Admin_Pending_RviewAdpter.DeleteHospital deleteHospital) {
        this.context = context;
        this.admin_hospital_first_list = admin_hospital_first_list;
        inflater = LayoutInflater.from(context);
        this.deleteHospital=deleteHospital;
    }



    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.pending_request_inner_design, parent, false);

        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        AppConstants.dissmissDialog();

        holder.txt_admin_1_hsname.setText(admin_hospital_first_list.get(position).getHospitalname().toString());
        final String image=admin_hospital_first_list.get(position).getImage().toString();
        Log.e("image",image);
        Glide.with(context).load(image).into(holder.iv_admin_1_hsimage);
        holder.btn_admin1_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 hospital_id=admin_hospital_first_list.get(position).getHospital_id().toString().trim();
                deleteHospital.deletedata(position,hospital_id);

                //CallVerifyApi();
            }
        });
        holder.ad_pending_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hospital_id=admin_hospital_first_list.get(position).getHospital_id().toString().trim();

                Intent intent=new Intent(context, Admin_sep_hospital_details.class);
                intent.putExtra("hospital_id",hospital_id);
                context.startActivity(intent);
            }
        });


    }

   /*private void CallVerifyApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id",hospital_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.ADMIN_VERIDED_HOSPITAL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }*/

    @Override
    public int getItemCount() {
        return admin_hospital_first_list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        ImageView iv_admin_1_hsimage;
        TextView txt_admin_1_hsname;
        Button btn_admin1_verify;
        LinearLayout ad_pending_ll;

        public Holder(View itemView) {
            super(itemView);
            iv_admin_1_hsimage = (ImageView) itemView.findViewById(R.id.iv_admin_1_hsimage);
            txt_admin_1_hsname = (TextView) itemView.findViewById(R.id.txt_admin_1_hsname);
            btn_admin1_verify=(Button)itemView.findViewById(R.id.btn_admin1_verify);
            ad_pending_ll=(LinearLayout)itemView.findViewById(R.id.ad_pending_ll);

        }
    }

    public interface DeleteHospital {

        public void deletedata(int position,String hospital_id);


    }
}
