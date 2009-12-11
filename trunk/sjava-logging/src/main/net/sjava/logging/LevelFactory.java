/**
 * http://www.sjava.net/category/Sjava%27s%20Library/sjava-logging
 */
package net.sjava.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 1.
 * @version 
 */
public class LevelFactory {	
	/** map of levels	 */
	private Map<String, Level> levelMap;

	/** singleton instance */
	private static LevelFactory instance = new LevelFactory();
	
	/** constructor */
	private LevelFactory() {
		this.levelMap = new HashMap<String, Level>();
		
		this.levelMap.put("all", new Level(0, "all"));
		this.levelMap.put("fatal", new Level(1, "fatal"));
		this.levelMap.put("error", new Level(2, "error"));
		this.levelMap.put("warn", new Level(3, "warn"));
		this.levelMap.put("info", new Level(4, "info"));
		this.levelMap.put("debug", new Level(5, "debug"));
		this.levelMap.put("trace", new Level(6, "trace"));
		this.levelMap.put("system", new Level(7, "system"));
	}
	
	/**
	 * 
	 * @return
	 */
	public static LevelFactory getInstance() {
		return instance;
	}
	
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	public Level getLevel(int level) {
		switch (level) {
		case 1:
			return this.getLevel("fatal");
		case 2:
			return this.getLevel("error");
		case 3:
			return this.getLevel("warn");
		case 4:
			return this.getLevel("info");
		case 5:
			return this.getLevel("debug");
		case 6:
			return this.getLevel("trace");
		case 7:
			return this.getLevel("system");
			
		default:
			return this.getLevel("all");
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Level getLevel(String name) {
		if (name == null)
			return null;
		
		if(!this.levelMap.containsKey(name.toLowerCase()))
			return (Level)this.levelMap.get("all");
		
		return (Level)this.levelMap.get(name.toLowerCase());
	}
}
