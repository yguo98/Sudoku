/**
 * The Player Class
 * 
 * @author Caleb D'Souza
 * @version May, 2016
 */
import java.io.*;
import java.util.ArrayList;
public class Player implements Serializable
{
    // instance variables
    private boolean isCompletingGame;
    private long averageTime;
    private long fastestTime;
    private ArrayList<Long> listOfTimes = new ArrayList<Long>();
    private int gamesCompleted; 
    private int rank;
    private String username; 
    private String title;

    //Class Variables
    public static final String[] TITLES = {"Novice", "Amateur", "Semi-Pro", "Professional", "Master", "Expert"}; 
    public static ArrayList<String> usernamesTaken = new ArrayList<String>();

    /***************************************************************************
     * Constructors
     * ************************************************************************/
    /**
     * Default Constructor 
     * 
     * @param none
     */
    public Player()
    {

        this(false, 0, 0, 0, 0, "Player", "novice");
    }//end default constructor 

    public Player(boolean isGaming, long fastestTime, long avgTime, int gamesComp, int rank, String name, String title)
    {
        //Check if the given argument for the fastest time is less than 0 
        if(fastestTime < 0)
        {
            fastestTime = 0; 
        }//end if 

        //Check if the given argument for the avrage time is less than 0
        if(avgTime < 0)
        {
            avgTime = 0;
        }//end if 

        //Check if the given augment for the name is not already taken 
        if(usernamesTaken.indexOf(name) > -1)
        {
            name = ("Player" + usernamesTaken.size()); 
        }//end if 

        //Check if the given argument for the number of games completed is less than 0 
        if(gamesComp < 0)
        {
            gamesCompleted = 0;
        }//end if 

        //Check if the given argument for the rank is less than 0 
        if(rank < 0)
        {
            rank  = 0; 
        }//end if 

        this.isCompletingGame = false; 
        this.averageTime = avgTime; 
        this.fastestTime = fastestTime; 
        this.gamesCompleted = gamesComp; 
        this.rank = rank; 
        this.title = title;
        this.username = name;
        usernamesTaken.add(name);
    }//end (boolean isGaming, double fastestTime, double avgTime, int ganesCompleted, int rank, String title) constructor 

    public Player(Player p)
    {
        this(p.isCompletingGame, p.averageTime, p.fastestTime, p.gamesCompleted, p.rank, p.title, p.username);
    }//end (Player p) constructor 

    /***************************************************************************
     * Get Methods
     * ************************************************************************/
    /**
     * Returns the a list of all the times of this Player 
     * 
     * @param 
     * @return ArrayList<long> listOfTimes - the list of times 
     */
    public ArrayList<Long> getListOfTimes()
    {
        return this.listOfTimes;
    }//end getListOfTimes

    /**
     * Returns true if this Player is in the middle of a sudoku game, else returns false
     * 
     * @param none
     * @return boolean isCompletingGame - the state of weather the player is currently completing a game 
     */
    public boolean getIsCompletingGame()
    {
        return this.isCompletingGame; 
    }//end getIsCompletingGame

    /**
     * Returns the average time is take this Player to complete a game
     * 
     * @param none
     * @return long averageTime - the average time to complete a game 
     */
    public long getAverageTime()
    {
        return this.averageTime;
    }//end getAvrageTime 

    /**
     * Returns the fastest time this Player took to complete a game 
     * 
     * @param none
     * @return long fastestTime - the fastest time to complete a game 
     */
    public Long getFastestTime()
    {
        return this.fastestTime;
    }//end getFastestTime

    /**
     * Returns the number of games this Player has completed 
     * 
     * @param none
     * @return int gamesCompleted - the number of games completed
     */
    public int getGamesCompleted()
    {
        return this.gamesCompleted;
    }//end getGamesCompleted 

    /**
     * Returns the rank of this Player
     * 
     * @param none
     * #return int rank - the rank
     */
    public int getRank()
    {
        return this.rank;
    }//end getRank

