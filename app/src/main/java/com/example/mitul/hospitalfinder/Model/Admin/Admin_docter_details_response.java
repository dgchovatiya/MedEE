package com.example.mitul.hospitalfinder.Model.Admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 09-01-2019.
 */

public class Admin_docter_details_response {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("DocterDetails")
    @Expose
    private List<DocterDetails> docterDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<DocterDetails> getDocterDetails() {
        return docterDetails;
    }

    public void setDocterDetails(List<DocterDetails> docterDetails) {
        this.docterDetails = docterDetails;
    }
}
