package com.example.mitul.hospitalfinder.Model.Patient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 07-02-2019.
 */

public class Patient_Order_Blood {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("Patient_Order_Details")
    @Expose
    private List<PatientOrderDetail> patientOrderDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<PatientOrderDetail> getPatientOrderDetails() {
        return patientOrderDetails;
    }

    public void setPatientOrderDetails(List<PatientOrderDetail> patientOrderDetails) {
        this.patientOrderDetails = patientOrderDetails;
    }
}
