package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpPatientResponse.SignUpPatientResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Patient_Signup_Activity extends AppCompatActivity {
    String login_id, email_id, first_name, middle_name, last_name, date_of_birth, mobile_number;
    Context context;
    EditText et_signup_patient_fname, et_signup_patient_mname, et_signup_patient_lname,
             et_signup_patient_mo_no;
    Button btn_signup_patient,btn_date_patient;
    ImageView pt_s_back;

    TextView et_signup_patient_dob;
    public static Patient_Signup_Activity patient_signup_activity;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__signup_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }


    private void initView() {
        getSupportActionBar().hide();

        context = Patient_Signup_Activity.this;
        patient_signup_activity = Patient_Signup_Activity.this;
        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        et_signup_patient_fname = (EditText) findViewById(R.id.et_signup_patient_fname);
        et_signup_patient_mname = (EditText) findViewById(R.id.et_signup_patient_mname);
        et_signup_patient_lname = (EditText) findViewById(R.id.et_signup_patient_lname);
        et_signup_patient_dob = (TextView) findViewById(R.id.et_signup_patient_dob);
        et_signup_patient_mo_no = (EditText) findViewById(R.id.et_signup_patient_mo_no);
        btn_signup_patient = (Button) findViewById(R.id.btn_signup_patient);
        btn_date_patient=(Button)findViewById(R.id.btn_date_patient);
        pt_s_back = (ImageView) findViewById(R.id.pt_s_back);


    }

    private void setListener() {
        btn_signup_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = et_signup_patient_fname.getText().toString().trim();
                middle_name = et_signup_patient_mname.getText().toString().trim();
                last_name = et_signup_patient_lname.getText().toString().trim();
                date_of_birth = et_signup_patient_dob.getText().toString().trim();
                mobile_number = et_signup_patient_mo_no.getText().toString().trim();

                if (first_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter First name first...");
                    et_signup_patient_fname.requestFocus();

                } else if (middle_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Middle name first...");
                    et_signup_patient_mname.requestFocus();

                } else if (last_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter last name first...");
                    et_signup_patient_lname.requestFocus();

                } else if (date_of_birth.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter date of birth name first...");
                    et_signup_patient_dob.requestFocus();

                } else if (mobile_number.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter mobile name first...");
                    et_signup_patient_mo_no.requestFocus();

                } else {
                    if (isValidMobile(mobile_number) == true) {
                        CallSignUpPatientApi();
                    } else {
                        AppConstants.openErrorDialog(context, "Please enter valid mobile number ...");
                        et_signup_patient_mo_no.requestFocus();


                    }


                }


            }
        });
        pt_s_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calldelteApi();

            }
        });
        btn_date_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {


                            @SuppressLint("WrongConstant")
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Toast.makeText(context,year + "/" + month  + "/" + dayOfMonth,0).show();
                                et_signup_patient_dob.setText(year + "/" + month  + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        calldelteApi();

    }

    private void CallSignUpPatientApi() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("login_id", login_id);
        requestParams.put("email_id", email_id);
        requestParams.put("first_name", first_name);
        requestParams.put("middle_name", middle_name);
        requestParams.put("last_name", last_name);
        requestParams.put("date_of_birth", date_of_birth);
        requestParams.put("mobile_number", mobile_number);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.SIGNUP_PATIENT_URL, requestParams, new AsyncHttpResponseHandler() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response SignUPpatient", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                SignUpPatientResponse signUpPatientResponse = gson.fromJson(response, SignUpPatientResponse.class);
                String status = signUpPatientResponse.getSTATUS().toString().trim();
                String msg = signUpPatientResponse.getMSG().toString().trim();

                if (status.equals("1")) {
                    Intent intent = new Intent(Patient_Signup_Activity.this, SignUpSucessActivity.class);
                    intent.putExtra("signuptype","patient");
                    startActivity(intent);
                    Toast.makeText(context, "signup Sucess", 0);
                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "email already exists");

                } else {
                    AppConstants.openErrorDialog(context, "some error");

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    private void calldelteApi() {
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.DELTE_LOGIN_ENTRY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Res hos_up_medicne", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {
                        finish();

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Medicine not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Field not set");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.openErrorDialog(context, "Some error");
            }
        });


        smr.addStringParam("login_id", login_id);


        MyApplication.getInstance().addToRequestQueue(smr);


    }
}
