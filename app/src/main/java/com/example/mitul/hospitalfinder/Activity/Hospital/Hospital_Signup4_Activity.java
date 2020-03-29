package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

public class Hospital_Signup4_Activity extends AppCompatActivity {
    EditText et_hs_su4_about;
    Button btn_next_su4;
    ImageView hs_s4_back;

    Context context;
    String hbuilding_no4, hstreet4, hlandmark4, harea4, hpincode4, hcity4, hstate4, hlatitude4, hlongitude4;
    String hname4, hownername4, hcategory4, htype4, htime4, hmo4;
    String hmedical_facilities4;
    String habout;
    String login_id, email_id;

    public static Hospital_Signup4_Activity hospital_signup4_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup4_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Signup4_Activity.this;
        hospital_signup4_activity=Hospital_Signup4_Activity.this;
        et_hs_su4_about = (EditText) findViewById(R.id.et_hs_su4_about);
        btn_next_su4 = (Button) findViewById(R.id.btn_next_su4);
        hs_s4_back = (ImageView) findViewById(R.id.hs_s4_back);


        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname4 = getIntent().getStringExtra("name");
        hownername4 = getIntent().getStringExtra("owner_name");
        hcategory4 = getIntent().getStringExtra("category");
        htype4 = getIntent().getStringExtra("type");
        htime4 = getIntent().getStringExtra("time");
        hmo4 = getIntent().getStringExtra("mo");
        hbuilding_no4 = getIntent().getStringExtra("building_no");
        hstreet4 = getIntent().getStringExtra("street");
        hlandmark4 = getIntent().getStringExtra("landmark");
        harea4 = getIntent().getStringExtra("area");
        hpincode4 = getIntent().getStringExtra("pincode");
        hcity4 = getIntent().getStringExtra("city");
        hstate4 = getIntent().getStringExtra("state");
        hlatitude4 = getIntent().getStringExtra("latitude");
        hlongitude4 = getIntent().getStringExtra("longitude");
        hmedical_facilities4 = getIntent().getStringExtra("medical_facilities");
    }

    private void setListener() {
        btn_next_su4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                habout = et_hs_su4_about.getText().toString().trim();
                if (habout.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter About hospital first...");
                    et_hs_su4_about.requestFocus();
                } else {

                    Intent intent = new Intent(Hospital_Signup4_Activity.this, Hospital_Signup5_Activity.class);
                    intent.putExtra("login_id", login_id);
                    intent.putExtra("email_id", email_id);
                    intent.putExtra("name", hname4);
                    intent.putExtra("owner_name", hownername4);
                    intent.putExtra("category", hcategory4);
                    intent.putExtra("type", htype4);
                    intent.putExtra("time", htime4);
                    intent.putExtra("mo", hmo4);
                    intent.putExtra("building_no", hbuilding_no4);
                    intent.putExtra("street", hstreet4);
                    intent.putExtra("landmark", hlandmark4);
                    intent.putExtra("area", harea4);
                    intent.putExtra("pincode", hpincode4);
                    intent.putExtra("city", hcity4);
                    intent.putExtra("state", hstate4);
                    intent.putExtra("latitude", hlatitude4);
                    intent.putExtra("longitude", hlongitude4);
                    intent.putExtra("medical_facilities", hmedical_facilities4);
                    intent.putExtra("about", habout);
                    startActivity(intent);
                }

            }
        });
        hs_s4_back.setOnClickListener(new View.OnClickListener() {
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

}

