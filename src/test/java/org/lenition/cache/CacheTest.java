package org.lenition.cache;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 *
 * @author gaineys
 */
public abstract class CacheTest<T> {
        
    
    protected Cache testCache;
    
    public CacheTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
        
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    

    @Test
    public void testPut() {
        
        testCache.put("key1", 1);
        testCache.put("key2", 2);
        testCache.put("keyString", "string");         
        
        testCache.put("key1", 1);
        testCache.put("key1", 2);
        testCache.put("key1", "key");

    }
    
    @Test
    public void testPutArrays(){
        
        int[] arr = {1,2,3,4,5};
        testCache.put("key",arr);
        Assert.assertArrayEquals(arr, (int[])testCache.get("key"));
    }
    
    
    @Test
    public void testGet() {
        
        Assert.assertNull(testCache.get("key"));
        
        testCache.put("key1", 1);
        testCache.put("key2", 2);
        testCache.put("keyString", "string");
        
        Assert.assertEquals(1, testCache.get("key1"));
        Assert.assertEquals(2, testCache.get("key2"));
        Assert.assertEquals("string", testCache.get("keyString"));
        
        testCache.put("key1", 1);
        Assert.assertEquals(1, testCache.get("key1"));

        testCache.put("key1", 2);
        Assert.assertEquals(2, testCache.get("key1"));

        testCache.put("key1", "key");
        Assert.assertEquals("key", testCache.get("key1"));
        
    }

    @Test
    public void testDelete() {

        testCache.put("key1", 1);
        testCache.delete("key");
        
        Assert.assertNotNull(testCache.get("key1"));
        
        testCache.delete("key1");
        Assert.assertNull(testCache.get("key1"));
        
    }

    @Test
    public void testFlush() {
    }
    
    @Test
    public void testSize() {
        Assert.assertEquals(0, testCache.size());        
        for(int i=1; i<=testCache.getMaxElements(); i++) {
            testCache.put(i, String.valueOf(i));
            Assert.assertEquals(i, testCache.size());
            testCache.delete(i);
            Assert.assertEquals(i-1, testCache.size());
            testCache.put(i, String.valueOf(i));
            Assert.assertEquals(i, testCache.size());
        }
        
        Assert.assertEquals(testCache.getMaxElements(), testCache.size());
    }
    
    @Test
    public void testGetGenericsInstance() {
        Assert.assertNotNull(testCache);
        testCache.put("key", new ArrayList() {{ add(1);}});
        Assert.assertTrue(testCache.get("key") instanceof List);
        Assert.assertTrue(testCache.get("key") instanceof ArrayList);
//        testCache.put("key", "not a list");
//        Assert.assertTrue(testCache.get("key") instanceof String);
        
    }
    
    @Test
    public void testGetWithoutGenericsInstance() {
        Assert.assertNotNull(testCache);
        testCache.put("key", new ArrayList() {{ add(1);}});
        testCache.put("key", "not a list");
        Assert.assertTrue(testCache.get("key") instanceof Object);
        Assert.assertTrue(testCache.get("key") instanceof String);
    }

}
