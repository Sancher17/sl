package com.cafe.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);

	public static LocalDateTime dateFromString(String stringDate) {
		return LocalDateTime.parse(stringDate, dateFormat);
	}

	public static String stringFromDate(LocalDateTime date) {
		return dateFormat.format(date);
	}

	private DateUtil() {
	}
}
