package com.example.truckingwellness;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String userName = "", pin = "";
    Button loginbtn2, registerUser;
    TextView forgot_pin;

    ImageButton onlineBtn, onlineBtn_2;
    //ActivityLoginBinding binding;
    Button loginButton;

    ImageView forgot_container;

    CheckBox mCheckBox, mCheckBox2;

    SharedPreferences sp, loginCount , nurseidt;
    private FirebaseAuth auth;
    DatabaseHelper databaseHelper;

    Date curDate2 = Calendar.getInstance().getTime();

    EditText text;

    DatabaseHelper DB;

    Context context;

    ScrollView mscroll;

    private long mLastClickTime = 0;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Date d=new Date();
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy, h:mm a");
    String dateTime2 = sdf.format(d);

    Date currentTime = Calendar.getInstance().getTime();
    String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
    String month_name = month_date.format(cal.getTime());
    String [] splitDate = formattedDate.split(",");

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main2);

        databaseHelper = new DatabaseHelper(this);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        forgot_pin = findViewById(R.id.forgot_pin);
        loginbtn2 = findViewById(R.id.loginbtn2);

        forgot_container = findViewById(R.id.imageView);
        mCheckBox = findViewById(R.id.checkBox);
        mCheckBox2 = findViewById(R.id.checkBox2);
        mscroll = findViewById(R.id.main_menu_scroll);
        text = findViewById(R.id.username);

        Calendar cal=Calendar.getInstance();

//        System.out.println("Current Date:"+cal.get(Calendar.DATE));

        Calendar calendar = Calendar.getInstance();
        int currentDay= calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS" , 0 );
        int lasDay = settings.getInt("day" , 0);

        auth = FirebaseAuth.getInstance();

        sp = getSharedPreferences("user_email", Context.MODE_PRIVATE);

        nurseidt = getSharedPreferences("nurseidt" , Context.MODE_PRIVATE);

        loginCount = getSharedPreferences("counter", Context.MODE_PRIVATE);

        loginButton = findViewById(R.id.loginButton);

        TextView email1 = (TextView) findViewById(R.id.login_email);
        TextView password1 = (TextView) findViewById(R.id.login_password);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(null);
                } else {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        mCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password1.setTransformationMethod(null);
                } else {
                    password1.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        loginbtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String txt_email, txt_pin;

                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                txt_email = username.getText().toString();
                txt_pin = password.getText().toString();
                if (txt_email.isEmpty() || txt_pin.isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else if (!txt_email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()) {
                    loginUser(txt_email, txt_pin);

                } else {
                    showErrorDialog();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password;

                email = email1.getText().toString();
                password = password1.getText().toString();

                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000){
                    return;
                }

                mLastClickTime = SystemClock.elapsedRealtime();

                    if (email.equals("") || password.equals(""))
                        Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    else {
                        Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

                        if (checkCredentials == true) {
                            showSuccessDialog();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    SharedPreferences.Editor editor = sp.edit();
                                    SharedPreferences.Editor editor3 = loginCount.edit();

                                    editor.putString("email", email);
                                    editor3.putString("counter", dateTime2);

                                    editor.commit();
                                    editor3.commit();


                                    HashMap<String, Object> map = new HashMap<>();
                                    map.put("Login Time", "" + dateTime2);
                                    db.collection("Nurses")
                                            .document(email +"/Login /"+splitDate[2]+"/"+month_name+"/"+cal.get(Calendar.DATE)+"/"+ "LoginTimes"+"/" + dateTime2  )
                                            .set(map)

                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                }
                                            });

                                    HashMap<String, Object> map2 = new HashMap<>();
                                    map2.put("Year", " " + splitDate[2]);

                                    db.collection("Nurses")
                                            .document(email)
                                            .update("Status", "Online")
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


                                    if (lasDay != currentDay) {
                                        SharedPreferences.Editor editor2 = settings.edit();
                                        editor2.putInt("day", currentDay);
                                        editor2.commit();

                                        //Run the code
                                        Intent intent = new Intent(getApplicationContext(), QrScannerActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                        startActivity(intent);
                                    }

                                }
                            }, 3000);
                        } else {
                            showErrorDialog();

                        }
                    }
                }

        });

        forgot_pin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);

                startActivity(intent);
                finish();
            }
        }));

        if (!isConnected()) {
            showOfflineDialogue();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    mscroll.fullScroll(ScrollView.FOCUS_DOWN);
                    email1.requestFocus();

                }
            };
            mscroll.post(runnable);
        }

    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    public void headsUpNotification(View view) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, Utils.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(Utils.NOTI_TITLE)
                .setContentText(Utils.NOTI_DESC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(Utils.NOTI_ID, builder.build());

    }

    private void loginUser(String txt_email, String txt_pin) {
        auth.signInWithEmailAndPassword(txt_email, txt_pin).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

            Calendar calendar = Calendar.getInstance();
            int currentDay= calendar.get(Calendar.DAY_OF_MONTH);
            SharedPreferences settings = getSharedPreferences("PREFS" , 0 );
            int lasDay = settings.getInt("day" , 0);

            public void onSuccess(AuthResult authResult) {
                showSuccessDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                            SharedPreferences.Editor editor = sp.edit();

                            editor.putString("email" , txt_email);
                            editor.commit();

                        SharedPreferences.Editor editor3 = loginCount.edit();

                        editor3.putString("counter", dateTime2);
                        editor.commit();
                        editor3.commit();

                        HashMap<String, Object> map = new HashMap<>();
                                map.put("Login",""+dateTime2);
                                map.put("Logout",""+"--");

                                db.collection("Nurses")
                                        .document(txt_email+"/Login /"+splitDate[2]+"/"+month_name+"/"+cal.get(Calendar.DATE)+"/"+ "LoginTimes"+"/" + dateTime2 )
                                        .set(map )

                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                            }
                                        });

                        HashMap<String, Object> map2 = new HashMap<>();
                        map2.put("Year", " " + splitDate[2]);
                        db.collection("Nurses").document(txt_email)
                                .update("Status", "Online")
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

                        if (lasDay != currentDay){
                            SharedPreferences.Editor editor2 = settings.edit();
                            editor2.putInt("day" , currentDay);
                            editor2.commit();

                            //Run the code
                            Intent intent = new Intent(getApplicationContext(), QrScannerActivity.class);
                            startActivity(intent);

                        }else {
                            Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                            startActivity(intent);
                        }
                    }
                }, 3000);
            }

        });
        auth.signInWithEmailAndPassword(txt_email, txt_pin).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showErrorDialog();

                    }
                }, 3000);
            }
        });

    }

    private void showErrorDialog(){
        ConstraintLayout errorConstraintLayout = findViewById(R.id.login_failed_alert);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.login_alert, errorConstraintLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    private void showSuccessDialog(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.login_success);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.login_success, errorConstraintLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    private void showOfflineDialogue(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.offline_notification);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.offline_notification, errorConstraintLayout);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }


    
}

