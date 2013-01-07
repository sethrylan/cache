package org.lenition.cache;

/**
 * An interface for a generic associative array.
 * @author gaineys
 * @param <K> object type for keys
 * @param <V> object type for values
 */
public interface Cache<K, V> {

    /**
     * Inserts a key-value pair. Inserting a key that already exists will 
     * overwrite the existing value. If invoking put increase the cache size 
     * beyond maxElements, then a key-value element is dropped based on the 
     * algorithm left to implementation.
     * @param key key for the associative array element
     * @param value value for the associative array element
     */
    void put(K key, V value);

    /**
     * Returns the value for the key, if one exists.
     * @param key key for the desired element
     * @return Value for the associated element, or null if none exists
     */
    V get(K key);

    /**
     * Removes a key-value pair.
     * @param key key for the key-value pair to remove
     */
    void delete(K key);

    
    /**
     * Returns the current number of key-value elements in this cache.
     * @return number of cache elements
     */
    int size();

    /**
     * Returns the time-to-live (maximum time between creation time and when an element expires) for this cache in seconds.
     * @return time-to-live in seconds
     */
    long getTimeToLiveInSeconds();

    /**
     * Returns the time-to-idle (maximum amount of time between accesses before an element expires) for this cache in seconds.
     * @return time-to-idle in seconds
     */
    long getTimeToIdleInSeconds();
    
    /**
     * Returns maximum number of elements allowed for this cache.
     * @return maximum number of elements
     */
    long getMaxElements();
    
    /**
     * Returns whether nulls values are allowed in this cache.
     * @return true if null values can be put into the cache, false if not
     */
    boolean isPutNullsInCache();
        
}