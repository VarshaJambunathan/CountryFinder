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
    static final String TAG = CountryActivity.class.getName();

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

    private ArrayList<Country> loadCountries (final ArrayList<Country> countries) {
        progressBar.setVisibility(View.VISIBLE);

        //Creating a new StringRequest
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.INVISIBLE);

                        Log.e(TAG, response);

                        try {
                            JSONArray root = new JSONArray(response);

                            for(int i=0; i< root.length(); i++) {

                                JSONObject each_country = root.getJSONObject(i);

                                String name = each_country.getString("name");
                                String capital = each_country.getString("capital");
                                String alphacode = each_country.getString("alpha3Code");
                                String region = each_country.getString("region");
                                String subregion= each_country.getString("subregion");
                                long population = each_country.getLong("population");
                                String numericcode= each_country.getString("numericCode");
                                String nativename= each_country.getString("nativeName");
                                //String = each_country.getString("");

                                ArrayList<String> borders= new ArrayList<>();
                                JSONArray bordersArray = each_country.getJSONArray("borders");
                                for(int j=0; j< bordersArray.length(); j++) {

                                    String each_border = bordersArray.getString(j);
                                    //Log.e(TAG, each_border);
                                    borders.add(each_border);

                                }
                                //Log.e(TAG, name + " " + capital + " " + population);

                                JSONArray jsonLatLng = each_country.getJSONArray("latlng");
                                double lat = jsonLatLng.getDouble(0);
                                double lng = jsonLatLng.getDouble(1);

                                ArrayList<String> timezones = new ArrayList<>();
                                JSONArray timezoneArray = each_country.getJSONArray("timezones");
                                for(int j=0; j< timezoneArray.length(); j++) {

                                    String each_timezone = timezoneArray.getString(j);
                                    timezones.add(each_timezone);
                                }

                                ArrayList<String> languages = new ArrayList<>();
                                JSONArray languageArray = each_country.getJSONArray("languages");
                                for(int j=0; j< languageArray.length(); j++) {

                                    JSONObject jsonLanguage = languageArray.getJSONObject(j);
                                    String each_language = jsonLanguage.getString("name");
                                    languages.add(each_language);
                                }

                                String flag = each_country.getString("flag");

                                JSONArray currenciesArray = each_country.getJSONArray("currencies");
                                JSONObject jsonCur = currenciesArray.getJSONObject(0);
                                String cur_code = jsonCur.getString("code");
                                String cur_name = jsonCur.getString("name");
                                String cur_symbol = jsonCur.getString("symbol");

                                JSONArray ccArray = each_country.getJSONArray("callingCodes");
                                String callingcode = ccArray.getString(0);

                                Country country = new Country (name, capital, callingcode, alphacode, region, subregion,
                                        population, lat, lng, timezones, borders, nativename, numericcode, languages, flag,
                                        cur_code, cur_name, cur_symbol);

                                countries.add(country);

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
                        Log.e(TAG, error.getMessage());
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
