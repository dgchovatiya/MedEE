package com.example.mitul.hospitalfinder.Model.LoginActivityResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 05-01-2019.
 */

public class UserDetails {
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("category")
    @Expose
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
