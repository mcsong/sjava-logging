package net.sjava.logging.rollover;

import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sjava.logging.Level;
import net.sjava.logging.util.BufferedWriterCacheUtility;
import net.sjava.logging.util.ConfigUtility;
import net.sjava.logging.util.SimpleDateFormatFactory;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 8.
 */
public class MinutesFileAppender extends AbstractFileAppender {
	/** pool size */
	private static final int size = 10;
	
	/** pool */
    private static Stack<MinutesFileAppender> stack = new Stack<MinutesFileAppender>();
	
    /** lock instance */
    private static final Lock lock = new ReentrantLock();
    
	/** for singleton instance using private constructor */
	private MinutesFileAppender() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static MinutesFileAppender getInstance() {
		lock.lock();
		try {
			if(stack.empty())
				return new MinutesFileAppender();
			
			return stack.pop();
		} finally {
			lock.unlock();
		}
	}	
	
	/**
	 * free HourlyFileAppender instance
	 */
	private static void free(MinutesFileAppender appender) {
		lock.lock();
		try {
			if(stack.size() < size)
				stack.push(appender);			
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * close this instance
	 */
	public void close() {
		free(this);
	}

	@Override
	void setDirectory(String dir, String serviceName) {
		StringBuilder builder = new StringBuilder(256);
		
		builder.append(ConfigUtility.createBaseDir(dir));		
		builder.append(System.getProperty("file.separator"));
		builder.append(ConfigUtility.createServiceDir(serviceName));
		builder.append(System.getProperty("file.separator") + super.year);
		builder.append(System.getProperty("file.separator") + super.month);
		builder.append(System.getProperty("file.separator") + super.day); 
		builder.append(System.getProperty("file.separator") + super.hour);
		
		super.logfileName = builder.toString();
		File file = new File(builder.toString());
		if(file.exists())
			return;
		
		file.mkdirs();
	}

	@Override
	void setFile(Level level, String fileName) {
		// TODO Auto-generated method stub
		
		StringBuilder builder = new StringBuilder(256);
		builder.append(super.logfileName);
		builder.append(System.getProperty("file.separator"));
		builder.append(super.year + ".");
		builder.append(super.month + ".");
		builder.append(super.day + ".");	
		builder.append(super.hour + ".");
		builder.append(super.minute);
		
		builder.append("-" + ConfigUtility.createFileName(fileName));
		builder.append( "-" + level.name);
		builder.append("." + ConfigUtility.getFileExtensionName());
		
		super.logfileName = builder.toString();
	}
	
	
	@Override
	public void write(String serviceName, String fileName, Level level, String data) {
		
		BufferedWriter bwriter = null;
		
		try {			
			bwriter = BufferedWriterCacheUtility.createBufferedWriter(super.logfileName);
			bwriter.write(SimpleDateFormatFactory.createLogFormat().format(super.date));
			bwriter.write(" " + level.name.toLowerCase());
			bwriter.write(" " + data);
			bwriter.newLine();
			
			BufferedWriterCacheUtility.close(super.logfileName, bwriter);
		} catch(IOException e) {
			// ignore because not critical
			e.printStackTrace();
		} 
	}		
}
