package com.example.matrimonialapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.matrimonialapplication.fragments.Chats;
import com.example.matrimonialapplication.fragments.Dashboard;
import com.example.matrimonialapplication.fragments.Profile;
import com.example.matrimonialapplication.fragments.Request;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    Dashboard dashboardFragment = new Dashboard();
    Profile expenseFragment = new Profile();
    Chats incomeFragment = new Chats();
    Request requestFragment = new Request();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.request:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, requestFragment).commit();
                return true;
            case R.id.chats:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, incomeFragment).commit();
                return true;

            case R.id.dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, dashboardFragment).commit();
                return true;

            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, expenseFragment).commit();
                return true;
        }
        return false;
    }

}