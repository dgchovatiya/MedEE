package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mitul.hospitalfinder.Activity.SignUpCommonActivity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.State.State_All_Details;
import com.example.mitul.hospitalfinder.Model.State.State_city_Details;
import com.example.mitul.hospitalfinder.Model.State.State_pincode_Details;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Hospital_Signup2_Activity extends AppCompatActivity {
    EditText et_Ho_su2_buildingno, et_Ho_su2_street, et_Ho_su2_landmark, et_Ho_su2_area;
    Button btn_next_su2, btn_set_location;
    ImageView hs_s2_back;
    TextView et_Ho_su2_latitude, et_Ho_su2_longitude;
    Context context;
    Spinner sp_Ho_su2_state, sp_Ho_su2_city, sp_Ho_su2_pincode;
    ArrayList<String> stateList, cityList, pincodeList;
    String hbuilding_no2, hstreet2, hlandmark2, harea2, hpincode2, hcity2, hstate2, hlatitude2, hlongitude2;
    String hname1, hownername1, hcategory1, htype1, htime1, hmo1;
    String login_id, email_id;
    public static Hospital_Signup2_Activity hospital_signup2_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup2_);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Signup2_Activity.this;
        hospital_signup2_activity = Hospital_Signup2_Activity.this;

        et_Ho_su2_buildingno = (EditText) findViewById(R.id.et_Ho_su2_buildingno);
        et_Ho_su2_street = (EditText) findViewById(R.id.et_Ho_su2_street);
        et_Ho_su2_landmark = (EditText) findViewById(R.id.et_Ho_su2_landmark);
        et_Ho_su2_area = (EditText) findViewById(R.id.et_Ho_su2_area);
        hs_s2_back = (ImageView) findViewById(R.id.hs_s2_back);
        btn_set_location = (Button) findViewById(R.id.btn_set_location);

      /*  et_Ho_su2_city = (EditText) findViewById(R.id.et_Ho_su2_city);
        et_Ho_su2_state = (EditText) findViewById(R.id.et_Ho_su2_state);*/
        et_Ho_su2_latitude = (TextView) findViewById(R.id.et_Ho_su2_latitude);
        et_Ho_su2_longitude = (TextView) findViewById(R.id.et_Ho_su2_longitude);
        btn_next_su2 = (Button) findViewById(R.id.btn_next_su2);
        sp_Ho_su2_state = (Spinner) findViewById(R.id.sp_Ho_su2_state);
        sp_Ho_su2_city = (Spinner) findViewById(R.id.sp_Ho_su2_city);
        sp_Ho_su2_pincode = (Spinner) findViewById(R.id.sp_Ho_su2_pincode);
        CallOnlyGetStateApi();

        stateList = new ArrayList<>();
        cityList = new ArrayList<>();
        pincodeList = new ArrayList<>();
      /*  stateList.add("--Select State--");
        stateList.add("Gujarat");
        stateList.add("Maharashtra");
        stateList.add("Goa");
        stateList.add("Kolkata");
        stateList.add("Punjab");*/


        onClickEventOfStateSpinner();


        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname1 = getIntent().getStringExtra("name");
        hownername1 = getIntent().getStringExtra("owner_name");
        hcategory1 = getIntent().getStringExtra("category");
        htype1 = getIntent().getStringExtra("type");
        htime1 = getIntent().getStringExtra("time");
        hmo1 = getIntent().getStringExtra("mo");


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
                stateList = new ArrayList<>();
                stateList.add("--Select State--");
                if (status.equals("1")) {
                    for (int i = 0; i < state_all_details.getStateDetails().size(); i++) {

                        String state = state_all_details.getStateDetails().get(i).getStateName().toString();

                        stateList.add(state);

                    }
                    setAdapter(sp_Ho_su2_state, stateList);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }

    private void CallcityApi(String state) {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("state_name", state);

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
                cityList = new ArrayList<>();
                cityList.add("--Select city--");
                if (status.equals("1")) {
                    for (int i = 0; i < state_city_details.getStateDetails().size(); i++) {

                        String city = state_city_details.getStateDetails().get(i).getDistrictName().toString();

                        cityList.add(city);

                    }
                    setAdapter(sp_Ho_su2_city, cityList);
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
        sp_Ho_su2_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = sp_Ho_su2_state.getSelectedItem().toString();
                Log.e("State", state);
                id = sp_Ho_su2_state.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        cityList.clear();
                        pincodeList.clear();
                        setAdapter(sp_Ho_su2_city, cityList);
                        setAdapter(sp_Ho_su2_pincode, pincodeList);

                        CallcityApi(state);
                        setAdapter(sp_Ho_su2_city, cityList);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_Ho_su2_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = sp_Ho_su2_city.getSelectedItem().toString();
                Log.e("city", city);
                id = sp_Ho_su2_city.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        CallpincodeApi(city);
                        setAdapter(sp_Ho_su2_pincode, pincodeList);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void CallpincodeApi(String city) {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("district_name", city);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.GET_PINCODE_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                State_pincode_Details state_pincode_details = gson.fromJson(response, State_pincode_Details.class);

                String status = state_pincode_details.getSTATUS().toString().trim();
                pincodeList = new ArrayList<>();
                pincodeList.add("--Select pincode--");
                if (status.equals("1")) {
                    for (int i = 0; i < state_pincode_details.getStateDetails().size(); i++) {

                        String city = state_pincode_details.getStateDetails().get(i).getPincode().toString();

                        pincodeList.add(city);

                    }
                    setAdapter(sp_Ho_su2_pincode, pincodeList);
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }


    private void setAdapter(Spinner sp_Ho_su2_state, List<String> list_category) {
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
        sp_Ho_su2_state.setAdapter(arrayAdapter);
    }


    private void setListener() {


        btn_next_su2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hbuilding_no2 = et_Ho_su2_buildingno.getText().toString().trim();
                hstreet2 = et_Ho_su2_street.getText().toString().trim();
                hlandmark2 = et_Ho_su2_landmark.getText().toString().trim();
                harea2 = et_Ho_su2_area.getText().toString().trim();
                hstate2 = sp_Ho_su2_state.getSelectedItem().toString().trim();
                hlatitude2 = et_Ho_su2_latitude.getText().toString().trim();
                hlongitude2 = et_Ho_su2_longitude.getText().toString().trim();

                if (hbuilding_no2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  adress first...");
                    et_Ho_su2_buildingno.requestFocus();
                } else if (hstreet2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  street or colony first...");
                    et_Ho_su2_street.requestFocus();
                } else if (hlandmark2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  landmark first...");
                    et_Ho_su2_landmark.requestFocus();
                } else if (harea2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  area first...");
                    et_Ho_su2_area.requestFocus();
                } else if (hstate2.equals("--Select State--")) {
                    AppConstants.openErrorDialog(context, "Please choose  state first...");


                } else if (!hstate2.equals("--Select State--")) {
                    hcity2 = sp_Ho_su2_city.getSelectedItem().toString().trim();

                    if (hcity2.equals("--Select city--")) {
                        AppConstants.openErrorDialog(context, "Please choose  city first...");

                    } else {
                        hpincode2 = sp_Ho_su2_pincode.getSelectedItem().toString().trim();
                        if (hpincode2.length()==0) {
                            AppConstants.openErrorDialog(context, "Please choose  pincode first...");

                        } else {
                            if (hlatitude2.length()==0) {
                                AppConstants.openErrorDialog(context, "Please choose  latitude first...");

                            } else if (hlongitude2.equals("longitude")) {
                                AppConstants.openErrorDialog(context, "Please choose  longitude first...");

                            } else {
                                Log.e("pincode", hpincode2);
                                Intent intent = new Intent(Hospital_Signup2_Activity.this, Hospital_Signup3_Activity.class);
                                intent.putExtra("login_id", login_id);
                                intent.putExtra("email_id", email_id);
                                intent.putExtra("name", hname1);
                                intent.putExtra("owner_name", hownername1);
                                intent.putExtra("category", hcategory1);
                                intent.putExtra("type", htype1);
                                intent.putExtra("time", htime1);
                                intent.putExtra("mo", hmo1);
                                intent.putExtra("building_no", hbuilding_no2);
                                intent.putExtra("street", hstreet2);
                                intent.putExtra("landmark", hlandmark2);
                                intent.putExtra("area", harea2);
                                intent.putExtra("pincode", hpincode2);
                                intent.putExtra("city", hcity2);
                                intent.putExtra("state", hstate2);
                                intent.putExtra("latitude", hlatitude2);
                                intent.putExtra("longitude", hlongitude2);
                                startActivity(intent);


                            }
                        }
                    }
                }

            }
        });

        hs_s2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == 1) {



                        hlatitude2 = data.getStringExtra("latitude");
                        hlongitude2 = data.getStringExtra("longitude");
                        Log.e("latitude",hlatitude2);
                        Log.e("longitude",hlongitude2);
                        et_Ho_su2_latitude.setText(hlatitude2);
                        et_Ho_su2_longitude.setText(hlongitude2);


            }
    }


}
