
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;
//Import the rules ExpectedException object
import org.junit.rules.ExpectedException;
//Import the NoSuchElementException
import java.util.NoSuchElementException;
/**
 * StackTest - Test Stack class include constructors and methods
 * 
 * <pre>
 * 
 * Assignment: #2
 * Course: ADEV-3001
 * Date Created: February 12, 2017
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
public class StackTest
{
    /**
     * Default constructor for test class StackTest
     */
    public StackTest()
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

        Stack<Point> poiStack = new Stack<Point>();
        
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(poiStack);
        assertNull(nodeHead);
        
        //test the private field tail node
        privateField = Stack.class.getDeclaredField("size");
        privateField.setAccessible(true);
        int size = (int) privateField.get(poiStack);
        assertEquals(0, size);
    }
    
    /**
     * Test the push method to ensure it returns the proper value.
     */
    @Test
    public void testPush() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        Stack<Point> poiStack = new Stack<Point>();
        assertEquals(true, poiStack.isEmpty());
        Point point1 = new Point(111, 222);
        Node<Point> node1 = new Node<Point>(point1, null);
        
        Point point2 = new Point(222, 222);
        Node<Point> node2 = new Node<Point>(point2, node1);
        
        poiStack.push(point1);
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Node nodeHead = (Node) privateField.get(poiStack);
        assertEquals(node1.getElement(), nodeHead.getElement());
        assertEquals(node1.getPrevious(), nodeHead.getPrevious());
        assertEquals(1, poiStack.getSize());
        
        poiStack.push(point2);
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        nodeHead = (Node) privateField.get(poiStack);
        assertEquals(node2.getElement(), nodeHead.getElement());
        assertEquals(node2.getPrevious().getElement(), nodeHead.getPrevious().getElement());
        assertEquals(node2.getPrevious().getPrevious(), nodeHead.getPrevious().getPrevious());
        assertEquals(2, poiStack.getSize());
    }
    
    /**
     * Test the top method to ensure it returns the proper value.
     */
    @Test
    public void testTop()
    {
        Stack<Point> poiStack = new Stack<Point>();
        Point point1 = new Point(111, 222);
        
        Point point2 = new Point(222, 222);
        
        poiStack.push(point1);
        
        assertEquals(point1, poiStack.top());
        
        poiStack.push(point2);
        assertEquals(point2, poiStack.top());
    }
    
    /**
     * Test the top method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForTop()
    {        
        Stack<Point> poiStack = new Stack<Point>();
        poiStack.top();
    }
    
    /**
     * Test the pop method to ensure it returns the proper value.
     */
    @Test
    public void testPop()
    {
        Stack<Point> poiStack = new Stack<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        
        poiStack.push(point1);
        poiStack.push(point2);
        
        assertEquals(2, poiStack.getSize());
        assertEquals(point2, poiStack.pop());
        assertEquals(1, poiStack.getSize());
        assertEquals(point1, poiStack.pop());
        assertEquals(0, poiStack.getSize());
    }
    
    /**
     * Test the pop method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForPop()
    {        
        Stack<Point> poiStack = new Stack<Point>();
        poiStack.pop();
    }
    
    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        Stack<Point> poiStack = new Stack<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        assertEquals(0, poiStack.getSize());
        poiStack.push(point1);
        assertEquals(1, poiStack.getSize());
        poiStack.push(point2);
        assertEquals(2, poiStack.getSize());
    }
    
    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    public void testIsEmpty()
    {
        Stack<Point> poiStack = new Stack<Point>();
        assertEquals(0, poiStack.getSize());
        assertEquals(true, poiStack.isEmpty());
        Point point1 = new Point(111, 222);
        
        poiStack.push(point1);
        assertEquals(1, poiStack.getSize());
        assertEquals(false, poiStack.isEmpty());
    }
}
