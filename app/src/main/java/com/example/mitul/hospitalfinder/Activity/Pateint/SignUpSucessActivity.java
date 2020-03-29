package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup1_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup2_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup3_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup4_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup5_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup6_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_SignupDocterDetails;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup_Set_Loctaion_Activity;
import com.example.mitul.hospitalfinder.Activity.LoginActivity;
import com.example.mitul.hospitalfinder.Activity.SignUpCommonActivity;
import com.example.mitul.hospitalfinder.R;

public class SignUpSucessActivity extends AppCompatActivity {
    Button back_to_login;
    Context context;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_sucess);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();
        context = SignUpSucessActivity.this;
        back_to_login = (Button) findViewById(R.id.back_to_login);
        type = getIntent().getStringExtra("signuptype");

    }

    private void setListener() {
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("hospital")) {

                    SignUpCommonActivity.signUpCommonActivity.finish();
                    Hospital_Signup1_Activity.hospital_signup1_activity.finish();
                    Hospital_Signup2_Activity.hospital_signup2_activity.finish();
                    Hospital_Signup3_Activity.hospital_signup3_activity.finish();
                    Hospital_Signup4_Activity.hospital_signup4_activity.finish();
                    Hospital_Signup5_Activity.hospital_signup5_activity.finish();
                    Hospital_Signup6_Activity.hospital_signup6_activity.finish();
                    Hospital_SignupDocterDetails.hospital_signupDocterDetails.finish();
                } else if (type.equals("patient")) {
                    SignUpCommonActivity.signUpCommonActivity.finish();
                    Patient_Signup_Activity.patient_signup_activity.finish();
                }
                Intent intent = new Intent(SignUpSucessActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
