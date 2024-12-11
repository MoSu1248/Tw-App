package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TestTypeActivity extends AppCompatActivity {
    private static final String PREF_TOTAL_KEY = "pref_total_key";
    // String [] testTypes = {"Single","Couple","Group"};

    Spinner spinner , spinner2 , nationalitySpin;
    public static String testType , idNo , idType ,  nationality  ;
    Button next , previous , endYes , endNo ;

    TextInputLayout type;

    TextInputEditText idNum ;
    String value = "South African" ;

    String idValue = "South African ID"  ;
    SharedPreferences UserID ;

    Dialog dialog ;


//    private FirebaseFirestore db = FirebaseFirestore.getInstance();
//    private DocumentReference noteRef = db.collection("Clients").document(idNo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details0);

        previous = findViewById(R.id.session_prevbtn);
        next = findViewById(R.id.session_nextbtn);
        spinner = findViewById(R.id.spinnerTest);
        idNum = findViewById(R.id.client_number);
        spinner2 = findViewById(R.id.idSpinner);
        nationalitySpin = findViewById(R.id.nationalitySpinner);
        type = findViewById(R.id.textInputLayout4);


        dialog = new Dialog(TestTypeActivity.this);
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
                Intent intent = new Intent(TestTypeActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        UserID = getSharedPreferences("client_id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = UserID.edit();

//        Context context;
//        SharedPreferences pref = context.getSharedPreferences(MY_PREFERENCE_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(PREF_TOTAL_KEY, idNo);
//        editor.apply();

        idNo = idNum.getText().toString();

        Intent intent = getIntent();
        String username = getIntent().getStringExtra("message_key");

        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(TestTypeActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.testTypeArray));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdpater);

        ArrayAdapter<String> myAdpater2 = new ArrayAdapter<String>(TestTypeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.idType));
        myAdpater2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdpater2);


        ArrayAdapter<String> myAdpater3 = new ArrayAdapter<String>(TestTypeActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.nationality));
        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationalitySpin.setAdapter(myAdpater3);


        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (spinner2.getSelectedItemPosition() == 1){
                    type.setHint("ID Number");
                    
                } else if (spinner2.getSelectedItemPosition() == 2){
                    type.setHint("Passport Number");

                } else if (spinner2.getSelectedItemPosition() == 3) {
                    type.setHint("Phone Number");
                }

                if (spinner2.getSelectedItemPosition() == 1){
                    nationalitySpin.setSelection(1);
                } else if (spinner2.getSelectedItemPosition() == 2 || spinner2.getSelectedItemPosition() == 3) {
                    nationalitySpin.setSelection(2);
                }

                if (spinner2.getSelectedItemPosition() == 1 && nationalitySpin.getSelectedItemPosition() == 2){
                    Toast.makeText(TestTypeActivity.this, "Nationality and ID type dont match", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createLabel(spinner.getSelectedItem().toString());
                createLabel(nationalitySpin.getSelectedItem().toString());

//                 intent.putExtra("IdNumber",idNo);
                String str = idNum.getText().toString();

                idNo = idNum.getText().toString();
                idType = spinner.getSelectedItem().toString();
                nationality = nationalitySpin.getSelectedItem().toString();

                editor.putString("client_id", idNo);
                editor.commit();


                if (idNo.isEmpty()) {
                    Toast.makeText(TestTypeActivity.this, "ID Number needs to be filled in", Toast.LENGTH_SHORT).show();
                } else if (spinner2.getSelectedItemPosition() == 0 || spinner.getSelectedItemPosition() == 0 || nationalitySpin.getSelectedItemPosition() == 0 ) {
                    Toast.makeText(TestTypeActivity.this, "Please make sure all fields are selected", Toast.LENGTH_SHORT).show();
                } else if (spinner2.getSelectedItemPosition() == 1 ) {
                    if (idNum.getText().toString().trim().length() != 13 ) {
                        Toast.makeText(TestTypeActivity.this, "ID number invalid", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
                        editor.putString("client_id", idNo);
                        editor.commit();
                        startActivity(intent);
                    }
                }
                else  if (spinner2.getSelectedItemPosition() == 2 ) {
                    if (idNum.getText().toString().trim().length() != 9) {
                        Toast.makeText(TestTypeActivity.this, "Passport number invalid", Toast.LENGTH_SHORT).show();
                    }else {

                        Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
                        editor.putString("client_id", idNo);
                        editor.commit();
                        startActivity(intent);
                    }
                }
                else  if (spinner2.getSelectedItemPosition() == 3 ) {
                    if (idNum.getText().toString().trim().length() != 10) {
                        Toast.makeText(TestTypeActivity.this, "Phone number invalid", Toast.LENGTH_SHORT).show();
                    }else {

                        Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
                        editor.putString("client_id", idNo);
                        editor.commit();
                        startActivity(intent);
                    }
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), ClientDetailsActivity.class);
                    editor.putString("client_id", idNo);
                    editor.commit();
                    startActivity(intent);
                }

            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestTypeActivity.this,MainMenuActivity.class);
                dialog.show();
////                Pair[] pairs = new Pair[2];
////                pairs[0] = new Pair<View,String>(setup_trans, "scan_transition");
////                pairs[1] = new Pair<View,String>(setup_btn, "scan_card_transition");
//
//               // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(QrScannerActivity.this,pairs);
//                intent.putExtra("message_key",username);
//
////                startActivity(intent,options.toBundle());
//                startActivity(intent);
            }
        });
    }

    public void createLabel(String temp){
        testType = temp;
    }

    private void check(){

        if (spinner2.getSelectedItemPosition() == 1 ) {
        if (idNum.getText().toString().trim().length() != 13 ) {
            Toast.makeText(TestTypeActivity.this, "ID number invalid", Toast.LENGTH_SHORT).show();

        }
    }
    else  if (spinner2.getSelectedItemPosition() == 2 ) {
        if (idNum.getText().toString().trim().length() != 9) {
            Toast.makeText(TestTypeActivity.this, "Passport number invalid", Toast.LENGTH_SHORT).show();
        }
    }
    else  if (spinner2.getSelectedItemPosition() == 3 ) {
        if (idNum.getText().toString().trim().length() != 10) {
            Toast.makeText(TestTypeActivity.this, "Phone number invalid", Toast.LENGTH_SHORT).show();
        }
    }}

    @Override
    public void onBackPressed() {

        dialog.show();

    }
}