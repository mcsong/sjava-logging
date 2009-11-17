package net.sjava.logging.test;

import net.sjava.logging.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;

public class LoggingTest {

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss SSS");



	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("s - " + format.format(new Date()));
		
		for(int i=0; i < 1; i++) {
			Logger.getInstance().log("aaaaaaaaaaaaaaa");
			Logger.getInstance().log("aaaaaaaaaaaaccccccccccccccccccaaa");
			Logger.getInstance().log("metoo", "ǪǪǪǪǪ��.. ");
			Logger.getInstance().log("abcded", "abcde", "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
		
		Thread.sleep(1000 * 3);
		//Thread.sleep(1000 * 60);
		
		System.out.println("e - " + format.format(new Date()));
	}
}
