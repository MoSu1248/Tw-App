package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class WellnessActivity extends AppCompatActivity {

    public static String smoke,numCiggs,quitSmoke,exercise,increaseExercise;

    RadioButton doSomkeYes,doSmokeNo,quitCiggsyes,quitCiggsNo,exerciseYes,exerciseNo,increaseYes,increaseNo;

    Button next,previous , endYes , endNo;

    Spinner numCiggsSpin;

    Dialog dialog;

    TextView clientName , smoke_text;

    RadioGroup smoke_group;

    MaterialCardView smokeNoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_14);

        smokeNoContainer = findViewById(R.id.materialCardView1);
        doSomkeYes = findViewById(R.id.smoking_yes);
        doSmokeNo = findViewById(R.id.smoking_no);
        quitCiggsNo = findViewById(R.id.quit_no);
        quitCiggsyes = findViewById(R.id.quit_yes);
        exerciseNo = findViewById(R.id.exercise_no);
        exerciseYes = findViewById(R.id.exercise_yes);
        increaseNo = findViewById(R.id.activity_no);
        increaseYes = findViewById(R.id.activity_yes);
        numCiggsSpin = findViewById(R.id.numCiggsSpinner);
        previous = findViewById(R.id.wellness1_prevbtn);
        next = findViewById(R.id.wellness1_nextbtn);
        clientName = findViewById(R.id.client_name);
        smoke_text = findViewById(R.id.textView9);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        smoke_group= findViewById(R.id.radioGroup4);

        doSomkeYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smokeNoContainer.setVisibility(View.VISIBLE);
                smoke_text.setVisibility(View.VISIBLE);
                smoke_group.setVisibility(View.VISIBLE);
            }
        });


        doSmokeNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smokeNoContainer.setVisibility(View.INVISIBLE);
                smoke_text.setVisibility(View.INVISIBLE);
                smoke_group.setVisibility(View.INVISIBLE);
                numCiggsSpin.setSelection(0);

            }
        });

        dialog = new Dialog(WellnessActivity.this);
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
                Intent intent = new Intent(WellnessActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(WellnessActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.numCiggs));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numCiggsSpin.setAdapter(myAdpater);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (doSomkeYes.isChecked()){
                    smoke = doSomkeYes.getText().toString();
                } else if (doSmokeNo.isChecked()) {
                    smoke = doSmokeNo.getText().toString();
                }

                if (quitCiggsyes.isChecked()){
                    quitSmoke = quitCiggsyes.getText().toString();
                } else if (quitCiggsNo.isChecked()) {
                    quitSmoke = quitCiggsNo.getText().toString();
                }

                if (exerciseYes.isChecked()){
                    exercise = exerciseYes.getText().toString();
                } else if (exerciseNo.isChecked()) {
                    exercise = exerciseNo.getText().toString();
                }

                if (increaseYes.isChecked()){
                    increaseExercise = increaseYes.getText().toString();
                } else if (increaseNo.isChecked()) {
                    increaseExercise = increaseNo.getText().toString();
                }
                numCiggs = numCiggsSpin.getSelectedItem().toString();

//                if(doSomkeYes.isChecked() && numCiggsSpin.getSelectedItemPosition() != 0 && (quitCiggsyes.isChecked() || quitCiggsNo.isChecked())){
//                    if((increaseNo.isChecked() || increaseYes.isChecked()) && (exerciseYes.isChecked() || exerciseNo.isChecked())){
//                        startActivity(new Intent(WellnessActivity.this,Wellness2Activity.class));
//                        finish();
//                    }
//                }

                if ((increaseNo.isChecked() || increaseYes.isChecked()) && (exerciseYes.isChecked() || exerciseNo.isChecked()) && (doSomkeYes.isChecked() || doSmokeNo.isChecked() )){
                    if (doSomkeYes.isChecked() && numCiggsSpin.getSelectedItemPosition() != 0 && (quitCiggsNo.isChecked() || quitCiggsyes.isChecked()) ){
                        startActivity(new Intent(WellnessActivity.this,Wellness2Activity.class));
                        finish();
                    }
                    else if (doSmokeNo.isChecked()){
                        startActivity(new Intent(WellnessActivity.this,Wellness2Activity.class));
                        finish();
                    }
                    else
                    {
                            Toast.makeText(WellnessActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    }
                }
//                else {
//                    Toast.makeText(WellnessActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WellnessActivity.this,CovidActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        dialog.show();

    }

}