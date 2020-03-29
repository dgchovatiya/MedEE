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

import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Blood_pending_Adapter;
import com.example.mitul.hospitalfinder.Adapter.Patient.Patient_Order_Medicine_pending_Adapter;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderBlooditem;
import com.example.mitul.hospitalfinder.Class.Patient.PatientOrderMedicineitem;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Blood;
import com.example.mitul.hospitalfinder.Model.Patient.Patient_Order_Medicine;
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
public class Patient_Pending_Order_Medicine_Request extends Fragment implements  Patient_Order_Medicine_pending_Adapter.DeleteCancleRqeuest{
    RecyclerView pt_lg2_md_pen_rv;
    TextView txt_pt_lg2_order_pen_md_nofound;
    Context context;
    ArrayList<PatientOrderMedicineitem> patient_medicine_order_pending_list;
    SwipeRefreshLayout pt_lg2_md_pend_swipeContainer;
    Patient_Order_Medicine_pending_Adapter.DeleteCancleRqeuest deleteCancleRqeuest;

    String patient_id;
    String order_medicine_id,BLOOD_ID,STOCK;

    public Patient_Pending_Order_Medicine_Request(Context context, String patient_id) {
        this.context = context;
        this.patient_id = patient_id;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.patient_pending_order_medicine_request, container, false);
        pt_lg2_md_pen_rv = (RecyclerView) view.findViewById(R.id.pt_lg2_md_pen_rv);
        txt_pt_lg2_order_pen_md_nofound=(TextView)view.findViewById(R.id.txt_pt_lg2_order_pen_md_nofound);
        deleteCancleRqeuest = this;

        pt_lg2_md_pend_swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.pt_lg2_md_pend_swipeContainer);

        CallPatientMedicinePendingRequstApi();

        pt_lg2_md_pend_swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);

               CallAdpter();
            }
        });


        return view;
    }

    private void CallPatientMedicinePendingRequstApi() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("patient_id", patient_id);
        Log.e("a","a");

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_MEDICINE_PENDIGNG_REQUEST, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("Response", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                Patient_Order_Medicine patient_order_medicine = gson.fromJson(response, Patient_Order_Medicine.class);
                String stauts = patient_order_medicine.getSTATUS().toString().trim();
                patient_medicine_order_pending_list = new ArrayList<>();
                Log.e("status", stauts);

                    if(stauts.equals("1")){
                        if(patient_order_medicine.getPatientMOrderDetails().size()!=0) {
                            txt_pt_lg2_order_pen_md_nofound.setVisibility(View.GONE);
                            for (int i = 0; i < patient_order_medicine.getPatientMOrderDetails().size(); i++) {


                                String order_medicine_id = patient_order_medicine.getPatientMOrderDetails().get(i).getOrderMedicineId().toString().trim();
                                String hospital_id=patient_order_medicine.getPatientMOrderDetails().get(i).getHospitalId().toString().trim();
                                String Patient_id = patient_order_medicine.getPatientMOrderDetails().get(i).getPatientId().toString().trim();
                                String medicine_id = patient_order_medicine.getPatientMOrderDetails().get(i).getMedicineId().toString().trim();
                                String hname = patient_order_medicine.getPatientMOrderDetails().get(i).getName().toString().trim();
                                String hmo = patient_order_medicine.getPatientMOrderDetails().get(i).getMobileNumber().toString().trim();
                                String quntity = patient_order_medicine.getPatientMOrderDetails().get(i).getQuantity().toString().trim();
                                String date = patient_order_medicine.getPatientMOrderDetails().get(i).getDate().toString().trim();
                                String medicine_name = patient_order_medicine.getPatientMOrderDetails().get(i).getMedicineName().toString().trim();

                                PatientOrderMedicineitem patientOrderMedicineitem = new PatientOrderMedicineitem();
                                patientOrderMedicineitem.setOrder_id(order_medicine_id);
                                patientOrderMedicineitem.setHospital_id(hospital_id);
                                patientOrderMedicineitem.setPatient_id(Patient_id);
                                patientOrderMedicineitem.setMedicine_id(medicine_id);
                                patientOrderMedicineitem.setHospital_name(hname);
                                patientOrderMedicineitem.setHospitalmob(hmo);
                                patientOrderMedicineitem.setBookingdate(date);
                                patientOrderMedicineitem.setQuantity(quntity);
                                patientOrderMedicineitem.setMedcine_name(medicine_name);
                                patient_medicine_order_pending_list.add(patientOrderMedicineitem);

                            }
                            CallAdpter();


                        }
                        else{
                            CallAdpter();

                            txt_pt_lg2_order_pen_md_nofound.setVisibility(View.VISIBLE);

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
        CallPatientMedicinePendingRequstApi();
        pt_lg2_md_pend_swipeContainer.setRefreshing(false);

    }

    private void CallAdpter() {
        AppConstants.dissmissDialog();
        Collections.sort(patient_medicine_order_pending_list, new Comparator<PatientOrderMedicineitem>() {
            @Override
            public int compare(PatientOrderMedicineitem o1, PatientOrderMedicineitem o2) {
                return o1.getBookingdate().compareToIgnoreCase(o2.getBookingdate());
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        pt_lg2_md_pen_rv.setLayoutManager(layoutManager);
        Patient_Order_Medicine_pending_Adapter adapter = new Patient_Order_Medicine_pending_Adapter(context, patient_medicine_order_pending_list,deleteCancleRqeuest);
        pt_lg2_md_pen_rv.setAdapter(adapter);
    }



    private void CallMakeCancleOrderMedicineRequestApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("order_medicine_id", order_medicine_id);


        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.PATIENT_ORDER_MEDICINE_CANCALE_REQUEST, requestParams, new AsyncHttpResponseHandler() {
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
        txt_error_msg.setText("Your Medicine Order Request has been Canacled");
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                CallPatientMedicinePendingRequstApi();
                CallAdpter();
            }

        });
        dialog.show();

    }

    @Override
    public void deletedata(int position, String order_id, String blood_id, String stock) {
        order_medicine_id=order_id;
     Log.e("Orderid",order_medicine_id);



        CallMakeCancleOrderMedicineRequestApi();
    }




}
