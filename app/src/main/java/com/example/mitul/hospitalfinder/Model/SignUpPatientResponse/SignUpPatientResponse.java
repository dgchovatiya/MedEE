package com.example.mitul.hospitalfinder.Model.SignUpPatientResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 05-01-2019.
 */

public class SignUpPatientResponse {

    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("MSG")
    @Expose
    private String mSG;

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
}
