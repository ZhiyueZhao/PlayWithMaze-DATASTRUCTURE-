import java.io.File;
import java.io.*;
import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
* DepthFirstTest – This class is used to test DepthFirst
*
* <pre>
*
* Assignment:   #4
* Course:       ADEV-3001
* Date Created: April 15, 2017
*
* Revision Log
* Who When Reason
* --------- ---------- ----------------------------------
*
* </pre>
*
* @author Zhiyue Zhao
* @version 1.0
*
*/
@SuppressWarnings("unchecked")
public class DepthFirstTest
{
    private char[][] hasExit, noExit, noItem;
    private MazePoint[][] mHasExit, mNoExit, mNoItem;
    private int row, column;
    private final String[] items = {"Jeweled Bracelet", "Diamond", "Helmet", "Crossbow", "Scroll"};
    private LinkedList<String> itemList = new LinkedList();
    private LinkedList<String> itemListTest = new LinkedList();
    private HashMap<Item, ItemValue> hmTest = new HashMap(5);
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Stack<MazePoint> stack = new Stack<MazePoint>();
    
    /**
     * Default constructor for test class DepthFirstTest
     */
    public DepthFirstTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        //prepare mazes for use
        noExit = new char[][]
                  {{'W', 'W', 'W', 'W', 'W', 'W'},
                   {'W', ' ', ' ', 'I', ' ', 'W'},
                   {'W', 'I', ' ', ' ', 'I', 'W'},
                   {'W', 'W', 'W', ' ', 'W', 'W'},
                   {'E', 'W', 'I', ' ', 'I', 'W'},
                   {'W', 'W', 'W', 'W', 'W', 'W'}};
                   
        mNoExit = buildMaze(noExit);
        
        hasExit = new char[][]
                  {{'W', 'W', 'W', 'W', 'W', 'W'},
                   {'W', ' ', ' ', 'I', ' ', 'W'},
                   {'W', 'I', 'I', 'W', ' ', 'W'},
                   {'W', 'I', 'W', 'W', 'W', 'W'},
                   {'E', ' ', ' ', 'I', ' ', 'W'},
                   {'W', 'W', 'W', 'W', 'W', 'W'}};
                   
        mHasExit = buildMaze(hasExit);
        
        noItem = new char[][]
                  {{'W', 'W', 'W', 'W', 'W', 'W'},
                   {'W', ' ', ' ', 'I', ' ', 'W'},
                   {'W', ' ', ' ', 'W', ' ', 'W'},
                   {'W', ' ', 'W', 'W', 'W', 'W'},
                   {'E', ' ', ' ', ' ', ' ', 'W'},
                   {'W', 'W', 'W', 'W', 'W', 'W'}};
                   
        mNoItem = buildMaze(noItem);
        
        buildHashMap();
        
        itemListTest.insert("Diamond");
        itemListTest.insert("Crossbow");
        
        //set starting point
        row = 1;
        column = 4;
        
        stack.push(new MazePoint(row, column, noExit[row][column]));
        
