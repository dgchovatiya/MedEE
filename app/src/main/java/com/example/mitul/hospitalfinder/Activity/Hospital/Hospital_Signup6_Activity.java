package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.util.regex.Pattern;

public class Hospital_Signup6_Activity extends AppCompatActivity {
    ImageView iv_hs_su6_image;
    Button btn_hs_su6_chooseImage, btn_hs_su6_choosepdf, btn_hs_su6_next;
    Context context;
    ImageView hs_s6_back;

    String profile6;
    String hbuilding_no6, hstreet6, hlandmark6, harea6, hpincode6, hcity6, hstate6, hlatitude6, hlongitude6;
    String hname6, hownername6, hcategory6, htype6, htime6, hmo6;
    String hmedical_facilities6;
    String habout6;
    String hospital_image;
    String certificate;
    String login_id, email_id;
    int i = 0, j = 0;
    public static Hospital_Signup6_Activity hospital_signup6_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup6_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    @SuppressLint("WrongConstant")
    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Signup6_Activity.this;
        hospital_signup6_activity=Hospital_Signup6_Activity.this;

        iv_hs_su6_image = (ImageView) findViewById(R.id.iv_hs_su6_image);
        btn_hs_su6_chooseImage = (Button) findViewById(R.id.btn_hs_su6_chooseImage);
        btn_hs_su6_choosepdf = (Button) findViewById(R.id.btn_hs_su6_choosepdf);
        btn_hs_su6_next = (Button) findViewById(R.id.btn_hs_su6_next);
        hs_s6_back = (ImageView) findViewById(R.id.hs_s6_back);


        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname6 = getIntent().getStringExtra("name");
        hownername6 = getIntent().getStringExtra("owner_name");
        hcategory6 = getIntent().getStringExtra("category");
        htype6 = getIntent().getStringExtra("type");
        hmo6 = getIntent().getStringExtra("mo");
        htime6 = getIntent().getStringExtra("time");
        hbuilding_no6 = getIntent().getStringExtra("building_no");
        hstreet6 = getIntent().getStringExtra("street");
        hlandmark6 = getIntent().getStringExtra("landmark");
        harea6 = getIntent().getStringExtra("area");
        hpincode6 = getIntent().getStringExtra("pincode");
        hcity6 = getIntent().getStringExtra("city");
        hstate6 = getIntent().getStringExtra("state");
        hlatitude6 = getIntent().getStringExtra("latitude");
        hlongitude6 = getIntent().getStringExtra("longitude");
        hmedical_facilities6 = getIntent().getStringExtra("medical_facilities");
        habout6 = getIntent().getStringExtra("about");
        profile6 = getIntent().getStringExtra("profile");


       /* Toast.makeText(context, hname6, 0).show();
        Toast.makeText(context, hownername6, 0).show();
        Toast.makeText(context, hcategory6, 0).show();
        Toast.makeText(context, htype6, 0).show();
        Toast.makeText(context, htime6, 0).show();
        Toast.makeText(context, hmo6, 0).show();
        Toast.makeText(context, hbuilding_no6, 0).show();
        Toast.makeText(context, hstreet6, 0).show();
        Toast.makeText(context, hlandmark6, 0).show();
        Toast.makeText(context, harea6, 0).show();
        Toast.makeText(context, hpincode6, 0).show();
        Toast.makeText(context, hcity6, 0).show();
        Toast.makeText(context, hstate6, 0).show();
        Toast.makeText(context, hlatitude6, 0).show();
        Toast.makeText(context, hlongitude6, 0).show();
        Toast.makeText(context, hmedical_facilities6, 0).show();
        Toast.makeText(context, habout6, 0).show();
        Toast.makeText(context, profile6, 0).show();*/

