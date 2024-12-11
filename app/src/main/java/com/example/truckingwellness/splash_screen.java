package com.example.truckingwellness;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2000;
    Animation topanimation , botanimation;

    ImageView nbcrfi_logo, trucking_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setExitTransition(null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        topanimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botanimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        nbcrfi_logo = findViewById(R.id.nbcrfi_logo);
        trucking_logo = findViewById(R.id.trucking_logo);

       nbcrfi_logo.setAnimation(topanimation);
        trucking_logo.setAnimation(botanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash_screen.this, MainActivity.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(trucking_logo, "trucking_transition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splash_screen.this,pairs);
                startActivity(intent,options.toBundle());

            }
        }, SPLASH_SCREEN);
    }
}