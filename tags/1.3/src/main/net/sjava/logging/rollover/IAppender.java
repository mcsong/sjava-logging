package net.sjava.logging.rollover;

import net.sjava.logging.Level;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 7.
 */
public interface IAppender {
	
	/**
	 * 
	 * @param serviceName
	 * @param level
	 * @param data
	 */
	public void log(String serviceName, Level level, String data);
	
	
	/**
	 * 
	 * @param serviceName
	 * @param fileName
	 * @param level
	 * @param data
	 */
	public void log(String serviceName, String fileName, Level level, String data);
}
