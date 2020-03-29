package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Activity.ForgetPassword1Activity;
import com.example.mitul.hospitalfinder.Activity.ForgetPasswordActivity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalResponse;
import com.example.mitul.hospitalfinder.Model.State.State_All_Details;
import com.example.mitul.hospitalfinder.Model.State.State_city_Details;
import com.example.mitul.hospitalfinder.Model.State.State_pincode_Details;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Hospital_EditBasic_Details_Activity2 extends AppCompatActivity {

    EditText et_Ho_ed_bd_buildingno, et_Ho_ed_bd_street, et_Ho_ed_bd_landmark, et_Ho_ed_bd_area,
            et_Ho_ed_bd_pincode;
    Button btn_next_ed_bd1;
    Context context;
    ImageView hs_lg1_edit_basic2_back;
    Spinner sp_Ho_ed_bd_state, sp_Ho_ed_bd_city,sp_Ho_ed_bd_pincode;
    ArrayList<String> stateList, cityList,pincodeList;
    String hospital_id, hbuilding_no1, hstreet1, hlandmark1, harea1, hpincode1, hcity1, hstate1;
    String hname1, hownername1, hcategory1, htype1, htime1, hmo1;
    String hbuilding_no2, hstreet2, hlandmark2, harea2, hpincode2, hcity2, hstate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__edit_basic__details_2);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_EditBasic_Details_Activity2.this;
        et_Ho_ed_bd_buildingno = (EditText) findViewById(R.id.et_Ho_ed_bd_buildingno);
        et_Ho_ed_bd_street = (EditText) findViewById(R.id.et_Ho_ed_bd_street);
        et_Ho_ed_bd_landmark = (EditText) findViewById(R.id.et_Ho_ed_bd_landmark);
        et_Ho_ed_bd_area = (EditText) findViewById(R.id.et_Ho_ed_bd_area);
        hs_lg1_edit_basic2_back=(ImageView)findViewById(R.id.hs_lg1_edit_basic2_back);
      /*  et_Ho_su2_city = (EditText) findViewById(R.id.et_Ho_su2_city);
        et_Ho_su2_state = (EditText) findViewById(R.id.et_Ho_su2_state);*/

        btn_next_ed_bd1 = (Button) findViewById(R.id.btn_next_ed_bd1);
        sp_Ho_ed_bd_state = (Spinner) findViewById(R.id.sp_Ho_ed_bd_state);
        sp_Ho_ed_bd_city = (Spinner) findViewById(R.id.sp_Ho_ed_bd_city);
        sp_Ho_ed_bd_pincode = (Spinner) findViewById(R.id.sp_Ho_ed_bd_pincode);

        stateList = new ArrayList<>();
        cityList = new ArrayList<>();
        pincodeList = new ArrayList<>();
        CallOnlyGetStateApi();
        /*stateList.add("--Select State--");
        stateList.add("Gujarat");
        stateList.add("Maharashtra");
        stateList.add("Goa");
        stateList.add("Kolkata");
        stateList.add("Punjab");*/

        setAdapter(sp_Ho_ed_bd_state, stateList);

        onClickEventOfStateSpinner();


        hospital_id = getIntent().getStringExtra("hospital_id");
        hname1 = getIntent().getStringExtra("name");
        hownername1 = getIntent().getStringExtra("owner_name");
        hcategory1 = getIntent().getStringExtra("category");
        htype1 = getIntent().getStringExtra("type");
        htime1 = getIntent().getStringExtra("time");
        hmo1 = getIntent().getStringExtra("mo");
        hbuilding_no1 = getIntent().getStringExtra("building_no");
        hstreet1 = getIntent().getStringExtra("street");
        hlandmark1 = getIntent().getStringExtra("landmark");
        harea1 = getIntent().getStringExtra("area");
        hpincode1 = getIntent().getStringExtra("pincode");
        hcity1 = getIntent().getStringExtra("city");
        hstate1 = getIntent().getStringExtra("state");


        et_Ho_ed_bd_buildingno.setText(hbuilding_no1);
        et_Ho_ed_bd_street.setText(hstreet1);
        et_Ho_ed_bd_landmark.setText(hlandmark1);
        et_Ho_ed_bd_area.setText(harea1);
       /* ArrayAdapter myAdap = (ArrayAdapter) sp_Ho_ed_bd_state.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(hstate1);
        sp_Ho_ed_bd_state.setSelection(spinnerPosition);*/

       /* ArrayAdapter myAdap1 = (ArrayAdapter) sp_Ho_ed_bd_city.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition1 = myAdap.getPosition(hcity1);
        sp_Ho_ed_bd_city.setSelection(spinnerPosition);*/
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
                    setAdapter(sp_Ho_ed_bd_state, stateList);
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
                    setAdapter(sp_Ho_ed_bd_city, cityList);
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
        sp_Ho_ed_bd_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String state = sp_Ho_ed_bd_state.getSelectedItem().toString();
                Log.e("State", state);
                id = sp_Ho_ed_bd_state.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        cityList.clear();
                        pincodeList.clear();
                        setAdapter(sp_Ho_ed_bd_city, cityList);
                        setAdapter(sp_Ho_ed_bd_pincode, pincodeList);

                        CallcityApi(state);
                        setAdapter(sp_Ho_ed_bd_city, cityList);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_Ho_ed_bd_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = sp_Ho_ed_bd_city.getSelectedItem().toString();
                Log.e("city", city);
                id = sp_Ho_ed_bd_city.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        CallpincodeApi(city);
                        setAdapter(sp_Ho_ed_bd_pincode, pincodeList);
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
                    setAdapter(sp_Ho_ed_bd_pincode, pincodeList);
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


        btn_next_ed_bd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hbuilding_no2 = et_Ho_ed_bd_buildingno.getText().toString().trim();
                hstreet2 = et_Ho_ed_bd_street.getText().toString().trim();
                hlandmark2 = et_Ho_ed_bd_landmark.getText().toString().trim();
                harea2 = et_Ho_ed_bd_area.getText().toString().trim();
                hstate2 = sp_Ho_ed_bd_state.getSelectedItem().toString().trim();

                if (hbuilding_no2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  adress first...");
                    et_Ho_ed_bd_buildingno.requestFocus();
                } else if (hstreet2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  street or colony first...");
                    et_Ho_ed_bd_street.requestFocus();
                } else if (hlandmark2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  landmark first...");
                    et_Ho_ed_bd_landmark.requestFocus();
                } else if (harea2.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  area first...");
                    et_Ho_ed_bd_area.requestFocus();
                }  else if (hstate2.equals("--Select State--")) {
                    AppConstants.openErrorDialog(context, "Please choose  state first...");


                } else if (!hstate2.equals("--Select State--")) {
                    hcity2 = sp_Ho_ed_bd_city.getSelectedItem().toString().trim();

                    if (hcity2.equals("--Select city--")) {
                        AppConstants.openErrorDialog(context, "Please choose  city first...");

                    } else {
                        /*if (hlatitude2.length() == 0) {
                            AppConstants.openErrorDialog(context, "Please enter  latitude first...");
                            et_Ho_su2_latitude.requestFocus();
                        } else if (hlongitude2.length() == 0) {
                            AppConstants.openErrorDialog(context, "Please enter  longitude first...");
                            et_Ho_su2_longitude.requestFocus();
                        } else {*/
                        hpincode2 = sp_Ho_ed_bd_city.getSelectedItem().toString().trim();

                        if(hpincode2.equals("--Select pincode--"))
                        {
                            AppConstants.openErrorDialog(context, "Please choose  pincode first...");

                        }else{
                            CallHospitalBasicDetailsApi();

                        }




                    }
                }

            }
        });
        hs_lg1_edit_basic2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void CallHospitalBasicDetailsApi() {
        AppConstants.showDialog(context);

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_BASIC_DETAILS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                AppConstants.dissmissDialog();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");
                    if (status.equals("1")) {

                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.dialog_messge);
                        dialog.setCancelable(false);

                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        final Button btn_done = (Button) dialog.findViewById(R.id.btn_done1);
                        TextView txt_error_msg = (TextView) dialog.findViewById(R.id.txt_error_msg1);
                        txt_error_msg.setText("Updated");
                        btn_done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Hospital_EditBasic_Details_Activity1.heda1.finish();
                                finish();                            }
                        });
                        dialog.show();
                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Not updated....");

                    } else if (status.equals("00")) {
                       AppConstants.openErrorDialog(context, "Error.");


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


        smr.addStringParam("hospital_id", hospital_id);
        smr.addStringParam("name", hname1);
        smr.addStringParam("owner_name", hownername1);
        smr.addStringParam("category", hcategory1);
        smr.addStringParam("type", htype1);
        smr.addStringParam("mobile_number", hmo1);
        smr.addStringParam("time", htime1);
        smr.addStringParam("building_no", hbuilding_no2);
        smr.addStringParam("colony", hstreet2);
        smr.addStringParam("land_mark", hlandmark2);
        smr.addStringParam("area", harea2);
        smr.addStringParam("pincode", hpincode2);
        smr.addStringParam("city", hcity2);
        smr.addStringParam("state", hstate2);


        Log.e("hospital_id", hospital_id);

        Log.e("hname6", hname1);
        Log.e("hownername6", hownername1);
        Log.e("hcategory6", hcategory1);
        Log.e("htype6", htype1);
        Log.e("htime6", htime1);
        Log.e("hmo6", hmo1);
        Log.e("hbuilding_no6", hbuilding_no2);
        Log.e("hstreet6", hstreet2);
        Log.e("hlandmark6", hlandmark2);
        Log.e("harea6", harea2);
        Log.e("hpincode6", hpincode2);
        Log.e("hcity6", hcity2);
        Log.e("hstate6", hstate2);

        MyApplication.getInstance().addToRequestQueue(smr);
    }
}
