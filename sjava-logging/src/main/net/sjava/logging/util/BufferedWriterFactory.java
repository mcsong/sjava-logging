package net.sjava.logging.util;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class BufferedWriterFactory {
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static BufferedWriter create(String fileName) throws IOException {
		return new BufferedWriter(new FileWriter(fileName, true), 1024);
	}
}
