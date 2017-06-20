
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
    public void setUp()
    {
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

        LinkedList<Employee> empList = new LinkedList<Employee>();    
        
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(empList);
        assertNull(nodeHead);
        
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        Node nodeTail = (Node) privateField.get(empList);
        assertNull(nodeTail);
        
        //test the private field size if its 0
        assertEquals(0, empList.getSize());
        
    }

    /**
     * Test the add method to ensure it returns the proper value.
     */
    @Test
    public void testAdd()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(111, "Zhiyue", "Zhao");
        
        assertEquals(true, empList.add(emp1));
        assertEquals(emp1, empList.get());
    }

    /**
     * Test the clear method to ensure it returns the proper value.
     */
    @Test
    public void testClear() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        empList.add(new Employee(111, "Zhiyue", "Zhao"));
        
        empList.clear();
        
        assertEquals(0, empList.getSize());
        
        //Needed by multiple calls, so declare once.
        Field privateField; 
        
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(empList);
        assertNull(nodeHead);
        
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        Node nodeTail = (Node) privateField.get(empList);
        assertNull(nodeTail);
        //asertNull(empList.
    }

    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    public void testIsEmpty()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        assertEquals(true, empList.isEmpty());
        
        empList.add(new Employee(1));
        
        assertEquals(false, empList.isEmpty());
    }

    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testRemove()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1);
        empList.add(emp1);
        
        assertEquals(1, empList.getSize());
        
        Employee emp2 = empList.remove();
        
        assertEquals(emp1, emp2);
        
        assertEquals(0, empList.getSize());
    }
    
    /**
     * Test the remove method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemove()
    {        
        LinkedList<Employee> empList = new LinkedList<Employee>();
        empList.remove();
    }

    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        assertEquals(0, empList.getSize());
        
        empList.add(new Employee(1));
        empList.add(new Employee(2));
        empList.add(new Employee(3, "Zhiyue", "Zhao"));
        
        assertEquals(3, empList.getSize());
    }

    /**
     * Test the get method to ensure it returns the proper value.
     */
    @Test
    public void testGet()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        
        empList.add(emp1);
        
        assertEquals(emp1, empList.get());
    }
    
    /**
     * Test the get method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGet()
    {        
        LinkedList<Employee> empList = new LinkedList<Employee>();
        empList.get();
    }

    /**
     * Test the set method to ensure it returns the proper value.
     */
    @Test
    public void testSet()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        empList.add(new Employee(1, "Zhiyue", "Zhao"));
        empList.add(new Employee(4));
        
        Employee emp3 = new Employee(3);
        empList.add(emp3);
        Employee emp5 = new Employee(5);
        
        assertEquals(emp3,(Employee)empList.set(emp5));
    }
    
    /**
     * Test the set method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSet()
    {        
        LinkedList<Employee> empList = new LinkedList<Employee>();

        empList.set(new Employee(1));
    }

    /**
     * Test the addAfter(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddAfterDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        empList.add(new Employee(1, "Zhiyue", "Zhao"));
        empList.add(new Employee(3));
        Employee emp2 = new Employee(2, "George", "Zhao");
        Employee emp4 = new Employee(4, "Zhiyue", "George");
        //Position != Size
        assertEquals(true, empList.addAfter(emp2, 1));
        //Position = Size
        assertEquals(true, empList.addAfter(emp4, 3));
        
        assertEquals(emp2,(Employee)empList.get(2));
        assertEquals(emp4,(Employee)empList.get(4));
    }
    
    /**
     * Test the addAfter(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddAfterDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        empList.add(new Employee(1, "Zhiyue", "Zhao"));
        empList.add(new Employee(2));
        Employee emp4 = new Employee(4, "Zhiyue", "George");
        empList.addAfter(emp4, 3);
    }
    
    /**
     * Test the addBefore(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddBeforeDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        empList.add(new Employee(2, "Zhiyue", "Zhao"));
        empList.add(new Employee(4));
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        //Position = 1
        assertEquals(true, empList.addBefore(emp1, 1));
        //Position != 1
        assertEquals(true, empList.addBefore(emp3, 3));
        
        assertEquals(emp1,(Employee)empList.get(1));
        assertEquals(emp3,(Employee)empList.get(3));
    }
    
    /**
     * Test the addBefore(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddBeforeDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        
        empList.add(new Employee(1, "Zhiyue", "Zhao"));
        empList.add(new Employee(2));
        Employee emp0 = new Employee(0, "George", "Zhao");
        empList.addBefore(emp0, 0);
    }
    
    /**
     * Test the addFirst(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddFirstData() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;  
        
        LinkedList<Employee> empList = new LinkedList<Employee>();
        assertEquals(0, empList.getSize());
        
        Employee emp0 = new Employee(0, "George", "Zhao");
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        
        assertEquals(true, empList.addFirst(emp1));
        assertEquals(emp1,(Employee)empList.get(1));
        assertEquals(1, empList.getSize());
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(empList);
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        Node nodeTail = (Node) privateField.get(empList);
        assertEquals(nodeHead, nodeTail);
        
        assertEquals(true, empList.addFirst(emp0));
        assertEquals(emp0,(Employee)empList.get(1));
        assertEquals(2, empList.getSize());
        
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        nodeHead = (Node) privateField.get(empList);
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        nodeTail = (Node) privateField.get(empList);
        assertEquals(emp0,(Employee)nodeTail.getPrevious().getElement());
        assertEquals(emp0,(Employee)nodeHead.getElement());
        assertEquals(emp1,(Employee)nodeHead.getNext().getElement());
        assertEquals(emp1,(Employee)nodeTail.getElement());
    }
    
    /**
     * Test the addLast(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddLastData() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;  
        
        LinkedList<Employee> empList = new LinkedList<Employee>();
        assertEquals(0, empList.getSize());
        
        Employee emp2 = new Employee(2, "George", "Zhao");
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        //size = 0
        assertEquals(true, empList.addLast(emp1));
        assertEquals(emp1,(Employee)empList.get(1));
        assertEquals(1, empList.getSize());
        //test the private field head node
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(empList);
        //test the private field tail node
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        Node nodeTail = (Node) privateField.get(empList);
        assertEquals(nodeHead, nodeTail);
        //size > 0
        assertEquals(true, empList.addLast(emp2));
        assertEquals(emp2,(Employee)empList.get(2));
        assertEquals(2, empList.getSize());
        
        privateField = LinkedList.class.getDeclaredField("head");
        privateField.setAccessible(true);
        nodeHead = (Node) privateField.get(empList);
        privateField = LinkedList.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        nodeTail = (Node) privateField.get(empList);
        assertEquals(emp1,(Employee)nodeTail.getPrevious().getElement());
        assertEquals(emp1,(Employee)nodeHead.getElement());
        assertEquals(emp2,(Employee)nodeHead.getNext().getElement());
        assertEquals(emp2,(Employee)nodeTail.getElement());
    }
    
    /**
     * Test the get(position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp2 = new Employee(2, "George", "Zhao");
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        assertEquals(emp1,(Employee)empList.get(1));
        assertEquals(emp2,(Employee)empList.get(2));
    }
    
    /**
     * Test the get(position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        empList.get(2);
    }
    
    /**
     * Test the getFirst() method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetFirst()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(2, "George", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        assertEquals(emp1, empList.getFirst());
    }
    
    /**
     * Test the getLast() method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetLast()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(2, "George", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        assertEquals(emp2, empList.getLast());
    }
    
    /**
     * Test the getLast() method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetLast()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        empList.getLast();
    }
    
    /**
     * Test the insert(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testInsertData()
    {
        //Size = 0
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp3 = new Employee(3, "Zhiyue", "Zhao");
        assertEquals(0, empList.getSize());
        assertEquals(true, empList.insert(emp3));
        assertEquals(emp3, empList.get(1));
        assertEquals(1, empList.getSize());
        //Size > 0
        //Current >= Data
        //Previous is null
        Employee emp1 = new Employee(1, "George", "Zhao");
        assertEquals(true, empList.insert(emp1));
        assertEquals(emp1, empList.get(1));
        assertEquals(2, empList.getSize());
        
        //Previous is not null
        //Current is not null
        Employee emp2 = new Employee(2, "Zhiyue", "George");
        assertEquals(true, empList.insert(emp2));
        assertEquals(emp2, empList.get(2));
        assertEquals(3, empList.getSize());
        
        //Current is null
        Employee emp4 = new Employee(4, "George", "George");
        assertEquals(true, empList.insert(emp4));
        assertEquals(emp4, empList.get(4));
        assertEquals(4, empList.getSize());
    }
    
    /**
     * Test the remove(position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemovePosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "George");
        Employee emp3 = new Employee(3, "Zhiyue", "Zhao");
        Employee emp4 = new Employee(4, "George", "George");
        empList.addLast(emp1);
        empList.addLast(emp2);
        empList.addLast(emp3);
        empList.addLast(emp4);
        assertEquals(4, empList.getSize());
        
        //Position = 1
        assertEquals(emp1, (Employee)empList.get(1));
        empList.remove(1);
        assertEquals(3, empList.getSize());
        assertEquals(emp2, (Employee)empList.get(1));
        
        //position != size
        empList.remove(3);
        assertEquals(2, empList.getSize());
        assertEquals(emp3, (Employee)empList.get(empList.getSize()));
        
        //position == size
        empList.remove(2);
        assertEquals(1, empList.getSize());
        assertEquals(emp2, (Employee)empList.get(1));
    }
    
    /**
     * Test the remove(position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemovePosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        empList.addLast(emp1);
        
        empList.get(9);
    }
    
    /**
     * Test the removeFirst() method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveFirst()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        empList.addLast(emp1);
        
        assertEquals(1, empList.getSize());
        assertEquals(emp1, (Employee)empList.removeFirst());
        assertEquals(0, empList.getSize());
    }
    
    /**
     * Test the removeLast() method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveLast()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        
        assertEquals(2, empList.getSize());
        assertEquals(emp2, (Employee)empList.removeLast());
        assertEquals(1, empList.getSize());
        assertEquals(emp1, (Employee)empList.removeLast());
        assertEquals(0, empList.getSize());
    }
    
    /**
     * Test the removeLast() method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemoveLast()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        empList.removeLast();
    }
    
    /**
     * Test the set(data, position) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        Employee emp4 = new Employee(4, "George", "George");
        Employee emp5 = new Employee(5, "Zhao", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        empList.addLast(emp3);
        
        //Position = Size
        assertEquals(3, empList.getSize());
        assertEquals(emp3, empList.set(emp4,3));
        //Position != Size
        assertEquals(emp2, empList.set(emp5,2));
    }
    
    /**
     * Test the set(data, position) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetDataPosition()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        
        empList.set(emp2, 0);
    }
    
    /**
     * Test the setFirst(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetFirstData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        assertEquals(emp1, empList.setFirst(emp2));
        assertEquals(1, empList.getSize());
    }
    
    /**
     * Test the setFirst(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetFirstData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        empList.setFirst(emp1);
    }
    
    /**
     * Test the setLast(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetLastData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.addLast(emp1);
        assertEquals(emp1, empList.setLast(emp2));
        assertEquals(1, empList.getSize());
    }
    
    /**
     * Test the setLast(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetLastData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        empList.setLast(emp1);
    }
    
    /**
     * Test the addAfter(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddAfterData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        empList.addLast(emp1);
        //current is tail
        assertEquals(true, empList.addAfter(emp2, emp1));
        assertEquals(2, empList.getSize());
        assertEquals(emp2, empList.getLast());
        assertEquals(emp1, empList.getFirst());
        //current is not tail
        assertEquals(true, empList.addAfter(emp3, emp1));
        assertEquals(3, empList.getSize());
        assertEquals(emp2, empList.getLast());
        assertEquals(emp1, empList.getFirst());
    }
    
    /**
     * Test the addAfter(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddAfterData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.setLast(emp1);
        
        empList.addAfter(emp3, emp2);
    }
    
    /**
     * Test the addBefore(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testAddBeforeData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        empList.addLast(emp1);
        //Current = head
        assertEquals(true, empList.addBefore(emp2, emp1));
        assertEquals(2, empList.getSize());
        assertEquals(emp1, empList.getLast());
        assertEquals(emp2, empList.getFirst());
        //current is not head
        assertEquals(true, empList.addBefore(emp3, emp1));
        assertEquals(3, empList.getSize());
        assertEquals(emp1, empList.getLast());
        assertEquals(emp2, empList.getFirst());
    }
    
    /**
     * Test the addBefore(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForAddBeforeData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.setLast(emp1);
        
        empList.addBefore(emp3, emp2);
    }
    
    /**
     * Test the get(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testGetData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        empList.addLast(emp1);
        empList.addBefore(emp2, emp1);
        empList.addBefore(emp3, emp1);
        assertEquals(emp2, empList.get(emp2));
    }
    
    /**
     * Test the get(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForGetData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        
        empList.get(emp1);
    }
    
    /**
     * Test the remove(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testRemoveData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        Employee emp4 = new Employee(4, "Zhiyue", "Zhiyue");
        Employee emp5 = new Employee(5, "Zhao", "Zhiyue");
        Employee emp6 = new Employee(6, "Zhao", "Zhao");
        empList.addLast(emp1);
        empList.addLast(emp2);
        empList.addLast(emp3);
        empList.addLast(emp4);
        empList.addLast(emp5);
        empList.addLast(emp6);
        //Current = head
        assertEquals(emp1, empList.remove(emp1));
        //Current = Tail
        assertEquals(emp6, empList.remove(emp6));
        //Current in between
        assertEquals(emp3, empList.remove(emp3));
    }
    
    /**
     * Test the remove(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemoveData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        
        empList.remove(emp1);
    }
    
    /**
     * Test the set(data) method to ensure it returns the proper Exception.
     */
    @Test
    public void testSetData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        Employee emp3 = new Employee(3, "Zhiyue", "George");
        empList.addLast(emp1);
        empList.addLast(emp2);
        assertEquals(emp2, empList.set(emp3, emp2));
    }
    
    /**
     * Test the set(data) method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForSetData()
    {
        LinkedList<Employee> empList = new LinkedList<Employee>();
        Employee emp1 = new Employee(1, "George", "Zhao");
        Employee emp2 = new Employee(2, "Zhiyue", "Zhao");
        empList.set(emp1, emp2);
    }
}
