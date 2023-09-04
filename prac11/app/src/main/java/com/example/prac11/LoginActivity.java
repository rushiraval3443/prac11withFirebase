package com.example.prac11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText et_user, et_pass;
    Button btn_login, btn_clear;
    TextView tv_otp;
    String user, pass;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user = findViewById(R.id.et_username_login);
        et_pass = findViewById(R.id.et_pass_login);
        btn_login = findViewById(R.id.btn_login_login);
        btn_clear = findViewById(R.id.btn_clear_login);
        tv_otp = findViewById(R.id.tv_otp);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btn_login.setOnClickListener(view -> performLogin());
        btn_clear.setOnClickListener(view -> {
            et_user.setText("");
            et_pass.setText("");
        });
        tv_otp.setOnClickListener(v -> sendToOtpActivity());
    }
    private void sendToOtpActivity() {
        startActivity(new Intent(LoginActivity.this,otp.class));
    }
    private void performLogin() {
        user = et_user.getText().toString();
        pass = et_pass.getText().toString();
        if (pass.isEmpty() || pass.length() < 6) {
            et_pass.setError("Enter min 6 char password");
        } else {
            progressDialog.setMessage("Please Wait while Login.....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent i = new Intent(LoginActivity.this, WelcomeDashboard.class);
        String uname = user.replace("@gmail.com", "");
        i.putExtra("user", uname);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}