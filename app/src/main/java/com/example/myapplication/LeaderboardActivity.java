package com.example.myapplication;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityLeaderboardBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaderboardActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private ListView listViewLeaderboard;
    private SimpleAdapter leaderboardAdapter;
    private List<Map<String, Object>> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
/*
        db = MainActivity.getFirebaseInstance();

        listViewLeaderboard = findViewById(R.id.listview_leaderboard);
        userList = new ArrayList<>();
        leaderboardAdapter = new SimpleAdapter(this, userList, android.R.layout.simple_list_item_2,
                new String[]{"username", "bmi"}, new int[]{android.R.id.text1, android.R.id.text2});
        listViewLeaderboard.setAdapter(leaderboardAdapter);
*/
        //loadLeaderboard();
    }
/*
    private void loadLeaderboard() {
        db.collection("userdata")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            userList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> userData = document.getData();
                                String username = (String) userData.get("username");
                                double weight = (double) userData.get("weight");
                                double height = (double) userData.get("height");
                                double bmi = calculateBMI(weight, height);
                                Map<String, Object> user = new HashMap<>();
                                user.put("username", username);
                                user.put("bmi", bmi);
                                userList.add(user);
                            }

                            // Sort the user list based on BMI
                            Collections.sort(userList, new Comparator<Map<String, Object>>() {
                                @Override
                                public int compare(Map<String, Object> user1, Map<String, Object> user2) {
                                    double bmi1 = (double) user1.get("bmi");
                                    double bmi2 = (double) user2.get("bmi");
                                    return Double.compare(bmi2, bmi1);
                                }
                            });

                            leaderboardAdapter.notifyDataSetChanged();
                        } else {
                            Log.d("debug", "Error getting userdata: ", task.getException());
                        }
                    }
                });*/
    //}

    /*private double calculateBMI(double weight, double height) {
        double heightInMeter = height / 100.0; // Convert height to meters
        return weight / (heightInMeter * heightInMeter);
    }*/
}


