package com.example.mitul.hospitalfinder.Model.SignUpHospitalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 06-01-2019.
 */

public class SignUpHospitalinsertDocterDetails {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("MSG")
    @Expose
    private String mSG;
    @SerializedName("docter_id")
    @Expose
    private String docterId;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getMSG() {
        return mSG;
    }

    public void setMSG(String mSG) {
        this.mSG = mSG;
    }

    public String getDocterId() {
        return docterId;
    }

    public void setDocterId(String docterId) {
        this.docterId = docterId;
    }
}
