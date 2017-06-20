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
 * ItemValueTest - Test ItemValue class include constructors and methods
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
public class ItemValueTest
{
    /**
     * Default constructor for test class ItemValueTest
     */
    public ItemValueTest()
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
     * Test the all arg constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testAllArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;
        
        ItemValue itemValue = new ItemValue(0);
        
        //test the private field head node
        privateField = ItemValue.class.getDeclaredField("goldPieces");
        privateField.setAccessible(true);
        int iGoldPieces = (int)privateField.get(itemValue);
        
        assertEquals(0, iGoldPieces);
        
    }
    
    /**
     * Test the getGoldPieces method to ensure it returns the proper value.
     */
    @Test
    public void testGetGoldPieces()
    {
        ItemValue itemValue = new ItemValue(999999);
        
        int iGoldPieces = itemValue.getGoldPieces();
        
        assertEquals(999999, iGoldPieces);
    }
    
    /**
     * Test the toString method to ensure it returns the proper value.
     */
    @Test
    public void testToString()
    {
        ItemValue itemValue = new ItemValue(999999);
        
        String expectedString = "999999";
        
        String outPutString = itemValue.toString();
        
        assertEquals(expectedString, outPutString);
    }
}
