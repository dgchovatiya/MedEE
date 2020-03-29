package com.example.mitul.hospitalfinder.Class.Hospital;

/**
 * Created by Denish on 23-03-2019.
 */

public class Notification_Appointment {
    String a_notification_id;
    String hospital_id;
    String patient_id;

    public String getA_notification_id() {
        return a_notification_id;
    }

    public void setA_notification_id(String a_notification_id) {
        this.a_notification_id = a_notification_id;
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

    public String getDocter_id() {
        return docter_id;
    }

    public void setDocter_id(String docter_id) {
        this.docter_id = docter_id;
    }

    String docter_id;
}
