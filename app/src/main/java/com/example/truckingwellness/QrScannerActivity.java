package com.example.truckingwellness;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputEditText;

public class QrScannerActivity extends AppCompatActivity {

    Spinner spinner;


    public static String testType;
   Button set;

    ImageView setup_trans;

    LottieAnimationView scan;

    CardView setup_btn;

    ImageButton back;


    Spinner location;

    SharedPreferences LastSelected;

    TextInputEditText scan_location ;
    TextView textLocation;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        back = findViewById(R.id.back_btn);
        spinner = findViewById(R.id.location_spinner);
        scan = findViewById(R.id.scanner);
        setup_btn = findViewById(R.id.scan_btn);
        setup_trans = findViewById(R.id.scan_image);
        location =  findViewById(R.id.location_spinner);
        scan_location = findViewById(R.id.editTextTextPersonName2);
        set = findViewById(R.id.set_btn);


        Intent intent = getIntent();
        String username = getIntent().getStringExtra("message_key");

        LastSelected = getSharedPreferences("Last_selected", Context.MODE_PRIVATE);

        ArrayAdapter<String> myAdpater = new ArrayAdapter<String>(QrScannerActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.location_array));

        myAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdpater);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = LastSelected.edit();
                Intent intent = new Intent(QrScannerActivity.this,MainMenuActivity.class);


                
                if (spinner.getSelectedItemPosition() != 0 ){

                    state = spinner.getSelectedItem().toString();
                    editor.putString("location", state);
                    editor.commit();

                    startActivity(intent);
                } else if (scan_location != null){
                    state = scan_location.getText().toString() ;
                    editor.putString("location", state);
                    editor.commit();

                    startActivity(intent);
                } if (scan_location == null && spinner.getSelectedItemPosition() == 0 ) {
                    Toast.makeText(QrScannerActivity.this, "Please Select A Location", Toast.LENGTH_SHORT).show();
                }
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.setSelection(0);
                Intent intent = new Intent(QrScannerActivity.this, ScannedBarcodeActivity.class);
                scanBarcodeLauncher.launch(intent);

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QrScannerActivity.this,MainMenuActivity.class);

                startActivity(intent);
                finish();

            }
        });
    }

    private ActivityResultLauncher<Intent> scanBarcodeLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null && data.hasExtra("intentData")) {
                        String intentData = data.getStringExtra("intentData");
//                        textLocation.setText(intentData);
                        scan_location.setText(intentData);
                        System.out.println(intentData);
                        Toast.makeText(QrScannerActivity.this, intentData,Toast.LENGTH_LONG).show();
                    }
                }
            });

}