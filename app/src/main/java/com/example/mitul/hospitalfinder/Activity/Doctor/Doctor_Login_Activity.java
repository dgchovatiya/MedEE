package com.example.mitul.hospitalfinder.Activity.Doctor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Activity.LoginActivity;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Login_Response;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class Doctor_Login_Activity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener  {
    TextView txt_d_name,txt_d_mo,txt_d_category,txt_d_qualification,txt_d_email;
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;

    ImageView imgvw,docter_iv;
    TextView email,appointment_notify;
    boolean doubleBackToExitPressedOnce = false;

    Context context;
    String docter_id,login_id,hospital_id,email_id,docter_image,first_name,middle_name,last_name,qualification,mobile_number,category;
    public static ArrayList<Notification_Appointment> notification_appointment_array1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor__login_);
        initview();



        toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.d_toolb);
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.d_dawer);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.d_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.inflateHeaderView(R.layout.navigatiton_hedar_ad);
        imgvw = (ImageView) navView.findViewById(R.id.iv_ad_lg1_hd);
        email = (TextView) navView.findViewById(R.id.txt_ad_lg1_hd_email);
        appointment_notify = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.d_ap));

        initializeCountDrawer();
        callcontinueosmethod();

    }

    private void callcontinueosmethod() {
        final int count = 0;
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private Handler updateUI = new Handler() {
                @Override
                public void dispatchMessage(Message msg) {
                    super.dispatchMessage(msg);
                    CallnotificationappointmentApi();


                }
            };

            public void run() {
                try {
                    updateUI.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000 * 5);
    }

    private void CallnotificationappointmentApi() {
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.DOCTOR_APPOINTMENT_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AppConstants.dissmissDialog();
                Log.e("Res notify_blood", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");


                    if (status.equals("1")) {
                        notification_appointment_array1 = new ArrayList<>();

                        JSONArray jsonArray = jsonObject.getJSONArray("Anotification");
                        if (jsonArray.length() != 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String a_notification_id = object.getString("a_notification_id");
                                String hospital_id1 = object.getString("hospital_id");
                                String patient_id = object.getString("patient_id");
                                String docter_id = object.getString("docter_id");

                                Notification_Appointment notification_appointment = new Notification_Appointment();
                                notification_appointment.setA_notification_id(a_notification_id);
                                notification_appointment.setHospital_id(hospital_id1);
                                notification_appointment.setPatient_id(patient_id);
                                notification_appointment.setDocter_id(docter_id);

                                notification_appointment_array1.add(notification_appointment);
                            }
                            appointment_notify.setVisibility(View.VISIBLE);

                            appointment_notify.setText(jsonArray.length() + "");


                        }else{
                            appointment_notify.setVisibility(View.GONE);
                        }

                        Log.e("lenght", jsonArray.length() + "");
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


            }
        });
        smr.addStringParam("docter_id", docter_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to Logout", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void initializeCountDrawer() {
        appointment_notify.setGravity(Gravity.CENTER_VERTICAL);
        appointment_notify.setTypeface(null, Typeface.BOLD);
        appointment_notify.setTextColor(getResources().getColor(R.color.white));
    }

    private void initview() {
        context=Doctor_Login_Activity.this;
        login_id=getIntent().getStringExtra("login_id");
        txt_d_name=(TextView)findViewById(R.id.txt_d_name);
        txt_d_mo=(TextView)findViewById(R.id.txt_d_mo);
        txt_d_category=(TextView)findViewById(R.id.txt_d_category);
        txt_d_qualification=(TextView)findViewById(R.id.txt_d_qualification);
        txt_d_email=(TextView)findViewById(R.id.txt_d_email);
        docter_iv=(ImageView)findViewById(R.id.docter_iv);
        CallDocoterDetailsApi();
    }

    private void CallDocoterDetailsApi() {
            AppConstants.showDialog(context);
            RequestParams requestParams = new RequestParams();
            requestParams.put("login_id", login_id);

            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            asyncHttpClient.post(context, URL.DOCTOR_LOGIN_DETAILS, requestParams, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    AppConstants.dissmissDialog();
                    String response = new String(responseBody);
                    Log.e("response", response);


                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("STATUS");
                        Log.e("status", status);
                        if(status.equals("1")){
                            JSONArray docterdetails = jsonObject.getJSONArray("docterdetails");
                            for (int i=0;i<docterdetails.length();i++){
                                JSONObject object = docterdetails.getJSONObject(i);

                                 docter_id=object.getString("docter_id");
                                 login_id=object.getString("login_id");
                                 hospital_id=object.getString("hospital_id");
                                 email_id=object.getString("email_id");
                                 docter_image=object.getString("docter_image");
                                 first_name=object.getString("first_name");
                                 middle_name=object.getString("middle_name");
                                 last_name=object.getString("last_name");
                                 qualification=object.getString("qualification");
                                 mobile_number=object.getString("mobile_number");
                                 category=object.getString("category");

                            }
                           String docter_name=first_name+" "+middle_name+" "+last_name;
                            txt_d_name.setText(docter_name);
                            txt_d_mo.setText(mobile_number);
                            txt_d_qualification.setText(qualification);
                            txt_d_category.setText(category);
                            txt_d_email.setText(email_id);
                            Glide.with(context).load(docter_image).into(docter_iv);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    AppConstants.dissmissDialog();
                    AppConstants.openErrorDialog(context, "some error");
                }
            });



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.d_logout) {

            //startActivity(new Intent(home.this, homeappliencew.class));
            Log.e("Logout", "logout");
            startActivity(new Intent(Doctor_Login_Activity.this, LoginActivity.class));
            finish();

        }
        if (id==R.id.d_ap){
            Intent intent=new Intent(context,Doctor_Login_Appointment_Activity.class);
            intent.putExtra("docter_id",docter_id);
            startActivity(intent);
            Log.e("appointment","appointment");
        }

        return false;
    }
}
