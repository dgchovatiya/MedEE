package com.example.mitul.hospitalfinder.Fragment.Admin;

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

import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_Pending_RviewAdpter;
import com.example.mitul.hospitalfinder.Adapter.Admin.Admin_Sucess_RviewAdpter;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_PendingRq_List_response;
import com.example.mitul.hospitalfinder.Class.Admin.Admin_item1;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Denish on 07-01-2019.
 */

@SuppressLint("ValidFragment")
public class Admin_Sucess_list extends Fragment {
    RecyclerView admin_pen_rv;
    Context context;
    ArrayList<Admin_item1> admin_hospital_first_list;
    SwipeRefreshLayout admn_sucess_swipeContainer;


    @SuppressLint("ValidFragment")
    public Admin_Sucess_list(Context context) {
        this.context=context;

    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_sucess, container, false);
        admin_pen_rv = (RecyclerView)view.findViewById(R.id.admin_suc_rv);
        admn_sucess_swipeContainer=(SwipeRefreshLayout)view.findViewById(R.id.admn_sucess_swipeContainer);

        CallAdminSucessApi();

        admn_sucess_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
       CallAdminSucessApi();
        admn_sucess_swipeContainer.setRefreshing(false);

    }

    public void CallAdminSucessApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.SIGNUP_ADMIN_SUCESS_LIST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Admin_PendingRq_List_response admin_pendingRq_list_response = gson.fromJson(response, Admin_PendingRq_List_response.class);
                String stauts = admin_pendingRq_list_response.getSTATUS().toString().trim();
                Log.e("staus",stauts);
                admin_hospital_first_list = new ArrayList<>();

                for (int i = 0; i < admin_pendingRq_list_response.getHospitaldetails().size(); i++) {


                    String hospital_id = admin_pendingRq_list_response.getHospitaldetails().get(i).getHospitalId().toString().trim();
                    String name = admin_pendingRq_list_response.getHospitaldetails().get(i).getName().toString().trim();
                    String image = admin_pendingRq_list_response.getHospitaldetails().get(i).getProfile().toString();
                    Log.e("hospital _id", hospital_id);
                    Log.e("name", name);
                    Log.e("image", image);

                    Admin_item1 item=new Admin_item1();
                    item.setHospital_id(hospital_id);
                    item.setHospitalname(name);
                    item.setImage(image);
                    admin_hospital_first_list.add(item);
                }
                CallAdpter();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }

    public void CallAdpter() {
        AppConstants.dissmissDialog();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        admin_pen_rv.setLayoutManager(layoutManager);
        Admin_Sucess_RviewAdpter adapter = new Admin_Sucess_RviewAdpter(context, admin_hospital_first_list);
        admin_pen_rv.setAdapter(adapter);
    }


}
