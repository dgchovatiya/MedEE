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
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Hospital_Login1_MedicalFacilities extends AppCompatActivity {

    TextView txt_pt_lg3_mf;
    EditText et_hs_lg1_mf;
    Button btn_hs_lg1_mf_update, btn_hs_lg1_mf_done;
    Context context;
    ImageView pt_lg3_mf_back;
    String medical_facilities, update_medical_facilities, hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1__medical_facilities);
        getSupportActionBar().hide();
        initview();
        setListner();
    }


    private void initview() {
        context = Hospital_Login1_MedicalFacilities.this;

        txt_pt_lg3_mf = (TextView) findViewById(R.id.txt_hs_lg1_mf1);
        et_hs_lg1_mf = (EditText) findViewById(R.id.et_hs_lg1_mf);
        btn_hs_lg1_mf_update = (Button) findViewById(R.id.btn_hs_lg1_mf_update);
        btn_hs_lg1_mf_done = (Button) findViewById(R.id.btn_hs_lg1_mf_done);
        hospital_id = getIntent().getStringExtra("hospital_id");
        CallMedical_facilityAPi();
        pt_lg3_mf_back = (ImageView) findViewById(R.id.pt_lg3_mf_back);
        txt_pt_lg3_mf.setVisibility(View.VISIBLE);
        et_hs_lg1_mf.setVisibility(View.INVISIBLE);


    }

    private void CallMedical_facilityAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_GET_MEDICAL_FACILITIES, new Response.Listener<String>() {
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

                        medical_facilities=jsonObject.getString("medical_facilities");

                        Log.e("medical_facilities",medical_facilities);

                        txt_pt_lg3_mf.setText(medical_facilities);


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

        MyApplication.getInstance().addToRequestQueue(smr);


    }


    private void setListner() {
        pt_lg3_mf_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_hs_lg1_mf_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_pt_lg3_mf.setVisibility(View.INVISIBLE);
                btn_hs_lg1_mf_update.setVisibility(View.INVISIBLE);
                btn_hs_lg1_mf_done.setVisibility(View.VISIBLE);
                et_hs_lg1_mf.setVisibility(View.VISIBLE);
                et_hs_lg1_mf.setText(txt_pt_lg3_mf.getText().toString());
            }
        });
        btn_hs_lg1_mf_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_medical_facilities = et_hs_lg1_mf.getText().toString().trim();
                if (update_medical_facilities.length() == 0) {
                    AppConstants.openErrorDialog(context, "pelase type some..");
                } else {
                    CallUpdateMedicalFacilitiesAPi();
                }

            }
        });


    }

    private void CallUpdateMedicalFacilitiesAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_MEDICAL_FACILITIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        AppConstants.openErrorDialog1(context, "medical_facilities updated");
                        et_hs_lg1_mf.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_mf.setVisibility(View.VISIBLE);
                        btn_hs_lg1_mf_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_mf_update.setVisibility(View.VISIBLE);
                        txt_pt_lg3_mf.setText(update_medical_facilities);

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "medical_facilities not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Some error");
                        et_hs_lg1_mf.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_mf.setVisibility(View.VISIBLE);
                        btn_hs_lg1_mf_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_mf_update.setVisibility(View.VISIBLE);

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


        smr.addStringParam("medical_facilities", update_medical_facilities);
        smr.addStringParam("hospital_id", hospital_id);

        MyApplication.getInstance().addToRequestQueue(smr);


    }
}

