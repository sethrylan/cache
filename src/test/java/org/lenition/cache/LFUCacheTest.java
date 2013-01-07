package org.lenition.cache;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;

/**
 *
 * @author gaineys
 */
public class LFUCacheTest extends CacheTest<LFUCache>{
        
    public LFUCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
        
    
    @Before
    public void setUp() {
        testCache = LFUCache.getDefaultInstance("testCache");
    }

    @After
    public void tearDown() {
        LFUCache.getDefaultInstance("testCache").removeAllCaches();
        testCache = null;
    }
    
    @Test
    public void testRemoveAllCaches() throws Exception {
        LFUCache testCache = LFUCache.getDefaultInstance("testCache");
        testCache.put("key", 1);
        testCache.removeAllCaches();
        Assert.assertNotNull(testCache);
        
        thrown.expect(NullPointerException.class);
        testCache.get("key");
    }
    
    
    
    @Test
    public void testRemoveCache() throws Exception {
        LFUCache testCache = LFUCache.getDefaultInstance("testCache");
        testCache.put("key", 1);
        testCache.removeCache("testCache");
        Assert.assertNotNull(testCache);
        
        thrown.expect(NullPointerException.class);
        testCache.get("key");
    }   

    @Test
    public void testGetDefaultInstance() {
        LFUCache testCache = LFUCache.getDefaultInstance("testCache");
        Assert.assertNotNull(testCache);
        Assert.assertEquals(172800 ,testCache.getTimeToLiveInSeconds());
        Assert.assertEquals(1000, testCache.getMaxElements());
        Assert.assertEquals(testCache.getCacheName(), "testCache");
        Assert.assertEquals(testCache.isPutNullsInCache(), true);
    }

    @Test
    public void testGetInstance() {
        LFUCache testCache = LFUCache.getInstance("testCache", 1000, 1000, 1000, true);
        Assert.assertNotNull(testCache);
        Assert.assertEquals(1000 ,testCache.getTimeToLiveInSeconds());
        Assert.assertEquals(1000, testCache.getMaxElements());
        Assert.assertEquals(1000, testCache.getTimeToIdleInSeconds());
        Assert.assertEquals("testCache", ((LFUCache)testCache).getCacheName());
        Assert.assertEquals(true, testCache.isPutNullsInCache());
    }
    

    
    @Test
    public void testGetExistingInstance() {
        LFUCache cache1 = LFUCache.getInstance("testCache", 1000, 1000, 1000, false);
        LFUCache cache2 = LFUCache.getInstance("testCache", 1000, 1000, 1000, false);
        Assert.assertEquals(cache1.getCacheName(), cache2.getCacheName());
        
        cache1.put("key", 1);
        cache2.put("keyString", "string");
        Assert.assertEquals(cache1.get("key"), cache2.get("key"));
        Assert.assertEquals(cache1.get("keyString"), cache2.get("keyString"));
        
        LFUCache cache3 = LFUCache.getInstance("testCache", 999, 999, 999, true);
        Assert.assertEquals(cache1.get("key"), cache3.get("key"));
        Assert.assertEquals(cache1.get("keyString"), cache3.get("keyString"));
        
        LFUCache cache4 = LFUCache.getInstance("anotherCache", 888, 888, 888, true);
        
        Assert.assertEquals(1000, cache1.getTimeToIdleInSeconds());
        Assert.assertEquals(1000, cache1.getTimeToLiveInSeconds());
        Assert.assertEquals(1000, cache1.getMaxElements());
        Assert.assertEquals(false, cache1.isPutNullsInCache());
        
        Assert.assertEquals(999, cache3.getTimeToIdleInSeconds());
        Assert.assertEquals(999, cache3.getTimeToLiveInSeconds());
        Assert.assertEquals(999, cache3.getMaxElements());
        Assert.assertEquals(true, cache3.isPutNullsInCache());
                
        Assert.assertEquals(888, cache4.getTimeToIdleInSeconds());
        Assert.assertEquals(888, cache4.getTimeToLiveInSeconds());
        Assert.assertEquals(888, cache4.getMaxElements());
        Assert.assertEquals(true, cache4.isPutNullsInCache());
        
        
    }

   
}
