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

import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.R;

public class Hospital_Signup5_Activity extends AppCompatActivity {
    ImageView img_hs_su5_img;
    Button btn_hs_su5_next;
    Context context;
    ImageView hs_s5_back;

    String profile;
    String hbuilding_no5, hstreet5, hlandmark5, harea5, hpincode5, hcity5, hstate5, hlatitude5, hlongitude5;
    String hname5, hownername5, hcategory5, htype5, htime5, hmo5;
    String hmedical_facilities5;
    String habout5;
    String login_id, email_id;
    public int i = 0;
    public static Hospital_Signup5_Activity hospital_signup5_activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup5_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();
        context = Hospital_Signup5_Activity.this;
        hospital_signup5_activity=Hospital_Signup5_Activity.this;

        img_hs_su5_img = (ImageView) findViewById(R.id.img_hs_su5_img);
        btn_hs_su5_next = (Button) findViewById(R.id.btn_hs_su5_next);
        hs_s5_back = (ImageView) findViewById(R.id.hs_s5_back);


        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");
        hname5 = getIntent().getStringExtra("name");
        hownername5 = getIntent().getStringExtra("owner_name");
        hcategory5 = getIntent().getStringExtra("category");
        htype5 = getIntent().getStringExtra("type");
        htime5 = getIntent().getStringExtra("time");
        hmo5 = getIntent().getStringExtra("mo");
        hbuilding_no5 = getIntent().getStringExtra("building_no");
        hstreet5 = getIntent().getStringExtra("street");
        hlandmark5 = getIntent().getStringExtra("landmark");
        harea5 = getIntent().getStringExtra("area");
        hpincode5 = getIntent().getStringExtra("pincode");
        hcity5 = getIntent().getStringExtra("city");
        hstate5 = getIntent().getStringExtra("state");
        hlatitude5 = getIntent().getStringExtra("latitude");
        hlongitude5 = getIntent().getStringExtra("longitude");
        hmedical_facilities5 = getIntent().getStringExtra("medical_facilities");
        habout5 = getIntent().getStringExtra("about");
    }

    private void setListener() {

        img_hs_su5_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);


            }
        });
        btn_hs_su5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i != 0) {
                    Intent intent1 = new Intent(Hospital_Signup5_Activity.this, Hospital_Signup6_Activity.class);
                    intent1.putExtra("login_id", login_id);
                    intent1.putExtra("email_id", email_id);
                    intent1.putExtra("name", hname5);
                    intent1.putExtra("owner_name", hownername5);
                    intent1.putExtra("category", hcategory5);
                    intent1.putExtra("type", htype5);
                    intent1.putExtra("time", htime5);
                    intent1.putExtra("mo", hmo5);
                    intent1.putExtra("building_no", hbuilding_no5);
                    intent1.putExtra("street", hstreet5);
                    intent1.putExtra("landmark", hlandmark5);
                    intent1.putExtra("area", harea5);
                    intent1.putExtra("pincode", hpincode5);
                    intent1.putExtra("city", hcity5);
                    intent1.putExtra("state", hstate5);
                    intent1.putExtra("latitude", hlatitude5);
                    intent1.putExtra("longitude", hlongitude5);
                    intent1.putExtra("medical_facilities", hmedical_facilities5);
                    intent1.putExtra("about", habout5);
                    intent1.putExtra("profile", profile);
                    startActivity(intent1);
                } else {
                    AppConstants.openErrorDialog(context, "please Choose image..");
                }
            }
        });
        hs_s5_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case 1:
                    Uri imageuri = data.getData();

                    profile = getPath(imageuri);
                    if (profile.equals(null)) {
                        AppConstants.openErrorDialog(context, "please rechoose");
                    } else {
                        i = i + 1;
                        Log.e("imageuri", imageuri.toString());
                        Log.e("profiled", profile);

                        img_hs_su5_img.setImageURI(imageuri);
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
