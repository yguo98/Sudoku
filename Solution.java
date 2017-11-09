/**
 * Solution Class
 * Creates a unique solution for a game of sudoku.
 * @author Stella Guo
 * @date June 13, 2016
 * */

// import required packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Collections;

//# Stella

// for testing the class
class TestSolution
{
  public static void main(String[] args)
  {
    new Solution();
  }
}

// Solution class
//#define
class Solution
{
  /**
   * Instance Variables
   * */
  
  // the solution array
  int[][] solution = new int[9][9];
  
  // the random number selected from a list of possible valid values
  int randNum;
  
  // the coordinates
  int  cSol=0,rSol=0;
  
  // is the board filled or not
  boolean filledBoard = false;
  
  // is the number valid
  boolean invalid;
  
  // stores objects that help keep track of possible valid values for each cell
  SolCell[][] cellArr = new SolCell[9][9];
  
  // a cell for temporary storage and cell creation
  SolCell cell;
  
  /**
   * default constructor
   * constructs the solution
   * @param none
   * */
  public Solution()
  {    
    do
    {
      // creates a new cell with the given coorinates and adds it to the array
      cell = new SolCell(rSol, cSol, solution);
      cellArr[rSol][cSol]=cell;
      
      //testing
      do
      {
        invalid = false;
        
        // if there are still numbers in the list of valid values, then takes the first in that list
        if(cellArr[rSol][cSol].valid.size()>0)
        {
          randNum = cellArr[rSol][cSol].valid.get(0);
        }
        // otherwise, sets invalid to true and goes back a box to try something else
        else
        {
          invalid = true;
          
          // goes back a box
          cSol--;
          if(cSol==-1)
          {
            cSol=8;
            rSol--;
          }
          // sets the backtracked box to zero
          solution[rSol][cSol]=0;
        }
      } while (invalid);
      
      // if a number is valid, the assigns it to the solution array and removes it from the list
      solution[rSol][cSol] = randNum;
      cellArr[rSol][cSol].valid.remove(0);
      
      // moves up a box
      cSol++;
      
      // makes sure there aren't any index out of bounds exceptions
      if(cSol==9)
      {
        rSol++;
        cSol=0;
        
      }
      
      // checks if the board is filled
      if(rSol==9)
        filledBoard = true;
      
    }
    while(!filledBoard);    
    
//    System.out.println(toString(solution));
  }//end default constructor
  
  /**
   * toString method 
   * for printing/testing for integer arrays
   * @param int[][] s - the array to print
   * @return String
   * */
  public String toString(int[][] s)
  {
    String printStr = "\n\n";
    
    for(int i = 0; i < s.length; i++)
    {
      for(int j = 0; j < s[i].length; j++)
      {
        printStr+=s[i][j] + " ";
        if((j+1)%3==0)
          printStr+="   ";
      }
      printStr+="\n";
      if((i+1)%3==0)
        printStr+="\n";
    }
    
    printStr += "\n\n";
    return printStr;
  }//end toString(int[][])
  
  /**
   * toString method 
   * for printing/testing for boolean arrays
   * @param int[][] s - the array to print
   * @return String
   * */
  public String toString(boolean[][] s)
  {
    String printStr = "\n\n";
    
    for(int i = 0; i < s.length; i++)
    {
      for(int j = 0; j < s[i].length; j++)
      {
        printStr+=s[i][j] + " ";
        if((j+1)%3==0)
          printStr+="   ";
      }
      printStr+="\n";
      if((i+1)%3==0)
        printStr+="\n";
    }
    
    printStr += "\n\n";
    return printStr;
  }//end toString(boolean[][])
  
  
}//end Solution

// class of cells for the solution to keep track of valid values
class SolCell
{
  // arary lists to keep track of the row, column, and box the cell is in
  ArrayList<Integer> usedRow = new ArrayList<Integer>();
  ArrayList<Integer> usedCol = new ArrayList<Integer>();
  ArrayList<Integer> usedBox = new ArrayList<Integer>();
  
  // list of valid values for this cell
  ArrayList<Integer> valid = new ArrayList<Integer>();
  
