package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Hospital_Login1_Medicine_DescriptionActivity extends AppCompatActivity {
    TextView txt_pt_lg3_md_des;
    EditText et_hs_lg1_md_des;
    Button btn_hs_lg1_md_des_update, btn_hs_lg1_md_des_done;
    Context context;
    ImageView pt_lg3_md_des_back;
    String description,medicine_id,hospital_id,update_medicine_description,description1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1__medicine__description);
        getSupportActionBar().hide();
        initview();
        setListner();
    }
    private void initview() {
        context = Hospital_Login1_Medicine_DescriptionActivity.this;

        txt_pt_lg3_md_des = (TextView) findViewById(R.id.txt_pt_lg3_md_des);
        et_hs_lg1_md_des = (EditText) findViewById(R.id.et_hs_lg1_md_des);
        btn_hs_lg1_md_des_update = (Button) findViewById(R.id.btn_hs_lg1_md_des_update);
        btn_hs_lg1_md_des_done = (Button) findViewById(R.id.btn_hs_lg1_md_des_done);
        hospital_id = getIntent().getStringExtra("hospital_id");
        medicine_id = getIntent().getStringExtra("medicine_id");
       // description = getIntent().getStringExtra("description");


        CallMedical_facilityAPi();
        pt_lg3_md_des_back = (ImageView) findViewById(R.id.pt_lg3_md_des_back);
        txt_pt_lg3_md_des.setVisibility(View.VISIBLE);
        et_hs_lg1_md_des.setVisibility(View.INVISIBLE);


    }

    private void CallMedical_facilityAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_MEDICINE_DESCRIPTION, new Response.Listener<String>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        description=jsonObject.getString("description");

                        Log.e("description",description);

                        txt_pt_lg3_md_des.setText(description);


                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "medical_facilities not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Some error");


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


        smr.addStringParam("hospital_id", hospital_id);
        smr.addStringParam("medicine_id",medicine_id);


        MyApplication.getInstance().addToRequestQueue(smr);


    }


    private void setListner() {
        pt_lg3_md_des_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_hs_lg1_md_des_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_pt_lg3_md_des.setVisibility(View.INVISIBLE);
                btn_hs_lg1_md_des_update.setVisibility(View.INVISIBLE);
                btn_hs_lg1_md_des_done.setVisibility(View.VISIBLE);
                et_hs_lg1_md_des.setVisibility(View.VISIBLE);
                et_hs_lg1_md_des.setText(txt_pt_lg3_md_des.getText().toString());
            }
        });
        btn_hs_lg1_md_des_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_medicine_description = et_hs_lg1_md_des.getText().toString().trim();
                if (update_medicine_description.length() == 0) {
                    AppConstants.openErrorDialog(context, "pelase type some..");
                } else {
                    CallUpdateMedicalFacilitiesAPi();
                }

            }
        });


    }

    private void CallUpdateMedicalFacilitiesAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_MEDICINE_DESCRIPTION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        AppConstants.openErrorDialog1(context, "Medicine description updated");
                        et_hs_lg1_md_des.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_md_des.setVisibility(View.VISIBLE);
                        btn_hs_lg1_md_des_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_md_des_update.setVisibility(View.VISIBLE);
                        txt_pt_lg3_md_des.setText(update_medicine_description);

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Medicine description not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Some error");
                        et_hs_lg1_md_des.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_md_des.setVisibility(View.VISIBLE);
                        btn_hs_lg1_md_des_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_md_des_update.setVisibility(View.VISIBLE);

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


        smr.addStringParam("description", update_medicine_description);
        smr.addStringParam("medicine_id",medicine_id);

        MyApplication.getInstance().addToRequestQueue(smr);


    }
}
