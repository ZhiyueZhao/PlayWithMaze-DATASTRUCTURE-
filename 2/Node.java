
/**
 * Node  - Contains information added to the stack (a point in the maze in this assignment).
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
public class Node<E>
{
    // Private Properties
    private E element;
    private Node<E> previous;

    /**
     * Constructor for objects of class Node
     * @param element The object to be stored
     * @param previous Reference to the previous Node
     */
    public Node(E date, Node<E> node)
    {
        // initialise instance variables
        this.element = date;
        this.previous = node;
    }
    
    /**
     * Returns the stored object
     * @return The object in the type it was given
     */
    public E getElement()
    {
        // put your code here
        return element;
    }

    /**
     * return the Previous node
     * @return Reference to the previous Node
     */
    public Node<E> getPrevious()
    {
        // put your code here
        return previous;
    }
}
