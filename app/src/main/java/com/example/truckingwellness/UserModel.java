package com.example.truckingwellness;

import com.google.firebase.Timestamp;

public class UserModel {
    private String HIV;
    private String Name;
    private Timestamp createdTimestamp;
    private String clientID;
    private String cellNumber;

    private String Flagged , lastAppointment , flagReason;

    public UserModel() {
    }

    boolean visibility ;
    public UserModel(String phone, String Name, Timestamp createdTimestamp, String cellNumber , String clientID, String HIV , String Flagged , String lastAppointment, String flagReason) {
        this.HIV = HIV;
        this.Name = Name;
        this.createdTimestamp = createdTimestamp;
        this.clientID = clientID;
        this.cellNumber = cellNumber;
        this.Flagged = Flagged ;
        this.lastAppointment = lastAppointment ;
        this.flagReason = flagReason;
        this.visibility= false ;
    }

    public String getHIV() {
        return HIV;
    }

    public void setHIV(String HIV) {
        this.HIV = HIV;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
       this.Name = Name;
    }
//
//    public Timestamp getCreatedTimestamp() {
//        return createdTimestamp;
//    }
//
//    public void setCreatedTimestamp(Timestamp createdTimestamp) {
//        this.createdTimestamp = createdTimestamp;
//    }

    public String getclientID() {
        return clientID;
    }

   public void setclientID(String clientID) {
       this.clientID = clientID;
   }

    public String getFlagReason() {
        return flagReason;
    }

    public void setFlagReason(String flagReason) {
        this.flagReason = flagReason;
    }

    public String getcellNumber() {
        return cellNumber;
    }

    public void setcellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getFlagged() {
        return Flagged;
    }

    public void setFlagged(String flagged) {
        Flagged = flagged;
    }

    public String getLastAppointment() {
        return lastAppointment;
    }

    public void setLastAppointment(String lastAppointment) {
        this.lastAppointment = lastAppointment;
    }
}
