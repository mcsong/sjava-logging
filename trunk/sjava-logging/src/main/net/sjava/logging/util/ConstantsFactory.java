/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.util;

import static net.sjava.config.ConfigHandler.getInstance;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2010. 1. 5.
 *
 */
public class ConstantsFactory {
	/*
	<key name="base-dir" value="d:\common-logging" />
	<key name="service-dir" value="default-service" />
	<key name="file-name" value="default-filename" />
	<key name="file-ext" value="log" />
	<key name="buffer-size" value="1024" />
	
	<!-- day(1), hour(2), minute(3) -->
	<key name="strategy" value="2" />
	
	<!-- lru cache map size -->
	<key name="cache-size" value="200" />
	*/
	/**
	 * 
	 */
	private static String serviceName = "sjava-logging";
	
	/**
	 * Create or get base directory
	 */
	public static String createBaseDirectory(String directory) {
		if(isUseableString(directory))
			return directory;
		
		String value = "";
		String osName = System.getProperty("os.name").toLowerCase();
			
		if(osName.indexOf("linux") > -1)  // linux
			value = "//usr/sjava-logging";
		else if (osName.indexOf("mac") > -1)  // max
			value = "//usr/sjava-logging";
		else
			value = "d:\\sjava-logging";
	
		return getInstance().getValue(serviceName, "base-dir", value);
	}
	
	/**
	 * Create or get service directory
	 * @param service
	 * @return
	 */
	public static String createServiceDirectory(String service) {
		if(isUseableString(service))
			return service;
		
		return getInstance().getValue(serviceName, "service-dir", "default");
	}

	/**
	 * Create or get filename
	 * @param fileName
	 * @return
	 */
	public static String createFileName(String fileName) {
		if(isUseableString(fileName))
			return fileName;

		return getInstance().getValue(serviceName, "file-name", "default");
	}
	
	/**
	 * Get file extension name
	 * @return file extension name
	 */
	public static String createFileExtensionName() {
		return getInstance().getValue(serviceName, "file-ext", "log");
	}
	
	/**
	 * Create buffer size
	 * @return buffer size
	 */
	public static int createBufferSize() {
		int value = 1024;
		try {
			value = Integer.parseInt(getInstance().getValue(serviceName, "buffer-size", String.valueOf(value)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * Get rolling strategy
	 * @return
	 */
	public static int createStrategy() {
		int value = 2;
		try {
			value = Integer.parseInt(getInstance().getValue(serviceName, "strategy", String.valueOf(value)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 *  Get cache size
	 * @return
	 */
	public static int createCacheSize() {
		int value = 200; // default 200
		try {
			value = Integer.parseInt(getInstance().getValue(serviceName, "cache-size", String.valueOf(value)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * Get level value 
	 * 
	 * @return
	 */
	public static int createLevelStrategy() {
		int value = 0;
		try {
			value = Integer.parseInt(getInstance().getValue(serviceName, "level", String.valueOf(value)));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return value;
	}
			
	/**
	 * Is Enable string 
	 *  
	 * @param value
	 * @return
	 */
	public static boolean isUseableString(String value) {
		if(value == null || value.trim().length() == 0)
			return false;
		
		return true;
	}
	

	
}
