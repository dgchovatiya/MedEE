package com.example.mitul.hospitalfinder.Model.State;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 15-03-2019.
 */

public class State_All_Details {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("stateDetails")
    @Expose
    private List<StateDetail> stateDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<StateDetail> getStateDetails() {
        return stateDetails;
    }

    public void setStateDetails(List<StateDetail> stateDetails) {
        this.stateDetails = stateDetails;
    }

}
