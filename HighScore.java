import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import java.util.Date;

//# Caleb
public class HighScore
{
    //Create JFrame frame with the title "House Drawing"
    public JFrame frame = new JFrame("Sudoku: Highscores");
    public HighScore()
    {

        //Create a DrawPanel object that will contain the drawing of the house
        HighScorePanel drawPnl = new HighScorePanel();

        //Set the size of the JFrame
        drawPnl.setPreferredSize(new Dimension(600, 600));
        //Add the DrawPanel to the JFrame
        frame.add(drawPnl);
        //Pack frame
        frame.pack();
        //Exit
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Make the JFrame not resizable
        //frame.setResizable(false);
        //Make the JFrame visible
        frame.setVisible(true);
    }//end default constructor

    /**
     * Main method
     * @param String[] args - not used
     * @returen void
     */
    public static void main(String[] args)
    {
        //Call the House() default constructor to construct the GUI
        new HighScore();
    }//end main
}

class HighScorePanel extends JPanel
{

    private long timeOfThisPlayer;
    private Player thePlayer;
    public JPanel main = new JPanel();
    public JPanel topPanel = new JPanel();
    public JPanel midPanel = new JPanel();
    public JPanel botPanel = new JPanel();
//    public JPanel btnPanel = new JPanel();
    public JPanel congratsPanel = new JPanel();
    public JPanel endMsgPanel = new JPanel();
    public JPanel timePanel = new JPanel();
    // public JPanel fastestTimePanel = new JPanel();
    public JPanel tabPanel = new JPanel();
    // public JPanel avgTimePanel = new JPanel();
    public HighScoreTitlePanel titlePanel = new HighScoreTitlePanel();
    public JTextArea fastestTimeTextArea = new JTextArea("\t ssfgobgohsod\nsiugf dfoids");
    public JTextArea avgTimeTextArea = new JTextArea();
    public JScrollPane fastestTimePanel = new JScrollPane(fastestTimeTextArea); 
    public JScrollPane avgTimePanel = new JScrollPane(avgTimeTextArea);

    public JTabbedPane tabbedPane = new JTabbedPane();
    public JLabel congratsMsg = new JLabel("YOU DID IT!");
    public JLabel endMsg = new JLabel("Do you want to play again?");
    public JLabel timeMsg = new JLabel("YOUR TIME!");

    public JButton quitBtn = new JButton("QUIT");
    public JButton playBtn = new JButton("PLAY AGAIN");

    public HighScorePanel()
    {
        this.repaint();
    }//end defauklt constructor

    public HighScorePanel(long time, Player p)
    {
        this.timeOfThisPlayer = time;
        this.thePlayer = p;
        this.repaint();
    }//end defauklt constructor

    /**
     * Paints the timer
     * @param Graphics g
     * @return void
     * */
    @Override
    public void paintComponent(Graphics g)
    {
        ArrayList<Player> p = new ArrayList<Player>();
        p = Player.readPlayersFromFile();
        int numberOfPlayers = Player.readPlayersFromFile().size();
        Player[] players = new Player[numberOfPlayers];
        players = Player.readPlayersFromFile().toArray(players);
        
        //Calls the paintComponent method from the JPanel class
        super.paintComponent(g);
        main.add(topPanel);
        topPanel.add(congratsPanel);
        topPanel.add(timePanel);

       // timeMsgCheck(thePlayer);
        
        congratsPanel.add(congratsMsg);
        congratsPanel.setBackground(Color.white);
        timeMsg.setText("YOU TIME IS: " + TimerPanel.DATE_FORMAT.format(timeOfThisPlayer + 18000000));
        timePanel.add(timeMsg);
        timePanel.setBackground(Color.white);
        //set the box layout with y-axis alignment
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.white);
        titlePanel.setBackground(Color.white);
        
        main.add(midPanel);
        midPanel.add(titlePanel);
        midPanel.add(tabPanel);
        tabPanel.add(tabbedPane);
        tabPanel.setBackground(Color.white);
        tabbedPane.addTab("Fastest Time Highscores", fastestTimePanel);
        
