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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReferralsActivity extends AppCompatActivity {

    RadioButton eilsaYes,eilsaNo,preArtYes,preArtNo,artYes,artNo,pmtctYes,pmtctNo,genderViloanceYes,genderViloanceNo,stiTestYes,stiTestNo,tbTestYes,tbTestNo,circumcisionYes,circumcisionNo;

    RadioGroup vmmcVisable;
    Button next, previous , endYes , endNo;

    TextView clientName;

    Dialog dialog;


    public static String eilsa,preArt,art,pmtct,genderViolence,stiTest,tbTest,circumcision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_22);

        eilsaYes = findViewById(R.id.elisa_yes);
        eilsaNo = findViewById(R.id.elisa_no);
        preArtYes = findViewById(R.id.pre_art_yes);
        preArtNo = findViewById(R.id.pre_art_no);
        artYes = findViewById(R.id.art_yes);
        artNo = findViewById(R.id.art_no);
        pmtctYes = findViewById(R.id.pmtct_yes);
        pmtctNo = findViewById(R.id.pmtct_no);
        genderViloanceYes = findViewById(R.id.gender_violence_yes);
        genderViloanceNo = findViewById(R.id.gender_violence_no);
        stiTestYes = findViewById(R.id.sti_yes);
        stiTestNo = findViewById(R.id.sti_no);
        tbTestYes = findViewById(R.id.tb_test_yes);
        tbTestNo = findViewById(R.id.tb_test_no);
        circumcisionYes = findViewById(R.id.vmmc_yes);
        circumcisionNo = findViewById(R.id.vmmc_no);
        next = findViewById(R.id.referral1_nextbtn);
        previous = findViewById(R.id.referral1_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        vmmcVisable= findViewById(R.id.radioGroup18);

        dialog = new Dialog(ReferralsActivity.this);
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
                Intent intent = new Intent(ReferralsActivity.this,MainMenuActivity.class);
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
                if (eilsaYes.isChecked()){
                    eilsa = eilsaYes.getText().toString();
                } else if (eilsaNo.isChecked()) {
                    eilsa = eilsaNo.getText().toString();
                }

                if (preArtYes.isChecked()){
                    preArt = preArtYes.getText().toString();
                } else if (preArtNo.isChecked()) {
                    preArt = preArtNo.getText().toString();
                }

                if (artYes.isChecked()){
                    art = artYes.getText().toString();
                } else if (artNo.isChecked()) {
                    art = artNo.getText().toString();
                }

                if (pmtctYes.isChecked()){
                    pmtct = pmtctYes.getText().toString();
                } else if (pmtctNo.isChecked()) {
                    pmtct = pmtctNo.getText().toString();
                }

                if (genderViloanceYes.isChecked()){
                    genderViolence = genderViloanceYes.getText().toString();
                } else if (genderViloanceNo.isChecked()) {
                    genderViolence = genderViloanceNo.getText().toString();
                }

                if (stiTestYes.isChecked()){
                    stiTest = stiTestYes.getText().toString();
                } else if (stiTestNo.isChecked()) {
                    stiTest = stiTestNo.getText().toString();
                }

                if (tbTestYes.isChecked()){
                    tbTest = tbTestYes.getText().toString();
                } else if (tbTestNo.isChecked()) {
                    tbTest = tbTestNo.getText().toString();
                }

                if (circumcisionYes.isChecked()){
                    circumcision = circumcisionYes.getText().toString();
                } else if (circumcisionNo.isChecked()) {
                    circumcision = circumcisionNo.getText().toString();
                }

                if ((circumcisionYes.isChecked() || circumcisionNo.isChecked()) && (tbTestYes.isChecked() || tbTestNo.isChecked()) && (stiTestYes.isChecked() || stiTestNo.isChecked())
                        && (genderViloanceYes.isChecked() || genderViloanceNo.isChecked())){
                    startActivity(new Intent(ReferralsActivity.this,Referrals2Activity.class));
                    finish();
                }else {
                    Toast.makeText(ReferralsActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReferralsActivity.this,STIActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}