package com.example.firebaseextra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Button btn_sub;
    EditText et_user,et_pass,et_email,et_phone;
    Spinner spn_state,spn_country;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sub = findViewById(R.id.btnSubmit);
        et_user=findViewById(R.id.et_user);
        et_pass=findViewById(R.id.et_pass);
        et_email=findViewById(R.id.et_email);
        et_phone=findViewById(R.id.et_number);
        spn_state= findViewById(R.id.spn_state);
        spn_country= findViewById(R.id.spn_country);
        db = FirebaseFirestore.getInstance();

        btn_sub.setOnClickListener(v -> {
            String user,pass,email,phone;
            user=et_user.getText().toString();
            pass=et_pass.getText().toString();
            email=et_email.getText().toString();
            phone=et_phone.getText().toString();
            if(TextUtils.isEmpty(user)){
                et_user.setError("Please Enter Username");
            }else if(TextUtils.isEmpty(pass)){
                et_pass.setError("Please Enter Password");
            }else if(TextUtils.isEmpty(email)){
                et_email.setError("Please Enter Email");
            }else if(TextUtils.isEmpty(phone)){
                et_phone.setError("Please Enter Phone");
            } else {
                addDataToFirestore(user,pass,email,phone);
                /*Toast toast = Toast.makeText(getApplicationContext(), "Inserted Succesully", Toast.LENGTH_LONG);
                toast.show();*/
            }
        });
    }

    private void addDataToFirestore(String user,String pass,String email,String phone) {

        CollectionReference dbUsers = db.collection("Users");
        dataConnect dc = new dataConnect(user,pass,email,phone);
        dbUsers.add(dc).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(MainActivity.this, "Your User has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(MainActivity.this, "Fail to add User \n" + e, Toast.LENGTH_SHORT).show();
            }
        });

    }
}