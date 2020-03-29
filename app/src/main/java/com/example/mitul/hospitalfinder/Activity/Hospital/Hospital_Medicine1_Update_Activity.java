package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Activity.ForgetPassword1Activity;
import com.example.mitul.hospitalfinder.Activity.ForgetPasswordActivity;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Hospital_Medicine1_Update_Activity extends AppCompatActivity {
    EditText et_hs_up_med_name, et_hs_up_med_stock;
    Button btn_hs_lg_med_add, btn_hs_lg_med_sub;
    TextView txt_hs_up_med_stock;
    Context context;
    String hospital_id1, medicine_id, name1, stock1, name, stock;
    int stock2, stock3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__medicine1__update_);
        getSupportActionBar().hide();
        initview();
        setListener();
    }


    private void initview() {
        context = Hospital_Medicine1_Update_Activity.this;
        et_hs_up_med_name = (EditText) findViewById(R.id.et_hs_up_med_name);
        et_hs_up_med_stock = (EditText) findViewById(R.id.et_hs_up_med_stock);
        btn_hs_lg_med_add = (Button) findViewById(R.id.btn_hs_lg_med_add);
        btn_hs_lg_med_sub = (Button) findViewById(R.id.btn_hs_lg_med_sub);
        txt_hs_up_med_stock = (TextView) findViewById(R.id.txt_hs_up_med_stock);

        hospital_id1 = getIntent().getStringExtra("hospital_id");
        medicine_id = getIntent().getStringExtra("medicine_id");
        name1 = getIntent().getStringExtra("name");
        stock1 = getIntent().getStringExtra("stock");
        et_hs_up_med_name.setText(name1);
        String s = "Current Stock is :" + stock1;
        txt_hs_up_med_stock.setText(s);
        et_hs_up_med_stock.setText("");


    }

    private void setListener() {

        btn_hs_lg_med_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_hs_up_med_name.getText().toString().trim();
                stock = et_hs_up_med_stock.getText().toString().trim();
                name = name.toUpperCase();
                if (name.length() == 0) {
                    et_hs_up_med_name.setError("Please enter name");
                    et_hs_up_med_name.requestFocus();
                } else if (stock.length() == 0) {
                    et_hs_up_med_stock.setError("Enter Stock");
                    et_hs_up_med_stock.requestFocus();
                } else {
                    stock2 = Integer.parseInt(stock);
                    stock3 = Integer.parseInt(stock1);
                    stock2 = stock2 + stock3;
                    if (stock2 < 0) {
                        et_hs_up_med_stock.setError("Nagative stock is not valid");
                        et_hs_up_med_stock.setText("");
                        et_hs_up_med_stock.requestFocus();
                    } else {
                        stock = stock2 + "";
                        stock = stock.toString().trim();
                        CallUpdateMedicineAPi();
                    }
                }


            }
        });
        btn_hs_lg_med_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = et_hs_up_med_name.getText().toString().trim();
                stock = et_hs_up_med_stock.getText().toString().trim();
                name = name.toUpperCase();
                stock2 = Integer.parseInt(stock);
                if (name.length() == 0) {

                    et_hs_up_med_name.setError("Please Enter Medicine Name");
                    et_hs_up_med_name.requestFocus();

                } else if (stock2 < 0) {
                    et_hs_up_med_stock.setError("Nagative stock is not valid");
                    et_hs_up_med_stock.setText("");
                    et_hs_up_med_stock.requestFocus();
                } else {
                    stock3 = Integer.parseInt(stock1);
                    stock2 = stock3 - stock2;
                    if (stock2 < 0) {
                        et_hs_up_med_stock.setError("Final stock must be positive or 0");
                        et_hs_up_med_stock.setText("");
                        et_hs_up_med_stock.requestFocus();
                    } else {
                        stock = stock2 + "";
                        stock = stock.toString().trim();
                        CallUpdateMedicineAPi();

                    }
                }
            }
        });
    }

    private void CallUpdateMedicineAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_MEDICINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_up_medicne", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

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

                                finish();
                            }
                        });
                        dialog.show();

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


        smr.addStringParam("medicine_id", medicine_id);
        smr.addStringParam("name", name);
        smr.addStringParam("stock", stock);

        MyApplication.getInstance().addToRequestQueue(smr);


    }
}
