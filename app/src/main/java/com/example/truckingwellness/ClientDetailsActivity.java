package com.example.truckingwellness;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClientDetailsActivity extends AppCompatActivity {

    public static String name, surname, dob, gender, companyName , cellNum ,optionalNum = " " , str , current_user;

    int spinPos, number;
    EditText fname, sname, idNum, company ,cellNo,optionalCell;
    boolean genderType;
    Button next, previous , endYes , endNo;
    RadioButton male, female, otherGen;

    TextView test ,  location, date;
    Spinner spinner;
    ArrayAdapter<String> adapterItems;

    SharedPreferences UserID, User_status , flagged_info , flagged_info_reason , dailyAppCount, lastApp;

    private EditText dateEdt;

    Dialog dialog ;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    Date curDate = Calendar.getInstance().getTime();
    String dateTime = DateFormat.getDateInstance(DateFormat.FULL).format(curDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.recent_details_1);

        spinner = findViewById(R.id.idSpinner);
        next = findViewById(R.id.client_nextbtn);
        previous = findViewById(R.id.client_prevbtn);
        fname = findViewById(R.id.f_name);
        sname = findViewById(R.id.surname);
        idNum = findViewById(R.id.client_number);
        dateEdt = findViewById(R.id.idEdtDate);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        otherGen = findViewById(R.id.Other);
        company = findViewById(R.id.company_name);
        test = findViewById(R.id.textView8);
        cellNo= findViewById(R.id.client_number);
        optionalCell = findViewById(R.id.additional_cell);
        location = findViewById(R.id.last_location) ;
        date = findViewById(R.id.last_date) ;

        SharedPreferences UserID = getApplication().getSharedPreferences("client_id", Context.MODE_PRIVATE);
        String Id_details = UserID.getString("client_id", "");

        dialog = new Dialog(ClientDetailsActivity.this);
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
                Intent intent = new Intent(ClientDetailsActivity.this,MainMenuActivity.class);
                startActivity(intent);
            }
        });

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        loadNote();


        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();

                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(

                        ClientDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        },

                        year, month, day);
                datePickerDialog.show();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (male.isChecked()) {
                    gender = male.getText().toString();
                } else if (female.isChecked()) {
                    gender = female.getText().toString();
                } else if (otherGen.isChecked()) {
                    gender = otherGen.getText().toString();
                }
                name = fname.getText().toString();
                surname = sname.getText().toString();
                dob = dateEdt.getText().toString();
                companyName = company.getText().toString();
                cellNum = cellNo.getText().toString();
                optionalNum = optionalCell.getText().toString();
                Intent intent = new Intent(getApplicationContext(), DemographicsActivity.class);
                intent.putExtra("message_key", str);

                if (male.isChecked() || female.isChecked() || otherGen.isChecked())
                {
                    if (name.isEmpty() || surname.isEmpty() || dob.isEmpty() || cellNum.isEmpty()) {
                    Toast.makeText(ClientDetailsActivity.this, "All fields need to be filled", Toast.LENGTH_SHORT).show();
                } else {
                        startActivity(intent);
                        finish();
                    }
                }
                   else {
                        Toast.makeText(ClientDetailsActivity.this, "All options must Be checked", Toast.LENGTH_SHORT).show();
                    }
            }
        });


        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), TestTypeActivity.class);
                startActivity(intent);
            }
        });
    }


    public void loadNote() {

        SharedPreferences UserID = getApplication().getSharedPreferences("client_id", Context.MODE_PRIVATE);
        String Id_details = UserID.getString("client_id", "");

        flagged_info = getSharedPreferences("flagged_info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = flagged_info.edit();

        flagged_info_reason = getSharedPreferences("flagged_info_reason", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 =  flagged_info_reason.edit();

        dailyAppCount = getSharedPreferences("dailyAppCount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 =  dailyAppCount.edit();

        lastApp = getSharedPreferences("lastAppointment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor4 =  lastApp.edit();

        editor.putString("flagged_info","No");
        editor2.putString("flagged_info_reason", "");

        editor.commit();
        editor2.commit();

        db.collection("Clients").document(Id_details)
                .get()
               .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            Toast.makeText(ClientDetailsActivity.this, "Existing Client", Toast.LENGTH_SHORT).show();
                            fname.setText(documentSnapshot.getString("Name"));
                            sname.setText(documentSnapshot.getString("Surname"));
                            company.setText(documentSnapshot.getString("companyName"));
                            cellNo.setText(documentSnapshot.getString("cellNumber"));
                            dateEdt.setText(documentSnapshot.getString("dateOfBirth"));
                            optionalCell.setText(documentSnapshot.getString("additionalCellNumber"));
                            location.setText(documentSnapshot.getString("lastAppointmentlocation"));
                            date.setText(documentSnapshot.getString("lastAppointment"));
                            if (documentSnapshot.getString("Flagged").equals("Yes")) {
                                showFlagDialogue();
                                editor.putString("flagged_info", documentSnapshot.getString("Flagged"));
                                editor2.putString("flagged_info_reason", documentSnapshot.getString("flagReason"));
                                editor.commit();
                                editor2.commit();
                            }

                            if (documentSnapshot.getString("lastAppointment").equals(dateTime)){
                                showWarningDialogue();
                                number = Integer.parseInt((documentSnapshot.getString("DailyAppCount")));
                                editor3.putString("dailyAppCount", String.valueOf(number+1));
                                editor3.commit();


                            }else{
                                number = 1;
                                editor3.putString("dailyAppCount", String.valueOf(number));
                                editor3.commit();
                            }

                            current_user = "Exist" ;
                            User_status = getSharedPreferences("client_exist", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = User_status.edit();
                            editor.putString("client_exist", current_user);
                            editor.commit();

                            if (documentSnapshot.getString("gender").equals("Male")){
                                male.setChecked(true);
                            } else  if (documentSnapshot.getString("gender").equals("Female")){
                                female.setChecked(true);
                            } else  if (documentSnapshot.getString("gender").equals("Other")){
                                otherGen.setChecked(true);
                            }

                        }else{
                           Toast.makeText(ClientDetailsActivity.this, "New User", Toast.LENGTH_SHORT).show();
                            current_user = "New" ;
                            User_status = getSharedPreferences("client_exist", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = User_status.edit();
                            number = 1;
                            editor3.putString("dailyAppCount", String.valueOf(number));
                            editor3.commit();
                            System.out.println(number);
                            editor.putString("client_exist", current_user);
                            editor.commit();
                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                   @Override public void onFailure(@NonNull Exception e) {
                       number = 1;
                       editor3.putString("dailyAppCount", String.valueOf(number));
                       editor3.commit();
                       System.out.println(number);

                       editor.putString("flagged_info","No");
                       editor2.putString("flagged_info_reason", "");
                       editor.commit();
                       editor2.commit();

                   }
               });
    }


    private void showFlagDialogue(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.flagged_user);
        View view = LayoutInflater.from(ClientDetailsActivity.this).inflate(R.layout.flag_user, errorConstraintLayout);

        //Button errorClose = (Button) findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(ClientDetailsActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    private void showWarningDialogue(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.caution_user);
        View view = LayoutInflater.from(ClientDetailsActivity.this).inflate(R.layout.caution_user, errorConstraintLayout);


        AlertDialog.Builder builder = new AlertDialog.Builder(ClientDetailsActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {

        dialog.show();

    }

}
