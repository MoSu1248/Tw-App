package com.example.truckingwellness;

public class User {

    String Nurse_Name , Surname , Appointment_Date , Appointment_ID , Appointment_Location , Nurse_Id , Medication_Administered ;


    public User(){}


    public User(String name, String surname) {
        Nurse_Name = name;
        Surname = surname;

    }

//    public String getName() {
//        return Nurse_Name;
//    }
//
//    public void setName(String name) {
//        Nurse_Name = Nurse_Name;
//    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getNurse_Name() {
        return Nurse_Name;
    }

    public void setNurse_Name(String nurse_Name) {
        Nurse_Name = nurse_Name;
    }

    public String getAppointment_Date() {
        return Appointment_Date;
    }

    public void setAppointment_Date(String appointment_Date) {
        Appointment_Date = appointment_Date;
    }

    public String getAppointment_ID() {
        return Appointment_ID;
    }

    public void setAppointment_ID(String appointment_ID) {
        Appointment_ID = appointment_ID;
    }

    public String getAppointment_Location() {
        return Appointment_Location;
    }

    public void setAppointment_Location(String appointment_Location) {
        Appointment_Location = appointment_Location;
    }

    public String getNurse_Id() {
        return Nurse_Id;
    }

    public void setNurse_Id(String nurse_Id) {
        Nurse_Id = nurse_Id;
    }

    public String getMedication_Administered() {
        return Medication_Administered;
    }

    public void setMedication_Administered(String medication_Administered) {
        Medication_Administered = medication_Administered;
    }
}
