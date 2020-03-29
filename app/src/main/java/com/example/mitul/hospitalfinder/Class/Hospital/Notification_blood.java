package com.example.mitul.hospitalfinder.Class.Hospital;

/**
 * Created by Denish on 23-03-2019.
 */

public class Notification_blood {
    public String getB_notification_id() {
        return b_notification_id;
    }

    public void setB_notification_id(String b_notification_id) {
        this.b_notification_id = b_notification_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    String b_notification_id,hospital_id,patient_id;
}
