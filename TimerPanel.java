//import required packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer; 
import java.util.Date; 
import java.text.SimpleDateFormat; 
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

//# Caleb
class TestTimerPanel
{
    /** 
     * Default constructor
     * @param none 
     * */ 
    public TestTimerPanel()
    {
        //Create JFrame frame with the title "House Drawing"
        JFrame frame = new JFrame("Test Timer");
        //Create a DrawPanel object that will contain the drawing of the house
        TimerPanel drawPnl = new TimerPanel(System.currentTimeMillis(), 0, 0, 16668, false);
         boolean s = true;
        //Set the size of the JFrame
        drawPnl.setPreferredSize(new Dimension(200, 30));
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
        new TestTimerPanel();
    }//end main
}//end TestTimerPanel

/** 
 * Constructs the panel where the Timer is drawn
 * */ 
public class TimerPanel extends JPanel implements ActionListener 
{
    //Instance veriable
    private boolean isStopped;
    private long startTime; 
    private long endTime; 
    private long timeElapsed; 
    private long pausedTime; 
    //Global variables
    //Initialize timer 
    public Timer timer = new Timer(10, this);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss:SSS"); 

    /***************************************************************************
     * Constructors
     * ************************************************************************/
    /**
     * Default constructor, which repaints the panel 
     * @param none 
     * */ 
    public TimerPanel()
    { 
        this(System.currentTimeMillis(), 0, 0, 0, false);

        //Repaint panel
        repaint(); 
    } //end DrawPanel

    public TimerPanel(long start, long end, long elapsed, long paused, boolean state)
    { 
        this.startTime = start; 
        this.endTime = end; 
        this.timeElapsed = elapsed;
        this.pausedTime = paused;
        this.isStopped = state;

        //Repaint panel
        repaint(); 
    } //end DrawPanel

    /***************************************************************************
     * Get Methods
     * ************************************************************************/

    /**
     * Returns the true if the timer is stopped
     * @param none
     * @return boolean isStopped - the state of the timer
     */
    public boolean getIsStopped()
    {
        return this.isStopped;
    }//end getIsStopped

    /**
     * Returns the start time of the timer
     * @param none
     * @reutrn long startTime - the system time this timer started 
     */
    public long getStartTime()
    {
        return this.startTime;
    }//end getStartTime

    /**
     * Returns the end time of the timer 
     * @param none
     * @return long endTime - the system time this timer ended
     */
    public long getEndTime()
    {
        return this.endTime; 
    }//end getEndTime

    /**
     * Returns the elapsed time of the timer 
     * @param none
     * @reutrn long timeElapsed - the system time this timer elapsed
     */
    public long getTimeElapsed()
    {
        return this.timeElapsed - 18000000L;
    }//end getTimeElapsed
    
    /**
     * Return the paused time of the timer from when it was last used
     * @param none
     * @return long pausedTime - the time from last use
     */
    public long getPausedTime()
    {
        return this.pausedTime;
    }//end getPausedTime

    /***************************************************************************
     * Set Methods
     * ************************************************************************/
    /**
     * Set the new start time to this timer 
     * 
     * @param long start - this Timer's new start time 
     * @return void
     */
    public void setStartTime(long start)
    {
        this.startTime = start;
    }//end setStartTime

    /**
     * Set the new state of this timer is stopped
     * 
     * @param boolean state - this timer's new state
     * @return void
     */
    public void setIsStopped(boolean state)
    {
        this.isStopped = state;
    }//end setIsStopped

    /**
     * Check if the new end time is less than zero before setting the new end time for this timer 
     * 
     * @param long end - this Timer's new end time 
     * @return void 
     */
    public void setEndTime(long end)
    {
        //Check if the new end time is less than 0 before setting the end time
        if(end < 0)
        {
            //Outputs a warning message
            System.out.println("WARNING:You cannot assign a negative end time");
        }
        else
        {
            this.endTime = end;
        }//end if 

    }//end setEndTime
    
    /**
     * Check if the new paused time is less than zero before setting the new paused time for this timer 
     * 
     * @param long end - this Timer's new paused time 
     * @return void 
     */
    public void setPausedTime(long paused)
    {
         //Check if the new end time is less than 0 before setting the paused time
        if(paused <= -1L)
        {
            //Outputs a warning message
            System.out.println("WARNING:You cannot assign a negative paused time");
        }
        else
        {
            this.pausedTime = paused;
        }//end if 

    }//end setPausedTime

    /** 
     * Paints the timer
     * @param Graphics g 
     * @return void
     * */ 
    @Override
    public void paintComponent(Graphics g)
    {
        //Calls the paintComponent method from the JPanel class 
        super.paintComponent(g);
        
        //Start the timer 
        timer.start();
        
        //Initialized the timer
        Date time = new Date(this.timeElapsed + this.pausedTime);
        
        //System.out.println(this.getTimeElapsed());

        //Output the timer 
        g.drawString("Timer: " + DATE_FORMAT.format(time), 30, 20);


    }//end paintComponent

    /**
     * Perform an action to display the current timer
     * @parma ActionEvent e
     * @return void 
     */
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(isStopped)
        {
          
            //Stop the timer 
            
            endTime = System.currentTimeMillis();
            timer.stop(); 
          System.out.println("TIME IS STOPPED");
        }
        else 
        {            
            //Calculate the time elapsed 
            this.timeElapsed = System.currentTimeMillis() - startTime +  TimeUnit.MINUTES.toMillis(300);
            //repaint the timer panel 
            repaint();
            
        }//end if
       
    }//end actionPerformed
}//end class
