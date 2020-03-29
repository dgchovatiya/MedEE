package com.example.mitul.hospitalfinder.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup1_Activity;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Signup_Activity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.SignUpCommonResponse.SignUpCommonResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMail;
import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidPassword;

public class SignUpCommonActivity extends AppCompatActivity {
    RadioGroup common_signup_rg;
    EditText et_common_signup_email_id, et_common_signup_password, et_common_signup_confirm_password;
    Button btn_next_signup;
    Context context;
    ImageView signup_common_back;
    String email_id, password, category;
    public static SignUpCommonActivity signUpCommonActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_common);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();

    }

    private void initView() {
        getSupportActionBar().hide();

        context = SignUpCommonActivity.this;
        signUpCommonActivity = SignUpCommonActivity.this;
        et_common_signup_email_id = (EditText) findViewById(R.id.et_common_signup_email_id);
        et_common_signup_password = (EditText) findViewById(R.id.et_common_signup_password);
        et_common_signup_confirm_password = (EditText) findViewById(R.id.et_common_signup_confirm_password);
        common_signup_rg = (RadioGroup) findViewById(R.id.common_signup_rg);
        btn_next_signup = (Button) findViewById(R.id.btn_next_signup);
        signup_common_back = (ImageView) findViewById(R.id.signup_common_back);
    }

    private void setListener() {
        btn_next_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_id = et_common_signup_email_id.getText().toString().trim();
                password = et_common_signup_password.getText().toString().trim();

                int selectedid = common_signup_rg.getCheckedRadioButtonId();
                RadioButton rgtype = (RadioButton) findViewById(selectedid);
                category = rgtype.getText().toString();
                Log.e("category", category);
                if (category.equals("Patient")) {
                    category = "patient";
                } else if (category.equals("Hospital Receptiontist")) {
                    category = "hospital";
                }
                Log.e("category", category);

                if (email_id.equals("Admin@gmail.com")) {
                    AppConstants.openErrorDialog(context, "Please choose another email...");


                } else if (email_id.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter email First...");
                    et_common_signup_email_id.requestFocus();

                } else if (password.length() == 0) {

                    AppConstants.openErrorDialog(context, "Please enter password First...");
                    et_common_signup_password.requestFocus();

                } else if (et_common_signup_confirm_password.getText().length() == 0) {

                    AppConstants.openErrorDialog(context, "Please enter confirm password First...");
                    et_common_signup_confirm_password.requestFocus();

                } else {
                    if (password.equals(et_common_signup_confirm_password.getText().toString().trim())) {
                        if (!isValidMail(email_id)) {
                            et_common_signup_email_id.setError("Email Must be Contain @gmail.com");
                            et_common_signup_email_id.setText("");
                            et_common_signup_email_id.requestFocus();

                        } else if (!isValidPassword(password)) {
                            et_common_signup_password.setError("Password Must be Contain 1 Uppercase,1 Lowercase,1 numeric,1 special character");
                            et_common_signup_password.setText("");
                            et_common_signup_confirm_password.setText("");
                            et_common_signup_password.requestFocus();

                        } else {
                            CallSignUpCommonApi();
                        }
                    } else {
                        et_common_signup_confirm_password.setError("Do not match confirm password");
                        et_common_signup_confirm_password.setText("");
                        et_common_signup_confirm_password.requestFocus();

                    }
                }


            }
        });
        signup_common_back.setOnClickListener(new View.OnClickListener() {
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

    private void CallSignUpCommonApi() {


        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("email_id", email_id);
        requestParams.put("password", password);
        requestParams.put("category", category);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.SIGNUP_COMMON_URL, requestParams, new AsyncHttpResponseHandler() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response SignUPCommon", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                SignUpCommonResponse signUpCommonResponse = gson.fromJson(response, SignUpCommonResponse.class);
                String status = signUpCommonResponse.getSTATUS().toString();
                String msg = signUpCommonResponse.getMSG().toString();

                if (status.equals("1")) {
                    String login_id = signUpCommonResponse.getLoginId().toString().trim();
                    String email_id = signUpCommonResponse.getEmailId().toString().trim();

                    if (category.equals("patient")) {

                        Intent intent = new Intent(SignUpCommonActivity.this, Patient_Signup_Activity.class);
                        intent.putExtra("login_id", login_id);
                        intent.putExtra("email_id", email_id);
                        startActivity(intent);

                        Toast.makeText(context, "signupcommonsucessPatient", 0);
                    } else if (category.equals("hospital")) {
                        Intent intent = new Intent(SignUpCommonActivity.this, Hospital_Signup1_Activity.class);
                        intent.putExtra("login_id", login_id);
                        intent.putExtra("email_id", email_id);
                        startActivity(intent);
                        Toast.makeText(context, "signupcommonsucessHosppital", 0);

                    }


                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Email already exists...");
                    et_common_signup_email_id.setText("");
                    et_common_signup_password.setText("");
                    et_common_signup_confirm_password.setText("");
                    et_common_signup_email_id.requestFocus();
                } else {
                    et_common_signup_email_id.setText("");
                    et_common_signup_password.setText("");
                    et_common_signup_confirm_password.setText("");
                    et_common_signup_email_id.requestFocus();
                    AppConstants.openErrorDialog(context, "some error..");

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context, "Error........");
            }
        });
    }


}
