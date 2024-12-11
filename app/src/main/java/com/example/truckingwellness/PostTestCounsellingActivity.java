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

public class PostTestCounsellingActivity extends AppCompatActivity {

    EditText maleCondoms,femaleCondoms,lubricants;

    RadioButton postTestCompletedYes,postTestCompletedNo,informedHivYes,informedHivNo;

    Button next,previous , endYes , endNo;

    TextView clientName;

    Dialog dialog ;


    public static String condomsMale = "0",condomsFemale = "0",postTestCompleted,informedHiv,lube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_25);

        maleCondoms = findViewById(R.id.male_condoms);
        femaleCondoms = findViewById(R.id.female_condoms);
        lubricants = findViewById(R.id.lubricant);
        postTestCompletedYes = findViewById(R.id.post_test_completed_yes);
        postTestCompletedNo = findViewById(R.id.post_test_completed_no);
        informedHivYes = findViewById(R.id.client_informed_yes);
        informedHivNo = findViewById(R.id.client_informed_no);
        next = findViewById(R.id.posttest_nextbtn);
        previous = findViewById(R.id.posttest_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        maleCondoms.setText(condomsMale);
        femaleCondoms.setText(condomsFemale);
        lubricants.setText(lube);


        dialog = new Dialog(PostTestCounsellingActivity.this);
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
                Intent intent = new Intent(PostTestCounsellingActivity.this,MainMenuActivity.class);
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
                if (postTestCompletedYes.isChecked()){
                    postTestCompleted = postTestCompletedYes.getText().toString();
                } else if (postTestCompletedNo.isChecked()) {
                    postTestCompleted = postTestCompletedNo.getText().toString();
                }

                if (informedHivYes.isChecked()){
                    informedHiv = informedHivYes.getText().toString();
                } else if (informedHivNo.isChecked()) {
                    informedHiv = informedHivNo.getText().toString();
                }
                condomsMale = maleCondoms.getText().toString();
                condomsFemale = femaleCondoms.getText().toString();
                lube = lubricants.getText().toString();

                if ((postTestCompletedNo.isChecked() || postTestCompletedYes.isChecked()) && (informedHivYes.isChecked() || informedHivNo.isChecked()) ){
                    startActivity(new Intent(PostTestCounsellingActivity.this,SubmissionOverviewActivity.class));
                    finish();
                } else {
                    Toast.makeText(PostTestCounsellingActivity.this, "All fields and choices need to be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostTestCounsellingActivity.this,FacilityReferralActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}