package com.techgig.countryfinder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getName();
    TextView mSample;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().hide();

        mSample = findViewById(R.id.sample);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading . . . ");

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
                        mSample.setText(response);

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
}
