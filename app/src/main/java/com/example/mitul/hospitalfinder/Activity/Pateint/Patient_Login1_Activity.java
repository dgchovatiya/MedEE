package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Login_Response;
import com.example.mitul.hospitalfinder.Model.State.CityDetail;
import com.example.mitul.hospitalfinder.Model.State.State_All_Details;
import com.example.mitul.hospitalfinder.Model.State.State_city_Details;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Patient_Login1_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Spinner sp_pt_lg1_state, sp_pt_lg1_city, sp_ls_lg1_category, sp_ls_lg1_type,sp_ls_lg1_extra;
    ArrayList<String> list_state, list_city, list_category, list_type,list_extra;
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    Button btn_next_pt_lg1;
    DrawerLayout drawer;
    ImageView iv_hs_lg1_menu;
    ImageView imgvw;
    TextView email;
    Context context;
    String login_id, patient_id, email_id, first_name, middle_name, last_name, date_of_birth_, mo;
    String state, city, category, type,extra;
    String email_id1;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login1_);

        toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.toolb_pt);
        setSupportActionBar(toolbar1);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        drawer = (DrawerLayout) findViewById(R.id.pt_drawer1);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        login_id = getIntent().getStringExtra("login_id");
        email_id1 = getIntent().getStringExtra("email_id");


        NavigationView navigationView = (NavigationView) findViewById(R.id.pt_nav_view1);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.inflateHeaderView(R.layout.navigatiton_hedar_pt);
        imgvw = (ImageView) navView.findViewById(R.id.iv_pt_lg1_hd);
        email = (TextView) navView.findViewById(R.id.txt_pt_lg1_hd_email);
        email.setText(email_id1);
        //setkarvu header


        initView();
        setListner();


    }


    private void initView() {

        context = Patient_Login1_Activity.this;
        sp_pt_lg1_state = (Spinner) findViewById(R.id.sp_pt_lg1_state);
        sp_pt_lg1_city = (Spinner) findViewById(R.id.sp_pt_lg1_city);
        sp_ls_lg1_category = (Spinner) findViewById(R.id.sp_ls_lg1_category);
        sp_ls_lg1_type = (Spinner) findViewById(R.id.sp_ls_lg1_type);
        btn_next_pt_lg1 = (Button) findViewById(R.id.btn_next_pt_lg1);
        //sp_ls_lg1_extra=(Spinner)findViewById(R.id.sp_ls_lg1_extra);
        CallPatientDetailsApi();
        CallOnlyGetStateApi();

        list_category = new ArrayList<>();
        list_category.add("--Hospital Category--");
        list_category.add("Orthopedic");
        list_category.add("Dental");
        list_category.add("MultiSpecialist");
        list_category.add("Radiological");
        list_category.add("Heart");
        list_category.add("Skin");
        list_category.add("Neurological");
        list_category.add("Urological");
        list_category.add("Nephrological");
        list_category.add("Homeopathic");
        list_category.add("Genrel Physicalogical");
        list_category.add("Ayurvedic");


        setAdapter(sp_ls_lg1_category, list_category);

        list_type = new ArrayList<>();
        list_type.add("--Select Type--");
        list_type.add("All");
        list_type.add("Goverment");
        list_type.add("Private");
        list_type.add("Trust");
        setAdapter(sp_ls_lg1_type, list_type);

        list_state = new ArrayList<>();
        list_city = new ArrayList<>();
        /*list_state.add("--Select State--");
        list_state.add("Gujarat");
        list_state.add("Maharashtra");
        list_state.add("Goa");
        list_state.add("Kolkata");
        list_state.add("Punjab");*/



        onClickEventOfStateSpinner();

    }

    private void CallOnlyGetStateApi() {
        RequestParams requestParams = new RequestParams();

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.GET_STATE_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                State_All_Details state_all_details = gson.fromJson(response, State_All_Details.class);

                String status = state_all_details.getSTATUS().toString().trim();
                list_state = new ArrayList<>();
                list_state.add("--Select State--");
                if (status.equals("1")) {
                    for (int i = 0; i < state_all_details.getStateDetails().size(); i++) {

                        String state = state_all_details.getStateDetails().get(i).getStateName().toString();

                        list_state.add(state);

                    }
                    setAdapter(sp_pt_lg1_state, list_state);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }

    private void CallPatientDetailsApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("login_id", login_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_LOGIN_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Patient_Login_Response patient_login_response = gson.fromJson(response, Patient_Login_Response.class);

                String status = patient_login_response.getSTATUS().toString().trim();

                for (int i = 0; i < patient_login_response.getPatientdetails().size(); i++) {

                    patient_id = patient_login_response.getPatientdetails().get(i).getPatientId().toString().trim();
                    email_id = patient_login_response.getPatientdetails().get(i).getEmailId().toString().trim();
                    first_name = patient_login_response.getPatientdetails().get(i).getFirstName().toString().trim();
                    middle_name = patient_login_response.getPatientdetails().get(i).getMiddleName().toString().trim();
                    last_name = patient_login_response.getPatientdetails().get(i).getLastName().toString().trim();
                    date_of_birth_ = patient_login_response.getPatientdetails().get(i).getDateOfBirth().toString().trim();
                    mo = patient_login_response.getPatientdetails().get(i).getMobileNumber().toString().trim();

                    Log.e("patient_id", patient_id);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });


    }

    private void onClickEventOfStateSpinner() {
        sp_pt_lg1_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = sp_pt_lg1_state.getSelectedItem().toString();
                Log.e("State", state);
                id = sp_pt_lg1_state.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        CallcityApi(state);
                        setAdapter(sp_pt_lg1_city, list_city);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void CallcityApi(String state) {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state_name",state);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.GET_CITY_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                State_city_Details state_city_details = gson.fromJson(response, State_city_Details.class);

                String status = state_city_details.getSTATUS().toString().trim();
                list_city = new ArrayList<>();
                list_city.add("--Select city--");
                if (status.equals("1")) {
                    for (int i = 0; i < state_city_details.getStateDetails().size(); i++) {

                        String city = state_city_details.getStateDetails().get(i).getDistrictName().toString();

                        list_city.add(city);

                    }
                    setAdapter(sp_pt_lg1_city, list_city);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }

    private void setCityList(String state) {
        switch (state) {
            case "Gujarat":
                list_city.clear();
                list_city.add("--Select city--");
                list_city.add("Ahmedabad");
                list_city.add("Surat");
                list_city.add("Bhavanagar");
                list_city.add("Vadodara");
                list_city.add("Jamnagar");
                list_city.add("Anand");
                list_city.add("Morbi");
                list_city.add("Navsari");
                break;
            case "Maharashtra":
                list_city.clear();
                list_city.add("--Select city--");
                list_city.add("Mumbai");
                list_city.add("Pune");
                list_city.add("Aurangabaad");
                list_city.add("Dhule");
                list_city.add("Navi mumbai");
                list_city.add("Nandurbar");
                list_city.add("Jalgao");
                list_city.add("Solapur");
                break;
            case "Goa":
                list_city.clear();
                list_city.add("--Select city--");
                list_city.add("Panaji");
                list_city.add("Prenem");
                list_city.add("Ponda");
                list_city.add("Sanguem");
                list_city.add("Cuncolim");
                list_city.add("Valpoi");
                list_city.add("Margao");
                break;
            case "Punjab":
                list_city.add("--Select city--");
                list_city.add("Amritsar");
                list_city.add("Jalandhar");
                list_city.add("Patiala");
                list_city.add("Pathankot");
                list_city.add("Rajpura");
                list_city.add("Firozpur");
                list_city.add("Sunam");
                list_city.add("Mohali");
                break;

        }

    }

    private void setAdapter(Spinner sp_hs_su1_category, ArrayList<String> list_category) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.hs_su1_hoscategory_spinner, R.id.txt_view, list_category) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sp_hs_su1_category.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.pt_lg1_am) {
            Intent intent = new Intent(context, Patient_Login1_All_Appointment_Activity.class);
            intent.putExtra("patient_id", patient_id);
            startActivity(intent);
            //startActivity(new Intent(home.this, homeappliencew.class));
            Log.e("Appointment", "Appointment");

        } else if (id == R.id.pt_lg1_ob) {
            Intent intent = new Intent(context, Patient_Login2_BloodOrderActivity.class);
            intent.putExtra("patient_id", patient_id);
            startActivity(intent);

        } else if (id == R.id.pt_lg1_om) {
            Intent intent = new Intent(context, Patient_Login2_MedicineOrderActivity.class);
            intent.putExtra("patient_id", patient_id);
            startActivity(intent);

        }
        if(id== R.id.pt_lg1_editp){
            Intent intent = new Intent(context, Edit_Profile_Activity.class);
            intent.putExtra("login_id", login_id);

            startActivity(intent);
        }
        if (id == R.id.pt_lg1_logout) {

            //startActivity(new Intent(home.this, homeappliencew.class));
            Log.e("Logout", "logout");
            finish();

        }

        return false;
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
    private void setListner() {
        btn_next_pt_lg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                state = sp_pt_lg1_state.getSelectedItem().toString().trim();
                category = sp_ls_lg1_category.getSelectedItem().toString().trim();
                type = sp_ls_lg1_type.getSelectedItem().toString().trim();

                if (state.equals("--Select State--")) {
                    AppConstants.openErrorDialog(context, "Please choose State");
                } else {
                    city = sp_pt_lg1_city.getSelectedItem().toString().trim();
                    if (city.equals("--Select city--")) {
                        AppConstants.openErrorDialog(context, "Please choose city");
                    } else {

                        if (category.equals("--Hospital Category--")) {
                            AppConstants.openErrorDialog(context, "Please choose category");
                        } else {

                            if (type.equals("--Select Type--")) {
                                AppConstants.openErrorDialog(context, "Please choose type");

                            } else {

                                    Intent intent = new Intent(Patient_Login1_Activity.this, Patient_Login2_Acitvity.class);
                                    intent.putExtra("patient_id", patient_id);
                                    intent.putExtra("email_id", email_id1);
                                    intent.putExtra("state", state);
                                    intent.putExtra("city", city);
                                    intent.putExtra("category", category);
                                    intent.putExtra("type", type);
                                    startActivity(intent);



                            }
                        }
                    }
                }

            }
        });

    }
}