        Log.e("loginid", login_id);
        Log.e("loginid", email_id);
        Log.e("hname6", hname6);
        Log.e("hownername6", hownername6);
        Log.e("hcategory6", hcategory6);
        Log.e("htype6", htype6);
        Log.e("htime6", htime6);
        Log.e("hmo6", hmo6);
        Log.e("hbuilding_no6", hbuilding_no6);
        Log.e("hstreet6", hstreet6);
        Log.e("hlandmark6", hlandmark6);
        Log.e("harea6", harea6);
        Log.e("hpincode6", hpincode6);
        Log.e("hcity6", hcity6);
        Log.e("hstate6", hstate6);
        Log.e("hlatitude6", hlatitude6);
        Log.e("hlongitude6", hlongitude6);
        Log.e("hmedical_facilities6", hmedical_facilities6);
        Log.e("habout6", habout6);
        Log.e("profile6", profile6);


    }

    private void setListener() {
        btn_hs_su6_chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);

            }
        });
        btn_hs_su6_choosepdf.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                launchPicker();


            }
        });
        btn_hs_su6_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i != 0 && j != 0) {
                    CallHospitalSignUpApi();
                } else {

                    if (i == 0) {
                        AppConstants.openErrorDialog(context, "pelase Choose image");
                    } else if (j == 0) {
                        AppConstants.openErrorDialog(context, "pelase Chosse pdf");
                    }
                }


            }
        });
        hs_s6_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    private void CallHospitalSignUpApi() {
        AppConstants.showDialog(context);

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.SIGNUP_HOSPITAL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                AppConstants.dissmissDialog();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                SignUpHospitalResponse signUpHospitalResponse = gson.fromJson(response, SignUpHospitalResponse.class);
                String status = signUpHospitalResponse.getSTATUS().toString().trim();
                String msg = signUpHospitalResponse.getMSG().toString().trim();
                if (status.equals("1")) {
                    String hospital_id = signUpHospitalResponse.getHospitalId().toString().trim();
                    Intent intent = new Intent(Hospital_Signup6_Activity.this, Hospital_SignupDocterDetails.class);
                    intent.putExtra("hospital_id", hospital_id);
                    startActivity(intent);

                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Selected File Have Some Error....");

                } else if (status.equals("00")) {
                    AppConstants.openErrorDialog(context, "Max 100MB Is Allow.....");


                } else if (status.equals("000")) {
                    AppConstants.openErrorDialog(context, "This File Type Is Not Allow.....");

                } else if (status.equals("0000")) {
                    AppConstants.openErrorDialog(context, "Field Is Not Set........");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context, "Error........");

            }
        });
        smr.addStringParam("login_id", login_id);
        smr.addStringParam("email_id", email_id);
        smr.addStringParam("name", hname6);
        smr.addStringParam("owner_name", hownername6);
        smr.addStringParam("category", hcategory6);
        smr.addStringParam("type", htype6);
        smr.addStringParam("mobile_number", hmo6);
        smr.addStringParam("time", htime6);
        smr.addStringParam("building_no", hbuilding_no6);
        smr.addStringParam("colony", hstreet6);
        smr.addStringParam("land_mark", hlandmark6);
        smr.addStringParam("area", harea6);
        smr.addStringParam("pincode", hpincode6);
        smr.addStringParam("city", hcity6);
        smr.addStringParam("state", hstate6);
        smr.addStringParam("latitude", hlatitude6);
        smr.addStringParam("longitude", hlongitude6);
        smr.addStringParam("medical_facilities", hmedical_facilities6);
        smr.addStringParam("about", habout6);
        smr.addFile("hospital_image", hospital_image);
        smr.addFile("profile", profile6);
        smr.addFile("certificate", certificate);
        MyApplication.getInstance().addToRequestQueue(smr);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case 1:
                    Uri imageuri = data.getData();

                    hospital_image = getPath(imageuri);
                    if (hospital_image.equals(null)) {
                        AppConstants.openErrorDialog(context, "please rechoose image");

                    } else {

                        Log.e("imageuri", imageuri.toString());
                        Log.e("hospital_image", hospital_image);
                        i = i + 1;

                        iv_hs_su6_image.setImageURI(imageuri);
                    }
                    break;

                case 123:

                    certificate = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                    File file = new File(certificate);
                    //displayFromFile(file);
                    if (certificate.equals(null)) {
                        AppConstants.openErrorDialog(context, "please rechoose Pdf");

                    } else {

                        Log.e("certificate: ", certificate);
                        j = j + 1;
                        certificate = certificate;
                        Toast.makeText(this, "Picked file: " + certificate, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }

    private String getPath(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        android.content.CursorLoader loader = new android.content.CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    private void launchPicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRequestCode(123)
                .withHiddenFiles(true)
                .withFilter(Pattern.compile(".*\\.pdf$"))
                .withTitle("Select PDF file")
                .start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