    /**
     * Return this Player's username 
     * 
     * @param none
     * #return String username - the username of this player 
     */
    public String getUsername()
    {
        return this.username; 
    }//end getUsername 

    /**
     * Return this Player's gaming sudoku title 
     * 
     * @param none
     * #return String title - the sudoku gaming title
     */
    public String getTitle()
    {
        return this.title;
    }//end getTitle

    /***************************************************************************
     * Set Methods
     * ************************************************************************/
    /**
     * Sets the new state of this PLayer weather they are completing a game or not
     * 
     * @param boolean state - this Player's new state of currently playing a game or not 
     * @return void 
     */
    public void setIsCompletingGame(boolean state)
    {
        this.isCompletingGame= state; 
    }//end setIsCompletingGame

    /**
     * Check if the new average time is less than zero before setting the new average time 
     * 
     * @param long time - this Player's new avrage time 
     * @return void
     */
    public void setAverageTime(long time)
    {
        //Check if the new average time is less than 0 before setting the new average time 
        if(time < 0)
        {
            //Outouts a warning message
            System.out.println("WARNING:You cannot assign a negative time");
        }
        else 
        {
            this.averageTime = time; 
        }//end if
    }//end setAverageTime

    /**
     * Check if the new fastest time is less than zero before setting the new fastest time 
     * 
     * @param long time - this Player's new fastest time 
     * @return void
     */
    public void setFastestTime(long time)
    {
        //Check if the new fastest time is less than 0 before setting the new fastest time 
        if(time < 0)
        {
            //Outouts a warning message
            System.out.println("WARNING:You cannot assign a negative time");
        }
        else 
        {
            this.fastestTime = time; 
        }//end if
    }//end setFastestTime

    /**
     * Check if the new number of game completed is less than zero before setting the new number of games completed
     * 
     * @param int num - this Player's new number of games completed 
     * @return void
     */
    public void setGamesCompleted(int num)
    {
        //Check if the new average time is less than 0 before setting the new average time 
        if(num < 0)
        {
            //Outouts a warning message
            System.out.println("WARNING:You cannot assign a negative number of games completed");
        }
        else 
        {
            this.gamesCompleted = num; 
        }//end if
    }//end setAverageTime

    /**
     * Check if the new ArrayList of times is empty before setting the new ArrayList of times
     * 
     * @param ArrayList<long> listOfTimes - this Player's new list of times 
     * @return void 
     */
    public void setListOfTimes(ArrayList<Long> list)
    {
        //Check if the new list times is empty before setting the new list of times
        if(list.isEmpty())
        {
            //Outputs a warning message 
            System.out.println("WARNING: You cannot assign a empty list of times");
        }
        else 
        {
            if(this.listOfTimes.isEmpty())
            {
                this.listOfTimes.addAll(list);
            }
            else 
            {
                this.listOfTimes.clear();
                this.listOfTimes.addAll(0, list);
            }//end if 
        }//end if
    }//end getListOfTimes

    /**
     * Check if the new rank is less than zero before setting the new rank 
     * 
     * @param int num - this Player's new rank
     * @return void
     */
    public void setRank(int num)
    {
        //Check if the new rank is less than 0 before setting the new rank 
        if(num < 0)
        {
            //Outouts a warning message
            System.out.println("WARNING:You cannot assign a negative time");
        }
        else 
        {
            this.rank = num;
        }//end if
    }//end setAverageTime

    /**
     * Check if the new username is not already taken by another Player setting the new username 
     * 
     * @param String name - this Player's new title 
     * @return void 
     */
    public void setUsername(String name)
    {
        int index = -1;
        //Check if the new username is not already taken before setting the new username
        if(usernamesTaken.indexOf(name) == -1)
        {
            index = usernamesTaken.indexOf(this.username);
            usernamesTaken.set(index, name);
            this.username = name;
        }
        else
        {
            //Outouts a warning message
            System.out.println("WARNING: This username is already taken");
        }//end if 
    }//end setTitle

