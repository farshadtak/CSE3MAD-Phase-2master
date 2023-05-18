package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;
//import { doc, setDoc } from "firebase/firestore";


public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = null;
    String TAG = "find";

    Button b_login = null;
    Button b_signup = null;
    TextInputEditText username = null;
    TextInputEditText password = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        b_login = (Button) findViewById(R.id.b_login);
        b_signup = (Button) findViewById(R.id.b_signup);
        username = (TextInputEditText) findViewById(R.id.inp_username);
        password = (TextInputEditText) findViewById(R.id.inp_password);


//
        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
//

        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.d(TAG,  password.getText().toString()+ "   ---   " + username.getText());

                db.collection("users")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                                        Log.d(TAG, document.get("password").getClass() + "   ---   " + document.get("username").getClass());

                                        if (document.get("username").equals(username.getText().toString())) {
                                            Log.d(TAG, document.getId() + "   ---   " + "username +");
                                            if (document.get("password").equals(password.getText().toString())) {
                                                Log.d(TAG, document.getId() + "   ---   " + "password +");
                                            } else {
                                                Log.d(TAG, document.getId() + "   ---   " + "password -");
                                            }
                                        } else {
                                            Log.d(TAG, document.getId() + "   ---   " + "username -");
                                        }
                                    }
                                } else {
                                    Log.d(TAG, "Error getting doc. ", task.getException());
                                }
                            }
                        });
            }
        });


    }
}