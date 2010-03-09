/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.junit;


import junit.framework.Test;
import junit.framework.TestSuite;

public class LoggerTestSuite extends TestSuite {
	
	public static Test suite() {
		TestSuite suite01 = new TestSuite("sjava-logging test suite");
		suite01.addTestSuite(LoggerTest.class);
		
		
		
		//TestSuite suite02 = new TestSuite("icafe-config test suite");
		//suite02.addTestSuite(ConfigSetterTest.class);
		
		
		return suite01;
		
	}
	

}
