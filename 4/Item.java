
/**
 * Item  - Contains the key for an item found in the maze (the item name)
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
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private final int POLYNOMIAL_VALUE = 31;

    /**
     * Constructor for objects of class Item
     * @param  name   a String parameter for initialize
     */
    public Item(String name)
    {
        // initialise instance variables
        this.name = name;
    }
    
    /**
     * Use a polynomial equation and return a hash code based on the item name
     * 
     * @return a hash code based on the item name
     */
    public int hashCode()
    {
        int result = 0;
        for(int i=0; i<this.name.length(); i++)
        {
            char character = name.charAt(i);
            int ascii = (int) character;
            //polynomial
            result += Math.pow(POLYNOMIAL_VALUE, i) * ascii;
        }
        return result;
    }
    
    /**
     * comparison of the item name
     * 
     * @return     same named items return true
     */
    public boolean equals(Object o)
    {
        //determine if object is an Item and if it has the same name
        return (o != null && 
                this.getClass() == o.getClass() && 
                ((Item)o).getName().equals(this.name));
    }
    
    /**
     * Get the name of the item
     * 
     * @return     the name of the item
     */
    public String getName()
    {
        // put your code here
        return this.name;
    }
    
    /**
     * Override the toString method
     * 
     * @return a string to describe this item
     */
    public String toString()
    {
        // put your code here
        return getName();
    }
}
