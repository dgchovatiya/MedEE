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

import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_Pending_RviewAdpter;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.Item_Medicine;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Blood;
import com.example.mitul.hospitalfinder.R;
import com.google.android.gms.tasks.TaskExecutors;
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
 * Created by Denish on 01-02-2019.
 */

@SuppressLint("ValidFragment")
public class Hospital_Pending_Order_Blood_Request extends Fragment implements  Hospital_Order_Blood_pending_Adapter.DeleteSucessRqeuest,Hospital_Order_Blood_pending_Adapter.DeleteRejectedRqeuest{
    RecyclerView hs_lg_bd_pen_rv;
    TextView txt_hs_lg_order_pen_bd_nofound;
    Context context;
    ArrayList<OrderBlooditem> hospital_blood_order_pending_list;
    SwipeRefreshLayout hs_lg_bd_pend_swipeContainer;
    Hospital_Order_Blood_pending_Adapter.DeleteSucessRqeuest deleteSucessRqeuest;
    Hospital_Order_Blood_pending_Adapter.DeleteRejectedRqeuest deleteRejectedRqeuest;
    public  static int bcount=0;

    String hospital_id;
    String ORDERID,BLOOD_ID,STOCK;
    String ORDERID1,BLOOD_ID1,REASON1;

    public Hospital_Pending_Order_Blood_Request(Context context, String hospital_id) {
        this.context = context;
        this.hospital_id = hospital_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        bcount=0;
        View view = inflater.inflate(R.layout.hospital_pending_order_blood_request, container, false);
        hs_lg_bd_pen_rv = (RecyclerView) view.findViewById(R.id.hs_lg_bd_pen_rv);
        txt_hs_lg_order_pen_bd_nofound=(TextView)view.findViewById(R.id.txt_hs_lg_order_pen_bd_nofound);
        deleteSucessRqeuest = this;
        deleteRejectedRqeuest=this;

        hs_lg_bd_pend_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.hs_lg_bd_pend_swipeContainer);

        CallHospitalBloodPendingRequstApi();

        hs_lg_bd_pend_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

               CallAdpter();
            }
        });


        return view;
    }

    private void CallHospitalBloodPendingRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);
        Log.e("a","a");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_ORDER_BLOOD_PENIDNG_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Order_Blood order_blood = gson.fromJson(response, Order_Blood.class);
                String stauts = order_blood.getSTATUS().toString().trim();
                hospital_blood_order_pending_list = new ArrayList<>();
                Log.e("status", stauts);

                    if(stauts.equals("1")){
                        if(order_blood.getOrderDetails().size()!=0) {
                            txt_hs_lg_order_pen_bd_nofound.setVisibility(View.GONE);
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
                                String pname = pfname + "  " + pmname + "  " + plname;
                                OrderBlooditem orderBlooditem = new OrderBlooditem();
                                orderBlooditem.setOrder_id(order_id);
                                orderBlooditem.setPatient_id(Patient_id);
                                orderBlooditem.setBlood_id(blood_id);
                                orderBlooditem.setPatientname(pname);
                                orderBlooditem.setPatientdob(pdob);
                                orderBlooditem.setPatientmob(pmo);
                                orderBlooditem.setBookingdate(date);
                                orderBlooditem.setQuantity(quntity);
                                orderBlooditem.setBloodgroup(bloodgroup);
                                hospital_blood_order_pending_list.add(orderBlooditem);

                            }
                            CallAdpter();


                        }
                        else{
                            CallAdpter();

                            txt_hs_lg_order_pen_bd_nofound.setVisibility(View.VISIBLE);

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


    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallHospitalBloodPendingRequstApi();
        hs_lg_bd_pend_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        AppConstants.dissmissDialog();
        Collections.sort(hospital_blood_order_pending_list, new Comparator<OrderBlooditem>() {
            @Override
            public int compare(OrderBlooditem o1, OrderBlooditem o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        hs_lg_bd_pen_rv.setLayoutManager(layoutManager);
        Hospital_Order_Blood_pending_Adapter adapter = new Hospital_Order_Blood_pending_Adapter(context, hospital_blood_order_pending_list,deleteSucessRqeuest,deleteRejectedRqeuest);
        hs_lg_bd_pen_rv.setAdapter(adapter);
    }



    private void CallMakeSucessBloodRequestApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("order_id", ORDERID);
        requestParams.put("blood_id", BLOOD_ID);
        requestParams.put("stock", STOCK);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_MAKE_CONFIRM_ORDER_BLOOD, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);
                CallHospitalBloodPendingRequstApi();
                CallAdpter();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void deletedata(int position, String order_id, String blood_id, String stock) {
     ORDERID=order_id;
        BLOOD_ID=blood_id;
        STOCK=stock;
        Log.e("Orderid",ORDERID);
        Log.e("BLOOD_ID",BLOOD_ID);
        Log.e("STOCK",STOCK);


        CallMakeSucessBloodRequestApi();
    }

    @Override
    public void deletedata1(int position, String order_id, String blood_id, String Reason) {
        ORDERID1=order_id;
        BLOOD_ID1=blood_id;
        REASON1=Reason;
        Log.e("order",ORDERID1);
        Log.e("blood",BLOOD_ID1);
        Log.e("reason",REASON1);

        CallRejectOrderBloodApi();
    }

    private void CallRejectOrderBloodApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("order_id", ORDERID1);
        requestParams.put("reason",REASON1);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_MAKE_REJECT_ORDER_BLOOD, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);
                CallHospitalBloodPendingRequstApi();
                CallAdpter();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
