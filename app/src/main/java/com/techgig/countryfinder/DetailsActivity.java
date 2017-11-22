package com.techgig.countryfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.techgig.countryfinder.Beans.Country;
import com.techgig.countryfinder.Beans.Names;

public class DetailsActivity extends AppCompatActivity {

    TextView mSampleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mSampleName = (TextView) findViewById(R.id.sampleText);

        Intent intent = getIntent();
        if(intent != null) {
            Names eachCountry = (Names) intent.getSerializableExtra("EachCountry");
            String data = eachCountry.getName() + " " + eachCountry.getFlagUrl();
            mSampleName.setText(data);
        }
    }
}
