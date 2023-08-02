package com.example.prac11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        EditText inputMobile = findViewById(R.id.inputMobile);
        Button getOTP = findViewById(R.id.btn_getOTP);
        getOTP.setOnClickListener(v -> {
            if(inputMobile.getText().toString().trim().isEmpty())
            {
                Toast.makeText(otp.this,"Enter Mobile number",Toast.LENGTH_LONG).show();
                return;
            }
            else if(inputMobile.getText().toString().length()<=9)
            {
                inputMobile.setError("Enter 10 Digit Mobile number");
                return;
            }

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + inputMobile.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    otp.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            Toast.makeText(otp.this,"Verification Completed",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(otp.this,e.getMessage(),Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            //super.onCodeSent(s, forceResendingToken);
                            Intent i = new Intent(otp.this, VerifyOtp.class);
                            i.putExtra("mobile",inputMobile.getText().toString());
                            i.putExtra("verificationID", verificationID);
                            startActivity(i);
                        }
                    }
            );
        });
    }
}