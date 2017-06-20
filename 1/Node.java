
/**
 * Node  - Node class include constructors and methods
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
public class Node <E>
{
    // Private Properties
    private E element;
    private Node<E> previous;
    private Node<E> next;

    /**
     * Constructor for objects of class Node
     */
    public Node()
    {
        // initialise instance variables
        this(null, null, null);
    }
    
    /**
     * Constructor for objects of class Node
     * @param element The object to be stored
     * @param previous Reference to the previous Node
     * @param next Reference to the next Node
     */
    public Node(E element, Node<E> previous, Node<E> next)
    {
        // initialise instance variables
        setElement(element);
        setPrevious(previous);
        setNext(next);
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
     * set the element in the node
     * @param element The object to be stored
     */
    public void setElement(E element)
    {
        // put your code here
        this.element = element;
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
    
    /**
     * set the Previous node
     * @param previous The reference to be stored
     */
    public void setPrevious(Node<E> previous)
    {
        // put your code here
        this.previous = previous;
    }
    
    /**
     * return the next node
     * @return Reference to the next Node
     */
    public Node<E> getNext()
    {
        // put your code here
        return next;
    }
    
    /**
     * set the next node
     * @param next The reference to be stored
     */
    public void setNext(Node<E> next)
    {
        // put your code here
        this.next = next;
    }
}
