/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.util;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2010. 1. 5.
 *
 */
public class ExceptionConverter {
	
	/**
	 * Get exception's call stack
	 * @param e
	 * @return
	 */
	public static String getExceptionCallStack(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
}
