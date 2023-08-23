package com.example.prac10;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
    private void populateSpinner()
    {
        try {
            JSONArray jsonCountryArray = new JSONObject(loadJSONFromAsset()).optJSONArray("country");

            ArrayList<String> countryList = new ArrayList<>();

            for (int i = 0; i < jsonCountryArray.length(); i++) {
                countryList.add(jsonCountryArray.optJSONObject(i).optString("name"));
            }

            ArrayAdapter<String> countryListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryList);

            spn_country.setAdapter(countryListAdapter);

            spn_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ArrayList<String> stateArray = new ArrayList<>();

                    final JSONArray jsonStateArray = jsonCountryArray.optJSONObject(position).optJSONArray("state");

                    for (int i = 0; i < jsonStateArray.length(); i++) {
                        stateArray.add(jsonStateArray.optJSONObject(i).optString("name"));
                    }

                    ArrayAdapter<String> stateListAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, stateArray);

                    spn_state.setAdapter(stateListAdapter);

                    spn_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            final ArrayList<String> cityArray = new ArrayList<>();

                            final JSONArray jsonCityArray = jsonStateArray.optJSONObject(position).optJSONArray("city");

                            for (int i = 0; i < jsonCityArray.length(); i++) {
                                cityArray.add(jsonCityArray.optJSONObject(i).optString("name"));
                            }

                            ArrayAdapter<String> cityListAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, cityArray);

                            //citySpinner.setAdapter(cityListAdapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "error=" + e.getMessage());
        }
    }
    public void submit(View view) {
        int countryPosition = spn_country.getSelectedItemPosition();
        int statePosition = spn_state.getSelectedItemPosition();
        //int cityPosition = citySpinner.getSelectedItemPosition();

        JSONArray jsonCountryArray = null;
        int countryId = jsonCountryArray
                .optJSONObject(countryPosition)
                .optInt("id");

        int stateId = jsonCountryArray
                .optJSONObject(countryPosition)
                .optJSONArray("state")
                .optJSONObject(statePosition)
                .optInt("id");

        /*int cityId = jsonCountryArray
                .optJSONObject(countryPosition)
                .optJSONArray("state")
                .optJSONObject(statePosition)
                .optJSONArray("city")
                .optJSONObject(cityPosition)
                .optInt("id");*/

        try {
            JSONObject result = new JSONObject()
                    .put("countryId", countryId)
                    .put("stateId", stateId);
                    /*.put("cityId", cityId);*/

            Log.d(TAG, "result=" + result);
        } catch (JSONException e) {
            Log.e(TAG, "error=" + e.getMessage());
            e.printStackTrace();
        }
    }
    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getAssets().open("country.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}