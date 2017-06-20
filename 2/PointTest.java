import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//test private field
import java.lang.reflect.*;

/**
 * PointTest - Test Point class include constructors and methods
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
public class PointTest
{
    /**
     * Default constructor for test class PointTest
     */
    public PointTest()
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

        Point point1 = new Point(1, 2);  
        
        //Extract  and test "row"
        privateField = Point.class.getDeclaredField("row");
        privateField.setAccessible(true);
        int row1 = (int) privateField.get(point1);
        assertEquals(1, row1);
        
        //Extract and test "column"
        privateField = Point.class.getDeclaredField("column");
        privateField.setAccessible(true);
        int column1 = (int) privateField.get(point1);
        assertEquals(2, column1);
    }
    
    /**
     * Test the getRow method to ensure it returns the proper value.
     */
    @Test
    public void testGetRow()
    {
        Point point1 = new Point(12345, 2); 
        assertEquals(12345, point1.getRow());
    }
    
    /**
     * Test the getColumn method to ensure it returns the proper value.
     */
    @Test
    public void testGetColumn()
    {
        Point point1 = new Point(12345, 23456); 
        assertEquals(23456, point1.getColumn());
    }
    
     /**
     * Test the toString method to ensure it returns the proper value.
     */
    @Test
    public void testToString()
    {
        Point point1 = new Point(12345, 23456); 
        assertEquals("[12345,23456]", point1.toString());
    }
}
