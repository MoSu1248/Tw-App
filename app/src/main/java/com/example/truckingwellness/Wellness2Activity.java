package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness2Activity extends AppCompatActivity {

    public static  String bingeDrink,tooMuchDrink,highlyStressed,managingStress,sleepProblems;

    RadioButton bingeDrinkYes,bingeDrinkNo,tooMuchDrinkYes,tooMuchDrinkNo,highlyStressedYes,highlyStressedNo,managingStressYes,managingStressNo,sleepProblemsYes,sleepProblemsNo;

    Button next,previous , endNo , endYes;

    Dialog dialog;

    TextView clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.recent_details_15);

//        bingeDrinkYes = findViewById(R.id.binge_yes);
//        tooMuchDrinkYes = findViewById(R.id.too_much_yes);
        highlyStressedYes = findViewById(R.id.high_stress_yes);
        managingStressYes = findViewById(R.id.wellness_measurment_yes);
        sleepProblemsYes = findViewById(R.id.finance_manage_yes);
//        bingeDrinkNo = findViewById(R.id.binge_no);
//        tooMuchDrinkNo = findViewById(R.id.too_much_no);
        highlyStressedNo = findViewById(R.id.high_stress_no);
        managingStressNo = findViewById(R.id.wellness_measurment_no);
        sleepProblemsNo = findViewById(R.id.finance_manage_no);
        next = findViewById(R.id.wellness2_nextbtn);
        previous = findViewById(R.id.wellness2_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(Wellness2Activity.this);
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
                Intent intent = new Intent(Wellness2Activity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (bingeDrinkYes.isChecked()){
//                    bingeDrink = bingeDrinkYes.getText().toString();
//                } else if (bingeDrinkNo.isChecked()) {
//                    bingeDrink = bingeDrinkNo.getText().toString();
//                }
//
//                if (tooMuchDrinkYes.isChecked()){
//                    tooMuchDrink = tooMuchDrinkYes.getText().toString();
//                } else if (tooMuchDrinkNo.isChecked()) {
//                    tooMuchDrink = tooMuchDrinkNo.getText().toString();
//                }

                if (highlyStressedYes.isChecked()){
                    highlyStressed = highlyStressedYes.getText().toString();
                } else if (highlyStressedNo.isChecked()) {
                    highlyStressed = highlyStressedNo.getText().toString();
                }

                if (managingStressYes.isChecked()){
                    managingStress = managingStressYes.getText().toString();
                } else if (managingStressNo.isChecked()) {
                    managingStress = managingStressNo.getText().toString();
                }

                if (sleepProblemsYes.isChecked()){
                    sleepProblems = sleepProblemsYes.getText().toString();
                } else if (sleepProblemsNo.isChecked()) {
                    sleepProblems = sleepProblemsYes.getText().toString();
                }


                if ((sleepProblemsNo.isChecked() || sleepProblemsYes.isChecked()) && (highlyStressedNo.isChecked() || highlyStressedYes.isChecked()) &&
                        (managingStressYes.isChecked() || managingStressNo.isChecked())){
                    startActivity(new Intent(Wellness2Activity.this,Wellness3Activity.class));
                    finish();
                } else {
                    Toast.makeText(Wellness2Activity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness2Activity.this,WellnessActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}