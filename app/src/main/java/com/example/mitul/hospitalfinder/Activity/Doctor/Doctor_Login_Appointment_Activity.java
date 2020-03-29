package com.example.mitul.hospitalfinder.Activity.Doctor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Activity;
import com.example.mitul.hospitalfinder.Activity.LoginActivity;
import com.example.mitul.hospitalfinder.Adapter.Doctor.Doctor_Appointment_ViewPage_Adpter;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Doctor_Login_Appointment_Activity extends AppCompatActivity {

    TabLayout doctor_main_tablayout;
    ViewPager doctor_main_viewpager;
    ImageView doctor_ap_back;
    Context context;
    String doctor_id;
    ArrayList<Notification_Appointment> notification_appointment_array2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__login_appointment);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.admin_color)); //status bar or the time bar at the top
        }
        initview();
        CallappointmentnotificationDeleteApi();

        setlistner();
        //setkarvu header


    }

    private void CallappointmentnotificationDeleteApi() {
        notification_appointment_array2 = new ArrayList<>();

        notification_appointment_array2= Doctor_Login_Activity.notification_appointment_array1;
        for(int i=0;i<notification_appointment_array2.size();i++){
            calldeleteApi(notification_appointment_array2.get(i).getA_notification_id());
        }
        Doctor_Login_Activity.notification_appointment_array1.clear();
        notification_appointment_array2.clear();
    }

    private void calldeleteApi(String a_notification_id) {

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.DOCTOR_APPOINTMENT_NOTIFICATION_DELETE, new Response.Listener<String>() {
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
        smr.addStringParam("a_notification_id", a_notification_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }

    private void setlistner() {
        doctor_ap_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initview() {
        getSupportActionBar().hide();
        context = Doctor_Login_Appointment_Activity.this;
        doctor_id = getIntent().getStringExtra("docter_id");
        doctor_main_tablayout = (TabLayout) findViewById(R.id.doctor_main_tablayout);
        doctor_main_viewpager = (ViewPager) findViewById(R.id.doctor_main_viewpager);
        doctor_ap_back = (ImageView) findViewById(R.id.doctor_ap_back);
        doctor_main_tablayout.addTab(doctor_main_tablayout.newTab().setText("PENDING REQUEST"));
        doctor_main_tablayout.addTab(doctor_main_tablayout.newTab().setText("CONFIRM  REQUEST"));
        doctor_main_tablayout.addTab(doctor_main_tablayout.newTab().setText("REJECTED REQUEST"));

        Doctor_Appointment_ViewPage_Adpter viewPager_adpter = new Doctor_Appointment_ViewPage_Adpter(getSupportFragmentManager(), doctor_main_tablayout.getTabCount(), context, doctor_id);
        doctor_main_viewpager.setAdapter(viewPager_adpter);

        doctor_main_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(doctor_main_tablayout));

        doctor_main_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                doctor_main_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
