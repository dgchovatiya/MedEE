package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denish on 15-01-2019.
 */

public class Hospital_medical_details_response {
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("MedicineDetails")
    @Expose
    private List<MedicineDetail> medicineDetails = null;

    public String getSTATUS() {
        return sTATUS;
    }

    public void setSTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public List<MedicineDetail> getMedicineDetails() {
        return medicineDetails;
    }

    public void setMedicineDetails(List<MedicineDetail> medicineDetails) {
        this.medicineDetails = medicineDetails;
    }

}
