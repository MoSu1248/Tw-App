package com.example.truckingwellness;

public class PatientInfo {

    String Name,Surname,Date,Evaluator_Comments,Other_Referrals,Comments;

    public PatientInfo() {

    }

    public PatientInfo(String name, String surname, String date, String evaluator_Comments, String other_Referrals, String comments) {
        Name = name;
        Surname = surname;
        Date = date;
        Evaluator_Comments = evaluator_Comments;
        Other_Referrals = other_Referrals;
        Comments = comments;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getDate() {
        return Date;
    }

    public String getEvaluator_Comments() {
        return Evaluator_Comments;
    }

    public String getOther_Referrals() {
        return Other_Referrals;
    }

    public String getComments() {
        return Comments;
    }

}
