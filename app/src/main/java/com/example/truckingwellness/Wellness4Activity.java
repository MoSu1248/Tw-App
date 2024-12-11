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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness4Activity extends AppCompatActivity {

    RadioButton financeYes,financeNo,measurementsYes,measurementsNo;

    EditText bloodPressureUpper,bloodPressureLower,heartRate;

    Button next, previous , endYes , endNo;

    TextView clientName;

    Dialog dialog;

    RelativeLayout wellness_container;

    public static Double bpLow,bpUp;
    public static String finance,measurement,bdUpper,bpLower,hr , bloodPressureStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_17);

        financeYes = findViewById(R.id.finance_manage_yes);
        financeNo = findViewById(R.id.finance_manage_no);
        measurementsYes = findViewById(R.id.wellness_measurment_yes);
        measurementsNo = findViewById(R.id.wellness_measurment_no);
        bloodPressureUpper = findViewById(R.id.systolic_pressure);
        bloodPressureLower = findViewById(R.id.diastolic_pressure);
        heartRate = findViewById(R.id.rate);
        next = findViewById(R.id.wellness4_nextbtn);
        previous = findViewById(R.id.wellness4_prevbtn);
        clientName = findViewById(R.id.client_name);
        wellness_container = findViewById(R.id.display_wellness);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        bloodPressureLower.setText(bpLower);
        bloodPressureUpper.setText(bdUpper);
        heartRate.setText(hr);

        dialog = new Dialog(Wellness4Activity.this);
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
                Intent intent = new Intent(Wellness4Activity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        measurementsYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wellness_container.setVisibility(View.VISIBLE);
            }
        });

        measurementsNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wellness_container.setVisibility(View.INVISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (financeYes.isChecked()){
                    finance = financeYes.getText().toString();
                } else if (financeNo.isChecked()) {
                    finance = financeNo.getText().toString();
                }

                if (measurementsYes.isChecked()){
                    measurement = measurementsYes.getText().toString();
                } else if (measurementsNo.isChecked()) {
                    measurement = measurementsNo.getText().toString();
                }

                bpLower = bloodPressureLower.getText().toString();
                bdUpper = bloodPressureUpper.getText().toString();
                hr = heartRate.getText().toString();

                if (measurementsYes.isChecked() && (bloodPressureUpper.getText().toString().isEmpty() || bloodPressureLower.getText().toString().isEmpty() || hr.isEmpty())){
                    Toast.makeText(Wellness4Activity.this, "Make sure all fields are filled in", Toast.LENGTH_SHORT).show();
                }else if (measurementsYes.isChecked()){
                    bpUp = Double.parseDouble(bloodPressureUpper.getText().toString());
                    bpLow = Double.parseDouble(bloodPressureLower.getText().toString());

                    if (bpUp > 300) {
                        bloodPressureUpper.setError("Invalid Input");
                        bloodPressureUpper.requestFocus();
                    } else if (bpLow < 40) {
                        bloodPressureLower.setError("Invalid Input");
                        bloodPressureLower.requestFocus();
                    } else {
                        if (((bpUp >= 70 && bpUp <= 90) && (bpLow >= 40 && bpLow <= 60))) {
                            bloodPressureStatus = "Low Blood Pressure";
                        } else if (((bpUp >= 90 && bpUp <= 120) && (bpLow >= 60 && bpLow <= 80))) {
                            bloodPressureStatus = "Ideal Blood Pressure";

                        } else if (((bpUp >= 120 && bpUp<= 140) && (bpLow >= 80 && bpLow <= 90))) {
                            bloodPressureStatus = "High blood Pressure";

                        } else if (((bpUp >= 140 && bpUp <= 190) && (bpLow >= 90 && bpLow <= 100))) {
                            bloodPressureStatus = "Dangerously high";
                        }
                        startActivity(new Intent(Wellness4Activity.this,Wellness5Activity.class));
                        finish();
                    }
                }

                if ((measurementsNo.isChecked() && (financeYes.isChecked() || financeNo.isChecked()))){
                    startActivity(new Intent(Wellness4Activity.this,TBActivity.class));
                    finish();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness4Activity.this,Wellness3Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}