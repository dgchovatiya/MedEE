package com.example.mitul.hospitalfinder.Activity.Hospital;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.bumptech.glide.Glide;
import com.example.mitul.hospitalfinder.Constants.AppConstants;
import com.example.mitul.hospitalfinder.Constants.URL;
import com.example.mitul.hospitalfinder.Model.Admin.Admin_sep_hospitalDetails;
import com.example.mitul.hospitalfinder.Model.MyApplication;
import com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse.SignUpHospitalinsertDocterDetails;
import com.example.mitul.hospitalfinder.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.mitul.hospitalfinder.Constants.AppConstants.isValidMobile;

public class Hospital_Login1_Docter1_UpdateActivity extends AppCompatActivity {
    EditText et_hs_lg1_d1_up_fname, et_hs_lg1_d1_up_mname, et_hs_lg1_d1_up_lname, et_hs_lg1_d1_up_qualification,
            et_hs_lg1_d1_up_mo;
    Spinner sp_hs_lg1_d1_up_category, sp_hs_lg1_d1_up_qualification;

    ImageView iv_hs_lg1_d1_up_docteriv, hs_lg1_d1_up1_back;
    Button btn_hs_lg1_d1_up_update;
    Context context;

    ArrayList<String> list_d_category, list_d_qualification;
    String hospital_id, docter_id;
    String fname, mname, lname, qualification, mo, docterimage;
    String fname1, mname1, lname1, qualification1, mo1, category1, dprofile, hcategory;
    public int i = 0, j = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital__login1__docter1__update);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.Darkbule));
        }
        initView();
        setListener();
    }


    private void initView() {
        getSupportActionBar().hide();

        context = Hospital_Login1_Docter1_UpdateActivity.this;
        et_hs_lg1_d1_up_fname = (EditText) findViewById(R.id.et_hs_lg1_d1_up_fname);
        et_hs_lg1_d1_up_mname = (EditText) findViewById(R.id.et_hs_lg1_d1_up_mname);
        et_hs_lg1_d1_up_lname = (EditText) findViewById(R.id.et_hs_lg1_d1_up_lname);
        et_hs_lg1_d1_up_mo = (EditText) findViewById(R.id.et_hs_lg1_d1_up_mo);
        //    et_hs_sud_category = (EditText) findViewById(R.id.et_hs_sud_category);
        iv_hs_lg1_d1_up_docteriv = (ImageView) findViewById(R.id.iv_hs_lg1_d1_up_docteriv);
        hs_lg1_d1_up1_back = (ImageView) findViewById(R.id.hs_lg1_d1_up1_back);
        btn_hs_lg1_d1_up_update = (Button) findViewById(R.id.btn_hs_lg1_d1_up_update);
        sp_hs_lg1_d1_up_category = (Spinner) findViewById(R.id.sp_hs_lg1_d1_up_category);
        sp_hs_lg1_d1_up_qualification = (Spinner) findViewById(R.id.sp_hs_lg1_d1_up_qualification);


        hospital_id = getIntent().getStringExtra("hospital_id");
        docter_id = getIntent().getStringExtra("docter_id");

        fname = getIntent().getStringExtra("first_name");
        mname = getIntent().getStringExtra("middle_name");
        lname = getIntent().getStringExtra("last_name");
        qualification = getIntent().getStringExtra("qualification");
        mo = getIntent().getStringExtra("mobile_number");
        docterimage = getIntent().getStringExtra("image");
        Log.e("docter_image", docterimage);
        Glide.with(context).load(docterimage).into(iv_hs_lg1_d1_up_docteriv);

        et_hs_lg1_d1_up_fname.setText(fname);
        et_hs_lg1_d1_up_mname.setText(mname);
        et_hs_lg1_d1_up_lname.setText(lname);
//        et_hs_lg1_d1_up_qualification.setText(qualification);
        et_hs_lg1_d1_up_mo.setText(mo);
        CallHospitalCategoryApi();


        onClickEventOfStateSpinner();


    }

    private void CallHospitalCategoryApi() {
        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();
        requestParams.put("hospital_id", hospital_id);
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.post(context, URL.ADMIN_SEP_HOSPITAL_DETAILS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                AppConstants.dissmissDialog();
                String response = new String(responseBody);
                Log.e("responseDoctercategory", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Admin_sep_hospitalDetails admin_sep_hospitalDetails = gson.fromJson(response, Admin_sep_hospitalDetails.class);
                String status = admin_sep_hospitalDetails.getSTATUS().toString().trim();

                if (status.equals("1")) {
                    for (int i = 0; i < admin_sep_hospitalDetails.getHospitaldetails().size(); i++) {
                        hcategory = admin_sep_hospitalDetails.getHospitaldetails().get(i).getCategory().toString().trim();
                        setList();
                    }


                } else if (status.equals("0")) {
                    AppConstants.openErrorDialog(context, "Hospital details not avialable");

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                AppConstants.dissmissDialog();
                AppConstants.openErrorDialog(context, "some error");
            }
        });

    }


    private void setList() {
        list_d_category = new ArrayList<>();
        if (!hcategory.equals("MultiSpecialist")) {
            list_d_category = new ArrayList<>();

            list_d_category.add("--Doctor Category--");
            if (hcategory.equals("Heart")) {
                list_d_category.add("Cardiologist");

            } else if (hcategory.equals("Orthopedic")) {
                list_d_category.add("Orthopedic");

            } else if (hcategory.equals("Dental")) {
                list_d_category.add("Dental");

            } else if (hcategory.equals("Radiological")) {
                list_d_category.add("Radiologist");

            } else if (hcategory.equals("Skin")) {
                list_d_category.add("Dermatologist");

            } else if (hcategory.equals("Neurological")) {
                list_d_category.add("Neurosergen");

            } else if (hcategory.equals("Urological")) {
                list_d_category.add("Urologist");

            } else if (hcategory.equals("Nephrological")) {
                list_d_category.add("Nephrologist");

            } else if (hcategory.equals("Homeopathic")) {
                list_d_category.add("Homeopathist");

            } else if (hcategory.equals("Genrel Physicalogical")) {
                list_d_category.add("General Physician");

            } else if (hcategory.equals("Ayurvedic")) {
                list_d_category.add("Ayurvedist");

            }
        } else {
            list_d_category.add("--Doctor Category--");
            list_d_category.add("Neurosergen");
            list_d_category.add("Dental");
            list_d_category.add("Cardiologist");
            list_d_category.add("Orthopedic");
            list_d_category.add("Radiologist");
            list_d_category.add("Dermatologist");
            list_d_category.add("Urologist");
            list_d_category.add("Nephrologist");
            list_d_category.add("Homeopathist");
            list_d_category.add("General Physician");
            list_d_category.add("Ayurvedist");

        }

        setAdapter(sp_hs_lg1_d1_up_category, list_d_category);

        list_d_qualification = new ArrayList<>();
      /*  list_d_qualification.add("--Chooose Qualification--");
        list_d_qualification.add("Mbbs");
        list_d_qualification.add("MS");
        list_d_qualification.add("MD");
        setAdapter(sp_hs_lg1_d1_new_qualification, list_d_qualification);*/

    }

    private void onClickEventOfStateSpinner() {
        sp_hs_lg1_d1_up_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String degree = sp_hs_lg1_d1_up_category.getSelectedItem().toString();
                Log.e("degree", degree);
                id = sp_hs_lg1_d1_up_category.getSelectedItemPosition();
                if (id > 0)
                    // Toast.makeText(context, state + "  " + id, Toast.LENGTH_SHORT).show();
                    if (id > 0) {
                        setDegreeList(degree);
                        setAdapter(sp_hs_lg1_d1_up_qualification, list_d_qualification);
                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDegreeList(String degree) {
        switch (degree) {
            case "Neurosergen":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("DM Neuro");
                list_d_qualification.add("M.Tech Neuro");


                break;
            case "Dental":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("DDS");
                list_d_qualification.add("DMS");


                break;
            case "Cardiologist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("DM Cardio");
                list_d_qualification.add("M.ch Cardio");

                break;

            case "Orthopedic":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("MS ortho");

            case "Radiologist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("MD radio");

                break;
            case "Dermatologist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("MD skin");
                list_d_qualification.add("DVD");

                break;
            case "Urologist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("M.ch urology");

                break;

            case "Nephrologist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("DM Nephro");
            case "Homeopathist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("B.H.M.S");

                break;
            case "General Physician":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("M.B.B.S.");
                list_d_qualification.add("MD");


                break;


            case "Ayurvedist":
                list_d_qualification.clear();
                list_d_qualification.add("--Doctor Degree--");
                list_d_qualification.add("BAMS");
                break;

        }

    }

    private void setAdapter(Spinner sp_hs_sud_category, ArrayList<String> list_d_category) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context, R.layout.hs_su1_hoscategory_spinner, R.id.txt_view, list_d_category) {
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
        sp_hs_sud_category.setAdapter(arrayAdapter);
    }


    private void setListener() {
        iv_hs_lg1_d1_up_docteriv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 1);
            }
        });
        btn_hs_lg1_d1_up_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("docter_id", docter_id);
                Log.e("j", j + "");
                Log.e("image", docterimage);

                fname1 = et_hs_lg1_d1_up_fname.getText().toString().trim();
                mname1 = et_hs_lg1_d1_up_mname.getText().toString().trim();
                lname1 = et_hs_lg1_d1_up_lname.getText().toString().trim();
                mo1 = et_hs_lg1_d1_up_mo.getText().toString().trim();
                category1 = sp_hs_lg1_d1_up_category.getSelectedItem().toString().trim();

                if (fname.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter First name first...");
                    et_hs_lg1_d1_up_fname.requestFocus();


                } else if (mname.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Middle name first...");
                    et_hs_lg1_d1_up_mname.requestFocus();


                } else if (lname.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter last name first...");
                    et_hs_lg1_d1_up_lname.requestFocus();


                } else if (mo1.length() == 0) {
                    AppConstants.openErrorDialog(context, "Please enter Mobile no first...");
                    et_hs_lg1_d1_up_mo.requestFocus();


                } else {
                    if (category1.equals("--Doctor Category--")) {
                        AppConstants.openErrorDialog(context, "Please choose category first...");

                    } else {
                        qualification1 = sp_hs_lg1_d1_up_qualification.getSelectedItem().toString().trim();
                        if (qualification1.equals("--Doctor Degree--")) {

                            AppConstants.openErrorDialog(context, "Please choose Qualification first...");

                        } else {

                            if (isValidMobile(mo1) == true) {

                                if (j != 0) {
                                    CallSignupHospitalUpdateDocterDetailsApiwithImage();
                                } else if (j == 0) {
                                    CallSignupHospitalUpdateDocterDetailswithoutApi();
                                }

                            } else {
                                AppConstants.openErrorDialog(context, "Please enter valid mobile number ...");
                                et_hs_lg1_d1_up_mo.requestFocus();
                            }
                        }
                    }

                }
            }

        });

        hs_lg1_d1_up1_back.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void CallSignupHospitalUpdateDocterDetailswithoutApi() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_DOCTER_WITHOUT_IMAGE, new Response.Listener<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_ins_docter_Ds", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                SignUpHospitalinsertDocterDetails signUpHospitalinsertDocterDetails = gson.fromJson(response, SignUpHospitalinsertDocterDetails.class);
                String status = signUpHospitalinsertDocterDetails.getSTATUS().toString().trim();
                String msg = signUpHospitalinsertDocterDetails.getMSG().toString().trim();

                if (status.equals("1")) {
                    //AppConstants.openErrorDialog1(context, "Updated");
                    //finish();
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
                    AppConstants.openErrorDialog(context, "Some error..");


                } else if (status.equals("00")) {
                    AppConstants.openErrorDialog(context, "Field is not set");
                } else if (status.equals("000")) {
                    AppConstants.openErrorDialog(context, "Some Error");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.openErrorDialog(context, "Some error");
            }
        });


        smr.addStringParam("docter_id", docter_id);
        smr.addStringParam("first_name", fname1);
        smr.addStringParam("middle_name", mname1);
        smr.addStringParam("last_name", lname1);
        smr.addStringParam("qualification", qualification1);
        smr.addStringParam("mobile_number", mo1);
        smr.addStringParam("category", category1);

        MyApplication.getInstance().addToRequestQueue(smr);

    }

    private void CallSignupHospitalUpdateDocterDetailsApiwithImage() {

        AppConstants.showDialog(context);
        RequestParams requestParams = new RequestParams();


        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, URL.HOSPITAL_UPDATE_DOCTER_WITH_IMAGE, new Response.Listener<String>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(String response) {

                AppConstants.dissmissDialog();
                Log.e("Res hos_ins_docter_Ds", response);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                SignUpHospitalinsertDocterDetails signUpHospitalinsertDocterDetails = gson.fromJson(response, SignUpHospitalinsertDocterDetails.class);
                String status = signUpHospitalinsertDocterDetails.getSTATUS().toString().trim();
                String msg = signUpHospitalinsertDocterDetails.getMSG().toString().trim();

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
                    AppConstants.openErrorDialog(context, "Some error..");


                } else if (status.equals("00")) {
                    AppConstants.openErrorDialog(context, "Field is not set");
                } else if (status.equals("000")) {
                    AppConstants.openErrorDialog(context, "Some Error");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppConstants.openErrorDialog(context, "Some error");
            }
        });


        smr.addStringParam("docter_id", docter_id);
        smr.addStringParam("first_name", fname1);
        smr.addStringParam("middle_name", mname1);
        smr.addStringParam("last_name", lname1);
        smr.addStringParam("qualification", qualification1);
        smr.addStringParam("mobile_number", mo1);
        smr.addStringParam("category", category1);
        smr.addFile("docter_image", dprofile);

        MyApplication.getInstance().addToRequestQueue(smr);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case 1:
                    Uri imageuri = data.getData();

                    dprofile = getPath(imageuri);
                    if (dprofile.equals(null)) {
                        j = 0;
                    } else {
                        j = j + 1;
                        Log.e("dimageuri", imageuri.toString());
                        Log.e("dprofile", dprofile);

                        iv_hs_lg1_d1_up_docteriv.setImageURI(imageuri);
                    }
                    break;
            }
        }
    }

    private String getPath(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        android.content.CursorLoader loader = new android.content.CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
