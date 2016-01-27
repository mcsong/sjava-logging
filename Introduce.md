## Introduction ##
java logging library 입니다. file에 write 하는 BufferedWriter의 성능을 높이기 위한 tip이 적용되어 있습니다. config 처리는 sjava-config library를 사용하고 있습니다.
아래 예제는 설정파일과 sjava-logging-1.3.jar를 클래스 패스가 잡힌 위치에 복사를 하시고 돌리시면 됩니다

## 설정파일 형태 ##
```
<?xml version="1.0" encoding="utf-8"?>
<sjava-config>
	<!-- array variable delimeter is "," -->
	
	<!-- sjava-config 설정, 아래 설정은 지우지 마세요 -->
	<sjava-service name="config">
		<key name="watch" value="false" /> <!-- true, false -->
		<key name="period" value="60" /> <!-- 초단위, 60 second is 1 minute -->
	</sjava-service>
	
	<!-- 로그서버 설정 -->
	<sjava-service name="logging">
		<key name="base-dir" value="d:\sjava-logging" />
		<key name="service-dir" value="default" />
		<key name="file-name" value="default" />
		<key name="file-ext" value="log" />
		<key name="buffer-size" value="1024" />
		
		<!-- day(1), hour(2), minute(3) -->
		<key name="strategy" value="2" />
		<!-- lru cache map size -->
		<key name="cache-size" value="100" />
		
		<!-- flush option -->
		<key name="flush-option" value="false" />
		<!-- flush period -->
		<key name="flush-period" value="10000" />
		
	</sjava-service>
</sjava-config>
```
그리고 위 파일(sjava-config.xml)은 classpath에 위치를 해야 읽을 수 있습니다.

## test ##
LoggingTest.java
```
package net.sjava.logging.test;

import net.sjava.logging.Logger;

import java.util.Date;
import java.text.SimpleDateFormat;

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