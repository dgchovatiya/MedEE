package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_Appointment;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_blood;
import com.example.mitul.hospitalfinder.Class.Hospital.Notification_medicine;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_sep_hospitalDetails;
import com.example.mitul.hospitalfinder.Model.MyApplication;
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

public class Hospital_Login1_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    ImageView iv_hs_lg1_menu;
    ImageView imgvw, hs_lg_1image;

    TextView hs_lg1_name, hs_lg1_ow_name, hs_lg1_mo, hs_lg1_category, hs_lg1_type, hs_lg1_time, hs_lg1_bno,
            hs_lg1_colony, hs_lg1_landmark, hs_lg1_area, hs_lg1_pincode, hs_lg1_city, hs_lg1_state, hs_lg1_adress;
    TextView email;
    Context context;
    String login_id1, email_id1;
    String adress;
    SwipeRefreshLayout hs_lg1_swipeContainer1;
    TextView medicine_notify, blood_notify,appointment_notify;
    String profile, status, hospital_id, email_id, hospital_image, name, owner_name, mobile_number, category, type, time, building_no,
            colony, land_mark, area, pincode, city, state, medical_facilities, about, certificate;
    boolean doubleBackToExitPressedOnce = false;

    public static ArrayList<Notification_medicine> notification_medicine_array;
    public static ArrayList<Notification_blood> notification_blood_array;
    public static ArrayList<Notification_Appointment> notification_appointment_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1_);
       /* getSupportActionBar().hide();
        getSupportActionBar().hide();*/

        toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.hs_lg1_toolb1);
