package com.example.prac10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_sub;
    EditText et_user, et_pass, et_email, et_phone;
    Spinner spn_state, spn_country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sub = findViewById(R.id.btnSubmit);
        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_number);
        spn_state = findViewById(R.id.spn_state);
        spn_country = findViewById(R.id.spn_country);

        btn_sub.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, DisplayData.class);
            i.putExtra("username", et_user.getText().toString());
            i.putExtra("pass", et_pass.getText().toString());
            i.putExtra("email", et_email.getText().toString());
            i.putExtra("phone", et_phone.getText().toString());
            i.putExtra("state", spn_state.getSelectedItem().toString());
            i.putExtra("country", spn_country.getSelectedItem().toString());
            startActivity(i);
        });
    }
}