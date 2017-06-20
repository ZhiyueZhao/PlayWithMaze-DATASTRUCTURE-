
//NoSuchMethodException, ClassCastException, IllegalAccessException, InvocationTargetException
/**
 * Employee  - Employee class include constructors and methods
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
public class Employee implements Comparable<Employee>
{
    /*Private Properties*/
    private int employeeId;
    private String firstName;
    private String lastName;

    /**
     * Constructor for objects of class Employee
     * @param employeeId Number used to identify Employee
     */
    public Employee(int employeeId)
    {
        // initialise instance variables
        this(employeeId, null, null);
    }
    
    /**
     * Constructor for objects of class Employee
     * @param employeeId Number used to identify Employee
     * @param firstName Employee's first name
     * @param lastName Employee's last name
     */
    public Employee(int employeeId, String firstName,String lastName)
    {
        // initialise instance variables
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * return the employeeId
     * @return Number representing employee ID
     */
    public int getEmployeeId()
    {
        // put your code here
        return employeeId;
    }
    
    /**
     * return the first name
     * @return String value containing employee's first name
     */
    public String getFirstName()
    {
        // put your code here
        return firstName;
    }
    
    /**
     * return the last name
     * @return String value containing employee's last name
     */
    public String getLastName()
    {
        // put your code here
        return lastName;
    }
    
    @Override
    /**
     * compare the employeeId
     * @param o Employee to compare against
     * @return Negative integer, zero, or a positive integer as this Employee 
     *         is less than, equal to, or greater than the specified Employee.
     * return negative value when current lower than the other
     * return possitive value when current greater than the other
     * return 0 when equals
     */
    public int compareTo(Employee other)
    {
        // put your code here
        return this.getEmployeeId() - other.getEmployeeId();
    }
    
    @Override
    /**
     * return the employee info: id firstName lastName
     * @return String value representing the Employee
     */
    public String toString()
    {
        // put your code here
        return String.valueOf(this.getEmployeeId()) + " " + (this.getFirstName() != null ? this.getFirstName() : "") + " " + (this.getLastName()!= null ? this.getLastName() : "");
    }
}
