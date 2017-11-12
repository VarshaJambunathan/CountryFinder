package com.techgig.countryfinder.Beans;

/**
 * Created by Varsha on 11-11-2017.
 */

public class Country
{
    String name;
    String code;
    String capital;
    String language;

    public Country(String name, String code, String capital, String language)
    {
        this.name = name;
        this.code = code;
        this.capital = capital;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCapital() {
        return capital;
    }

    public String getLanguage() {
        return language;
    }
}
