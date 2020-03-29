package com.example.mitul.hospitalfinder.Class.Hospital;

/**
 * Created by Denish on 19-01-2019.
 */

public class Item_blood {
    String hospital_id;

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(String blood_id) {
        this.blood_id = blood_id;
    }

    String blood_id;
    String blood_group;
    String stock;


    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
