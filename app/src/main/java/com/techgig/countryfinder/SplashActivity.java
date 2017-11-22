package com.techgig.countryfinder;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity
{

    private static int SPLASH_TIME_OUT = 750;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app register activity for a new user
                Intent login = new Intent(SplashActivity.this, CountryActivity.class);
                startActivity(login);

                //Else handle the cookies/preferences to navigate to app main activity
                //Code here!

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