  /**
   * default constructor
   * creates a cell
   * @param none
   * */
  public SolCell(int rSol, int ySol, int[][] solution)
  {
    this.setup(rSol, ySol, solution);
  }//end default constructor
  
  
  /**
   * setup method
   * creates the arraylists
   * @param int rSol, ySol - the coordinates
   * @param int[][] solution = the array to look in
   * @return void
   * */
  //#instance
  public void setup(int rSol, int ySol, int[][] solution)
  {
    // clears lists
    usedRow.clear();
    usedCol.clear();
    usedBox.clear();
    
    // adds numbers to valid list
    valid.add(1);
    valid.add(2);
    valid.add(3);
    valid.add(4);
    valid.add(5);
    valid.add(6);
    valid.add(7);
    valid.add(8);
    valid.add(9);
    
    // goes through the row and adds already used numbers  to the appropriate list and removes those numbbers from the valid list
    for(int d = 0; d < rSol; d++)
    {
      if(solution[d][ySol]>0)
      {
        usedCol.add(solution[d][ySol]);
        if(valid.contains(solution[d][ySol]))
          valid.remove(valid.indexOf(solution[d][ySol]));
      }//end if
    }//end for
    
    // goes through the column and adds already used numbers to the appropriate list and removes those numbbers from the valid list
    for(int e = 0; e < ySol; e++)
    {
      if(solution[rSol][e]>0)
      {
        usedRow.add(solution[rSol][e]);
        if(valid.contains(solution[rSol][e]))
          valid.remove(valid.indexOf(solution[rSol][e]));
      }//end if
    }//end for
    
    // goes through the box and adds already used numbers to the appropriate list and removes those numbbers from the valid list
    for(int a = rSol-rSol%3; a < rSol-rSol%3+3; a++)
    {
      for(int b = ySol-ySol%3; b < ySol-ySol%3+3; b++)
      {
        if(solution[a][b]>0)
        {
          usedBox.add(solution[a][b]);
          if(valid.contains(solution[a][b]))
            valid.remove(valid.indexOf(solution[a][b]));
        }//end if
      }//end for
    }//end for
    
    //shuffles valid list
    Collections.shuffle(valid);
  }//end setup
}//end SolCell


// a class for the cheat mode, to show the solution
//#cheat, //#define
class ShowSolution extends JPanel
{
  /**
   * variables needed to display the solution
   * */
  int gameMode;
  int[][] gameSol;
  Color[] colourArr;  
  String[] alphaArr;
  
  /**
   * int, int[][], Color[], String[]
   * @param int mode - the mode
   * @param int[][] sol - the solution
   * @param color[] newcolourArr - array of colours needed
   * @param String[] newAlphaArr - array of letters needed 
   * */
  public ShowSolution(int mode, int[][] sol, Color[] newcolourArr, String[] newAlphaArr)
  {
    // sets variables
    this.gameMode = mode;
    this.gameSol = sol;
    this.colourArr = newcolourArr;
    this.alphaArr = newAlphaArr;
    repaint();
  }//end (int, int[][], Color[], String[]) constructor
  
  /**
   * paintComponent
   * paints the solution
   * @param Graphics g - the graphics object
   * @return void
   * */
  //#instance
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    // calls the appropriate method to display the solution
    if(gameMode == 1)
    {
      this.showIntSol(g);
    }
    else if(gameMode == 2)
    {
      this.showAlphaSol(g);
    }
    else
    {
      this.showColSol(g);
    }
  }//end paintComponent
  
  /**
   * showIntSol method
   * displays solution if classic mode
   * @param Graphics g - the graphics object
   * @return void
   * */
  //#instance
  public void showIntSol(Graphics g)
  {
    int x = 30;
    int y = 30;
    
    g.setFont(new Font("Garamond", Font.PLAIN, 15));
    
    for(int i = 0; i < gameSol.length; i++)
    {
      for(int j = 0; j < gameSol[i].length; j++)
      {
        if(i%3==0&&i>0&&j==0)
          y+=15;
        if(j%3==0&&j>0)
          x+=15;
        g.drawString(""+gameSol[i][j], x,y);
        x+=20;
        if(j==8)
        {
          y+=20;
          x=30;
        }
      }
    }
  }//end showIntSol
  
  /**
   * showAlphaSol method
   * displays solution if alphabet mode
   * @param Graphics g - the graphics object
   * @return void
   * */
  //#instance
  public void showAlphaSol(Graphics g)
  {
    int x = 30;
    int y = 30;
    
    g.setFont(new Font("Garamond", Font.PLAIN, 15));
    
    for(int i = 0; i < gameSol.length; i++)
    {
      for(int j = 0; j < gameSol[i].length; j++)
      {
        if(i%3==0&&i>0&&j==0)
          y+=15;
        if(j%3==0&&j>0)
          x+=15;
        
        g.drawString(""+alphaArr[gameSol[i][j]], x,y);
        
        x+=20;
        if(j==8)
        {
          y+=15;
          x=30;
        }
      }
    }
  }//end showAlphaSol
  
  /**
   * showColSol method
   * displays solution if colours mode
   * @param Graphics g - the graphics object
   * @return void
   * */
  //#instance
  public void showColSol(Graphics g)
  {
    int x = 25;
    int y = 15;
    
    g.setFont(new Font("Garamond", Font.PLAIN, 15));
    
    for(int i = 0; i < gameSol.length; i++)
    {
      for(int j = 0; j < gameSol[i].length; j++)
      {
        if(i%3==0&&i>0&&j==0)
          y+=20;
        if(j%3==0&&j>0)
          x+=15;
        
        g.setColor(colourArr[gameSol[i][j]]);
        g.fillRect(x,y,15,15);
        
        x+=20;
        if(j==8)
        {
          y+=20;
          x=25;
        }
      }
    }
  }//end showColSol
  
}//end ShowSolution
















