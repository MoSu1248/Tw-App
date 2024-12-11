package com.example.truckingwellness;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainMenuActivity extends AppCompatActivity {

    CardView quiz_btn, logout_btn, offline_card, setup_btn, request_btn, client_trans;
    ImageView request_trans, offline_img, setup_trans, client_img;
    TextView offline_txt, edit_settings, textViewData, email_text, text_location, header_name;
    SharedPreferences sp, nurse_name, nurse_id , nurse_surname;

    SharedPreferences.Editor editor;
    Button endYes , endNo ;
    Date curDate = Calendar.getInstance().getTime();
    String dateTime = DateFormat.getDateInstance(DateFormat.FULL).format(curDate);
    String[] splitDate = dateTime.split(",");
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
    String month_name = month_date.format(cal.getTime());
    Date curDate2 = Calendar.getInstance().getTime();
    Date d=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy, h:mm a");
    String dateTime3 = sdf.format(d);
    Dialog dialog ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setEnterTransition(null);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_menu);

        logout_btn = findViewById(R.id.logout_btn);
        quiz_btn = findViewById(R.id.quiz_btn);
        offline_card = findViewById(R.id.offline_card);
        setup_btn = findViewById(R.id.setup_btn);
        request_btn = findViewById(R.id.request_btn);
        offline_txt = findViewById(R.id.offline_txt);
        offline_img = findViewById(R.id.offline_img);
        setup_btn = findViewById(R.id.setup_btn);
        setup_trans = findViewById(R.id.setup_img);
        edit_settings = findViewById(R.id.edit_settings_text);
        textViewData = findViewById(R.id.text_view_data);
        email_text = findViewById(R.id.email_text);
        text_location = findViewById(R.id.text_location);
        header_name = findViewById(R.id.header_name);

        client_img = findViewById(R.id.client_image);
        client_trans = findViewById(R.id.client_card);

        dialog = new Dialog(MainMenuActivity.this);
        dialog.setContentView(R.layout.logout_noti);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        endNo = dialog.findViewById(R.id.endNo);
        endYes = dialog.findViewById(R.id.endYes);

        endNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Calendar calender = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calender.getTime());

        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        request_trans = findViewById(R.id.request_image);

        sp = getSharedPreferences("user_email", Context.MODE_PRIVATE);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_email", Context.MODE_PRIVATE);
        String name = sp.getString("email", "");

        SharedPreferences LastSelected = getApplication().getSharedPreferences("Last_selected", Context.MODE_PRIVATE);
        String location = LastSelected.getString("location", "");

        SharedPreferences  Login_counter = getApplication().getSharedPreferences("counter", Context.MODE_PRIVATE);
        String loginCount = Login_counter.getString("counter", "");

        text_location.setText(location);

        nurse_name = getSharedPreferences("nurse_name", Context.MODE_PRIVATE);

        nurse_id = getSharedPreferences("nurse_id", Context.MODE_PRIVATE);
        nurse_surname = getSharedPreferences("nurse_surname", Context.MODE_PRIVATE);


        DocumentReference docRef = db.collection("Nurses").document(name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        document.getString("Name");
                        document.getString("NurseID");
                        document.getString("Surname");

                        textViewData.setText(document.getString("Name"));
                        email_text.setText(document.getString("NurseID"));
                        header_name.setText(document.getString("Name"));

                        SharedPreferences.Editor editor = nurse_name.edit();
                        SharedPreferences.Editor editor1 = nurse_id.edit();
                        SharedPreferences.Editor editor2 = nurse_surname.edit();

                        editor.putString("nurseName", document.getString("Name"));
                        editor1.putString("nurseID", document.getString("NurseID"));
                        editor2.putString("nurseSurname", document.getString("Surname"));

                        editor.commit();
                        editor1.commit();
                        editor2.commit();

                    } else {
                    }
                } else {
                }
            }
        });


        edit_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, QrScannerActivity.class);


                startActivity(intent);
                finish();
            }
        });

        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, TestTypeActivity.class);

                startActivity(intent);
                finish();
            }
        });

        client_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, ClientSearch.class);

                startActivity(intent);
                finish();

            }
        });

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        endYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainMenuActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                SharedPreferences  Login_counter = getApplication().getSharedPreferences("counter", Context.MODE_PRIVATE);
                String loginCount = Login_counter.getString("counter", "");
                HashMap<String, Object> map = new HashMap<>();
                map.put("Logout",dateTime3);
                db.collection("Nurses")
                        .document(name +"/Login /"+splitDate[2]+"/"+month_name+"/"+cal.get(Calendar.DATE)+"/"+ "LoginTimes"+"/" + loginCount )
                        .update(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                }, 3000);
                            }
                        });

                db.collection("Nurses")
                        .document(name)
                        .update(
                                "Status", "Offline",
                                "lastLogin", dateTime3
                        )
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                }, 3000);
                            }
                        });

                dialog.dismiss();
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
                finish();
            }
        });

        offline_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, OfflineOptions.class);

                startActivity(intent);
                finish();
            }
        });

        setup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, QrScannerActivity.class);

                startActivity(intent);
                finish();
            }
        });

        request_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainMenuActivity.this, RequestActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        dialog.show();
    }
    protected void onStop(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String name = sp.getString("email", "");
        SharedPreferences  Login_counter = getApplication().getSharedPreferences("counter", Context.MODE_PRIVATE);
        String loginCount = Login_counter.getString("counter", "");
        FirebaseAuth.getInstance().signOut();

        HashMap<String, Object> map = new HashMap<>();
        map.put("Logout",dateTime3);
        db.collection("Nurses")
                .document(name +"/Login /"+splitDate[2]+"/"+month_name+"/"+cal.get(Calendar.DATE)+"/"+ "LoginTimes"+"/" + loginCount  )
                .update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 3000);
                    }
                });
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("Year", " " + splitDate[2]);
        db.collection("Nurses")
                .document(name)
                .update(
                        "Status", "Offline",
                        "lastLogin", dateTime3
                )
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 3000);
                    }
                });
        super.onStop(); }

    }




