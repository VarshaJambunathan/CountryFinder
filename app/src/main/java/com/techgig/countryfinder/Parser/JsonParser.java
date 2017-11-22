package com.techgig.countryfinder.Parser;

import android.util.Log;
import android.widget.Toast;

import com.techgig.countryfinder.Beans.Country;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Varsha on 12-11-2017.
 */

public class JsonParser {

    public static void ParseCountry(String response) {

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

                //mCountryNames.add(country_layout);
                //mCountryAdapter.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            //Log.e(TAG, e.toString());
        }

    }
}
