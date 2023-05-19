package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    Button btnLeaderboard;
    Button btnInputPage;
    Button btnResultPage;
    String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Instantiate and initialize the buttons
        btnLeaderboard = findViewById(R.id.btn_leaderboard);
        btnInputPage = findViewById(R.id.btn_input);
        btnResultPage = findViewById(R.id.btn_result);


        currentUserId = getIntent().getStringExtra("userId");
        Log.d("TAG", currentUserId + "+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        // Set click listeners for the buttons
        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        btnInputPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, InputPageActivity.class);
                intent.putExtra("userId", currentUserId);
                startActivity(intent);
            }
        });

        btnResultPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", currentUserId + "-------------------------------------------------");
                Intent intent = new Intent(HomeActivity.this, ResultPageActivity.class);
                intent.putExtra("userId", currentUserId);
                startActivity(intent);
            }
        });
    }

    // Method to open the leaderboard activity
    public void openLeaderboard() {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }

    // Method to open the input page activity
    public void openInputPage() {
        Intent intent = new Intent(this, InputPageActivity.class);
        startActivity(intent);
    }

    // Method to open the result page activity
    public void openResultPage() {
        Intent intent = new Intent(this, ResultPageActivity.class);
        startActivity(intent);
    }
}