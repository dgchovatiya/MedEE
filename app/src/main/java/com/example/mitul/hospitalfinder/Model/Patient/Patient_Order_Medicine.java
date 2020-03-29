package com.example.mitul.hospitalfinder.Model.Patient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 08-02-2019.
 */

public class Patient_Order_Medicine {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("Patient_MOrder_Details")
    @Expose
    private List<PatientMOrderDetail> patientMOrderDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<PatientMOrderDetail> getPatientMOrderDetails() {
        return patientMOrderDetails;
    }

    public void setPatientMOrderDetails(List<PatientMOrderDetail> patientMOrderDetails) {
        this.patientMOrderDetails = patientMOrderDetails;
    }
}
