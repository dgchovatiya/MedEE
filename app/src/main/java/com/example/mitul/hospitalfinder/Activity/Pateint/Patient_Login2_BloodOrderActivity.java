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
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_ViewPage_Adpter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_ViewPage_Adpter;
import com.example.mitul.hospitalfinder.R;

public class Patient_Login2_BloodOrderActivity extends AppCompatActivity {
    Context context;
    String patient_id;
    TabLayout patient_order_blood_tablayout;
    ViewPager patient_order_blood_viewpager;
    ImageView pt_lg2_order_blood_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login2__blood_order);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.admin_color)); //status bar or the time bar at the top
        }
        getSupportActionBar().hide();

        initview();
        setListner();
    }

    private void initview() {
        context = Patient_Login2_BloodOrderActivity.this;
        patient_id = getIntent().getStringExtra("patient_id");
        patient_order_blood_tablayout = (TabLayout) findViewById(R.id.patient_order_blood_tablayout);
        patient_order_blood_viewpager = (ViewPager) findViewById(R.id.patient_order_blood_viewpager);
        pt_lg2_order_blood_back = (ImageView) findViewById(R.id.pt_lg2_order_blood_back);

        patient_order_blood_tablayout.addTab(patient_order_blood_tablayout.newTab().setText("PENDING REQUEST"));
        patient_order_blood_tablayout.addTab(patient_order_blood_tablayout.newTab().setText("CONFIRM  REQUEST"));
        patient_order_blood_tablayout.addTab(patient_order_blood_tablayout.newTab().setText("REJECTED  REQUEST"));

        for (int i = 0; i < patient_order_blood_tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = patient_order_blood_tablayout.getTabAt(i);
            tab.setCustomView(R.layout.badged_tab);

        }
        Patient_Order_Blood_ViewPage_Adpter patient_order_blood_viewPage_adpter = new Patient_Order_Blood_ViewPage_Adpter(getSupportFragmentManager(), patient_order_blood_tablayout.getTabCount(), context, patient_id);
        patient_order_blood_viewpager.setAdapter(patient_order_blood_viewPage_adpter);

        patient_order_blood_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(patient_order_blood_tablayout));

        patient_order_blood_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                patient_order_blood_viewpager.setCurrentItem(tab.getPosition());
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
        pt_lg2_order_blood_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
