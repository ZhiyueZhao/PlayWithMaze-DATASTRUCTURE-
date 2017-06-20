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
* Assignment:   #2
* Course:       ADEV-3001
* Date Created: March 5, 2017
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
public class DepthFirstTest
{
    //--------------------------------------------------------------------------
    /*Testing Properties*/
    private char[][] hasExit, noExit;
    private int row, column;
 
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*JUnit Test Management Methods*/
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
                   {'W', ' ', 'W', 'E', ' ', 'W'},
                   {'W', ' ', 'W', ' ', ' ', 'W'},
                   {'W', ' ', 'W', 'W', 'W', 'W'},
                   {'W', ' ', ' ', ' ', ' ', 'W'},
                   {'W', 'W', 'W', 'W', 'W', 'W'}};
        
        hasExit = new char[][]
                  {{'W', 'W', 'W', 'W', 'W', 'W'},
                   {'W', ' ', ' ', ' ', ' ', 'W'},
                   {'W', ' ', ' ', 'W', ' ', 'W'},
                   {'W', ' ', 'W', 'W', 'W', 'W'},
                   {'E', ' ', ' ', ' ', ' ', 'W'},
                   {'W', 'W', 'W', 'W', 'W', 'W'}};
        
        //set starting point
        row = 1;
        column = 1;
        
        //set stream to catch output
        System.setOut(new PrintStream(outContent));
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
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Milestone 2 Tests*/
    
    @Test
    public void testAllArgConstructor() throws NoSuchFieldException, 
                                               ClassCastException, 
                                               IllegalArgumentException, 
                                               IllegalAccessException
    {
        //to store current private member
        Field privateField;
       
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(hasExit);
        
        //extract and test "maze"
        privateField = DepthFirst.class.getDeclaredField("cMaze");
        privateField.setAccessible(true);
        
        char[][] maze = (char[][]) privateField.get(depthFirst1);
        assertArrayEquals(hasExit, maze);
        
        //extract and test "stack"
        privateField = DepthFirst.class.getDeclaredField("sPath");
        privateField.setAccessible(true);
        
        Stack stack = (Stack) privateField.get(depthFirst1);
        assertTrue(stack.isEmpty());
    }
    
    /**
     * Test public method to ensure it produces desired output
     */
    @Test
    public void testDepthFirstSearchAtExit()
    {
        //value to test against
        String output = "Path to follow from Start [0,0] to Exit [0,0] - 1 steps:\r\n" + 
                        "[0,0]\r\n\n" +
                        ".\n";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(new char[][]{{'E'}});
        
        //execute method
        depthFirst1.DepthFirstSearch(0, 0);
        
        String output2 = outContent.toString();
        
        //test output
        assertEquals(output, output2);
    }
    
    /**
     * Test public method to ensure it follows directional order S E W N,
     * marks visited Points properly, and produces desired output
     */
    @Test
    public void testDepthFirstSearchHasExit()
    {
        //value to test against
        String output = "Path to follow from Start [1,1] to Exit [4,0] - 5 steps:\r\n" + 
                        "[1,1]\r\n" +
                        "[2,1]\r\n" +
                        "[3,1]\r\n" +
                        "[4,1]\r\n" +
                        "[4,0]\r\n\n" +
                        "WWWWWW\n" +
                        "W.   W\n" +
                        "W. W W\n" +
                        "W.WWWW\n" +
                        "..VVVW\n" +
                        "WWWWWW\n";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(hasExit);
        
        //execute method
        depthFirst1.DepthFirstSearch(row, column);
        
        String output2 = outContent.toString();
        //test output
        assertEquals(output, outContent.toString());
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testDepthFirstSearchNoExit()
    {
        //value to test against
        String output = "There is no exit out of the maze.\r\n" +
                        "WWWWWW\n" +
                        "WVWE W\n" +
                        "WVW  W\n" +
                        "WVWWWW\n" +
                        "WVVVVW\n" +
                        "WWWWWW\n";
      
        //Create a DepthFirst instance
        DepthFirst depthFirst1 = new DepthFirst(noExit);
        
        //execute method
        depthFirst1.DepthFirstSearch(row, column);
        
        String output2 = outContent.toString();
        //test output
        assertEquals(output, outContent.toString());
    }
    //--------------------------------------------------------------------------
}
