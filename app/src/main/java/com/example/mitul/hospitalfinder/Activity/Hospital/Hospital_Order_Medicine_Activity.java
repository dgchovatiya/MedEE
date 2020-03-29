package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_ViewPage_Adpter;
import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Medicine_ViewPage_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_medicine;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hospital_Order_Medicine_Activity extends AppCompatActivity {
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;

    ImageView imgvw;
    TextView email;
    Context context;
    String hospital_id;
    TabLayout hospital_order_medicine_tablayout;
    ViewPager hospital_order_medicine_viewpager;
    ImageView hs_lg_order_medicine_back;
    ArrayList<Notification_medicine> notification_medicine_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__order__medicine_);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.admin_color)); //status bar or the time bar at the top
        }
        getSupportActionBar().hide();

        initview();
        CallMedicienenotificationDeleteApi();
        setListner();
    }

    private void CallMedicienenotificationDeleteApi() {
        notification_medicine_array = new ArrayList<>();

        notification_medicine_array= Hospital_Login1_Activity.notification_medicine_array;
        for(int i=0;i<notification_medicine_array.size();i++){
            calldeleteApi(notification_medicine_array.get(i).getM_notification_id());
        }
        Hospital_Login1_Activity.notification_medicine_array.clear();
        notification_medicine_array.clear();
    }

    private void calldeleteApi(String m_notification_id) {
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_MEDICINE_NOTIFICATION_DELETE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AppConstants.dissmissDialog();
                Log.e("Res notify_med", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");


                    if (status.equals("1")) {


                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Not Inserted");

                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Field is not set");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context, "Error........");

            }
        });
        smr.addStringParam("m_notification_id", m_notification_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }


    private void initview() {
        context=Hospital_Order_Medicine_Activity.this;
        hospital_id=getIntent().getStringExtra("hospital_id");
        hospital_order_medicine_tablayout = (TabLayout) findViewById(R.id.hospital_order_medicine_tablayout);
        hospital_order_medicine_viewpager = (ViewPager) findViewById(R.id.hospital_order_medicine_viewpager);
        hs_lg_order_medicine_back=(ImageView)findViewById(R.id.hs_lg_order_medicine_back);

        hospital_order_medicine_tablayout.addTab(hospital_order_medicine_tablayout.newTab().setText("PENDING REQUEST"));
        hospital_order_medicine_tablayout.addTab(hospital_order_medicine_tablayout.newTab().setText("CONFIRM  REQUEST"));
        hospital_order_medicine_tablayout.addTab(hospital_order_medicine_tablayout.newTab().setText("REJECTED  REQUEST"));

        Hospital_Order_Medicine_ViewPage_Adapter hospital_order_medicine_viewPage_adpter = new Hospital_Order_Medicine_ViewPage_Adapter(getSupportFragmentManager(), hospital_order_medicine_tablayout.getTabCount(),context,hospital_id);
        hospital_order_medicine_viewpager.setAdapter(hospital_order_medicine_viewPage_adpter);

        hospital_order_medicine_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(hospital_order_medicine_tablayout));

        hospital_order_medicine_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                hospital_order_medicine_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setListner() {
        hs_lg_order_medicine_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
