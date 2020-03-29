package com.example.mitul.hospitalfinder.Class.Patient;

/**
 * Created by Denish on 02-02-2019.
 */

public class PatientOrderBlooditem {
    String order_id;
    String blood_id;
    String Patient_id;
    String hospital_id;
    String hospital_name;
    String hospitalmob;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(String blood_id) {
        this.blood_id = blood_id;
    }

    public String getPatient_id() {
        return Patient_id;
    }

    public void setPatient_id(String patient_id) {
        Patient_id = patient_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospitalmob() {
        return hospitalmob;
    }

    public void setHospitalmob(String hospitalmob) {
        this.hospitalmob = hospitalmob;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(String bookingdate) {
        this.bookingdate = bookingdate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBloodstock() {
        return bloodstock;
    }

    public void setBloodstock(String bloodstock) {
        this.bloodstock = bloodstock;
    }

    String bloodgroup;
    String bookingdate;
    String quantity;
    String bloodstock;
}
