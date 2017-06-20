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
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: April 15, 2017
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
    private Stack<String> strStack;
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
        strStack = new Stack<String>();
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
        
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        assertNull(privateField.get(strStack));
        
        //test the private field tail node
        privateField = Stack.class.getDeclaredField("size");
        privateField.setAccessible(true);
        int size = (int) privateField.get(strStack);
        assertEquals(0, size);
    }
    
    /**
     * Test the push method to ensure it returns the proper value.
     */
    @Test
    public void testPush() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        assertEquals(true, strStack.isEmpty());
        String String1 = "111";
        //Node<String> node1 = new Node<String>(String1, null);
        
        String String2 = "222";
        //Node<String> node2 = new Node<String>(String2, node1);
        
        strStack.push(String1);
        
        //Needed by multiple calls, so declare once.
        Field privateField;
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        
        Method privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        assertEquals(String1, privateMethod.invoke(privateField.get(strStack)).toString());
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        assertEquals(null, privateMethod.invoke(privateField.get(strStack)));
        assertEquals(1, strStack.getSize());
        
        strStack.push(String2);
        //test the private field head node
        privateField = Stack.class.getDeclaredField("head");
        privateField.setAccessible(true);
        Object nodeIn = (Object)privateField.get(strStack);
        Object nodeClassObject1 = nodeConstructor.newInstance(strStack, String1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(strStack, String2, nodeClassObject1);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals((String)privateField.get(nodeClassObject2), privateField.get(nodeIn).toString());
        
        //test previous
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        Object nodePrevious = (Object)privateField.get(nodeIn);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        assertEquals(privateField.get(nodeClassObject1).toString(), privateField.get(nodePrevious).toString());
        
        privateField = nodeClass.getDeclaredField("previous");
        privateField.setAccessible(true);
        assertNull(privateField.get(nodePrevious));
        
        assertEquals(2, strStack.getSize());
    }
    
    /**
     * Test the top method to ensure it returns the proper value.
     */
    @Test
    public void testTop()
    {
        String String1 = "George";
        
        String String2 = "Zhao";
        
        strStack.push(String1);
        
        assertEquals(String1, strStack.top());
        
        strStack.push(String2);
        assertEquals(String2, strStack.top());
    }
    
    /**
     * Test the top method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForTop()
    {
        strStack.top();
    }
    
    /**
     * Test the pop method to ensure it returns the proper value.
     */
    @Test
    public void testPop()
    {
        String String1 = "George";
        String String2 = "Zhao";
        
        strStack.push(String1);
        strStack.push(String2);
        
        assertEquals(2, strStack.getSize());
        assertEquals(String2, strStack.pop());
        assertEquals(1, strStack.getSize());
        assertEquals(String1, strStack.pop());
        assertEquals(0, strStack.getSize());
    }
    
    /**
     * Test the pop method to ensure it returns the proper Exception.
     */
    @Test (expected = NoSuchElementException.class)
    public void testExceptionForPop()
    {
        strStack.pop();
    }
    
    /**
     * Test the getSize method to ensure it returns the proper value.
     */
    @Test
    public void testGetSize()
    {
        String String1 = "George";
        String String2 = "Zhao";
        assertEquals(0, strStack.getSize());
        strStack.push(String1);
        assertEquals(1, strStack.getSize());
        strStack.push(String2);
        assertEquals(2, strStack.getSize());
    }
    
    /**
     * Test the isEmpty method to ensure it returns the proper value.
     */
    @Test
    public void testIsEmpty()
    {
        assertEquals(0, strStack.getSize());
        assertEquals(true, strStack.isEmpty());
        String String1 = "George";
        
        strStack.push(String1);
        assertEquals(1, strStack.getSize());
        assertEquals(false, strStack.isEmpty());
    }
    
    /**
     * Test the all arg constructor for node class, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructorInnerClass() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Field privateField;
        String String1 = "George";
        
        nodeClassObject = nodeConstructor.newInstance(strStack, String1, null);
        
        privateField = nodeClass.getDeclaredField("element");
        privateField.setAccessible(true);
        
        assertEquals(String1, privateField.get(nodeClassObject).toString());
        
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
        String String1 = "George";
        
        nodeClassObject = nodeConstructor.newInstance(strStack, String1, null);
        
        privateMethod = nodeClass.getDeclaredMethod("getElement");
        privateMethod.setAccessible(true);
        
        assertEquals(String1, privateMethod.invoke(nodeClassObject).toString());
    }
    
    /**
     * Test the getPrevious method to ensure it returns the proper value.
     */
    @Test
    public void testGetPreviouss() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException
    {
        Method privateMethod;
        String String1 = "George";
        String String2 = "Zhao";
        
        Object nodeClassObject1 = nodeConstructor.newInstance(strStack, String1, null);
        Object nodeClassObject2 = nodeConstructor.newInstance(strStack, String2, nodeClassObject1);
        
        privateMethod = nodeClass.getDeclaredMethod("getPrevious");
        privateMethod.setAccessible(true);
        
        assertEquals(nodeClassObject1, privateMethod.invoke(nodeClassObject2));
    }
}
