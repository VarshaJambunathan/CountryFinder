package com.techgig.countryfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techgig.countryfinder.Adapters.DetailsAdapter;
import com.techgig.countryfinder.Beans.Country;
import com.techgig.countryfinder.Beans.Names;
import com.techgig.countryfinder.CheckPermissions.CheckInternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getName();
    TextView mSample;
    ProgressDialog progressDialog;
    private RecyclerView mDetailsView;
    private RecyclerView.LayoutManager mDetailsLayoutManager;
    private RecyclerView.Adapter mDetailsAdapter;
    private ArrayList<Names> mDetailsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().hide();

        mSample = findViewById(R.id.sample);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . . ");
        progressDialog.setCancelable(false);

        mDetailsView = (RecyclerView) findViewById(R.id.details_view);
        mDetailsView.setHasFixedSize(true);
        mDetailsLayoutManager = new LinearLayoutManager(this);
        mDetailsView.setLayoutManager(mDetailsLayoutManager);

        Intent intent = getIntent();
        if(intent != null) {
            Names eachCountry = (Names) intent.getSerializableExtra("EachCountry");
            String name = eachCountry.getName();
            String data = eachCountry.getName() + " " + eachCountry.getFlagUrl();
            //Log.e(TAG, data);

            //Check internet permission
            if(CheckInternet.isNetworkAvailable(this)) {
                loadDetails(name);

            } else {
                Toast.makeText(this, "Internet Unavailable.", Toast.LENGTH_SHORT).show();

                //Read data from file
            }
        }

    }

    private void loadDetails(String key) {

        String URL = Config.EachCountryDetailsBaseUrl + key;
        //Creating a new StringRequest to get Names
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Log.e(TAG, response);
                        //mSample.setText(response);
                        countryResponse(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, error.getMessage());
                        progressDialog.dismiss();

                    }
                }
        );

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
        progressDialog.show();

    }

    private void countryResponse(String response) {

        try{
            JSONArray root = new JSONArray(response);

            JSONObject each_country = root.getJSONObject(0);

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

            mDetailsNames = Country.returnHashPairs(country);

            //Set DetailsAdapter
            mDetailsAdapter =  new DetailsAdapter(mDetailsNames);
            mDetailsView.setAdapter(mDetailsAdapter);

        } catch( JSONException e){
            Log.e("DetailsJsonParsing", e.toString());
        }

    }

}
