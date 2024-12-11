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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TBActivity extends AppCompatActivity {

    RadioButton tbYes,tbNo,mucusYes,mucusNo,coughYes,coughNo,lostWeightYes,lostWeightNo,feverYes,feverNo;

    Button next,previous , endNo , endYes;

    TextView clientName;

    Dialog dialog ;

    RelativeLayout tb_display;
    public static String tb,mucus,cough,lostWeight,fever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_19);

        tbYes = findViewById(R.id.tb_treatment_yes);
        tbNo = findViewById(R.id.tb_treatment_no);
        mucusYes = findViewById(R.id.mucus_test_yes);
        mucusNo = findViewById(R.id.mucus_test_no);
        coughYes = findViewById(R.id.cough_length_yes);
        coughNo = findViewById(R.id.cough_length_no);
        lostWeightYes = findViewById(R.id.lose_weight_yes);
        lostWeightNo = findViewById(R.id.lose_weight_no);

        feverYes = findViewById(R.id.fever_yes);
        feverNo = findViewById(R.id.fever_no);
        next = findViewById(R.id.screening_nextbtn);
        previous = findViewById(R.id.screening_prevbtn);
        clientName = findViewById(R.id.client_name);
        tb_display = findViewById(R.id.display_tb);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(TBActivity.this);
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
                Intent intent = new Intent(TBActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbNo.isChecked()){

                    tb_display.setVisibility(View.VISIBLE);
                }
            }
        });

        tbYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tb_display.setVisibility(View.INVISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tbYes.isChecked()){
                    tb = tbYes.getText().toString();
                } else if (tbNo.isChecked()) {
                    tb = tbNo.getText().toString();
                }

                if (mucusYes.isChecked()){
                    mucus = mucusYes.getText().toString();
                } else if (mucusNo.isChecked()) {
                    mucus = mucusNo.getText().toString();
                }

                if (coughNo.isChecked()){
                    cough = coughNo.getText().toString();
                } else if (coughYes.isChecked()) {
                    cough = coughYes.getText().toString();
                }

                if (lostWeightYes.isChecked()){
                    lostWeight = lostWeightYes.getText().toString();
                } else if (lostWeightNo.isChecked()) {
                    lostWeight = lostWeightNo.getText().toString();
                }

                if (feverYes.isChecked()){
                    fever = feverYes.getText().toString();
                } else if (feverNo.isChecked()) {
                    fever = feverNo.getText().toString();
                }

                if (tbNo.isChecked()){
                    if ((feverNo.isChecked() || feverYes.isChecked()) && (coughYes.isChecked() || coughNo.isChecked()) && (lostWeightNo.isChecked() || lostWeightYes.isChecked()) && (mucusNo.isChecked() ||
                            mucusYes.isChecked()) && (tbNo.isChecked())){
                        startActivity(new Intent(TBActivity.this,STIActivity.class));
                        finish();
                    }else {
                        Toast.makeText(TBActivity.this, "Make sure all options are checked", Toast.LENGTH_SHORT).show();
                    }
                } else if(tbYes.isChecked()){
                    startActivity(new Intent(TBActivity.this,STIActivity.class));
                    finish();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TBActivity.this,Wellness4Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}