        tabbedPane.addTab("Average Time Highscores", avgTimePanel);
        tabbedPane.setPreferredSize(new Dimension(600, 400));
        fastestTimeTextArea.setText("\tUSERNAME\t\t\tTIME" + displayFastestTimes(getHighScoreList(players, 0, numberOfPlayers-1, false)));
        fastestTimeTextArea.setEditable(false);
        avgTimeTextArea.setText("\tUSERNAME\t\t\tTIME" + displayAvgTimes(getHighScoreList(players, 0, numberOfPlayers-1, true)));
        avgTimeTextArea.setEditable(false);
        //set the box layout with y-axis alignment
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setBackground(Color.white);
        
//        main.setBorder(BorderFactory.createLineBorder(Color.black));
        main.add(botPanel);
        main.setBackground(Color.white);
        botPanel.add(endMsgPanel);
        botPanel.setBackground(Color.white);
//        botPanel.add(btnPanel);
        endMsgPanel.add(endMsg);
        endMsgPanel.setBackground(Color.white);
//        btnPanel.add(playBtn);
//        btnPanel.add(quitBtn);


        //set the box layout with y-axis alignment
        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.Y_AXIS));

        this.add(main);
        //set the box layout with y-axis alignment
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

    }//end paintComponent
    
    public String displayFastestTimes(Player[] list)
    {
        String fastestTimes = "";
        
        for(int i = 0; i < list.length; i++)
        {
            fastestTimes = fastestTimes + ("\n\t" + list[i].getUsername() + "\t\t\t" + TimerPanel.DATE_FORMAT.format(list[i].getFastestTime() + 18000000));
        }//end for 
        
        return fastestTimes;
    }//end displayFastestTimes
    
    public String displayAvgTimes(Player[] list)
    {
        String avgTimes = "";
        
        for(int i = 0; i < list.length; i++)
        {
            avgTimes = avgTimes+ ("\n\t" + list[i].getUsername() + "\t\t\t" + TimerPanel.DATE_FORMAT.format(list[i].getAverageTime() + 18000000));
        }//end for 
        
        return avgTimes;
    }//end displayAvgTimes

    public Player[] getHighScoreList(Player[] list, int start, int end, boolean isGettingAvgTime)
    {
        if(start < end)
        {
            int middle = (start + end + 1) / 2;

            getHighScoreList(list, start, middle - 1, isGettingAvgTime);
            getHighScoreList(list, middle, end, isGettingAvgTime);

            merge(list, start, middle, end + 1, isGettingAvgTime);
        }//end if 
        
        return list;
    }//end getHighScoreList

    public static void merge(Player[] list, int start, int middle, int end, boolean isMergingAvgTime)
    {
        Player[] merged = new Player[end - start];

        int index = 0, left = start, right = middle;

        while(left < middle && right < end)
        {
            if(list[right].compareTo(list[left], isMergingAvgTime) == -1)
                merged[index++] = list[right++];
            else
                merged[index++] = list[left++];
        }//end while

        while (left < middle)
        {
            merged[index++] = list[left++];
        }//end while

        while(right < end)
        {
            merged[index++] = list[right++];
        }//end while

        for(int i = 0; i < index; i++)
        {
            list[start + i] = merged[i];
        }//end for
    }//end merge

    public void timeMsgCheck(Player p)
    {
        if(timeOfThisPlayer > p.getAverageTime() )
        {
            congratsMsg.setText("YOU DID IT! LOOKS LIKE A NEW PERSONAL BEST!");
        }
        else if(timeOfThisPlayer > (p.getAverageTime()-2000) && timeOfThisPlayer < (p.getAverageTime()))
        {
            congratsMsg.setText("YOU DID IT! ALMOST BEAT YOUR PERSONAL BEST");
        }
        else
        {
            congratsMsg.setText("YOU DID IT! GOOD TRY");
        }//end if
    }//end timeMsgCheck
   
}//end class

class HighScoreTitlePanel extends JPanel
{

    public HighScoreTitlePanel()
    {
        this.repaint();
    }//end default constructor

    /**
     * Paints the timer
     * @param Graphics g
     * @return void
     * */
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Font font = new Font("Trattatello", Font.PLAIN, 50);
        g2.setFont(font);
        g2.setPaint(Color.BLUE);
        g2.drawString("HIGHSCORES", 140,50);
        this.setPreferredSize(new Dimension(50, 60)); 
    }//end paintComponent 
}//end class

