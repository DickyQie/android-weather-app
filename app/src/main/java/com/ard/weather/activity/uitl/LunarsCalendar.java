package com.ard.weather.activity.uitl;

import com.ard.weather.activity.api.LunarCalendar;

import java.util.Date;
import java.util.GregorianCalendar;


public class LunarsCalendar implements LunarCalendar {

	
	public int[] solarToLunar(int year, int month, int monthDay) {
		int[] lunarDate = new int[4];
		Date baseDate = new GregorianCalendar(1900, 0, 31).getTime();
		Date objDate = new GregorianCalendar(year, month - 1, monthDay)
				.getTime();
		int offset = (int) ((objDate.getTime() - baseDate.getTime()) / 86400000L);

		int iYear, daysOfYear = 0;
		for (iYear = MIN_YEAR; iYear <= MAX_YEAR && offset > 0; iYear++) {
			daysOfYear = daysInLunarYear(iYear);
			offset -= daysOfYear;
		}
		if (offset < 0) {
			offset += daysOfYear;
			iYear--;
		}

		lunarDate[0] = iYear;

		int leapMonth = leapMonth(iYear); 
		boolean isLeap = false;
		int iMonth, daysOfMonth = 0;
		for (iMonth = 1; iMonth <= 13 && offset > 0; iMonth++) {
			daysOfMonth = daysInLunarMonth(iYear, iMonth);
			offset -= daysOfMonth;
		}
		if (leapMonth != 0 && iMonth > leapMonth) {
			--iMonth;

			if (iMonth == leapMonth) {
				isLeap = true;
			}
		}
		if (offset < 0) {
			offset += daysOfMonth;
			--iMonth;
		}

		lunarDate[1] = iMonth;
		lunarDate[2] = offset + 1;
		lunarDate[3] = isLeap ? 1 : 0;

		return lunarDate;
	}
	final public static int daysInMonth(int year, int month) {
		return daysInMonth(year, month, false);
	}

	public static final int daysInMonth(int year, int month, boolean leap) {
		int leapMonth = leapMonth(year);
		int offset = 0;

		if (leapMonth != 0 && month > leapMonth) {
			offset = 1;
		}

		if (!leap) {
			return daysInLunarMonth(year, month + offset);
		} else {
			if (leapMonth != 0 && leapMonth == month) {
				return daysInLunarMonth(year, month + 1);
			}
		}

		return 0;
	}

	private static int daysInLunarYear(int year) {
		int i, sum = 348;
		if (leapMonth(year) != 0) {
			sum = 377;
		}
		int monthInfo = LUNAR_INFO[year - MIN_YEAR] & 0x0FFF80;
		for (i = 0x80000; i > 0x7; i >>= 1) {
			if ((monthInfo & i) != 0)
				sum += 1;
		}
		return sum;
	}

	private static int daysInLunarMonth(int year, int month) {
		if ((LUNAR_INFO[year - MIN_YEAR] & (0x100000 >> month)) == 0)
			return 29;
		else
			return 30;
	}

	private static int leapMonth(int year) {
		return (int) ((LUNAR_INFO[year - MIN_YEAR] & 0xF00000)) >> 20;
	}
}
