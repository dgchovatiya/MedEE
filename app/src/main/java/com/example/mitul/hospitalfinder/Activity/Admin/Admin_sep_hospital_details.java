package com.example.mitul.hospitalfinder.Activity.Admin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_sep_hospitalDetails;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Admin_sep_hospital_details extends AppCompatActivity {
    TextView ad_hs_name, ad_hs_ow_name, ad_hs_mo, ad_hs_category, ad_hs_type, ad_hs_time, ad_hs_adress,
            ad_hs_pincode, ad_hs_city, ad_hs_state,
            ad_hs_mf, ad_hs_about;
    ImageView ad_hs_image;

    Button btn_certificate, btn_docterdetails;
    Context context;
    String hospital_id;
    String adress;
    String status, email_id, hospital_image, name, owner_name, mobile_number, category, type, time, building_no,
            colony, land_mark, area, pincode, city, state, medical_facilities, about, certificate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sep_hospital_details);

        getSupportActionBar().hide();
        initview();
        setListener();


    }

    private void initview() {

        context = Admin_sep_hospital_details.this;
        btn_certificate = (Button) findViewById(R.id.btn_certificate);
        btn_docterdetails = (Button) findViewById(R.id.btn_docterdetails);
        ad_hs_image = (ImageView) findViewById(R.id.ad_hs_image);
        hospital_id = getIntent().getStringExtra("hospital_id");


        ad_hs_name = (TextView) findViewById(R.id.ad_hs_name);
        ad_hs_ow_name = (TextView) findViewById(R.id.ad_hs_ow_name);
        ad_hs_mo = (TextView) findViewById(R.id.ad_hs_mo);
        ad_hs_type = (TextView) findViewById(R.id.ad_hs_type);
        ad_hs_time = (TextView) findViewById(R.id.ad_hs_time);
        ad_hs_category = (TextView) findViewById(R.id.ad_hs_category);
        ad_hs_adress = (TextView) findViewById(R.id.ad_hs_adress);

        ad_hs_pincode = (TextView) findViewById(R.id.ad_hs_pincode);
        ad_hs_city = (TextView) findViewById(R.id.ad_hs_city);
        ad_hs_state = (TextView) findViewById(R.id.ad_hs_state);
        ad_hs_mf = (TextView) findViewById(R.id.ad_hs_mf);
        ad_hs_about = (TextView) findViewById(R.id.ad_hs_about);
        CallHospitalDetailAPi();


    }

    private void CallHospitalDetailAPi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.ADMIN_SEP_HOSPITAL_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("response", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Admin_sep_hospitalDetails admin_sep_hospitalDetails = gson.fromJson(response, Admin_sep_hospitalDetails.class);
                status = admin_sep_hospitalDetails.getSTATUS().toString().trim();

                if (status.equals("1")) {
                    for (int i = 0; i < admin_sep_hospitalDetails.getHospitaldetails().size(); i++) {
                        email_id = admin_sep_hospitalDetails.getHospitaldetails().get(i).getEmailId().toString().trim();
                        hospital_image = admin_sep_hospitalDetails.getHospitaldetails().get(i).getHospitalImage().toString().trim();
                        name = admin_sep_hospitalDetails.getHospitaldetails().get(i).getName().toString().trim();
                        owner_name = admin_sep_hospitalDetails.getHospitaldetails().get(i).getOwnerName().toString().trim();
                        mobile_number = admin_sep_hospitalDetails.getHospitaldetails().get(i).getMobileNumber().toString().trim();
                        category = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCategory().toString().trim();
                        type = admin_sep_hospitalDetails.getHospitaldetails().get(i).getType().toString().trim();
                        time = admin_sep_hospitalDetails.getHospitaldetails().get(i).getTime().toString().trim();
                        building_no = admin_sep_hospitalDetails.getHospitaldetails().get(i).getBuildingNo().toString().trim();
                        colony = admin_sep_hospitalDetails.getHospitaldetails().get(i).getColony().toString().trim();
                        land_mark = admin_sep_hospitalDetails.getHospitaldetails().get(i).getLandMark().toString().trim();
                        area = admin_sep_hospitalDetails.getHospitaldetails().get(i).getArea().toString().trim();
                        pincode = admin_sep_hospitalDetails.getHospitaldetails().get(i).getPincode().toString().trim();
                        city = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCity().toString().trim();
                        state = admin_sep_hospitalDetails.getHospitaldetails().get(i).getState().toString().trim();
                        medical_facilities = admin_sep_hospitalDetails.getHospitaldetails().get(i).getMedicalFacilities().toString().trim();
                        about = admin_sep_hospitalDetails.getHospitaldetails().get(i).getAbout().toString().trim();
                        certificate = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCertificate().toString().trim();
                    }


                    ad_hs_name.setText(name);
                    ad_hs_ow_name.setText(owner_name);
                    ad_hs_mo.setText(mobile_number);
                    ad_hs_category.setText(category);
                    ad_hs_type.setText(type);
                    ad_hs_time.setText(time);
                    adress=building_no+","+colony+","+land_mark+","+area+","+city;

                    ad_hs_adress.setText(adress);
                    ad_hs_pincode.setText(pincode);
                    ad_hs_city.setText(city);
                    ad_hs_state.setText(state);
                    ad_hs_mf.setText(medical_facilities);
                    ad_hs_about.setText(about);
                    Glide.with(context).load(hospital_image).into(ad_hs_image);
                }
                else if(status.equals("0")){
                    AppConstants.openErrorDialog(context,"Hospital details not avialable");

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
        btn_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(certificate));
                startActivity(intent);
            }
        });
        btn_docterdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_sep_hospital_details.this, Admin_docterActivity.class);
                intent.putExtra("hospital_id", hospital_id);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
