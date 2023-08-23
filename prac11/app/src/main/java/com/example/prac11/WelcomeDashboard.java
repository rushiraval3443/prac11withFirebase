package com.example.prac11;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class WelcomeDashboard extends AppCompatActivity {

    TextView tv_welcome;
    /*String fname, lname;
    EditText et_firstname, et_lname;*/
    Button btnlogout, btnsubmit;
    FirebaseAuth mAuth;
    //DatabaseReference databaseReference;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_dashboard);
        tv_welcome = findViewById(R.id.tv_welcome_dashboard);
        btnlogout = findViewById(R.id.btnlogout);
        /*et_firstname = findViewById(R.id.et_fname);
        et_lname = findViewById(R.id.et_sname);
        btnsubmit = findViewById(R.id.btn_submit_welcome);*/
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
        /*btnsubmit.setOnClickListener(v -> {
            fname = et_firstname.getText().toString();
            lname = et_lname.getText().toString();
            if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname)) {
                Toast.makeText(WelcomeDashboard.this, "Please add some data.", Toast.LENGTH_SHORT).show();
            } else {
                // else call the method to add
                // data to our database.
                addDatatoFirebase(fname, lname);
            }


        });*/
    }

    private void addDatatoFirebase(String fname, String lname) {
        // below 3 lines of code is used to set
        // data in our object class.
        /*UserInfo.setFname(fname);
        UserInfo.setLname(lname);*/

        // we are use add value event listener method
        // which is called with database reference.
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(UserInfo);

                // after adding this data we are showing toast message.
                Toast.makeText(WelcomeDashboard.this, "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(WelcomeDashboard.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}