package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 19-01-2019.
 */

public class Hospital_blood_details_response {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("BloodDetails")
    @Expose
    private List<BloodDetail> bloodDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<BloodDetail> getBloodDetails() {
        return bloodDetails;
    }

    public void setBloodDetails(List<BloodDetail> bloodDetails) {
        this.bloodDetails = bloodDetails;
    }
}
