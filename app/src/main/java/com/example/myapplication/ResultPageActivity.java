package com.example.myapplication;

import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityResultPageBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ResultPageActivity extends AppCompatActivity {

        private TextView textViewResult;
        private FirebaseFirestore db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result_page);

            textViewResult = findViewById(R.id.textview_result);
            db = MainActivity.getFirebaseInstance();

            String currentUserId = getIntent().getStringExtra("currentUserId");
            Log.d("TAG", currentUserId + "+++++++++++++++++++++++++++++++++++++++++++++++++++++");
            if (currentUserId != null) {
                DocumentReference userRef = db.collection("userdata").document(currentUserId);
                Log.d("TAG", currentUserId + "+++++++++++++++++++++++++++++++++++++++++++++++++++++");
                userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            double weight = documentSnapshot.getDouble("weight");
                            double height = documentSnapshot.getDouble("height");

                            double bmi = calculateBMI(weight, height);

                            String resultText = "Your BMI: " + bmi;
                            textViewResult.setText(resultText);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Error getting user document: ");
                    }
                });
            }
        }

        private double calculateBMI(double weight, double height) {
            double heightInMeter = height / 100.0; // Convert height to meters
            return weight / (heightInMeter * heightInMeter);
        }
    }