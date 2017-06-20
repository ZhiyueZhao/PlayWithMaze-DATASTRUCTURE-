
/**
 * BreadthFirst  - A class used to used to search a maze by breadth.
 * 
 * <pre>
 * 
 * Assignment: #3
 * Course: ADEV-3001
 * Date Created: March 19, 2017
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
public class BreadthFirst
{
    // a private reference to the maze to be solved
    private char[][] cMaze;
    private boolean bSolved = false;
    private Stack<Point> sPointTravelled = new Stack<Point>();
    private final char VISITED_MARKER = 'v', EXIT_MAKER = 'E',HALL_MAKER = ' ',PATH_MAKER = '.';
    
    /**
     * Constructor for objects of class BreadthFirst
     */
    public BreadthFirst(char[][] cMaze)
    {
        // initialise instance variables
        this.cMaze = cMaze;
    }

    /**
    * Looping the maze by using breadth first approach to find the path with a Queue
    * @param row The numerical row of the start point
    * @param column The numerical column of the start point
    * @return true=exit found; false=no exit
    */
    public boolean BreadthFirstSearch(int row, int column)
    {
        bSolved = true;
        
        Point p;
        p = new Point(row, column);
        sPointTravelled.push(p);
        
        //If it is the exit you are done, exit
        if(cMaze[row][column] == EXIT_MAKER)
        {
            return true;
        }
        Queue<Point> qPoint = new Queue<Point>();
        //enqueue() the starting point 
        qPoint.enqueue(p);
        //mark first point as visited
        cMaze[row][column] = VISITED_MARKER;
        
        //If the queue ever becomes empty, the entire maze has been checked and no exit exists Terminate!!
        while(!qPoint.isEmpty())
        {
            //traverse the maze in this order: S, E, W, N.
            //dequeue() a location for processing
            p = qPoint.dequeue();
            int rowProcessing = p.getRow();
            int columnProcessing = p.getColumn();
            
            //If it is not
            //Visit the child in the first movement direction (‘S’) provided it is not marked as visited already
            //Queue the child for later processing
            //Mark the child as visited
            p = new Point(rowProcessing+1, columnProcessing, rowProcessing, columnProcessing);
            sPointTravelled.push(p);
            
            if(cMaze[rowProcessing+1][columnProcessing] == HALL_MAKER)
            {
                cMaze[rowProcessing+1][columnProcessing] = VISITED_MARKER;
                qPoint.enqueue(p);
            }
            else if(cMaze[rowProcessing+1][columnProcessing] == EXIT_MAKER)
            {
                return true;
            }
            
            p = new Point(rowProcessing, columnProcessing+1, rowProcessing, columnProcessing);
            sPointTravelled.push(p);
            if(cMaze[rowProcessing][columnProcessing+1] == HALL_MAKER)
            {
                cMaze[rowProcessing][columnProcessing+1] = VISITED_MARKER;
                qPoint.enqueue(p);
            }
            else if(cMaze[rowProcessing][columnProcessing+1] == EXIT_MAKER)
            {
                return true;
            }
            
            p = new Point(rowProcessing, columnProcessing-1, rowProcessing, columnProcessing);
            sPointTravelled.push(p);
            if(cMaze[rowProcessing][columnProcessing-1] == HALL_MAKER)
            {
                cMaze[rowProcessing][columnProcessing-1] = VISITED_MARKER;
                qPoint.enqueue(p);
            }
            else if(cMaze[rowProcessing][columnProcessing-1] == EXIT_MAKER)
            {
                return true;
            }
            
            p = new Point(rowProcessing-1, columnProcessing, rowProcessing, columnProcessing);
            sPointTravelled.push(p);
            if(cMaze[rowProcessing-1][columnProcessing] == HALL_MAKER)
            {
                cMaze[rowProcessing-1][columnProcessing] = VISITED_MARKER;
                qPoint.enqueue(p);
            }
            else if(cMaze[rowProcessing-1][columnProcessing] == EXIT_MAKER)
            {
                return true;
            }
        }
        
        return false;
    }
    
    /**
    * Return a message for being unable to find the exit of the maze
    * @return a string indicating there is no exit out of the maze
    * @throws IllegalStateException If is called before a maze has been solved
    */
    public String noExit()
    {
        if(!bSolved)
        {
            throw new IllegalStateException("A maze has not been solved!");
        }
        return "There is no exit out of the maze.\n";
    }
    
    /**
    * Print the path to the exit of the maze
    * @return a string indicating there is an exit
    * @throws IllegalStateException If is called before a maze has been solved
    */
    public String exitFound()
    {
        String sReturn = "";
        Stack<Point> sExitPath = pathToFollow();
        
        Point startPoint = sExitPath.top();
        int size = sExitPath.getSize();
        
        while(sExitPath.getSize() != 1)
        {
            Point p = sExitPath.pop();
            sReturn += p.toString() + "\n";
        }
        Point exitPoint = sExitPath.pop();
        sReturn += exitPoint.toString() + "\n";
        
        sReturn = "Path to follow from Start " + startPoint.toString() + " to Exit " + exitPoint.toString() + " - " + size + " steps:\n" + sReturn;
        
        return sReturn;
    }
    
    /**
    * Return a stack containing from the starting point to the exit point
    * @return a stack containing the points to follow to navigate from the starting point to the exit(The starting point must be first in the stack and the exit last. but here is reversed)
    * @throws IllegalStateException If is called before a maze has been solved
    */
    public Stack<Point> pathToFollow()
    {
        if(!bSolved)
        {
            throw new IllegalStateException("A maze has not been solved!");
        }
        
        Stack<Point> sExitToStart = new Stack<Point>();
        Point p, pProcessing;
        //pop the exit point
        p = sPointTravelled.pop();
        //insert exit point to the return stack
        sExitToStart.push(p);
        //mark the exit point as path
        cMaze[p.getRow()][p.getColumn()]=PATH_MAKER;
        
        while(!sPointTravelled.isEmpty())
        {
            //get the last point in the return stack
            pProcessing = sExitToStart.top();
            //pop the point
            p = sPointTravelled.pop();
            if(p.getRow() == pProcessing.getParentRow() && p.getColumn() == pProcessing.getParentColumn())
            {
                sExitToStart.push(p);
                cMaze[p.getRow()][p.getColumn()]=PATH_MAKER;
            }
        }
        return sExitToStart;
    }
    
    /**
    * Searches the maze recursively using breadth first approach with a Queue
    * @return a string that represents the contents of the maze
    * @throws IllegalStateException If is called before a maze has been solved
    */
    public String dumpMaze()
    {
        String sMaze = "";
        
        if(!bSolved)
        {
            throw new IllegalStateException("A maze has not been solved!");
        }
        
        for(int i=0; i< cMaze.length; i++)
        {
            for(int j=0; j<cMaze[i].length; j++)
            {
                sMaze += cMaze[i][j];
            }
            sMaze += "\n";
        }
        
        return sMaze;
    }
}
