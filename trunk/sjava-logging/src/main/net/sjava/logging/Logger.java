/**
 * http://www.sjava.net/category/Sjava%27s%20Library/sjava-logging
 */
package net.sjava.logging;

import static java.lang.Runtime.getRuntime;

import java.io.IOException;
import java.util.Stack;
import java.util.Timer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

	/** logger pool size */
	private static final int maxInstance = ConfigUtility.getLoggerPoolSize();
	
	/** logger pool */
    private static Stack<Logger> stack = new Stack<Logger>();
	
    /** lock instance */
    private static final Lock lock = new ReentrantLock();
    
    /** timer instance */
    private static Timer timer = new java.util.Timer("sjava-logging");
    
    /**
     * Shutdown hook start
     */
    /*
    static {
		getRuntime().addShutdownHook(new Thread() {
			public void run() {
				BufferedWriterCacheUtility.shutdown();
				timer.cancel();
			}
    	});
    }
    */
    /**
     * Flush start using Timer
     */
    static {    	
    	// flush option is true
    	if(ConfigUtility.isFlushing()) {
	    	timer.scheduleAtFixedRate(new java.util.TimerTask() {
	    		public void run(){
	    			try {
	    				BufferedWriterCacheUtility.flushAll();
	    			} catch(Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	    	}, 0, ConfigUtility.getFlushPeriod() * 1000); // 10 √ 
    	}
    }
    	
    
    
	/** for singleton instance using private constructor */
	private Logger() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static Logger getInstance() {
		lock.lock();
		try
		{
			if(stack.empty())
				return new Logger();
			
			return stack.pop();
		} finally {
			lock.unlock();
		}
	}
		
	/**
	 * free logger instance
	 */
	private static void free(Logger logger) {
		lock.lock();
		try {
			if(stack.size() < maxInstance)
				stack.push(logger);			
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * close this instance
	 */
	//public void close() {
	//	free(this);
	//}
	
	/**
	 * Set level
	 * 
	 * @param value
	 */
	public void setLevel(int value) {
		this.level = LevelFactory.getInstance().getLevel(value);
	}
	
	/**
	 * Set level
	 * 
	 * @param level
	 */
	public void setLevel(Level level) {
		this.level = level;
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
		if(type == 1) {
			return AppenderFactory.createDailyFileAppender();
		} else if(type == 2) {
			return AppenderFactory.createHourlyFileAppender();
		} else if(type == 3) {
			return AppenderFactory.createMinutesFileAppender();
		}
		
		return AppenderFactory.createDailyFileAppender();
		//return appender;
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
		if(type == 1) {
			this.appender = AppenderFactory.createDailyFileAppender();
		} else if(type == 2) {
			this.appender = AppenderFactory.createHourlyFileAppender();
		} else if(type == 3) {
			this.appender = AppenderFactory.createMinutesFileAppender();
		} else {
			this.appender = AppenderFactory.createDailyFileAppender();
		}
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
		AppenderFactory.free(appender);
		Logger.free(this);
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
			this.setLevel(LevelFactory.getInstance().getLevel("all"));
		
		this.appender.log(sname, fname, level, data);
	}
}
