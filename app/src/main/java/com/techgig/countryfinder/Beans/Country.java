package com.techgig.countryfinder.Beans;

import java.util.ArrayList;

/**
 * Created by Varsha on 11-11-2017.
 */

public class Country
{
   String name;
   String capital;
   String alphacode;
   String region;
   String subregion;
   long population;
   float latitude;
   float longitude;
   String timezone;
   ArrayList<String> borders;
   String nativename;
   String nativecode;
   ArrayList<String> languages;

   String flag;

   //constructor
    public Country(String name, String capital, String alphacode, String region, String subregion,
                   long population, float latitude, float longitude, String timezone, ArrayList<String> borders,
                   String nativename, String nativecode, ArrayList<String> languages, String flag) {
        this.name = name;
        this.capital = capital;
        this.alphacode = alphacode;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.borders = borders;
        this.nativename = nativename;
        this.nativecode = nativecode;
        this.languages = languages;
        this.flag = flag;
    }


    //getters
    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
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

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public String getNativename() {
        return nativename;
    }

    public String getNativecode() {
        return nativecode;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public String getFlag() {
        return flag;
    }


    //setters


    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setAlphacode(String alphacode) {
        this.alphacode = alphacode;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public void setNativename(String nativename) {
        this.nativename = nativename;
    }

    public void setNativecode(String nativecode) {
        this.nativecode = nativecode;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
