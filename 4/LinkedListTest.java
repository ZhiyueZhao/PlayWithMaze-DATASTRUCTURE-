import java.lang.reflect.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Import the rules ExpectedException object
import org.junit.rules.ExpectedException;
//Import the NoSuchElementException
import java.util.NoSuchElementException;

/**
 * LinkedListTest - Test LinkedList class include constructors and methods
 * 
 * <pre>
 * 
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: January 31, 2017
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
public class LinkedListTest
{
    private Class<?> nodeClass;
    private Object nodeClassObject;
    private LinkedList<String> nodeLinkedList;
    private Constructor<?> nodeAllArgConstructor;
    private Constructor<?> nodeNoArgConstructor;
    
    /**
     * Default constructor for test class LinkedListTest
     */
    public LinkedListTest()
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
        nodeLinkedList = new LinkedList<String>();
        nodeClass = Class.forName("LinkedList$Node");
        Class[] params1= {LinkedList.class, Object.class, nodeClass, nodeClass};
        nodeAllArgConstructor = nodeClass.getDeclaredConstructor(params1);
        nodeAllArgConstructor.setAccessible(true);
        
        Class[] params2= {LinkedList.class};
        nodeNoArgConstructor = nodeClass.getDeclaredConstructor(params2);
        nodeNoArgConstructor.setAccessible(true);
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
    public void testNoArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        nodeLinkedList = new LinkedList<String>();    
        
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodeLinkedList));
        
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodeLinkedList));
        
        //test the private field size if its 0
        assertEquals(0, nodeLinkedList.getSize());
        
    }

    /**
     * Test the add method to ensure it returns the proper value.
     */
    @Test
    public void testAdd()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        
        assertEquals(true, nodeLinkedList.add(testString1));
        assertEquals(testString1, nodeLinkedList.get());
    }

    /**
     * Test the clear method to ensure it returns the proper value.
     */
    @Test
    public void testClear() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        nodeLinkedList = new LinkedList<String>();
        nodeLinkedList.add("Zhiyue Zhao");
        
        nodeLinkedList.clear();
        
        assertEquals(0, nodeLinkedList.getSize());
        
        //Needed by multiple calls, so declare once.
        Field privateField; 
        
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodeLinkedList));
        
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodeLinkedList));
        //asertNull(nodeLinkedList.
    }

    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    public void testIsEmpty()
    {
        nodeLinkedList = new LinkedList<String>();
        
        assertEquals(true, nodeLinkedList.isEmpty());
        
        nodeLinkedList.add("Zhiyue Zhao");
        
        assertEquals(false, nodeLinkedList.isEmpty());
    }

    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testRemove()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.add(testString1);
        
        assertEquals(1, nodeLinkedList.getSize());
        
        String testString2 = nodeLinkedList.remove();
        
        assertEquals(testString1, testString2);
        
        assertEquals(0, nodeLinkedList.getSize());
    }
    
    /**
     * Test the remove method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemove()
    {        
        nodeLinkedList = new LinkedList<String>();
        nodeLinkedList.remove();
    }

    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        nodeLinkedList = new LinkedList<String>();
        
        assertEquals(0, nodeLinkedList.getSize());
        
        nodeLinkedList.add("Zhiyue Zhao");
        nodeLinkedList.add("Zhiyue");
        nodeLinkedList.add("Zhao");
        
        assertEquals(3, nodeLinkedList.getSize());
    }

    /**
     * Test the get method to ensure it returns the proper value.
     */
    @Test
    public void testGet()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.add(testString1);
        
        assertEquals(testString1, nodeLinkedList.get());
    }
    
    /**
     * Test the get method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGet()
    {        
        nodeLinkedList = new LinkedList<String>();
        nodeLinkedList.get();
    }

    /**
     * Test the set method to ensure it returns the proper value.
     */
    @Test
    public void testSet()
    {
        nodeLinkedList = new LinkedList<String>();
        
        nodeLinkedList.add("Zhiyue Zhao");
        nodeLinkedList.add("Zhiyue");
        
        String testString3 = "Zhao";
        nodeLinkedList.add(testString3);
        String testString5 = "George";
        
        assertEquals(testString3,(String)nodeLinkedList.set(testString5));
    }
    
    /**
     * Test the set method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSet()
    {        
        nodeLinkedList = new LinkedList<String>();

        nodeLinkedList.set("George");
    }

    /**
     * Test the addAfter(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddAfterDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        
        nodeLinkedList.add("Zhiyue Zhao");
        nodeLinkedList.add("Zhiyue");
        String testString2 = "Zhao";
        String testString4 = "George";
        //Position != Size
        assertEquals(true, nodeLinkedList.addAfter(testString2, 1));
        //Position = Size
        assertEquals(true, nodeLinkedList.addAfter(testString4, 3));
        
        assertEquals(testString2,(String)nodeLinkedList.get(2));
        assertEquals(testString4,(String)nodeLinkedList.get(4));
    }
    
    /**
     * Test the addAfter(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddAfterDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        
        nodeLinkedList.add("Zhiyue Zhao");
        nodeLinkedList.add("Zhiyue");
        String testString4 = "George";
        nodeLinkedList.addAfter(testString4, 3);
    }
    
    /**
     * Test the addBefore(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddBeforeDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        
        nodeLinkedList.add("George Zhao");
        nodeLinkedList.add("Zhiyue");
        String testString1 = "Zhiyue Zhao";
        String testString3 = "Zhao";
        //Position = 1
        assertEquals(true, nodeLinkedList.addBefore(testString1, 1));
        //Position != 1
        assertEquals(true, nodeLinkedList.addBefore(testString3, 3));
        
        assertEquals(testString1,(String)nodeLinkedList.get(1));
        assertEquals(testString3,(String)nodeLinkedList.get(3));
    }
    
    /**
     * Test the addBefore(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddBeforeDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        
        nodeLinkedList.add("Zhiyue Zhao");
        nodeLinkedList.add("Zhiyue");
        String testString0 = "George Zhao";
        nodeLinkedList.addBefore(testString0, 0);
    }
    
    /**
     * Test the addFirst(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddFirstData() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;  
        
        nodeLinkedList = new LinkedList<String>();
        assertEquals(0, nodeLinkedList.getSize());
        
        String testString0 = "George Zhao";
        String testString1 = "Zhiyue Zhao";
        
        assertEquals(true, nodeLinkedList.addFirst(testString1));
        assertEquals(testString1,(String)nodeLinkedList.get(1));
        assertEquals(1, nodeLinkedList.getSize());
        
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        //test the private field tail node
        Field privateField2 = LinkedList.class.getDeclaredField("tail");
        privateField2.setAccessible(true);
        assertEquals(privateField.get(nodeLinkedList), privateField2.get(nodeLinkedList));
        
        assertEquals(true, nodeLinkedList.addFirst(testString0));
        assertEquals(testString0,(String)nodeLinkedList.get(1));
        assertEquals(2, nodeLinkedList.getSize());
        
        assertEquals(testString0, nodeLinkedList.getFirst());
        assertEquals(testString1, nodeLinkedList.getLast());
    }
    
    /**
     * Test the addLast(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddLastData() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;  
        
        nodeLinkedList = new LinkedList<String>();
        assertEquals(0, nodeLinkedList.getSize());
        
        String testString2 = "Zhiyue";
        String testString1 = "Zhiyue Zhao";
        //size = 0
        assertEquals(true, nodeLinkedList.addLast(testString1));
        assertEquals(testString1,(String)nodeLinkedList.get(1));
        assertEquals(1, nodeLinkedList.getSize());
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        //test the private field tail node
        Field privateField2 = LinkedList.class.getDeclaredField("tail");
        privateField2.setAccessible(true);
        
        assertEquals(privateField.get(nodeLinkedList), privateField2.get(nodeLinkedList));
        //size > 0
        assertEquals(true, nodeLinkedList.addLast(testString2));
        assertEquals(testString2,(String)nodeLinkedList.get(2));
        assertEquals(2, nodeLinkedList.getSize());
        
        assertEquals(testString1,(String)nodeLinkedList.getFirst());
        assertEquals(testString2,(String)nodeLinkedList.getLast());
    }
    
    /**
     * Test the get(position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString2 = "Zhiyue";
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        assertEquals(testString1,(String)nodeLinkedList.get(1));
        assertEquals(testString2,(String)nodeLinkedList.get(2));
    }
    
    /**
     * Test the get(position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.get(2);
    }
    
    /**
     * Test the getFirst() method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetFirst()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString2 = "Zhiyue";
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        assertEquals(testString1, nodeLinkedList.getFirst());
    }
    
    /**
     * Test the getLast() method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetLast()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString2 = "Zhiyue";
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        assertEquals(testString2, nodeLinkedList.getLast());
    }
    
    /**
     * Test the getLast() method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetLast()
    {
        nodeLinkedList = new LinkedList<String>();
        nodeLinkedList.getLast();
    }
    
    /**
     * Test the insert(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testInsertData()
    {
        //Size = 0
        nodeLinkedList = new LinkedList<String>();
        String testString3 = "Zhao";
        assertEquals(0, nodeLinkedList.getSize());
        assertEquals(true, nodeLinkedList.insert(testString3));
        assertEquals(testString3, nodeLinkedList.get(1));
        assertEquals(1, nodeLinkedList.getSize());
        //Size > 0
        //Current >= Data
        //Previous is null
        String testString1 = "Zhiyue Zhao";
        assertEquals(true, nodeLinkedList.insert(testString1));
        String resultString = nodeLinkedList.get(1);
        assertEquals(testString1, nodeLinkedList.get(2));
        assertEquals(2, nodeLinkedList.getSize());
        
        //Previous is not null
        //Current is not null
        String testString2 = "Zhiyue";
        assertEquals(true, nodeLinkedList.insert(testString2));
        assertEquals(testString2, nodeLinkedList.get(2));
        assertEquals(3, nodeLinkedList.getSize());
        
        //Current is null
        String testString4 = "George";
        assertEquals(true, nodeLinkedList.insert(testString4));
        assertEquals(testString4, nodeLinkedList.get(1));
        assertEquals(4, nodeLinkedList.getSize());
    }
    
    /**
     * Test the remove(position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemovePosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        String testString4 = "George";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        nodeLinkedList.addLast(testString3);
        nodeLinkedList.addLast(testString4);
        assertEquals(4, nodeLinkedList.getSize());
        
        //Position = 1
        assertEquals(testString1, (String)nodeLinkedList.get(1));
        nodeLinkedList.remove(1);
        assertEquals(3, nodeLinkedList.getSize());
        assertEquals(testString2, (String)nodeLinkedList.get(1));
        
        //position != size
        nodeLinkedList.remove(3);
        assertEquals(2, nodeLinkedList.getSize());
        assertEquals(testString3, (String)nodeLinkedList.get(nodeLinkedList.getSize()));
        
        //position == size
        nodeLinkedList.remove(2);
        assertEquals(1, nodeLinkedList.getSize());
        assertEquals(testString2, (String)nodeLinkedList.get(1));
    }
    
    /**
     * Test the remove(position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemovePosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.addLast(testString1);
        
        nodeLinkedList.get(9);
    }
    
    /**
     * Test the removeFirst() method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveFirst()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.addLast(testString1);
        
        assertEquals(1, nodeLinkedList.getSize());
        assertEquals(testString1, (String)nodeLinkedList.removeFirst());
        assertEquals(0, nodeLinkedList.getSize());
    }
    
    /**
     * Test the removeLast() method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveLast()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        
        assertEquals(2, nodeLinkedList.getSize());
        assertEquals(testString2, (String)nodeLinkedList.removeLast());
        assertEquals(1, nodeLinkedList.getSize());
        assertEquals(testString1, (String)nodeLinkedList.removeLast());
        assertEquals(0, nodeLinkedList.getSize());
    }
    
    /**
     * Test the removeLast() method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemoveLast()
    {
        nodeLinkedList = new LinkedList<String>();
        nodeLinkedList.removeLast();
    }
    
    /**
     * Test the set(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        String testString4 = "George";
        String testString5 = "George Zhao";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        nodeLinkedList.addLast(testString3);
        
        //Position = Size
        assertEquals(3, nodeLinkedList.getSize());
        assertEquals(testString3, nodeLinkedList.set(testString4,3));
        //Position != Size
        assertEquals(testString2, nodeLinkedList.set(testString5,2));
        assertEquals(3, nodeLinkedList.getSize());
    }
    
    /**
     * Test the set(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetDataPosition()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        nodeLinkedList.addLast(testString1);
        
        nodeLinkedList.set(testString2, 0);
    }
    
    /**
     * Test the setFirst(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetFirstData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        nodeLinkedList.addLast(testString1);
        assertEquals(testString1, nodeLinkedList.setFirst(testString2));
        assertEquals(1, nodeLinkedList.getSize());
    }
    
    /**
     * Test the setFirst(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetFirstData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.setFirst(testString1);
    }
    
    /**
     * Test the setLast(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetLastData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        nodeLinkedList.addLast(testString1);
        assertEquals(testString1, nodeLinkedList.setLast(testString2));
        assertEquals(testString2, nodeLinkedList.getLast());
        assertEquals(1, nodeLinkedList.getSize());
    }
    
    /**
     * Test the setLast(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetLastData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        nodeLinkedList.setLast(testString1);
    }
    
    /**
     * Test the addAfter(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddAfterData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.addLast(testString1);
        //current is tail
        assertEquals(true, nodeLinkedList.addAfter(testString2, testString1));
        assertEquals(2, nodeLinkedList.getSize());
        assertEquals(testString2, nodeLinkedList.getLast());
        assertEquals(testString1, nodeLinkedList.getFirst());
        //current is not tail
        assertEquals(true, nodeLinkedList.addAfter(testString3, testString1));
        assertEquals(3, nodeLinkedList.getSize());
        assertEquals(testString2, nodeLinkedList.getLast());
        assertEquals(testString1, nodeLinkedList.getFirst());
    }
    
    /**
     * Test the addAfter(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddAfterData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.setLast(testString1);
        
        nodeLinkedList.addAfter(testString3, testString2);
    }
    
    /**
     * Test the addBefore(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddBeforeData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.addLast(testString1);
        //Current = head
        assertEquals(true, nodeLinkedList.addBefore(testString2, testString1));
        assertEquals(2, nodeLinkedList.getSize());
        assertEquals(testString1, nodeLinkedList.getLast());
        assertEquals(testString2, nodeLinkedList.getFirst());
        //current is not head
        assertEquals(true, nodeLinkedList.addBefore(testString3, testString1));
        assertEquals(3, nodeLinkedList.getSize());
        assertEquals(testString1, nodeLinkedList.getLast());
        assertEquals(testString2, nodeLinkedList.getFirst());
    }
    
    /**
     * Test the addBefore(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddBeforeData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.setLast(testString1);
        
        nodeLinkedList.addBefore(testString3, testString2);
    }
    
    /**
     * Test the get(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addBefore(testString2, testString1);
        nodeLinkedList.addBefore(testString3, testString1);
        assertEquals(testString2, nodeLinkedList.get(testString2));
    }
    
    /**
     * Test the get(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.get(testString1);
    }
    
    /**
     * Test the remove(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        String testString4 = "George";
        String testString5 = "George Zhao";
        String testString6 = "George Zhiyue";
        
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        nodeLinkedList.addLast(testString3);
        nodeLinkedList.addLast(testString4);
        nodeLinkedList.addLast(testString5);
        nodeLinkedList.addLast(testString6);
        //Current = head
        assertEquals(testString1, nodeLinkedList.remove(testString1));
        //Current = Tail
        assertEquals(testString6, nodeLinkedList.remove(testString6));
        //Current in between
        assertEquals(testString3, nodeLinkedList.remove(testString3));
    }
    
    /**
     * Test the remove(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemoveData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        
        nodeLinkedList.remove(testString1);
    }
    
    /**
     * Test the set(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        String testString3 = "Zhao";
        nodeLinkedList.addLast(testString1);
        nodeLinkedList.addLast(testString2);
        assertEquals(testString2, nodeLinkedList.set(testString3, testString2));
    }
    
    /**
     * Test the set(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetData()
    {
        nodeLinkedList = new LinkedList<String>();
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        nodeLinkedList.set(testString1, testString2);
    }
    
    /**
     * Test the all arg constructor for node class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructorInnerClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        String testString1 = "Zhiyue Zhao";
        
        nodeClassObject = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        
        assertEquals(testString1, (String) privateField.get(nodeClassObject));
        
        privateField = nodeClass.getDeclaredField("next");
        privateField.setAccessible(true);
        
        assertNull(privateField.get(nodeClassObject));
        
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        
        assertNull(privateField.get(nodeClassObject));
    }
    
    /**
     * Test the no arg constructor for node class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testNoArgConstructorInnerClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        
        nodeClassObject = nodeNoArgConstructor.newInstance(nodeLinkedList);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        
        assertNull(privateField.get(nodeClassObject));
        
        privateField = nodeClass.getDeclaredField("next");
        privateField.setAccessible(true);
        
        assertNull(privateField.get(nodeClassObject));
        
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        
        assertNull(privateField.get(nodeClassObject));
    }
    
    /**
     * Test the getElement method to ensure it returns the proper value.
     */
    @Test
    public void testGetElement() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        
        nodeClassObject = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        
        assertEquals(testString1, (String) privateMethod.invoke(nodeClassObject));
    }
    
    /**
     * Test the setElement method to ensure it returns the proper value.
     */
    @Test
    public void testSetElement() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        
        Object nodeClassObject1 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        
        privateMethod = nodeClass.getDeclaredMethod("setElement", Object.class);
        privateMethod.setAccessible(true);
        privateMethod.invoke(nodeClassObject1, testString2);
        
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        
        assertEquals(testString2, privateMethod.invoke(nodeClassObject1));
    }
    
    /**
     * Test the getNext method to ensure it returns the proper value.
     */
    @Test
    public void testGetNext() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        
        Object nodeClassObject1 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        Object nodeClassObject2 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString2, null, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
    
    /**
     * Test the setNext method to ensure it returns the proper value.
     */
    @Test
    public void testSetNext() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        
        Object nodeClassObject1 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        Object nodeClassObject2 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString2, null, null);
        
        privateMethod = nodeClass.getDeclaredMethod("setNext", nodeClass);
        privateMethod.setAccessible(true);
        privateMethod.invoke(nodeClassObject2, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
    
    /**
     * Test the getPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testGetPrevious() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        
        Object nodeClassObject1 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        Object nodeClassObject2 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString2, nodeClassObject1, null);
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
    
    /**
     * Test the setPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testSetPrevious() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String testString1 = "Zhiyue Zhao";
        String testString2 = "Zhiyue";
        
        Object nodeClassObject1 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString1, null, null);
        Object nodeClassObject2 = nodeAllArgConstructor.newInstance(nodeLinkedList, testString2, null, null);
        
        privateMethod = nodeClass.getDeclaredMethod("setPrevious", nodeClass);
        privateMethod.setAccessible(true);
        privateMethod.invoke(nodeClassObject2, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
}
