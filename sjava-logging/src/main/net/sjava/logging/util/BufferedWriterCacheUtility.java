package net.sjava.logging.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 8.
 */

public class BufferedWriterCacheUtility {
	
	/** 
	 * max cache size 
	 */
	private static final int size = 100;
	
	/** 
	 * lock 
	 */
	private static Lock lock = new ReentrantLock();
	
	/** 
	 * initialized lru cache 
	 */         
	private static Map<String, BufferedWriter> cache = null;
	
	static {
		cache = new LRUCache<String, BufferedWriter>(200);
		cache = (Map<String, BufferedWriter>)Collections.synchronizedMap(cache);
	}
	

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	
	public static BufferedWriter createWriter(String fileName) throws Exception {
		lock.lock();
		try {
			if(cache.containsKey(fileName))
				return cache.get(fileName);
		
			return new BufferedWriter(new FileWriter(fileName, true), 1024);
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @param writer
	 */
	
	public static void close(String fileName, BufferedWriter writer) throws Exception {
		lock.lock();
		try {
			writer.flush();
			cache.put(fileName, writer);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	

	/**
	 * shutdown hooking thread call
	 */

	public static void shutdown() throws Exception {
		lock.lock();
		try {
			Iterator<BufferedWriter> iter = cache.values().iterator();
		    while(iter.hasNext())
		    	iter.next().close();
		    
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Flush all cached BufferedWriter instance
	 */
	
	public static void flushAll() throws Exception {
		lock.lock();
		try {
			Iterator<BufferedWriter> iter = cache.values().iterator();
		    while(iter.hasNext())
		    	iter.next().flush();
		    
		} finally {
			lock.unlock();
		}
	}
	
	
	
}
