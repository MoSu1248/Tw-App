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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TestingReasonActivity extends AppCompatActivity {

    public static String hivTest,lastTested,testResult,accessedTreatment,testReason,otherReasonTest;

    EditText othertestReason;

    RadioButton hivYes,hivNo;
    Button next, previous , endYes , endNo;

    Spinner lastTestSpin,lastResultSpin,accessedTreatSpin,reasonTestSpin;

    TextView clientName;

    RelativeLayout testing ;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_4);

        lastTestSpin = findViewById(R.id.lastTested);
        lastResultSpin = findViewById(R.id.lastResult);
        accessedTreatSpin = findViewById(R.id.accessedTreatmeant);
        reasonTestSpin = findViewById(R.id.reasonLastTest);
        othertestReason = findViewById(R.id.other_testing_reason);
        hivNo = findViewById(R.id.first_test_no);
        hivYes = findViewById(R.id.first_test_yes);
        next =findViewById(R.id.reason_nextbtn);
        previous =findViewById(R.id.reason_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        testing = findViewById(R.id.display_firstTestHiv);

        othertestReason.setText(otherReasonTest);

        dialog = new Dialog(TestingReasonActivity.this);
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
                Intent intent = new Intent(TestingReasonActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(TestingReasonActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.lastTested));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lastTestSpin.setAdapter(myAdpater);
        ArrayAdapter<String> myAdpater1 = new ArrayAdapter<String>(TestingReasonActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.lastResult));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lastResultSpin.setAdapter(myAdpater1);
        ArrayAdapter<String> myAdpater2 = new ArrayAdapter<String>(TestingReasonActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.accessed));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accessedTreatSpin.setAdapter(myAdpater2);
        ArrayAdapter<String> myAdpater3 = new ArrayAdapter<String>(TestingReasonActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.testReason));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reasonTestSpin.setAdapter(myAdpater3);

        hivYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testing.setVisibility(View.INVISIBLE);

            }
        });

        hivNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testing.setVisibility(View.VISIBLE);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hivNo.isChecked()){
                    hivTest = hivNo.getText().toString();
                } else if (hivYes.isChecked()) {
                    hivTest = hivYes.getText().toString();
                }
                lastTested = lastTestSpin.getSelectedItem().toString();
                testResult = lastResultSpin.getSelectedItem().toString();
                accessedTreatment = accessedTreatSpin.getSelectedItem().toString();
                testReason = reasonTestSpin.getSelectedItem().toString();
                otherReasonTest = othertestReason.getText().toString();

                if (hivNo.isChecked()){
                    if ((lastTestSpin.getSelectedItemPosition() != 0 && lastResultSpin.getSelectedItemPosition() != 0  && reasonTestSpin.getSelectedItemPosition() != 0) && otherReasonTest != "null")
                    {
                        startActivity(new Intent(TestingReasonActivity.this,InformedConsentActivity.class));
                        finish();
                    }
                    else {
                        Toast.makeText(TestingReasonActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();
                    }

                }
                if (hivYes.isChecked()){
                    if (otherReasonTest.isEmpty()){
                        Toast.makeText(TestingReasonActivity.this, "Please Complete Testing Reason", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        lastTested.equals("--");
                        testResult.equals("--");
                        testReason.equals("--");

                        startActivity(new Intent(TestingReasonActivity.this,InformedConsentActivity.class));
                        finish();
                    }

                } else {
                    Toast.makeText(TestingReasonActivity.this, "Make sure all fields are filled in and check boxes selected", Toast.LENGTH_SHORT).show();

                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestingReasonActivity.this,DemographicsActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {

        dialog.show();

    }

}