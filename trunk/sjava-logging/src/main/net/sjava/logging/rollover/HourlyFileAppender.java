/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging.rollover;


import java.io.File;
import java.io.BufferedWriter;

import net.sjava.logging.Level;
import net.sjava.logging.util.BufferedWriterFactory;
import net.sjava.logging.util.SimpleDateFormatFactory;

import static net.sjava.logging.util.ConstantsFactory.createBaseDirectory;
import static net.sjava.logging.util.ConstantsFactory.createServiceDirectory;
import static net.sjava.logging.util.ConstantsFactory.createFileName;
import static net.sjava.logging.util.ConstantsFactory.createFileExtensionName;

public class HourlyFileAppender extends AbstractFileAppender {
    
	/**
	 * 
	 * @return
	 */
	public static HourlyFileAppender create(){
		return new HourlyFileAppender();
	}	
	
	@Override
	boolean setDirectory(String directory, String serviceName) {
		StringBuilder builder = new StringBuilder(256);
		
		builder.append(createBaseDirectory(directory));		
		builder.append(System.getProperty("file.separator"));
		builder.append(createServiceDirectory(serviceName));
		builder.append(System.getProperty("file.separator") + super.year);
		builder.append(System.getProperty("file.separator") + super.month);
		builder.append(System.getProperty("file.separator") + super.day); 
		
		super.logfileName = builder.toString();
		File file = new File(builder.toString());
		if(file.exists())
			return true;
		
		return file.mkdirs();
	}

	@Override
	void setFile(Level level, String fileName) {
		
		StringBuilder builder = new StringBuilder(256);
		builder.append(super.logfileName);
		builder.append(System.getProperty("file.separator"));
		builder.append(super.year + ".");
		builder.append(super.month + ".");
		builder.append(super.day + ".");	
		builder.append(super.hour);
		builder.append("-" + createFileName(fileName));
		builder.append( "-" + level.getName());
		builder.append("." + createFileExtensionName());
		
		super.logfileName = builder.toString();
	}	
	
	
	@Override
	public void write(String serviceName, String fileName, Level level, String data) {
		try {
			BufferedWriter bwriter = BufferedWriterFactory.create(super.logfileName);
			synchronized(bwriter) {
				bwriter.write(SimpleDateFormatFactory.createLogFormat().format(super.date));
				bwriter.write(" [" + level.getName().toLowerCase() +"] ");
				bwriter.write(data);
				bwriter.newLine();
				bwriter.flush();
			}
			BufferedWriterFactory.close(super.logfileName, bwriter);
		} catch(Exception e) {
			// ignore because not critical
			e.printStackTrace();	
		}
	}	
}
