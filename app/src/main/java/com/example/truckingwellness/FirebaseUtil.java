package com.example.truckingwellness;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {

    public static CollectionReference allUserCollectionReference(){
        return FirebaseFirestore.getInstance().collection("Clients");

    }

}
