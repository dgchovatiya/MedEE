package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.R;

import org.w3c.dom.Text;

public class Patient_Login3_About extends AppCompatActivity {
    TextView txt_pt_lg3_about;
    ImageView pt_lg3_about_back;
    Context context;
    String about;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__about);
        getSupportActionBar().hide();
        initView();
        setListner();
}


    private void initView() {

        context=Patient_Login3_About.this;
        txt_pt_lg3_about=(TextView) findViewById(R.id.txt_pt_lg3_about);
        pt_lg3_about_back=(ImageView)findViewById(R.id.pt_lg3_about_back);
        about=getIntent().getStringExtra("about");
        txt_pt_lg3_about.setText(about);
    }
    private void setListner() {
pt_lg3_about_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});
    }

}
