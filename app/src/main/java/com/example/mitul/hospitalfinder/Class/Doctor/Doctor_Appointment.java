package com.example.mitul.hospitalfinder.Class.Doctor;

/**
 * Created by Denish on 17-03-2019.
 */

public class Doctor_Appointment {
    String appointment_id;
    String patient_id;
    String hospital_id;
    String docter_id;
    String date;
    String time;
    String status;
    String email_id;
    String patient_name;
    String hosptial_name;
    String city,state;
    String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(String appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getDocter_id() {
        return docter_id;
    }

    public void setDocter_id(String docter_id) {
        this.docter_id = docter_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getHosptial_name() {
        return hosptial_name;
    }

    public void setHosptial_name(String hosptial_name) {
        this.hosptial_name = hosptial_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
