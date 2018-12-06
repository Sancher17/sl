package com.cafe.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

//	private static final String DATE_PATTERN = "yyyy-MM-dd 00:00:00";
	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static LocalDateTime dateFromString(String stringDate) {
//		String str = "1986-04-08 12:30";
		LocalDateTime dateTime = LocalDateTime.parse(stringDate, dateFormat);
		return dateTime;
//		return LocalDadteTime.parse(stringDate, dateFormat);
	}

	public static String stringFromDate(LocalDateTime date) {
		return dateFormat.format(date);
	}

	private DateUtil() {
	}
}
