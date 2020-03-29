package com.example.mitul.hospitalfinder.Model.Admin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 08-01-2019.
 */

public class Hospitaldetails {
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("login_id")
    @Expose
    private String loginId;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("hospital_image")
    @Expose
    private String hospitalImage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("building_no")
    @Expose
    private String buildingNo;
    @SerializedName("colony")
    @Expose
    private String colony;
    @SerializedName("land_mark")
    @Expose
    private String landMark;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("medical_facilities")
    @Expose
    private String medicalFacilities;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("certificate")
    @Expose
    private String certificate;
    @SerializedName("status")
    @Expose
    private String status;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        this.hospitalImage = hospitalImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMedicalFacilities() {
        return medicalFacilities;
    }

    public void setMedicalFacilities(String medicalFacilities) {
        this.medicalFacilities = medicalFacilities;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
