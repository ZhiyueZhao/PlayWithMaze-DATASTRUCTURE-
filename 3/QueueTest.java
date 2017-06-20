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
 * QueueTest - Test Queue class include constructors and methods
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
public class QueueTest
{
    private Class<?> nodeClass;
    private Object nodeClassObject;
    private Queue<Point> poiQueue;
    private Constructor<?> nodeConstructor;
    /**
     * Default constructor for test class QueueTest
     */
    public QueueTest()
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
        poiQueue = new Queue<Point>();
        nodeClass = Class.forName("Queue$Node");
        Class[] params= {Queue.class, Object.class, nodeClass};
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
    public void testNoArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        poiQueue = new Queue<Point>();
        
        //test the private field head node
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        assertNull(privateField.get(poiQueue));
        
        //test the private field tail node
        privateField = Queue.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        assertNull(privateField.get(poiQueue));
        
        //test the private field siz node
        privateField = Queue.class.getDeclaredField("size");
        privateField.setAccessible(true);
        int size = (int) privateField.get(poiQueue);
        assertEquals(0, size);
    }
    
    /**
     * Test the enqueue method to ensure it returns the proper value.
     */
    @Test
    public void testEnqueue() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
    {
        poiQueue = new Queue<Point>();
        assertEquals(true, poiQueue.isEmpty());
        Point point1 = new Point(111, 222);
        //Node<Point> node1 = new Node<Point>(point1, null);
        
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        Method privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        //Node nodeHead = (Node) privateField.get(poiQueue);
        assertEquals(point1, (Point)privateMethod.invoke(privateField.get(poiQueue)));
        
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertNull(privateMethod.invoke(privateField.get(poiQueue)));
        assertEquals(1, poiQueue.getSize());
        
        poiQueue.enqueue(point2);
        //test the private field head node
        privateField = Queue.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        //nodeHead = (Node) privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        Object node2 = privateField.get(poiQueue);
        assertEquals(point2, (Point)privateMethod.invoke(node2));
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertNull(privateMethod.invoke(privateField.get(poiQueue)));
        
        assertEquals(2, poiQueue.getSize());
        
        //test getNext of head
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Object nodeHead = privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertEquals(node2, (Object)privateMethod.invoke(nodeHead));
    }
    
    /**
     * Test the front method to ensure it returns the proper value.
     */
    @Test
    public void testFront()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        assertEquals(point1, poiQueue.front());
    }
    
    /**
     * Test the front method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForFront()
    {        
        poiQueue = new Queue<Point>();
        poiQueue.front();
    }
    
    /**
     * Test the dequeue method to ensure it returns the proper value.
     */
    @Test
    public void testDequeue()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        
        assertEquals(2, poiQueue.getSize());
        assertEquals(point1, poiQueue.dequeue());
        assertEquals(1, poiQueue.getSize());
        assertEquals(point2, poiQueue.dequeue());
        assertEquals(0, poiQueue.getSize());
    }
    
    /**
     * Test the dequeue method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForDequeue()
    {        
        poiQueue = new Queue<Point>();
        poiQueue.dequeue();
    }
    
    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        assertEquals(0, poiQueue.getSize());
        poiQueue.enqueue(point1);
        assertEquals(1, poiQueue.getSize());
        poiQueue.enqueue(point2);
        assertEquals(2, poiQueue.getSize());
    }
    
    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    public void testIsEmpty()
    {
        poiQueue = new Queue<Point>();
        assertEquals(0, poiQueue.getSize());
        assertEquals(true, poiQueue.isEmpty());
        Point point1 = new Point(111, 222);
        
        poiQueue.enqueue(point1);
        assertEquals(1, poiQueue.getSize());
        assertEquals(false, poiQueue.isEmpty());
    }
    
    /**
     * Test the add method to ensure it returns the proper value.
     */
    @Test
    public void testAdd() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException
    {
        poiQueue = new Queue<Point>();
        assertEquals(true, poiQueue.isEmpty());
        Point point1 = new Point(111, 222);
        //Node<Point> node1 = new Node<Point>(point1, null);
        
        Point point2 = new Point(222, 222);
        //Node<Point> node2 = new Node<Point>(point2, node1);
        Class<?> node = Queue.class.getDeclaredClasses()[0];
        
        assertEquals(true, poiQueue.add(point1));
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        Method privateMethod = node.getDeclaredMethod("getElement", new Class[0]);
        privateMethod.setAccessible(true);
        //Node nodeHead = (Node) privateField.get(poiQueue);
        assertEquals(point1, (Point)privateMethod.invoke(privateField.get(poiQueue)));
        
        privateMethod = node.getDeclaredMethod("getNext", new Class[0]);
        privateMethod.setAccessible(true);
        assertEquals(null, (Point)privateMethod.invoke(privateField.get(poiQueue)));
        assertEquals(1, poiQueue.getSize());
        
        assertEquals(true, poiQueue.add(point2));
        //test the private field head node
        privateField = Queue.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        //nodeHead = (Node) privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        Object node2 = privateField.get(poiQueue);
        assertEquals(point2, (Point)privateMethod.invoke(node2));
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertNull(privateMethod.invoke(privateField.get(poiQueue)));
        
        assertEquals(2, poiQueue.getSize());
        
        //test getNext of head
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Object nodeHead = privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertEquals(node2, (Object)privateMethod.invoke(nodeHead));
    }
    
    /**
     * Test the offer method to ensure it returns the proper value.
     */
    @Test
    public void testOffer() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException
    {
        poiQueue = new Queue<Point>();
        assertEquals(true, poiQueue.isEmpty());
        Point point1 = new Point(111, 222);
        //Node<Point> node1 = new Node<Point>(point1, null);
        
        Point point2 = new Point(222, 222);
        //Node<Point> node2 = new Node<Point>(point2, node1);
        Class<?> node = Queue.class.getDeclaredClasses()[0];
        
        
        assertEquals(true, poiQueue.offer(point1));
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        Method privateMethod = node.getDeclaredMethod("getElement", new Class[0]);
        privateMethod.setAccessible(true);
        //Node nodeHead = (Node) privateField.get(poiQueue);
        assertEquals(point1, (Point)privateMethod.invoke(privateField.get(poiQueue)));
        
        privateMethod = node.getDeclaredMethod("getNext", new Class[0]);
        privateMethod.setAccessible(true);
        assertEquals(null, (Point)privateMethod.invoke(privateField.get(poiQueue)));
        assertEquals(1, poiQueue.getSize());
        
        assertEquals(true, poiQueue.offer(point2));
        //test the private field head node
        privateField = Queue.class.getDeclaredField("tail");
        privateField.setAccessible(true);
        //nodeHead = (Node) privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        Object node2 = privateField.get(poiQueue);
        assertEquals(point2, (Point)privateMethod.invoke(node2));
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertNull(privateMethod.invoke(privateField.get(poiQueue)));
        
        assertEquals(2, poiQueue.getSize());
        
        //test getNext of head
        privateField = Queue.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Object nodeHead = privateField.get(poiQueue);
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        assertEquals(node2, (Object)privateMethod.invoke(nodeHead));
    }
    
    /**
     * Test the remove method to ensure it returns the proper value.
     */
    @Test
    public void testRemove()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        
        assertEquals(2, poiQueue.getSize());
        assertEquals(point1, poiQueue.remove());
        assertEquals(1, poiQueue.getSize());
        assertEquals(point2, poiQueue.remove());
        assertEquals(0, poiQueue.getSize());
    }
    
    /**
     * Test the remove method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForRemove()
    {        
        poiQueue = new Queue<Point>();
        poiQueue.remove();
    }
    
    /**
     * Test the poll method to ensure it returns the proper value.
     */
    @Test
    public void testPoll()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        
        assertEquals(2, poiQueue.getSize());
        assertEquals(point1, poiQueue.poll());
        assertEquals(1, poiQueue.getSize());
        assertEquals(point2, poiQueue.poll());
        assertEquals(0, poiQueue.getSize());
        assertNull(poiQueue.poll());
    }
    
    /**
     * Test the element method to ensure it returns the proper value.
     */
    @Test
    public void testElement()
    {
        poiQueue = new Queue<Point>();
        Point point1 = new Point(111, 222);
        
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        assertEquals(point1, poiQueue.element());
    }
    
    /**
     * Test the element method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForElement()
    {        
        poiQueue = new Queue<Point>();
        poiQueue.element();
    }
    
    /**
     * Test the peek method to ensure it returns the proper value.
     */
    @Test
    public void testPeek()
    {
        poiQueue = new Queue<Point>();
        assertNull(poiQueue.peek());
        Point point1 = new Point(111, 222);
        
        Point point2 = new Point(222, 222);
        
        poiQueue.enqueue(point1);
        poiQueue.enqueue(point2);
        assertEquals(point1, poiQueue.peek());
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
        
        nodeClassObject = nodeConstructor.newInstance(poiQueue, point1, null);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        
        assertEquals(point1, (Point) privateField.get(nodeClassObject));
        
        privateField = nodeClass.getDeclaredField("next");
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
        
        nodeClassObject = nodeConstructor.newInstance(poiQueue, point1, null);
        
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        
        assertEquals(point1, (Point) privateMethod.invoke(nodeClassObject));
    }
    
    /**
     * Test the getNext method to ensure it returns the proper value.
     */
    @Test
    public void testGetNext() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        Point point1 = new Point(111, 222);
        Point point2 = new Point(333, 444);
        
        Object nodeClassObject1 = nodeConstructor.newInstance(poiQueue, point1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(poiQueue, point2, nodeClassObject1);
        
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
        Point point1 = new Point(111, 222);
        Point point2 = new Point(333, 444);
        
        Object nodeClassObject1 = nodeConstructor.newInstance(poiQueue, point1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(poiQueue, point2, null);
        
        privateMethod = nodeClass.getDeclaredMethod("setNext", nodeClass);
        privateMethod.setAccessible(true);
        privateMethod.invoke(nodeClassObject2, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getNext");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
}
