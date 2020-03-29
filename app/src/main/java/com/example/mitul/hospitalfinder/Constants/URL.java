package com.example.mitul.hospitalfinder.Constants;

/**
 * Created by Denish on 30-10-2018.
 */

public class URL {
    public static String IpAdress = "192.168.0.7";
    //wifi=192.168.0.6 mobile= 172.20.10.2
    public static String LOGIN_API_URL = "http://"+ IpAdress +"/hospitalfinder/login.php";
    public static String SIGNUP_COMMON_URL = "http://" + IpAdress + "/hospitalfinder/Signup_common.php";
    public static String SIGNUP_PATIENT_URL = "http://" + IpAdress + "/hospitalfinder/signup_patient.php";
    public static String SIGNUP_HOSPITAL_URL = "http://" + IpAdress + "/hospitalfinder/signup_hospital.php";
    public static String SIGNUP_HOSPITAL_INSERTDOCTERDETAILS_URL = "http://" + IpAdress + "/hospitalfinder/insert_docter_details.php";
    public static String SIGNUP_ADMIN_FIRSTPAGE_LIST = "http://" + IpAdress + "/hospitalfinder/Adminside_hospitallist_firstpage.php";
    public static String SIGNUP_ADMIN_SUCESS_LIST = "http://" + IpAdress + "/hospitalfinder/Adminside_hospital_verifiedlist.php";
    public static String ADMIN_VERIDED_HOSPITAL = "http://" + IpAdress + "/hospitalfinder/Adimin_verified.php";
    public static String ADMIN_SEP_HOSPITAL_DETAILS = "http://" + IpAdress + "/hospitalfinder/Adminside_hospitallist_secondpage.php";
    public static String ADMIN_DOCTER_LIST = "http://" + IpAdress + "/hospitalfinder/docterdetails.php";

    public static String FORGETPASSWORD = "http://" + IpAdress + "/hospitalfinder/email_checker.php";
    public static String FORGETPASSWORD1 = "http://" + IpAdress + "/hospitalfinder/forgetpassword.php";
    public static String FORGETPASSWORD2 = "http://" + IpAdress + "/hospitalfinder/updatepassword.php";
    public static String DELTE_LOGIN_ENTRY = "http://" + IpAdress + "/hospitalfinder/delete_login_entry.php";
    public static String DELETE_HOSPITAL_DETAIS="http://" + IpAdress + "/hospitalfinder/delete_hospital_entry.php";
    public static String GET_STATE_DETAILS="http://" + IpAdress + "/hospitalfinder/give_state.php";
    public static String GET_STATE_ALL_DETAILS="http://" + IpAdress + "/hospitalfinder/state_city_pincode.php";
    public static String GET_CITY_DETAILS="http://" + IpAdress + "/hospitalfinder/give_city.php";
    public static String GET_PINCODE_DETAILS="http://" + IpAdress + "/hospitalfinder/give_pincode.php";

    public static String PATIENT_UPDATE_DETAILS = "http://" + IpAdress + "/hospitalfinder/Patient_Edit_profile.php";

    public static String PATIENT_LOGIN_DETAILS = "http://" + IpAdress + "/hospitalfinder/Patient_login_detail.php";
    public static String PATIENT_SEARCH_HOSPITAL_LIST = "http://" + IpAdress + "/hospitalfinder/Patient_Hospital_Search.php";
    public static String PATIENT_BLOOD_BOOK = "http://" + IpAdress + "/hospitalfinder/Patient_blood_book.php";
    public static String PATIENT_MEDICINE_BOOK = "http://" + IpAdress + "/hospitalfinder/Patient_medicine_book.php";
    public static String PATIENT_SEARCH_HOSPITAL_BLOOD_LIST = "http://" + IpAdress + "/hospitalfinder/Patient_Hospital_serach_Blood.php";
    public static String PATIENT_SEARCH_HOSPITAL_MEDICINE_LIST = "http://" + IpAdress + "/hospitalfinder/Patient_hospital_Search_medicine.php";
    public static String PATIENT_SEARCH_HOSPITAL_BOTH_LIST = "http://" + IpAdress + "/hospitalfinder/Patient_Hospital_Search_Both.php";



