package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.R;

public class Patient_Login3_MedicalFacilities extends AppCompatActivity {

    TextView txt_pt_lg3_mf;
    Context context;
    ImageView pt_lg3_mf_back;
    String medical_facilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__medical_facilities);
        getSupportActionBar().hide();
        initview();
        setListner();
    }



    private void initview() {
        context=Patient_Login3_MedicalFacilities.this;
        txt_pt_lg3_mf=(TextView)findViewById(R.id.txt_pt_lg3_mf);
        pt_lg3_mf_back=(ImageView)findViewById(R.id.pt_lg3_mf_back);
        medical_facilities=getIntent().getStringExtra("medical_facilites");
        Log.e("m",medical_facilities);
        txt_pt_lg3_mf.setText(medical_facilities);



    }

    private void setListner() {
        pt_lg3_mf_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