    /**
     * Check if the new title is a valid title before setting the new title 
     * 
     * @param String newTitle - this Player's new title 
     * @return void 
     */
    public void setTitle(String newTitle)
    {
        boolean check = false;
        //Check if the new title is a vaild title before setting the new title 
        for(int i = 0; i < TITLES.length; i++)
        {
            if(newTitle.equals(TITLES[i]))
            {
                check = true; 
                this.title = TITLES[i];
            }//end if 
        }//end for 

        if(!check)
        {
            //Outputs a warning message
            System.out.println("WARNING: You cannot assign an invalid title");
        }//end if 
    }//end setTitle

    /***************************************************************************
     * Instance Methods
     * ************************************************************************/
    /**
     * Adds a time to the list of times for this player
     * 
     * @param long time - the time to be added
     * @return void 
     */
    public void addTime(long time)
    {
        listOfTimes.add(time);
    }//end addTime 

    /**
     * Calculates the average time of this player
     * 
     * @param none
     * @return long
     */
    public long calulateAverageTime()
    {
        long sum = 0;
        long avgTime = 0;

        //Get the sum of all the times 
        for(long time : this.listOfTimes)
        {
            sum += time;
        }//end for 

        //calculate the average time 
        if(this.gamesCompleted>0)
          avgTime = sum / this.gamesCompleted;
        else
          avgTime = sum;

        return avgTime;
    }//end calulateAverageTime

    /**
     * Finds the fastest time for this player
     * 
     * @param none
     * @return long 
     */ 
    public long findFastestTime()
    {
        for(int top = 1; top < listOfTimes.size(); top++)
        { 
            long item = listOfTimes.get(top); 
            int i = top;

            while(i > 0 && item < listOfTimes.get(i - 1))
            {
                listOfTimes.set(i, listOfTimes.get(i- 1));
                i--;
            }//end while 

            listOfTimes.set(i, item);
        }//end for 

        return listOfTimes.get(0);
    }//end findFastestTime

    /**
     * Overrides comapreTo() 
     * Returns a negative integer if this Player is not better than the otherPlayer, a positive integer if
     * this Player is better than the otherPlayer, and a 0 if this player and the otherPlayer are equal.
     * 
     * @param Player otherPlayer - the other player to be compareTo
     * @return int - the resultant value of the comparison 
     */

    public int compareTo(Player otherPlayer, boolean isComparingAvgTime)
    {
        if(isComparingAvgTime)
        {
            //Compare the average times of this player to the other player
            if(this.averageTime > otherPlayer.getAverageTime())
            {
                return 1; 
            }
            else if (this.averageTime == otherPlayer.getAverageTime())
            {
                //Compare the number of games this player to the other player
                if(this.gamesCompleted > otherPlayer.getGamesCompleted())
                {
                    return 1;
                }
                else if (this.gamesCompleted == otherPlayer.getGamesCompleted())
                {
                    return 0;
                }
                else 
                {
                    return -1;
                }//end if

            }
            else 
            {
                return -1;
            }//end if
        }
        else 
        {
            //Compare the fastest times of this player to the other player 
            if(this.fastestTime > otherPlayer.getFastestTime())
            {
                return 1; 
            }
            else if(this.fastestTime == otherPlayer.getFastestTime())
            {
                //Compare the average times of this player to the other player
                if(this.averageTime > otherPlayer.getAverageTime())
                {
                    return 1; 
                }
                else if (this.averageTime == otherPlayer.getAverageTime())
                {
                    //Compare the number of games this player to the other player
                    if(this.gamesCompleted > otherPlayer.getGamesCompleted())
                    {
                        return 1;
                    }
                    else if (this.gamesCompleted == otherPlayer.getGamesCompleted())
                    {
                        return 0;
                    }
                    else 
                    {
                        return -1;
                    }//end if

                }
                else 
                {
                    return -1;
                }//end if

            }
            else 
            {
                return -1; 
            }//end if
        }//end if
    }//end comapareTo

