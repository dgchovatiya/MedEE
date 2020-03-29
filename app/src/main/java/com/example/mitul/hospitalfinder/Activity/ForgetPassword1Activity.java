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
import com.example.mitul.hospitalfinder.R;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class ForgetPassword1Activity extends AppCompatActivity {
    EditText et_fp_otp;
    Button fp_btn_next1;
    TextView txt_fs_resend;
    Context context;
    String otp1,email_id,otp,status,email_status;
public static ForgetPassword1Activity fpa1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password1);
        getSupportActionBar().hide();
        initview();
        setlistner();
        CallAPiEmail();
    }

    private void initview() {
        context=ForgetPassword1Activity.this;
        fpa1=ForgetPassword1Activity.this;
        email_id=getIntent().getStringExtra("email_id");

        et_fp_otp=(EditText)findViewById(R.id.et_fp_otp);
        fp_btn_next1=(Button)findViewById(R.id.fp_btn_next1);
        txt_fs_resend=(TextView)findViewById(R.id.txt_fs_resend);

    }

    private void setlistner() {

        fp_btn_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp1=et_fp_otp.getText().toString().trim();
                if(otp1.length()==0){

                    AppConstants.openErrorDialog(context,"Enter otp..");
                    et_fp_otp.requestFocus();
                }
                else{
                    if(otp1.equals(otp)){
                        Intent intent=new Intent(ForgetPassword1Activity.this,ForgetPassword2Activity.class);
                        intent.putExtra("email_id",email_id);
                        startActivity(intent);
                        finish();
                    }
                        else{
                        AppConstants.openErrorDialog(context,"Wrong otp");

                    }

                }
            }
        });

        txt_fs_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallAPiEmail();
            }
        });

    }

    private void CallAPiEmail() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.FORGETPASSWORD1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_ins_docter_Ds", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                     status=jsonObject.getString("STATUS").toString().trim();
                    Log.e("status",status);

                    if(status.equals("1") ){

                        otp=jsonObject.getString("otp").toString().trim();
                        email_status=jsonObject.getString("emial_status").toString().trim();
                        Log.e("otp",otp);
                        Log.e("email_Status",email_status);

                    }
                    else if(status.equals("0")){
                        AppConstants.openErrorDialog(context,"Email not found");


                    }
                    /*else if(email_status.equals("not send")){
                        AppConstants.openErrorDialog(context,"Email not send");

                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.openErrorDialog(context,"Some error");
            }
        });




        smr.addStringParam("email_id",email_id);

        MyApplication.getInstance().addToRequestQueue(smr);

    }
}
