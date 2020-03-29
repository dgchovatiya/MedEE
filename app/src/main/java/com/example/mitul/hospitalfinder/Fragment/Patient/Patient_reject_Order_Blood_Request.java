package com.example.mitul.hospitalfinder.Fragment.Patient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_reject_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_reject_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem1;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem1;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Blood;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Blood;
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

/**
 * Created by Denish on 02-02-2019.
 */

@SuppressLint("ValidFragment")
public class Patient_reject_Order_Blood_Request extends Fragment {
    RecyclerView pt_lg2_bd_rej_rv;
    TextView txt_pt_lg2_order_rej_bd_nofound;
    Context context;
    String hospital_id,patient_id;
    ArrayList<PatientOrderBlooditem1> patient_blood_order_reject_list;
    SwipeRefreshLayout pt_lg2_md_rej_swipeContainer;

    public Patient_reject_Order_Blood_Request(Context context, String patient_id) {
        this.context = context;
        this.patient_id = patient_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_reject_order_blood_request, container, false);
        pt_lg2_bd_rej_rv = (RecyclerView) view.findViewById(R.id.pt_lg2_bd_rej_rv);
        txt_pt_lg2_order_rej_bd_nofound=(TextView)view.findViewById(R.id.txt_pt_lg2_order_rej_bd_nofound);
        pt_lg2_md_rej_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg2_rej_swipeContainer);
        CallPatientBloodRejectRequstApi();

        pt_lg2_md_rej_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

                CallAdpter();
            }
        });


        return view;
    }
    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallPatientBloodRejectRequstApi();
        pt_lg2_md_rej_swipeContainer.setRefreshing(false);

    }


    private void CallPatientBloodRejectRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id", patient_id);
        Log.e("a","a");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_BLOOOD_REJECT_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response sucess", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Patient_Order_Blood patient_order_blood = gson.fromJson(response, Patient_Order_Blood.class);
                String stauts = patient_order_blood.getSTATUS().toString().trim();
                patient_blood_order_reject_list = new ArrayList<>();
                Log.e("status", stauts);

                if(stauts.equals("1")){
                    if(patient_order_blood.getPatientOrderDetails().size()!=0) {
                        txt_pt_lg2_order_rej_bd_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < patient_order_blood.getPatientOrderDetails().size(); i++) {


                            String order_id = patient_order_blood.getPatientOrderDetails().get(i).getOrderId().toString().trim();
                            String hospital_id=patient_order_blood.getPatientOrderDetails().get(i).getHospitalId().toString().trim();
                            String Patient_id = patient_order_blood.getPatientOrderDetails().get(i).getPatientId().toString().trim();
                            String blood_id = patient_order_blood.getPatientOrderDetails().get(i).getBloodId().toString().trim();
                            String hname = patient_order_blood.getPatientOrderDetails().get(i).getName().toString().trim();
                            String hmo = patient_order_blood.getPatientOrderDetails().get(i).getMobileNumber().toString().trim();
                            String quntity = patient_order_blood.getPatientOrderDetails().get(i).getQuantity().toString().trim();
                            String date = patient_order_blood.getPatientOrderDetails().get(i).getDate().toString().trim();
                            String bloodgroup = patient_order_blood.getPatientOrderDetails().get(i).getBloodGroup().toString().trim();
                            String reason=patient_order_blood.getPatientOrderDetails().get(i).getReason().toString().trim();

                            PatientOrderBlooditem1 patientOrderBlooditem = new PatientOrderBlooditem1();
                            patientOrderBlooditem.setOrder_id(order_id);
                            patientOrderBlooditem.setHospital_id(hospital_id);
                            patientOrderBlooditem.setPatient_id(Patient_id);
                            patientOrderBlooditem.setBlood_id(blood_id);
                            patientOrderBlooditem.setHospital_name(hname);
                            patientOrderBlooditem.setHospitalmob(hmo);
                            patientOrderBlooditem.setBookingdate(date);
                            patientOrderBlooditem.setQuantity(quntity);
                            patientOrderBlooditem.setBloodgroup(bloodgroup);
                            patientOrderBlooditem.setReason(reason);
                            patient_blood_order_reject_list.add(patientOrderBlooditem);

                        }
                        CallAdpter();


                    }
                    else{
                        CallAdpter();

                        txt_pt_lg2_order_rej_bd_nofound.setVisibility(View.VISIBLE);

                    }
                }else if(stauts.equals("0")){
                    AppConstants.openErrorDialog(context, "Not available");

                }
                else if(stauts.equals("00")){
                    AppConstants.openErrorDialog(context, "Field is not set");

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
        Collections.sort(patient_blood_order_reject_list, new Comparator<PatientOrderBlooditem1>() {
            @Override
            public int compare(PatientOrderBlooditem1 o1, PatientOrderBlooditem1 o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_bd_rej_rv.setLayoutManager(layoutManager);
        Patient_Order_Blood_reject_Adapter adapter = new Patient_Order_Blood_reject_Adapter(context, patient_blood_order_reject_list);
        pt_lg2_bd_rej_rv.setAdapter(adapter);
    }
}
