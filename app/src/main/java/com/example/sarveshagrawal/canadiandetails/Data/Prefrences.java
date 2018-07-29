package com.example.sarveshagrawal.canadiandetails.Data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sarvesh Agrawal on 30-07-2018.
 */

public class Prefrences {

    public static final String key = "keys";
    public static final String TITLE = "title";

    public static void setTittle(String tittle,Context context){
        SharedPreferences sp = context.getSharedPreferences(key , Context.MODE_PRIVATE);
        sp.edit().putString(TITLE,tittle).commit();
    }

    public static String getTittle(Context context){
        String str = "";
        SharedPreferences sp = context.getSharedPreferences(key ,Context.MODE_PRIVATE);
        str = sp.getString(TITLE,"");
        return str;
    }
}
