package com.example.truckingwellness;

import android.app.AlertDialog;
import android.content.Intent;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ResetActivity extends AppCompatActivity {

    TextView loginEmail ;
    EditText pass, repass;
    Button btnconfirm;

    DatabaseHelper DB;

    ImageButton back;

    ImageView signup_trans;

    CardView signup_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

       loginEmail = (TextView)  findViewById(R.id.username_reset_text);
        pass = (EditText)  findViewById(R.id.password_reset);
        repass = (EditText)  findViewById(R.id.confirm_password_reset);
        btnconfirm = (Button)  findViewById(R.id.btn_confirm) ;
        DB = new DatabaseHelper(this);

        back = findViewById(R.id.Backbtn);

        signup_card= findViewById(R.id.signup_card);

        signup_trans = findViewById(R.id.signup_image);


        Intent intent = getIntent();

        String email = intent.getStringExtra("loginEmail");
        loginEmail.setText(email);

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString();
                String password = pass.getText().toString();
                String repassw = repass.getText().toString();

                if (password.equals( repassw)) {


                    Boolean checkpasswordupdate = DB.updatepassword(email, password);

                    if (checkpasswordupdate == true) {


                        showSuccessDialog();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                startActivity(intent);
                            }
                        }, 4000);
                    } else {
                        Toast.makeText(ResetActivity.this, "Password not updated", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ResetActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetActivity.this,PasswordActivity.class);

                startActivity(intent);
                finish();
            }
        });
    }


    private void showSuccessDialog(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.login_failed_alert);
        View view = LayoutInflater.from(ResetActivity.this).inflate(R.layout.login_password_reset_success, errorConstraintLayout);

        //Button errorClose = (Button) findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(ResetActivity.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();


        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}