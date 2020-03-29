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

import com.example.mitul.hospitalfinder.Adapter.Doctor.Doctor_Appointment_Pending_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Login1_Appointment_Pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Doctor.Doctor_Appointment;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
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
 * Created by Denish on 06-03-2019.
 */

@SuppressLint("ValidFragment")
public class Doctor_Pending_Appointment_Request extends Fragment implements Doctor_Appointment_Pending_Adapter.DeleteRejectedRqeuest,Doctor_Appointment_Pending_Adapter.DeleteSucessRqeuest {

    RecyclerView d_app_pen_rv;
    TextView d_lg1_app_nofound;
    Context context;
    ArrayList<Doctor_Appointment> docter_appointments_pending_list;
    SwipeRefreshLayout d_app_pend_swipeContainer;
    String doctor_id;
    Doctor_Appointment_Pending_Adapter.DeleteRejectedRqeuest deleteRejectedRqeuest;
    Doctor_Appointment_Pending_Adapter.DeleteSucessRqeuest deleteSucessRqeuest;

    String APPOINMENT_ID,APPOINMENT_ID1,REASON;

    @SuppressLint("ValidFragment")
    public Doctor_Pending_Appointment_Request(Context context, String doctor_id) {
        this.context = context;
        this.doctor_id = doctor_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.doctor_pending_appointment_request, container, false);
        d_app_pen_rv = (RecyclerView) view.findViewById(R.id.d_app_pen_rv);
        d_lg1_app_nofound = (TextView) view.findViewById(R.id.d_lg1_app_nofound);
        deleteRejectedRqeuest = this;
        deleteSucessRqeuest=this;
        d_app_pend_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.d_app_pend_swipeContainer);

        CallPatientAppointmentPendingRequstApi();

        d_app_pend_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        asyncHttpClient.post(context, URL.DOCTOR_APPOINTMENT_PENIDNG_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
                        docter_appointments_pending_list = new ArrayList<>();
                        if ((jsonArray.length()!=0)) {
                            d_lg1_app_nofound.setVisibility(View.GONE);


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

                                docter_appointments_pending_list.add(doctor_appointment);
                            }
                            CallAdpter();
                        }
                        else {
                            CallAdpter();

                            d_lg1_app_nofound.setVisibility(View.VISIBLE);

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
        d_app_pend_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        Collections.sort(docter_appointments_pending_list, new Comparator<Doctor_Appointment>() {
            @Override
            public int compare(Doctor_Appointment o1, Doctor_Appointment o2) {
                return o1.getDate().compareToIgnoreCase(o2.getDate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        d_app_pen_rv.setLayoutManager(layoutManager);
        Doctor_Appointment_Pending_Adapter adapter = new Doctor_Appointment_Pending_Adapter(context, docter_appointments_pending_list, deleteRejectedRqeuest,deleteSucessRqeuest);
        d_app_pen_rv.setAdapter(adapter);
    }


    private void CallMakeCancleOrderBloodRequestApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("appointment_id", APPOINMENT_ID);
        requestParams.put("reason",REASON);
        Log.e("appoinment_id",APPOINMENT_ID);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.DOCTOR_APPOINTMENT_CANCLE_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
        txt_error_msg.setText("Appointment Request has been Canacled");
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
    public void deletedata1(String apointment_id,String reason) {
        APPOINMENT_ID = apointment_id;
        REASON=reason;
        CallMakeCancleOrderBloodRequestApi();
    }


    @Override
    public void deletedata(String apointment_id) {
        APPOINMENT_ID1 = apointment_id;
        CallmakesucessApi();
    }

    private void CallmakesucessApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("appointment_id", APPOINMENT_ID1);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.DOCTOR_APPOINTMENT_MAKE_CONFIRM_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
}
