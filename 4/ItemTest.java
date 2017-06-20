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
 * ItemTest - Test Item class include constructors and methods
 * 
 * <pre>
 * 
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: March 27, 2017
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
public class ItemTest
{
    private final String TEST_STRING = "George";
    private final int POLYNOMIAL_VALUE = 31;
    private int iTestStringHash = 0;
    /**
     * Default constructor for test class ItemTest
     */
    public ItemTest()
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
        for(int i=0; i<TEST_STRING.length(); i++)
        {
            char character = TEST_STRING.charAt(i);
            int ascii = (int) character;
            //polynomial
            iTestStringHash += Math.pow(POLYNOMIAL_VALUE, i) * ascii;
        }
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
     * Test the all arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;
        
        Item item = new Item(TEST_STRING);
        
        //test the private field head node
        privateField = Item.class.getDeclaredField("name");
        privateField.setAccessible(true);
        String sName = (String)privateField.get(item);
        
        assertEquals(TEST_STRING, sName);
    }
    
    /**
     * Test the hashCode method to ensure it returns the proper value.
     */
    @Test
    public void testHashCodes()
    {
        Item item = new Item(TEST_STRING);
        
        int iHashCode = item.hashCode();
        
        assertEquals(iTestStringHash, iHashCode);
    }
    
    /**
     * Test the equals method to ensure it returns the proper value.
     */
    @Test
    public void testEquals()
    {
        Item item1 = new Item(TEST_STRING);
        Item item2 = new Item(TEST_STRING);
        Item item3 = new Item("TEST_STRING");
        
        assertEquals(true, item1.equals(item2));
        assertEquals(true, item2.equals(item1));
        assertEquals(false, item1.equals(item3));
    }
    
    /**
     * Test the getName method to ensure it returns the proper value.
     */
    @Test
    public void testGetName()
    {
        Item item = new Item(TEST_STRING);
        
        String sName = item.getName();
        
        assertEquals(TEST_STRING, sName);
    }
    
    /**
     * Test the toString method to ensure it returns the proper value.
     */
    @Test
    public void testToString()
    {
        Item item = new Item(TEST_STRING);
        
        String sName = item.toString();
        
        assertEquals(TEST_STRING, sName);
    }
}
