package com.example.mitul.hospitalfinder.Activity.Hospital;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class Hospital_Edit_Hospital_Image extends AppCompatActivity {
    Button btn_hs_ed_bd_update;
    ImageView img_hs_ed_bd_img1,hs_lg1_uimage_back;
    Context context;

    String hospital_id;
    String hospital_image,hospital_image1;
    public int i = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();

        setContentView(R.layout.activity_hospital__edit_hospital_image);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }


    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Edit_Hospital_Image.this;
        img_hs_ed_bd_img1 = (ImageView) findViewById(R.id.img_hs_ed_bd_img1);
        btn_hs_ed_bd_update = (Button) findViewById(R.id.btn_hs_ed_bd_update);
        hs_lg1_uimage_back=(ImageView)findViewById(R.id.hs_lg1_uimage_back);

        hospital_id = getIntent().getStringExtra("hospital_id");
        hospital_image=getIntent().getStringExtra("hospital_image");
        Log.e("hospital_image",hospital_image);
        Glide.with(context).load(hospital_image).into(img_hs_ed_bd_img1);





    }

    private void setListener() {
        hs_lg1_uimage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_hs_ed_bd_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });
        btn_hs_ed_bd_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallHospitalImageUpdateApi();
            }
        });

    }

    private void CallHospitalImageUpdateApi() {
        AppConstants.showDialog(context);

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_HOSPITAL_IMAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                AppConstants.dissmissDialog();

                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");
                    if (status.equals("1")) {

                        AppConstants.openErrorDialog(context,"updated");

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Selected File Have Some Error....");

                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Max 100MB Is Allow.....");


                    } else if (status.equals("000")) {
                        AppConstants.openErrorDialog(context, "This File Type Is Not Allow.....");

                    } else if (status.equals("0000")) {
                        AppConstants.openErrorDialog(context, "Field Is Not Set........");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context, "Error........");

            }
        });


        smr.addStringParam("hospital_id", hospital_id);

        smr.addFile("hospital_image", hospital_image1);

        MyApplication.getInstance().addToRequestQueue(smr);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case 1:
                    Uri imageuri = data.getData();

                    hospital_image1 = getPath(imageuri);
                    if (hospital_image1.equals(null)) {
                        AppConstants.openErrorDialog(context, "please rechoose");
                    } else {
                        i = i + 1;
                        Log.e("imageuri", imageuri.toString());
                        Log.e("hospital_image", hospital_image1);

                        img_hs_ed_bd_img1.setImageURI(imageuri);
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

}
