
/**
 * DepthFirst  - A class used to used to search a maze by depth.
 * 
 * <pre>
 * 
 * Assignment: #4
 * Course: ADEV-3001
 * Date Created: April 15, 2017
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
    private MazePoint[][] cMaze;
    private LinkedList<String> inventoryList;
    private HashMap<Item, ItemValue> availableItemsHashMap;
    private Stack<MazePoint> sPath = new Stack<MazePoint>();
    
    /**
     * Constructor for objects of class DepthFirst
     */
    public DepthFirst(MazePoint[][] cMaze, LinkedList<String> inventoryList, HashMap<Item, ItemValue> availableItemsHashMap)
    {
        // initialise instance variables
        this.cMaze = cMaze;
        this.inventoryList = inventoryList;
        this.availableItemsHashMap = availableItemsHashMap;
    }

    /**
    * Searches the maze recursively using depth first approach with a Stack
    * @param row The numerical row of the start point
    * @param column The numerical column of the start point
    */
    public Stack<MazePoint> DepthFirstSearch(int row, int column)
    {
        //cMaze[row][column].wasVisited()
        if(cMaze[row][column].isExit())
        {
            sPath.push(cMaze[row][column]);
            //print out the path
            PrintPath(sPath, true);
            return sPath;
        }
        else
        {
            //when not visit
            if(!cMaze[row][column].wasVisited())
            {
                if(cMaze[row][column].hasItem())
                {
                    String itemName = cMaze[row][column].pickUpItem();
                    Item item = new Item(itemName);
                    //check if the item is in the hashmap
                    if(availableItemsHashMap.get(item) != null)
                    {
                        inventoryList.insert(itemName);
                    }
                    else
                    {
                        cMaze[row][column].dropItem();
                    }
                }
                //mark as vist
                cMaze[row][column].markVisited();
                //push into stack
                sPath.push(cMaze[row][column]);
            }
            //determine where to traverse next (S, E, W, N)
            //recursively
            if(cMaze[row+1][column].canBeNavigated() || cMaze[row+1][column].isExit())// or (cMaze[row+1][column] != VISITED_MARKER && cMaze[row+1][column] != 'W')
            {
                return DepthFirstSearch(row+1, column);
            }
            else if(cMaze[row][column+1].canBeNavigated() || cMaze[row][column+1].isExit())
            {
                return DepthFirstSearch(row, column+1);
            }
            else if(cMaze[row][column-1].canBeNavigated() || cMaze[row][column-1].isExit())
            {
                return DepthFirstSearch(row, column-1);
            }
            else if(cMaze[row-1][column].canBeNavigated() || cMaze[row-1][column].isExit())
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
                     PrintPath(sPath, false);
                     return sPath;
                 }
                 MazePoint mp = sPath.top();
                 int mpRow = mp.getRow();
                 int mpColumn = mp.getColumn();
                 return DepthFirstSearch(mpRow, mpColumn);
            }
        }
    }
    
    /**
    * Print the path to the exit of the maze, and the maze
    * @param stack the collection of the exit path
    * @param hasExit true when there is an exit, false when no exit
    */
    private void PrintPath(Stack<MazePoint> stack, boolean hasExit)
    {
        if(hasExit)
        {
            //reverse the sPath
            Stack<MazePoint> sExitPath = new Stack<MazePoint>();
            MazePoint exitPoint = stack.top();
            int size = stack.getSize();
            while(!stack.isEmpty())
            {
                MazePoint p = stack.top();
                sExitPath.push(p);
                stack.pop();
                
                int pRow = p.getRow();
                int pColumn = p.getColumn();
                cMaze[pRow][pColumn].markPath();
            }
            MazePoint startPoint = sExitPath.top();
            
            System.out.println("Path to follow from Start " + startPoint.toString() + " to Exit " + exitPoint.toString() + " - " + size + " steps:");
            //print the sExitPath
            while(!sExitPath.isEmpty())
            {
                MazePoint p = sExitPath.top();
                sExitPath.pop();
                System.out.println(p.toString());
            }
            System.out.print("\n");
        }
        else
        {
            System.out.println("There is no exit out of the maze.");
        }
        //PrintContents();
        for(int i=0; i< cMaze.length; i++)
        {
            for(int j=0; j<cMaze[i].length; j++)
            {
                char locationData = cMaze[i][j].getLocationData();
                //when it is not on the path and visited
                if(locationData != ('.') && cMaze[i][j].wasVisited())
                {
                    System.out.print('V');
                }
                else
                {
                    System.out.print(locationData);
                }
            }
            System.out.print("\n");
        }
        
        PrintItems();
    }
    
    /**
    * Left pads a String with spaces
    * @param str The String to be padded
    * @param length The desired String length
    */
    private String LeftPad(String str, int length)
    {
        //pad and return String
        return String.format("%1$" + length + "s", str);
    }
    
    /**
    * Print the item
    */
    private void PrintItems()
    {
        if(inventoryList.isEmpty())
        {
            System.out.print("No items were found exploring the maze.");
            return;
        }
        
        int length = 50;
        int total = 0;
        String sItem;
        ItemValue val;
        
        for(int i =1; i<= inventoryList.getSize(); i++)
        {
            sItem = inventoryList.get(i);
            val = availableItemsHashMap.get(new Item(sItem));
            total += val.getGoldPieces();
            System.out.println(sItem + LeftPad(val.toString() + "GP", length - sItem.length()));
        }
        
        System.out.println(LeftPad("--------", length));
        
        System.out.print("Total" + LeftPad(String.valueOf(total) + "GP", length - 5) + "\n");
    }
}
