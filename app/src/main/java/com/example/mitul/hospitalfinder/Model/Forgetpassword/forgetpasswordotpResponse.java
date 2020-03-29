package com.example.mitul.hospitalfinder.Model.Forgetpassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 10-01-2019.
 */

public class forgetpasswordotpResponse {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("emial_status")
    @Expose
    private String emialStatus;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getEmialStatus() {
        return emialStatus;
    }

    public void setEmialStatus(String emialStatus) {
        this.emialStatus = emialStatus;
    }
}
