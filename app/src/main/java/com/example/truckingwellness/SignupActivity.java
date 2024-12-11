package com.example.truckingwellness;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.truckingwellness.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper databaseHelper;

    Button reset;

    ImageButton back;

    ImageView signup_image,  register_img;

    CardView signup_card , register_off ;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        databaseHelper = new DatabaseHelper(this);
        back = findViewById(R.id.Backbtn);
        sp = getSharedPreferences("user_email", Context.MODE_PRIVATE);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_email", Context.MODE_PRIVATE);
        String name = sp.getString("email", "");
        binding.signupEmail.setText(name);

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();

                signup_image = findViewById(R.id.signup_image);
                signup_card = findViewById(R.id.signup_card);

                register_img = findViewById(R.id.offline_img);
                register_off = findViewById(R.id.register_offline_btn);



                if (email.equals(name)) {
                    if (email.equals("") || password.equals("") || confirmPassword.equals(""))
                        Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    else {
                        if (password.equals(confirmPassword)) {
                            Boolean checkUserEmail = databaseHelper.checkEmail(email);
                            if (checkUserEmail == false) {
                                Boolean insert = databaseHelper.insertData(email, password);

                                if (insert == true) {
                                    Toast.makeText(SignupActivity.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainMenuActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(SignupActivity.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(SignupActivity.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignupActivity.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(SignupActivity.this, "Please enter the same email", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,OfflineOptions.class);



                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignupActivity.this);
                startActivity(intent,options.toBundle());
            }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(SignupActivity.this,OfflineOptions.class);

        startActivity(intent);
        finish();

    }

}