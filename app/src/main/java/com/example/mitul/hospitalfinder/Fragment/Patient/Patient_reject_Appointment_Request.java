package com.example.mitul.hospitalfinder.Fragment.Patient;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login1_Appointment_reject_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login1_Appointment_sucess_Adapter;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Appointment;
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
 * Created by Denish on 02-02-2019.
 */

@SuppressLint("ValidFragment")
public class Patient_reject_Appointment_Request extends Fragment {
    RecyclerView pt_lg1_ap_rej_rv;
    TextView txt_pt_lg1_ap_rej_nofound;
    Context context;
    ArrayList<Patient_Appointment> patient_appointments_reject_list;
    SwipeRefreshLayout pt_lg1_ap_rej_swipeContainer;


    String patient_id;
    String ORDERID, BLOOD_ID, STOCK, REASON;

    public Patient_reject_Appointment_Request(Context context, String patient_id) {
        this.context = context;
        this.patient_id = patient_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_reject_appointment_request, container, false);
        pt_lg1_ap_rej_rv = (RecyclerView) view.findViewById(R.id.pt_lg1_ap_rej_rv);
        txt_pt_lg1_ap_rej_nofound = (TextView) view.findViewById(R.id.txt_pt_lg1_ap_rej_nofound);
        pt_lg1_ap_rej_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg1_ap_rej_swipeContainer);
        CallPatientAppointmentSucessRequstApi();
        pt_lg1_ap_rej_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

            }
        });


        return view;
    }

    private void CallAdpter() {

        Collections.sort(patient_appointments_reject_list, new Comparator<Patient_Appointment>() {
            @Override
            public int compare(Patient_Appointment o1, Patient_Appointment o2) {
                return o1.getDate().compareToIgnoreCase(o2.getDate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg1_ap_rej_rv.setLayoutManager(layoutManager);
        Patient_Login1_Appointment_reject_Adapter adapter = new Patient_Login1_Appointment_reject_Adapter(context, patient_appointments_reject_list);
        pt_lg1_ap_rej_rv.setAdapter(adapter);
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallPatientAppointmentSucessRequstApi();
        pt_lg1_ap_rej_swipeContainer.setRefreshing(false);

    }

    private void CallPatientAppointmentSucessRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id", patient_id);

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_APPOINTMENT_REJECT_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
                        JSONArray jsonArray = jsonObject.getJSONArray("Patient_Appointment_Details");
                        patient_appointments_reject_list = new ArrayList<>();
                        if ((jsonArray.length()!=0)) {
                            txt_pt_lg1_ap_rej_nofound.setVisibility(View.GONE);


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
                                String first_name = object.getString("first_name");
                                String middle_name = object.getString("middle_name");
                                String last_name = object.getString("last_name");
                                String reason=object.getString("reason");

                                Log.e("json", appointment_id + patient_id + hospital_id + name + first_name);

                                String doctername = first_name + middle_name + last_name;
                                Patient_Appointment patient_appointment = new Patient_Appointment();
                                patient_appointment.setAppointment_id(appointment_id);
                                patient_appointment.setPatient_id(patient_id);
                                patient_appointment.setHospital_id(hospital_id);
                                patient_appointment.setDocter_id(docter_id);
                                patient_appointment.setDate(date);
                                patient_appointment.setTime(time);
                                patient_appointment.setEmail_id(email_id);
                                patient_appointment.setName(name);
                                patient_appointment.setMobile_number(mobile_number);
                                patient_appointment.setCity(city);
                                patient_appointment.setState(state);
                                patient_appointment.setDocter_name(doctername);
                                patient_appointment.setReason(reason);
                                patient_appointments_reject_list.add(patient_appointment);
                            }
                            CallAdpter();
                        }
                        else {
                            CallAdpter();

                            txt_pt_lg1_ap_rej_nofound.setVisibility(View.VISIBLE);

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
                AppConstants.openErrorDialog(context, "fail");

            }
        });
    }


}
