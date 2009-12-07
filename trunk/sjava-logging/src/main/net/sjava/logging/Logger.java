/**
 * http://www.sjava.net/category/Sjava%27s%20Library/sjava-logging
 */
package net.sjava.logging;

import static java.lang.Runtime.getRuntime;

import java.io.IOException;
import java.util.Timer;

import net.sjava.logging.rollover.AppenderFactory;
import net.sjava.logging.rollover.IAppender;
import net.sjava.logging.util.BufferedWriterCacheUtility;
import net.sjava.logging.util.ConfigUtility;




/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 2.
 */
public class Logger implements Cloneable {
       
	/** level */
	private Level level = null;
	
	/** appender type	 */
	private IAppender appender = null;
    
    
    /**
     * Shutdown hook start
     */
    
    static {
		getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					BufferedWriterCacheUtility.shutdown();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    
    /**
     * Flush start using Timer
     */
    static {    	
    	// flush option is true
    	if(ConfigUtility.isFlushing()) {   
    		Timer timer = new java.util.Timer("sjava-logging", true);
	    	timer.scheduleAtFixedRate(new java.util.TimerTask() {
	    		public void run(){
	    			try {
	    				BufferedWriterCacheUtility.flushAll();
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	}, 0, ConfigUtility.getFlushPeriod() * 1000); // 10 ÃÊ
    	}
    }
	
	/**
	 * 
	 * @return
	 */
	public static Logger create() {
		return new Logger();
	}
			
	/**
	 * Set level
	 * 
	 * @param value
	 */
	public void setLevel(int value) {
		this.level = LevelFactory.getInstance().getLevel(value);
	}
	
		
	/**
	 * Get appender
	 * @return the appender
	 */
	public IAppender getAppender() {
		return appender;
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
	 * Set appender
	 * @param type
	 */
	public void setAppender(int type) {
		this.appender = this.createAppender(type);	
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private IAppender createAppender(int type) {
		if(type == 1) {
			return AppenderFactory.createDailyFileAppender();
		} else if(type == 2) {
			return AppenderFactory.createHourlyFileAppender();
		} else if(type == 3) {
			return AppenderFactory.createMinutesFileAppender();
		}
		
		return AppenderFactory.createDailyFileAppender();
	}
	
	
	/**
	 * Write data
	 * @param data
	 */
	public void log(String data) {
		this.write(null, null, data);
	}
	
	/**
	 * Write data
	 * @param fileName
	 * @param data
	 */
	public void log(String fileName, String data) {
		this.write(null, fileName, data);
	}
	
	/**
	 * Write data
	 *  
	 * @param service
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public void log(String service, String fileName, String data) {
		this.write(service, fileName, data);
	}
	
	/**
	 *  
	 * 
	 * @param serviceName 
	 * @param fileName
	 * @param data
	 */
	private void write(String service, String fileName, String data) {
		String sname = service;
		String fname = fileName;
	
		if(!ConfigUtility.isUseableString(sname))
			sname = ConfigUtility.createServiceDir(null);
		
		if(!ConfigUtility.isUseableString(fname))
			fname = ConfigUtility.createFileName(null);
		
		//strategy
		if(this.appender == null)
			this.setAppender(ConfigUtility.getRollingStrategy());
		
		if(this.level == null)
			this.level = LevelFactory.getInstance().getLevel("all");
		
		this.appender.log(sname, fname, level, data);
	}
}
