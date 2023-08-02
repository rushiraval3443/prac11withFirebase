package com.example.prac11withoutfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_user, et_pass;
    TextView tv_welcome;
    Button btn_login, btn_clear;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user = findViewById(R.id.et_username_login);
        et_pass = findViewById(R.id.et_pass_login);
        tv_welcome = findViewById(R.id.tv_welcome_login);
        btn_login = findViewById(R.id.btn_login_login);
        btn_clear = findViewById(R.id.btn_clear_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = et_user.getText().toString();
                pass = et_pass.getText().toString();
                if (user.equals("rushi")&&pass.equals("12345"))
                {
                    Intent i = new Intent(MainActivity.this, Welcome.class);
                    i.putExtra("user",user);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Username or Password",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}