//        toolbar1.setNavigationIcon(R.drawable.ic_back);
//        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.hs_lg1_drawer);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        login_id1 = getIntent().getStringExtra("login_id");
        email_id1 = getIntent().getStringExtra("email_id");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.inflateHeaderView(R.layout.navigatiton_hedar_hs);
        imgvw = (ImageView) navView.findViewById(R.id.iv_hs_lg1_hd);
        email = (TextView) navView.findViewById(R.id.txt_hs_lg1_hd_email);
        email.setText(email_id1);
        //setkarvu header
        appointment_notify = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.hs_lg1_appointmnet));
        blood_notify = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.hs_lg1_blood_order));
        medicine_notify = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.hs_lg1_medicine_order));
        initView();
        setListner();
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
                    Callnotificationmedicine();
                    Callnotificationblood();

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
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_APPOINTMENT_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AppConstants.dissmissDialog();
                Log.e("Res notify_blood", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");


                    if (status.equals("1")) {
                        notification_appointment_array = new ArrayList<>();

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

                                notification_appointment_array.add(notification_appointment);
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
        smr.addStringParam("hospital_id", hospital_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }

    private void Callnotificationblood() {
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_BLOOD_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AppConstants.dissmissDialog();
                Log.e("Res notify_blood", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");


                    if (status.equals("1")) {
                        notification_blood_array = new ArrayList<>();

                        JSONArray jsonArray = jsonObject.getJSONArray("Bnotification");
                        if (jsonArray.length() != 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String b_notification_id = object.getString("b_notification_id");
                                String hospital_id1 = object.getString("hospital_id");
                                String patient_id = object.getString("patient_id");
                                Notification_blood notification_blood = new Notification_blood();
                                notification_blood.setB_notification_id(b_notification_id);
                                notification_blood.setHospital_id(hospital_id1);
                                notification_blood.setPatient_id(patient_id);
                                notification_blood_array.add(notification_blood);
                            }
                            blood_notify.setVisibility(View.VISIBLE);

                            blood_notify.setText(jsonArray.length() + "");


                        }else{
                            blood_notify.setVisibility(View.GONE);
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
        smr.addStringParam("hospital_id", hospital_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }

    private void Callnotificationmedicine() {

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_MEDICINE_NOTIFICATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                AppConstants.dissmissDialog();
                Log.e("Res notify_med1", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");


                    if (status.equals("1")) {
                        notification_medicine_array = new ArrayList<>();

                        JSONArray jsonArray = jsonObject.getJSONArray("Mnotification");
                        if (jsonArray.length() != 0) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String m_notification_id = object.getString("m_notification_id");
                                String hospital_id1 = object.getString("hospital_id");
                                String patient_id = object.getString("patient_id");
                                Notification_medicine notification_medicine = new Notification_medicine();
                                notification_medicine.setM_notification_id(m_notification_id);
                                notification_medicine.setHospital_id(hospital_id1);
                                notification_medicine.setPatient_id(patient_id);
                                notification_medicine_array.add(notification_medicine);
                            }
                            medicine_notify.setVisibility(View.VISIBLE);

                            medicine_notify.setText(jsonArray.length() + "");


                        }else{
                            medicine_notify.setVisibility(View.GONE);
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
        smr.addStringParam("hospital_id", hospital_id);


        MyApplication.getInstance().addToRequestQueue(smr);
    }

    private void initializeCountDrawer() {
        blood_notify.setGravity(Gravity.CENTER_VERTICAL);
        blood_notify.setTypeface(null, Typeface.BOLD);
        blood_notify.setTextColor(getResources().getColor(R.color.white));
        medicine_notify.setGravity(Gravity.CENTER_VERTICAL);
        medicine_notify.setTypeface(null, Typeface.BOLD);
        medicine_notify.setTextColor(getResources().getColor(R.color.white));
        appointment_notify.setGravity(Gravity.CENTER_VERTICAL);
        appointment_notify.setTypeface(null, Typeface.BOLD);
        appointment_notify.setTextColor(getResources().getColor(R.color.white));
//count is added
    }


    private void initView() {
        context = Hospital_Login1_Activity.this;

        hs_lg_1image = (ImageView) findViewById(R.id.hs_lg_1image);
        hs_lg1_name = (TextView) findViewById(R.id.hs_lg1_name);
        hs_lg1_ow_name = (TextView) findViewById(R.id.hs_lg1_ow_name);
        hs_lg1_mo = (TextView) findViewById(R.id.hs_lg1_mo);
        hs_lg1_type = (TextView) findViewById(R.id.hs_lg1_type);
        hs_lg1_time = (TextView) findViewById(R.id.hs_lg1_time);
        hs_lg1_category = (TextView) findViewById(R.id.hs_lg1_category);
        hs_lg1_adress = (TextView) findViewById(R.id.hs_lg1_adress);
        hs_lg1_pincode = (TextView) findViewById(R.id.hs_lg1_pincode);
        hs_lg1_city = (TextView) findViewById(R.id.hs_lg1_city);
        hs_lg1_state = (TextView) findViewById(R.id.hs_lg1_state);
        hs_lg1_swipeContainer1 = (SwipeRefreshLayout) findViewById(R.id.hs_lg1_swipeContainer1);


        CallApiHospitalApi();
        hs_lg1_swipeContainer1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

                //setAdpter();
            }
        });
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallApiHospitalApi();
        hs_lg1_swipeContainer1.setRefreshing(false);

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

    private void CallApiHospitalApi() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("login_id", login_id1);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_LOGIN_PAGE1, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Admin_sep_hospitalDetails admin_sep_hospitalDetails = gson.fromJson(response, Admin_sep_hospitalDetails.class);
                status = admin_sep_hospitalDetails.getSTATUS().toString().trim();

                if (status.equals("1")) {
                    for (int i = 0; i < admin_sep_hospitalDetails.getHospitaldetails().size(); i++) {
                        hospital_id = admin_sep_hospitalDetails.getHospitaldetails().get(i).getHospitalId().toString().trim();
                        email_id = admin_sep_hospitalDetails.getHospitaldetails().get(i).getEmailId().toString().trim();
                        profile = admin_sep_hospitalDetails.getHospitaldetails().get(i).getProfile().toString().trim();
                        hospital_image = admin_sep_hospitalDetails.getHospitaldetails().get(i).getHospitalImage().toString().trim();
                        name = admin_sep_hospitalDetails.getHospitaldetails().get(i).getName().toString().trim();
                        owner_name = admin_sep_hospitalDetails.getHospitaldetails().get(i).getOwnerName().toString().trim();
                        mobile_number = admin_sep_hospitalDetails.getHospitaldetails().get(i).getMobileNumber().toString().trim();
                        category = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCategory().toString().trim();
                        type = admin_sep_hospitalDetails.getHospitaldetails().get(i).getType().toString().trim();
                        time = admin_sep_hospitalDetails.getHospitaldetails().get(i).getTime().toString().trim();
                        building_no = admin_sep_hospitalDetails.getHospitaldetails().get(i).getBuildingNo().toString().trim();
                        colony = admin_sep_hospitalDetails.getHospitaldetails().get(i).getColony().toString().trim();
                        land_mark = admin_sep_hospitalDetails.getHospitaldetails().get(i).getLandMark().toString().trim();
                        area = admin_sep_hospitalDetails.getHospitaldetails().get(i).getArea().toString().trim();
                        pincode = admin_sep_hospitalDetails.getHospitaldetails().get(i).getPincode().toString().trim();
                        city = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCity().toString().trim();
                        state = admin_sep_hospitalDetails.getHospitaldetails().get(i).getState().toString().trim();
                        medical_facilities = admin_sep_hospitalDetails.getHospitaldetails().get(i).getMedicalFacilities().toString().trim();
                        about = admin_sep_hospitalDetails.getHospitaldetails().get(i).getAbout().toString().trim();
                        certificate = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCertificate().toString().trim();
                    }

                    adress = building_no + "," + colony + "," + land_mark + "," + area + "," + city;

                    hs_lg1_name.setText(name);
                    hs_lg1_ow_name.setText(owner_name);
                    hs_lg1_mo.setText(mobile_number);
                    hs_lg1_category.setText(category);
                    hs_lg1_type.setText(type);
                    hs_lg1_time.setText(time);
                    hs_lg1_adress.setText(adress);
                    hs_lg1_pincode.setText(pincode);
                    hs_lg1_city.setText(city);
                    hs_lg1_state.setText(state);

                    Glide.with(context).load(hospital_image).into(hs_lg_1image);
                    Glide.with(context).load(profile).into(imgvw);

                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Hospital details not avialable");

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

        if (id == R.id.home) {

            //startActivity(new Intent(home.this, homeappliencew.class));
            Log.e("home", "home");

        }
        if (id == R.id.hs_lg1_docter) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Login1_Docter1_Activity.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_medicine) {

            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Login1_Medicine1_Activity.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_blood) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Login1_Blood_Activity.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_blood_order) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Order_Blood_Activity.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_medicine_order) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Order_Medicine_Activity.class);
            intent.putExtra("hospital_id", hospital_id);

            startActivity(intent);
        }
        if (id == R.id.hs_lg1_appointmnet) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Appointment_Activity.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }

        if (id == R.id.hs_lg1_m_mf) {

            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Login1_MedicalFacilities.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_m_about) {

            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Login1_About.class);
            intent.putExtra("hospital_id", hospital_id);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_editProfile) {
            Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_EditBasic_Details_Activity1.class);
            intent.putExtra("hospital_id", hospital_id);
            intent.putExtra("name", name);
            intent.putExtra("owner_name", owner_name);
            intent.putExtra("category", category);
            intent.putExtra("type", type);
            intent.putExtra("time", time);
            intent.putExtra("mo", mobile_number);
            intent.putExtra("building_no", building_no);
            intent.putExtra("street", colony);
            intent.putExtra("landmark", land_mark);
            intent.putExtra("area", area);
            intent.putExtra("pincode", pincode);
            intent.putExtra("city", city);
            intent.putExtra("state", state);
            startActivity(intent);
        }
        if (id == R.id.hs_lg1_logout) {
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.hs_lg1_drawer);
        drawer.closeDrawer(GravityCompat.START);


        return false;
    }

    @Override
    public void onResume() {
        super.onResume();

        // CallApiHospitalApi();

    }

    private void setListner() {
        hs_lg_1image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hospital_Login1_Activity.this, Hospital_Edit_Hospital_Image.class);
                intent.putExtra("hospital_id", hospital_id);
                intent.putExtra("hospital_image", hospital_image);
                startActivity(intent);
            }
        });
    }

}
