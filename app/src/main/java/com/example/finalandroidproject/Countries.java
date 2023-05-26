package com.example.finalandroidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Countries extends AppCompatActivity {
    ListView countries_lv;
    private CountryAdapter countryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        Intent i = this.getIntent();
        ArrayList<Country> carList = i.getParcelableArrayListExtra("list");

        countries_lv = findViewById(R.id.countries_lv);
        countryAdapter = new CountryAdapter(carList, getApplicationContext());
        countries_lv.setAdapter(countryAdapter);
    }
}
