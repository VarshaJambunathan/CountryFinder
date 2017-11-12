package com.techgig.countryfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techgig.countryfinder.Adapters.CountryAdapter;
import com.techgig.countryfinder.Beans.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    private ArrayList<Country> mCountryNames;
    private RecyclerView mCountryView;
    private RecyclerView.LayoutManager mCountryLayoutManager;
    private RecyclerView.Adapter mCountryAdapter;
    ProgressBar progressBar;
    public static final String JSON_URL = "https://restcountries.eu/rest/v2/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mCountryNames = new ArrayList<>();
      /*  mCountryNames.add(new Country("India", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("Israel", "IN", "New Delhi", "Hindi"));
        mCountryNames.add(new Country("United States", "IN", "New Delhi", "Hindi"));
*/
        //Country RecyclerView
        mCountryView = (RecyclerView) findViewById(R.id.country_view);
        mCountryView.setHasFixedSize(true);
        mCountryLayoutManager = new LinearLayoutManager(this);
        mCountryView.setLayoutManager(mCountryLayoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mCountryAdapter =  new CountryAdapter(mCountryNames);
        mCountryView.setAdapter(mCountryAdapter);

        //Check internet permission
        loadCountries(mCountryNames);
    }

    private ArrayList<Country> loadCountries (ArrayList<Country> countries) {
        progressBar.setVisibility(View.VISIBLE);

        //Creating a new StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        Log.e("CountryActivity", response);

                        try {
                            JSONArray root = new JSONArray(response);

                            for(int i=0; i< root.length(); i++) {

                                JSONObject each_country = root.getJSONObject(i);
                                String name = each_country.getString("name");

                                String capital = each_country.getString("capital");




                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("CountryActivity", error.getMessage());
                    }
                }
        );

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

        return countries;
    }
}
