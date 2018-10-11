package com.senla.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ScannerHelper {

    private static final Logger log = Logger.getLogger(ScannerHelper.class);
    private static final String INCORRECT_DATA = "не корректные введены даные";
    private static final String DATE_PATTERN = "dd.M.yyyy";
    private static final String DATE_PARSING_ERROR = "Ошибка парсинга даты ";

    private ScannerHelper() {}

    public static String scannerString() {
        return new Scanner(System.in).nextLine();
    }

    public static Double scannerDouble(Scanner in) {
        double number = -1.0;
        try {
            number = Double.parseDouble(in.next());
            return number;
        } catch (NumberFormatException e) {
            Printer.println(INCORRECT_DATA);
            log.info(INCORRECT_DATA + e);
        }
        return number;
    }

    public static Long scannerLong(Scanner in) {
        Long number = -1L;
        try {
            return Long.parseLong(in.next());
        } catch (NumberFormatException e) {
            Printer.println(INCORRECT_DATA);
            log.info(INCORRECT_DATA + e);
        }
        return number;
    }

    public static Date scannerDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        try {
            String[] dates = date.split("\\.");
            if (dates[2].length() != 4) {
                throw new NumberFormatException();
            }
            int year = Integer.parseInt(dates[2]);
            if (year <= 0) {
                throw new NumberFormatException();
            }
            int month = Integer.parseInt(dates[1]);
            if (month < 1 || month > 12) {
                throw new NumberFormatException();
            }
            int day = Integer.parseInt(dates[0].replaceAll("\\s+", ""));
            if (day < 1 || day > 31) {
                throw new NumberFormatException();
            }
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
                log.error(DATE_PARSING_ERROR + e);
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            Printer.println(INCORRECT_DATA);
            log.info(INCORRECT_DATA + e);
        }
        return null;
    }
}
