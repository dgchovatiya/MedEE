package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_Signup6_Activity;
import com.example.mitul.hospitalfinder.Activity.Hospital.Hospital_SignupDocterDetails;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalResponse;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Patient_Login3_Blood_Book_Activity extends AppCompatActivity {

    TextView txt_pt_lg3_blood_group, txt_pt_lg3_blood_note, et_pt_lg3_blood_date;
    EditText et_pt_lg3_blood_quantity;
    Button btn_pt_lg3_blood_book, btn_pt_lg3_blood_date;
    ImageView pt_lg3_order_blood_back;
    Context context;
    String patient_id, hospital_id, blood_id, blood_group, stock, quantity, date;
    int quantity1, stock1;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__blood__book_);
        getSupportActionBar().hide();
        initview();
        setListener();
    }

    private void initview() {
        context = Patient_Login3_Blood_Book_Activity.this;
        txt_pt_lg3_blood_group = (TextView) findViewById(R.id.txt_pt_lg3_blood_group);
        txt_pt_lg3_blood_note = (TextView) findViewById(R.id.txt_pt_lg3_blood_note);
        et_pt_lg3_blood_quantity = (EditText) findViewById(R.id.et_pt_lg3_blood_quantity);
        et_pt_lg3_blood_date = (TextView) findViewById(R.id.et_pt_lg3_blood_date);
        btn_pt_lg3_blood_book = (Button) findViewById(R.id.btn_pt_lg3_blood_book);
        pt_lg3_order_blood_back = (ImageView) findViewById(R.id.pt_lg3_order_blood_back);
        btn_pt_lg3_blood_date = (Button) findViewById(R.id.btn_pt_lg3_blood_date);

        patient_id = getIntent().getStringExtra("patient_id");
        hospital_id = getIntent().getStringExtra("hospital_id");
        blood_id = getIntent().getStringExtra("blood_id");
        blood_group = getIntent().getStringExtra("blood_group");
        stock = getIntent().getStringExtra("stock");
        txt_pt_lg3_blood_group.setText(blood_group);
        stock1 = Integer.parseInt(stock);
        String txt = "*Hospital has " + stock + " Blood Bottels";
        txt_pt_lg3_blood_note.setText(txt);


    }

    private void setListener() {

        btn_pt_lg3_blood_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = et_pt_lg3_blood_quantity.getText().toString().trim();
                date = et_pt_lg3_blood_date.getText().toString().trim();

                if (quantity.length() == 0) {
                    AppConstants.openErrorDialog(context, "please enter quantity");

                } else {
                    quantity1 = Integer.parseInt(quantity);

                    if (date.length() == 0) {
                        AppConstants.openErrorDialog(context, "please Choose Date");


                    } else if (quantity1 > stock1) {
                        AppConstants.openErrorDialog(context, "please enter limited quantity");


                    } else {

                        CallBloodBookApi();
                    }
                }

            }
        });
        btn_pt_lg3_blood_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {


                            @SuppressLint("WrongConstant")
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Toast.makeText(context,year + "/" + (month +1)  + "/" + dayOfMonth,0).show();
                                et_pt_lg3_blood_date.setText(year + "/" + (month +1)  + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - 1000);

                datePickerDialog.show();
            }
        });
        pt_lg3_order_blood_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void CallBloodBookApi() {
      /*  Log.e("hospital_id",hospital_id);
        Log.e("patient_id",patient_id);
        Log.e("blood_id",blood_id);
        Log.e("blood_group",blood_group);
        Log.e("stock",stock);
        Log.e("quantity",quantity);
        Log.e("stock",stock1+"");
        Log.e("quantity",quantity1+"");*/
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.PATIENT_BLOOD_BOOK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Response", response);
                AppConstants.dissmissDialog();
                Log.e("Res hos_up_blood_book", response);
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
                        txt_error_msg.setText("Your Blood Order Request has been sent");
                        btn_done.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();

                                finish();
                            }
                        });
                        dialog.show();

                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Not Inserted");

                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Field is not set");


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.dissmissDialog();

                AppConstants.openErrorDialog(context, "Error........");

            }
        });
        smr.addStringParam("patient_id", patient_id);
        smr.addStringParam("hospital_id", hospital_id);
        smr.addStringParam("blood_id", blood_id);
        smr.addStringParam("quantity", quantity);
        smr.addStringParam("date", date);

        MyApplication.getInstance().addToRequestQueue(smr);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
