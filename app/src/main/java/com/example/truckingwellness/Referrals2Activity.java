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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Referrals2Activity extends AppCompatActivity {

    RadioButton famliyYes,familyNo,socialYes,socialNo,antenatalYes,antenatalNo,ncdYes,ncdNo;

    Button next, previous , endNo , endYes ;

    RadioGroup antenatalVisable;
    Dialog dialog ;

    EditText otherRefferals,comments;

    TextView clientName;

    public static String family,social,antenatal,ncd,otherReferral,comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_23);

        famliyYes = findViewById(R.id.family_planning_yes);
        familyNo = findViewById(R.id.family_planning_no);
        socialYes = findViewById(R.id.social_services_yes);
        socialNo = findViewById(R.id.social_services_no);
        antenatalYes = findViewById(R.id.anten_yes);
        antenatalNo = findViewById(R.id.anten_no);
        ncdYes = findViewById(R.id.ncd_treatment_yes);
        ncdNo = findViewById(R.id.ncd_treatment_no);
        next = findViewById(R.id.referral2_nextbtn);
        previous = findViewById(R.id.referral2_prevbtn);
        otherRefferals = findViewById(R.id.other_refferrals);
        comments = findViewById(R.id.comments);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        antenatalVisable= findViewById(R.id.radioGroup10);
        otherRefferals.setText(otherReferral);
        comments.setText(comment);
//
//        if (CounsellingActivity.antnatal.equals(antenatalYes)){
//            antenatalYes.isChecked();
//        }else if (CounsellingActivity.antnatal.equals(antenatalNo)){
//
//            antenatalNo.isChecked();
//
//        }

        dialog = new Dialog(Referrals2Activity.this);
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
                Intent intent = new Intent(Referrals2Activity.this,MainMenuActivity.class);
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
                if (famliyYes.isChecked()){
                    family = famliyYes.getText().toString();
                } else if (familyNo.isChecked()) {
                    family = familyNo.getText().toString();
                }

                if (socialYes.isChecked()){
                    social = socialYes.getText().toString();
                } else if (socialNo.isChecked()) {
                    social = socialNo.getText().toString();
                }

                if (antenatalYes.isChecked()){
                    antenatal = antenatalYes.getText().toString();
                } else if (antenatalNo.isChecked()) {
                    antenatal = antenatalNo.getText().toString();
                }

                if (ncdYes.isChecked()){
                    ncd = ncdYes.getText().toString();
                } else if (ncdNo.isChecked()) {
                    ncd = ncdNo.getText().toString();
                }

                otherReferral = otherRefferals.getText().toString();
                comment = comments.getText().toString();
                if ((ncdYes.isChecked() || ncdNo.isChecked()) && (antenatalYes.isChecked() || antenatalNo.isChecked()) && (socialYes.isChecked() || socialNo.isChecked()) &&
                        (famliyYes.isChecked() || familyNo.isChecked())){
                    startActivity(new Intent(Referrals2Activity.this,FacilityReferralActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(Referrals2Activity.this, "All fields and choices need to be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Referrals2Activity.this,ReferralsActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}