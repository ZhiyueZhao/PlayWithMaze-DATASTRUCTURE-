import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;

/** BreadthFirstTest – This class is used to test BreadthFirst
*
* <pre>
*
* Assignment:   #3
* Course:       ADEV-3001
* Date Created: March 24, 2017
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
public class BreadthFirstTest
{
    private char[][] hasExit, noExit;
    private int row, column;
    
    /**
     * Default constructor for test class BreadthFirstTest
     */
    public BreadthFirstTest()
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
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test public method to ensure it produces desired output
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
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //extract and test "maze"
        privateField = BreadthFirst.class.getDeclaredField("cMaze");
        privateField.setAccessible(true);
        
        char[][] maze = (char[][]) privateField.get(breadthFirst1);
        assertArrayEquals(hasExit, maze);
    }
    
    /**
     * Test public method to ensure it follows directional order S E W N,
     * marks visited Points properly, and produces desired output
     */
    @Test
    public void testBreadthFirstSearchHasExit()
    {
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        Boolean result = breadthFirst1.BreadthFirstSearch(row, column);
        
        assertEquals(result, true);
        
        //Create a BreadthFirst instance
        breadthFirst1 = new BreadthFirst(noExit);
        
        //execute method
        result = breadthFirst1.BreadthFirstSearch(row, column);
        
        assertEquals(result, false);
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testNoExit()
    {
        String exceptString = "There is no exit out of the maze.\n";
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(noExit);
        
        //execute method
        breadthFirst1.BreadthFirstSearch(row, column);
        String outputString = breadthFirst1.noExit();
        
        assertEquals(exceptString, outputString);
    }
    
    /**
     * Test the NoExit method to ensure it returns the proper Exception.
     */
    @Test (expected = IllegalStateException.class)
    public void testExceptionForNoExit()
    {        
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(noExit);
        
        //execute method
        String outputString = breadthFirst1.noExit();
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testExitFound()
    {
        String exceptString = "Path to follow from Start [1, 1] to Exit [4, 0] - 5 steps:\n" + 
                        "[1, 1]\n" +
                        "[2, 1]\n" +
                        "[3, 1]\n" +
                        "[4, 1]\n" +
                        "[4, 0]\n";
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        breadthFirst1.BreadthFirstSearch(row, column);
        String outputString = breadthFirst1.exitFound();
        
        assertEquals(exceptString, outputString);
        
        //value to test against
        exceptString = "Path to follow from Start [0, 0] to Exit [0, 0] - 1 steps:\n" + 
                        "[0, 0]\n";
      
        //Create a DepthFirst instance
        breadthFirst1 = new BreadthFirst(new char[][]{{'E'}});
        
        //execute method
        breadthFirst1.BreadthFirstSearch(0, 0);
        outputString = breadthFirst1.exitFound();
        
        assertEquals(exceptString, outputString);
    }
    
    /**
     * Test the ExitFound method to ensure it returns the proper Exception.
     */
    @Test (expected = IllegalStateException.class)
    public void testExceptionForExitFound()
    {        
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        String outputString = breadthFirst1.exitFound();
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testPathToFollow()
    {
        //value to test against
        Stack<Point> exceptStack = new Stack<Point>();
      
        //Create a DepthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(new char[][]{{'E'}});
        
        //execute method
        breadthFirst1.BreadthFirstSearch(0, 0);
        Stack<Point> sExitPath = breadthFirst1.pathToFollow();
        
        Point p = new Point(0, 0);
        exceptStack.push(p);
        
        assertEquals(sExitPath.getSize(), exceptStack.getSize());
        
        Point exceptPoint = exceptStack.pop();
        Point outputPoint = sExitPath.pop();
        
        assertEquals(outputPoint.getRow(), exceptPoint.getRow());
        assertEquals(outputPoint.getColumn(), exceptPoint.getColumn());
        assertEquals(outputPoint.getParentRow(), exceptPoint.getParentRow());
        assertEquals(outputPoint.getParentColumn(), exceptPoint.getParentColumn());
        
        p = new Point(4, 0 , 4, 1);
        exceptStack.push(p);
        p = new Point(4, 1 , 3, 1);
        exceptStack.push(p);
        p = new Point(3, 1 , 2, 1);
        exceptStack.push(p);
        p = new Point(2, 1 , 1, 1);
        exceptStack.push(p);
        p = new Point(1, 1);
        exceptStack.push(p);
      
        //Create a DepthFirst instance
        breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        breadthFirst1.BreadthFirstSearch(row, column);
        sExitPath = breadthFirst1.pathToFollow();
        
        assertEquals(sExitPath.getSize(), exceptStack.getSize());
        
        while(!sExitPath.isEmpty())
        {
            exceptPoint = exceptStack.pop();
            outputPoint = sExitPath.pop();
            assertEquals(outputPoint.getRow(), exceptPoint.getRow());
            assertEquals(outputPoint.getColumn(), exceptPoint.getColumn());
            assertEquals(outputPoint.getParentRow(), exceptPoint.getParentRow());
            assertEquals(outputPoint.getParentColumn(), exceptPoint.getParentColumn());
        }
    }
    
    /**
     * Test the pathToFollow method to ensure it returns the proper Exception.
     */
    @Test (expected = IllegalStateException.class)
    public void testExceptionForPathToFollow()
    {        
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        breadthFirst1.pathToFollow();
    }
    
    /**
     * Test public method to ensure it marks visited Points properly,
     * exhausts all paths, and produces desired output
     */
    @Test
    public void testDumpMaze()
    {
        //value to test against
        String exceptString = ".\n";
      
        //Create a DepthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(new char[][]{{'E'}});
        
        //execute method
        breadthFirst1.BreadthFirstSearch(0, 0);
        breadthFirst1.exitFound();
        String outputString = breadthFirst1.dumpMaze();
        
        assertEquals(exceptString, outputString);
        
        //value to test against
        exceptString = "WWWWWW\n" +
                        "W.vvvW\n" +
                        "W.vW W\n" +
                        "W.WWWW\n" +
                        "..v  W\n" +
                        "WWWWWW\n";
      
        //Create a DepthFirst instance
        breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        breadthFirst1.BreadthFirstSearch(row, column);
        breadthFirst1.exitFound();
        outputString = breadthFirst1.dumpMaze();
        
        assertEquals(exceptString, outputString);
        
        //value to test against
        exceptString = "WWWWWW\n" +
                        "WvWE W\n" +
                        "WvW  W\n" +
                        "WvWWWW\n" +
                        "WvvvvW\n" +
                        "WWWWWW\n";
      
        //Create a DepthFirst instance
        breadthFirst1 = new BreadthFirst(noExit);
        
        //execute method
        breadthFirst1.BreadthFirstSearch(row, column);
        breadthFirst1.noExit();
        outputString = breadthFirst1.dumpMaze();
        
        assertEquals(exceptString, outputString);
    }
    
    /**
     * Test the DumpMaze method to ensure it returns the proper Exception.
     */
    @Test (expected = IllegalStateException.class)
    public void testExceptionForDumpMaze()
    {        
        //Create a BreadthFirst instance
        BreadthFirst breadthFirst1 = new BreadthFirst(hasExit);
        
        //execute method
        String outputString = breadthFirst1.dumpMaze();
    }
}
