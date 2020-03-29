package com.example.mitul.hospitalfinder.Class.Hospital;

/**
 * Created by Denish on 03-02-2019.
 */

public class OrderMedicineitem {

    String order_medicine_id;
    String medicine_id;
    String medicine_name;
    String bookingdate;
    String quantity;
    String Patient_id;
    String Patientname;
    String patientdob;
    String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOrder_medicine_id() {
        return order_medicine_id;
    }

    public void setOrder_medicine_id(String order_medicine_id) {
        this.order_medicine_id = order_medicine_id;
    }


    String patientmob;



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

    public String getPatientname() {
        return Patientname;
    }

    public void setPatientname(String patientname) {
        Patientname = patientname;
    }

    public String getPatientdob() {
        return patientdob;
    }

    public void setPatientdob(String patientdob) {
        this.patientdob = patientdob;
    }

    public String getPatientmob() {
        return patientmob;
    }

    public void setPatientmob(String patientmob) {
        this.patientmob = patientmob;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
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


}
