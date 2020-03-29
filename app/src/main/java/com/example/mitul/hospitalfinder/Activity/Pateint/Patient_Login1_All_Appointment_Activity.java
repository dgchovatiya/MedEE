package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Appointment_ViewPage_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_ViewPage_Adpter;
import com.example.mitul.hospitalfinder.R;

public class Patient_Login1_All_Appointment_Activity extends AppCompatActivity {
    Context context;
    String patient_id;
    TabLayout patient_appointment_tablayout;
    ViewPager patient_appointment_tablayout_viewpager;
    ImageView pt_lg1_Appointment_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login1__all__appointment_);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.admin_color)); //status bar or the time bar at the top
        }
        getSupportActionBar().hide();

        initview();
        setListner();
    }
    private void initview() {
        context=Patient_Login1_All_Appointment_Activity.this;
        patient_id=getIntent().getStringExtra("patient_id");
        patient_appointment_tablayout = (TabLayout) findViewById(R.id.patient_appointment_tablayout);
        patient_appointment_tablayout_viewpager = (ViewPager) findViewById(R.id.patient_appointment_tablayout_viewpager);
        pt_lg1_Appointment_back=(ImageView)findViewById(R.id.pt_lg1_Appointment_back);

        patient_appointment_tablayout.addTab(patient_appointment_tablayout.newTab().setText("PENDING REQUEST"));
        patient_appointment_tablayout.addTab(patient_appointment_tablayout.newTab().setText("CONFIRM  REQUEST"));
        patient_appointment_tablayout.addTab(patient_appointment_tablayout.newTab().setText("REJECTED  REQUEST"));

        Patient_Appointment_ViewPage_Adapter patient_order_blood_viewPage_adpter  = new Patient_Appointment_ViewPage_Adapter(getSupportFragmentManager(), patient_appointment_tablayout.getTabCount(),context,patient_id);
        patient_appointment_tablayout_viewpager.setAdapter(patient_order_blood_viewPage_adpter);

        patient_appointment_tablayout_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(patient_appointment_tablayout));

        patient_appointment_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                patient_appointment_tablayout_viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setListner() {
        pt_lg1_Appointment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
