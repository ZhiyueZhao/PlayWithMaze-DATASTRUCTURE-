import java.util.Iterator;
import java.lang.reflect.Array;
import java.util.NoSuchElementException;
/**
 * HashMap  - implement the Map interface..
 * 
 * <pre>
 * 
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: April 10, 2017
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
@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> 
{
    // instance variables - replace the example below with your own
    private int size;
    private double loadFactor;
    private int threshold;
    private Entry<K, V> table[];
    private Entry<K, V> available = new Entry(null, null);
    private static final int DEFAULT_CAPACITY = 11;
    private static final double DEFAULT_LOAD_FACTOR = .75;
    
    /**
     * NoArg Constructor for objects of class HashMap
     */
    public HashMap()
    {
        this(0, 0);
    }
    
    /**
     * OneArg Constructor for objects of class HashMap
     * @param initialCapacity The Capacity of the HashMap to create
     */
    public HashMap(int initialCapacity)
    {
        this(initialCapacity, 0);
    }
    
    /**
     * AllArg Constructor for objects of class HashMap
     * @param initialCapacity The Capacity of the HashMap to create
     * @param loadFactor The loadFactor of the HashMap to create
     */
    public HashMap(int initialCapacity, double loadFactor)
    {
        initialCapacity = initialCapacity<=0 ? DEFAULT_CAPACITY : initialCapacity;
        loadFactor = loadFactor<=0 ? DEFAULT_LOAD_FACTOR : loadFactor;
        
        this.loadFactor = loadFactor;
        this.threshold = (int)(initialCapacity * loadFactor);
        this.size = 0;
        table = new Entry[initialCapacity];
    }

    @Override
    /**
     * Get the size of the map
     * 
     * @return     the size of the map
     */
    public int getSize()
    {
        // put your code here
        return size;
    }
    
    @Override
    /**
     * Check if the map is empty
     * 
     * @return     true if the map is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return size == 0; 
    }
    
    @Override
    /**
     * Clear the map
     * Sets all entries in the HashMap to null
     * Set the size to zero
     */
    public void clear()
    {
        //Sets all entries in the HashMap to null
        for (int i = 0; i < this.table.length; i++)
        {
            this.table[i] = null;
        }
        //Set the size to zero
        size = 0; 
    }
    
    @Override
    /**
     * Get the value based on the key from the map
     * @param key The key of the Entry
     * @return     value based on the key, null if nothing found
     */
    public V get(K key)
    {
        //invoke findMatchingBucket method to find the index match the key
        int i = findMatchingBucket(key);
        //if key is in the table
        if( i!= -1)
        {
            return ((Entry<K, V>)this.table[i]).getValue();
        }
        //if key is not in the table
        return null;
    }
    
    @Override
    /**
     * Attempts to either add a new entry or replace the key already on the HashMap
     * @param key The key of the Entry
     * @param value The value of the Entry
     * @return oldValue or null
     * @throws Throws an IllegalArgumentException if either the key or value are null
     */
    public V put(K key, V value)
    {
        if(key==null || value==null)
        {
            throw new IllegalArgumentException("Nether key or value could be null");
        }
        
        //store replaced value
        V oldValue = remove(key);
        
        //add new Entry to Map
        this.table[findBucket(key)] = new Entry<>(key, value);
        
        //increment size
        this.size++;
        
        //determine if threshold has been reached
        if(size > threshold)
        {
            //extend table
            rehash();
        }
        
        //return replaced value
        return oldValue;
    }
    
    @Override
    /**
     * Removes the key and value from the HashMap for the key specified, Replaces the HashMap entry with the available sentinel
     * @param key The key of the Entry
     * @return value The value of the Entry If the key is not found in the HashMap null is returned 
     */
    public V remove(K key)
    {
        //to store retrieved value
        V returnValue = null;
        
        //to store Entry index
        int i;
        
        //determine if corresponding Entry exists
        if((i = findMatchingBucket(key)) != -1)
        {
            //store value to be removed
            returnValue = ((Entry<K, V>)table[i]).getValue();
            
            //replace Entry with sentinnel Entry
            this.table[i] = available;
            
            //decrement size
            this.size--;
        }
        
        //return removed value
        return returnValue;
    }
    
    @Override
    /**
     * Returns an iterator of all keys in the Map
     * 
     * @return     an iterator of all keys in the Map
     */
    public Iterator keys()
    {
        return new KeyIterator<K, V>(this.table);
    }
    
    @Override
    /**
     * Returns an iterator of all values in the Map
     * 
     * @return     an iterator of all values in the Ma
     */
    public Iterator values()
    {
        return new ValueIterator<K, V>(this.table);
    }
    
    /**
     * find the next available spot
     * @param key The key of the Entry
     * @return A null or available HashMap entry (for a new entry) or A used HashMap entry where the keys match
     */
    private int findBucket(K key)
    {
        //Can only occur if the HashMap is at 100% capacity
        if(table.length == size)
        {
            throw new NoSuchElementException("The map is full");
        }
        //to store index and additive value
        int i = Math.abs(key.hashCode() % this.table.length), j = 0;
        
        if(findMatchingBucket(key) != -1)
        {
            i = findMatchingBucket(key);
        }
        else
        {
            //keep recalculating index until a suitable index is found
            while(this.table[i] != null && !this.table[i].equals(available))
            {
                //calculate index
                i = Math.abs((key.hashCode() + ++j) % this.table.length);
            }
        }
        //return valid index
        return i;
    }
    
    /**
     * find the index of the key in the table
     * @param key The key of the Entry
     * @return -1 indicating the key does not exist on the HashMap or An integer >= 0 and < capacity indicating where the key was found
     */
    private int findMatchingBucket(K key)
    {
        int iMatchingBucket = -1;
        
        for(int i=0; i<this.table.length; i++)
        {
            int j = Math.abs((key.hashCode() + i) % this.table.length);
            
            if(this.table[j] != null && !this.table[j].equals(available) && (key.equals(((Entry<K, V>)this.table[j]).getKey())))
            {
                iMatchingBucket = j;
            }
        }
        return iMatchingBucket;
    }
    
    /**
     * Creates a new array at the new prime number size, Moves all existing HashMap entries to the new array
     */
    private void rehash()
    {
        this.size = 0;
        int newcapacity = resize();
        //store old table
        Entry<K, V>[] old = this.table;
        
        //create a new table
        this.table = new Entry[newcapacity];
        
        //recalculate threshold
        this.threshold = (int)(loadFactor * newcapacity);
        
        //iterate over old table
        for(Entry<K, V> e : old)
        {
            //determine if empty Entry
            if(e!=null && !e.equals(available))
            {
                //add Entry to extended table
                put(e.getKey(), e.getValue());
            }
        }
    }
    
    /**
     * Determines the closest prime number to current capacity * 2 + 1
     * 
     * @return the new prime number
     */
    private int resize()
    {
        //get current table's capacity
        int capacity = this.table.length;
        int i,t=0;
        //find the newcapacity which is a closest prime number to current capacity * 2 + 1
        for(int newcapacity = 2*capacity+1; newcapacity > capacity; newcapacity++)
        {
            t=0;
            int basesqr = (int)Math.sqrt(newcapacity);
            for(i=2; i<= basesqr; i++)
            {
                //no reminder means not prime
                if(newcapacity%i == 0)
                {
                    t=1;
                }
            }
            
            if(t == 0)
            {
                return newcapacity;
            }
        }
        
        return 0;
    }
    
    /**
     * Entry  - Objects that hold references to Key and Value Object
     * 
     * <pre>
     * 
     * Assignment: #4
     * Course: ADEV-3001
     * Date Created: April 10, 2017
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
    public class Entry<K, V>
    {
        // Private Properties
        private K key;
        private V value;
        
        /**
         * Constructor for objects of Entry
         * @param key The key of the Entry
         * @param value The value of the Entry
         */
        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Get The key of the Entry
         * 
         * @return The key of the Entry
         */
        public K getKey()
        {
            return this.key;
        }
        
        /**
         * Get The value of the Entry
         * 
         * @return The value of the Entry
         */
        public V getValue()
        {
            return this.value;
        }
        
        /**
         * set The value of the Entry
         * @param value The new value of the Entry
         * @return The old value of the Entry
         */
        public void setValue(V value)
        {
            this.value = value;
        }
        
        @Override
        /**
         * return the employee info: id firstName lastName
         * @return String value representing the Employee
         */
        public String toString()
        {
            return this.key + " " + this.value;
        }
    }
    
    /**
     * KeyIterator  - An Iterator holds all the keys in the table
     * 
     * <pre>
     * 
     * Assignment: #4
     * Course: ADEV-3001
     * Date Created: April 10, 2017
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
    private class KeyIterator<K, V> implements Iterator<K>
    {
        // Private Properties
        private Entry values[];
        private int next;
        
        /**
         * Constructor for objects of KeyIterator
         * @param values The HashMap
         */
        public KeyIterator(Entry[] values)
        {
            this.values = values;
            next = 0;
        }
        
        /**
         * check if there is another Entry in the table
         * 
         * @return ture if has the next false if not
         */
        public boolean hasNext() 
        {
            while(next<values.length-1 && (values[next] == null || values[next].equals(available)))
            {
                next++;
            }
            return (values[next] != null && !values[next].equals(available)); 
        }
        
        /**
         * return the key of the next Entry in the table
         * 
         * @return the key of the next Entry in the table
         */
        public K next ()
        {
            Entry<K, V> current = (Entry<K, V>)values[next];
            next++;
            return current.getKey();
        }
    }
    
    /**
     * ValueIterator  - An Iterator holds all the values in the table
     * 
     * <pre>
     * 
     * Assignment: #4
     * Course: ADEV-3001
     * Date Created: April 10, 2017
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
    private class ValueIterator<K, V> implements Iterator<V>
    {
        // Private Properties
        private Entry values[];
        private int next;
        
        /**
         * Constructor for objects of ValueIterator
         * @param values The HashMap
         */
        public ValueIterator(Entry[] values)
        {
            this.values = values;
            next = 0;
        }
        
        /**
         * check if there is another Entry in the table
         * 
         * @return ture if has the next false if not
         */
        public boolean hasNext() 
        {
            while(next<values.length-1 && (values[next] == null || values[next].equals(available)))
            {
                next++;
            }
            return (values[next] != null && !values[next].equals(available)); 
        }
        
        /**
         * return the value of the next Entry in the table
         * 
         * @return the value of the next Entry in the table
         */
        public V next ()
        {
            Entry<K, V> current = (Entry<K, V>)values[next];
            next++;
            return current.getValue();
        }
    }
}
