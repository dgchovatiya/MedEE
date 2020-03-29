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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Medicine1_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Medicine1_Add_New_Medicine_Activity;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Login1_Medicine_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login3_Medicine_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Hospital_medical_details_response;
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

public class Patient_Login3_Medicine extends AppCompatActivity implements SearchView.OnQueryTextListener{

    TextView txt_pt_lg3_med_nofound;
    RecyclerView pt_lg3_res_med1;
    ImageView pt_lg3_med1_back;
    SwipeRefreshLayout pt_lg3_med1_swipeContainer1;
    ArrayList<Item_Medicine> list;
    Context context;
    Patient_Login3_Medicine_Adapter adapter;
    SearchView editsearch;
    String hospital_id,patient_id;
    String hospital_id1,medicine_id,name,stock,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__medicine);
        getSupportActionBar().hide();
        initview();
        setListener();
    }
    private void initview() {
        context = Patient_Login3_Medicine.this;
        pt_lg3_res_med1 = (RecyclerView) findViewById(R.id.pt_lg3_res_med1);
        pt_lg3_med1_back = (ImageView) findViewById(R.id.pt_lg3_med1_back);
        txt_pt_lg3_med_nofound=(TextView)findViewById(R.id.txt_pt_lg3_med_nofound);
        hospital_id1 = getIntent().getStringExtra("hospital_id");
        patient_id=getIntent().getStringExtra("patient_id");

        pt_lg3_med1_swipeContainer1 = (SwipeRefreshLayout) findViewById(R.id.pt_lg3_med1_swipeContainer1);
        editsearch = (SearchView)findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);


        CallPatientLoginMedicineDetailApi();
        pt_lg3_med1_swipeContainer1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        CallPatientLoginMedicineDetailApi();
        pt_lg3_med1_swipeContainer1.setRefreshing(false);

    }

    private void CallPatientLoginMedicineDetailApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id1);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_MEDICAL_DETAILS_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Hospital_medical_details_response hospital_medical_details_response=gson.fromJson(response,Hospital_medical_details_response.class);
                String status=hospital_medical_details_response.getSTATUS().toString().trim();
                if(hospital_medical_details_response.getMedicineDetails().size()!=0){
                    if(status.equals("1")){
                        txt_pt_lg3_med_nofound.setVisibility(View.GONE);
                        pt_lg3_res_med1.setVisibility(View.VISIBLE);

                        list = new ArrayList<>();
                        for(int i=0;i<hospital_medical_details_response.getMedicineDetails().size();i++){

                            hospital_id=hospital_medical_details_response.getMedicineDetails().get(i).getHospitalId().toString().trim();
                            medicine_id=hospital_medical_details_response.getMedicineDetails().get(i).getMedicineId().toString().trim();
                            name=hospital_medical_details_response.getMedicineDetails().get(i).getName().toString().trim();
                            stock=hospital_medical_details_response.getMedicineDetails().get(i).getStock().toString().trim();
                            description=hospital_medical_details_response.getMedicineDetails().get(i).getDescription().toString().trim();

                            Item_Medicine item=new Item_Medicine();
                            item.setHospital_id(hospital_id);
                            item.setMedicine_id(medicine_id);
                            item.setMedicine_name(name);
                            item.setStock(stock);
                            item.setDescription(description);
                            list.add(item);

                        }
                        Collections.sort(list, new Comparator<Item_Medicine>() {
                            @Override
                            public int compare(Item_Medicine o1, Item_Medicine o2) {
                                return o1.getMedicine_name().compareToIgnoreCase(o2.getMedicine_name());
                            }
                        });
                        setAdpter();


                    }else if(status.equals("0")){
                        AppConstants.openErrorDialog(context,"Details Not Available");


                    }else if(status.equals("00")){
                        AppConstants.openErrorDialog(context,"Field is not set");
                    }
                }
                else{
                    txt_pt_lg3_med_nofound.setVisibility(View.VISIBLE);
                    pt_lg3_res_med1.setVisibility(View.GONE);

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
        pt_lg3_res_med1.setLayoutManager(layoutManager);
         adapter = new Patient_Login3_Medicine_Adapter(context, list,patient_id);
        pt_lg3_res_med1.setAdapter(adapter);
    }

    private void setListener() {
        pt_lg3_med1_back.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String aa = "Ovan";
        list.clear();
        String text = newText;
        Log.e("text",text);
        adapter.filter(text);
        return false;
    }
}
