package org.lenition.cache;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 * @author gaineys
 */
public class AbstractCache<K, V> implements org.lenition.cache.Cache<K, V> {
    
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractCache.class);
    
    protected static final int SECONDS_PER_DAY = 86400;
    static int defaultTimeToLiveInSeconds;
    static int defaultTimeToIdleInSeconds;
    static int defaultMaxElements;
    static boolean defaultPutNullsInCache;
    
    protected int timeToLiveInSeconds;
    protected int timeToIdleInSeconds;
    protected int maxElements;
    protected boolean putNullsInCache;

    public AbstractCache() {
    }
    
    protected final static void loadProperties(){
        Properties props = new Properties();
        try {
            props.load(AbstractCache.class.getClassLoader().getResourceAsStream("cache.properties"));
        } catch (IOException ex) {
            logger.error(null, ex);
        }
        defaultTimeToLiveInSeconds = Integer.valueOf(props.getProperty("default.timeToLiveInSeconds"));
        defaultTimeToIdleInSeconds = Integer.valueOf(props.getProperty("default.timeToIdleInSeconds"));
        defaultMaxElements = Integer.valueOf(props.getProperty("default.maxElements"));
        defaultPutNullsInCache = Boolean.valueOf(props.getProperty("default.putNullsInCache"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(K key, V value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long getTimeToLiveInSeconds() {
        return timeToLiveInSeconds;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTimeToIdleInSeconds() {
        return timeToIdleInSeconds;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public long getMaxElements() {
        return maxElements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPutNullsInCache() {
        return putNullsInCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
