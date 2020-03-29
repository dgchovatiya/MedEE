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

import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_sucess_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Medicine_sucess_Adapter;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderMedicineitem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Blood;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Medicine;
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
public class Patient_sucess_Order_Medicine_Request extends Fragment {
    RecyclerView pt_lg2_md_suc_rv;
    TextView txt_pt_lg2_order_suc_md_nofound;
    Context context;
    ArrayList<PatientOrderMedicineitem> patient_medicine_order_sucess_list;
    SwipeRefreshLayout pt_lg2_md_suc_swipeContainer;


    String patient_id;
    String ORDERID,BLOOD_ID,STOCK,REASON;

    public Patient_sucess_Order_Medicine_Request(Context context, String patient_id) {
        this.context = context;
        this.patient_id = patient_id;
    }
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_sucess_order_medicine_request, container, false);
        pt_lg2_md_suc_rv = (RecyclerView) view.findViewById(R.id.pt_lg2_md_suc_rv);
        txt_pt_lg2_order_suc_md_nofound=(TextView)view.findViewById(R.id.txt_pt_lg2_order_suc_md_nofound);
        pt_lg2_md_suc_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg2_md_suc_swipeContainer);
        CallPatientMedicineSucessRequstApi();
        pt_lg2_md_suc_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

                CallAdpter();
            }
        });


        return view;
    }

    private void CallAdpter() {
        AppConstants.dissmissDialog();

        Collections.sort(patient_medicine_order_sucess_list, new Comparator<PatientOrderMedicineitem>() {
            @Override
            public int compare(PatientOrderMedicineitem o1, PatientOrderMedicineitem o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_md_suc_rv.setLayoutManager(layoutManager);
        Patient_Order_Medicine_sucess_Adapter adapter = new Patient_Order_Medicine_sucess_Adapter(context, patient_medicine_order_sucess_list);
        pt_lg2_md_suc_rv.setAdapter(adapter);
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallPatientMedicineSucessRequstApi();
        pt_lg2_md_suc_swipeContainer.setRefreshing(false);

    }

    private void CallPatientMedicineSucessRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id", patient_id);
        Log.e("a","a");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_MEDICINE_SUCESS_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Patient_Order_Medicine patient_order_medicine = gson.fromJson(response, Patient_Order_Medicine.class);
                String stauts = patient_order_medicine.getSTATUS().toString().trim();
                patient_medicine_order_sucess_list = new ArrayList<>();
                Log.e("status", stauts);

                if(stauts.equals("1")){
                    if(patient_order_medicine.getPatientMOrderDetails().size()!=0) {
                        txt_pt_lg2_order_suc_md_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < patient_order_medicine.getPatientMOrderDetails().size(); i++) {


                            String order_medicine_id = patient_order_medicine.getPatientMOrderDetails().get(i).getOrderMedicineId().toString().trim();
                            String hospital_id=patient_order_medicine.getPatientMOrderDetails().get(i).getHospitalId().toString().trim();
                            String Patient_id = patient_order_medicine.getPatientMOrderDetails().get(i).getPatientId().toString().trim();
                            String medicine_id = patient_order_medicine.getPatientMOrderDetails().get(i).getMedicineId().toString().trim();
                            String hname = patient_order_medicine.getPatientMOrderDetails().get(i).getName().toString().trim();
                            String hmo = patient_order_medicine.getPatientMOrderDetails().get(i).getMobileNumber().toString().trim();
                            String quntity = patient_order_medicine.getPatientMOrderDetails().get(i).getQuantity().toString().trim();
                            String date = patient_order_medicine.getPatientMOrderDetails().get(i).getDate().toString().trim();
                            String medicine_name = patient_order_medicine.getPatientMOrderDetails().get(i).getMedicineName().toString().trim();

                            PatientOrderMedicineitem patientOrderMedicineitem = new PatientOrderMedicineitem();
                            patientOrderMedicineitem.setOrder_id(order_medicine_id);
                            patientOrderMedicineitem.setHospital_id(hospital_id);
                            patientOrderMedicineitem.setPatient_id(Patient_id);
                            patientOrderMedicineitem.setMedicine_id(medicine_id);
                            patientOrderMedicineitem.setHospital_name(hname);
                            patientOrderMedicineitem.setHospitalmob(hmo);
                            patientOrderMedicineitem.setBookingdate(date);
                            patientOrderMedicineitem.setQuantity(quntity);
                            patientOrderMedicineitem.setMedcine_name(medicine_name);
                            patient_medicine_order_sucess_list.add(patientOrderMedicineitem);

                        }
                        CallAdpter();


                    }
                    else{
                        CallAdpter();

                        txt_pt_lg2_order_suc_md_nofound.setVisibility(View.VISIBLE);

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





}
