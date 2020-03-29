package com.example.mitul.hospitalfinder.Class.Patient;

/**
 * Created by Denish on 08-02-2019.
 */

public class PatientOrderMedicineitem {
    String order_id;
    String medicine_id;
    String Patient_id;
    String hospital_id;
    String hospital_name;
    String hospitalmob;
    String medcine_name;
    String bookingdate;
    String quantity;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
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

    public String getMedcine_name() {
        return medcine_name;
    }

    public void setMedcine_name(String medcine_name) {
        this.medcine_name = medcine_name;
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

    public String getMedicine_stock() {
        return medicine_stock;
    }

    public void setMedicine_stock(String medicine_stock) {
        this.medicine_stock = medicine_stock;
    }

    String medicine_stock;
}
