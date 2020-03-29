package com.example.mitul.hospitalfinder.Model.Admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 09-01-2019.
 */

public class DocterDetails {

    @SerializedName("docter_id")
    @Expose
    private String docterId;
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("docter_image")
    @Expose
    private String docterImage;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("qualification")
    @Expose
    private String qualification;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("category")
    @Expose
    private String category;

    public String getDocterId() {
        return docterId;
    }

    public void setDocterId(String docterId) {
        this.docterId = docterId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
    public String getDocterImage() {
        return docterImage;
    }

    public void setDocterImage(String docterImage) {
        this.docterImage = docterImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
