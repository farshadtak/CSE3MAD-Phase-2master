package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class InputPageActivity extends AppCompatActivity {

    private EditText etHeight;
    private EditText etWeight;
    private FirebaseFirestore db;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_page);

        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);

        db = MainActivity.getFirebaseInstance();

        // Get the current user ID passed from MainActivity
        currentUserId = getIntent().getStringExtra("userId");
        Log.d("TAG", currentUserId + "+++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    public void saveUserData(View view) {
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();

        if (height.isEmpty() || weight.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new userdata document
        Map<String, Object> userData = new HashMap<>();
        userData.put("height", height);
        userData.put("weight", weight);
        Log.d("heree", db.collection("users").document(currentUserId).getId() + "+++++++++++++++++++++++++++++++++++++++++++++++++++++");
        userData.put("userRef", db.collection("users").document(currentUserId).getId());

        db.collection("userdata")
                .add(userData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(InputPageActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                        // Clear the input fields after saving
                        etHeight.setText("");
                        etWeight.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InputPageActivity.this, "Failed to save userdata", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}