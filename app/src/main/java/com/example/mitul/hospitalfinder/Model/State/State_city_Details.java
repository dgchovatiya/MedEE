package com.example.mitul.hospitalfinder.Model.State;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 15-03-2019.
 */

public class State_city_Details {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("stateDetails")
    @Expose
    private List<CityDetail> stateDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<CityDetail> getStateDetails() {
        return stateDetails;
    }

    public void setStateDetails(List<CityDetail> stateDetails) {
        this.stateDetails = stateDetails;
    }
}
