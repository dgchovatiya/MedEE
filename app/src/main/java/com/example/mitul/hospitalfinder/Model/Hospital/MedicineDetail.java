package com.example.mitul.hospitalfinder.Model.Hospital;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Denish on 15-01-2019.
 */

public class MedicineDetail {

    @SerializedName("medicine_id")
    @Expose
    private String medicineId;
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("description")
    @Expose
    private String description;

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
