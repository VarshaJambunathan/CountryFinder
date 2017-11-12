package com.techgig.countryfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.techgig.countryfinder.Beans.Country;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    ArrayList<Country> mCountryNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mCountryNames = new ArrayList<>();
        mCountryNames.add(new Country("India", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("India", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("India", "IN", "New Delhi", "Hindi"));

        Log.e("SplashActivity", mCountryNames.get(1).getCapital());


    }
}