    public static String PATIENT_ORDER_BLOOOD_PENDIGNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Blood_Pending_Request.php";
    public static String PATIENT_ORDER_BLOOOD_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Blood_Sucess_Request.php";
    public static String PATIENT_ORDER_BLOOOD_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Blood_Rejected_Request.php";
    public static String PATIENT_ORDER_BLOOOD_CANCALE_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Blood_Order_Cancle_Request.php";
    public static String PATIENT_DOCTER_DETAILS_CATEGORY_WISE = "http://" + IpAdress + "/hospitalfinder/category_wise_docter_details.php";
    public static String PATIENT_ORDER_MEDICINE_PENDIGNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Medicine_Pending_Request.php";
    public static String PATIENT_ORDER_MEDICINE_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Medicine_Sucess_Request.php";
    public static String PATIENT_ORDER_MEDICINE_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Medicine_Rejected_Request.php";
    public static String PATIENT_ORDER_MEDICINE_CANCALE_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Medicine_Order_Cancle_Request.php";
    public static String PATIENT_APPOINTMENT_BOOK_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Appointment_Book.php";
    public static String PATIENT_APPOINTMENT_PENDING_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Appointment_Pending_Request.php";
    public static String PATIENT_ORDER_APPOINTMENT_CANCALE_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Appointment_Cancle_Request.php";
    public static String PATIENT_APPOINTMENT_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Appointment_Sucess_Request.php";
    public static String PATIENT_APPOINTMENT_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Patient_Appointment_Rejected_Request.php";


    public static String HOSPITAL_LOGIN_PAGE1 = "http://" + IpAdress + "/hospitalfinder/Hospital_login_detailslist.php";
    public static String HOSPITAL_UPDATE_MEDICAL_FACILITIES = "http://" + IpAdress + "/hospitalfinder/Update_medical_facilities.php";
    public static String HOSPITAL_GET_MEDICAL_FACILITIES = "http://" + IpAdress + "/hospitalfinder/get_medical_facitlities.php";
    public static String HOSPITAL_UPDATE_ABOUT = "http://" + IpAdress + "/hospitalfinder/Update_About.php";
    public static String HOSPITAL_GET_ABOUT = "http://" + IpAdress + "/hospitalfinder/get_about.php";
    public static String HOSPITAL_UPDATE_BASIC_DETAILS = "http://" + IpAdress + "/hospitalfinder/Update_Hospital_Basic_Details.php";
    public static String HOSPITAL_UPDATE_HOSPITAL_IMAGE = "http://" + IpAdress + "/hospitalfinder/Update_hospital_image.php";
    public static String HOSPITAL_UPDATE_DOCTER_WITHOUT_IMAGE = "http://" + IpAdress + "/hospitalfinder/Update_docter_without_image.php";
    public static String HOSPITAL_UPDATE_DOCTER_WITH_IMAGE = "http://" + IpAdress + "/hospitalfinder/Update_docter_with_image.php";
    public static String HOSPITAL_MEDICAL_DETAILS_LIST = "http://" + IpAdress + "/hospitalfinder/medicine_list.php";
    public static String HOSPITAL_UPDATE_MEDICINE = "http://" + IpAdress + "/hospitalfinder/Update_medicine.php";
    public static String HOSPITAL_CHECK_NEW__MEDICINE = "http://" + IpAdress + "/hospitalfinder/check_available_medicine.php";
    public static String HOSPITAL_INSERT_NEW__MEDICINE = "http://" + IpAdress + "/hospitalfinder/insert_medicine_details.php";
    public static String HOSPITAL_BLOOD_DETAILS_LIST = "http://" + IpAdress + "/hospitalfinder/blood_list.php";
    public static String HOSPITAL_INSERT_NEW__BLOOD = "http://" + IpAdress + "/hospitalfinder/insert_blood_details.php";
    public static String HOSPITAL_CHECK_NEW__BLOOD = "http://" + IpAdress + "/hospitalfinder/check_available_blood.php";
    public static String HOSPITAL_MEDICINE_DESCRIPTION = "http://" + IpAdress + "/hospitalfinder/get_medicine_description.php";
    public static String HOSPITAL_UPDATE_MEDICINE_DESCRIPTION = "http://" + IpAdress + "/hospitalfinder/Update_Medicine_description.php";


    public static String HOSPITAL_UPDATE_BLOOD = "http://" + IpAdress + "/hospitalfinder/Update_blood.php";
    public static String HOSPITAL_SEND_DOCTER_MAIL = "http://" + IpAdress + "/hospitalfinder/Send_email_Password_to_Docter.php";

