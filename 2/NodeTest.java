
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//test private field
import java.lang.reflect.*;
/**
 * NodeTest - Test Node class include constructors and methods
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
public class NodeTest
{
    /**
     * Default constructor for test class NodeTest
     */
    public NodeTest()
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
     * Test the constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;

        Node<Point> nodePre = new Node<Point>(new Point(111,222), null);
        Point printCur = new Point(333,444);
        Node<Point> nodeCur = new Node<Point>(printCur, nodePre);
        
        //Extract and test element field
        privateField = Node.class.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals(printCur, privateField.get(nodeCur));
        
        //Extract and test previous field
        privateField = Node.class.getDeclaredField("previous");
        privateField.setAccessible(true);
        assertEquals(nodePre, privateField.get(nodeCur));
    }
    
    /**
     * Test the getElement method to ensure it returns the proper value.
     */
    @Test
    public void testGetElement()
    {
        Point point = new Point(123,456);
        
        Node<Point> node = new Node<Point>(point, null);
        
        assertEquals(point, node.getElement());
    }
    
    /**
     * Test the getPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testGetPrevious()
    {
        Point point1 = new Point(12345, 56789);
        Point point2 = new Point(12, 34);
        
        Node<Point> node1 = new Node<Point>(point1, null);
        
        Node<Point> node2 = new Node<Point>(point2, node1);
        
        assertEquals(node1, node2.getPrevious());
    }
}
