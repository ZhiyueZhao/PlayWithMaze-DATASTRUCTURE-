
import static org.junit.Assert.*;
import java.lang.reflect.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * EmployeeTest  - Test Employee class include constructors and methods
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
public class EmployeeTest
{
    /**
     * Default constructor for test class EmployeeTest
     */
    public EmployeeTest()
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
     * Test the ID constructor, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testIDArgConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        Employee employee1 = new Employee(1);  
        
        //Extract  and test "employeeId"
        privateField = Employee.class.getDeclaredField("employeeId");
        privateField.setAccessible(true);
        int id1 = (int) privateField.get(employee1);
        assertEquals(1, id1);
        
        //Extract and test "firstName"
        privateField = Employee.class.getDeclaredField("firstName");
        privateField.setAccessible(true);
        String firstName = (String) privateField.get(employee1);
        assertNull(firstName);
        
        //Extract and test "lastName"
        privateField = Employee.class.getDeclaredField("lastName");
        privateField.setAccessible(true);
        String lastName = (String) privateField.get(employee1);
        assertNull(lastName);
    }
    
    /**
     * Test the constructor with all arguements, first to make sure it is there and
     * that is sets properties properly.
     */
    @Test
    public void testIDAndNameConstructor() throws NoSuchFieldException, ClassCastException, IllegalArgumentException, IllegalAccessException
    {
        //Needed by multiple calls, so declare once.
        Field privateField;      

        Employee employee1 = new Employee(31415926, "Zhiyue", "Zhao"); 
        
        //Extract  and test "employeeId"
        privateField = Employee.class.getDeclaredField("employeeId");
        privateField.setAccessible(true);
        int id1 = (int) privateField.get(employee1);
        assertEquals(31415926, id1);
        
        //Extract and test "firstName"
        privateField = Employee.class.getDeclaredField("firstName");
        privateField.setAccessible(true);
        String firstName = (String) privateField.get(employee1);
        assertEquals("Zhiyue", firstName);
        
        //Extract and test "lastName"
        privateField = Employee.class.getDeclaredField("lastName");
        privateField.setAccessible(true);
        String lastName = (String) privateField.get(employee1);
        assertEquals("Zhao", lastName);
    }
    
    /**
     * Test the getEmployeeId method to ensure it returns the proper value.
     */
    @Test
    public void testGetEmployeeId()
    {
        Employee employee1 = new Employee(12345);
        assertEquals(12345, employee1.getEmployeeId());
        
        Employee employee2 = new Employee(23456, "Zhiyue", "Zhao");
        assertEquals(23456, employee2.getEmployeeId());
    }

    /**
     * Test the getFirstName method to ensure it returns the proper value.
     */
    @Test
    public void testGetFirstName()
    {
        Employee employee1 = new Employee(1345, "Zhiyue", "Zhao");
        assertEquals("Zhiyue", employee1.getFirstName());
        
        Employee employee2 = new Employee(2345);
        assertNull(employee2.getFirstName());
    }
    
    /**
     * Test the getLastName method to ensure it returns the proper value.
     */
    @Test
    public void testGetLastName()
    {
        Employee employee1 = new Employee(1, "Zhiyue", "Zhao");
        assertEquals("Zhao", employee1.getLastName());
        
        Employee employee2 = new Employee(13);
        assertNull(employee2.getLastName());
    }
    
    /**
     * Test the compareTo method to ensure it returns the proper value.
     */
    @Test
    public void testCompareTo()
    {
        Employee employee1 = new Employee(1234, "Zhiyue", "Zhao");
        
        assertTrue(employee1.compareTo(employee1) == 0);
        
        Employee employee2 = new Employee(1200);
        Employee employee3 = new Employee(1199);
        
        assertTrue(employee2.compareTo(employee3) > 0);
        
        assertTrue(employee3.compareTo(employee2) < 0);
    }
    
    /**
     * Test the toString method to ensure it returns the proper value.
     */
    @Test
    public void testToString()
    {
        Employee employee1 = new Employee(1200, "Zhiyue", "Zhao");
        
        assertEquals("1200 Zhiyue Zhao", employee1.toString());
        
        Employee employee2 = new Employee(1200, "Zhiyue", null);
        
        assertEquals("1200 Zhiyue ", employee2.toString());
        
        Employee employee3 = new Employee(1211, null, "Zhao");
        
        assertEquals("1211  Zhao", employee3.toString());
        
        Employee employee4 = new Employee(1211);
        
        assertEquals("1211  ", employee4.toString());
    }
}