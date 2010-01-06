package net.sjava.logging.util;

import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferedWriterFactory {
	/** 
	 * max cache size 
	 */
	private static final int maxSize = ConfigUtility.createCacheCount();
	
	/** 
	 * lock 
	 */
	private static Lock lock = new ReentrantLock();
	
	/** 
	 * initialized lru cache 
	 */         
	private static Map<String, BufferedWriter> cache = null;
	
	/**
	 * 
	 */
	static {
		cache = new LRUCache<String, BufferedWriter>(maxSize);
		cache = (Map<String, BufferedWriter>)Collections.synchronizedMap(cache);
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static BufferedWriter create(String fileName) throws IOException {
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
			cache.put(fileName, writer);
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
