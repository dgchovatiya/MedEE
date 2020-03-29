package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

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

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Hospital_EditBasic_Details_Activity1 extends AppCompatActivity {
    EditText et_hs_ed_bd_name, et_hs_ed_bd_ow_name, et_hs_ed_bd_time, et_hs_ed_bd_mo;
    Button btn_next_ed_bd;
    ImageView hs_lg1_edit_basic1_back;
    Spinner sp_hs_ed_bd_category, sp_hs_ed_bd_type;
    Context context;
    String hospital_id, hname, hownername, hcategory, htype, htime, hmo;
    String hname1, hownername1, hcategory1, htype1, htime1, hmo1;
    String hbuilding_no2, hstreet2, hlandmark2, harea2, hpincode2, hcity2, hstate2;
    String hcaitegory2;

    public static Hospital_EditBasic_Details_Activity1 heda1;
    String hcategory2;
    ArrayList<String> list_category, list_type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__edit_basic__details_1);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }


    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_EditBasic_Details_Activity1.this;
        heda1 = Hospital_EditBasic_Details_Activity1.this;

        et_hs_ed_bd_name = (EditText) findViewById(R.id.et_hs_ed_bd_name);
        et_hs_ed_bd_ow_name = (EditText) findViewById(R.id.et_hs_ed_bd_ow_name);
        et_hs_ed_bd_time = (EditText) findViewById(R.id.et_hs_ed_bd_time);
        et_hs_ed_bd_mo = (EditText) findViewById(R.id.et_hs_ed_bd_mo);
        hs_lg1_edit_basic1_back = (ImageView) findViewById(R.id.hs_lg1_edit_basic1_back);
        //et_hs_su1_category = (EditText) findViewById(R.id.et_hs_su1_category);
        sp_hs_ed_bd_type = (Spinner) findViewById(R.id.sp_hs_ed_bd_type);
        btn_next_ed_bd = (Button) findViewById(R.id.btn_next_ed_bd);
        sp_hs_ed_bd_category = (Spinner) findViewById(R.id.sp_hs_ed_bd_category);


        hospital_id = getIntent().getStringExtra("hospital_id");
        hname1 = getIntent().getStringExtra("name");
        hownername1 = getIntent().getStringExtra("owner_name");
        hcategory1 = getIntent().getStringExtra("category");
        htype1 = getIntent().getStringExtra("type");
        htime1 = getIntent().getStringExtra("time");
        hmo1 = getIntent().getStringExtra("mo");
        hbuilding_no2 = getIntent().getStringExtra("building_no");
        hstreet2 = getIntent().getStringExtra("street");
        hlandmark2 = getIntent().getStringExtra("landmark");
        harea2 = getIntent().getStringExtra("area");
        hpincode2 = getIntent().getStringExtra("pincode");
        hcity2 = getIntent().getStringExtra("city");
        hstate2 = getIntent().getStringExtra("state");
        Log.e("category", hcategory1);

        et_hs_ed_bd_name.setText(hname1);
        et_hs_ed_bd_ow_name.setText(hownername1);
        et_hs_ed_bd_time.setText(htime1);
        et_hs_ed_bd_mo.setText(hmo1);
        CallHospitalCategoryApi();

        /*ArrayAdapter myAdap = (ArrayAdapter) sp_hs_ed_bd_category.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(hcategory1);
        sp_hs_ed_bd_category.setSelection(spinnerPosition);

        ArrayAdapter myAdap1 = (ArrayAdapter) sp_hs_ed_bd_type.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition1 = myAdap.getPosition(htype1);
        sp_hs_ed_bd_type.setSelection(spinnerPosition);*/




        list_type = new ArrayList<>();
        list_type.add(htype1);
        list_type.add("Goverment");
        list_type.add("Private");
        list_type.add("Trust");
        for (int i = 1; i < list_type.size(); i++) {
            Log.e("category" + i, list_type.get(i).toString());
            if (htype1.equals(list_type.get(i).toString())) {
                list_type.remove(i);
            }
        }
        setAdapter(sp_hs_ed_bd_type, list_type);


    }

    private void CallHospitalCategoryApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.ADMIN_SEP_HOSPITAL_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("responseDoctercategory", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Admin_sep_hospitalDetails admin_sep_hospitalDetails = gson.fromJson(response, Admin_sep_hospitalDetails.class);
                String status = admin_sep_hospitalDetails.getSTATUS().toString().trim();

                if (status.equals("1")) {
                    for (int i = 0; i < admin_sep_hospitalDetails.getHospitaldetails().size(); i++) {
                        hcategory2 = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCategory().toString().trim();
                        setList();
                    }


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

    private void setList() {
        list_category = new ArrayList<>();
        list_category.add(hcategory2);
        list_category.add("Orthopedic");
        list_category.add("Dental");
        list_category.add("MultiSpecialist");
        list_category.add("Radiological");
        list_category.add("Heart");
        list_category.add("Skin");
        list_category.add("Neurological");
        list_category.add("Urological");
        list_category.add("Nephrological");
        list_category.add("Homeopathic");
        list_category.add("Genrel Physicalogical");
        list_category.add("Ayurvedic");
        for (int i = 1; i < list_category.size(); i++) {
            Log.e("category" + i, list_category.get(i).toString());
            if (hcategory2.equals(list_category.get(i).toString())) {
                list_category.remove(i);
            }
        }
        setAdapter(sp_hs_ed_bd_category, list_category);
    }


    private void setListener() {

        btn_next_ed_bd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                hname = et_hs_ed_bd_name.getText().toString().trim();
                hownername = et_hs_ed_bd_ow_name.getText().toString().trim();
                hcategory = sp_hs_ed_bd_category.getSelectedItem().toString().trim();
                htype = sp_hs_ed_bd_type.getSelectedItem().toString().trim();
                htime = et_hs_ed_bd_time.getText().toString().trim();
                hmo = et_hs_ed_bd_mo.getText().toString().trim();

                if (hname.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  name first...");
                    et_hs_ed_bd_name.requestFocus();

                } else if (hownername.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Owner name  first...");
                    et_hs_ed_bd_ow_name.requestFocus();

                } else if (hcategory.equals("Choose Category")) {
                    AppConstants.openErrorDialog(context, "Please choose category  first...");
                    //et_hs_su1_category.requestFocus();

                } else if (htype.equals("Choose Tpye")) {
                    AppConstants.openErrorDialog(context, "Please Choose type  first...");

                } else if (hmo.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter mobile no first...");
                    et_hs_ed_bd_mo.requestFocus();

                } else if (htime.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter time  first...");
                    et_hs_ed_bd_time.requestFocus();

                } else {

                    if (isValidMobile(hmo) == true) {

                        Intent intent = new Intent(Hospital_EditBasic_Details_Activity1.this, Hospital_EditBasic_Details_Activity2.class);
                        intent.putExtra("hospital_id", hospital_id);
                        intent.putExtra("name", hname);
                        intent.putExtra("owner_name", hownername);
                        intent.putExtra("category", hcategory);
                        intent.putExtra("type", htype);
                        intent.putExtra("time", htime);
                        intent.putExtra("mo", hmo);
                        intent.putExtra("building_no", hbuilding_no2);
                        intent.putExtra("street", hstreet2);
                        intent.putExtra("landmark", hlandmark2);
                        intent.putExtra("area", harea2);
                        intent.putExtra("pincode", hpincode2);
                        intent.putExtra("city", hcity2);
                        intent.putExtra("state", hstate2);
                        startActivity(intent);
                        finish();

                    } else {
                        AppConstants.openErrorDialog(context, "Please enter valid mobile number ...");
                        et_hs_ed_bd_mo.requestFocus();
                    }
                }


            }
        });
        hs_lg1_edit_basic1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setAdapter(Spinner sp_hs_su1_category, ArrayList<String> list_category) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.hs_su1_hoscategory_spinner, R.id.txt_view, list_category) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return true;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.BLACK);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sp_hs_su1_category.setAdapter(arrayAdapter);
    }
}
