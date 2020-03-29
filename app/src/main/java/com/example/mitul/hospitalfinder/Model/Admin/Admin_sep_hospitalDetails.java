package com.example.mitul.hospitalfinder.Model.Admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 08-01-2019.
 */

public class Admin_sep_hospitalDetails {


    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("hospitaldetails")
    @Expose
    private List<Hospitaldetails> hospitaldetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<Hospitaldetails> getHospitaldetails() {
        return hospitaldetails;
    }

    public void setHospitaldetails(List<Hospitaldetails> hospitaldetails) {
        this.hospitaldetails = hospitaldetails;
    }
}
