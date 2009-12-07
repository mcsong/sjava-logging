package net.sjava.logging.test;

import net.sjava.logging.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LoggingTest {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss SSS");

	private static ConcurrentLinkedQueue<LoggingThread> queue = new ConcurrentLinkedQueue<LoggingThread>();
	
	static {
		for(int i=0; i < 50; i++) {
			queue.add(new LoggingThread());
		}
	}
	
	public static void testMulti() {
		for(int i=0; i < 1000; i++) {
			new LoggingThread().start();
		}
	}
	
	public static void testSingle() {
		for(int i=0; i < 1000000; i++) {
			Logger.create().log("aaaaaaaaaaaaaaa");
			Logger.create().log("aaaaaaaaaaaaccccccccccccccccccaaa");
			Logger.create().log("metoo", "ǪǪǪǪǪ��.. ");
			Logger.create().log("abcded", "abcde", "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("s - " + format.format(new Date()));
		
		//testSingle();
		testMulti();
		//Thread.sleep(1000 * 3);
		//Thread.sleep(1000 * 60);
		
		System.out.println("e - " + format.format(new Date()));
	}
}
