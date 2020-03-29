package com.example.mitul.hospitalfinder.Fragment.Doctor;

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

import com.example.mitul.hospitalfinder.Adapter.Doctor.Doctor_Appointment_Reject_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Doctor.Doctor_Appointment_Sucess_Adapter;
import com.example.mitul.hospitalfinder.Class.Doctor.Doctor_Appointment;
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
public class Doctor_Reject_Appointment_Request extends Fragment{

    RecyclerView pt_lg2_app_rej_rv;
    TextView txt_pt_lg1_rej_app_nofound;
    Context context;
    ArrayList<Doctor_Appointment> docter_appointments_reject_list;
    SwipeRefreshLayout pt_lg2_app_rej_swipeContainer;
    String doctor_id;

    String APPOINMENT_ID,APPOINMENT_ID1,REASON;

    @SuppressLint("ValidFragment")
    public Doctor_Reject_Appointment_Request(Context context, String doctor_id) {
        this.context = context;
        this.doctor_id = doctor_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_reject_appointment_request, container, false);
        pt_lg2_app_rej_rv = (RecyclerView) view.findViewById(R.id.pt_lg2_app_rej_rv);
        txt_pt_lg1_rej_app_nofound = (TextView) view.findViewById(R.id.txt_pt_lg1_rej_app_nofound);
        pt_lg2_app_rej_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg2_app_rej_swipeContainer);

        CallPatientAppointmentPendingRequstApi();

        pt_lg2_app_rej_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

            }
        });


        return view;
    }

    private void CallPatientAppointmentPendingRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("docter_id", doctor_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.DOCTOR_APPOINTMENT_REJECT_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
                        JSONArray jsonArray = jsonObject.getJSONArray("Hospital_Appointment_Details");
                        docter_appointments_reject_list = new ArrayList<>();
                        if ((jsonArray.length()!=0)) {
                            txt_pt_lg1_rej_app_nofound.setVisibility(View.GONE);


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String appointment_id = object.getString("appointment_id");
                                String patient_id = object.getString("patient_id");
                                String hospital_id = object.getString("hospital_id");
                                String docter_id = object.getString("docter_id");
                                String date = object.getString("date");
                                String time = object.getString("time");
                                String email_id = object.getString("email_id");
                                String name = object.getString("name");
                                String mobile_number = object.getString("mobile_number");
                                String city = object.getString("city");
                                String state = object.getString("state");
                                String first_name = object.getString("patient_first_name");
                                String middle_name = object.getString("patient_middle_name");
                                String last_name = object.getString("patient_last_name");
                                String reason=object.getString("reason");
                                Log.e("json", appointment_id + patient_id + hospital_id + name + first_name);

                                String patientname = first_name +" "+ middle_name +" "+ last_name;
                                Doctor_Appointment doctor_appointment = new Doctor_Appointment();
                                doctor_appointment.setAppointment_id(appointment_id);
                                doctor_appointment.setPatient_id(patient_id);
                                doctor_appointment.setHospital_id(hospital_id);
                                doctor_appointment.setDocter_id(docter_id);
                                doctor_appointment.setDate(date);
                                doctor_appointment.setTime(time);
                                doctor_appointment.setEmail_id(email_id);
                                doctor_appointment.setHosptial_name(name);
                                doctor_appointment.setCity(city);
                                doctor_appointment.setState(state);
                                doctor_appointment.setPatient_name(patientname);
                                doctor_appointment.setReason(reason);

                                docter_appointments_reject_list.add(doctor_appointment);
                            }
                            CallAdpter();
                        }
                        else {
                            CallAdpter();

                            txt_pt_lg1_rej_app_nofound.setVisibility(View.VISIBLE);

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
        pt_lg2_app_rej_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        Collections.sort(docter_appointments_reject_list, new Comparator<Doctor_Appointment>() {
            @Override
            public int compare(Doctor_Appointment o1, Doctor_Appointment o2) {
                return o1.getDate().compareToIgnoreCase(o2.getDate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_app_rej_rv.setLayoutManager(layoutManager);
        Doctor_Appointment_Reject_Adapter adapter = new Doctor_Appointment_Reject_Adapter(context, docter_appointments_reject_list);
        pt_lg2_app_rej_rv.setAdapter(adapter);
    }











}
