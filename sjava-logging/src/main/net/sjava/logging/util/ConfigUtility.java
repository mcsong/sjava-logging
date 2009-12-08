package net.sjava.logging.util;

//import java.util.concurrent.TimeUnit;

import net.sjava.config.ConfigHandler;

public class ConfigUtility {
	/*
	<key name="base-dir" value="c:\sjava-log-dir" />
	<key name="service-dir" value="default" />
	<key name="fileName" value="default" />
	<key name="fileExt" value="log" />
	<key name="bufferSize" value="1024" />
	<!-- day(1), hour(2), minute(3) -->
	<key name="strategy" value="2" />
	*/
	
		
	/**
	 * Create or get base directory
	 */
	public static String createBaseDirectory(String directory) {
		if(ConfigUtility.isUseableString(directory))
			return directory;
		
		String value = "";
		String osName = System.getProperty("os.name").toLowerCase();
			
		if(osName.indexOf("linux") > -1)  // linux
			value = "//usr/sjava-logging";
		else if (osName.indexOf("mac") > -1)  // max
			value = "//usr/sjava-logging";
		else
			value = "d:\\sjava-logging";
	
		return ConfigHandler.getInstance().getValue("sjava-logging", "base-dir", value);
	}
	
	/**
	 * Create or get service directory
	 * @param service
	 * @return
	 */
	public static String createServiceDir(String service) {
		if(ConfigUtility.isUseableString(service))
			return service;
		
		return ConfigHandler.getInstance().getValue("sjava-logging", "service-dir", "default");
	}

	/**
	 * Create or get filename
	 * @param fileName
	 * @return
	 */
	public static String createFileName(String fileName) {
		if(ConfigUtility.isUseableString(fileName))
			return fileName;

		return ConfigHandler.getInstance().getValue("sjava-logging", "file-name", "default");
	}
	
	/**
	 * Get file extension name
	 * @return file extension name
	 */
	public static String createFileExtensionName() {
		return ConfigHandler.getInstance().getValue("sjava-logging", "file-ext", "log");
	}
	
	/**
	 * Create buffer size
	 * @return buffer size
	 */
	public static int createBufferSize() {
		int value = 1024;
		try {
			value = Integer.parseInt(ConfigHandler.getInstance().getValue("sjava-logging", "buffer-size", "1024"));
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
			value = Integer.parseInt(ConfigHandler.getInstance().getValue("sjava-logging", "strategy", "2"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int createCacheCount() {
		int value = 300; // default 200
		try {
			// default is 1 minute
			value = Integer.parseInt(ConfigHandler.getInstance().getValue("sjava-logging", "cache-size", "300"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * Is flush option
	 * @return
	 */
	/*
	public static boolean isFlushing() {
		String value = ConfigHandler.getInstance().getValue("sjava-logging", "flush-option", "false").toLowerCase();
		if(value.equals("true"))
			return true;
		
		return false;			
	}
	*/
	
	/**
	 * Get flush period
	 * @return
	 */
	/*
	public static int getFlushPeriod() {
		int value = 60; // 1Ка
		try {
			// default is 1 minute
			value = Integer.parseInt(ConfigHandler.getInstance().getValue("sjava-logging", "flush-period", "60"));
			//return Integer.parseInt(ConfigHandler.getInstance().getValue("sjava-logging", "flush-period", "60000"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// default flush period
			value = 60; // 1Ка
		}
		
		return value;
	}
	*/
	
	
	
	/**
	 * Is string empty
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isUseableString(String value) {
		if(value == null || value.length() == 0)
			return false;
		
		return true;
	}
	
	/**
	 * Get exception's call stack
	 * @param e
	 * @return
	 */
	public static String getExceptionCallStack(Exception e) {
		java.io.StringWriter sw = new java.io.StringWriter();
		e.printStackTrace(new java.io.PrintWriter(sw));
		return sw.toString();
	}
	
}
