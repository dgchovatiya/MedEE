package com.example.mitul.hospitalfinder.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalinsertDocterDetails;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText et_fp_email;
    Button fp_btn_next;
    Context context;
    String email_id;
    public static ForgetPasswordActivity fpa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        initview();
        setlistner();
    }
    private void initview() {
        context=ForgetPasswordActivity.this;
        fpa=ForgetPasswordActivity.this;


        et_fp_email=(EditText)findViewById(R.id.et_fp_email);
        fp_btn_next=(Button)findViewById(R.id.fp_btn_next);

    }

    private void setlistner() {

        fp_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               email_id=et_fp_email.getText().toString().trim();
               if (email_id.length()==0){

                   AppConstants.openErrorDialog(context,"Enter otp..");
                   et_fp_email.requestFocus();
               }
               else{

                   CallForgetPasswordemailApi();
               }
            }
        });

    }

    private void CallForgetPasswordemailApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.FORGETPASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_ins_docter_Ds", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status=jsonObject.getString("STATUS");
                    String email_id1=jsonObject.getString("email_id");

                    if(status.equals("1")){
                        Intent intent=new Intent(ForgetPasswordActivity.this,ForgetPassword1Activity.class);
                        intent.putExtra("email_id",email_id1);
                        startActivity(intent);
                        finish();

                    }
                    if(status.equals("0")){
                        AppConstants.openErrorDialog(context,"Email do not exist");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context,"Some error");
            }
        });




        smr.addStringParam("email_id",email_id);

        MyApplication.getInstance().addToRequestQueue(smr);

    }
}
