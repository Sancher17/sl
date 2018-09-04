package com.senla.fileworker.imports.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {

    public static Date parseDate(String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
            return sdf.parse(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}