package net.sjava.logging.test;

import net.sjava.logging.Logger;

public class LoggingThread extends Thread {

	public void run() {
		Logger.create().log("aaaaaaaaaaaaaaa");
		Logger.create().log("�����ٶ󸶹ٻ��");
		Logger.create().log("metoo", "ǪǪǪǪǪ��.. ");
		Logger.create().log("service", "fileName", "����");
	
	}
}
