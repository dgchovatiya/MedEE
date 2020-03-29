package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_Sucess_RviewAdpter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_search_Hospital_listAdpter;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item1;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_PendingRq_List_response;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Patient_Login2_Acitvity extends AppCompatActivity {
    RecyclerView pt_lg2_rv;
    ImageView pt_lg2_back;
    SwipeRefreshLayout pt_lg2_swipeContainer;
    ArrayList<Admin_item1> Patient_hospital_list;
    String state, city, category, type,pcategory,extra;
    TextView txt_pt_lg2_nofound;
    String email_id,patient_id;



    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login2__acitvity);


        initView();
        setListner();
    }


    private void initView() {
        getSupportActionBar().hide();
        context = Patient_Login2_Acitvity.this;
        pt_lg2_rv = (RecyclerView) findViewById(R.id.pt_lg2_rv);
        pt_lg2_back = (ImageView) findViewById(R.id.pt_lg2_back);
        txt_pt_lg2_nofound=(TextView)findViewById(R.id.txt_pt_lg2_nofound);
        pt_lg2_swipeContainer = (SwipeRefreshLayout) findViewById(R.id.pt_lg2_swipeContainer);
        email_id=getIntent().getStringExtra("email_id");
        patient_id=getIntent().getStringExtra("patient_id");

        state = getIntent().getStringExtra("state");
        city = getIntent().getStringExtra("city");
        category = getIntent().getStringExtra("category");
        pcategory=category;
        type = getIntent().getStringExtra("type");


            CallPatientSearchHospitalListApi();



        pt_lg2_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

                CallAdpter();
            }
        });


    }

    private void CallPatientSearchBloodHospitalListApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state", state);
        requestParams.put("city", city);
        requestParams.put("category", category);
        requestParams.put("type", type);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_SEARCH_HOSPITAL_BLOOD_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                //Admin And Patient Response same as Patient search hospital list so use Admin_PendingRq_List_response

                Admin_PendingRq_List_response admin_pendingRq_list_response = gson.fromJson(response, Admin_PendingRq_List_response.class);
                String stauts = admin_pendingRq_list_response.getSTATUS().toString().trim();
                Log.e("staus", stauts);
                Patient_hospital_list = new ArrayList<>();
                if (stauts.equals("1")) {
                    if (admin_pendingRq_list_response.getHospitaldetails().size() != 0) {
                        txt_pt_lg2_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < admin_pendingRq_list_response.getHospitaldetails().size(); i++) {


                            String hospital_id = admin_pendingRq_list_response.getHospitaldetails().get(i).getHospitalId().toString().trim();
                            String name = admin_pendingRq_list_response.getHospitaldetails().get(i).getName().toString().trim();
                            String image = admin_pendingRq_list_response.getHospitaldetails().get(i).getProfile().toString();
                            String category=admin_pendingRq_list_response.getHospitaldetails().get(i).getCategory().toString().trim();
                            String type1=admin_pendingRq_list_response.getHospitaldetails().get(i).getType().toString().trim();
                            Log.e("hospital _id", hospital_id);
                            Log.e("name", name);
                            Log.e("image", image);
                            Log.e("category",category);

                            Admin_item1 item = new Admin_item1();
                            item.setHospital_id(hospital_id);
                            item.setHospitalname(name);
                            item.setImage(image);
                            item.setCategory(category);
                            item.setType(type1);
                            Patient_hospital_list.add(item);
                        }
                        CallAdpter();
                    }
                    else{
                        txt_pt_lg2_nofound.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    AppConstants.openErrorDialog(context,"Some error");

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }

    private void CallPatientSearchMedicineHospitalListApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state", state);
        requestParams.put("city", city);
        requestParams.put("category", category);
        requestParams.put("type", type);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_SEARCH_HOSPITAL_MEDICINE_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                //Admin And Patient Response same as Patient search hospital list so use Admin_PendingRq_List_response

                Admin_PendingRq_List_response admin_pendingRq_list_response = gson.fromJson(response, Admin_PendingRq_List_response.class);
                String stauts = admin_pendingRq_list_response.getSTATUS().toString().trim();
                Log.e("staus", stauts);
                Patient_hospital_list = new ArrayList<>();
                if (stauts.equals("1")) {
                    if (admin_pendingRq_list_response.getHospitaldetails().size() != 0) {
                        txt_pt_lg2_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < admin_pendingRq_list_response.getHospitaldetails().size(); i++) {


                            String hospital_id = admin_pendingRq_list_response.getHospitaldetails().get(i).getHospitalId().toString().trim();
                            String name = admin_pendingRq_list_response.getHospitaldetails().get(i).getName().toString().trim();
                            String image = admin_pendingRq_list_response.getHospitaldetails().get(i).getProfile().toString();
                            String category=admin_pendingRq_list_response.getHospitaldetails().get(i).getCategory().toString().trim();
                            String type1=admin_pendingRq_list_response.getHospitaldetails().get(i).getType().toString().trim();
                            Log.e("hospital _id", hospital_id);
                            Log.e("name", name);
                            Log.e("image", image);
                            Log.e("category",category);

                            Admin_item1 item = new Admin_item1();
                            item.setHospital_id(hospital_id);
                            item.setHospitalname(name);
                            item.setImage(image);
                            item.setCategory(category);
                            item.setType(type1);
                            Patient_hospital_list.add(item);
                        }
                        CallAdpter();
                    }
                    else{
                        txt_pt_lg2_nofound.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    AppConstants.openErrorDialog(context,"Some error");

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }

    private void CallPatientSearchBothHospitalListApi() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state", state);
        requestParams.put("city", city);
        requestParams.put("category", category);
        requestParams.put("type", type);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_SEARCH_HOSPITAL_BOTH_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                //Admin And Patient Response same as Patient search hospital list so use Admin_PendingRq_List_response

                Admin_PendingRq_List_response admin_pendingRq_list_response = gson.fromJson(response, Admin_PendingRq_List_response.class);
                String stauts = admin_pendingRq_list_response.getSTATUS().toString().trim();
                Log.e("staus", stauts);
                Patient_hospital_list = new ArrayList<>();
                if (stauts.equals("1")) {
                    if (admin_pendingRq_list_response.getHospitaldetails().size() != 0) {
                        txt_pt_lg2_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < admin_pendingRq_list_response.getHospitaldetails().size(); i++) {


                            String hospital_id = admin_pendingRq_list_response.getHospitaldetails().get(i).getHospitalId().toString().trim();
                            String name = admin_pendingRq_list_response.getHospitaldetails().get(i).getName().toString().trim();
                            String image = admin_pendingRq_list_response.getHospitaldetails().get(i).getProfile().toString();
                            String category=admin_pendingRq_list_response.getHospitaldetails().get(i).getCategory().toString().trim();
                            String type1=admin_pendingRq_list_response.getHospitaldetails().get(i).getType().toString().trim();
                            Log.e("hospital _id", hospital_id);
                            Log.e("name", name);
                            Log.e("image", image);
                            Log.e("category",category);

                            Admin_item1 item = new Admin_item1();
                            item.setHospital_id(hospital_id);
                            item.setHospitalname(name);
                            item.setImage(image);
                            item.setCategory(category);
                            item.setType(type1);
                            Patient_hospital_list.add(item);
                        }
                        CallAdpter();
                    }
                    else{
                        txt_pt_lg2_nofound.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    AppConstants.openErrorDialog(context,"Some error");

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }


    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.

            CallPatientSearchHospitalListApi();


        pt_lg2_swipeContainer.setRefreshing(false);

    }

    private void CallPatientSearchHospitalListApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state", state);
        requestParams.put("city", city);
        requestParams.put("category", category);
        requestParams.put("type", type);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_SEARCH_HOSPITAL_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                //Admin And Patient Response same as Patient search hospital list so use Admin_PendingRq_List_response

                Admin_PendingRq_List_response admin_pendingRq_list_response = gson.fromJson(response, Admin_PendingRq_List_response.class);
                String stauts = admin_pendingRq_list_response.getSTATUS().toString().trim();
                Log.e("staus", stauts);
                Patient_hospital_list = new ArrayList<>();
                if (stauts.equals("1")) {
                    if (admin_pendingRq_list_response.getHospitaldetails().size() != 0) {
                        txt_pt_lg2_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < admin_pendingRq_list_response.getHospitaldetails().size(); i++) {


                            String hospital_id = admin_pendingRq_list_response.getHospitaldetails().get(i).getHospitalId().toString().trim();
                            String name = admin_pendingRq_list_response.getHospitaldetails().get(i).getName().toString().trim();
                            String image = admin_pendingRq_list_response.getHospitaldetails().get(i).getProfile().toString();
                            String category=admin_pendingRq_list_response.getHospitaldetails().get(i).getCategory().toString().trim();
                            String type1=admin_pendingRq_list_response.getHospitaldetails().get(i).getType().toString().trim();
                            Log.e("hospital _id", hospital_id);
                            Log.e("name", name);
                            Log.e("image", image);
                            Log.e("category",category);

                            Admin_item1 item = new Admin_item1();
                            item.setHospital_id(hospital_id);
                            item.setHospitalname(name);
                            item.setImage(image);
                            item.setCategory(category);
                            item.setType(type1);
                            Patient_hospital_list.add(item);
                        }
                        CallAdpter();
                    }
                    else{
                        txt_pt_lg2_nofound.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    AppConstants.openErrorDialog(context,"Some error");

                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }

    private void CallAdpter() {

        AppConstants.dissmissDialog();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_rv.setLayoutManager(layoutManager);
        Patient_search_Hospital_listAdpter adapter = new Patient_search_Hospital_listAdpter(context, Patient_hospital_list,email_id,patient_id,pcategory);
        pt_lg2_rv.setAdapter(adapter);

    }

    private void setListner() {
        pt_lg2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
