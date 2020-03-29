package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 02-02-2019.
 */

public class Order_Blood {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("Order_Details")
    @Expose
    private List<OrderDetail> orderDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
