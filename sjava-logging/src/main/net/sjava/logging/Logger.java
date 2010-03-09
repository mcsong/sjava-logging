/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging;

import net.sjava.logging.rollover.AppenderFactory;
import net.sjava.logging.rollover.IAppender;
import net.sjava.logging.util.ConstantsFactory;

/**
 * 로깅을 처리하기 위한 Facade 클래스
 * 
 * @author mcsong@gmail.com
 * @since 2010. 1. 5.
 */
public class Logger {
	
	/** Configuration level */
	private static int configLevel = ConstantsFactory.createLevelStrategy();
	
	/**  Level */
	private Level level = null;
	
	/** Appender type */
	private IAppender appender = null;
	
	/** Service name */
	private String serviceName = null;
	
	/** File name */
	private String fileName = null;
	
        
	/**
	 * 
	 * @return
	 */
	public static Logger open() {
		return new Logger();
	}
	
	/** Constructor */
	//private void Logger(){ }
			
	/**
	 * Set level
	 * 
	 * @param value
	 */
	public void setLevel(int value) {
		this.level = LevelFactory.getLevel(value);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public String getLevelName(int value) {
		return LevelFactory.getLevel(value).getName();
	}
	
	/**
	 * Get appender
	 * @return the appender
	 */
	public IAppender getAppender() {	
		return this.appender;
	}
	
	/**
	 * Get appender
	 * 1-> day, 2-> hour, 3 -> minute
	 * @param type
	 * @return the appender
	 */
	public IAppender getAppender(int type) {
		return this.createAppender(type);
	}

	/**
	 * Set appender
	 * @param appender the appender to set
	 */
	public void setAppender(IAppender appender) {
		this.appender = appender;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private IAppender createAppender(int type) {
		if(type == 1)
			return AppenderFactory.createDailyFileAppender();
		
		if(type == 2)
			return AppenderFactory.createHourlyFileAppender();
		
		if(type == 3)
			return AppenderFactory.createMinutesFileAppender();
		
		return AppenderFactory.createDailyFileAppender();
	}
	
	
	/**
	 * Write data
	 * @param data
	 */
	//public void log(String data) {
	//	this.write(null, null, data);
	//}
	
	/**
	 * Write data
	 * @param fileName
	 * @param data
	 */
	//public void log(String fileName, String data) {
	//	this.write(null, fileName, data);
	//}
	
	/**
	 * Write data
	 *  
	 * @param service
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	protected void write(String service, String fileName, String data) {
		if(appender == null)
			setAppender(createAppender(ConstantsFactory.createStrategy()));

		appender.log(service, fileName, level, data);
	}
	
	
	/**
	 * 
	 * <key name="level" value="0" />
	 * 
	 * new Level(0, "all");
	 * new Level(1, "fatal");
	 * new Level(2, "error");
	 * new Level(3, "warn");
	 * new Level(4, "info");
	 * new Level(5, "debug");
	 * new Level(6, "trace");
	 * new Level(7, "system");
	 * @return
	 */
	private boolean isLevelEnable(int levelValue) {		
		if(configLevel == 0)
			return true;
		
		if(configLevel >= levelValue)
			return true;
		
		return false;
	}
		
	/**
	 * Is fatal enable
	 * @return
	 */
	public boolean fatalEnable() {
		return this.isLevelEnable(1);
	}
	
	
	/**
	 * Is error enable 
	 * @return
	 */
	public boolean errorEnable() {
		return this.isLevelEnable(2);
	}
	
	/**
	 * Is warn enable
	 * @return
	 */
	public boolean warnEnable() {
		return this.isLevelEnable(3);
	}
	
	/**
	 * Is info enable
	 * @return
	 */
	public boolean infoEnable() {
		return this.isLevelEnable(4);
	}
	
	/**
	 * Is debug enable
	 * @return
	 */
	public boolean debugEnable() {
		return this.isLevelEnable(5);
	}
	
	/**
	 * Is trace enable
	 * @return
	 */
	public boolean traceEnable() {
		return this.isLevelEnable(6);
	}
	
	
	/**
	 * 
	 * @param service
	 * @param fileName
	 */
	private void setServiceNameAndFileName(String service, String fileName) {
		this.serviceName = ConstantsFactory.createServiceDirectory(service);
		this.fileName = ConstantsFactory.createFileName(fileName);
	}
	
	/**
	 * 
	 * @param levelName
	 */
	private void setAppenderAndLevel(String levelName) {
		if(appender == null)
			setAppender(createAppender(ConstantsFactory.createStrategy()));
		
		if(level == null)
			this.level = LevelFactory.getLevel(levelName);
	}
	
	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void fatal(String service, String fileName, String data) {	
		this.setServiceNameAndFileName(service, fileName);
		
		if(fatalEnable()) {
			this.setAppenderAndLevel("fatal");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}
	
	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void error(String service, String fileName, String data) {
		this.setServiceNameAndFileName(service, fileName);
		
		if(errorEnable()) {
			this.setAppenderAndLevel("error");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}
	
	
	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void warn(String service, String fileName, String data) {
		this.setServiceNameAndFileName(service, fileName);
		
		if(warnEnable()) {
			this.setAppenderAndLevel("warn");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}

	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void info(String service, String fileName, String data) {
		this.setServiceNameAndFileName(service, fileName);
		
		if(infoEnable()) {			
			this.setAppenderAndLevel("info");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}
	
	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void debug(String service, String fileName, String data) {
		this.setServiceNameAndFileName(service, fileName);
		
		if(debugEnable()) {			
			this.setAppenderAndLevel("debug");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}

	/**
	 * 
	 * @param service
	 * @param fileName
	 * @param data
	 */
	public void trace(String service, String fileName, String data) {
		this.setServiceNameAndFileName(service, fileName);
		
		if(traceEnable()) {			
			this.setAppenderAndLevel("trace");
			this.appender.log(this.serviceName, this.fileName, level, data);
		}
	}
	
	/**
	 * 레벨체크를 하지 않고 저장을 한다. 
	 * 파라미터는 null이 되면 안된다.
	 * 
	 * @param service 
	 * @param fileName
	 * @param data
	 */
	public void system(String service, String fileName, String data) {
		if(service == null || fileName == null || data == null)
			return;
		
		this.setAppenderAndLevel("system");
		this.appender.log(service, fileName, level, data);
	}
	
}
