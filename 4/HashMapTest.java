import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
//Import the rules ExpectedException object
import org.junit.rules.ExpectedException;
//Import the NoSuchElementException
import java.util.NoSuchElementException;

/**
 * HashMapTest - Test HashMap class include constructors and methods
 * 
 * <pre>
 * 
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: April 10, 2017
 * 
 * Revision Log
 * Who          When          Reason
 * ------------ ------------- ---------------------------------
 * 
 * </pre>
 * 
 * @author Zhiyue Zhao 
 * @version 1.0
 */
public class HashMapTest
{
    private static final int TEST_CAPACITY = 5;
    private static final double TEST_LOAD_FACTOR = .5;
    
    private static final int TEST_DEFAULT_CAPACITY = 11;
    private static final double TEST_DEFAULT_LOAD_FACTOR = .75;
    private HashMap hashMap;
    
    private Class<?> entryClass;
    private Object entryClassObject;
    private Constructor<?> entryAllArgConstructor;
    
    private Class<?> keyIteratorClass;
    private Object keyIteratorClassObject;
    private Constructor<?> keyIteratorAllArgConstructor;
    
    private Class<?> valueIteratorClass;
    private Object valueIteratorClassObject;
    private Constructor<?> valueIteratorAllArgConstructor;
    
    /**
     * Default constructor for test class HashMapTest
     */
    public HashMapTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        entryClass = Class.forName("HashMap$Entry");
        Class[] params1= {HashMap.class, Object.class, Object.class};
        entryAllArgConstructor = entryClass.getDeclaredConstructor(params1);
        entryAllArgConstructor.setAccessible(true);
        
        keyIteratorClass = Class.forName("HashMap$KeyIterator");
        keyIteratorAllArgConstructor = keyIteratorClass.getDeclaredConstructors()[0];
        keyIteratorAllArgConstructor.setAccessible(true);
        
        valueIteratorClass = Class.forName("HashMap$ValueIterator");
        valueIteratorAllArgConstructor = valueIteratorClass.getDeclaredConstructors()[0];
        valueIteratorAllArgConstructor.setAccessible(true);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test the no arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testNoArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        hashMap = new HashMap();    
        
        //test the private field size
        privateField = HashMap.class.getDeclaredField("size");
        privateField.setAccessible(true);
        assertEquals(0, privateField.get(hashMap));
        
