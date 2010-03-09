/**
 * http://www.sjava.net/category/sjava%20project
 */
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
		for(int i=0; i < 4000; i++) {
			new LoggingThread().start();
			
		}
	}
	
	public static void testSingle() {
		for(int i=0; i < 500; i++) {
			Logger.open().fatal("aaaaaaaaaaaaaaa", "asdsda", "dfjahshhjkshjkldfhjkldafdfadfadfhjkldfkj");
			//Logger.open().log("aaaaaaaaaaaaccccccccccccccccccaaa");
			Logger.open().error("metoo", "ǪǪǪǪǪ��.. ", "adaas");
			Logger.open().debug("abcded", "abcde" + i, "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("s - " + format.format(new Date()));
		
		//testSingle();
		testMulti();

		
		System.out.println("e - " + format.format(new Date()));
	}
}
