package com.example.truckingwellness;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RequestActivity extends AppCompatActivity {

    public static  String nurseId = "",nurseName = "",nuresRequest = "" , requestTitle = "";

    TextView id,name,request , test, title ;

    Button submit;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Date curDate = Calendar.getInstance().getTime();
    String dateTime = DateFormat.getDateInstance(DateFormat.FULL).format(curDate);
    String[] splitDate = dateTime.split(",");
    ImageView request_trans;
    CardView request_btn;

    ImageButton back;

    EditText enurseNam;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        id = findViewById(R.id.Idtxt);
        request = findViewById(R.id.Reasontxt);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back_btn);
        submit = findViewById(R.id.Submitbtn);
        name = findViewById(R.id.nurse_name);

        test = findViewById(R.id.text_test);

        request.setText("");

        request_btn = findViewById(R.id.request_btn);

        request_trans = findViewById(R.id.request_image);

        Intent intent = getIntent();
        String username = getIntent().getStringExtra("message_key");

        SharedPreferences nurseName_user = getApplicationContext().getSharedPreferences("nurse_name", Context.MODE_PRIVATE);
        String nurseUserName = nurseName_user.getString("nurseName", "");

        SharedPreferences nurseID_user = getApplicationContext().getSharedPreferences("nurse_id", Context.MODE_PRIVATE);
        String nurseIDName = nurseID_user.getString("nurseID", "");

        SharedPreferences nurseSurname = getApplicationContext().getSharedPreferences("nurse_surname", Context.MODE_PRIVATE);
        String Surname = nurseSurname.getString("nurseSurname", "");

        name.setText(nurseUserName+" "+Surname);
        id.setText(nurseIDName);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nuresRequest = request.getText().toString();
                requestTitle = title.getText().toString();
                HashMap<String, Object> map = new HashMap<>();
                map.put("Employee_id",""+nurseIDName);
                map.put("Name",""+nurseUserName);
                map.put("Sname",""+Surname);
                map.put("Title",""+requestTitle);
                map.put("Request",""+nuresRequest);
                map.put("Date",""+dateTime);
                map.put("id",""+ requestTitle+"_"+dateTime);

                db.collection("Requests")
                        .document(""+ requestTitle+"_"+dateTime)
                        .set(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                showSuccessDialog();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent  = new Intent(getApplicationContext(), RequestActivity.class);

                                        startActivity(intent);
                                    }
                                }, 3000);
                            }
                        });

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RequestActivity.this,MainMenuActivity.class);

                startActivity(intent);
                finish();
            }
        });

    }

    private void showSuccessDialog(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.email_success);
        View view = LayoutInflater.from(RequestActivity.this).inflate(R.layout.email_success, errorConstraintLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}