package net.sjava.logging.test;

import net.sjava.logging.Logger;

public class LoggingThread extends Thread {

	public void run() {
		Logger.create().log("aaaaaaaaaaaaaaa");
		Logger.create().log("°¡³ª´Ù¶ó¸¶¹Ù»ç¾Æ");
		Logger.create().log("metoo", "ÇªÇªÇªÇªÇª¹Ú.. ");
		Logger.create().log("service", "fileName", "³»¿ë");
	
	}
}
