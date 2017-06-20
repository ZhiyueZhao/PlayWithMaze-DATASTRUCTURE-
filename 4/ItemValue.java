
/**
 * ItemValue  - Contains the value of the found item (how much the item is worth in gold pieces).
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
public class ItemValue
{
    // instance variables - replace the example below with your own
    private int goldPieces;

    /**
     * Constructor for objects of class ItemValue
     */
    public ItemValue(int goldPieces)
    {
        // initialise instance variables
        this.goldPieces = goldPieces;
    }

    /**
     * Get the goldPieces of the itemValue
     * 
     * @return     the goldPieces of the itemValue
     */
    public int getGoldPieces()
    {
        // put your code here
        return this.goldPieces;
    }
    
    /**
     * Override the toString method
     * 
     * @return a string to describe this item
     */
    public String toString()
    {
        // put your code here
        return String.valueOf(this.goldPieces);
    }
}
