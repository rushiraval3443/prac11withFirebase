package com.example.prac11withoutfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    TextView tv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv_welcome = findViewById(R.id.tv_welcome_dashboard);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        tv_welcome.setText("Welcome "+user+" On Successfull Login.");
    }
}