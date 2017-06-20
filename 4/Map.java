import java.util.Iterator;
/**
 * Map  - An interface that Specifies the methods a map must implement..
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
public interface Map<K, V>
{
    /**
     * Get the size of the map
     * 
     * @return     the size of the map
     */
    public int getSize();
    
    /**
     * Check if the map is empty
     * 
     * @return     true if the map is empty, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Clear the map
     */
    public void clear();
    
    /**
     * Get the value based on the key from the map
     * 
     * @return     value based on the key, null if nothing found
     */
    public V get(K key);
    
    /**
     * Insert a new Entry to the map
     * 
     * @return     value based on the key, null if nothing found
     */
    public V put(K key, V value);
    
    /**
     * Remove the Entry based on the key from the map
     * 
     * @return     value based on the key, null if nothing found
     */
    public V remove(K key);
    
    /**
     * Returns an iterator of all keys in the Map
     * 
     * @return     an iterator of all keys in the Map
     */
    public Iterator keys();
    
    /**
     * Returns an iterator of all values in the Map
     * 
     * @return     an iterator of all values in the Ma
     */
    public Iterator values();
}