    /**
     * Overrides toString()
     * Returns a String represenation of a Player Object
     * 
     * @param none
     * @returns String 
     */
    @Override
    public String toString()
    {
        //Returns String representation of this Player object
        return ("Player: " + username + " is a " + title + " ranked " + rank
            + ", has completed " + gamesCompleted 
            + " with a fastest time of " + fastestTime + ", and an average time of " + averageTime
            + " currently completing a game: " + isCompletingGame);
    }//end toString

    /***************************************************************************
     * Class Methods
     * ************************************************************************/
    /**
     * Write the toString data of a Player object to a  file named playersData.txt
     * 
     * @param Player p - the Player of which their toString is to be writen to a file 
     * @return void 
     */
    public static void writeToStringToFile(Player p)
    {
        try 
        { 
            //Create file 
            PrintWriter fileOutput = new PrintWriter(new FileWriter("playersData.txt", true), true); 

            //Write data from given ArrayList to the file 
            fileOutput.println(p);

            //Close file
            fileOutput.close();
        } 
        catch (IOException ioException) 
        { 
            //Output error message 
            System.out.println("Error: The file cannot be created"); 
        }//end try 
    }//end writeToStringToFile(Player p)

    /**
     * Clear the contents of a file
     * 
     */
    public static void clearFile(File fileName)
    {
        try 
        {
            FileWriter fw = new FileWriter(fileName);
            fw.write("");

            fw.close();
        }
        catch(IOException ioe)
        {
            //Output error message 
            System.out.println(ioe); 
        }
    }

    /**
     * Write the Player object to a  file named players.txt
     * 
     * @param Player p - the Player object to be writen to a file
     * @return void 
     */
    public static void writePlayerToFile(Player p)
    {
        try{
            //Create an "players.txt" file with an ObjectOutputStream
            FileOutputStream fileOut = new FileOutputStream("players.txt", true);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);   

            //Write the Player objetct to the file 
            objectOut.writeObject(p);
            //Close file
            objectOut.close();
        }catch (IOException ioException) 
        { 
            //Output error message 
            System.out.println("Error: The file cannot be created"); 
        }//end try
    }//end writePlayerToFile

    /**
     * Write the Player object to a  file named players.txt
     * 
     * @param Player p - the Player object to be writen to a file
     * @return void 
     */
    public static void writePlayerToFile(ArrayList<Player> players)
    {
        clearFile(new File("players.txt"));

        for(Player p : players)
        {
            writePlayerToFile(p);
        }//end for
    }//end writePlayerToFile

    /**
     * Read the player objects from a file named "players.txt"
     * 
     * @param none
     * @return ArrayList<Player> - an ArrayList of all the players ihe file named "players.txt"
     */
    public static ArrayList<Player> readPlayersFromFile() 
    {
        //Inisialize Arraylist 
        ArrayList<Player> players = new ArrayList<Player>();
        //Inisialize FileInputStream
        FileInputStream fileIn = null;

        try 
        {
            //Establish connection to file 
            fileIn = new FileInputStream("players.txt");
            while (true) 
            {
                //Create an ObjectOutputStream 
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                //Read a Player object from the file and add it to the ArrayList
                players.add((Player)objectIn.readObject());
            }//end while

        }
        catch (Exception e)
        {
            //System.out.println(e);
        }
        finally 
        {
            usernamesTaken.clear();

            for(int i = 0; i < players.size(); i++)
            {
                usernamesTaken.add(players.get(i).getUsername());
            }//end for

            //Try to close the file and return the ArrayList
            try
            {
                //Check if the end of the file is reached
                if (fileIn != null)
                {   
                    //Close the file 
                    fileIn.close();
                    //Return the ArrayList of players
                    return players;
                }
            }
            catch (Exception e)
            {
                //System.out.println(e)
            }//end try 

            //Return null if there is an excetion 
            return null;
        }//end try 
    }//end readPlayersFromFile
}