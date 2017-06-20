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
 * Assignment: #3
 * Course: ADEV-3001
 * Date Created: March 12, 2017
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
    private Class<?> nodeClass;
    private Object nodeClassObject;
    private Stack<Point> poiStack;
    private Constructor<?> nodeConstructor;
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
    public void setUp() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        poiStack = new Stack<Point>();
        nodeClass = Class.forName("Stack$Node");
        Class[] params= {Stack.class, Object.class, nodeClass};
        nodeConstructor = nodeClass.getDeclaredConstructor(params);
        nodeConstructor.setAccessible(true);
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

        poiStack = new Stack<Point>();
        
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        assertNull(privateField.get(poiStack));
        
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
    public void testPush() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        poiStack = new Stack<Point>();
        assertEquals(true, poiStack.isEmpty());
        Point point1 = new Point(111, 222);
        //Node<Point> node1 = new Node<Point>(point1, null);
        
        Point point2 = new Point(222, 222);
        //Node<Point> node2 = new Node<Point>(point2, node1);
        
        poiStack.push(point1);
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        Method privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        //Node nodeHead = (Node) privateField.get(poiStack);
        assertEquals(point1, (Point)privateMethod.invoke(privateField.get(poiStack)));
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        assertEquals(null, (Point)privateMethod.invoke(privateField.get(poiStack)));
        assertEquals(1, poiStack.getSize());
        
        poiStack.push(point2);
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Object nodeIn = (Object)privateField.get(poiStack);
        Object nodeClassObject1 = nodeConstructor.newInstance(poiStack, point1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(poiStack, point2, nodeClassObject1);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals((Point)privateField.get(nodeClassObject2), (Point)privateField.get(nodeIn));
        
        //test previous
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        Object nodePrevious = (Object)privateField.get(nodeIn);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals((Point)privateField.get(nodeClassObject1), (Point)privateField.get(nodePrevious));
        
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodePrevious));
        
        assertEquals(2, poiStack.getSize());
    }
    
    /**
     * Test the top method to ensure it returns the proper value.
     */
    @Test
    public void testTop()
    {
        poiStack = new Stack<Point>();
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
        poiStack = new Stack<Point>();
        poiStack.top();
    }
    
    /**
     * Test the pop method to ensure it returns the proper value.
     */
    @Test
    public void testPop()
    {
        poiStack = new Stack<Point>();
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
        poiStack = new Stack<Point>();
        poiStack.pop();
    }
    
    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        poiStack = new Stack<Point>();
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
        poiStack = new Stack<Point>();
        assertEquals(0, poiStack.getSize());
        assertEquals(true, poiStack.isEmpty());
        Point point1 = new Point(111, 222);
        
        poiStack.push(point1);
        assertEquals(1, poiStack.getSize());
        assertEquals(false, poiStack.isEmpty());
    }
    
    /**
     * Test the all arg constructor for node class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructorInnerClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        Point point1 = new Point(111, 222);
        
        nodeClassObject = nodeConstructor.newInstance(poiStack, point1, null);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        
        assertEquals(point1, (Point) privateField.get(nodeClassObject));
        
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
        Point point1 = new Point(111, 222);
        
        nodeClassObject = nodeConstructor.newInstance(poiStack, point1, null);
        
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        
        assertEquals(point1, (Point) privateMethod.invoke(nodeClassObject));
    }
    
    /**
     * Test the getPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testGetPreviouss() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        Point point1 = new Point(111, 222);
        Point point2 = new Point(333, 444);
        
        Object nodeClassObject1 = nodeConstructor.newInstance(poiStack, point1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(poiStack, point2, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
}
