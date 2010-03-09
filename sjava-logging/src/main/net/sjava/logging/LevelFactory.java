/**
 * http://www.sjava.net/category/sjava%20project
 */
package net.sjava.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 레벨을 유지하고 있는 Factory 클래스
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 1.
 * @version 
 */
public class LevelFactory {	

	/** levels */
	private static Map<String, Level> levels;

	/** inialize */
	static {
		levels = new ConcurrentSkipListMap<String, Level>();
		
		levels.put("all", new Level(0, "all"));
		levels.put("fatal", new Level(1, "fatal"));
		levels.put("error", new Level(2, "error"));
		levels.put("warn", new Level(3, "warn"));
		levels.put("info", new Level(4, "info"));
		levels.put("debug", new Level(5, "debug"));
		levels.put("trace", new Level(6, "trace"));
		levels.put("system", new Level(7, "system"));
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	public static Level getLevel(int level) {
		switch (level) {
		case 1:
			return getLevel("fatal");
		case 2:
			return getLevel("error");
		case 3:
			return getLevel("warn");
		case 4:
			return getLevel("info");
		case 5:
			return getLevel("debug");
		case 6:
			return getLevel("trace");
		case 7:
			return getLevel("system");
			
		default:
			return getLevel("all");
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Level getLevel(String name) {
		if (name == null)
			return null;
		
		if(!levels.containsKey(name.toLowerCase()))
			return (Level)levels.get("all");
		
		return (Level)levels.get(name.toLowerCase());
	}
}