        //test the private field loadFactor
        privateField = HashMap.class.getDeclaredField("loadFactor");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field DEFAULT_CAPACITY
        privateField = HashMap.class.getDeclaredField("DEFAULT_CAPACITY");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_CAPACITY, privateField.get(hashMap));
        
        //test the private field DEFAULT_LOAD_FACTOR
        privateField = HashMap.class.getDeclaredField("DEFAULT_LOAD_FACTOR");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field threshold
        privateField = HashMap.class.getDeclaredField("threshold");
        privateField.setAccessible(true);
        int testThreshold = (int)(TEST_DEFAULT_LOAD_FACTOR*TEST_DEFAULT_CAPACITY);
        assertEquals(testThreshold, privateField.get(hashMap));
        
        ///test the private field threshold
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_CAPACITY, ((Object[])privateField.get(hashMap)).length);
        
        //test the private field available
        privateField = HashMap.class.getDeclaredField("available");
        privateField.setAccessible(true);
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, null, null);
        Method privateMethod = entryClass.getDeclaredMethod("getKey");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
    }
    
    /**
     * Test the One arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testOneArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        hashMap = new HashMap(TEST_CAPACITY);
        
        //test the private field size
        privateField = HashMap.class.getDeclaredField("size");
        privateField.setAccessible(true);
        assertEquals(0, privateField.get(hashMap));
        
        //test the private field loadFactor
        privateField = HashMap.class.getDeclaredField("loadFactor");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field DEFAULT_CAPACITY
        privateField = HashMap.class.getDeclaredField("DEFAULT_CAPACITY");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_CAPACITY, privateField.get(hashMap));
        
        //test the private field DEFAULT_LOAD_FACTOR
        privateField = HashMap.class.getDeclaredField("DEFAULT_LOAD_FACTOR");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field threshold
        privateField = HashMap.class.getDeclaredField("threshold");
        privateField.setAccessible(true);
        int testThreshold = (int)(TEST_DEFAULT_LOAD_FACTOR*TEST_CAPACITY);
        assertEquals(testThreshold, privateField.get(hashMap));
        
        ///test the private field threshold
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        assertEquals(TEST_CAPACITY, ((Object[])privateField.get(hashMap)).length);
        
        //test the private field available
        privateField = HashMap.class.getDeclaredField("available");
        privateField.setAccessible(true);
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, null, null);
        Method privateMethod = entryClass.getDeclaredMethod("getKey");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
    }
    
    /**
     * Test the All arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        hashMap = new HashMap(TEST_CAPACITY, TEST_LOAD_FACTOR);
        
        //test the private field size
        privateField = HashMap.class.getDeclaredField("size");
        privateField.setAccessible(true);
        assertEquals(0, privateField.get(hashMap));
        
        //test the private field loadFactor
        privateField = HashMap.class.getDeclaredField("loadFactor");
        privateField.setAccessible(true);
        assertEquals(TEST_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field DEFAULT_CAPACITY
        privateField = HashMap.class.getDeclaredField("DEFAULT_CAPACITY");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_CAPACITY, privateField.get(hashMap));
        
        //test the private field DEFAULT_LOAD_FACTOR
        privateField = HashMap.class.getDeclaredField("DEFAULT_LOAD_FACTOR");
        privateField.setAccessible(true);
        assertEquals(TEST_DEFAULT_LOAD_FACTOR, privateField.get(hashMap));
        
        //test the private field threshold
        privateField = HashMap.class.getDeclaredField("threshold");
        privateField.setAccessible(true);
        int testThreshold = (int)(TEST_LOAD_FACTOR*TEST_CAPACITY);
        assertEquals(testThreshold, privateField.get(hashMap));
        
        ///test the private field threshold
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        assertEquals(TEST_CAPACITY, ((Object[])privateField.get(hashMap)).length);
        
        //test the private field available
        privateField = HashMap.class.getDeclaredField("available");
        privateField.setAccessible(true);
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, null, null);
        Method privateMethod = entryClass.getDeclaredMethod("getKey");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(privateField.get(hashMap)));
    }
    
    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetSize()
    {
        hashMap = new HashMap();
        assertEquals(0, hashMap.getSize());
        hashMap.put("Helmet", "3");
        assertEquals(1, hashMap.getSize());
        hashMap.put("Diamond", "4");
        assertEquals(2, hashMap.getSize());
    }
    
    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testIsEmpty()
    {
        hashMap = new HashMap();
        assertTrue(hashMap.isEmpty());
        hashMap.put("Helmet", "3");
        assertFalse(hashMap.isEmpty());
    }
    
    /**
     * Test the clear method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testClear()
    {
        hashMap = new HashMap();
        assertTrue(hashMap.isEmpty());
        hashMap.put("Helmet", "3");
        hashMap.put("Diamond", "4");
        hashMap.clear();
        assertTrue(hashMap.isEmpty());
    }
    
    /**
     * Test the get method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGet()
    {
        hashMap = new HashMap();
        hashMap.put("Helmet", "3");
        hashMap.put("Diamond", "4");
        assertEquals("3", hashMap.get("Helmet"));
        assertEquals("4", hashMap.get("Diamond"));
    }
    
    /**
     * Test the put method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testPut() throws NoSuchFieldException, IllegalAccessException
    {
        Field privateField;
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put("Helmet", "3");
        hashMap.put("Diamond", "4");
        assertEquals("3", hashMap.get("Helmet"));
        assertEquals("4", hashMap.get("Diamond"));
        hashMap.put("Helmet", "5");
        assertEquals("5", hashMap.get("Helmet"));
        assertEquals(2, hashMap.getSize());
        
        hashMap.put("Armor", "6");
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        assertEquals(5, ((Object[])privateField.get(hashMap)).length);
        
        hashMap.put("Crossbow", "7");
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        assertEquals(11, ((Object[])privateField.get(hashMap)).length);
    }
    
    /**
     * Test the put method to ensure it returns the proper value.
     */
    @SuppressWarnings("unchecked")
    @Test (expected = IllegalArgumentException.class)
    public void testExceptionForPut()
    {
        Field privateField;
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put(null, "3");
    }
    
    /**
     * Test the remove method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRemove()
    {
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put("Helmet", "3");
        hashMap.put("Diamond", "4");
        
        assertEquals(2, hashMap.getSize());
        assertEquals("3", hashMap.remove("Helmet"));
        assertEquals(1, hashMap.getSize());
        
        assertNull(hashMap.remove("Helmet"));
        assertEquals(1, hashMap.getSize());
    }
    
    /**
     * Test the keys method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testKeys()
    {
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        Iterator itr = hashMap.keys();
        assertTrue(itr.hasNext());
        assertEquals("H", itr.next());
        
        assertTrue(itr.hasNext());
        assertEquals("D", itr.next());
        
        assertFalse(itr.hasNext());
    }
    
    /**
     * Test the values method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testValues()
    {
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        Iterator itr = hashMap.values();
        assertTrue(itr.hasNext());
        assertEquals("3", itr.next());
        
        assertTrue(itr.hasNext());
        assertEquals("4", itr.next());
        
        assertFalse(itr.hasNext());
    }
    
    /**
     * Test the findBucket method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testFindBucket() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Method privateMethod;
        
        hashMap = new HashMap(TEST_CAPACITY);
        
        privateMethod = HashMap.class.getDeclaredMethod("findBucket", Object.class);
        privateMethod.setAccessible(true);
        assertEquals(2, (int)privateMethod.invoke(hashMap, "H"));
        
        hashMap.put("H", "3");
        assertEquals(2, (int)privateMethod.invoke(hashMap, "H"));
        
        assertEquals(3, (int)privateMethod.invoke(hashMap, "D"));
    }
    
    /**
     * Test the findMatchingBucket method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testFindMatchingBucket() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Method privateMethod;
        
        hashMap = new HashMap(TEST_CAPACITY);
        
        privateMethod = HashMap.class.getDeclaredMethod("findMatchingBucket", Object.class);
        privateMethod.setAccessible(true);
        assertEquals(-1, (int)privateMethod.invoke(hashMap, "H"));
        
        hashMap.put("H", "3");
        assertEquals(2, (int)privateMethod.invoke(hashMap, "H"));
        
        assertEquals(-1, (int)privateMethod.invoke(hashMap, "D"));
    }
    
    /**
     * Test the resize method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testResize() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        Method privateMethod;
        
        hashMap = new HashMap(TEST_CAPACITY);
        
        privateMethod = HashMap.class.getDeclaredMethod("resize");
        privateMethod.setAccessible(true);
        assertEquals(11, privateMethod.invoke(hashMap));
    }
    
    /**
     * Test the rehash method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRehash() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException
    {
        Method privateMethod;
        Field privateField;
        
        hashMap = new HashMap(TEST_CAPACITY);
        hashMap.put("H", "3");
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        
        privateMethod = entryClass.getDeclaredMethod("getKey");
        
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(((Object[])privateField.get(hashMap))[2]));
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(((Object[])privateField.get(hashMap))[2]));
        
        privateMethod = HashMap.class.getDeclaredMethod("rehash");
        privateMethod.setAccessible(true);
        privateMethod.invoke(hashMap);
        
        privateMethod = entryClass.getDeclaredMethod("getKey");
        
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(((Object[])privateField.get(hashMap))[6]));
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        
        assertEquals(privateMethod.invoke(entryClassObject), privateMethod.invoke(((Object[])privateField.get(hashMap))[6]));
    }
    
    /**
     * Test the all arg constructor for Entry class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAllArgConstructorEntryClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateField = entryClass.getDeclaredField("key");
        privateField.setAccessible(true);
        
        assertEquals("H", privateField.get(entryClassObject));
        
        privateField = entryClass.getDeclaredField("value");
        privateField.setAccessible(true);
        
        assertEquals("3", privateField.get(entryClassObject));
    }
    
    /**
     * Test the getKey method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetKey() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateMethod = entryClass.getDeclaredMethod("getKey");
        privateMethod.setAccessible(true);
        
        assertEquals("H", (String) privateMethod.invoke(entryClassObject));
    }
    
    /**
     * Test the getValue method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetValue() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        
        assertEquals("3", (String) privateMethod.invoke(entryClassObject));
    }
    
    /**
     * Test the setValue method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSetValue() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        
        assertEquals("3", (String) privateMethod.invoke(entryClassObject));
        
        privateMethod = entryClass.getDeclaredMethod("setValue", Object.class);
        privateMethod.setAccessible(true);
        
        privateMethod.invoke(entryClassObject, "4");
        
        privateMethod = entryClass.getDeclaredMethod("getValue");
        privateMethod.setAccessible(true);
        
        assertEquals("4", (String) privateMethod.invoke(entryClassObject));
    }
    
    /**
     * Test the toString method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testToString() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        entryClassObject = entryAllArgConstructor.newInstance(hashMap, "H", "3");
        
        privateMethod = entryClass.getDeclaredMethod("toString");
        privateMethod.setAccessible(true);
        
        assertEquals("H 3", (String) privateMethod.invoke(entryClassObject));
    }
    
    /**
     * Test the all arg constructor for KeyIterator class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAllArgConstructorKeyIteratorClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        hashMap = new HashMap();
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        
        keyIteratorClassObject = keyIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateField = keyIteratorClass.getDeclaredField("values");
        privateField.setAccessible(true);
        
        assertEquals(table, privateField.get(keyIteratorClassObject));
        
        privateField = keyIteratorClass.getDeclaredField("next");
        privateField.setAccessible(true);
        
        assertEquals(0, privateField.get(keyIteratorClassObject));
    }
    
    /**
     * Test the hasNext method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextKeyIterator() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        Field privateField;
        hashMap = new HashMap();
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        keyIteratorClassObject = keyIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateMethod = keyIteratorClass.getDeclaredMethod("hasNext");
        assertFalse((boolean)(privateMethod.invoke(keyIteratorClassObject)));
        
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        table = privateField.get(hashMap);
        
        keyIteratorClassObject = keyIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        assertTrue((boolean)(privateMethod.invoke(keyIteratorClassObject)));
    }
    
    /**
     * Test the next method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testNextKeyIterator() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        Field privateField;
        hashMap = new HashMap();
        
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        
        keyIteratorClassObject = keyIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateMethod = keyIteratorClass.getDeclaredMethod("hasNext");
        privateMethod.invoke(keyIteratorClassObject);
        
        privateMethod = keyIteratorClass.getDeclaredMethod("next");
        assertEquals("D", (String)(privateMethod.invoke(keyIteratorClassObject)));
        
        privateMethod = keyIteratorClass.getDeclaredMethod("hasNext");
        privateMethod.invoke(keyIteratorClassObject);
        
        privateMethod = keyIteratorClass.getDeclaredMethod("next");
        assertEquals("H", (String)(privateMethod.invoke(keyIteratorClassObject)));
    }
    
    /**
     * Test the all arg constructor for ValueIterator class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testAllArgConstructorValueIteratorClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        hashMap = new HashMap();
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        
        valueIteratorClassObject = valueIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateField = valueIteratorClass.getDeclaredField("values");
        privateField.setAccessible(true);
        
        assertEquals(table, privateField.get(valueIteratorClassObject));
        
        privateField = valueIteratorClass.getDeclaredField("next");
        privateField.setAccessible(true);
        
        assertEquals(0, privateField.get(valueIteratorClassObject));
    }
    
    /**
     * Test the hasNext method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testHasNextValueIterator() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        Field privateField;
        hashMap = new HashMap();
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        valueIteratorClassObject = valueIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateMethod = valueIteratorClass.getDeclaredMethod("hasNext");
        assertFalse((boolean)(privateMethod.invoke(valueIteratorClassObject)));
        
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        table = privateField.get(hashMap);
        
        valueIteratorClassObject = valueIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        assertTrue((boolean)(privateMethod.invoke(valueIteratorClassObject)));
    }
    
    /**
     * Test the next method to ensure it returns the proper value.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testNextValueIterator() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        
        Field privateField;
        hashMap = new HashMap();
        
        hashMap.put("H", "3");
        hashMap.put("D", "4");
        
        privateField = HashMap.class.getDeclaredField("table");
        privateField.setAccessible(true);
        Object table = privateField.get(hashMap);
        
        valueIteratorClassObject = valueIteratorAllArgConstructor.newInstance(new Object[] { hashMap, table });
        
        privateMethod = valueIteratorClass.getDeclaredMethod("hasNext");
        privateMethod.invoke(valueIteratorClassObject);
        
        privateMethod = valueIteratorClass.getDeclaredMethod("next");
        assertEquals("4", (String)(privateMethod.invoke(valueIteratorClassObject)));
        
        privateMethod = valueIteratorClass.getDeclaredMethod("hasNext");
        privateMethod.invoke(valueIteratorClassObject);
        
        privateMethod = valueIteratorClass.getDeclaredMethod("next");
        assertEquals("3", (String)(privateMethod.invoke(valueIteratorClassObject)));
    }
}
