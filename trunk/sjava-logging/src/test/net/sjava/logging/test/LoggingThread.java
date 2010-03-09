/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.test;

import net.sjava.logging.Logger;

public class LoggingThread extends Thread {

	public void run() {
		Logger.open().fatal("aaaaaaaaaaaaaaa", "asdsda", "dfjahshhjkshjkldfhjkldafdfadfadfhjkldfkj");
		//Logger.open().log("aaaaaaaaaaaaccccccccccccccccccaaa");
		Logger.open().error("metoo", "ǪǪǪǪǪ��.. ", "adaas");
		Logger.open().debug("abcded", "abcde" , "aaaa b aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	
	}
}
