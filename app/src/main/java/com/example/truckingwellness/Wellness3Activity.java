package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness3Activity extends AppCompatActivity {

    Spinner impactIllnessSpin,emotionalScaleSpin;

    Button next,previous , endNo , endYes;

    RadioButton emoHealthCounsellingYes,emoHealthCounsellingNo;

    EditText otherIllnesses;

    TextView clientName;

    Dialog dialog;

    public  static String impactIllness,emotionalScale,otherIllness,emoHealth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_16);

        impactIllnessSpin = findViewById(R.id.emotioanlWellnessSpinner);
        emotionalScaleSpin = findViewById(R.id.biggestImpactSpinner);
        next = findViewById(R.id.wellness3_nextbtn);
        previous = findViewById(R.id.wellness3_prevbtn);
        emoHealthCounsellingYes = findViewById(R.id.emotional_counsel_yes);
        emoHealthCounsellingNo = findViewById(R.id.emotional_counsel_no);
        otherIllnesses = findViewById(R.id.other_illness);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(Wellness3Activity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.biggestImpactIllness));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        impactIllnessSpin.setAdapter(myAdpater);
        ArrayAdapter<String> myAdpater1 = new ArrayAdapter<String>(Wellness3Activity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.emotionalWellness));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emotionalScaleSpin.setAdapter(myAdpater1);

        otherIllnesses.setText(otherIllness);

        dialog = new Dialog(Wellness3Activity.this);
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
                Intent intent = new Intent(Wellness3Activity.this,MainMenuActivity.class);
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
                impactIllness = impactIllnessSpin.getSelectedItem().toString();
                emotionalScale = emotionalScaleSpin.getSelectedItem().toString();
                otherIllness = otherIllnesses.getText().toString();
                if (emoHealthCounsellingYes.isChecked()){
                    emoHealth = emoHealthCounsellingYes.getText().toString();
                } else if (emoHealthCounsellingNo.isChecked()) {
                    emoHealth = emoHealthCounsellingNo.getText().toString();
                }

                if (impactIllnessSpin.getSelectedItemPosition() != 0 && emotionalScaleSpin.getSelectedItemPosition() != 0 && (emoHealthCounsellingYes.isChecked() || emoHealthCounsellingNo.isChecked())){
                    if (emoHealthCounsellingNo.isChecked() || emoHealthCounsellingYes.isChecked()){
                        startActivity(new Intent(Wellness3Activity.this,Wellness4Activity.class));
                        finish();
                    }else {
                        Toast.makeText(Wellness3Activity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Wellness3Activity.this, "Please complete all necessary fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness3Activity.this,Wellness2Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}