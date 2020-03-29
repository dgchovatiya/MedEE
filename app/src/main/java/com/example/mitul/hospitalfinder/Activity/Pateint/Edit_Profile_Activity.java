package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Login_Response;
import com.example.mitul.hospitalfinder.Model.SignUpPatientResponse.SignUpPatientResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Edit_Profile_Activity extends AppCompatActivity {
    String  first_name, middle_name, last_name, date_of_birth, mobile_number;
    Context context;
    EditText et_esignup_patient_fname, et_esignup_patient_mname, et_esignup_patient_lname,
            et_esignup_patient_mo_no;
    Button btn_esignup_patient, btn_edate_patient;
    ImageView pt_es_back;

    String login_id,patient_id,fname,mname,lname,dob,mo;
    TextView et_esignup_patient_dob;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Edit_Profile_Activity.this;

        et_esignup_patient_fname = (EditText) findViewById(R.id.et_esignup_patient_fname);
        et_esignup_patient_mname = (EditText) findViewById(R.id.et_esignup_patient_mname);
        et_esignup_patient_lname = (EditText) findViewById(R.id.et_esignup_patient_lname);
        et_esignup_patient_dob = (TextView) findViewById(R.id.et_esignup_patient_dob);
        et_esignup_patient_mo_no = (EditText) findViewById(R.id.et_esignup_patient_mo_no);
        btn_esignup_patient = (Button) findViewById(R.id.btn_esignup_patient);
        btn_edate_patient = (Button) findViewById(R.id.btn_edate_patient);
        pt_es_back = (ImageView) findViewById(R.id.pt_es_back);
        login_id=getIntent().getStringExtra("login_id");
        callPAtientDetailsApi();


    }

    private void callPAtientDetailsApi() {
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
                        fname = patient_login_response.getPatientdetails().get(i).getFirstName().toString().trim();
                        mname = patient_login_response.getPatientdetails().get(i).getMiddleName().toString().trim();
                        lname = patient_login_response.getPatientdetails().get(i).getLastName().toString().trim();
                        dob = patient_login_response.getPatientdetails().get(i).getDateOfBirth().toString().trim();
                        mo = patient_login_response.getPatientdetails().get(i).getMobileNumber().toString().trim();

                        et_esignup_patient_fname.setText(fname);
                        et_esignup_patient_mname.setText(mname);
                        et_esignup_patient_lname.setText(lname);
                        et_esignup_patient_dob.setText(dob);
                        et_esignup_patient_mo_no.setText(mo);
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

    private void setListener() {
        btn_esignup_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = et_esignup_patient_fname.getText().toString().trim();
                middle_name = et_esignup_patient_mname.getText().toString().trim();
                last_name = et_esignup_patient_lname.getText().toString().trim();
                date_of_birth = et_esignup_patient_dob.getText().toString().trim();
                mobile_number = et_esignup_patient_mo_no.getText().toString().trim();

                if (first_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter First name first...");
                    et_esignup_patient_fname.requestFocus();

                } else if (middle_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Middle name first...");
                    et_esignup_patient_mname.requestFocus();

                } else if (last_name.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter last name first...");
                    et_esignup_patient_lname.requestFocus();

                } else if (date_of_birth.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter date of birth name first...");
                    et_esignup_patient_dob.requestFocus();

                } else if (mobile_number.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter mobile number first...");
                    et_esignup_patient_mo_no.requestFocus();

                } else {
                    if (isValidMobile(mobile_number) == true) {
                        CallUpdatePatientApi();
                    } else {
                        AppConstants.openErrorDialog(context, "Please enter valid mobile number ...");
                        et_esignup_patient_mo_no.requestFocus();


                    }


                }


            }
        });
        pt_es_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_edate_patient.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(context, year + "/" + month + "/" + dayOfMonth, 0).show();
                                et_esignup_patient_dob.setText(year + "/" + month + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }

    private void CallUpdatePatientApi() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id",patient_id);
        requestParams.put("first_name", first_name);
        requestParams.put("middle_name", middle_name);
        requestParams.put("last_name", last_name);
        requestParams.put("date_of_birth", date_of_birth);
        requestParams.put("mobile_number", mobile_number);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_UPDATE_DETAILS, requestParams, new AsyncHttpResponseHandler() {
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
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_messge);
                    dialog.setCancelable(false);

                    Window window = dialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    final Button btn_done = (Button) dialog.findViewById(R.id.btn_done1);
                    TextView txt_error_msg = (TextView) dialog.findViewById(R.id.txt_error_msg1);
                    txt_error_msg.setText("Sucess Fully Updated");
                    btn_done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                            finish();
                        }
                    });
                    dialog.show();
                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "email already exists");

                } else {
                    AppConstants.openErrorDialog(context, "some error");

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }
}
