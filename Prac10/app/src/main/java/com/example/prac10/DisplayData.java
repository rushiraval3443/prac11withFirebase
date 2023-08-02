package com.example.prac10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {
    TextView tv_user, tv_pass, tv_email, tv_phone, tv_state, tv_country;
    String user, pass, email, phone, state, country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        tv_user = findViewById(R.id.tv_user);
        tv_pass = findViewById(R.id.tv_pass);
        tv_email = findViewById(R.id.tv_email);
        tv_phone = findViewById(R.id.tv_phone);
        tv_state = findViewById(R.id.tv_state);
        tv_country = findViewById(R.id.tv_country);
        user = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");
        phone = getIntent().getStringExtra("phone");
        state = getIntent().getStringExtra("state");
        country = getIntent().getStringExtra("country");
        tv_user.setText(user);
        tv_pass.setText(pass);
        tv_email.setText(email);
        tv_phone.setText(phone);
        tv_state.setText(state);
        tv_country.setText(country);
    }
}