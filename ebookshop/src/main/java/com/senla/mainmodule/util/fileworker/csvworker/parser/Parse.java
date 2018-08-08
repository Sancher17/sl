package com.senla.mainmodule.util.fileworker.csvworker.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parse {

    public static Date parseDate(String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
            return sdf.parse(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Double parseDouble(String aDouble) {
        return Double.parseDouble(aDouble);
    }

    public static Integer parseInteger(String integer) {
        return Integer.parseInt(integer);
    }

    public static Long parseLong(String longs) {
        return Long.parseLong(longs);
    }

    public static Boolean parseBoolean(String aBoolean) {
        return Boolean.parseBoolean(aBoolean);
    }
}