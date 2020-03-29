package com.example.mitul.hospitalfinder.Model.State;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 15-03-2019.
 */

public class StateDetail {

    @SerializedName("state_name")
    @Expose
    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
