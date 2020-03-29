package com.example.mitul.hospitalfinder.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mitul.hospitalfinder.Activity.Admin.AdminMainActivity;
import com.example.mitul.hospitalfinder.Activity.Doctor.Doctor_Login_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Login1_Activity;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Login1_Activity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.LoginActivityResponse.LoginApiResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.net.SocketTimeoutException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMail;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_loginemail, et_loginpassword;
        Context context;
    TextView txt_sign_up, txt_forgetpwd;
    RadioGroup login_radiao_type;
    String category;
    int PERMISSION_ALL = 1;
    boolean doubleBackToExitPressedOnce = false;

    String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void initView() {
        getSupportActionBar().hide();

        context = LoginActivity.this;

        btn_login = (Button) findViewById(R.id.btn_login);
        et_loginemail = (EditText) findViewById(R.id.et_loginemail);
        et_loginpassword = (EditText) findViewById(R.id.et_loginpassword);
        et_loginemail.setText(null);
        et_loginpassword.setText(null);
        txt_sign_up = (TextView) findViewById(R.id.txt_sign_up);
        txt_forgetpwd = (TextView) findViewById(R.id.txt_forgetpwd);
        login_radiao_type = (RadioGroup) findViewById(R.id.login_radiao_type);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void setListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedid = login_radiao_type.getCheckedRadioButtonId();
                RadioButton rgtype = (RadioButton) findViewById(selectedid);
                category = rgtype.getText().toString();
                if(category.equals("Patient")){
                    category="patient";
                }else if(category.equals("Hospital Receptiontist"))
                {
                    category="hospital";
                }
                else if(category.equals("Doctor")){
                    category="docter";
                }
                Log.e("category", category);


                if (et_loginemail.getText().length() == 0) {
                    AppConstants.openErrorDialog(context, "Please Enter EmailId First...");
                } else if (et_loginpassword.getText().length() == 0) {
                    AppConstants.openErrorDialog(context, "Please Enter Password First...");
                } else {
                    String email = et_loginemail.getText().toString().trim();
                    String password = et_loginpassword.getText().toString().trim();

                    if (AppConstants.isNetworkAvailable(context)) {
                        if (email.equals("Admin@gmail.com") && password.equals("Admin123")) {
                            //Admin intnet
                            // AppConstants.setData(context, AppData.LOGIN_STATUS,"1");
                            Intent intent = new Intent(context, AdminMainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            if (!isValidMail(email)) {
                                AppConstants.openErrorDialog(context, "Inalid Email");
                            } else {
                                CallLoginAPI();

                            }

                        }
                    } else {
                        Log.e("Internet", "Not Available");
                    }
                }
            }
        });
        txt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpCommonActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txt_forgetpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });

    }


    private void CallLoginAPI() {



       AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("email_id", et_loginemail.getText().toString());
        requestParams.put("password", et_loginpassword.getText().toString());
        requestParams.put("category", category);

       /* AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.setTimeout(3*1000);


        asyncHttpClient.setMaxConnections(6);
        asyncHttpClient.setMaxRetriesAndTimeout(6,3*100);
        asyncHttpClient.post(URL.LOGIN_API_URL, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("ResponseLogin", response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                Log.e("Hello", "onFailure: "+error+"               "+responseBody );
                Log.e("stack trace",error.getMessage()+"    "+error.getStackTrace().toString() );
                AppConstants.openErrorDialog(context, "Error in Api Respponse");
            }
        });*/
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.post(context, URL.LOGIN_API_URL, requestParams, new AsyncHttpResponseHandler() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("ResponseLogin", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                LoginApiResponse loginApiResponse = gson.fromJson(response, LoginApiResponse.class);

                String status = loginApiResponse.getSTATUS().toString();
                String msg = loginApiResponse.getMSG().toString();
                Log.e("stauts and msg", status + msg);
                if (status.equals("1")) {
                    String login_id = loginApiResponse.getUserDetails().getLoginId().toString();
                    String email = loginApiResponse.getUserDetails().getEmailId().toString();
                    String category_rs = loginApiResponse.getUserDetails().getCategory().toString();
                    Log.e("login_id em ca", login_id + email + category_rs);
                    if (category_rs.equals("patient")) {

                        Intent intent = new Intent(LoginActivity.this, Patient_Login1_Activity.class);
                        intent.putExtra("login_id",login_id);
                        intent.putExtra("email_id", email);
                        startActivity(intent);
                        Toast.makeText(context, "patient", 0).show();

                    } else if (category_rs.equals("hospital")) {
                        Intent intent = new Intent(LoginActivity.this, Hospital_Login1_Activity.class);
                        intent.putExtra("login_id", login_id);
                        intent.putExtra("email_id",email);
                        startActivity(intent);
                        Toast.makeText(context, "hospital", 0).show();


                    }
                    else if (category_rs.equals("docter")) {
                        Intent intent = new Intent(LoginActivity.this, Doctor_Login_Activity.class);
                        intent.putExtra("login_id", login_id);
                        intent.putExtra("email_id",email);
                        startActivity(intent);
                        Toast.makeText(context, "hospital", 0).show();


                    }


                } else if (status.equals("0")) {
                    // Log.e("Password", "incorrect");

                    //logout
                    //   AppConstants.setData(context, AppData.LOGIN_STATUS, "0");

                    AppConstants.openErrorDialog(context, "Incorrect Password...");
                    et_loginpassword.setText("");
                    et_loginpassword.requestFocus();


                } else {
                    AppConstants.openErrorDialog(context, "Invalid Email or Password...");
                    et_loginpassword.setText("");
                    et_loginemail.setText("");
                    et_loginemail.requestFocus();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                Log.e("Hello", "onFailure: "+error+"               "+responseBody );

                AppConstants.openErrorDialog(context, "Error in Api Respponse");
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        et_loginemail.setText("");
        et_loginpassword.setText("");
        et_loginemail.requestFocus();
    }
}
