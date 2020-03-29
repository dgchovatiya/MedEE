package com.example.mitul.hospitalfinder.Fragment.Hospital;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Appointment_Pending_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login1_Appointment_Pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Doctor.Doctor_Appointment;
import com.example.mitul.hospitalfinder.Class.Hospital.Hospital_Appointment;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Denish on 06-03-2019.
 */

@SuppressLint("ValidFragment")
public class Hospital_Pending_Appointment_Request extends Fragment implements Hospital_Appointment_Pending_Adapter.DeleteCancleRqeuest {

    RecyclerView hs_lg1_app_pen_rv;
    TextView txt_hs_lg1_app_nofound;
    Context context;
    ArrayList<Hospital_Appointment> hospital_appointments_pending_list;
    SwipeRefreshLayout hs_lg1_app_pend_swipeContainer;
    String hospital_id;
    Hospital_Appointment_Pending_Adapter.DeleteCancleRqeuest deleteCancleRqeuest;
    String APPOINMENT_ID;

    @SuppressLint("ValidFragment")
    public Hospital_Pending_Appointment_Request(Context context, String hospital_id) {
        this.context = context;
        this.hospital_id = hospital_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hospital_pending_appointment_request, container, false);
        hs_lg1_app_pen_rv = (RecyclerView) view.findViewById(R.id.hs_lg1_app_pen_rv);
        txt_hs_lg1_app_nofound = (TextView) view.findViewById(R.id.txt_hs_lg1_app_nofound);
        deleteCancleRqeuest = this;

        hs_lg1_app_pend_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.hs_lg1_app_pend_swipeContainer);

        CallPatientAppointmentPendingRequstApi();

        hs_lg1_app_pend_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

            }
        });


        return view;
    }

    private void CallPatientAppointmentPendingRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_APPOINTMENT_PENIDNG_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    Log.e("statuspenap", status);
                    if (status.equals("1")) {
                        Log.e("statuspenap", status);

                        JSONArray jsonArray = jsonObject.getJSONArray("Hospital_Appointment_Details");
                        hospital_appointments_pending_list = new ArrayList<>();
                        if ((jsonArray.length()!=0)) {
                            txt_hs_lg1_app_nofound.setVisibility(View.GONE);


                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.e("statuspenap", status);

                                JSONObject object = jsonArray.getJSONObject(i);
                                String appointment_id = object.getString("appointment_id");
                                String patient_id = object.getString("patient_id");
                                String hospital_id = object.getString("hospital_id");
                                String docter_id = object.getString("docter_id");
                                String date = object.getString("date");
                                String time = object.getString("time");
                                String email_id = object.getString("email_id");
                                String pfname = object.getString("patient_first_name");
                                String pmname = object.getString("patient_middle_name");
                                String plname = object.getString("patient_last_name");
                                String mobile_number = object.getString("mobile_number");

                                String dfirst_name = object.getString("first_name");
                                String dmiddle_name = object.getString("middle_name");
                                String dlast_name = object.getString("last_name");
                                Log.e("json", appointment_id + patient_id + hospital_id + pfname + dfirst_name);

                                String patient_name=pfname+" "+pmname+" "+plname;
                                String doctername = dfirst_name + " " +dmiddle_name +" "+ dlast_name;
                                Hospital_Appointment hospital_appointment = new Hospital_Appointment();
                                hospital_appointment.setAppointment_id(appointment_id);
                                hospital_appointment.setPatient_id(patient_id);
                                hospital_appointment.setHospital_id(hospital_id);
                                hospital_appointment.setDocter_id(docter_id);
                                hospital_appointment.setPatinet_name(patient_name);
                                hospital_appointment.setDate(date);
                                hospital_appointment.setTime(time);
                                hospital_appointment.setEmail_id(email_id);
                                hospital_appointment.setMobile_number(mobile_number);

                                hospital_appointment.setDocter_name(doctername);

                                hospital_appointments_pending_list.add(hospital_appointment);
                            }
                            CallAdpter();
                        }
                        else {
                            CallAdpter();

                            txt_hs_lg1_app_nofound.setVisibility(View.VISIBLE);

                        }
                    }
                    else if(status.equals("0")){
                        AppConstants.openErrorDialog(context,"Not Found");
                    }
                    else if(status.equals("00")){
                        AppConstants.openErrorDialog(context,"Field not set");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context,"fail");
            }
        });
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallPatientAppointmentPendingRequstApi();
        hs_lg1_app_pend_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        Log.e("set", "Adapter");
        Collections.sort(hospital_appointments_pending_list, new Comparator<Hospital_Appointment>() {
            @Override
            public int compare(Hospital_Appointment o1, Hospital_Appointment o2) {
                return o1.getDate().compareToIgnoreCase(o2.getDate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        hs_lg1_app_pen_rv.setLayoutManager(layoutManager);
        Hospital_Appointment_Pending_Adapter adapter = new Hospital_Appointment_Pending_Adapter(context, hospital_appointments_pending_list, deleteCancleRqeuest);
        hs_lg1_app_pen_rv.setAdapter(adapter);
    }


    private void CallMakeCancleOrderBloodRequestApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("appointment_id", APPOINMENT_ID);
        Log.e("appoinment_id",APPOINMENT_ID);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.HOSPITAL_APPOINTMENT_PENIDNG_SEND_DOCTER_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("STATUS");
                    String msg = jsonObject.getString("MSG");

                    if (status.equals("1")) {


                        CallDialogwithApi();


                    } else if (status.equals("0")) {
                        AppConstants.openErrorDialog(context, "Your request not cancaled");


                    } else if (status.equals("00")) {
                        AppConstants.openErrorDialog(context, "Some error");


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();

            }
        });
    }

    private void CallDialogwithApi() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_messge);
        dialog.setCancelable(false);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final Button btn_done = (Button) dialog.findViewById(R.id.btn_done1);
        TextView txt_error_msg = (TextView) dialog.findViewById(R.id.txt_error_msg1);
        txt_error_msg.setText("Appointment has been Sent to the Docter");
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CallPatientAppointmentPendingRequstApi();
                //  CallAdpter();
            }

        });
        dialog.show();

    }

    @Override
    public void deletedata(int position, String apointment_id) {
        APPOINMENT_ID = apointment_id;
        Log.e("delete","appointment");
        CallMakeCancleOrderBloodRequestApi();
    }





}
