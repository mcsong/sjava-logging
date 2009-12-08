package net.sjava.logging.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author mcsong@gmail.com
 * @since 2009. 7. 8.
 */
public class BufferedWriterCacheUtility {
	
	/** max cache size */
	//private static final int size = 100;
	private static final int size = 100;
	
	/** lock */
	private static Lock lock = new ReentrantLock();
	
	/** initialized lru cache */
	private static Map<String, BufferedWriter> cache = new LinkedHashMap<String, BufferedWriter> (size, .75F, true) {
		private static final long serialVersionUID = 1L;
		protected boolean removeEldestEntry(Map.Entry<String, BufferedWriter> eldest) {
			if(size() > size) {
				lock.lock();
				try {
					// flush before deleted
					eldest.getValue().close();
				} catch(java.io.IOException e) {
					// ignore
				} finally {
					lock.unlock();
				}
			}
			
			return size() > size;   
		}
	};
	
	

	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static BufferedWriter createBufferedWriter(String fileName) {
		lock.lock();
		try {
			if(cache.containsKey(fileName))
				return cache.get(fileName);
		
			return new BufferedWriter(new FileWriter(fileName, true), 1024);
		} catch(java.io.IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 
	 * @param fileName
	 * @param writer
	 */
	public static void close(String fileName, BufferedWriter writer) {
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
	public static void shutdown() {
		Iterator<BufferedWriter> iter = null;
		lock.lock();
		try {
		    iter = cache.values().iterator();
		    while(iter.hasNext())
		    	iter.next().close();
		    
		} catch(java.io.IOException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * Flush all cached BufferedWriter instance
	 */
	public static void flushAll() {
		Iterator<BufferedWriter> iter = null;
		lock.lock();
		try {
		    iter = cache.values().iterator();
		    while(iter.hasNext()) {
		    	iter.next().flush();
		    }
		    
		} catch(java.io.IOException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	
	
}
