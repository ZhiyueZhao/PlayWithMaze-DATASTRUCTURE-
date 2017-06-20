import java.io.*;
import java.util.*;
/**
 * Main  - A class used to start the application.
 * 
 * <pre>
 * 
 * Assignment: #3
 * Course: ADEV-3001
 * Date Created: March 20, 2017
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
        String path = P4Utils.choose();
        if(path == null)
        {
            System.out.println("No file selected.");
        }
        else
        {
            try
            {
                //open file
                FileReader fr = new FileReader(path);
                BufferedReader br = new BufferedReader(fr);
                
                String s="";
                char [] lineChar;
                int i=0,j=0 ,mRow=0, mColumn=0, sRow=0, sColumn=0;
                //initialize
                char[][] cMaze = new char[9][9];
                String [] stringArr;
                while((s = br.readLine()) != null) 
                {
                    //first line contain the size of the maze and the second contain the start point
                    switch (i)
                    {
                        case 0:
                            stringArr = s.split(" ");
                            mRow = Integer.parseInt(stringArr[0]);
                            mColumn = Integer.parseInt(stringArr[1]);
                            //re-initialize
                            cMaze = new char[mRow][mColumn];
                            break;
                        case 1:
                            stringArr = s.split(" ");
                            sRow = Integer.parseInt(stringArr[0]);
                            sColumn = Integer.parseInt(stringArr[1]);
                            break;
                        default:
                            lineChar=s.toCharArray();
                        
                            for(j=0;j<lineChar.length;j++)
                            {
                                cMaze[i-2][j]=lineChar[j];
                            }
                            break;
                    }
                    i++;
                }
                
                BreadthFirst bf = new BreadthFirst(cMaze);
                if(bf.BreadthFirstSearch(sRow, sColumn))
                {
                    System.out.println(bf.exitFound());
                }
                else
                {
                    System.out.println(bf.noExit());
                }
                
                System.out.println(bf.dumpMaze());
            }
            catch(Exception e)
            {
                 e.printStackTrace();
            }
        }
    }
}
