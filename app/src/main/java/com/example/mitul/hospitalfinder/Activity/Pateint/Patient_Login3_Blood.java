package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Blood_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Blood_Add_new_Blood_Activity;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Login1_Blood_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login3_Blood_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_blood;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Hospital_blood_details_response;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

public class Patient_Login3_Blood extends AppCompatActivity {
    TextView txt_pt_lg3_blood_nofound;
    ImageView pt_lg3_blood1_back;
    SwipeRefreshLayout pt_lg3_blood1_swipeContainer1;
    RecyclerView pt_lg3_res_blood1;
    ArrayList<Item_blood> list;
    Context context;
    String hospital_id1,patient_id;
    String hospital_id,blood_id,blood_group,stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__blood);
        getSupportActionBar().hide();
        initview();
        setListener();
    }
    private void initview() {
        context = Patient_Login3_Blood.this;
        pt_lg3_res_blood1 = (RecyclerView) findViewById(R.id.pt_lg3_res_blood1);
        pt_lg3_blood1_back = (ImageView) findViewById(R.id.pt_lg3_blood1_back);
        txt_pt_lg3_blood_nofound = (TextView) findViewById(R.id.txt_pt_lg3_blood_nofound);
        hospital_id1 = getIntent().getStringExtra("hospital_id");
        patient_id=getIntent().getStringExtra("patient_id");
        pt_lg3_blood1_swipeContainer1 = (SwipeRefreshLayout) findViewById(R.id.pt_lg3_blood1_swipeContainer1);

        CallHospitalLoginBloodlApi();
        pt_lg3_blood1_swipeContainer1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

            }
        });


    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallHospitalLoginBloodlApi();
        pt_lg3_blood1_swipeContainer1.setRefreshing(false);

    }

    private void CallHospitalLoginBloodlApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id1);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_BLOOD_DETAILS_LIST, requestParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Hospital_blood_details_response hospital_blood_details_response = gson.fromJson(response, Hospital_blood_details_response.class);
                String status = hospital_blood_details_response.getSTATUS().toString().trim();
                if (hospital_blood_details_response.getBloodDetails().size() != 0) {
                    if (status.equals("1")) {
                        txt_pt_lg3_blood_nofound.setVisibility(View.GONE);
                        pt_lg3_res_blood1.setVisibility(View.VISIBLE);

                        list = new ArrayList<>();
                        for (int i = 0; i < hospital_blood_details_response.getBloodDetails().size(); i++) {

                            hospital_id = hospital_blood_details_response.getBloodDetails().get(i).getHospitalId().toString().trim();
                            blood_id = hospital_blood_details_response.getBloodDetails().get(i).getBloodId().toString().trim();
                            blood_group = hospital_blood_details_response.getBloodDetails().get(i).getBloodGroup().toString().trim();
                            stock = hospital_blood_details_response.getBloodDetails().get(i).getStock().toString().trim();

                            Item_blood item = new Item_blood();
                            item.setHospital_id(hospital_id);
                            item.setBlood_id(blood_id);
                            item.setBlood_group(blood_group);
                            item.setStock(stock);
                            list.add(item);

                        }
                        setAdpter();


                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Details Not Available");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Field is not set");
                    }
                } else {
                    txt_pt_lg3_blood_nofound.setVisibility(View.VISIBLE);
                    pt_lg3_res_blood1.setVisibility(View.GONE);

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
        Collections.sort(list, new Comparator<Item_blood>() {
            @Override
            public int compare(Item_blood o1, Item_blood o2) {
                return o1.getBlood_group().compareToIgnoreCase(o2.getBlood_group());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg3_res_blood1.setLayoutManager(layoutManager);
        Patient_Login3_Blood_Adapter adapter = new Patient_Login3_Blood_Adapter(context, list,patient_id);
        pt_lg3_res_blood1.setAdapter(adapter);
    }

    private void setListener() {
        pt_lg3_blood1_back.setOnClickListener(new View.OnClickListener() {
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
}
