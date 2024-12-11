package com.example.truckingwellness;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PasswordActivity extends AppCompatActivity {

    EditText username;
    Button reset;
    DatabaseHelper DB;
    ImageButton back;
    ImageView signup_trans;
    CardView signup_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = (EditText) findViewById(R.id.username_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DatabaseHelper(this) ;

        back = findViewById(R.id.Backbtn);

        signup_card= findViewById(R.id.signup_card);

        signup_trans = findViewById(R.id.signup_image);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = username.getText().toString();
                Boolean checkuser = DB.checkusername(email) ;
                if (checkuser == true){
                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("loginEmail", email);
                    startActivity(intent);
                }else
                {
                    Toast.makeText(PasswordActivity.this, "User does not Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PasswordActivity.this,OfflineOptions.class);

                startActivity(intent);
                finish();
            }
        });
    }
}