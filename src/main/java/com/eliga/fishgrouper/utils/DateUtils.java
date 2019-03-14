package com.eliga.fishgrouper.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class DateUtils {

	public static final long MILLIS_IN_A_DAY = TimeUnit.DAYS.toMillis(1);

	public static Date getDateAtMidnight(Date date) {
		if (date != null) {
			return new Date(MILLIS_IN_A_DAY * (date.getTime() / MILLIS_IN_A_DAY));
		} else {
			return null;
		}
	}

	public static Date getDateAtMidnightAddDaysFromNow(int daysToAdd) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.DATE, daysToAdd);
		return getDateAtMidnight(calendar.getTime());
	}

	public static Date getDateAtMidnight(long dateValue) {
		if (dateValue > 0) {
			return new Date(MILLIS_IN_A_DAY * (dateValue / MILLIS_IN_A_DAY));
		} else {
			return null;
		}
	}

	public static Date getFutureDate(String delayPeriod) {
		int number = Integer.parseInt(delayPeriod.substring(0, delayPeriod.length() - 1));
		Calendar cal = GregorianCalendar.getInstance();
		int field = -1;
		switch (delayPeriod.charAt(delayPeriod.length() - 1)) {
		case 'm':
			field = Calendar.MINUTE;
			break;
		case 'h':
			field = Calendar.HOUR;
			break;
		case 'd':
			field = Calendar.DATE;
			break;
		case 'M':
			field = Calendar.MONTH;
			break;
		default:
			throw new RuntimeException(delayPeriod + " is not valid");
		}
		cal.add(field, number);
		return cal.getTime();
	}

	public static Date getDate(int days) {
		if (days == 0) {
			return new Date();
		}
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static String getJulianTime(Date date) {
		if (date != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyDDDHHmmss");
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

	public static String getJulianDate(Date date) {
		if (date != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyyDDD");
			return dateFormat.format(date);
		} else {
			return null;
		}
	}
}