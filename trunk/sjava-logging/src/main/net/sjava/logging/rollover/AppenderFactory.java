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
		return DailyFileAppender.getInstance();
	}
	
	/**
	 * 
	 * @return
	 */
	public static IAppender createHourlyFileAppender() {
		return HourlyFileAppender.getInstance();
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
	
	/**
	 * 
	 * @param appender
	 */
	public static void free(IAppender appender) {
		
		if(appender instanceof DailyFileAppender)
			((DailyFileAppender) appender).close();
					
		if(appender instanceof HourlyFileAppender)
			((HourlyFileAppender) appender).close();
				
		if(appender instanceof MinutesFileAppender)
			((MinutesFileAppender) appender).close();
		
		if(appender instanceof ConsoleAppender)
			((ConsoleAppender) appender).close();	
	}
}
