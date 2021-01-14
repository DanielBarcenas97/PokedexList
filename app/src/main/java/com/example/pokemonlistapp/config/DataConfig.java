package com.example.pokemonlistapp.config;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataConfig {

    public static String getCurrentDate(Context context){
        Calendar calendar = Calendar.getInstance();

        return  new SimpleDateFormat("dd-M-yyyy").format(calendar.getTime());
    }

    public static String formatDate(Context context, String dateString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy");

        try{
            Date parseDate = simpleDateFormat.parse(dateString);
            return new SimpleDateFormat("dd MMM").format(parseDate);
        }catch (Exception e ){

        }
        return dateString;
    }
}
