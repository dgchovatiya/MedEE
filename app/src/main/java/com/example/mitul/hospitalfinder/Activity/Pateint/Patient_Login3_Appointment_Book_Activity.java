package com.example.mitul.hospitalfinder.Activity.Pateint;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.TimePicker;
import android.widget.Toast;

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

import java.util.Calendar;

public class Patient_Login3_Appointment_Book_Activity extends AppCompatActivity {
    Context context;
    TextView et_pt_lg3_appointment_book_date,et_pt_lg3_appointment_book_time;
    Button btn_pt_lg3_et_appointment_book,btn_pt_lg3_ap_date,btn_pt_lg3_ap_time;
    ImageView pt_lg3_appointment_book_back;

    String hospital_id, patient_id, docter_id;
    String date, time;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__login3__appointment__book_);

        initview();
        setListner();
    }


    private void initview() {
        getSupportActionBar().hide();

        context = Patient_Login3_Appointment_Book_Activity.this;
        hospital_id = getIntent().getStringExtra("hospital_id");
        patient_id = getIntent().getStringExtra("patient_id");
        docter_id = getIntent().getStringExtra("docter_id");

        et_pt_lg3_appointment_book_date = (TextView) findViewById(R.id.et_pt_lg3_appointment_book_date);
        et_pt_lg3_appointment_book_time = (TextView) findViewById(R.id.et_pt_lg3_appointment_book_time);
        btn_pt_lg3_et_appointment_book = (Button) findViewById(R.id.btn_pt_lg3_et_appointment_book);
        btn_pt_lg3_ap_time=(Button)findViewById(R.id.btn_pt_lg3_ap_time);
        btn_pt_lg3_ap_date=(Button)findViewById(R.id.btn_pt_lg3_ap_date) ;
        pt_lg3_appointment_book_back=(ImageView)findViewById(R.id.pt_lg3_appointment_book_back);


    }


    private void setListner() {
        btn_pt_lg3_et_appointment_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=et_pt_lg3_appointment_book_date.getText().toString().trim();
                time=et_pt_lg3_appointment_book_time.getText().toString().trim();
                if(date.length()==0){
                    AppConstants.openErrorDialog(context, "please Choose Date");
                }else if(time.length()==0){
                    AppConstants.openErrorDialog(context, "please Choose Time");
                }else{
                    callAppointmnetbookApi();
                }
            }
        });
        btn_pt_lg3_ap_date.setOnClickListener(new View.OnClickListener() {
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
                                Toast.makeText(context,year + "/" + (month +1) + "/" + dayOfMonth,0).show();
                                et_pt_lg3_appointment_book_date.setText(year + "/" + (month +1) + "/" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis() - 1000);

                datePickerDialog.show();
            }
        });
        btn_pt_lg3_ap_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                et_pt_lg3_appointment_book_time.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }

        });
        pt_lg3_appointment_book_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void callAppointmnetbookApi() {
        AppConstants.showDialog(context);
        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.PATIENT_APPOINTMENT_BOOK_REQUEST, new Response.Listener<String>() {
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
                        txt_error_msg.setText("Your Appointment Request has been sent");
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


                    }else if(status.equals("000")){
                        AppConstants.openErrorDialog(context,"You have already Book apoointment for same docter");
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
        smr.addStringParam("docter_id", docter_id);
        smr.addStringParam("date",date);
        smr.addStringParam("time",time);


        MyApplication.getInstance().addToRequestQueue(smr);

    }
}
