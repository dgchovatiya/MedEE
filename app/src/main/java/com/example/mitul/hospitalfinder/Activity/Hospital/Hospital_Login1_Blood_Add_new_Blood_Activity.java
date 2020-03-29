package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
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
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;

public class Hospital_Login1_Blood_Add_new_Blood_Activity extends AppCompatActivity {
    EditText et_hs_new_blood_name, et_hs_new_blood_stock;
    Button btn_hs_lg_new_blood_Add;
    Context context;
    Spinner sp_hs_new_blood_name;
    String hospital_id1, blood_group, stock;
    ArrayList<String> bloodlist;
    int stock1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1__blood__add_new__blood_);
        getSupportActionBar().hide();
        initview();
        setListener();
    }

    private void initview() {
        context = Hospital_Login1_Blood_Add_new_Blood_Activity.this;
       /* et_hs_new_blood_name = (EditText) findViewById(R.id.et_hs_new_blood_name);*/
        sp_hs_new_blood_name = (Spinner) findViewById(R.id.sp_hs_new_blood_name);
        et_hs_new_blood_stock = (EditText) findViewById(R.id.et_hs_new_blood_stock);
        btn_hs_lg_new_blood_Add = (Button) findViewById(R.id.btn_hs_lg_new_blood_Add);
        hospital_id1 = getIntent().getStringExtra("hospital_id");
        bloodlist = new ArrayList<>();
        bloodlist.add("--Choose Blood Group--");
        bloodlist.add("A+");
        bloodlist.add("A-");
        bloodlist.add("B+");
        bloodlist.add("B-");
        bloodlist.add("O+");
        bloodlist.add("O-");
        setAdapter(sp_hs_new_blood_name, bloodlist);


    }

    private void setListener() {

        btn_hs_lg_new_blood_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   blood_group = et_hs_new_blood_name.getText().toString().trim();
                blood_group = sp_hs_new_blood_name.getSelectedItem().toString().trim();
                stock = et_hs_new_blood_stock.getText().toString().trim();
                if (blood_group.equals("--Choose Blood Group--")) {
                    AppConstants.openErrorDialog(context, "Please choose  Blood Group first...");


                } else if (stock.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please Enter Stock first...");


                } else {
                    stock1 = Integer.parseInt(stock);

                    if (stock1 < 0) {
                        et_hs_new_blood_stock.setError("Nagative stock is not valid");
                        et_hs_new_blood_stock.setText("");
                        et_hs_new_blood_stock.requestFocus();
                    } else {
                        CallCheckBloodApi();

                    }
                }

            }
        });
    }

    private void CallCheckBloodApi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_CHECK_NEW__BLOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("Res hos_new_medicne", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {
                        AppConstants.dissmissDialog();

                        AppConstants.openErrorDialog(context, "Blood is already exist");
                        bloodlist = new ArrayList<>();
                        bloodlist.add("--Choose Blood Group--");
                        bloodlist.add("A+");
                        bloodlist.add("A-");
                        bloodlist.add("B+");
                        bloodlist.add("B-");
                        bloodlist.add("O+");
                        bloodlist.add("O-");
                        setAdapter(sp_hs_new_blood_name, bloodlist);
                        et_hs_new_blood_stock.setText("");

                    } else if (status.equals("0")) {
                        CallUpdateBloodAPi();


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

        smr.addStringParam("hospital_id",hospital_id1);
        smr.addStringParam("blood_group", blood_group);

        MyApplication.getInstance().addToRequestQueue(smr);
    }

    private void CallUpdateBloodAPi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_INSERT_NEW__BLOOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_new_medicne", response);

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
                        txt_error_msg.setText("Inserted");
                        btn_done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                                finish();
                            }
                        });
                        dialog.show();

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Medicine not Inserted");


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


        smr.addStringParam("hospital_id", hospital_id1);
        smr.addStringParam("blood_group", blood_group);
        smr.addStringParam("stock", stock);

        MyApplication.getInstance().addToRequestQueue(smr);


    }

    private void setAdapter(Spinner sp_hs_new_blood_name, List<String> list_category) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.hs_su1_hoscategory_spinner, R.id.txt_view, list_category) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        sp_hs_new_blood_name.setAdapter(arrayAdapter);
    }
}
