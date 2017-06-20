//Import the exception since it can be thrown by this E
import java.util.NoSuchElementException;
/**
 * Stack  - A new class that implements the Stack methods as discussed in the lectures.
 * 
 * <pre>
 * 
 * Assignment: #1
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
public class Stack<E>
{
    // Private Properties
    private Node<E> head;
    private int size;

    /**
     * Constructor for objects of class Stack
     */
    public Stack()
    {
        // initialise instance variables
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Add a new node to the Stack at the head.
     * @param data The object to be added
     */
    public void push(E data)
    {
        Node<E> toAdd = new Node<E>(data, this.head);
        this.head = toAdd;
        this.size ++;
    }
    
    /**
     * Return the first data item on the Stack.
     * @return Data stored in the node
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E top()
    {
        if(getSize() == 0)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            return this.head.getElement();
        }
    }
    
    /**
     * Remove the node at the head of the Stack and return the data it contains
     * @return Data stored in the node
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E pop()
    {
        if(getSize() == 0)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            Node<E> current = this.head;
            this.head = this.head.getPrevious();
            this.size--;
            return current.getElement();
        }
    }
    
    /**
     * Return the count of the number of data elements (nodes) in the list
     * @return An int value denoting the number of nodes contained
     */
    public int getSize()
    {
        // put your code here
        return this.size;
    }

    /**
     * Indicates whether the LinkedList is empty or not
     * @return True if no nodes contained, otherwise false
     */
    public boolean isEmpty()
    {
        // put your code here
        return (getSize() == 0);
    }
}
