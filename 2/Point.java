
/**
 * Point  - Describes a point in the maze (numeric row and column coordinates).
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
public class Point
{
    // instance variables - replace the example below with your own
    private int row;
    private int column;

    /**
     * Constructor for objects of class Point
     * @param row used to identify Current Row
     * @param column used to identify Current Column
     */
    public Point(int row, int column)
    {
        // initialise instance variables
        this.row = row;
        this.column = column;
    }
    
    /**
     * return the Current Row
     * @return Number representing Current Row
     */
    public int getRow()
    {
        // put your code here
        return this.row;
    }
    
    /**
     * return the Current Column
     * @return Number representing Current Column
     */
    public int getColumn()
    {
        // put your code here
        return this.column;
    }

    @Override
    /**
     * return the Point info: row and column
     * @return String value representing the Point
     */
    public String toString()
    {
        // put your code here[5,7]
        return "[" + String.valueOf(this.getRow()) + "," + String.valueOf(this.getColumn())+ "]";
    }
}
