
/**
 * DepthFirst  - A class used to used to search a maze by depth.
 * 
 * <pre>
 * 
 * Assignment: #1
 * Course: ADEV-3001
 * Date Created: March 5, 2017
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
public class DepthFirst
{
    // instance variables - replace the example below with your own
    private char[][] cMaze;
    private Stack<Point> sPath = new Stack<Point>();
    private final char VISITED_MARKER = 'V', EXIT_MAKER = 'E',HALL_MAKER = ' ',PATH_MAKER = '.';
    /**
     * Constructor for objects of class DepthFirst
     */
    public DepthFirst(char[][] cMaze)
    {
        // initialise instance variables
        this.cMaze = cMaze;
    }

    /**
    * Searches the maze recursively using depth first approach with a Stack
    * @param row The numerical row of the start point
    * @param column The numerical column of the start point
    */
    public Stack<Point> DepthFirstSearch(int row, int column)
    {
        Point p;
        if(cMaze[row][column] == EXIT_MAKER)
        {
            p = new Point(row, column);
            sPath.push(p);
            //print out the path
            PrintExitPath(sPath);
            return sPath;
        }
        else
        {
            if(cMaze[row][column] != VISITED_MARKER)
            {
                p = new Point(row, column);
                sPath.push(p);
                cMaze[row][column] = VISITED_MARKER;
            }
            //determine where to traverse next (S, E, W, N)
            //recursively
            if(cMaze[row+1][column] == HALL_MAKER || cMaze[row+1][column] == EXIT_MAKER)// or (cMaze[row+1][column] != VISITED_MARKER && cMaze[row+1][column] != 'W')
            {
                return DepthFirstSearch(row+1, column);
            }
            else if(cMaze[row][column+1] == HALL_MAKER || cMaze[row][column+1] == EXIT_MAKER)
            {
                return DepthFirstSearch(row, column+1);
            }
            else if(cMaze[row][column-1] == HALL_MAKER || cMaze[row][column-1] == EXIT_MAKER)
            {
                return DepthFirstSearch(row, column-1);
            }
            else if(cMaze[row-1][column] == HALL_MAKER || cMaze[row-1][column] == EXIT_MAKER)
            {
                return DepthFirstSearch(row-1, column);
            }
            //no where could go, then return
            else
            {
                 sPath.pop();
                 if(sPath.isEmpty())
                 {
                     //print nothing found
                     PrintNoExit();
                     return sPath;
                 }
                 Point mp = sPath.top();
                 int mpRow = mp.getRow();
                 int mpColumn = mp.getColumn();
                 return DepthFirstSearch(mpRow, mpColumn);
            }
        }
    }
    
    /**
    * Print a message for being unable to find the exit of the maze
    */
    private void PrintNoExit()
    {
        System.out.println("There is no exit out of the maze.");
        
        PrintContents();
    }
    
    /**
    * Print the path to the exit of the maze
    */
    private void PrintExitPath(Stack<Point> stack)
    {
        //reverse the sPath
        Stack<Point> sExitPath = new Stack<Point>();
        Point exitPoint = stack.top();
        int size = stack.getSize();
        while(!stack.isEmpty())
        {
            Point p = stack.top();
            sExitPath.push(p);
            stack.pop();
            
            int pRow = p.getRow();
            int pColumn = p.getColumn();
            cMaze[pRow][pColumn] = PATH_MAKER;
        }
        Point startPoint = sExitPath.top();
        
        System.out.println("Path to follow from Start " + startPoint.toString() + " to Exit " + exitPoint.toString() + " - " + size + " steps:");
        //print the sExitPath
        while(!sExitPath.isEmpty())
        {
            Point p = sExitPath.top();
            sExitPath.pop();
            System.out.println(p.toString());
            //System.out.print(p.toString());
        }
        System.out.print("\n");
        PrintContents();
    }
    
    /**
    * Print the maze
    */
    private void PrintContents()
    {
        for(int i=0; i< cMaze.length; i++)
        {
            for(int j=0; j<cMaze[i].length; j++)
            {
                System.out.print(cMaze[i][j]);
            }
            System.out.print("\n");
        }
    }
}
