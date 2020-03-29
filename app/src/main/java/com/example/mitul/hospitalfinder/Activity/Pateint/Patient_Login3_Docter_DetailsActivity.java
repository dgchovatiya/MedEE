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

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Docter1_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Docter_New_Add_Activity;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Login1_Docter1_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login3_Docter_Adapter;
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

public class Patient_Login3_Docter_DetailsActivity extends AppCompatActivity {


    RecyclerView pt_lg3_res_docter1;
    Context context;
    ImageView pt_lg3_docter1_back;
    String status, docter_id, f_name, m_name, l_name, qualification, mo, rescategory, docter_image;
    ArrayList<Admin_item2> list;
    String hospital_id, patient_id, category, pcategory;
    SwipeRefreshLayout pt_lg3_d1_swipeContainer1;
    int j ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__docter__details);
        getSupportActionBar().hide();
        initview();
        setListener();
    }

    private void initview() {
        context = Patient_Login3_Docter_DetailsActivity.this;
        pt_lg3_res_docter1 = (RecyclerView) findViewById(R.id.pt_lg3_res_docter1);
        pt_lg3_docter1_back = (ImageView) findViewById(R.id.pt_lg3_docter1_back);
        hospital_id = getIntent().getStringExtra("hospital_id");
        patient_id = getIntent().getStringExtra("patient_id");
        rescategory = getIntent().getStringExtra("category");
        pcategory = getIntent().getStringExtra("pcategory");
        pt_lg3_d1_swipeContainer1 = (SwipeRefreshLayout) findViewById(R.id.pt_lg3_d1_swipeContainer1);

        if (rescategory.equals(pcategory)) {
            j = 0;
            CallHospitalLoginDocterDetailApi();

        } else if (rescategory.equals("MultiSpecialist")) {
            j=1;
            if (pcategory.equals("Dental")) {
                pcategory = "Dental";
            } else if (pcategory.equals("Orthopedic")) {
                pcategory = "Orthopedic";
            } else if (pcategory.equals("Radiological")) {
                pcategory = "Radiologist";
            } else if (pcategory.equals("Heart")) {
                pcategory = "Cardiologist";
            } else if (pcategory.equals("Skin")) {
                pcategory = "Dermatologist";
            } else if (pcategory.equals("Neurological")) {
                pcategory = "Neurosergen";
            } else if (pcategory.equals("Urological")) {
                pcategory = "Urologist";
            } else if (pcategory.equals("Nephrological")) {
                pcategory = "Nephrologist";
            } else if (pcategory.equals("Homeopathic")) {
                pcategory = "Homeopathist";
            } else if (pcategory.equals("Genrel Physicalogical")) {
                pcategory = "General Physician";
            } else if (pcategory.equals("Ayurvedic")) {
                pcategory = "Ayurvedist";
            }


            CallHospitalLoginDocterDetailCategorywiseApi();
        }
        pt_lg3_d1_swipeContainer1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

                setAdpter();
            }
        });


    }


    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        if(j==0){
            CallHospitalLoginDocterDetailApi();
        }else{
            CallHospitalLoginDocterDetailCategorywiseApi();
        }


        pt_lg3_d1_swipeContainer1.setRefreshing(false);

    }

    private void CallHospitalLoginDocterDetailApi() {
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
                status = admin_docter_details_response.getSTATUS().toString().trim();
                if (status.equals("1")) {
                    list = new ArrayList<>();
                    for (int i = 0; i < admin_docter_details_response.getDocterDetails().size(); i++) {
                        docter_id = admin_docter_details_response.getDocterDetails().get(i).getDocterId().toString().trim();
                        f_name = admin_docter_details_response.getDocterDetails().get(i).getFirstName().toString().trim();
                        m_name = admin_docter_details_response.getDocterDetails().get(i).getMiddleName().toString().trim();
                        l_name = admin_docter_details_response.getDocterDetails().get(i).getLastName().toString().trim();
                        qualification = admin_docter_details_response.getDocterDetails().get(i).getQualification().toString().trim();
                        mo = admin_docter_details_response.getDocterDetails().get(i).getMobileNumber().toString().trim();
                        category = admin_docter_details_response.getDocterDetails().get(i).getCategory().toString().trim();
                        docter_image = admin_docter_details_response.getDocterDetails().get(i).getDocterImage().toString().trim();

                        Admin_item2 item = new Admin_item2();
                        item.setDocter_id(docter_id);
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
                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Details Not Available");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }

    private void CallHospitalLoginDocterDetailCategorywiseApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);
        requestParams.put("category", pcategory);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_DOCTER_DETAILS_CATEGORY_WISE, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Admin_docter_details_response admin_docter_details_response = gson.fromJson(response, Admin_docter_details_response.class);
                status = admin_docter_details_response.getSTATUS().toString().trim();
                if (status.equals("1")) {
                    list = new ArrayList<>();
                    for (int i = 0; i < admin_docter_details_response.getDocterDetails().size(); i++) {
                        docter_id = admin_docter_details_response.getDocterDetails().get(i).getDocterId().toString().trim();
                        f_name = admin_docter_details_response.getDocterDetails().get(i).getFirstName().toString().trim();
                        m_name = admin_docter_details_response.getDocterDetails().get(i).getMiddleName().toString().trim();
                        l_name = admin_docter_details_response.getDocterDetails().get(i).getLastName().toString().trim();
                        qualification = admin_docter_details_response.getDocterDetails().get(i).getQualification().toString().trim();
                        mo = admin_docter_details_response.getDocterDetails().get(i).getMobileNumber().toString().trim();
                        category = admin_docter_details_response.getDocterDetails().get(i).getCategory().toString().trim();
                        docter_image = admin_docter_details_response.getDocterDetails().get(i).getDocterImage().toString().trim();

                        Admin_item2 item = new Admin_item2();
                        item.setDocter_id(docter_id);
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
                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Details Not Available");
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
        pt_lg3_res_docter1.setLayoutManager(layoutManager);
        Patient_Login3_Docter_Adapter adapter = new Patient_Login3_Docter_Adapter(context, list, hospital_id, patient_id);
        pt_lg3_res_docter1.setAdapter(adapter);
    }

    private void setListener() {
        pt_lg3_docter1_back.setOnClickListener(new View.OnClickListener() {
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
