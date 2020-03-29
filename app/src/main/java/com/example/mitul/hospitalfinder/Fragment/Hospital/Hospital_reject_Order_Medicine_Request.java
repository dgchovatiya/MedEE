package com.example.mitul.hospitalfinder.Fragment.Hospital;

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
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Medicine_reject_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem1;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Blood;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Medicine;
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
public class Hospital_reject_Order_Medicine_Request extends Fragment {
    RecyclerView hs_lg_md_rej_rv;
    TextView txt_hs_lg_order_rej_md_nofound;
    Context context;
    String hospital_id;
    ArrayList<OrderMedicineitem1> hospital_medicine_order_reject_list;
    SwipeRefreshLayout hs_lg_md_rej_swipeContainer;

    public Hospital_reject_Order_Medicine_Request(Context context, String hospital_id) {
        this.context = context;
        this.hospital_id = hospital_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hospital_reject_order_medicine_request, container, false);
        hs_lg_md_rej_rv = (RecyclerView) view.findViewById(R.id.hs_lg_md_rej_rv);
        txt_hs_lg_order_rej_md_nofound=(TextView)view.findViewById(R.id.txt_hs_lg_order_rej_md_nofound);
        hs_lg_md_rej_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.hs_lg_md_rej_swipeContainer);
        CallHospitalBloodRejectRequstApi();

        hs_lg_md_rej_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        CallHospitalBloodRejectRequstApi();
        hs_lg_md_rej_swipeContainer.setRefreshing(false);

    }


    private void CallHospitalBloodRejectRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_ORDER_MEDICINE_REJECT_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Order_Medicine order_medicine = gson.fromJson(response, Order_Medicine.class);
                String stauts = order_medicine.getSTATUS().toString().trim();
                hospital_medicine_order_reject_list = new ArrayList<>();
                Log.e("status", stauts);

                if(stauts.equals("1")){
                    if(order_medicine.getOrderMedicineDetails().size()!=0) {
                        txt_hs_lg_order_rej_md_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < order_medicine.getOrderMedicineDetails().size(); i++) {


                            String order_medicine_id = order_medicine.getOrderMedicineDetails().get(i).getOrderMedicineId().toString().trim();
                            String Patient_id = order_medicine.getOrderMedicineDetails().get(i).getPatientId().toString().trim();
                            String medicine_id = order_medicine.getOrderMedicineDetails().get(i).getMedicineId().toString().trim();
                            String pfname = order_medicine.getOrderMedicineDetails().get(i).getFirstName().toString().trim();
                            String pmname = order_medicine.getOrderMedicineDetails().get(i).getMiddleName().toString().trim();
                            String plname = order_medicine.getOrderMedicineDetails().get(i).getLastName().toString().trim();
                            String pdob = order_medicine.getOrderMedicineDetails().get(i).getDateOfBirth().toString().trim();
                            String pmo = order_medicine.getOrderMedicineDetails().get(i).getMobileNumber().toString().trim();
                            String quntity = order_medicine.getOrderMedicineDetails().get(i).getQuantity().toString().trim();
                            String date = order_medicine.getOrderMedicineDetails().get(i).getDate().toString().trim();
                            String medicine_name = order_medicine.getOrderMedicineDetails().get(i).getMedicineName().toString().trim();
                            String reason=order_medicine.getOrderMedicineDetails().get(i).getReason().toString().trim();
                            String pname = pfname + "  " + pmname + "  " + plname;
                            OrderMedicineitem1 orderMedicineitem = new OrderMedicineitem1();
                            orderMedicineitem.setOrder_medicine_id(order_medicine_id);
                            orderMedicineitem.setPatient_id(Patient_id);
                            orderMedicineitem.setMedicine_id(medicine_id);
                            orderMedicineitem.setPatientname(pname);
                            orderMedicineitem.setPatientdob(pdob);
                            orderMedicineitem.setPatientmob(pmo);
                            orderMedicineitem.setBookingdate(date);
                            orderMedicineitem.setQuantity(quntity);
                            orderMedicineitem.setMedicine_name(medicine_name);
                            orderMedicineitem.setReason(reason);
                            hospital_medicine_order_reject_list.add(orderMedicineitem);

                        }
                        CallAdpter();


                    }
                    else{
                        CallAdpter();

                        txt_hs_lg_order_rej_md_nofound.setVisibility(View.VISIBLE);

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
        Collections.sort(hospital_medicine_order_reject_list, new Comparator<OrderMedicineitem1>() {
            @Override
            public int compare(OrderMedicineitem1 o1, OrderMedicineitem1 o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        hs_lg_md_rej_rv.setLayoutManager(layoutManager);
        Hospital_Order_Medicine_reject_Adapter adapter = new Hospital_Order_Medicine_reject_Adapter(context, hospital_medicine_order_reject_list);
        hs_lg_md_rej_rv.setAdapter(adapter);
    }
}
