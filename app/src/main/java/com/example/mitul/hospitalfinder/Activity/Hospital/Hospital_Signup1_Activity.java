package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Activity.Pateint.Patient_Signup_Activity;
import com.example.mitul.hospitalfinder.Activity.SignUpCommonActivity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Hospital_Signup1_Activity extends AppCompatActivity {
    EditText et_hs_su1_name, et_hs_su1_ow_name, et_hs_su1_time, et_hs_su1_mo;
    Button btn_next_su1;
    ImageView hs_s1_back;
    Spinner sp_hs_su1_category,sp_hs_su1_type;
    Context context;
    String hname, hownername, hcategory, htype, htime, hmo;
    String login_id, email_id;
    ArrayList<String> list_category,list_type;
    public static Hospital_Signup1_Activity hospital_signup1_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__signup1_);
        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }

    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Signup1_Activity.this;
        hospital_signup1_activity=Hospital_Signup1_Activity.this;

        et_hs_su1_name = (EditText) findViewById(R.id.et_hs_su1_name);
        et_hs_su1_ow_name = (EditText) findViewById(R.id.et_hs_su1_ow_name);
        et_hs_su1_time = (EditText) findViewById(R.id.et_hs_su1_time);
        et_hs_su1_mo = (EditText) findViewById(R.id.et_hs_su1_mo);
        //et_hs_su1_category = (EditText) findViewById(R.id.et_hs_su1_category);
        sp_hs_su1_type = (Spinner) findViewById(R.id.sp_hs_su1_type);
        btn_next_su1 = (Button) findViewById(R.id.btn_next_su1);
        hs_s1_back = (ImageView) findViewById(R.id.hs_s1_back);

        sp_hs_su1_category = (Spinner) findViewById(R.id.sp_hs_su1_category);
        login_id = getIntent().getStringExtra("login_id");
        email_id = getIntent().getStringExtra("email_id");

        list_category = new ArrayList<>();
        list_category.add("--Hospital Category--");
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
        setAdapter(sp_hs_su1_category, list_category);

        list_type=new ArrayList<>();
        list_type.add("--Hospital Type--");
        list_type.add("Goverment");
        list_type.add("Private");
        list_type.add("Trust");
        setAdapter(sp_hs_su1_type,list_type);

    }

    private void setAdapter(Spinner sp_hs_su1_category, ArrayList<String> list_category) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.hs_su1_hoscategory_spinner, R.id.txt_view, list_category){
            @ Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sp_hs_su1_category.setAdapter(arrayAdapter);
    }

    private void setListener() {
        btn_next_su1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                hname = et_hs_su1_name.getText().toString().trim();
                hownername = et_hs_su1_ow_name.getText().toString().trim();
                hcategory = sp_hs_su1_category.getSelectedItem().toString().trim();
                htype = sp_hs_su1_type.getSelectedItem().toString().trim();
                htime = et_hs_su1_time.getText().toString().trim();
                hmo = et_hs_su1_mo.getText().toString().trim();

                if (hname.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter  name first...");
                    et_hs_su1_name.requestFocus();

                } else if (hownername.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Owner name  first...");
                    et_hs_su1_ow_name.requestFocus();

                } else if (hcategory.equals("--Hospital Category--")) {
                    AppConstants.openErrorDialog(context, "Please choose category  first...");
                    //et_hs_su1_category.requestFocus();

                } else if (htype.equals("--Hospital Type--")) {
                    AppConstants.openErrorDialog(context, "Please Choose type  first...");

                }  else if (hmo.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter mobile no first...");
                    et_hs_su1_mo.requestFocus();

                }
                else if (htime.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter time  first...");
                    et_hs_su1_time.requestFocus();

                }else {

                    if (isValidMobile(hmo) == true) {

                        Intent intent = new Intent(Hospital_Signup1_Activity.this, Hospital_Signup2_Activity.class);
                        intent.putExtra("login_id", login_id);
                        intent.putExtra("email_id", email_id);
                        intent.putExtra("name", hname);
                        intent.putExtra("owner_name", hownername);
                        intent.putExtra("category", hcategory);
                        intent.putExtra("type", htype);
                        intent.putExtra("time", htime);
                        intent.putExtra("mo", hmo);
                        startActivity(intent);

                    } else {
                        AppConstants.openErrorDialog(context, "Please enter valid mobile number ...");
                        et_hs_su1_mo.requestFocus();
                    }
                }


            }
        });
        hs_s1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calldelteApi();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        calldelteApi();
    }

    private void calldelteApi() {
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.DELTE_LOGIN_ENTRY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Res hos_up_medicne", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        finish();

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Medicine not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Field not set");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.openErrorDialog(context, "Some error");
            }
        });


        smr.addStringParam("login_id", login_id);


        MyApplication.getInstance().addToRequestQueue(smr);



    }
}
