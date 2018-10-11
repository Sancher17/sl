package com.senla.fileworker.imports.parser;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {

    private static final Logger log = Logger.getLogger(ParseDate.class);
    private static final String DATE_PARSING_ERROR = "Ошибка парсинга даты ";
    private static final String DATE_PATTERN = "dd.M.yyyy";

    public static Date parseDate(String date) {
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            return dateFormat.parse(date);
        }catch (ParseException e) {
            log.error(DATE_PARSING_ERROR + e);
        }
        return null;
    }
}