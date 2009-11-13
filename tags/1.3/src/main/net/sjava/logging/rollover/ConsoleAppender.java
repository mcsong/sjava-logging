package net.sjava.logging.rollover;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sjava.logging.Level;
import net.sjava.logging.util.SimpleDateFormatFactory;

public class ConsoleAppender implements IAppender {
	
	/** pool size */
	private static final int size = 10;
	
	/** pool */
    private static Stack<ConsoleAppender> stack = new Stack<ConsoleAppender>();
	
    /** lock instance */
    private static final Lock lock = new ReentrantLock();
    
	/** for singleton instance using private constructor */
	private ConsoleAppender() {
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static ConsoleAppender getInstance() {
		lock.lock();
		try
		{
			if(stack.empty())
				return new ConsoleAppender();
			
			return stack.pop();
		} finally {
			lock.unlock();
		}
	}
		
	/**
	 * free logger instance
	 */
	private static void free(ConsoleAppender appender) {
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
	public void log(String serviceName, Level level, String data) {		
		System.out.print(serviceName);
		System.out.print("-"+SimpleDateFormatFactory.createLogFormat().format(new java.util.Date()));
		System.out.print("-"+level.name.toLowerCase());
		System.out.println("-" + data);
	}

	@Override
	@Deprecated
	public void log(String serviceName, String fileName, Level level, String data) {
		return;
		// TODO Auto-generated method stub
	}
	
}
