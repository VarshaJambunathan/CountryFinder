package com.techgig.countryfinder.Beans;

import java.util.ArrayList;

/**
 * Created by Varsha on 11-11-2017.
 */

public class Country
{
   String name;
   String capital;
   String callingcode;
   String alphacode;
   String region;
   String subregion;
   long population;
   double latitude;
   double longitude;
   ArrayList<String> timezones;
   ArrayList<String> borders;
   String nativename;
   String numericcode;
   ArrayList<String> languages;
   String flag;
   String cur_code;
   String cur_name;
   String cur_symbol;

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
}
