
/**
 * Point  - Describes a point in the maze (numeric row and column coordinates).
 * 
 * <pre>
 * 
 * Assignment: #3
 * Course: ADEV-3001
 * Date Created: March 11, 2017
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
    private int parentRow;
    private int parentColumn;
    

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
     * Constructor for objects of class Point
     * @param row used to identify Current Row
     * @param column used to identify Current Column
     * @param column used to identify Parent Row
     * @param column used to identify Parent Column
     */
    public Point(int row, int column, int parentRow, int parentColumn)
    {
        // initialise instance variables
        this(row, column);
        this.parentRow = parentRow;
        this.parentColumn = parentColumn;
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
    
    /**
     * return the Parent Row
     * @return Number representing Parent Row
     */
    public int getParentRow()
    {
        // put your code here
        return this.parentRow;
    }
    
    /**
     * set the Parent Row
     * @param parentRow Reference to the Parent Row
     */
    public void setParentRow(int parentRow)
    {
        // put your code here
        this.parentRow = parentRow;
    }
    
    /**
     * return the Parent Column
     * @return Number representing Parent Column
     */
    public int getParentColumn()
    {
        // put your code here
        return this.parentColumn;
    }
    
    /**
     * set the Parent Column
     * @param parentColumn Reference to the Parent Column
     */
    public void setParentColumn(int parentColumn)
    {
        // put your code here
        this.parentColumn = parentColumn;
    }

    @Override
    /**
     * return the Point info: row and column
     * @return String value representing the Point
     */
    public String toString()
    {
        // put your code here[5,7]
        return "[" + String.valueOf(this.getRow()) + ", " + String.valueOf(this.getColumn())+ "]";
    }
}
