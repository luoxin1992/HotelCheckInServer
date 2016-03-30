package cn.edu.xmu.ultraci.hotelcheckin.server.util;

import java.text.DateFormat;
import java.util.Date;

public class TimeUtil {
	public static String formatTime(long timeMillis) {
		return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
				.format(new Date(timeMillis));
	}
}
