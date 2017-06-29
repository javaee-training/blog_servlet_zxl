package com.doufuding.java.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeWithZoneUtil {
	
	public static String getTimeWithZone() {
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		TimeZone.setDefault(timeZone);
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss Z");
		Date date = calendar.getTime();
		return dateFormat.format(date);
	}
	
}
