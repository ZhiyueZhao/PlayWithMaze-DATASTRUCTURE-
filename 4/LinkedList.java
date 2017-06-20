//Import the exception since it can be thrown by this E
import java.util.NoSuchElementException;
/**
 * LinkedList  - LinkedList class include constructors and methods
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
public class LinkedList<E extends Comparable<E>>
{
    // Private Properties
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructor for objects of E LinkedList
     */
    public LinkedList()
    {
        // When initially created, the linked list object is empty.
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Add new data to the head of the list
     * @param element The object to be added
     * @return True if successfully added to the LinkedList, otherwise false
     */
    public boolean add(E element)
    {
        linkHead(element);
        return true;
    }
    
    /**
     * Add new data after the node number specified
     * @param element The object to be added
     * @param position Numeric position to insert after
     * @return true
     * @throws NoSuchElementException Occurs when Position Invalid
     */
    public boolean addAfter(E element, int position)
    {
        validatePosition(position);
        if(position == this.size)
        {
            linkTail(element);
        }
        else
        {
            Node<E> current = find(position);
            Node<E> next = current.getNext();
            link(element, current, next);
        }
        return true;
    }
    
    /**
     * Add new data after the node containing the ‘olddata’ specified
     * @param element: Class to add
     * @param Data: Data value to insert after
     * @return true
     * @throws NoSuchElementException Occurs when current is null
     */
    public boolean addAfter(E element, E data)
    {
        Node<E> current = find(data);
        //current is null
        if(current == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            //current is not tail
            if(current.getNext() != null)
            {
                Node<E> next = current.getNext();
                link(element, current, next);
            }
            else
            {
                linkTail(element);
            }
            // put your code here
            return true;
        }
    }
    
    /**
     * Add new data before the node number specified
     * @param element: Class to add
     * @param position: Numeric position to insert before
     * @return true
     * @throws NoSuchElementException Occurs when Position Invalid
     */
    public boolean addBefore(E element, int position)
    {
        validatePosition(position);
        if(position == 1)
        {
            linkHead(element);
        }
        else
        {
            Node<E> current = find(position);
            Node<E> previous = current.getPrevious();
            link(element, previous, current);
        }
        return true;
    }
    
    /**
     * Add new data after the node containing the ‘olddata’ specified
     * @param element: Class to add
     * @param Data value to insert before
     * @return true
     * @throws NoSuchElementException Occurs when Current is null
     */
    public boolean addBefore(E element, E data)
    {
        Node<E> current = find(data);
        
        if(current == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            if(current.getPrevious() == null)
            {
                linkHead(element);
            }
            else
            {
                Node<E> previous = current.getPrevious();
                link(element, previous, current);
            }
            return true;
        }
    }
    
    /**
     * DAdd a new node to the linked list at the head
     * @param data: Class to add
     * @return true
     */
    public boolean addFirst(E data)
    {
        linkHead(data);
        return true;
    }
    
    /**
     * Add a new node to the linked list at the end (tail)
     * @param data: Class to add
     * @return true
     */
    public boolean addLast(E data)
    {
        if(this.size == 0)
        {
            linkHead(data);
        }
        else
        {
            linkTail(data);
        }
        return true;
    }
    
    /**
     * Empty all elements from the list
     */
    public void clear()
    {
        // put your code here
        this.head = null;
        this.tail = null;
        this.size = 0;
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
    
    /**
     * Return the data in the head node
     * @return E data
     * @throws NoSuchElementException Occurs when head is null
     */
    public E get()
    {
         if(this.size > 0)
         {
             return this.head.getElement();
         }
         else
         {
             throw new NoSuchElementException("No such element");
         }
    }
    
    /**
     * Return the data in the node at the position number specified
     * @param position: Numeric position to retrieve
     * @return Data stored in the node
     * @throws NoSuchElementException Occurs when Position Invalid
     */
    public E get(int position)
    {
        validatePosition(position);
        Node<E> current = find(position);
        return current.getElement();
    }
    
    /**
     * Return the data in the node containing the data specified
     * @param data: Data to retrieve
     * @return Current - Node at numeric position specified
     * @hrows NoSuchElementException Occurs when not note found
     */
    public E get(E data)
    {
        Node<E> current = find(data);
        if(current == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            return current.getElement();
        }
    }
    
    /**
     * Return the data in the head node
     * @return Data stored in the first node
     */
    public E getFirst()
    {
        return get();
    }
    
    /**
     * Return the data in the tail node
     * @param parametername parameterdescription
     * @param parametername parameterdescription
     * @return Data stored in the last node
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E getLast()
    {
        if(this.size > 0)
        {
            return this.tail.getElement();
        }
        else
        {
            throw new NoSuchElementException("No such element");
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
     * Add the data specified into the proper location in the list based on natural ordering
     * @param data: Class to add
     * @return true
     */
    public boolean insert(E data)
    {
        if(this.size == 0)
        {
            linkHead(data);
        }
        else
        {
            Node<E> previous = null;
            Node<E> current = this.head;
            
            while(current != null && current.getElement().compareTo(data)<0)
            {
                previous = current;
                current = current.getNext();
            }
            
            if(previous == null)
            {
                linkHead(data);
            }
            else
            {
                if(current == null)
                {
                    linkTail(data);
                }
                else
                {
                    link(data, previous, current);
                }
            }
        }
        return true;
    }
    
    /**
     * Remove the head node
     * @return The removed object
     * @throws NoSuchElementException Occurs when size = 0
     */
    public E remove()
    {
        if(this.size > 0)
        {
            return unlinkHead();
        }
        else
        {
            throw new NoSuchElementException("No such element");
        }
    }
    
    /**
     * Remove the node at the numeric position specified
     * @param position: Numeric position to retrieve
     * @return Data - data stored in the node
     * @throws NoSuchElementException Occurs when Position Invalid
     */
    public E remove(int position)
    {
        validatePosition(position);
        E data = null;
        if(position == 1)
        {
            data = unlinkHead();
        }
        else if(position == this.size)
        {
            data = unlinkTail();
        }
        else
        {
            Node<E> current = find(position);
            data = unlink(current);
        }
        return data;
    }
    
    /**
     * Remove the node containing the data specified
     * @param Data to retrieve
     * @return Data stored in the node that was removed
     * @throws NoSuchElementException Occurs when data not found
     */
    public E remove(E data)
    {
        Node<E> current = find(data);
        if(current == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            //Current = head
            if(current.getPrevious() == null)
            {
                return unlinkHead();
            }
            //urrent = Tail
            else if(current.getNext() == null)
            {
                return unlinkTail();
            }
            //not head or tail
            else
            {
                return unlink(current);
            }
        }
    }
    
    /**
     * Remove the head node
     * @return Data - data stored in the node
     */
    public E removeFirst()
    {
        return remove();
    }
    
    /**
     * Remove the tail node
     * @return return value description
     * @throws NoSuchElementException Occurs when tail is null
     */
    public E removeLast()
    {
        if(this.tail == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else if(this.size == 1)
        {
            return unlinkHead();
        }
        else
        {
            return unlinkTail();
        }
    }
    
    /**
     * Change the data in the head node. This
     * method does not change node position, it
     * only changes the data the node points to. If
     * there is no head an exception is thrown.
     * @param data The new object to be stored
     * @return The old object stored in the head
     * @throws NoSuchElementException Occurs when head is null
     */
    public E set(E data)
    {
        if(this.size > 0)
        {
            return setData(data, this.head);
        }
        else
        {
            throw new NoSuchElementException("No such element");
        }
    }
    
    /**
     * Change the data on the node at the numeric position specified with the data passed and return the old data
     * @param data: New data for the node
     * @param position: Node position number to replace data on
     * @return Data originally stored in the node
     * @throws NoSuchElementException Occurs when Position Invalid
     */
    public E set(E data, int position)
    {
        validatePosition(position);
        if(position == this.size)
        {
            return setData(data, this.tail);
        }
        else
        {
            Node<E> current = find(position);
            return setData(data, current);
        }
    }
    
    /**
     * Change the data on the node containing the olddata with the data passed and return the old data
     * @param DataToReplace: New data for the node
     * @param Data on a node to be replaced
     * @return Data originally stored in the node
     * @throws NoSuchElementException Occurs when data not found
     */
    public E set(E dataToReplace, E data)
    {
        Node<E> current = find(data);
        
        if(current == null)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            return setData(dataToReplace, current);
        }
    }
    
    /**
     * Change the data on the head node with the data specified and return the old data
     * @param data: New data for the node
     * @return Data originally stored in the node
     * @throws NoSuchElementException Occurs when Size = 0
     */
    public E setFirst(E data)
    {
        return set(data);
    }
    
    /**
     * Change the data on the tail node with the data specified and return the old data
     * @param data: New data for the node
     * @return Data originally stored in the node
     * @throws NoSuchElementException Occurs when Size = 0
     */
    public E setLast(E data)
    {
        if(this.size == 0)
        {
            throw new NoSuchElementException("No such element");
        }
        else
        {
            return setData(data, this.tail);
        }
    }
    
    /**
     * Find and return the node at the position number specified
     * @param position: Numeric position of node to find
     * @return Current Node
     */
    private Node<E> find(int position)
    {
        Node<E> current = this.head;
        int i = 1;
        while(i<position)
        {
            current = current.getNext();
            i += 1;
        }
        return current;
    }
    
    /**
     * Find and return the node containing the data specified
     * @param Data: Data to find
     * @return Current Node
     */
    private Node<E> find(E data)
    {
        Node<E> current = this.head;
        //when currentData not = Data
        while(current != null && current.getElement().compareTo(data) != 0)
        {
            current = current.getNext();
        }
        return current;
    }
    
    /**
     * Add a new node to the linked list at the head.
     * If there are no nodes then this is also the
     * tail, so set it, too.
     * @param element The object to be added
     */
    private void linkHead(E element)
    {
        Node<E> toAdd = new Node<E>(element, null, null);
        if(this.head != null)
        {
            toAdd.setNext(this.head);
            this.head.setPrevious(toAdd);
        }
        this.head = toAdd;
        
        if(this.size == 0)
        {
            this.tail = this.head;
        }
        
        this.size += 1;
    }
    
    /**
     * Add a new node to the linked list at the tail
     * @param element The object to be added
     */
    private void linkTail(E element)
    {
        Node<E> toAdd = new Node<E>(element, this.tail, null);
        this.tail.setNext(toAdd);
        this.tail = toAdd;
        this.size += 1;
    }
    
    /**
     * Add a new node to the linked list between the nodes specified
     * @param element: Class to add
     * @param previous: Node to add new node after
     * @param current: Node to add new node before
     */
    private void link(E element, Node<E> previous, Node<E> current)
    {
        Node<E> toAdd = new Node<E>(element, previous, current);
        previous.setNext(toAdd);
        current.setPrevious(toAdd);
        this.size++;
    }
    
    /**
     * Description of what method does
     * @param parametername parameterdescription
     * @param parametername parameterdescription
     * @return return value description
     * @throws exceptionname description
     */
    private E setData(E data, Node<E> current)
    {
        E replacedData = current.getElement();
        current.setElement(data);
        // put your code here
        return replacedData;
    }
    
    /**
     * Remove node current from the linked list
     * @param current: The link to be removed
     * @return Data stored in the node
     */
    private E unlink(Node<E> current)
    {
        Node<E> previous = current.getPrevious();
        Node<E> next = current.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        this.size -= 1;
        return current.getElement();
    }
    
    /**
     * Remove the node at the head of the
     * linked list and return the data it contains
     * @return The object contained by the unlinked Node
     */
    private E unlinkHead()
    {
        Node<E> current = this.head;
        this.head = this.head.getNext();
        size -= 1;
        if (this.head != null)
        {
            this.head.setPrevious(null);
        }
        else
        {
            this.tail = null;
        }
        return current.getElement();
    }
    
    /**
     * Remove the tail node from the linked list
     * @return Data stored in the node being removed
     */
    private E unlinkTail()
    {
        Node<E> current = this.tail;
        this.tail = this.tail.getPrevious();
        this.tail.setNext(null);
        this.size -= 1;
        return current.getElement();
    }
    
    /**
     * Validate that the numeric position value specified
     * is within the bounds of the linked list (greater or
     * equal to 1 and less than or equal to Size)
     * @param position integer value
     * @throws NoSuchElementException Occurs when Position < 1 or Position > 1
     */
    private void validatePosition(int position)
    {
        if(position < 1 || position > this.size)
        {
            throw new NoSuchElementException("No such element");
        }
    }
    
    /**
     * Node  - Node class include constructors and methods
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
    private class Node <E>
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
}
