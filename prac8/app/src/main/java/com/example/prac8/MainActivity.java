package com.example.prac8;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_sub, btn_mul, btn_div;
    EditText et_num1, et_num2;
    TextView tv_output;
    float a, b, c;
    private FirebaseAuth mAuth;
    @SuppressLint("SetTextI18n")
    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        btn_add = findViewById(R.id.btn_add_main);
        btn_sub = findViewById(R.id.btn_sub_main);
        btn_mul = findViewById(R.id.btn_mul_main);
        btn_div = findViewById(R.id.btn_div_main);
        et_num1 = findViewById(R.id.et_num1_main);
        et_num2 = findViewById(R.id.et_num2_main);
        tv_output = findViewById(R.id.tv_output_main);
        btn_add.setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_num1.getText()) || TextUtils.isEmpty(et_num2.getText())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_add.getWindowToken(), 0);
                Toast toast = Toast.makeText(getApplicationContext(), "Numbers cannot be Empty", Toast.LENGTH_LONG);
                toast.show();
            } else {
                a = Float.parseFloat(et_num1.getText().toString());
                b = Float.parseFloat(et_num2.getText().toString());
                c = a + b;
                tv_output.setText("Addition Is: " + c);
                et_num1.setText("");
                et_num2.setText("");
                et_num1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_add.getWindowToken(), 0);
            }
        });
        btn_sub.setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_num1.getText()) || TextUtils.isEmpty(et_num2.getText())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_add.getWindowToken(), 0);
                Toast toast = Toast.makeText(getApplicationContext(), "Numbers cannot be Empty", Toast.LENGTH_LONG);
                toast.show();
            } else {
                a = Integer.parseInt(et_num1.getText().toString());
                b = Integer.parseInt(et_num2.getText().toString());
                c = a - b;
                tv_output.setText("Subtraction Is: " + c);
                et_num1.setText("");
                et_num2.setText("");
                et_num1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_sub.getWindowToken(), 0);
            }
        });
        btn_mul.setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_num1.getText()) || TextUtils.isEmpty(et_num2.getText())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_add.getWindowToken(), 0);
                Toast toast = Toast.makeText(getApplicationContext(), "Numbers cannot be Empty", Toast.LENGTH_LONG);
                toast.show();
            } else {
                a = Integer.parseInt(et_num1.getText().toString());
                b = Integer.parseInt(et_num2.getText().toString());
                c = a * b;
                tv_output.setText("Multiplication Is: " + c);
                et_num1.setText("");
                et_num2.setText("");
                et_num1.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_mul.getWindowToken(), 0);
            }
        });

        btn_div.setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_num1.getText()) || TextUtils.isEmpty(et_num2.getText())) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(btn_add.getWindowToken(), 0);
                Toast toast = Toast.makeText(getApplicationContext(), "Numbers cannot be Empty", Toast.LENGTH_LONG);
                toast.show();
            } else {
                a = Integer.parseInt(et_num1.getText().toString());
                b = Integer.parseInt(et_num2.getText().toString());
                if (b == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Number two cannot be 0", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    c = a / b;
                    tv_output.setText("Division Is: " + c);
                    et_num1.setText("");
                    et_num2.setText("");
                    et_num1.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(btn_div.getWindowToken(), 0);
                }
            }
        });
    }
}