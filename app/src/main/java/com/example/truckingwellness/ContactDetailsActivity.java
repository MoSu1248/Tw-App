package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {

    public static String cellNum,optionalNum, contactTime,prefLanguage,engProf , str;

    Button next,previous , endNo , endYes;

//    EditText cellNo,optionalCell;

    Spinner spinner1,spinner2,spinner3;

    TextView clientName , test;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_2);

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
                Intent intent = new Intent(ContactDetailsActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        previous = findViewById(R.id.contact_prevbtn);
        next = findViewById(R.id.contact_nextbtn);
        spinner1 = findViewById(R.id.contactTime);
        spinner2 = findViewById(R.id.prefLanguage);
        spinner3 = findViewById(R.id.englishProf);

        clientName = findViewById(R.id.client_name);
        clientName.setText(""+ClientDetailsActivity.name+" "+ ClientDetailsActivity.surname);
        test = findViewById(R.id.textView8) ;

        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(ContactDetailsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.contactTime));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(myAdpater);
        ArrayAdapter<String> myAdpater2 = new ArrayAdapter<String>(ContactDetailsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.language));
        myAdpater2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdpater2);
        ArrayAdapter<String> myAdpater3 = new ArrayAdapter<String>(ContactDetailsActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.englishProf));
        myAdpater3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(myAdpater3);

        Intent intent = getIntent();
        str = intent.getStringExtra("message_key");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                cellNum = cellNo.getText().toString();
//                optionalNum = optionalCell.getText().toString();
                contactTime = spinner1.getSelectedItem().toString();
                prefLanguage = spinner2.getSelectedItem().toString();
                engProf = spinner3.getSelectedItem().toString();
                startActivity(new Intent(ContactDetailsActivity.this,DemographicsActivity.class));
                finish();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
                startActivity(intent);

            }
        });
    }



}