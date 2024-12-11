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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FacilityReferralActivity extends AppCompatActivity {

    RadioButton privatebtn,publicbtn,affinitybtn;

    Button next, previous , endNo , endYes ;
    EditText prefFacility;
    TextView clientName;

    Dialog dialog ;

    public static String facility,preferredFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_24);

        privatebtn = findViewById(R.id.private_facility);
        publicbtn = findViewById(R.id.public_referral);
        affinitybtn = findViewById(R.id.affinity_health);
        prefFacility = findViewById(R.id.preferred_facility);
        next = findViewById(R.id.refferal3_nextbtn);
        previous = findViewById(R.id.refferal3_prevbtn);

        prefFacility.setText(preferredFacility);

        dialog = new Dialog(FacilityReferralActivity.this);
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
                Intent intent = new Intent(FacilityReferralActivity.this,MainMenuActivity.class);
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

                if (privatebtn.isChecked()){
                    facility = privatebtn.getText().toString();
                } else if (publicbtn.isChecked()) {
                    facility = publicbtn.getText().toString();
                } else if (affinitybtn.isChecked()) {
                    facility = affinitybtn.getText().toString();
                }

                preferredFacility = prefFacility.getText().toString();

                if (privatebtn.isChecked() || publicbtn.isChecked() || affinitybtn.isChecked()){
                    startActivity(new Intent(FacilityReferralActivity.this,PostTestCounsellingActivity.class));
                    finish();
                }else {
                    Toast.makeText(FacilityReferralActivity.this, "All fields and choices need to be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FacilityReferralActivity.this,Referrals2Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}