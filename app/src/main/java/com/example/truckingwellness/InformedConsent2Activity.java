package com.example.truckingwellness;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class InformedConsent2Activity extends AppCompatActivity {

    public static String followUp,anonData,anonDataConsent;

    RadioButton followUpYes,followUpNo,dataYes,dataNo,dataConsentYes,dataConsentNo;
    Button next,previous , endYes , endNo;
    TextView clientName;

    Dialog dialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_6);

        followUpNo = findViewById(R.id.followup_no);
        followUpYes = findViewById(R.id.followup_yes);
        dataConsentYes = findViewById(R.id.data_consent_yes);
        dataConsentNo = findViewById(R.id.data_consent_no);
        dataYes = findViewById(R.id.data_yes);
        dataNo = findViewById(R.id.data_no);
        next = findViewById(R.id.consent2_nextbtn);
        previous = findViewById(R.id.consent2_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(InformedConsent2Activity.this);
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
                Intent intent = new Intent(InformedConsent2Activity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dataConsentNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showconsentnoti();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 3000);
            }
        });




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (followUpNo.isChecked()){
                    followUp = followUpNo.getText().toString();
                } else if (followUpYes.isChecked()) {
                    followUp = followUpYes.getText().toString();
                }
                if (dataConsentYes.isChecked()){
                    anonData = dataConsentYes.getText().toString();
                } else if (dataConsentNo.isChecked()) {
                    anonData = dataConsentNo.getText().toString();
                }
                if (dataYes.isChecked()){
                    anonDataConsent = dataYes.getText().toString();
                } else if (dataNo.isChecked()) {
                    anonDataConsent = dataNo.getText().toString();
                }

                if (dataConsentNo.isChecked() ){
                    showconsentnoti();
                }
                else if ((followUpYes.isChecked() || followUpNo.isChecked()) && (dataConsentYes.isChecked()) && (dataYes.isChecked()|| dataNo.isChecked())){
                    startActivity(new Intent(InformedConsent2Activity.this,SignedConsentActivity.class));
                    finish();
                } else {
                    Toast.makeText(InformedConsent2Activity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InformedConsent2Activity.this,InformedConsentActivity.class));
                finish();
            }
        });


    }

    private void showconsentnoti(){



        ConstraintLayout errorConstraintLayout = findViewById(R.id.consent_noti);
        View view = LayoutInflater.from(InformedConsent2Activity.this).inflate(R.layout.consent_noti, errorConstraintLayout);

        //Button errorClose = (Button) findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(InformedConsent2Activity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}