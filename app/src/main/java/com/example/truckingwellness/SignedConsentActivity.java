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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kyanogen.signatureview.SignatureView;

public class SignedConsentActivity extends AppCompatActivity {

    Button next,previous , endYes , endNo;
    TextView rapidTestDisplay,homeVisitDisplay,callCenterDisplay,anonDataDisplay,testUnderstood,signedConsent,clientName;

    SignatureView sigView;

    Dialog dialog;


//    ActivityMainBinding mainBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.recent_details_7);

        next = findViewById(R.id.sign_nextbtn);
        previous = findViewById(R.id.sign_prevbtn);
        rapidTestDisplay = findViewById(R.id.textView22);
        homeVisitDisplay = findViewById(R.id.textView23);
        callCenterDisplay = findViewById(R.id.textView24);
        anonDataDisplay = findViewById(R.id.textView25);
        testUnderstood = findViewById(R.id.textView26);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        sigView = findViewById(R.id.signature_view);

        rapidTestDisplay.setText(InformedConsentActivity.rapidTest);
        homeVisitDisplay.setText(InformedConsentActivity.followUpVisit);
        callCenterDisplay.setText(InformedConsent2Activity.followUp);
        anonDataDisplay.setText(InformedConsent2Activity.anonData);
        testUnderstood.setText(InformedConsent2Activity.anonDataConsent);

        sigView.clearCanvas();

        dialog = new Dialog(SignedConsentActivity.this);
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
                Intent intent = new Intent(SignedConsentActivity.this,MainMenuActivity.class);
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

                if (sigView.isBitmapEmpty()){
                    Toast.makeText(SignedConsentActivity.this, "Please Sign The Document", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(SignedConsentActivity.this,CounsellingActivity.class));
                    finish();

                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignedConsentActivity.this,InformedConsent2Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}