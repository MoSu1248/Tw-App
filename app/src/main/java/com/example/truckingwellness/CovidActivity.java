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

import com.google.android.material.textfield.TextInputLayout;

public class CovidActivity extends AppCompatActivity {

    Button next, previous , endNo , endYes;

    RadioButton diagnosedYes,diagnosedNo,vaccinatedYes,vaccinatedNo;

    EditText numCovid,notVaccinated;

    TextView clientName;

    Dialog dialog;

    TextInputLayout notVaccine , covidnumber;

    public static String haveCovid,fullyVaccinated,covidNum,notVaccinatedReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_13);

        diagnosedYes = findViewById(R.id.covid_yes);
        diagnosedNo = findViewById(R.id.covid_no);
        vaccinatedYes = findViewById(R.id.vaccinated_yes);
        vaccinatedNo = findViewById(R.id.vaccinated_no);
        next = findViewById(R.id.covid_nextbtn);
        previous = findViewById(R.id.covid_prevbtn);
        numCovid = findViewById(R.id.times_covid);
        notVaccinated = findViewById(R.id.why_not_vaccinated);
        clientName = findViewById(R.id.client_name);
        notVaccine = findViewById(R.id.textInputLayout21);
        covidnumber = findViewById(R.id.textInputLayout22);

        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);

        dialog = new Dialog(CovidActivity.this);
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
                Intent intent = new Intent(CovidActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

      diagnosedYes.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            covidnumber.setVisibility(View.VISIBLE);
          }
      });

        diagnosedNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                covidnumber.setVisibility(View.INVISIBLE);
                numCovid.setText("");
            }
        });

        vaccinatedNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notVaccine.setVisibility(View.VISIBLE);

            }
        });

        vaccinatedYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            notVaccine.setVisibility(View.INVISIBLE);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diagnosedYes.isChecked()){
                    haveCovid = diagnosedYes.getText().toString();
                } else if (diagnosedNo.isChecked()) {
                    haveCovid = diagnosedNo.getText().toString();
                }

                if (vaccinatedYes.isChecked()){
                    fullyVaccinated = vaccinatedYes.getText().toString();
                } else if (vaccinatedNo.isChecked()) {
                    fullyVaccinated = vaccinatedNo.getText().toString();
                }

                covidNum = numCovid.getText().toString();
                notVaccinatedReason = notVaccinated.getText().toString();

                if((vaccinatedYes.isChecked() || vaccinatedNo.isChecked()) && (diagnosedYes.isChecked() || diagnosedNo.isChecked())){

                    if (vaccinatedNo.isChecked() && notVaccinatedReason.isEmpty()){
                        Toast.makeText(CovidActivity.this, "Please Fill in all Fields  ", Toast.LENGTH_SHORT).show();
                    }
                    else if (diagnosedYes.isChecked() && covidNum.isEmpty()){
                        Toast.makeText(CovidActivity.this, "Please Fill in all Fields  ", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        startActivity(new Intent(CovidActivity.this,WellnessActivity.class));
                        finish();
                    }

                }else {
                    Toast.makeText(CovidActivity.this, "Please Fill in all Fields  ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CovidActivity.this,ConfirmationResultActivity.class));
                finish();
            }
        });
    }


    @Override
    public void onBackPressed() {

        dialog.show();

    }
}