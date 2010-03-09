/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.util;

import java.text.SimpleDateFormat;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 2.
 */
public class SimpleDateFormatFactory {
	/** */
	private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
	/** */
	private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
	/** */
	private static final SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
	/** */
	private static final SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
	/** */
	private static final SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
	/** */
	//private static SimpleDateFormat secondFormat = new SimpleDateFormat("ss");
	/** */
	private static final SimpleDateFormat logFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss SSS");
	
	/** */
	public static SimpleDateFormat createYearFormat() {
		return yearFormat;
	}
	
	/** */
	public static SimpleDateFormat createMonthFormat() {
		return monthFormat;
	}
	
	/** */
	public static SimpleDateFormat createDayFormat() {
		return dayFormat;
	}
	
	/** */
	public static SimpleDateFormat createHourFormat() {
		return hourFormat;
	}
	
	/** */
	public static SimpleDateFormat createMinuteFormat() {
		return minuteFormat;
	}

	//public static SimpleDateFormat createSecondFormat() {
	//	return secondFormat;
	//}
	
	/** */
	public static SimpleDateFormat createLogFormat() {
		return logFormat;
	}	
}
