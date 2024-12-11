package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DemographicsActivity extends AppCompatActivity {

    EditText memberNum ;

    TextInputLayout memberContainer;

    Spinner keyPopSpin,relationshipSpin,ethnicitySpin;

    Button next,previous , endYes , endNo;
    RadioButton industryYes,industryNo;

    TextView clientName;

    Dialog dialog ;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public static String membershipNum,keyPop,relationship,nationality,ethnicity,industryMembership;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_3);

        memberNum = findViewById(R.id.member_number);
        keyPopSpin = findViewById(R.id.keyPopType);
        relationshipSpin = findViewById(R.id.relationship);
        ethnicitySpin = findViewById(R.id.ethnicity);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        memberContainer= findViewById(R.id.industrynum_container);

        previous = findViewById(R.id.demo_prevbtn);
        next = findViewById(R.id.demo_nextbtn);
        industryYes = findViewById(R.id.member_yes);
        industryNo = findViewById(R.id.member_no);

        dialog = new Dialog(DemographicsActivity.this);
        dialog.setContentView(R.layout.exit_noti);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        endNo = dialog.findViewById(R.id.endNo);
        endYes = dialog.findViewById(R.id.endYes);

        endYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClientDetailsActivity.name = "";
                ClientDetailsActivity.surname = "";
                ClientDetailsActivity.companyName = "";
                TestTypeActivity.idType = "";
                TestTypeActivity.idNo = "";
                ClientDetailsActivity.cellNum = "";
                ClientDetailsActivity.optionalNum = "";
                DemographicsActivity.membershipNum = "";
                TestingReasonActivity.testReason = "";
                TestingReasonActivity.otherReasonTest = "";
                TestKitDetailsActivity.testKitNum = "";
                ConfirmationTestActivity.confirmTestKitNum = "";
                CovidActivity.covidNum = "";
                CovidActivity.notVaccinatedReason = "";
                Wellness3Activity.otherIllness = "";
                Wellness4Activity.hr = "";
                Wellness4Activity.bdUpper = "";
                Wellness4Activity.bpLower = "";
                Wellness5Activity.sugar = Double.parseDouble(String.valueOf(0));
                Wellness5Activity.cholesterol = Double.parseDouble(String.valueOf(0));
                Wellness5Activity.waist = Double.parseDouble(String.valueOf(0));
                Wellness5Activity.weight= Double.parseDouble(String.valueOf(0));
                Wellness5Activity.height = Double.parseDouble(String.valueOf(0));
                Referrals2Activity.otherReferral = "";
                Referrals2Activity.comment = "";
                FacilityReferralActivity.preferredFacility = "";
                PostTestCounsellingActivity.condomsMale = "0";
                PostTestCounsellingActivity.condomsFemale = "0";
                PostTestCounsellingActivity.lube = "";
                Intent intent = new Intent(DemographicsActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        industryYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberContainer.setVisibility(View.VISIBLE);
            }
        });

        industryNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberContainer.setVisibility(View.INVISIBLE);
                memberNum.setText("");

            }
        });

        SharedPreferences UserID = getApplication().getSharedPreferences("client_id", Context.MODE_PRIVATE);
        String Id_details = UserID.getString("client_id", "");

        db.collection("Clients").document(Id_details)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            if (documentSnapshot.getString("industry_Member").equals("Yes")) {
                                memberContainer.setVisibility(View.VISIBLE);
                                industryYes.setChecked(true);
                                memberNum.setText(documentSnapshot.getString("industryNumber"));
                            }

                        }
                    }
                });
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override public void onFailure(@NonNull Exception e) {
//
//                        editor.putString("flagged_info","No");
//                        editor2.putString("flagged_info_reason", "");
//                        editor.commit();
//                        editor2.commit();
//                    }
//                });


        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(DemographicsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.keyPop));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keyPopSpin.setAdapter(myAdpater);
        ArrayAdapter<String> myAdpater2 = new ArrayAdapter<String>(DemographicsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.relationship));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relationshipSpin.setAdapter(myAdpater2);
        ArrayAdapter<String> myAdpater3 = new ArrayAdapter<String>(DemographicsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nationality));
        ArrayAdapter<String> myAdpater4 = new ArrayAdapter<String>(DemographicsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.ethnicity));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ethnicitySpin.setAdapter(myAdpater4);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                membershipNum = memberNum.getText().toString();
                keyPop = keyPopSpin.getSelectedItem().toString();
                relationship = relationshipSpin.getSelectedItem().toString();
                ethnicity = ethnicitySpin.getSelectedItem().toString();
                if (industryNo.isChecked()){
                    industryMembership = industryNo.getText().toString();;
                } else if (industryYes.isChecked()) {
                    industryMembership = industryYes.getText().toString();
                }

                if ((industryNo.isChecked()))
                {
                    if (keyPopSpin.getSelectedItemPosition() != 0 && relationshipSpin.getSelectedItemPosition() != 0  && ethnicitySpin.getSelectedItemPosition() != 0)
                    {
                        startActivity(new Intent(DemographicsActivity.this,TestingReasonActivity.class));
                        finish();
                    } else {
                        Toast.makeText(DemographicsActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    }
                }

                if (industryYes.isChecked()){

                    if (membershipNum.isEmpty()){
                        Toast.makeText(DemographicsActivity.this, "Please fill in the Industry Member number", Toast.LENGTH_SHORT).show();
                    }
                    else  if (keyPopSpin.getSelectedItemPosition() != 0 && relationshipSpin.getSelectedItemPosition() != 0  && ethnicitySpin.getSelectedItemPosition() != 0)
                    {
                        startActivity(new Intent(DemographicsActivity.this,TestingReasonActivity.class));
                        finish();
                    }else {
                        Toast.makeText(DemographicsActivity.this, "Make sure all fields are selected", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(DemographicsActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemographicsActivity.this,ClientDetailsActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}