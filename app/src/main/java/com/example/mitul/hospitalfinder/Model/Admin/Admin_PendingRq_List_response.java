package com.example.mitul.hospitalfinder.Model.Admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 07-01-2019.
 */

public class Admin_PendingRq_List_response {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("hospitaldetails")
    @Expose
    private List<Hospitaldetail> hospitaldetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<Hospitaldetail> getHospitaldetails() {
        return hospitaldetails;
    }

    public void setHospitaldetails(List<Hospitaldetail> hospitaldetails) {
        this.hospitaldetails = hospitaldetails;

    }
}
