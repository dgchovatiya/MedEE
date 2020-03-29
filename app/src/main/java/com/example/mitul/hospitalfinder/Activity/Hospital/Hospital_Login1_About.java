package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.content.Context;
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

public class Hospital_Login1_About extends AppCompatActivity {
    TextView txt_pt_lg3_about;
    EditText et_hs_lg1_about;
    Button btn_hs_lg1_about_update, btn_hs_lg1_about_done;
    Context context;
    ImageView pt_lg3_about_back;
    String about, update_about, hospital_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1__about);
        getSupportActionBar().hide();
        initview();
        setListner();
    }

    private void initview() {
        context = Hospital_Login1_About.this;

        txt_pt_lg3_about = (TextView) findViewById(R.id.txt_pt_lg3_about1);
        et_hs_lg1_about = (EditText) findViewById(R.id.et_hs_lg1_about1);
        btn_hs_lg1_about_update = (Button) findViewById(R.id.btn_hs_lg1_about_update);
        btn_hs_lg1_about_done = (Button) findViewById(R.id.btn_hs_lg1_about_done);
        hospital_id = getIntent().getStringExtra("hospital_id");
        pt_lg3_about_back = (ImageView) findViewById(R.id.pt_lg3_about_back);
        CallAPiabout();
        txt_pt_lg3_about.setVisibility(View.VISIBLE);
        et_hs_lg1_about.setVisibility(View.INVISIBLE);


    }

    private void CallAPiabout() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_GET_ABOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        about=jsonObject.getString("about");
                        txt_pt_lg3_about.setText(about);



                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "About not updated");


                    } else if (status.equals("00")) {


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
        pt_lg3_about_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_hs_lg1_about_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_pt_lg3_about.setVisibility(View.INVISIBLE);
                et_hs_lg1_about.setVisibility(View.VISIBLE);
                et_hs_lg1_about.setText(txt_pt_lg3_about.getText().toString());
                btn_hs_lg1_about_update.setVisibility(View.INVISIBLE);
                btn_hs_lg1_about_done.setVisibility(View.VISIBLE);

            }
        });
        btn_hs_lg1_about_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_about = et_hs_lg1_about.getText().toString().trim();
                if (update_about.length() == 0) {
                    AppConstants.openErrorDialog(context, "please type some");

                } else {
                    CallUpdateMedicalFacilitiesAPi();
                }
            }
        });


    }

    private void CallUpdateMedicalFacilitiesAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_ABOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {

                        AppConstants.openErrorDialog1(context, "About updated");
                        et_hs_lg1_about.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_about.setVisibility(View.VISIBLE);
                        btn_hs_lg1_about_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_about_update.setVisibility(View.VISIBLE);
                        txt_pt_lg3_about.setText(update_about);

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "About not updated");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Some error");
                        et_hs_lg1_about.setVisibility(View.INVISIBLE);
                        txt_pt_lg3_about.setVisibility(View.VISIBLE);
                        btn_hs_lg1_about_done.setVisibility(View.INVISIBLE);
                        btn_hs_lg1_about_update.setVisibility(View.VISIBLE);
                        txt_pt_lg3_about.setText(update_about);

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


        smr.addStringParam("about", update_about);
        smr.addStringParam("hospital_id", hospital_id);

        MyApplication.getInstance().addToRequestQueue(smr);


    }
}
