package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TB2Activity extends AppCompatActivity {

    Button next, previous, endNo , endYes;

    RadioButton treatTbYes,treatTbNo,tbMedsYes,tbMedsNo,householdTbYes,householdTbNo;

    EditText evaluatorComments;

    TextView clientName;

    Dialog dialog ;

    public static String treatTb,tbMeds,householdTb,comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_20);

        treatTbYes = findViewById(R.id.told_tb_yes);
        treatTbNo = findViewById(R.id.told_tb_no);
        tbMedsYes = findViewById(R.id.taken_meds_yes);
        tbMedsNo = findViewById(R.id.taken_meds_no);
        householdTbYes = findViewById(R.id.household_yes);
        householdTbNo = findViewById(R.id.household_no);
        evaluatorComments = findViewById(R.id.evaluator_comment);
        next = findViewById(R.id.screening_nextbtn);
        previous = findViewById(R.id.screening_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        evaluatorComments.setText(comments);

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
                Intent intent = new Intent(TB2Activity.this,MainMenuActivity.class);
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
                if (treatTbYes.isChecked()){
                    treatTb = treatTbYes.getText().toString();
                } else if (treatTbNo.isChecked()) {
                    treatTb = treatTbNo.getText().toString();
                }

                if (tbMedsYes.isChecked()){
                    tbMeds = tbMedsYes.getText().toString();
                } else if (tbMedsNo.isChecked()) {
                    tbMeds = tbMedsNo.getText().toString();
                }

                if (householdTbYes.isChecked()){
                    householdTb = householdTbYes.getText().toString();
                } else if (householdTbNo.isChecked()) {
                    householdTb = householdTbNo.getText().toString();
                }

                comments = evaluatorComments.getText().toString();

                if ((householdTbNo.isChecked() || householdTbYes.isChecked()) && (tbMedsYes.isChecked() || tbMedsNo.isChecked()) && (treatTbYes.isChecked() || treatTbNo.isChecked())){
                    startActivity(new Intent(TB2Activity.this,STIActivity.class));
                    finish();
                }else {
                    Toast.makeText(TB2Activity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TB2Activity.this,TBActivity.class));
                finish();
            }
        });
    }
}