    public static String HOSPITAL_ORDER_BLOOD_PENIDNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Blood_Penidng_Request.php";
    public static String HOSPITAL_MAKE_CONFIRM_ORDER_BLOOD = "http://" + IpAdress + "/hospitalfinder/Hospital_Blood_make_confirm_request.php";
    public static String HOSPITAL_MAKE_REJECT_ORDER_BLOOD = "http://" + IpAdress + "/hospitalfinder/Hospital_Reject_Order_Detail.php";
    public static String HOSPITAL_ORDER_BLOOD_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Blood_Sucess_Request.php";
    public static String HOSPITAL_MAKE_REJECT_AGAIN_ORDER_BLOOD = "http://" + IpAdress + "/hospitalfinder/Hospital_Blood_make_reject_request.php";
    public static String HOSPITAL_ORDER_BLOOD_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Blood_Rejected_Request.php";

    public static String HOSPITAL_ORDER_MEDICINE_PENIDNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Medicine_Pending_Request.php";
    public static String HOSPITAL_MAKE_CONFIRM_ORDER_MEDICINE = "http://" + IpAdress + "/hospitalfinder/Hospital_medicine_make_confirm_request.php";
    public static String HOSPITAL_MAKE_REJECT_ORDER_MEDICINE = "http://" + IpAdress + "/hospitalfinder/Hospital_Reject_Medicine_Order_Detail.php";
    public static String HOSPITAL_ORDER_MEDICINE_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Medicine_Sucess_Request.php";
    public static String HOSPITAL_MAKE_REJECT_AGAIN_ORDER_MEDICINE = "http://" + IpAdress + "/hospitalfinder/Hospital_Medicine_make_reject_request.php";
    public static String HOSPITAL_ORDER_MEDICINE_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Medicine_Rejected_Request.php";

    public static String HOSPITAL_APPOINTMENT_PENIDNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Appointment_Pending_Request.php";
    public static String HOSPITAL_APPOINTMENT_PENIDNG_SEND_DOCTER_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Appointment_Pending_Send_Docter.php";
    public static String HOSPITAL_APPOINTMENT_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Appointment_Sucess_Request.php";
    public static String HOSPITAL_APPOINTMENT_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Appointment_Reject_Request.php";

    public static String DOCTOR_LOGIN_DETAILS = "http://" + IpAdress + "/hospitalfinder/Doctor_Login_Details.php";
    public static String DOCTOR_APPOINTMENT_PENIDNG_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Docter_Appointment_Pending_Request.php";
    public static String DOCTOR_APPOINTMENT_SUCESS_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Docter_Appointment_Sucess_Request.php";
    public static String DOCTOR_APPOINTMENT_REJECT_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Docter_Appointment_Reject_Request.php";
    public static String DOCTOR_APPOINTMENT_MAKE_CONFIRM_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Docter_Appointment_Make_Confirm_Pending_Request.php";
    public static String DOCTOR_APPOINTMENT_CANCLE_REQUEST = "http://" + IpAdress + "/hospitalfinder/Hospital_Docter_Appointment_Cancle_Pending_Reqeust.php";

    public static String HOSPITAL_MEDICINE_NOTIFICATION = "http://" + IpAdress + "/hospitalfinder/Notification_medicine.php";
    public static String HOSPITAL_MEDICINE_NOTIFICATION_DELETE = "http://" + IpAdress + "/hospitalfinder/notification_medicine_delete.php";
    public static String HOSPITAL_BLOOD_NOTIFICATION = "http://" + IpAdress + "/hospitalfinder/notification_blood.php";
    public static String HOSPITAL_BLOOD_NOTIFICATION_DELETE = "http://" + IpAdress + "/hospitalfinder/notification_blood_delete.php";
    public static String HOSPITAL_APPOINTMENT_NOTIFICATION_DELETE = "http://" + IpAdress + "/hospitalfinder/notification_appointment_update.php";
    public static String HOSPITAL_APPOINTMENT_NOTIFICATION = "http://" + IpAdress + "/hospitalfinder/notification_Hospital_Appointment.php";
    public static String DOCTOR_APPOINTMENT_NOTIFICATION = "http://" + IpAdress + "/hospitalfinder/notification_Doctor_Appointment.php";
    public static String DOCTOR_APPOINTMENT_NOTIFICATION_DELETE = "http://" + IpAdress + "/hospitalfinder/notification_appointment_delete.php";




}