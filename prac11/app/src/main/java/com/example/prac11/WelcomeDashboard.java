package com.example.prac11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class WelcomeDashboard extends AppCompatActivity {

    TextView tv_welcome;
    Button btnlogout;
    FirebaseAuth mAuth;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_dashboard);
        tv_welcome = findViewById(R.id.tv_welcome_dashboard);
        btnlogout = findViewById(R.id.btnlogout);

        mAuth = FirebaseAuth.getInstance();

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        //String mobile = i.getStringExtra("mobile");
        tv_welcome.setText("Welcome " + user + " On Successful Login.");
        btnlogout.setOnClickListener(v -> {
            mAuth.signOut();
            Intent i1 = new Intent(WelcomeDashboard.this, RegisterActivity.class);
            i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i1);
        });
    }
}