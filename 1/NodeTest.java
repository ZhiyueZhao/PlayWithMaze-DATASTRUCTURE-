import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * NodeTest - Test Node class include constructors and methods
 * 
 * <pre>
 * 
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: Created: January 31, 2017
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
     * Test the no arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testNoArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      
        //Create an empty node object
        Node<Employee> node = new Node<Employee>();    
        
        //Extract and test element field
        privateField = Node.class.getDeclaredField("element");
        privateField.setAccessible(true);
        Employee employee1 = (Employee) privateField.get(node);
        assertNull(employee1);
        
        //Extract and test previous field
        privateField = Node.class.getDeclaredField("previous");
        privateField.setAccessible(true);
        Employee employee2 = (Employee) privateField.get(node);
        assertNull(employee2);
        
        //Extract and test next field
        privateField = Node.class.getDeclaredField("next");
        privateField.setAccessible(true);
        Employee employee3 = (Employee) privateField.get(node);
        assertNull(employee3);
        
    }
    
    /**
     * Test the Node class with arguements, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testWithArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;
        
        Employee employeeCurrent = new Employee(111);
        Node<Employee> nodePre = new Node<Employee>(new Employee(222), null, null);
        Node<Employee> nodeNext = new Node<Employee>(new Employee(333), null, null);
        
        //node used for testing
        Node<Employee> nodeCurrent = new Node<Employee>(employeeCurrent, nodePre, nodeNext);    
        
        //Extract and test element field
        privateField = Node.class.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals(employeeCurrent, privateField.get(nodeCurrent));
        
        //Extract and test previous field
        privateField = Node.class.getDeclaredField("previous");
        privateField.setAccessible(true);
        assertEquals(nodePre, privateField.get(nodeCurrent));
        
        //Extract and test next field
        privateField = Node.class.getDeclaredField("next");
        privateField.setAccessible(true);
        assertEquals(nodeNext, privateField.get(nodeCurrent));
        
    }
    
    /**
     * Test the getElement method to ensure it returns the proper value.
     */
    @Test
    public void testGetElement()
    {
        Employee emp = new Employee(123);
        
        Node<Employee> node = new Node<Employee>(emp, null, null);
        
        assertEquals(emp, node.getElement());
    }
    
    /**
     * Test the setElement method to ensure it returns the proper value.
     */
    @Test
    public void testSetElement()
    {
        Node<Employee> node = new Node<Employee>();
        
        Employee emp = new Employee(123);
        
        node.setElement(emp);
        
        assertEquals(emp, node.getElement());
    }
    
    /**
     * Test the getPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testGetPrevious()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(12, "John", "Doe");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>(emp2, node1, null);
        
        assertEquals(node1, node2.getPrevious());
    }
    
    /**
     * Test the setPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testSetPrevious()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>();
        
        node2.setPrevious(node1);
        
        assertEquals(node1, node2.getPrevious());
    }
    
    /**
     * Test the setPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testSetPrevious2()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(11111, "Jones", "Zhao");
        Employee emp3 = new Employee(22222, "Zhiyue", "Kyle");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>(emp2, null, null);
        
        Node<Employee> node3 = new Node<Employee>(emp3, node1, null);
        
        node3.setPrevious(node2);
        
        assertEquals(node2, node3.getPrevious());
    }
    
    /**
     * Test the getNext method to ensure it returns the proper value.
     */
    @Test
    public void testGetNext()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(12, "Jones", "Kyle");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>(emp2, null, node1);
        
        assertEquals(node1, node2.getNext());
    }
    
    /**
     * Test the setNext method to ensure it returns the proper value.
     */
    @Test
    public void testSetNext()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>();
        
        node2.setNext(node1);
        
        assertEquals(node1, node2.getNext());
    }
    
    /**
     * Test the setNext method to ensure it returns the proper value.
     */
    @Test
    public void testSetNext2()
    {
        Employee emp1 = new Employee(12345, "Zhiyue", "Zhao");
        Employee emp2 = new Employee(11111, "Jones", "Zhao");
        Employee emp3 = new Employee(22222, "Zhiyue", "Kyle");
        
        Node<Employee> node1 = new Node<Employee>(emp1, null, null);
        
        Node<Employee> node2 = new Node<Employee>(emp2, null, null);
        
        Node<Employee> node3 = new Node<Employee>(emp3, null, node1);
        
        node3.setNext(node2);
        
        assertEquals(node2, node3.getNext());
    }
}