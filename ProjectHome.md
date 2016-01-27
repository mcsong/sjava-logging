# sjava-logging #

## Description ##
고성능의 자바 로깅 라이브러리입니다.

## Features ##
  * simple use
  * 고성능을 위한 BufferedWriter의 캐싱기능

## Using ##
Download the latest JAR @: http://code.google.com/p/sjava-logging/downloads/

## Example ##

LoggingTest.java
```
package net.sjava.logging.test;

import java.util.Date;
import java.text.SimpleDateFormat;
import net.sjava.logging.Logger;

public class LoggingTest {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss SSS");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("s - " + format.format(new Date()));
		
		for(int i=0; i < 1000; i++) {
			Logger.getInstance().log("aaaaaaaaaaaaaaa");
			Logger.getInstance().log("aaaaaaaaaaaaccccccccccccccccccaaa");
			Logger.getInstance().log("metoo", "푸푸푸푸푸박.. ");
			Logger.getInstance().log("abcded", "abcde", "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		}
	
		System.out.println("e - " + format.format(new Date()));
	}
}
```