//Red Team: Anthony Wright Andreas Arnet Angela Perkins Jennifer Thomas Chad Hendren Rusty DeGarmo

package com.provisio.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

// Date formatter
public class DateFormatUtil {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	public static String format(Date date) {

		if (date != null) {
			return new SimpleDateFormat(DATE_FORMAT).format(date);
		}

		return "";
	}
	
	public static String format(Date date, String format) {

		if (date != null) {
			return new SimpleDateFormat(format).format(date);
		}

		return "";
	}
}
