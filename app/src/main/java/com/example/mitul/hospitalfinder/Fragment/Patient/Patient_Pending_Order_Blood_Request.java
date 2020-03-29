package com.example.mitul.hospitalfinder.Fragment.Patient;

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

import com.example.mitul.hospitalfinder.Adapter.Hospital.Hospital_Order_Blood_pending_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderBlooditem1;
import com.example.mitul.hospitalfinder.Class.Hospital.OrderMedicineitem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Hospital.Order_Blood;
import com.example.mitul.hospitalfinder.Model.Patient.PatientOrderDetail;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Blood;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Denish on 01-02-2019.
 */

@SuppressLint("ValidFragment")
public class Patient_Pending_Order_Blood_Request extends Fragment implements  Patient_Order_Blood_pending_Adapter.DeleteCancleRqeuest{
    RecyclerView pt_lg2_bd_pen_rv;
    TextView txt_pt_lg2_order_pen_bd_nofound;
    Context context;
    ArrayList<PatientOrderBlooditem> patient_blood_order_pending_list;
    SwipeRefreshLayout pt_lg2_bd_pend_swipeContainer;
    Patient_Order_Blood_pending_Adapter.DeleteCancleRqeuest deleteCancleRqeuest;

    String patient_id;
    String ORDERID,BLOOD_ID,STOCK;

    public Patient_Pending_Order_Blood_Request(Context context, String patient_id) {
        this.context = context;
        this.patient_id = patient_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_pending_order_blood_request, container, false);
        pt_lg2_bd_pen_rv = (RecyclerView) view.findViewById(R.id.pt_lg2_bd_pen_rv);
        txt_pt_lg2_order_pen_bd_nofound=(TextView)view.findViewById(R.id.txt_pt_lg2_order_pen_bd_nofound);
        deleteCancleRqeuest = this;

        pt_lg2_bd_pend_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg2_bd_pend_swipeContainer);

        CallPatientBloodPendingRequstApi();

        pt_lg2_bd_pend_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

               CallAdpter();
            }
        });


        return view;
    }

    private void CallPatientBloodPendingRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id", patient_id);
        Log.e("a","a");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_BLOOOD_PENDIGNG_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Patient_Order_Blood patient_order_blood = gson.fromJson(response, Patient_Order_Blood.class);
                String stauts = patient_order_blood.getSTATUS().toString().trim();
                patient_blood_order_pending_list = new ArrayList<>();
                Log.e("status", stauts);

                    if(stauts.equals("1")){
                        if(patient_order_blood.getPatientOrderDetails().size()!=0) {
                            txt_pt_lg2_order_pen_bd_nofound.setVisibility(View.GONE);
                            for (int i = 0; i < patient_order_blood.getPatientOrderDetails().size(); i++) {


                                String order_id = patient_order_blood.getPatientOrderDetails().get(i).getOrderId().toString().trim();
                                String hospital_id=patient_order_blood.getPatientOrderDetails().get(i).getHospitalId().toString().trim();
                                String Patient_id = patient_order_blood.getPatientOrderDetails().get(i).getPatientId().toString().trim();
                                String blood_id = patient_order_blood.getPatientOrderDetails().get(i).getBloodId().toString().trim();
                                String hname = patient_order_blood.getPatientOrderDetails().get(i).getName().toString().trim();
                                String hmo = patient_order_blood.getPatientOrderDetails().get(i).getMobileNumber().toString().trim();
                                String quntity = patient_order_blood.getPatientOrderDetails().get(i).getQuantity().toString().trim();
                                String date = patient_order_blood.getPatientOrderDetails().get(i).getDate().toString().trim();
                                String bloodgroup = patient_order_blood.getPatientOrderDetails().get(i).getBloodGroup().toString().trim();

                                PatientOrderBlooditem patientOrderBlooditem = new PatientOrderBlooditem();
                                patientOrderBlooditem.setOrder_id(order_id);
                                patientOrderBlooditem.setHospital_id(hospital_id);
                                patientOrderBlooditem.setPatient_id(Patient_id);
                                patientOrderBlooditem.setBlood_id(blood_id);
                                patientOrderBlooditem.setHospital_name(hname);
                                patientOrderBlooditem.setHospitalmob(hmo);
                                patientOrderBlooditem.setBookingdate(date);
                                patientOrderBlooditem.setQuantity(quntity);
                                patientOrderBlooditem.setBloodgroup(bloodgroup);
                                patient_blood_order_pending_list.add(patientOrderBlooditem);

                            }
                            CallAdpter();


                        }
                        else{
                            CallAdpter();

                            txt_pt_lg2_order_pen_bd_nofound.setVisibility(View.VISIBLE);

                        }
                }else if(stauts.equals("0")){
                        AppConstants.openErrorDialog(context, "Not available");

                    }
                else if(stauts.equals("00")){
                        AppConstants.openErrorDialog(context, "Field is not set");

                    }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });
    }


    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        CallPatientBloodPendingRequstApi();
        pt_lg2_bd_pend_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        AppConstants.dissmissDialog();

        Collections.sort(patient_blood_order_pending_list, new Comparator<PatientOrderBlooditem>() {
            @Override
            public int compare(PatientOrderBlooditem o1, PatientOrderBlooditem o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_bd_pen_rv.setLayoutManager(layoutManager);
        Patient_Order_Blood_pending_Adapter adapter = new Patient_Order_Blood_pending_Adapter(context, patient_blood_order_pending_list,deleteCancleRqeuest);
        pt_lg2_bd_pen_rv.setAdapter(adapter);
    }



    private void CallMakeCancleOrderBloodRequestApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("order_id", ORDERID);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_BLOOOD_CANCALE_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
        txt_error_msg.setText("Your Blood Order Request has been Canacled");
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CallPatientBloodPendingRequstApi();
                CallAdpter();
            }

        });
        dialog.show();

    }


    @Override
    public void deletedata(int position, String order_id, String blood_id, String stock) {
     ORDERID=order_id;
     Log.e("Orderid",ORDERID);



        CallMakeCancleOrderBloodRequestApi();
    }




}
