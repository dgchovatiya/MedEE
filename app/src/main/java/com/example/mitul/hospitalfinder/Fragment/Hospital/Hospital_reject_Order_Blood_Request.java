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
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_sucess_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem1;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Blood;
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
public class Hospital_reject_Order_Blood_Request extends Fragment {
    RecyclerView hs_lg_bd_rej_rv;
    TextView txt_hs_lg_order_rej_bd_nofound;
    Context context;
    String hospital_id;
    ArrayList<OrderBlooditem1> hospital_blood_order_reject_list;
    SwipeRefreshLayout hs_lg_bd_rej_swipeContainer;

    public Hospital_reject_Order_Blood_Request(Context context, String hospital_id) {
        this.context = context;
        this.hospital_id = hospital_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hospital_reject_order_blood_request, container, false);
        hs_lg_bd_rej_rv = (RecyclerView) view.findViewById(R.id.hs_lg_bd_rej_rv);
        txt_hs_lg_order_rej_bd_nofound=(TextView)view.findViewById(R.id.txt_hs_lg_order_rej_bd_nofound);
        hs_lg_bd_rej_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.hs_lg_bd_rej_swipeContainer);
        CallHospitalBloodRejectRequstApi();

        hs_lg_bd_rej_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        hs_lg_bd_rej_swipeContainer.setRefreshing(false);

    }


    private void CallHospitalBloodRejectRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_ORDER_BLOOD_REJECT_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Order_Blood order_blood = gson.fromJson(response, Order_Blood.class);
                String stauts = order_blood.getSTATUS().toString().trim();
                hospital_blood_order_reject_list = new ArrayList<>();
                Log.e("status", stauts);

                if(stauts.equals("1")){
                    if(order_blood.getOrderDetails().size()!=0) {
                        txt_hs_lg_order_rej_bd_nofound.setVisibility(View.GONE);
                        for (int i = 0; i < order_blood.getOrderDetails().size(); i++) {


                            String order_id = order_blood.getOrderDetails().get(i).getOrderId().toString().trim();
                            String Patient_id = order_blood.getOrderDetails().get(i).getPatientId().toString().trim();
                            String blood_id = order_blood.getOrderDetails().get(i).getBloodId().toString().trim();
                            String pfname = order_blood.getOrderDetails().get(i).getFirstName().toString().trim();
                            String pmname = order_blood.getOrderDetails().get(i).getMiddleName().toString().trim();
                            String plname = order_blood.getOrderDetails().get(i).getLastName().toString().trim();
                            String pdob = order_blood.getOrderDetails().get(i).getDateOfBirth().toString().trim();
                            String pmo = order_blood.getOrderDetails().get(i).getMobileNumber().toString().trim();
                            String quntity = order_blood.getOrderDetails().get(i).getQuantity().toString().trim();
                            String date = order_blood.getOrderDetails().get(i).getDate().toString().trim();
                            String bloodgroup = order_blood.getOrderDetails().get(i).getBloodGroup().toString().trim();
                            String reason=order_blood.getOrderDetails().get(i).getReason().toString().trim();
                            String pname = pfname + "  " + pmname + "  " + plname;
                            OrderBlooditem1 orderBlooditem = new OrderBlooditem1();
                            orderBlooditem.setOrder_id(order_id);
                            orderBlooditem.setPatient_id(Patient_id);
                            orderBlooditem.setBlood_id(blood_id);
                            orderBlooditem.setPatientname(pname);
                            orderBlooditem.setPatientdob(pdob);
                            orderBlooditem.setPatientmob(pmo);
                            orderBlooditem.setBookingdate(date);
                            orderBlooditem.setQuantity(quntity);
                            orderBlooditem.setBloodgroup(bloodgroup);
                            orderBlooditem.setReason(reason);
                            hospital_blood_order_reject_list.add(orderBlooditem);

                        }
                        CallAdpter();


                    }
                    else{
                        CallAdpter();

                        txt_hs_lg_order_rej_bd_nofound.setVisibility(View.VISIBLE);

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
        Collections.sort(hospital_blood_order_reject_list, new Comparator<OrderBlooditem1>() {
            @Override
            public int compare(OrderBlooditem1 o1, OrderBlooditem1 o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        hs_lg_bd_rej_rv.setLayoutManager(layoutManager);
        Hospital_Order_Blood_reject_Adapter adapter = new Hospital_Order_Blood_reject_Adapter(context, hospital_blood_order_reject_list);
        hs_lg_bd_rej_rv.setAdapter(adapter);
    }
}
