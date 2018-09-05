package com.senla.fileworker.imports.parser;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {

    private static final Logger log = Logger.getLogger(ParseDate.class);

    public static Date parseDate(String date) {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy");
            return sdf.parse(date);
        }catch (ParseException e) {
            log.error("Ошибка парсинга даты " + e);
        }
        return null;
    }
}