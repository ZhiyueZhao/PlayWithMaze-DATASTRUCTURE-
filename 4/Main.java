import java.io.File;
import java.io.*;
import java.util.*;
/**
 * Main  - A class used to start the application.
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
 * Zhiyue Zhao  April 15,2017 final mile stone
 * 
 * </pre>
 * 
 * @author Zhiyue Zhao 
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class Main
{
    private Main()
    {
    }
    
    /**
    * A method to read the whole maze
    * @param args Collection of arguments passed to the application
    */
    public static void main(String[] args)
    {
        File path = new File("ItemData.txt");
        
        if(path == null)
        {
            System.out.println("No file selected.");
        }
        else
        {
            try
            {
                //Create a hashmap and specify an initial capacity of 5. Leave the loadFactor default.
                HashMap<Item, ItemValue> hmTest = new HashMap(5);
                //Load the HashMap with the list of objects that may be found (read from the Item file provided).
                //open file
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);
                
                String s="";
                String [] stringArr;
                while((s = br.readLine()) != null) 
                {
                    stringArr = s.split(" ");
                    Item item = new Item(stringArr[0]);
                    ItemValue itemValue = new ItemValue(Integer.parseInt(stringArr[1]));
                    hmTest.put(item, itemValue);
                }
                
                Iterator itr = hmTest.keys();
                
                while(itr.hasNext())
                {
                    Item item = (Item)itr.next();
                    ItemValue itemValue = (ItemValue)hmTest.get(item);
                    //If any item is worth zero gold pieces, then remove it from the hash table.
                    if(itemValue.getGoldPieces() == 0)
                    {
                        hmTest.remove(item);
                        //System.out.println(item.toString() + " is removed." );
                    }
                    //else
                    //{
                        //System.out.println(item.toString());
                    //}
                }
                //final milestone
                LinkedList<String> itemList = new LinkedList();
        
                MazePoint[][] mazePoints = Maze.getMaze();
                
                int[] iStartCoordinates = Maze.getStartCoordinates();
                
                //create a DepthFirst instance to process the maze
                DepthFirst processor = new DepthFirst(mazePoints, itemList, hmTest);
    
                //process maze
                processor.DepthFirstSearch(iStartCoordinates[0], iStartCoordinates[1]);
    
                //print out inventory
                System.out.print("\n");
            }
            catch(Exception e)
            {
                 e.printStackTrace();
            }
        }
    }
}
