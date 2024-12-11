package com.example.truckingwellness;

import android.content.Intent;

public class AndroidUtil {

    public static void passUserModelAsIntent(Intent intent, UserModel model){

        intent.putExtra("Name", model.getName());
        intent.putExtra("ClientID", model.getclientID());
        intent.putExtra("cellNumber", model.getcellNumber());
        intent.putExtra("Hiv", model.getHIV());
        intent.putExtra("flagged" , model.getFlagged());

    }

//    public static void UserModel getUserModelFromIntent(Intent intent){
//        UserModel userModel = new UserModel();
//        userModel.setName(intent.getStringExtra("Name"));
//        userModel.setName(intent.getStringExtra("ClientID"));
//
//        return userModel;
//    }

    public static UserModel getUserModelFromIntent(Intent intent){

        UserModel userModel = new UserModel();
        userModel.setName(intent.getStringExtra("Name"));
        userModel.setclientID(intent.getStringExtra("ClientID"));
        userModel.setcellNumber(intent.getStringExtra("cellNumber"));
        userModel.setHIV(intent.getStringExtra("Hiv"));
        userModel.setFlagged(intent.getStringExtra("flagged"));

        return userModel;
    }

}
