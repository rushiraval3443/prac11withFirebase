package com.example.prac11;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    TextView tv_account;
    EditText email, password, confirm;
    Button btn_register;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tv_account = findViewById(R.id.tv_account);
        email = findViewById(R.id.et_email_reg);
        password = findViewById(R.id.et_password_reg);
        confirm = findViewById(R.id.et_confirm_reg);
        btn_register = findViewById(R.id.btn_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        tv_account.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
        btn_register.setOnClickListener(v -> performAuth());
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            //if user pressed "yes", then he is allowed to exit from application
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            //if user select "No", just cancel this dialog and continue with app
            dialog.cancel();
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    private void performAuth() {
        String email1 = email.getText().toString();
        String pass1 = password.getText().toString();
        String confirm1 = confirm.getText().toString();

        if (pass1.isEmpty() || pass1.length() < 6) {
            password.setError("Enter min 6 char password");
        } else if (!pass1.equals(confirm1)) {
            confirm.setError("Both Passwords Don't match");
        } else {
            progressDialog.setMessage("Please Wait while Registration.....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email1, pass1).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    sendUserToNextActivity();
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}