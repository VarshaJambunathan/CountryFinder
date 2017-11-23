package com.techgig.countryfinder.Beans;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Varsha on 11-11-2017.
 */

public class Country implements Serializable
{
   private String name;
   private String capital;
   private String callingcode;
   private String alphacode;
   private String region;
   private String subregion;
   private long population;
   private double latitude;
   private double longitude;
   private ArrayList<String> timezones;
   private ArrayList<String> borders;
   private String nativename;
   private String numericcode;
   private ArrayList<String> languages;
   private String flag;
   private String cur_code;
   private String cur_name;
   private String cur_symbol;

    public Country(String name, String capital, String callingcode, String alphacode, String region, String subregion,
                   long population, double latitude, double longitude, ArrayList<String> timezones, ArrayList<String> borders,
                   String nativename, String numericcode, ArrayList<String> languages, String flag, String cur_code,
                   String cur_name, String cur_symbol) {

        this.name = name;
        this.capital = capital;
        this.callingcode = callingcode;
        this.alphacode = alphacode;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezones = timezones;
        this.borders = borders;
        this.nativename = nativename;
        this.numericcode = numericcode;
        this.languages = languages;
        this.flag = flag;
        this.cur_code = cur_code;
        this.cur_name = cur_name;
        this.cur_symbol = cur_symbol;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getCallingcode() {
        return callingcode;
    }

    public String getAlphacode() {
        return alphacode;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public long getPopulation() {
        return population;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public String getNativename() {
        return nativename;
    }

    public String getNumericcode() {
        return numericcode;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public String getFlag() {
        return flag;
    }

    public String getCur_code() {
        return cur_code;
    }

    public String getCur_name() {
        return cur_name;
    }

    public String getCur_symbol() {
        return cur_symbol;
    }

    public static ArrayList<Names> returnHashPairs(Country country) {
        ArrayList<Names> mEachCountry = new ArrayList<>();

        mEachCountry.add(new Names("Name", country.getName()));
        mEachCountry.add(new Names("Capital", country.getCapital()));
        mEachCountry.add(new Names("Calling Code", country.getCallingcode()));
        mEachCountry.add(new Names("Alpha Code", country.getAlphacode()));
        mEachCountry.add(new Names("Region", country.getRegion()));
        mEachCountry.add(new Names("Sub Region", country.getSubregion()));
        mEachCountry.add(new Names("Population", Long.toString(country.getPopulation())));
        mEachCountry.add(new Names("Latitude", Double.toString(country.getLatitude())));
        mEachCountry.add(new Names("Longitude", Double.toString(country.getLongitude())));
        mEachCountry.add(new Names("Native name", country.getNativename()));
        mEachCountry.add(new Names("Numeric code", country.getNumericcode()));
        mEachCountry.add(new Names("Flag", country.getFlag()));
        mEachCountry.add(new Names("Currency code", country.getCur_code()));
        mEachCountry.add(new Names("Currency name", country.getCur_name()));
        mEachCountry.add(new Names("Currency symbol", country.getCur_symbol()));

        //Timezones
        /*
        if(country.getTimezones().size() > 1) {
            StringBuilder mTimezones = new StringBuilder();
            for (String i: country.getTimezones()) {
                mTimezones.append(i);
                mTimezones.append("\n ");
            }
        }

        mEachCountry.add(new Names("Timezones", country.getTimezones().get(0)));
        //Log.e("Country Timezones", mTimezones.toString());

        //Borders
        StringBuilder mBorders = new StringBuilder();
        for (String i: country.getBorders()) {
            mBorders.append(i);
            mBorders.append(", ");
        }
        mEachCountry.add(new Names("Borders", mBorders.toString()));
        Log.e("Country Borders", mBorders.toString());

        //Borders
        StringBuilder mLanguages = new StringBuilder();
        for (String i: country.getLanguages()) {
            mLanguages.append(i);
            mLanguages.append(", ");
        }
        mEachCountry.add(new Names("Languages", mLanguages.toString()));
        Log.e("Country Languages", mLanguages.toString());
        */

        //mEachCountry.add(new Names("Timezones", country.getTimezones().get(0)));
        //mEachCountry.add(new Names("Borders", country.getBorders().get(0)));
        //mEachCountry.add(new Names("Languages", country.getLanguages().get(0)));


        return mEachCountry;
    }
}
