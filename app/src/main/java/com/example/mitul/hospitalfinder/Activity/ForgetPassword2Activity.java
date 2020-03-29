package com.example.mitul.hospitalfinder.Activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidPassword;

public class ForgetPassword2Activity extends AppCompatActivity {
    Context context;
    EditText et_fp_pwd, et_fp_cwd;
    Button fp_btn_update, btn_fp_login;
    String email_id;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password2);
        getSupportActionBar().hide();
        initview();
        setListner();


    }

    private void initview() {
        context = ForgetPassword2Activity.this;
        email_id = getIntent().getStringExtra("email_id");

        et_fp_pwd = (EditText) findViewById(R.id.et_fp_pwd);
        fp_btn_update = (Button) findViewById(R.id.fp_btn_update);
        et_fp_cwd = (EditText) findViewById(R.id.et_fp_cwd);
        btn_fp_login = (Button) findViewById(R.id.btn_fp_login);

    }

    private void setListner() {
        fp_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password = et_fp_pwd.getText().toString().trim();
                if (password.length() == 0) {

                    AppConstants.openErrorDialog(context, "Please enter password First...");
                    et_fp_pwd.requestFocus();

                } else if (et_fp_cwd.getText().length() == 0) {

                    AppConstants.openErrorDialog(context, "Please enter confirm password First...");
                    et_fp_cwd.requestFocus();

                } else {
                    if (password.equals(et_fp_cwd.getText().toString())) {
                        if (!isValidPassword(password)) {
                            AppConstants.openErrorDialog(context, "Inalid password Format");
                            et_fp_pwd.setText("");
                            et_fp_cwd.setText("");
                            et_fp_pwd.requestFocus();

                        } else {

                            CallUpdatePasswordApi();
                        }
                    }
                    else{

                        AppConstants.openErrorDialog(context,"Do not match Confirm Password");
                    }
                }

            }
        });
        btn_fp_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void CallUpdatePasswordApi() {

        AppConstants.showDialog(context);


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.FORGETPASSWORD2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_ins_docter_Ds", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("msg");

                    if (status.equals("1")) {

                        final Dialog dialog = new Dialog(context);
                        dialog.setContentView(R.layout.dialog_messge);
                        dialog.setCancelable(false);

                        Window window = dialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        final Button btn_done = (Button) dialog.findViewById(R.id.btn_done1);
                        TextView txt_error_msg = (TextView) dialog.findViewById(R.id.txt_error_msg1);
                        txt_error_msg.setText("Updated");
                        btn_done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                ForgetPassword1Activity.fpa1.finish();
                                ForgetPasswordActivity.fpa.finish();
                                finish();                            }
                        });
                        dialog.show();

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "password not updated");


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


        smr.addStringParam("email_id", email_id);
        smr.addStringParam("password", password);

        MyApplication.getInstance().addToRequestQueue(smr);


    }

}
