package net.sjava.logging.util;

import java.util.Map;
import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2801436909858929375L;
	
	/**
	 * 
	 */
	private int maxsize;
 
	/**
	 * 
	 * @param maxsize
	 */
    public LRUCache(int maxsize) {
    	super(maxsize * 4 / 3 + 1, 0.75f, true);
    	this.maxsize = maxsize;
    }
 
    /**
     * 
     */
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
    	return size() > maxsize;
    }
}
