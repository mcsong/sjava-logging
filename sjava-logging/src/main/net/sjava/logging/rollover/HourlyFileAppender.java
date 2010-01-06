package net.sjava.logging.rollover;


import java.io.File;
import java.io.BufferedWriter;

import net.sjava.logging.Level;
import net.sjava.logging.util.ConfigUtility;
import net.sjava.logging.util.BufferedWriterFactory;
import net.sjava.logging.util.SimpleDateFormatFactory;

public class HourlyFileAppender extends AbstractFileAppender {
    
	/**
	 * 
	 * @return
	 */
	public static HourlyFileAppender create(){
		return new HourlyFileAppender();
	}	
	
	@Override
	void setDirectory(String directory, String serviceName) {
		StringBuilder builder = new StringBuilder(256);
		
		builder.append(ConfigUtility.createBaseDirectory(directory));		
		builder.append(System.getProperty("file.separator"));
		builder.append(ConfigUtility.createServiceDir(serviceName));
		builder.append(System.getProperty("file.separator") + super.year);
		builder.append(System.getProperty("file.separator") + super.month);
		builder.append(System.getProperty("file.separator") + super.day); 
		
		super.logfileName = builder.toString();
		File file = new File(builder.toString());
		if(file.exists())
			return;
		
		file.mkdirs();
	}

	@Override
	void setFile(Level level, String fileName) {
		// TODO Auto-generated method stub
		
		StringBuilder builder = new StringBuilder(256);
		builder.append(super.logfileName);
		builder.append(System.getProperty("file.separator"));
		builder.append(super.year + ".");
		builder.append(super.month + ".");
		builder.append(super.day + ".");	
		builder.append(super.hour);
		builder.append("-" + ConfigUtility.createFileName(fileName));
		builder.append( "-" + level.getName());
		builder.append("." + ConfigUtility.createFileExtensionName());
		
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
