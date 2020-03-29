package com.example.mitul.hospitalfinder.Model.State;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 15-03-2019.
 */

public class CityDetail {
    @SerializedName("district_name")
    @Expose
    private String districtName;

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
