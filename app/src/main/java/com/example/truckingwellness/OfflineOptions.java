package com.example.truckingwellness;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


public class OfflineOptions extends AppCompatActivity {

 CardView offline_register, offline_reset;

 ImageButton back;
 ImageView offline_trans , reset_img , register_img;

 CardView offline_card, register_off;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_options);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        String username = getIntent().getStringExtra("message_key");

        offline_register = (CardView) findViewById(R.id.register_offline_btn);
        offline_reset = (CardView)  findViewById(R.id.reset_password_btn);

        offline_card= findViewById(R.id.top_container);
        offline_trans = findViewById(R.id.offline_image);

        reset_img= findViewById(R.id.setup_img);
        back = findViewById(R.id.back_btn);

        register_img = findViewById(R.id.offline_img);
        register_off = findViewById(R.id.register_offline_btn);


        offline_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineOptions.this,SignupActivity.class);

                startActivity(intent);
                finish();
            }
        });

        offline_reset.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineOptions.this,PasswordActivity.class);

                startActivity(intent);
                finish();
            }
        }));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfflineOptions.this,MainMenuActivity.class);
                intent.putExtra("message_key",username);

                startActivity(intent);
                finish();


            }
        });

    }
}