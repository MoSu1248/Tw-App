package com.example.truckingwellness;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class ClientSearch extends AppCompatActivity {

    EditText searchInput ;
    Button searchButton;
    RecyclerView recyclerView;

    ImageButton back;
    ImageView client_img;
    CardView client_trans;

    SearchUserRecyclerAdapter adapter;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_search);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        client_img = findViewById(R.id.client_image);
        client_trans = findViewById(R.id.client_card);
        searchInput = findViewById(R.id.seach_username_input);
        searchButton = findViewById(R.id.search_user_btn2);
        back = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        searchInput.requestFocus();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = searchInput.getText().toString();
                if (searchTerm.isEmpty() || searchTerm.length() <7 ){
                    searchInput.setError("Invalid Username");
                    return;
                } else {
                    setupSearchRecyclerView(searchTerm);

                }
            }
        });


        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    String searchTerm = searchInput.getText().toString();
                    if (searchTerm.isEmpty() || searchTerm.length() < 7 ){
                        searchInput.setError("Invalid Username");

                    } else {                     setupSearchRecyclerView(searchTerm);
                    }
                }
                return false;
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientSearch.this,MainMenuActivity.class);

                startActivity(intent);
                finish();
            }
        });
        if (!isConnected()) {

            showOfflineDialogue();
        }

    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();

    }

    private void setupSearchRecyclerView(String searchTerm) {

        Query query = FirebaseUtil.allUserCollectionReference()
                .whereEqualTo("clientID" , searchTerm);

        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class).build();


        adapter = new SearchUserRecyclerAdapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void showOfflineDialogue(){

        ConstraintLayout errorConstraintLayout = findViewById(R.id.offline_notification);
        View view = LayoutInflater.from(ClientSearch.this).inflate(R.layout.offline_notification, errorConstraintLayout);

        //Button errorClose = (Button) findViewById(R.id.errorClose);

        AlertDialog.Builder builder = new AlertDialog.Builder(ClientSearch.this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        if(alertDialog.getWindow()!= null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(ClientSearch.this,MainMenuActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(ClientSearch.this,MainMenuActivity.class);

        startActivity(intent);
        finish();

    }
}