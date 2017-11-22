package com.techgig.countryfinder;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.techgig.countryfinder.Adapters.CountryAdapter;
import com.techgig.countryfinder.Beans.Country;
import com.techgig.countryfinder.Beans.Names;
import com.techgig.countryfinder.CheckPermissions.CheckInternet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    static ArrayList<Country> mCountryNames;
    static ArrayList<Names> mNames;
    private RecyclerView mCountryView;
    private RecyclerView.LayoutManager mCountryLayoutManager;
    static RecyclerView.Adapter mCountryAdapter;
    static final String TAG = CountryActivity.class.getName();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        mCountryNames = new ArrayList<>();
        mNames = new ArrayList<>();

        //Country RecyclerView
        mCountryView = (RecyclerView) findViewById(R.id.country_view);
        mCountryView.setHasFixedSize(true);
        mCountryLayoutManager = new LinearLayoutManager(this);
        mCountryView.setLayoutManager(mCountryLayoutManager);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . .");

        //Check internet permission
        if(CheckInternet.isNetworkAvailable(this)) {
            loadCountries();
            mCountryAdapter =  new CountryAdapter(this, mNames);
            mCountryView.setAdapter(mCountryAdapter);

        } else {
            Toast.makeText(this, "Internet Unavailable.", Toast.LENGTH_SHORT).show();

            //Read data from file
        }

    }

    private void loadCountries () {

        //Creating a new StringRequest to get Names
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.CountryNameUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        Log.e(TAG, response);
                        mNames = ParseCountryNames(response);
                        mCountryAdapter.notifyDataSetChanged();

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

    private static ArrayList<Names> ParseCountryNames(String response) {

        try {

            JSONArray root = new JSONArray(response);

            for(int i=0; i< root.length(); i++) {

                JSONObject each_country = root.getJSONObject(i);

                String name = each_country.getString("name");
                String flagString = each_country.getString("flag");

                Names each_name = new Names(name, flagString);
                mNames.add(each_name);
                mCountryAdapter.notifyDataSetChanged();
            }

        } catch(JSONException e){
            Log.e("JSONException:Names", e.toString());
        }

        return mNames;

    }

}
