package com.example.truckingwellness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class InformedConsentActivity extends AppCompatActivity {

    public static String rapidTest,hivConsent,followUpVisit;

    RadioButton rapidTestYes,rapidTestNo,hivConsentYes,hivConsentNo,followUpVisitYes,followUpVisitNo;
    Button next,previous , endYes , endNo;

    ImageView consentForm;

    Dialog dialog;
    TextView clientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_5);

        rapidTestYes = findViewById(R.id.rapid_test_yes);
        rapidTestNo = findViewById(R.id.rapid_test_no);
        hivConsentYes = findViewById(R.id.consent_yes);
        hivConsentNo = findViewById(R.id.consent_no);
        followUpVisitYes = findViewById(R.id.home_visit_yes);
        followUpVisitNo = findViewById(R.id.home_visit_no);
        next = findViewById(R.id.consent_nextbtn);
        previous = findViewById(R.id.consent_prevbtn);
        consentForm = findViewById(R.id.imageView3);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(InformedConsentActivity.this);
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
                Intent intent = new Intent(InformedConsentActivity.this,MainMenuActivity.class);
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
                if (rapidTestYes.isChecked()){
                    rapidTest = rapidTestYes.getText().toString();
                } else if (rapidTestNo.isChecked()) {
                    rapidTest = rapidTestNo.getText().toString();
                }

                if (hivConsentYes.isChecked()){
                    hivConsent = hivConsentYes.getText().toString();
                } else if (hivConsentNo.isChecked()) {
                    hivConsent = hivConsentNo.getText().toString();
                }

                if (followUpVisitYes.isChecked()){
                    followUpVisit = followUpVisitYes.getText().toString();
                } else if (followUpVisitNo.isChecked()) {
                    followUpVisit = followUpVisitNo.getText().toString();
                }

                if ((hivConsentNo.isChecked())){
                    showconsentnoti();
                }
                else if ((rapidTestYes.isChecked() || rapidTestNo.isChecked()) && ( hivConsentYes.isChecked()) && (followUpVisitYes.isChecked() || followUpVisitNo.isChecked())){
                    startActivity(new Intent(InformedConsentActivity.this,InformedConsent2Activity.class));
                    finish();
                }
                else {
                    Toast.makeText(InformedConsentActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();

                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InformedConsentActivity.this,TestingReasonActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        dialog.show();

    }
    private void showconsentnoti(){



        ConstraintLayout errorConstraintLayout = findViewById(R.id.consent_noti);
        View view = LayoutInflater.from(InformedConsentActivity.this).inflate(R.layout.consent_noti, errorConstraintLayout);

        //Button errorClose = (Button) findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(InformedConsentActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }
}