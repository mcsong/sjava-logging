package net.sjava.logging.junit;

import net.sjava.logging.Logger;
import junit.framework.Assert;
import junit.framework.TestCase;

public class LoggerTest extends TestCase {

	private Logger logger = null;
	
	public void setUp(){
		this.logger = Logger.getInstance();
	}
	
	public void testGetInstacne() {
		Assert.assertNotNull(this.logger);
	}
	
	public void tearDown() { 
		this.logger = null;
	}	
	
}
