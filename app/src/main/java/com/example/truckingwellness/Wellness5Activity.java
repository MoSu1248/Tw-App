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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Wellness5Activity extends AppCompatActivity {

    EditText bloodSugar,chol;
    EditText sugar_txt , weight_txt  , height_txt , waist_txt ;
    Button next,previous , endNo , endYes;

    Dialog dialog;

    TextView clientName;

//    public static String bs,ch,waistCm,weightKg,heightCm ;

    public static double  Bmi , height , waist , weight , cholesterol , sugar;

    static String bmiStatus, sugarStatus , cholStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_18);

        sugar_txt = findViewById(R.id.blood_sugar);
        chol = findViewById(R.id.cholesterol);
        weight_txt = findViewById(R.id.weight);
        height_txt = findViewById(R.id.height);
        waist_txt = findViewById(R.id.waist);

        next = findViewById(R.id.wellness5_nextbtn);
        previous = findViewById(R.id.wellness5_prevbtn);
        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(Wellness5Activity.this);
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
                Intent intent = new Intent(Wellness5Activity.this,MainMenuActivity.class);
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

//                bs = bloodSugar.getText().toString();
//                ch = cholesterol.getText().toString();
//                waistCm = waist.getText().toString();
//                weightKg = waist.getText().toString();
//                heightCm = height.getText().toString();

                if (sugar_txt.getText().toString().isEmpty() || chol.getText().toString().isEmpty() || height_txt.getText().toString().isEmpty() || weight_txt.getText().toString().isEmpty() || waist_txt.getText().toString().isEmpty()) {
                    Toast.makeText(Wellness5Activity.this, "Complete", Toast.LENGTH_SHORT).show();
                } else {
                    sugar = Double.parseDouble(sugar_txt.getText().toString());
                    cholesterol = Double.parseDouble(chol.getText().toString());
                    height = Double.parseDouble(height_txt.getText().toString());
                    weight = Double.parseDouble(weight_txt.getText().toString());

                    Bmi = weight / (height * height);

                    if (sugar < 2 || sugar > 35) {
                        sugar_txt.setError("Invalid Input");
                        sugar_txt.requestFocus();
                    }else if (cholesterol >= 15 ){
                        chol.setError("Invalid Input");
                        chol.requestFocus();
                    }else if (Bmi < 15 || Bmi > 200){
                        height_txt.setError("Invalid Input");
                        height_txt.requestFocus();
                    }
                    else {
                        if (sugar <= 2.8) {
                            sugarStatus = "Dangerously Low";
                        } else if (sugar >= 2.9 && sugar <= 3.9) {
                            sugarStatus = "Low";
                        } else if (sugar >= 4 && sugar <= 7.8) {
                            sugarStatus = "Normal";
                        } else if (sugar >= 7.9 && sugar <= 14.9) {
                            sugarStatus = "High";
                        } else if (sugar >= 15) {
                            sugarStatus = "Dangerously High";
                        }

                        if (cholesterol >= 0.9 && cholesterol <= 5) {
                            cholStatus = "Desirable";
                        } else if (cholesterol > 5 && cholesterol <= 7.5) {
                            cholStatus = "Borderline to high";
                        } else if (cholesterol >= 7.5) {
                            cholStatus = "High Risk";
                        }

                        if (Bmi < 18.5) {
                            bmiStatus = "Underweight";
                        } else if (Bmi >= 18.5 && Bmi <= 24.9) {
                            bmiStatus = "Normal";
                        } else if (Bmi >= 25 && Bmi <= 30) {
                            bmiStatus = "Overweight";
                        } else if (Bmi >= 31 && Bmi <= 40) {
                            bmiStatus = "Obese";
                        } else if (Bmi > 40) {
                            bmiStatus = "Severe Obese";
                        }
//                        Toast.makeText(Wellness5Activity.this, " Sugar status: " + sugarStatus + " Chol:  " + cholStatus + " Bmi Status: " + bmiStatus, Toast.LENGTH_SHORT).show();

                        if (Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high"){
                            startActivity(new Intent(Wellness5Activity.this, Wellness_retest2.class));
                            finish();
                        }else if (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously High"){
                            startActivity(new Intent(Wellness5Activity.this, Wellness_retest3.class));
                            finish();
                        }else if((Wellness4Activity.bloodPressureStatus == "Low Blood Pressure" || Wellness4Activity.bloodPressureStatus == "Dangerously high") && (Wellness5Activity.sugarStatus == "Dangerously Low" || Wellness5Activity.sugarStatus == "Dangerously high")) {
                            startActivity(new Intent(Wellness5Activity.this, Wellness_restest.class));
                            finish();
                        }
                        else {
                            startActivity(new Intent(Wellness5Activity.this, TBActivity.class));
                            finish();
                        }

                    }
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Wellness5Activity.this,Wellness4Activity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}