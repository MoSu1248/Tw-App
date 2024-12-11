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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class STIActivity extends AppCompatActivity {

    RadioButton stiYes,stiNo,urineyes,urineNo,dischargeYes,dischargeNo,itchingyes,itchingNo,unprotectedYes,unprotectedNo;

    Button next, previous , endNo , endYes;

    TextView clientName;

    RelativeLayout sti_display;

    Dialog dialog;

    public static String sti,urine,discharge,itching,unprotected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_21);

        stiYes = findViewById(R.id.sti_treatment_yes);
        stiNo = findViewById(R.id.sti_treatment_no);
        sti_display = findViewById(R.id.display_sti);
        urineyes = findViewById(R.id.pain_urine_yes);
        urineNo = findViewById(R.id.pain_urine_no);
        dischargeYes = findViewById(R.id.discahrge_odor_no);
        dischargeNo = findViewById(R.id.discahrge_odor_no);
        itchingyes = findViewById(R.id.sore_spots_yes);
        itchingNo = findViewById(R.id.sore_spots_yes);
        unprotectedYes = findViewById(R.id.gender_violence_yes);
        unprotectedNo = findViewById(R.id.gender_violence_no);
        next = findViewById(R.id.screening2_nextbtn);
        previous = findViewById(R.id.screening2_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(STIActivity.this);
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
                Intent intent = new Intent(STIActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        stiNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    sti_display.setVisibility(View.VISIBLE);
            }
        });
        stiYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sti_display.setVisibility(View.INVISIBLE);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (stiYes.isChecked()){
                    sti = stiYes.getText().toString();
                } else if (stiNo.isChecked()) {
                    sti = stiNo.getText().toString();
                }

                if (urineyes.isChecked()){
                    urine = urineyes.getText().toString();
                } else if (urineNo.isChecked()) {
                    urine = urineNo.getText().toString();
                }

                if (dischargeYes.isChecked()){
                    discharge = dischargeYes.getText().toString();
                } else if (dischargeNo.isChecked()) {
                    discharge = dischargeNo.getText().toString();
                }

                if (itchingyes.isChecked()){
                    itching = itchingyes.getText().toString();
                } else if (itchingNo.isChecked()) {
                    itching = itchingNo.getText().toString();
                }

                if (unprotectedYes.isChecked()){
                    unprotected = unprotectedYes.getText().toString();
                } else if (unprotectedNo.isChecked()) {
                    unprotected = unprotectedNo.getText().toString();
                }


                if (stiYes.isChecked() || stiNo.isChecked()) {
                    startActivity(new Intent(STIActivity.this, ReferralsActivity.class));
                    finish();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(STIActivity.this,TBActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}