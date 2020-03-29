package com.example.mitul.hospitalfinder.Activity.Admin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_docter_Adpter;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item2;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_docter_details_response;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Admin_docterActivity extends AppCompatActivity {
    RecyclerView ad_res_docter;
    Context context;
    ImageView ad_docter_back;
    String status, f_name, m_name, l_name, qualification, mo, category,docter_image;
    ArrayList<Admin_item2> list;
    String hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_docter);
        getSupportActionBar().hide();
        initview();
        setListener();
    }

    private void initview() {
        context = Admin_docterActivity.this;
        ad_res_docter = (RecyclerView) findViewById(R.id.ad_res_docter);
        ad_docter_back = (ImageView) findViewById(R.id.ad_docter_back);
        hospital_id = getIntent().getStringExtra("hospital_id");
        CallAdminHospitalDocterDetailApi();




    }


    private void setListener() {
        ad_docter_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {

        finish();

    }

    private void CallAdminHospitalDocterDetailApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.ADMIN_DOCTER_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Admin_docter_details_response admin_docter_details_response = gson.fromJson(response, Admin_docter_details_response.class);
                String status = admin_docter_details_response.getSTATUS().toString().trim();
                if (status.equals("1")) {
                    list = new ArrayList<>();
                    for (int i = 0; i < admin_docter_details_response.getDocterDetails().size(); i++) {

                        f_name = admin_docter_details_response.getDocterDetails().get(i).getFirstName().toString().trim();
                        m_name = admin_docter_details_response.getDocterDetails().get(i).getMiddleName().toString().trim();
                        l_name = admin_docter_details_response.getDocterDetails().get(i).getLastName().toString().trim();
                        qualification = admin_docter_details_response.getDocterDetails().get(i).getQualification().toString().trim();
                        mo = admin_docter_details_response.getDocterDetails().get(i).getMobileNumber().toString().trim();
                        category = admin_docter_details_response.getDocterDetails().get(i).getCategory().toString().trim();
                        docter_image=admin_docter_details_response.getDocterDetails().get(i).getDocterImage().toString().trim();
                        Admin_item2 item = new Admin_item2();
                        item.setFirst_name(f_name);
                        item.setMiddle_name(m_name);
                        item.setLast_name(l_name);
                        item.setQualification(qualification);
                        item.setMobilenumber(mo);
                        item.setCategory(category);
                        item.setDocter_image(docter_image);
                        list.add(item);
                    }
                    setAdpter();
                }
                else if(status.equals("0")){
                    AppConstants.openErrorDialog(context,"Details Not Available");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }

    private void setAdpter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        ad_res_docter.setLayoutManager(layoutManager);
        Admin_docter_Adpter adapter = new Admin_docter_Adpter(context, list);
        ad_res_docter.setAdapter(adapter);

    }
}
