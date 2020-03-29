package com.example.mitul.hospitalfinder.Model.Patient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 20-01-2019.
 */

public class Patient_Login_Response {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("patientdetails")
    @Expose
    private List<Patientdetail> patientdetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<Patientdetail> getPatientdetails() {
        return patientdetails;
    }

    public void setPatientdetails(List<Patientdetail> patientdetails) {
        this.patientdetails = patientdetails;
    }

}
