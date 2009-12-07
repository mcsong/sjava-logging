package net.sjava.logging.rollover;


/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 7.
 */
public class AppenderFactory {
	/**
	 * 
	 * @return
	 */
	public static IAppender createDailyFileAppender() {
		return DailyFileAppender.create();
	}
	
	/**
	 * 
	 * @return
	 */
	public static IAppender createHourlyFileAppender() {
		return HourlyFileAppender.create();
	}
	
	
	/**
	 * 
	 * @return
	 */
	public static IAppender createMinutesFileAppender() {
		return MinutesFileAppender.getInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public static IAppender createConsoleAppender() {
		return ConsoleAppender.getInstance();
	}
	
}
