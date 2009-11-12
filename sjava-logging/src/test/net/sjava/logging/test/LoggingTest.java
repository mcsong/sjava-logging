package net.sjava.logging.test;

import net.sjava.logging.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;
import net.sjava.logging.util.BufferedWriterCacheUtility;

// static import
import static java.lang.Runtime.getRuntime;

public class LoggingTest {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss SSS");
	

	public static String getExceptionCallStack(Exception e) {
	  java.io.StringWriter sw = new java.io.StringWriter();
	  e.printStackTrace(new java.io.PrintWriter(sw));
	  return sw.toString();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//try {
		//	throw new Exception();
		//} catch(Exception e) {
		//	System.out.println(getExceptionStackTraceAsString(e));
		//}
		
		// add shutdown hook
		//getRuntime().addShutdownHook(new Thread() {
		//	public void run() {
		//		BufferedWriterCacheUtility.shutdown();
		//	}
    	//});
		
	
		
		System.out.println("s - " + format.format(new Date()));
		
		for(int i=0; i < 1000; i++) {
			Logger.getInstance().log("aaaaaaaaaaaaaaa");
			Logger.getInstance().log("aaaaaaaaaaaaccccccccccccccccccaaa");
			Logger.getInstance().log("metoo", "ǪǪǪǪǪ��.. ");
			Logger.getInstance().log("abcded", "abcde", "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	
		System.out.println("e - " + format.format(new Date()));
		
	}
}
