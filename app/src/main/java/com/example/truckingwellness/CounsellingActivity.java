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

public class CounsellingActivity extends AppCompatActivity {

    RadioButton precnancyYes,pregnancyNo,vmmcYes,vmmcNo,hivTestingYes,hivTestingNo,antenatalYes,antenatalNo,referVmmcYes,referVmmcNo;
    Button next,previous , endYes , endNo ;

    TextView clientName;

    Dialog dialog;



    public static String pregnancy,vmmc,hivTest,antnatal,referVmmc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (ClientDetailsActivity.gender.equals("Female")){
            setContentView(R.layout.recent_details_8_f);

            precnancyYes = findViewById(R.id.pregnancy_yes);
            pregnancyNo = findViewById(R.id.pregnancy_no);
            hivTestingYes = findViewById(R.id.hiv_testing_yes);
            hivTestingNo = findViewById(R.id.hiv_testing_no);
            antenatalYes = findViewById(R.id.antenatal_yes);
            antenatalNo = findViewById(R.id.antenatal_no);
            next = findViewById(R.id.counsel_nextbtn);
            previous = findViewById(R.id.counsel_prevbtn);
            clientName = findViewById(R.id.client_name);
            clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);


            dialog = new Dialog(CounsellingActivity.this);
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
                    Intent intent = new Intent(CounsellingActivity.this,MainMenuActivity.class);
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
                    if (precnancyYes.isChecked()){
                        pregnancy = precnancyYes.getText().toString();
                    } else if (pregnancyNo.isChecked()) {
                        pregnancy = pregnancyNo.getText().toString();
                    }

                    if (hivTestingYes.isChecked()){
                        hivTest = hivTestingYes.getText().toString();
                    } else if (hivTestingNo.isChecked()) {
                        hivTest = hivTestingNo.getText().toString();
                    }

                    if (antenatalYes.isChecked()){
                        antnatal = antenatalYes.getText().toString();
                    } else if (antenatalNo.isChecked()) {
                        antnatal = antenatalNo.getText().toString();
                    }

                    if (!(precnancyYes.isChecked() || pregnancyNo.isChecked() || hivTestingYes.isChecked() || hivTestingNo.isChecked() || antenatalYes.isChecked() || antenatalNo.isChecked())){
                        Toast.makeText(CounsellingActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(CounsellingActivity.this,TestKitDetailsActivity.class));
                        finish();
                    }
                }
            });
        }

        else if (ClientDetailsActivity.gender.equals("Other")){
            setContentView(R.layout.recent_details_8_other);

            precnancyYes = findViewById(R.id.pregnancy_yes);
            pregnancyNo = findViewById(R.id.pregnancy_no);
            hivTestingYes = findViewById(R.id.hiv_testing_yes);
            hivTestingNo = findViewById(R.id.hiv_testing_no);
            antenatalYes = findViewById(R.id.antenatal_yes);
            antenatalNo = findViewById(R.id.antenatal_no);
            next = findViewById(R.id.counsel_nextbtn);
            previous = findViewById(R.id.counsel_prevbtn);
            clientName = findViewById(R.id.client_name);
            clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
            referVmmcYes = findViewById(R.id.refer_vmmc_yes);
            referVmmcNo = findViewById(R.id.refer_vmmc_no);
            vmmcYes = findViewById(R.id.vmmc_yes);
            vmmcNo = findViewById(R.id.vmmc_no);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (precnancyYes.isChecked()){
                        pregnancy = precnancyYes.getText().toString();
                    } else if (pregnancyNo.isChecked()) {
                        pregnancy = pregnancyNo.getText().toString();
                    }
                    if (hivTestingYes.isChecked()){
                        hivTest = hivTestingYes.getText().toString();
                    } else if (hivTestingNo.isChecked()) {
                        hivTest = hivTestingNo.getText().toString();
                    }
                    if (antenatalYes.isChecked()){
                        antnatal = antenatalYes.getText().toString();
                    } else if (antenatalNo.isChecked()) {
                        antnatal = antenatalNo.getText().toString();
                    }
                    if (vmmcYes.isChecked()){
                        vmmc = vmmcYes.getText().toString();
                    } else if (vmmcNo.isChecked())
                    {
                        vmmc = vmmcNo.getText().toString();
                    }  if (referVmmcYes.isChecked()){
                        referVmmc = referVmmcYes.getText().toString();
                    } else if (referVmmcNo.isChecked())
                    {
                        referVmmc = referVmmcNo.getText().toString();
                    }

                    if (!(precnancyYes.isChecked() || pregnancyNo.isChecked() || hivTestingYes.isChecked() || hivTestingNo.isChecked() || antenatalYes.isChecked() || antenatalNo.isChecked())){
                        Toast.makeText(CounsellingActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(CounsellingActivity.this,TestKitDetailsActivity.class));
                        finish();
                    }
                }
            });
        }

        else {
            setContentView(R.layout.recent_details_8_m);

            vmmcYes = findViewById(R.id.vmmc_yes);
            vmmcNo = findViewById(R.id.vmmc_no);
            hivTestingYes = findViewById(R.id.hiv_testing_yes);
            hivTestingNo = findViewById(R.id.hiv_testing_no);
            referVmmcYes = findViewById(R.id.refer_vmmc_yes);
            referVmmcNo = findViewById(R.id.refer_vmmc_no);
            next = findViewById(R.id.counsel_nextbtn);
            previous = findViewById(R.id.counsel_prevbtn);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (vmmcYes.isChecked()){
                        vmmc = vmmcYes.getText().toString();
                    } else if (vmmcNo.isChecked()) {
                        vmmc = vmmcNo.getText().toString();
                    }

                    if (hivTestingYes.isChecked()){
                        hivTest = hivTestingYes.getText().toString();
                    } else if (hivTestingNo.isChecked()) {
                        hivTest = hivTestingNo.getText().toString();
                    }

                    if (referVmmcYes.isChecked()){
                        referVmmc = referVmmcYes.getText().toString();
                    } else if (referVmmcNo.isChecked()) {
                        referVmmc = referVmmcNo.getText().toString();
                    }

                    if ((vmmcYes.isChecked() || vmmcNo.isChecked()) && (hivTestingYes.isChecked() || hivTestingNo.isChecked()) && (referVmmcYes.isChecked() || referVmmcNo.isChecked())){
                        startActivity(new Intent(CounsellingActivity.this,TestKitDetailsActivity.class));
                        finish();
                    } else {
                        Toast.makeText(CounsellingActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CounsellingActivity.this,InformedConsent2Activity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        dialog.show();

    }
}