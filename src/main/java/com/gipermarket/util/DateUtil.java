package com.gipermarket.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Util for operations with Dates. Convert to other format, Setting format, Adding days into Date.
 */
public class DateUtil {
	/*
	 * Default date format
	 */
	public static SimpleDateFormat FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy");

	/*
	 * Count of milliseconds in the day
	 */
	public static final int MILLS_IN_DAY = 1000 * 60 * 60 * 24;

	/**
	 * Convert string as @param pattern format to Date
	 * 
	 * @param date
	 *            - Date
	 * @param pattern
	 *            - SimpleDateFormat as String
	 * @return Date
	 */
	public static Date toDate(String stringDate, String pattern) {
		DateFormat toDate = new SimpleDateFormat(pattern);
		Date date;
		try {
			date = toDate.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		return date;
	}

	/**
	 * Convert Date to string as @param pattern format
	 * 
	 * @param date
	 *            - Date
	 * @param pattern
	 *            - SimpleDateFormat
	 * @return String as @param pattern format
	 */
	public static String toString(Date date, String pattern) {
		DateFormat toString = new SimpleDateFormat(pattern);
		if (date == null) {
			return null;
		}
		return toString.format(date);
	}

	/**
	 * Add specified time day into passed Date.
	 * 
	 * @param date
	 *            - incoming date
	 * @param days
	 *            - count of adding days
	 * @return shifted Date
	 */
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * Set time to end of day
	 * 
	 * @param date
	 *            - time
	 * @return Date with setting time to end of day
	 */
	public static Date setDayToEOD(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
}
