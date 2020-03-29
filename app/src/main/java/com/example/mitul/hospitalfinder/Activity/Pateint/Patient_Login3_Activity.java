package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Activity.LoginActivity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_sep_hospitalDetails;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class Patient_Login3_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar1;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawer;
    ImageView imgvw;
    TextView email;
    Context context;
    String email_id, hospital_id, patient_id,pcategory;
    Button btn_pt_lg3_call, btn_pt_lg3_location;
    TextView pt_lg3_name, pt_lg3_ow_name, pt_lg3_mo, pt_lg3_category, pt_lg3_type, pt_lg3_time, pt_lg3_adress, pt_lg3_bno,
            pt_lg3_colony, pt_lg3_landmark, pt_lg3_area, pt_lg3_pincode, pt_lg3_city, pt_lg3_state;
    ImageView pt_lg3_image;

    String status, email_id1, hospital_image, name, owner_name, mobile_number, category, type, time, adress, building_no,
            colony, land_mark, area, pincode, city, state, medical_facilites, about, latitude, longitude;
String search_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3_);

        toolbar1 = (android.support.v7.widget.Toolbar) findViewById(R.id.pt_lg3_toolb);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.pt_lg3_drawer);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar1, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.pt_lg_nav_view3);
        navigationView.setNavigationItemSelectedListener(this);
        email_id = getIntent().getStringExtra("email_id");
        hospital_id = getIntent().getStringExtra("hospital_id");
        patient_id = getIntent().getStringExtra("patient_id");
        search_category=getIntent().getStringExtra("category");
        pcategory=getIntent().getStringExtra("pcategory");
        View navView = navigationView.inflateHeaderView(R.layout.navigatiton_hedar_pt1);
        imgvw = (ImageView) navView.findViewById(R.id.iv_pt_lg1_hd1);
        email = (TextView) navView.findViewById(R.id.txt_pt_lg1_hd_email1);
        email.setText(email_id);


        initView();
        setListner();

    }

    private void initView() {

        context = Patient_Login3_Activity.this;
        pt_lg3_image = (ImageView) findViewById(R.id.pt_lg3_image);
        pt_lg3_name = (TextView) findViewById(R.id.pt_lg3_name);
        pt_lg3_ow_name = (TextView) findViewById(R.id.pt_lg3_ow_name);
        pt_lg3_mo = (TextView) findViewById(R.id.pt_lg3_mo);
        pt_lg3_category = (TextView) findViewById(R.id.pt_lg3_category);
        pt_lg3_type = (TextView) findViewById(R.id.pt_lg3_type);
        pt_lg3_time = (TextView) findViewById(R.id.pt_lg3_time);
       /* pt_lg3_bno = (TextView) findViewById(R.id.pt_lg3_bno);
        pt_lg3_colony = (TextView) findViewById(R.id.pt_lg3_colony);
        pt_lg3_landmark = (TextView) findViewById(R.id.pt_lg3_landmark);
        pt_lg3_area = (TextView) findViewById(R.id.pt_lg3_area);*/
        pt_lg3_adress = (TextView) findViewById(R.id.pt_lg3_adress);
        pt_lg3_pincode = (TextView) findViewById(R.id.pt_lg3_pincode);
        pt_lg3_city = (TextView) findViewById(R.id.pt_lg3_city);
        pt_lg3_state = (TextView) findViewById(R.id.pt_lg3_state);
        btn_pt_lg3_call = (Button) findViewById(R.id.btn_pt_lg3_call);
        btn_pt_lg3_location = (Button) findViewById(R.id.btn_pt_lg3_location);

        CallHospitalDetailsApi();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pt_lg3_docter) {


            Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_Docter_DetailsActivity.class);
            intent.putExtra("hospital_id", hospital_id);
            intent.putExtra("patient_id", patient_id);
            intent.putExtra("category",search_category);
            intent.putExtra("pcategory",pcategory);
           // intent.putExtra("category",search_category);

            startActivity(intent);

        } else if (id == R.id.pt_lg3_medicine) {
            Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_Medicine.class);
            intent.putExtra("hospital_id", hospital_id);
            intent.putExtra("patient_id", patient_id);

            startActivity(intent);

        } else if (id == R.id.pt_lg3_Blood) {
            Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_Blood.class);
            intent.putExtra("hospital_id", hospital_id);
            intent.putExtra("patient_id", patient_id);
            startActivity(intent);

        } else if (id == R.id.pt_lg3_mf) {
            Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_MedicalFacilities.class);
            intent.putExtra("medical_facilites", medical_facilites);
            startActivity(intent);

        } else if (id == R.id.pt_lg3_about) {
            Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_About.class);
            intent.putExtra("about", about);
            //intent.putExtra("medical_facilites", medical_facilites);

            startActivity(intent);


        }


        return false;
    }

    private void CallHospitalDetailsApi() {

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
                        email_id1 = admin_sep_hospitalDetails.getHospitaldetails().get(i).getEmailId().toString().trim();
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
                        medical_facilites = admin_sep_hospitalDetails.getHospitaldetails().get(i).getMedicalFacilities().toString().trim();
                        latitude = admin_sep_hospitalDetails.getHospitaldetails().get(i).getLatitude().toString().trim();
                        longitude = admin_sep_hospitalDetails.getHospitaldetails().get(i).getLongitude().toString().trim();
                        about = admin_sep_hospitalDetails.getHospitaldetails().get(i).getAbout().toString().trim();
                    }

                    adress = building_no + "," + colony + "," + land_mark + "," + area + "," + city;
                    pt_lg3_name.setText(name);
                    pt_lg3_ow_name.setText(owner_name);
                    pt_lg3_mo.setText(mobile_number);
                    pt_lg3_category.setText(category);
                    pt_lg3_type.setText(type);
                    pt_lg3_time.setText(time);
                    pt_lg3_adress.setText(adress);
                    pt_lg3_pincode.setText(pincode);
                    pt_lg3_city.setText(city);
                    pt_lg3_state.setText(state);
                    Log.e("hospital_image", hospital_image);
                    Glide.with(context).load(hospital_image).into(pt_lg3_image);
                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Hospital details not avialable");

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }

    private void setListner() {
        btn_pt_lg3_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + mobile_number));
                startActivity(intent);
            }
        });

        btn_pt_lg3_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Patient_Login3_Activity.this, Patient_Login3_Hospital_Location.class);
                intent.putExtra("place", name);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
        });
    }
}
