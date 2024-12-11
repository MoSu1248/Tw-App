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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness_retest3 extends AppCompatActivity {

    EditText sugar_txt_retest ;
    Button next, previous , endYes , endNo;
    Dialog dialog;
    public static String sugarStatus_retest ;
    public static Double sugar_retest ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wellness_retest3);

        dialog = new Dialog(Wellness_retest3.this);
        dialog.setContentView(R.layout.exit_noti);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        next = findViewById(R.id.wellness_retest_nextbtn);
        previous = findViewById(R.id.wellness_retest_prevbtn);

        sugar_txt_retest = findViewById(R.id.blood_sugar_restest);

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
                Intent intent = new Intent(Wellness_retest3.this,MainMenuActivity.class);
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
           public void onClick(View v) {

               sugar_retest = Double.parseDouble(sugar_txt_retest.getText().toString());


               if (sugar_txt_retest.getText().toString().isEmpty()){
                   Toast.makeText(Wellness_retest3.this, "Please Complete all required fields", Toast.LENGTH_SHORT).show();
               }else{
                   sugar_retest = Double.parseDouble(sugar_txt_retest.getText().toString());

                   if (sugar_retest < 2 || sugar_retest > 35) {
                       sugar_txt_retest.setError("Invalid Input");
                       sugar_txt_retest.requestFocus();
                   }
                   else if (sugar_retest <= 2.8) {
                       sugarStatus_retest = "Dangerously Low";
                   } else if (sugar_retest >= 2.9 && sugar_retest <= 3.9) {
                       sugarStatus_retest = "Low";
                   } else if (sugar_retest >= 4 && sugar_retest <= 7.8) {
                       sugarStatus_retest= "Normal";
                   } else if (sugar_retest >= 7.9 && sugar_retest <= 14.9) {
                       sugarStatus_retest = "High";
                   } else if (sugar_retest >= 15) {
                       sugarStatus_retest = "Dangerously High";
                   }
                   startActivity(new Intent(Wellness_retest3.this,TBActivity.class));
                   finish();
               }
           }
       });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness_retest3.this,Wellness4Activity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}