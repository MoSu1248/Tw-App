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
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness_restest extends AppCompatActivity {

    RelativeLayout sugar, bp;
    Button next, previous, endYes, endNo;
    EditText sugar_txt_retest, bloodPressureUpper_restest, bloodPressureLower_retest;
    Dialog dialog;

   public static Double bp_up_retest, bp_low_retest, sugar_retest;

    public static String sugarStatus_retest, bloodPressureStatus_retest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wellness_restest);

        next = findViewById(R.id.wellness_retest_nextbtn);
        previous = findViewById(R.id.wellness_retest_prevbtn);
        bloodPressureUpper_restest = findViewById(R.id.Bp_upper_retest);
        bloodPressureLower_retest = findViewById(R.id.Bp_lower_retest);
        sugar_txt_retest = findViewById(R.id.blood_sugar);
        dialog = new Dialog(Wellness_restest.this);
        dialog.setContentView(R.layout.exit_noti);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                Intent intent = new Intent(Wellness_restest.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        sugar = findViewById(R.id.relativeLayout);
        bp = findViewById(R.id.bloodpressure);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bloodPressureUpper_restest.getText().toString().isEmpty() || bloodPressureLower_retest.getText().toString().isEmpty() || sugar_txt_retest.getText().toString().isEmpty()) {
                    Toast.makeText(Wellness_restest.this, "Please Complete all required fields", Toast.LENGTH_SHORT).show();
                } else {

                    sugar_retest = Double.parseDouble(sugar_txt_retest.getText().toString());
                    bp_up_retest = Double.parseDouble(bloodPressureUpper_restest.getText().toString());
                    bp_low_retest = Double.parseDouble(bloodPressureLower_retest.getText().toString());

                    if (sugar_retest < 2 || sugar_retest > 35) {
                        sugar_txt_retest.setError("Invalid Input");
                        sugar_txt_retest.requestFocus();
                    } else if (sugar_retest <= 2.8) {
                        sugarStatus_retest = "Dangerously Low";
                    } else if (sugar_retest >= 2.9 && sugar_retest <= 3.9) {
                        sugarStatus_retest = "Low";
                    } else if (sugar_retest >= 4 && sugar_retest <= 7.8) {
                        sugarStatus_retest = "Normal";
                    } else if (sugar_retest >= 7.9 && sugar_retest <= 14.9) {
                        sugarStatus_retest = "High";
                    } else if (sugar_retest >= 15) {
                        sugarStatus_retest = "Dangerously High";
                    }

                    if (bp_up_retest > 300) {
                        bloodPressureUpper_restest.setError("Invalid Input");
                        bloodPressureUpper_restest.requestFocus();
                    } else if (bp_low_retest < 40) {
                        bloodPressureLower_retest.setError("Invalid Input");
                        bloodPressureLower_retest.requestFocus();
                    } else {
                        if (((bp_up_retest >= 70 && bp_up_retest <= 90) && (bp_low_retest >= 40 && bp_low_retest <= 60))) {
                            bloodPressureStatus_retest = "Low Blood Pressure";
                        } else if (((bp_up_retest >= 90 && bp_up_retest <= 120) && (bp_low_retest >= 60 && bp_low_retest <= 80))) {
                            bloodPressureStatus_retest = "Ideal Blood Pressure";

                        } else if (((bp_up_retest >= 120 && bp_up_retest <= 140) && (bp_low_retest >= 80 && bp_low_retest <= 90))) {
                            bloodPressureStatus_retest = "High blood Pressure";

                        } else if (((bp_up_retest >= 140 && bp_up_retest <= 190) && (bp_low_retest >= 90 && bp_low_retest <= 100))) {
                            bloodPressureStatus_retest = "Dangerously high";
                        }
                        startActivity(new Intent(Wellness_restest.this, TBActivity.class));
                        finish();
                    }

                }

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness_restest.this, Wellness4Activity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        dialog.show();

    }
}

