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

public class Hospital_Signup3_Activity extends AppCompatActivity {

    EditText et_hs_su3_medicalfacilities;
    Context context;
    ImageView hs_s3_back;

    Button btn_next_su3;
    String hbuilding_no3, hstreet3, hlandmark3, harea3, hpincode3, hcity3, hstate3, hlatitude3, hlongitude3;
    String hname3, hownername3, hcategory3, htype3, htime3, hmo3;
    String hmedical_facilities;
    String login_id, email_id;
    public static Hospital_Signup3_Activity hospital_signup3_activity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup3_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Signup3_Activity.this;
        hospital_signup3_activity = Hospital_Signup3_Activity.this;
        et_hs_su3_medicalfacilities = (EditText) findViewById(R.id.et_hs_su3_medicalfacilities);
        btn_next_su3 = (Button) findViewById(R.id.btn_next_su3);
        hs_s3_back = (ImageView) findViewById(R.id.hs_s3_back);


        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname3 = getIntent().getStringExtra("name");
        hownername3 = getIntent().getStringExtra("owner_name");
        hcategory3 = getIntent().getStringExtra("category");
        htype3 = getIntent().getStringExtra("type");
        htime3 = getIntent().getStringExtra("time");
        hmo3 = getIntent().getStringExtra("mo");
        hbuilding_no3 = getIntent().getStringExtra("building_no");
        hstreet3 = getIntent().getStringExtra("street");
        hlandmark3 = getIntent().getStringExtra("landmark");
        harea3 = getIntent().getStringExtra("area");
        hpincode3 = getIntent().getStringExtra("pincode");
        hcity3 = getIntent().getStringExtra("city");
        hstate3 = getIntent().getStringExtra("state");
        hlatitude3 = getIntent().getStringExtra("latitude");
        hlongitude3 = getIntent().getStringExtra("longitude");

    }

    private void setListener() {
        btn_next_su3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hmedical_facilities = et_hs_su3_medicalfacilities.getText().toString().trim();
                if (hmedical_facilities.length() == 0) {

                    AppConstants.openErrorDialog(context, "Please enter Medical Facilities first...");
                    et_hs_su3_medicalfacilities.requestFocus();
                } else {

                    Intent intent = new Intent(Hospital_Signup3_Activity.this, Hospital_Signup4_Activity.class);
                    intent.putExtra("login_id", login_id);
                    intent.putExtra("email_id", email_id);
                    intent.putExtra("name", hname3);
                    intent.putExtra("owner_name", hownername3);
                    intent.putExtra("category", hcategory3);
                    intent.putExtra("type", htype3);
                    intent.putExtra("time", htime3);
                    intent.putExtra("mo", hmo3);
                    intent.putExtra("building_no", hbuilding_no3);
                    intent.putExtra("street", hstreet3);
                    intent.putExtra("landmark", hlandmark3);
                    intent.putExtra("area", harea3);
                    intent.putExtra("pincode", hpincode3);
                    intent.putExtra("city", hcity3);
                    intent.putExtra("state", hstate3);
                    intent.putExtra("latitude", hlatitude3);
                    intent.putExtra("longitude", hlongitude3);
                    intent.putExtra("medical_facilities", hmedical_facilities);
                    startActivity(intent);
                }

            }
        });

        hs_s3_back.setOnClickListener(new View.OnClickListener() {
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
