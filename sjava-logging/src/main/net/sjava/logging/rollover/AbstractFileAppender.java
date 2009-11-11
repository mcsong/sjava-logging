package net.sjava.logging.rollover;

import java.util.Date;

import net.sjava.logging.Level;
import net.sjava.logging.util.ConfigUtility;
import net.sjava.logging.util.SimpleDateFormatFactory;

/**
 * 
 * 
 * 
 * @author mcsong@gmail.com
 * @version 1.0.0
 * @since
 */
public abstract class AbstractFileAppender implements IAppender {
	/** year key */
	protected String year ="1976";
	/** month key */
	protected String month ="01";
	/** day key */
	protected String day = "01";
	/** hour key */
	protected String hour = "01";
	/** minute key */
	protected String minute ="01";
	
	/** date instance */
	protected Date date;
	
	/** full log filename */
	protected String logfileName;
	
	/**
	 * set keys 
	 */
	private void initializeDateKey() {
		date = new Date();
		
		year = SimpleDateFormatFactory.createYearFormat().format(date);
		month = SimpleDateFormatFactory.createMonthFormat().format(date);
		day = SimpleDateFormatFactory.createDayFormat().format(date);
		hour = SimpleDateFormatFactory.createHourFormat().format(date);
		minute = SimpleDateFormatFactory.createMinuteFormat().format(date);
	}
	
	@Override
	public void log(String serviceName, Level level, String data) {
		this.log(serviceName, ConfigUtility.createFileName(null), level, data);
	}
	
	@Override
	public void log(String serviceName, String fileName, Level level, String data) {
		// this call
		this.initializeDateKey();
		
		this.setDirectory(null, serviceName);
		this.setFile(level, fileName);
		this.write(serviceName, fileName, level, data);
	}
	
	/**
	 * 
	 * @param dir
	 * @param serviceName
	 */
	abstract void setDirectory(String dir, String serviceName);
	
	/**
	 * 
	 * @param level
	 * @param fileName
	 */
	abstract void setFile(Level level, String fileName);
	
	/**
	 * abstract method for writing
	 * 
	 * @param serviceName
	 * @param fileName
	 * @param level
	 * @param data
	 */
	abstract void write(String serviceName, String fileName, Level level, String data);
}