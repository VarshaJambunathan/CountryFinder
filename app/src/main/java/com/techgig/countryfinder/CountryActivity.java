package com.techgig.countryfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.techgig.countryfinder.Adapters.CountryAdapter;
import com.techgig.countryfinder.Beans.Country;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    private ArrayList<Country> mCountryNames;
    private RecyclerView mCountryView;
    private RecyclerView.LayoutManager mCountryLayoutManager;
    private RecyclerView.Adapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mCountryNames = new ArrayList<>();
        mCountryNames.add(new Country("India", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("Israel", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("United States", "IN", "New Delhi", "Hindi"));

        //Country RecyclerView
        mCountryView = (RecyclerView) findViewById(R.id.country_view);
        mCountryView.setHasFixedSize(true);
        mCountryLayoutManager = new LinearLayoutManager(this);
        mCountryView.setLayoutManager(mCountryLayoutManager);
        mCountryAdapter =  new CountryAdapter(mCountryNames);
        mCountryView.setAdapter(mCountryAdapter);

        Log.e("SplashActivity", mCountryNames.get(1).getCapital());


    }
}