        //set stream to catch output
        System.setOut(new PrintStream(outContent));
    }
    
    private MazePoint[][] buildMaze(char[][] mazeBegin)
    {
        int k = 0;
        MazePoint[][] maze = new MazePoint[mazeBegin.length][mazeBegin[0].length];
        for (int i = 0; i < mazeBegin.length; i++) {
            for (int j = 0; j < mazeBegin[0].length; j++) {
                if ((mazeBegin[i][j] == 'W') || (mazeBegin[i][j] == ' ') || (mazeBegin[i][j] == 'E'))
                {
                    maze[i][j] = new MazePoint(i, j, mazeBegin[i][j]);
                }
                else
                {
                    String itemName = items[k];
                    k++;
                    maze[i][j] = new MazePoint(i, j, 'I', itemName);
                }
            }
        }
        
        return maze;
    }
    
    /**
     * Get the hashMap from the file ItemData.txt
     */
    private void buildHashMap()
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
                    }
                }
            }
            catch(Exception e)
            {
                 e.printStackTrace();
            }
        }
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        //clean streams
        System.setOut(null);
    }
    
    /**
     * Test public All Argument Constructor of the class
     */
    @Test
    public void testAllArgConstructor() throws NoSuchFieldException, 
                                               ClassCastException, 
                                               IllegalArgumentException, 
                                               IllegalAccessException
    {
        //to store current private member
        Field privateField;
       
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mHasExit, itemList, hmTest);
        
        //extract and test "maze"
        privateField = DepthFirst.class.getDeclaredField("cMaze");
        privateField.setAccessible(true);
        MazePoint[][] maze = (MazePoint[][]) privateField.get(depthFirst1);
        assertArrayEquals(mHasExit, maze);
        
        //extract and test "stack"
        privateField = DepthFirst.class.getDeclaredField("sPath");
        privateField.setAccessible(true);
        Stack<MazePoint> stack = (Stack<MazePoint>) privateField.get(depthFirst1);
        assertTrue(stack.isEmpty());
        
        //extract and test "inventoryList"
        privateField = DepthFirst.class.getDeclaredField("inventoryList");
        privateField.setAccessible(true);
        LinkedList<String> inventoryList = (LinkedList<String>) privateField.get(depthFirst1);
        assertTrue(inventoryList.isEmpty());
        
        //extract and test "inventoryList"
        privateField = DepthFirst.class.getDeclaredField("availableItemsHashMap");
        privateField.setAccessible(true);
        HashMap<Item, ItemValue> availableItemsHashMap = (HashMap<Item, ItemValue>) privateField.get(depthFirst1);
        assertEquals(hmTest, availableItemsHashMap);
    }
    
    /**
     * Test public method to ensure it follows directional order S E W N,
     * marks visited Points properly, and produces desired output
     */
    @Test
    public void testDepthFirstSearchHasExit()
    {
        //value to test against
        String output1 = "Path to follow from Start [1, 4] to Exit [4, 0] - 8 steps:\r\n" + 
                        "[1, 4]\r\n" +
                        "[1, 3]) - Jeweled Bracelet\r\n" +
                        "[1, 2]\r\n" +
                        "[2, 2]\r\n" +
                        "[2, 1]\r\n" +
                        "[3, 1]\r\n" +
                        "[4, 1]\r\n" +
                        "[4, 0]\r\n\n" +
                        "WWWWWW\n" +
                        "W ...W\n" +
                        "W..WVW\n" +
                        "W.WWWW\n" +
                        "..VVVW\n" +
                        "WWWWWW\n" +
                        "Crossbow                                     350GP\r\n" +
                        "Diamond                                     5000GP\r\n" +
                        "Helmet                                        35GP\r\n" +
                        "Scroll                                        10GP\r\n" +
                        "                                          --------\r\n" +
                        "Total                                       5395GP\n";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mHasExit, itemList, hmTest);
        
        //execute method
        depthFirst1.DepthFirstSearch(row, column);
        
        String output2 = outContent.toString();
        //test output
        assertEquals(output1, outContent.toString());
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testDepthFirstSearchNoExit()
    {
        //value to test against
        String output1 = "There is no exit out of the maze.\r\n" +
                        "WWWWWW\n" +
                        "WVVVVW\n" +
                        "WVVVVW\n" +
                        "WWWVWW\n" +
                        "EWVVVW\n" +
                        "WWWWWW\n" +
                        "Crossbow                                     350GP\r\n" +
                        "Diamond                                     5000GP\r\n" +
                        "Helmet                                        35GP\r\n" +
                        "Scroll                                        10GP\r\n" +
                        "                                          --------\r\n" +
                        "Total                                       5395GP\n";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mNoExit, itemList, hmTest);
        
        //execute method
        depthFirst1.DepthFirstSearch(row, column);
        
        String output2 = outContent.toString();
        //test output
        assertEquals(output1, outContent.toString());
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testDepthFirstSearchNoItem()
    {
        //value to test against
        String output1 = "Path to follow from Start [1, 4] to Exit [4, 0] - 8 steps:\r\n" + 
                        "[1, 4]\r\n" +
                        "[1, 3]) - Jeweled Bracelet\r\n" +
                        "[1, 2]\r\n" +
                        "[2, 2]\r\n" +
                        "[2, 1]\r\n" +
                        "[3, 1]\r\n" +
                        "[4, 1]\r\n" +
                        "[4, 0]\r\n\n" +
                        "WWWWWW\n" +
                        "W ...W\n" +
                        "W..WVW\n" +
                        "W.WWWW\n" +
                        "..VVVW\n" +
                        "WWWWWW\n" +
                        "No items were found exploring the maze.";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mNoItem, itemList, hmTest);
        
        //execute method
        depthFirst1.DepthFirstSearch(row, column);
        
        String output2 = outContent.toString();
        //test output
        assertEquals(output1, outContent.toString());
    }
    
    /**
     * Test private method to ensure it produces desired output
     */
    @Test
    public void testLeftPad() throws NoSuchFieldException, 
                                     ClassCastException, 
                                     IllegalArgumentException, 
                                     IllegalAccessException,
                                     NoSuchMethodException,
                                     SecurityException,
                                     InvocationTargetException
    {
        //to store current private member
        Method privateMethod;
        
        //value to test against
        String output1 = "  George";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mNoItem, itemList, hmTest);
        
        //extract and invoke "leftPad"
        privateMethod = DepthFirst.class.getDeclaredMethod("LeftPad", new Class[]{String.class, int.class});
        privateMethod.setAccessible(true);
        
        //execute method
        String output2 = (String) privateMethod.invoke(depthFirst1,new Object[]{"George", 8});
        
        //test result
        assertEquals(output1, output2);
    }
    
    /**
     * Test private method to ensure it produces desired output
     */
    @Test
    public void testPrintItems() throws NoSuchFieldException, 
                                     ClassCastException, 
                                     IllegalArgumentException, 
                                     IllegalAccessException,
                                     NoSuchMethodException,
                                     SecurityException,
                                     InvocationTargetException
    {
        String output1 = "No items were found exploring the maze.";
        //to store current private member
        Method privateMethod;

        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mNoItem, itemList, hmTest);
        
        //extract and invoke "leftPad"
        privateMethod = DepthFirst.class.getDeclaredMethod("PrintItems");
        privateMethod.setAccessible(true);
        
        privateMethod.invoke(depthFirst1);
        //execute method
        String output2 = outContent.toString();
        assertEquals(output1, output2);
        //clear the ByteArrayOutputStream
        outContent.reset();
        depthFirst1 = new DepthFirst(mNoItem, itemListTest, hmTest);
        output1 = "Crossbow                                     350GP\r\n" +
                  "Diamond                                     5000GP\r\n" +
                  "                                          --------\r\n" +
                  "Total                                       5350GP\n";
                  
        privateMethod.invoke(depthFirst1);
        String output3 = outContent.toString();
        //test result
        assertEquals(output1, output3);
    }
    
    /**
     * Test private method to ensure it produces desired output
     */
    @Test
    public void testPrintPath() throws NoSuchFieldException, 
                                     ClassCastException, 
                                     IllegalArgumentException, 
                                     IllegalAccessException,
                                     NoSuchMethodException,
                                     SecurityException,
                                     InvocationTargetException
    {
        String output1 = "There is no exit out of the maze.\r\n" +
                        "WWWWWW\n" +
                        "W  I W\n" +
                        "WI  IW\n" +
                        "WWW WW\n" +
                        "EWI IW\n" +
                        "WWWWWW\n" +
                        "No items were found exploring the maze.";
        //to store current private member
        Method privateMethod;

        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(mNoExit, itemList, hmTest);
        
        //extract and invoke "PrintPath"
        Method[] m = DepthFirst.class.getDeclaredMethods();
        privateMethod = m[0];
         for(int i = 0; i < m.length; i++) {
             String mm = m[i].toString();
            if(m[i].toString().equals("private void DepthFirst.PrintPath(Stack,boolean)")){
                privateMethod = m[i];
            }
         }
        privateMethod.setAccessible(true);
        privateMethod.invoke(depthFirst1,new Object[]{new Stack<MazePoint>(), false});
        String output2 = outContent.toString();
        assertEquals(output1, output2);
        //clear the ByteArrayOutputStream
        outContent.reset();
        output1 = "Path to follow from Start [1, 4] to Exit [1, 4] - 1 steps:\r\n" + 
                        "[1, 4]\r\n\n" +
                        "WWWWWW\n" +
                        "W  I.W\n" +
                        "WI  IW\n" +
                        "WWW WW\n" +
                        "EWI IW\n" +
                        "WWWWWW\n" +
                        "No items were found exploring the maze.";
        privateMethod.invoke(depthFirst1, new Object[]{stack, true});
        output2 = outContent.toString();
        assertEquals(output1, output2);
        
    }
}
