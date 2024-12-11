package com.example.truckingwellness;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

TextView name, userID, cell , Hiv  , flagged ;

String [] briefNews ;




    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter ;
    FirebaseFirestore db;

    RadioButton flag_yes , flag_no ;



    MaterialTextView flagReason;

    Button endNo , confirmFlag;

    ImageButton back , flag_close , flag_filled_button , flag_btn;

    ProgressDialog progressDialog  ;

    ImageView flag;
    LinearLayout expand;

    UserModel otherUser;

    Dialog dialog , f_reason ;

   public String flagCheck , flag_reason;

    TextView flag_reason_text , test ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter = new MyAdapter(Profile.this,userArrayList);

        dialog = new Dialog(Profile.this);
        dialog.setContentView(R.layout.flag_reason);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        f_reason = new Dialog(Profile.this);
        f_reason.setContentView(R.layout.flag_reason);
        f_reason.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        f_reason.setCancelable(false);

        flag_yes = dialog.findViewById(R.id.flag_user_yes);
        flag_no = dialog.findViewById(R.id.flag_user_no);
        flag_reason_text = dialog.findViewById(R.id.flag_reason);
        test = dialog.findViewById(R.id.textView4);
        flag_close = dialog.findViewById(R.id.dialog_close);
        flag_filled_button = findViewById(R.id.flag);


        flag_yes.isChecked();

        if (flag_yes.isChecked()){
            flagCheck = flag_yes.getText().toString();
            flag_btn.setVisibility(View.INVISIBLE);
        }

        flag_reason = flag_reason_text.getText().toString();

//        endNo = dialog.findViewById(R.id.endNo);
//        flagReason = dialog.findViewById(R.id.flag_reason);

        recyclerView.setAdapter(myAdapter);

        flag_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });




         EventChangeListener();

        name = findViewById(R.id.Name_txt);
        userID = findViewById(R.id.Client_ID);
        cell = findViewById(R.id.cellNumber);
        Hiv = findViewById(R.id.Hiv);
        back = findViewById(R.id.Backbtn);
        flag = findViewById(R.id.flag);
        flag_btn = findViewById(R.id.flag_btn);
        confirmFlag = dialog.findViewById(R.id.confirm_flag);

        name.setText(otherUser.getName());
        userID.setText(otherUser.getclientID());
        cell.setText(otherUser.getcellNumber());
        Hiv.setText(otherUser.getHIV());

        if (otherUser.getFlagged().equals("Yes")){
            flag.setVisibility(View.VISIBLE);
        }else if (otherUser.getFlagged().equals("No") || otherUser.getFlagged().equals("")){
            flag_btn.setVisibility(View.VISIBLE);
        }
        
        confirmFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference washingtonRef = db.collection("Clients").document(otherUser.getclientID());
                washingtonRef
                        .update( "flagReason",flag_reason_text.getText().toString() , "Flagged" ,flag_yes.getText().toString())

                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                test.setText(flag_reason_text.getText().toString());
                                flag_btn.setVisibility(View.INVISIBLE);
                                flag_filled_button.setVisibility(View.VISIBLE);
                                dialog.dismiss();

                            }
                        });

            }
        });


        flag_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,ClientSearch.class);

                startActivity(intent);
            }
        });

    }

    private void EventChangeListener() {
        db.collection("Clients").document(otherUser.getclientID()).collection("Appointments")
                .orderBy("Appointment_Date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                userArrayList.add(dc.getDocument().toObject(User.class));
                            }
                        }
                        myAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                });
    }


}