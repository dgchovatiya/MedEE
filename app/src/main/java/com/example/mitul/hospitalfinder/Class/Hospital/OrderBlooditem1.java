package com.example.mitul.hospitalfinder.Class.Hospital;

/**
 * Created by Denish on 02-02-2019.
 */

public class OrderBlooditem1 {
    String order_id;
    String blood_id;
String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    String Patient_id;
    String Patientname;
    String patientdob;
    String patientmob;
    String bloodgroup;
    String bookingdate;
    String quantity;
    public String getBlood_id() {
        return blood_id;
    }

    public void setBlood_id(String blood_id) {
        this.blood_id = blood_id;
    }
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public String getBloodstock() {
        return bloodstock;
    }

    public void setBloodstock(String bloodstock) {
        this.bloodstock = bloodstock;
    }

    String bloodstock;
}
