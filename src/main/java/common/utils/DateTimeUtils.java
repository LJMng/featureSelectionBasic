package common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtils {
	
	/**
	 * Transfer a {@link Date} to formatted {@link String}.
	 * 
	 * @see {@link SimpleDateFormat#format(Date)}
	 * 
	 * @param date
	 * 		A {@link Date} instance to be transformed.
	 * @param format
	 * 		The format transform into.
	 * @return Correspondent time {@link String}.
	 */
	public static String toString(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * Formatted {@link String} of now.
	 * 
	 * @see {@link #toString(Date, String)}
	 * 
	 * @param format
	 * 		The format transform into.
	 * @return Current time {@link String}.
	 */
	public static String currentDateTimeString(String format) {
		return toString(new Date(), format);
	}

	/**
	 * Translate time units into string. 
	 * 
	 * @param ms
	 * 		time value in ms.
	 * @param decimal
	 * 		how many number preserved after doc(".").
	 * @return
	 */
	public static String translateUnit(long ms, int decimal) {
		double time = ms / 1000.0;
		if (time < 60) {
			return String.format("%."+decimal+"f sec(s)", time);
		}else if (time < 3600) {
			time = time / 60;
			return String.format("%."+decimal+"f min(s)", time);
		}else {
			time = time / 3600;
			return String.format("%."+decimal+"f h(s)", time);
		}
	}
}
