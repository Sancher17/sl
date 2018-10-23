package com.senla.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date minusMonths(int month){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -month);
        return cal.getTime();
    }
}
