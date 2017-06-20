
import java.util.NoSuchElementException;
/**
 * Queue  - A new class that implements the Queue methods as discussed in the lectures.
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
public class Queue<E> 
{
    // Private Properties
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructor for objects of class Queue
     */
    public Queue()
    {
        // initialise instance variables
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Add a new node to the Queue at the tail
     * @param  Data: Class to add
     */
    public void enqueue(E element)
    {
        Node<E> toAdd = new Node<E>(element, null);
        
        if(isEmpty())
        {
            this.head = toAdd;
        }
        else
        {
            this.tail.setNext(toAdd);
        }
        
        this.tail = toAdd;
        this.size ++;
    }
    
    /**
     * Return the first data(head) item on the Queue.
     * @return Data stored in the head node
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E front()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("No such element");
        }
        
        return this.head.getElement();
    }
    
    /**
     * Remove the node at the head of the Queue and return the data it contains
     * @return Data stored in the head node
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E dequeue()
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("No such element");
        }
        
        Node<E> current = this.head;
        
        if(this.size == 1)
        {
            this.head = null;
            this.tail = null;
        }
        else
        {
            this.head = this.head.getNext();
        }
        
        this.size --;
        
        return current.getElement();
    }
    
    /**
     * Return the number of nodes (data items) in the queue.
     * @return number of nodes 
     */
    public int getSize()
    {
        // put your code here
        return this.size;
    }
    
    /**
     * Returns a boolean to indicate if the Queue is empty or not. True it is, false there are entries.
     * @return True if no nodes contained, otherwise false
     */
    public boolean isEmpty()
    {
        // put your code here
        return (getSize() == 0);
    }
    
    /*Queue interface methods*/
    /**
     * Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions
     * @param element The object to be stored
     * @return true upon success
     * @throws IllegalStateException if no space is currently available
     */
    public boolean add(E e) throws InterruptedException
    {
        // put your code here
        enqueue(e);
        return true;
    }
    
    /**
     * Inserts the specified element into this queue if it is possible to do so immediately without violating capacity restrictions. When using a capacity-restricted queue, this method is generally preferable to add(E), which can fail to insert an element only by throwing an exception.
     * @return true upon success
     */
    public boolean offer(E e)
    {
        // put your code here
        try
        {
            return add(e);
        }
        catch(InterruptedException ie)
        {
            return false;
        }
    }
    
    /**
     * Retrieves and removes the head of this queue.
     * @return the head of this queue
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E remove()
    {
        // put your code here
        return dequeue();
    }
    
    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty
     * @return the head of this queue
     */
    public E poll()
    {
        // put your code here
        if(isEmpty())
        {
            return null;
        }
        return dequeue();
    }
    
    /**
     * Retrieves, but does not remove, the head of this queue. 
     * @return the head of this queue
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E element()
    {
        return front();
    }
    
    /**
     * Retrieves, but does not remove, the head of this queue. 
     * @return the head of this queue or null if empty
     */
    public E peek()
    {
        // put your code here
        if(isEmpty())
        {
            return null;
        }
        
        return this.head.getElement();
    }
    
    /**
     * Node  - A inner class that implements the Node class and methods as discussed in the Queue.
     * 
     * <pre>
     * 
     * Assignment: #1
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
    private class Node<E>
	{
	    // Private Properties
	    private E element;
	    private Node<E> next;

	    /**
	     * Constructor for objects of class Node
	     * @param element The object to be stored
	     * @param next Reference to the next Node
	     */
	    public Node(E date, Node<E> node)
	    {
	        // initialise instance variables
	        this.element = date;
	        this.next = node;
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
	     * return the Next node
	     * @return Reference to the next Node
	     */
	    public Node<E> getNext()
	    {
	        // put your code here
	        return next;
	    }
	    
	    /**
	     * set the Next node
	     * @param Reference to the next Node
	     */
	    public void setNext(Node<E> node)
	    {
	        // put your code here
	        this.next = node;
	    }
	}
}
