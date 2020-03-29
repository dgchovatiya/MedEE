package com.example.mitul.hospitalfinder.Model.SignUpCommonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 05-01-2019.
 */

public class SignUpCommonResponse {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("MSG")
    @Expose
    private String mSG;
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("email_id")
    @Expose
    private String emailId;

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
