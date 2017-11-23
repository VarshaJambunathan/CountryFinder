package com.techgig.countryfinder.Beans;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Varsha on 21-11-2017.
 */

public class Names implements Serializable{
    String name;
    String flagUrl;
    Drawable flag;

    public Names(String name, String flagUrl) {
        this.name = name;
        this.flagUrl = flagUrl;
    }

    public String getName() {
        return name;
    }

    public String getFlagUrl() {
        return flagUrl;
    }

    private void convertSVGtoDrawable(String entity2) {

    }

}
