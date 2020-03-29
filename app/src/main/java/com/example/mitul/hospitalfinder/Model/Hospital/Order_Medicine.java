package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 03-02-2019.
 */

public class Order_Medicine {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("Order_Medicine_Details")
    @Expose
    private List<OrderMedicineDetail> orderMedicineDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<OrderMedicineDetail> getOrderMedicineDetails() {
        return orderMedicineDetails;
    }

    public void setOrderMedicineDetails(List<OrderMedicineDetail> orderMedicineDetails) {
        this.orderMedicineDetails = orderMedicineDetails;
    }
}
