package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 19-01-2019.
 */

public class BloodDetail {
    @SerializedName("blood_id")
    @Expose
    private String bloodId;
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("stock")
    @Expose
    private String stock;

    public String getBloodId() {
        return bloodId;
    }

    public void setBloodId(String bloodId) {
        this.bloodId = bloodId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
