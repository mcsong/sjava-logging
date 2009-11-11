package net.sjava.logging.util;

//import java.util.concurrent.TimeUnit;

import net.sjava.config.ConfigHandler;

public class ConfigUtility {
	/*
	<key name="baseDir" value="c:\sjava-log-dir" />
	<key name="serviceDir" value="default" />
	<key name="fileName" value="default" />
	<key name="fileExt" value="log" />
	<key name="bufferSize" value="1024" />
	<!-- day(1), hour(2), minute(3) -->
	<key name="strategy" value="2" />
	*/
	
	
	
	/**
	 * Create or get base directory
	 */
	public static String createBaseDir(String dir) {		
		if(dir == null) {
			String osName = System.getProperty("os.name").toLowerCase();
			String defaultValue = "d:\\sjava-logging";
			
			if(osName.indexOf("linux") > -1) { // linux
				defaultValue = "//usr/sjava-logging";
			} else if (osName.indexOf("mac") > -1) { // max
				defaultValue = "//usr/sjava-logging";
			} 
	
			return ConfigHandler.getInstance().getValue("logging", "baseDir", defaultValue);
		}
		
		return dir;
	}
	
	/**
	 * Create or get service directory
	 * @param service
	 * @return
	 */
	public static String createServiceDir(String service) {
		if(ConfigUtility.isUseableString(service))
			return service;
		
		return ConfigHandler.getInstance().getValue("logging", "serviceDir", "default");
	}

	/**
	 * Create or get filename
	 * @param fileName
	 * @return
	 */
	public static String createFileName(String fileName) {
		if(ConfigUtility.isUseableString(fileName))
			return fileName;

		return ConfigHandler.getInstance().getValue("logging", "fileName", "default");
	}
	
	/**
	 * Get file extension name
	 * @return file extension name
	 */
	public static String getFileExtensionName() {
		return ConfigHandler.getInstance().getValue("logging", "fileExt", "log");
	}
	
	/**
	 * Get buffer size
	 * @return buffer size
	 */
	public static int getBufferSize() {
		try {
			return Integer.parseInt(ConfigHandler.getInstance().getValue("logging", "bufferSize", "1024"));
		} catch (NumberFormatException e) {
			System.out.println("BufferSize format Exception");
			// default buffer size
			return 1024;
		}
	}
	
	/**
	 * Get rolling strategy
	 * @return
	 */
	public static int getRollingStrategy() {
		try {
			return Integer.parseInt(ConfigHandler.getInstance().getValue("logging", "strategy", "2"));
		} catch (NumberFormatException e) {
			System.out.println("RollingStrategy format Exception");
			// default strategy 
			return 2;
		}
	}
	
	
	/**
	 * Is flush option
	 * @return
	 */
	public static boolean isFlushing() {
		return Boolean.getBoolean(ConfigHandler.getInstance().getValue("logging", "flush-option", "false"));			
	}
	
	/**
	 * Get flush period
	 * @return
	 */
	public static int getFlushPeriod() {
		try {
			// default is 1 minute 
			return Integer.parseInt(ConfigHandler.getInstance().getValue("logging", "flush-period", "60000"));
		} catch (NumberFormatException e) {
			System.out.println("FlushPeriod format Exception");
			// default flush period
			return 60000;
		}
	}
	
	
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
}
