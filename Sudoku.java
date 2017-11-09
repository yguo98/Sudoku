/***
  * ICS4U1 Summative
  * Plays a game of sudoku
  * @author Stella Guo and Caleb D'Souza
  * @date June 13, 2016
  * */

// imports required packages
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.Border;
import java.util.*;
import java.util.Collections;
import java.io.*;
import java.util.ArrayList;

//# Stella
public class Sudoku implements ActionListener
{
  // layout colours
  Color navy = new Color(11,72,107);
  Color tealBlue = new Color(59,134,134);
  Color tealGreen = new Color(121,189,154);
  Color jade = new Color(168,219,168);
  Color grass = new Color(207,240,158);
  
  //input pad colours
  Color darkGreen = new Color(0, 102, 102);
  Color medGreen = new Color(0, 204, 153);
  Color lightGreen = new Color(0, 255, 153);
  Color darkRed = new Color(153, 0, 51);
  Color medRed = new Color(204, 0, 102);
  Color lightRed = new Color(198, 83, 140);
  Color darkBlue = new Color(41, 82, 163);
  Color medBlue = new Color(77, 166, 255);
  Color lightBlue = new Color(77, 219, 255);
  
  // starting frame components
  JFrame startFrame = new JFrame("Sudoku");
  JPanel pnlStartMain = new JPanel();
  JPanel pnlStartButtons = new JPanel();
  JLabel lblTitle = new JLabel();
  JButton btnEnter = new JButton("    Enter     ");
  JButton btnInstr = new JButton("Instructions");
  
  // resume or new game frame components
  JFrame resumeFrame = new JFrame("Sudoku: Resume or New Game");
  JPanel pnlResumeMain = new JPanel();
  JPanel pnlResumeBtn = new JPanel();
  JPanel pnlNewGameBtn = new JPanel();
  JPanel pnlOrLbl = new JPanel();
  JLabel lblOr = new JLabel("Or", JLabel.CENTER);
  JButton btnResume = new JButton("  Resume   ");
  JButton btnNewGame = new JButton("New Game");
  
  // choose player frame components
  JFrame playerFrame = new JFrame("Sudoku: Players");
  JPanel pnlPlayerMain = new JPanel();
  JPanel pnlPlayInstr = new JPanel();
  JPanel pnlComboBox = new JPanel();
  JPanel pnlNext = new JPanel();
  JLabel lblPlayInstr1 = new JLabel("Choose an existing player or choose");
  JLabel lblPlayInstr2 = new JLabel("\"Create new player\" to creat your own profile");
  JComboBox<String> players;
  ArrayList<Player> playerArr = new ArrayList<Player>();
  String[] playerNames;
  JButton btnNext = new JButton("Next");
  //#object
  Player player;
  String playerStr;
  int index = -1;
  
  // creating new player frame components
  JFrame newPlayFrame = new JFrame("Sudoku: Create new player");
  JLabel lblNewPlay = new JLabel("Enter a username");
  JPanel pnlNewPlay = new JPanel();
  JButton btnNewPlayNext = new JButton("Next");
  JTextField text = new JTextField();
  
  // choosing mode and difficulty frame components
  JFrame modeFrame = new JFrame("Sudoku: Modes and Difficulties");
  JPanel pnlModeMain = new JPanel();
  JPanel pnlModeBtns = new JPanel();
  JPanel pnlDiffBtns = new JPanel();
  JPanel pnlPlayBtn = new JPanel();
  JButton btnClassic;
  JButton btnAlpha;
  JButton btnColours;
  JButton btnEasy;
  JButton btnMed;
  JButton btnHard;
  JButton btnPlay = new JButton("Play");
  
  // game frame components
  JFrame gameFrame = new JFrame("Sudoku: Play");
  JPanel pnlGame = new JPanel(new BorderLayout());
  ImageIcon imgSave = new ImageIcon("save.jpg");
  ImageIcon imgNewGame = new ImageIcon("new game.jpg");
  ImageIcon imgQuit = new ImageIcon("quit.jpg");
  ImageIcon imgOne = new ImageIcon("one.jpg");
  ImageIcon imgA = new ImageIcon("A.jpg");
  ImageIcon imgGreen = new ImageIcon("green.jpg");
  JMenuBar menuBar = new JMenuBar();
  JButton btnSugg = new JButton(new ImageIcon("suggestion.jpg"));
  JButton btnCertain = new JButton(new ImageIcon("big.jpg"));
  JButton btnCheck = new JButton(new ImageIcon("check.jpg")); 
  JPanel gameboard = new JPanel();
  JPanel pnlInputBtns = new JPanel();
  JPanel pnlGameBtns = new JPanel();    
  JPanel pnlInModeBtns = new JPanel();
  TimerPanel timer = new TimerPanel();
  JButton[][] btnsInput = new JButton[3][3];
  int x = 0,y = 0;
  int intInput;
  int actCom;
  Color colInput;
  String letInput;
  boolean filled = true;
  int[][] numsForConversion = {{1, 2, 3},
    {4, 5, 6}, 
    {7, 8, 9},};
  Color[][] inputColours = {{darkGreen, medGreen, lightGreen}, 
    {darkRed, medRed, lightRed}, 
    {darkBlue, medBlue, lightBlue}};
  String[][] inputLet = {{"a", "b", "c"},
    {"d", "e", "f"}, 
    {"g", "h", "i"},};
  JMenu menuFile;
  JMenu menuModes;
  JMenuItem miSaveQuit;
  JMenuItem miSaveNewGame; 
  JMenuItem miNewGame; 
  JMenuItem miQuit;   
  JMenuItem miClassic;
  JMenuItem miAlpha;
  JMenuItem miColours;
  JMenuItem miAutofill;
  JMenuItem miShowSol;
  boolean play= false;
  Game gameSudoku;
  
  //end frame/highscore frame components
  JFrame endFrame = new JFrame();
  JPanel pnlEndMain = new JPanel();
  JPanel pnlRestart = new JPanel();
  JButton btnRestart = new JButton("Play again?");
  JButton btnQuit= new JButton("Quit");
  HighScorePanel highScores;
  
  // variables to use to create game
  boolean certain;
  int mode = 1,diff = 1;
  
  /**
   * default constructor
   * creates the entire game: sets colours of components, 
   * adds components to panels and to frames, add actionListeners to buttons, etc.
   * @param none
   * */
  public Sudoku()
  {
    
    /********************************************** start window setup ****************************************/
    
    ImageIcon title = new ImageIcon("title.jpg");
    lblTitle.setIcon(title);
    
    btnEnter.setBackground(tealGreen);
    btnEnter.addActionListener(this);
    btnInstr.addActionListener(this);
    btnEnter.setFont(new Font("Garamond", Font.PLAIN, 30));
    btnInstr.setBackground(tealGreen);
    btnInstr.setFont(new Font("Garamond", Font.PLAIN, 30));
    
    pnlStartButtons.setBackground(Color.white);
    pnlStartButtons.setLayout(new BoxLayout(pnlStartButtons, BoxLayout.PAGE_AXIS));
    pnlStartButtons.add(btnEnter);
    pnlStartButtons.add(Box.createRigidArea(new Dimension(400,30)));
    btnEnter.setAlignmentX(Component.CENTER_ALIGNMENT);
    pnlStartButtons.add(btnInstr);
    btnInstr.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    pnlStartMain.setLayout(new BoxLayout(pnlStartMain, BoxLayout.PAGE_AXIS));
    pnlStartMain.setBackground(Color.white);
    pnlStartMain.add(lblTitle);
    lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    pnlStartMain.add(Box.createRigidArea(new Dimension(400,100)));
    pnlStartMain.add(pnlStartButtons);
    pnlStartMain.add(Box.createRigidArea(new Dimension(400,100)));
    
    startFrame.add(pnlStartMain);
    
    startFrame.pack();
    startFrame.setVisible(true);
    startFrame.setResizable(false);
    
    /************************************ end of start window **********************************************/
    
    /************************************ resume or new game setup **********************************************/
    
    pnlResumeMain.setLayout(new BoxLayout(pnlResumeMain, BoxLayout.PAGE_AXIS));
    pnlResumeMain.setPreferredSize(new Dimension(300, 300));
    //pnlResumeMain.setBackground(Color.white);
    
    pnlResumeMain.setBackground(Color.white);
    
    btnResume.addActionListener(this);
    btnNewGame.addActionListener(this);
    
    btnResume.setFont(new Font("Garamond", Font.PLAIN, 30));
    btnNewGame.setFont(new Font("Garamond", Font.PLAIN, 30));
    btnResume.setBackground(tealGreen);
    btnNewGame.setBackground(tealGreen);
    
    pnlResumeBtn.add(btnResume);
    pnlResumeBtn.setBackground(Color.white);
    pnlNewGameBtn.add(btnNewGame);
    pnlNewGameBtn.setBackground(Color.white);
    pnlOrLbl.add(lblOr);
    pnlOrLbl.setBackground(Color.white);
    
    lblOr.setFont(new Font("Garamond", Font.PLAIN, 30));
    
    pnlResumeMain.add(Box.createRigidArea(new Dimension(100, 50)));
    pnlResumeMain.add(pnlResumeBtn);
    pnlResumeMain.add(Box.createRigidArea(new Dimension()));
    pnlResumeMain.add(pnlOrLbl);
    pnlResumeMain.add(Box.createRigidArea(new Dimension()));
    pnlResumeMain.add(pnlNewGameBtn);
    pnlResumeMain.add(Box.createRigidArea(new Dimension(100, 50)));
    
    pnlResumeMain.setBorder(BorderFactory.createLineBorder(Color.black));
    
    resumeFrame.add(pnlResumeMain);
    resumeFrame.pack();
    resumeFrame.setVisible(false);
    resumeFrame.setResizable(false);
    
    /************************************ end of resume or new game **********************************************/
    
    /************************************ player window setup **********************************************/
    
    pnlPlayerMain.setLayout(new BoxLayout(pnlPlayerMain, BoxLayout.PAGE_AXIS));
    pnlPlayerMain.setPreferredSize(new Dimension(450,300));
    pnlPlayerMain.setBackground(Color.white);
    
    lblPlayInstr1.setFont(new Font("Garamond", Font.PLAIN, 20));
    lblPlayInstr2.setFont(new Font("Garamond", Font.PLAIN, 20));
    pnlPlayInstr.add(lblPlayInstr1);
    pnlPlayInstr.add(lblPlayInstr2);
    pnlPlayInstr.setBackground(Color.white);
    
    
    lblNewPlay.setFont(new Font("Garamond", Font.PLAIN, 20));
    btnNewPlayNext.setFont(new Font("Garamond", Font.PLAIN, 20));
    btnNewPlayNext.addActionListener(this);
    btnNewPlayNext.setBackground(tealGreen);
    text.setFont(new Font("Garamond", Font.PLAIN, 20));
    
    pnlNewPlay.setLayout(new BoxLayout(pnlNewPlay, BoxLayout.PAGE_AXIS));
    pnlNewPlay.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
    pnlNewPlay.setBackground(Color.white);
    pnlNewPlay.add(lblNewPlay);
    pnlNewPlay.add(text);
    pnlNewPlay.add(btnNewPlayNext);
    lblNewPlay.setAlignmentX(Component.CENTER_ALIGNMENT);
    text.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnNewPlayNext.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    newPlayFrame.add(pnlNewPlay);
    newPlayFrame.pack();
    newPlayFrame.setVisible(false);
    newPlayFrame.setResizable(false);
    
    /************************************ end of player window **********************************************/
    
    /************************************ menu window setup **********************************************/
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Font titleFont = new Font("Garamond", Font.PLAIN, 30);
    Dimension btnDimensions = new Dimension(150,150);
    Dimension pnlDimensions = new Dimension(520,230);
    TitledBorder borderMode = BorderFactory.createTitledBorder(blackline, 
                                                               "Mode", TitledBorder.LEFT, TitledBorder.TOP, titleFont);
    TitledBorder borderDiff = BorderFactory.createTitledBorder(blackline, 
                                                               "Difficulty", TitledBorder.LEFT, TitledBorder.TOP, titleFont);
    ImageIcon imgClassic = new ImageIcon("classic.jpg");
    ImageIcon imgAlpha = new ImageIcon("alpha.jpg");
    ImageIcon imgColours = new ImageIcon("colours.jpg");
    ImageIcon imgEasy = new ImageIcon("easy.jpg");
    ImageIcon imgMed = new ImageIcon("medium.jpg");
    ImageIcon imgHard = new ImageIcon("hard.jpg");
    
    btnClassic = new JButton(imgClassic);
    btnAlpha = new JButton(imgAlpha);
    btnColours = new JButton(imgColours);
    btnEasy = new JButton(imgEasy);
    btnMed = new JButton(imgMed);
    btnHard = new JButton(imgHard);
    
    btnClassic.setPreferredSize(btnDimensions);
    btnClassic.setMaximumSize(btnDimensions);
    btnAlpha.setPreferredSize(btnDimensions);
    btnAlpha.setMaximumSize(btnDimensions);
    btnColours.setPreferredSize(btnDimensions);
    btnColours.setMaximumSize(btnDimensions);
    
    btnClassic.addActionListener(this);
    btnAlpha.addActionListener(this);
    btnColours.addActionListener(this);
    
    pnlModeBtns.setBackground(Color.white);
    
    pnlModeBtns.setLayout(new BoxLayout(pnlModeBtns, BoxLayout.LINE_AXIS));
    pnlModeBtns.setPreferredSize(pnlDimensions);
    pnlModeBtns.setMaximumSize(pnlDimensions);
    pnlModeBtns.setBorder(borderMode);
    
    pnlModeBtns.add(Box.createHorizontalGlue());
    pnlModeBtns.add(btnClassic);
    pnlModeBtns.add(Box.createHorizontalGlue());
    pnlModeBtns.add(btnAlpha);
    pnlModeBtns.add(Box.createHorizontalGlue());
    pnlModeBtns.add(btnColours);
    pnlModeBtns.add(Box.createHorizontalGlue());
    
    btnEasy.setPreferredSize(btnDimensions);
    btnEasy.setMaximumSize(btnDimensions);
    btnMed.setPreferredSize(btnDimensions);
    btnMed.setMaximumSize(btnDimensions);
    btnHard.setPreferredSize(btnDimensions);
    btnHard.setMaximumSize(btnDimensions);
   
    btnEasy.addActionListener(this);
    btnMed.addActionListener(this);
    btnHard.addActionListener(this);
    
    pnlDiffBtns.setBackground(Color.white);
    
    pnlDiffBtns.setLayout(new BoxLayout(pnlDiffBtns, BoxLayout.LINE_AXIS));
    pnlDiffBtns.setPreferredSize(pnlDimensions);
    pnlDiffBtns.setMaximumSize(pnlDimensions);
    pnlDiffBtns.add(Box.createHorizontalGlue());
    pnlDiffBtns.setBorder(borderDiff);
    pnlDiffBtns.add(Box.createHorizontalGlue());
    pnlDiffBtns.add(btnEasy);
    pnlDiffBtns.add(Box.createHorizontalGlue());
    pnlDiffBtns.add(btnMed);
    pnlDiffBtns.add(Box.createHorizontalGlue());
    pnlDiffBtns.add(btnHard);
    pnlDiffBtns.add(Box.createHorizontalGlue());
    
    pnlModeMain.setBackground(Color.white);
    pnlModeMain.setLayout(new BoxLayout(pnlModeMain, BoxLayout.PAGE_AXIS));
    pnlModeMain.setPreferredSize(new Dimension(560,540));
    
    btnPlay.addActionListener(this);
    btnPlay.setFont(new Font("Garamond", Font.PLAIN, 30));
    btnPlay.setBackground(tealGreen);
    pnlPlayBtn.add(btnPlay);
    pnlPlayBtn.setBackground(Color.white);
    
    pnlModeMain.add(Box.createVerticalGlue());
    pnlModeMain.add(pnlModeBtns);
    pnlModeMain.add(Box.createVerticalGlue());
    pnlModeMain.add(pnlDiffBtns);
    pnlModeMain.add(Box.createVerticalGlue());
    pnlModeMain.add(pnlPlayBtn);
    pnlModeMain.add(Box.createVerticalGlue());
    
    modeFrame.add(pnlModeMain);
    modeFrame.pack();
    modeFrame.setVisible(false);
    modeFrame.setResizable(false);
    
    /************************************ end of menu window **********************************************/
    
    /************************************ game window setup **********************************************/
    
    
    menuBar = new JMenuBar();
    menuFile = new JMenu("File");
    menuModes = new JMenu("Modes");
    miSaveQuit = new JMenuItem("Save and Quit", imgSave);
    miSaveNewGame = new JMenuItem("Save and New Game", imgSave);    
    miNewGame = new JMenuItem("Quit and New Game", imgNewGame);  
    miQuit = new JMenuItem("Quit", imgQuit);      
    miClassic = new JMenuItem("Switch to Classic", imgOne);
    miAlpha = new JMenuItem("Switch to Alphabet", imgA);
    miColours = new JMenuItem("Switch to Colours", imgGreen);
    miAutofill = new JMenuItem("Autofill (cheat)");
    miShowSol = new JMenuItem("Show Solution");
    
    menuFile.setFont(new Font("Garamond", Font.PLAIN, 15));
    menuModes.setFont(new Font("Garamond", Font.PLAIN, 15));
    
    miSaveQuit.addActionListener(this);
    miSaveNewGame.addActionListener(this);
    miNewGame.addActionListener(this);
    miQuit.addActionListener(this);
    miClassic.addActionListener(this);
    miAlpha.addActionListener(this);
    miColours.addActionListener(this);    
    miAutofill.addActionListener(this);  
    miShowSol.addActionListener(this);
    
    menuFile.add(miSaveQuit);
    menuFile.add(miSaveNewGame);
    menuFile.add(miNewGame);
    menuFile.add(miQuit);
    menuFile.add(miAutofill);
    menuFile.add(miShowSol);
    
    menuModes.add(miClassic);
    menuModes.add(miAlpha);
    menuModes.add(miColours);
    
    menuBar.add(menuFile);
    menuBar.add(menuModes);
    
    gameFrame.setJMenuBar(menuBar);
    gameFrame.pack();
    gameFrame.setVisible(false);
    gameFrame.setResizable(false);
    
    /************************************ end of game window **********************************************/
    
    /************************************ end window setup **********************************************/
    pnlRestart.setBackground(Color.white);
    pnlEndMain.setBackground(Color.white);
    btnRestart.addActionListener(this);
    btnQuit.addActionListener(this);
    btnRestart.setBackground(tealGreen);
    btnQuit.setBackground(tealGreen);
    pnlRestart.add(btnRestart);
    pnlRestart.add(btnQuit);
    
  }//end default constructor 
  
    
  /**
   * actionPerformed
   * controls actions when buttons are pressed
   * @param ActionEvent e - the action event
   * @retrn void
   * */
  //#action
  //#instance
  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnEnter)
    {
      // moves from start frame to choose resume or new game frame
      startFrame.setVisible(false);
      resumeFrame.setVisible(true);
    }
    else if(e.getSource() == btnInstr)
    {
      // shows instructions
      ImageIcon imgInstr = new ImageIcon("instr.jpg");
      JOptionPane.showMessageDialog(startFrame,
                                    "Fill out each square with a number from 1 to 9. No number can \n"+
                                    "be repeated in each row, column, and sector of 3x3 squares. ",
                                    "Instructions",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    imgInstr);
    }
    else if(e.getSource() == btnNewGame)
    {
      // moves to choosing player if user chooses to play a new game
      resumeFrame.setVisible(false);
      
      // reads players from list and creates combo box
      ArrayList<Player> fromFile = Player.readPlayersFromFile();
      
      if(fromFile!=null)
      {
        playerArr.clear();
        playerArr.addAll(fromFile);      
        playerNames = new String[playerArr.size()+1];
      }
      else
      {
        playerNames = new String[1];
      }
      System.out.println(playerArr);
      playerNames = Player.usernamesTaken.toArray(playerNames); 
      playerNames[playerArr.size()] = "Create new player";
      players = new JComboBox<String>(playerNames);
      players.setFont(new Font("Garamond", Font.PLAIN,20));
      players.addActionListener(this);
      
      players.setPreferredSize(new Dimension(350, 50));
      
      pnlComboBox.removeAll();
      pnlComboBox.repaint();
      
      pnlComboBox.add(players);
      pnlComboBox.setBackground(Color.white);
      
      btnNext.addActionListener(this);
      btnNext.setFont(new Font("Garamond", Font.PLAIN, 20));
      btnNext.setBackground(tealGreen);
      
      pnlNext.removeAll();
      pnlNext.repaint();
      
      pnlNext.add(btnNext);
      pnlNext.setBackground(Color.white);
      
      pnlPlayerMain.removeAll();
      pnlPlayerMain.repaint();
      
      pnlPlayerMain.add(Box.createVerticalGlue());
      pnlPlayerMain.add(pnlPlayInstr);
      pnlPlayerMain.add(Box.createVerticalGlue());
      pnlPlayerMain.add(pnlComboBox);
      pnlPlayerMain.add(Box.createVerticalGlue());
      pnlPlayerMain.add(pnlNext);
      pnlPlayerMain.add(Box.createVerticalGlue());
//      
//      playerFrame.removeAll();
//      playerFrame.repaint();
      
      playerFrame.add(pnlPlayerMain);
      playerFrame.pack();
      playerFrame.setVisible(false);
      playerFrame.setResizable(false);
      
      playerFrame.setVisible(true);
    }
    else if(e.getSource()== btnResume)
    {
      pnlGame.removeAll();
      pnlGame.repaint();
       
      // if user chooses to resume game, opens game
      //#read
//#Caleb
      open(resumeFrame, gameSudoku); 
      
      // sets up game if user chose a valid file to open
      //#error
      if(gameSudoku!=null) 
      {
       // sets the timer        
        timer = new TimerPanel(); 
        timer.setPausedTime(gameSudoku.time); 
        
        // sets up the game
        gameSetup();
        gameSudoku.setPreferredSize(new Dimension(590,590));
        gameSudoku.setMaximumSize(new Dimension(590,590));
        gameSudoku.setMinimumSize(new Dimension(590,590));
        
        pnlGame.setLayout(new BorderLayout());
        pnlGame.add(gameSudoku, BorderLayout.CENTER);
        pnlGame.add(pnlGameBtns, BorderLayout.EAST);
        
        gameFrame.add(pnlGame);
        gameFrame.pack();
        resumeFrame.setVisible(false);
        gameFrame.setVisible(true);
      }
    }
    else if(e.getSource() == btnNext)
    {
     // choosing players 
      playerStr = (String)players.getSelectedItem();
      
      // checks if playerStr is in the list of players read from file
      index = -1;
      for(int p = 0; p < playerNames.length-1; p++)
      {
        if(playerNames[p].equals(playerStr))
          index = p;
      }//end for
      
      //if it isn't, then creates new player
      if(index<0)
        newPlayFrame.setVisible(true);
      // otherwise, assigns player to existing player and moves onto choosing modes
      else
      {        
        player = playerArr.get(index);
        playerFrame.setVisible(false);
        modeFrame.setVisible(true);
      }//end if
    }
    else if(e.getSource() == btnNewPlayNext)
    {
      // choosing new player, creating new player, adding new player to lsit
      playerStr = text.getText();
      System.out.println("username: " + playerStr);
      //#object
      player = new Player(false,0,0,0,0,playerStr,"novice");
      playerArr.add(player);
      
      // moves on to chossing modes
      newPlayFrame.setVisible(false);
      playerFrame.setVisible(false);
      modeFrame.setVisible(true);
    }
    else if(e.getSource() == btnPlay)
    {
      // moves onto actual game
      modeFrame.setVisible(false);
      
      pnlGame.removeAll();
      pnlGame.repaint();
      
      // sets up game
      //#object
      gameSudoku = new Game(mode, player, diff);
      gameSudoku.setPreferredSize(new Dimension(590,590));
      gameSudoku.setMaximumSize(new Dimension(590,590));
      gameSudoku.setMinimumSize(new Dimension(590,590));
      
      // set up timer
      //#object
      timer = new TimerPanel(System.currentTimeMillis(),0,0, gameSudoku.time, false); 
      
      gameSetup();
      pnlGame.setLayout(new BorderLayout());
      pnlGame.add(gameSudoku, BorderLayout.CENTER);
      pnlGame.add(pnlGameBtns, BorderLayout.EAST);
      
      gameFrame.add(pnlGame);
      gameFrame.pack();
      gameFrame.setVisible(true);
      
    }
    else if(e.getSource() == btnClassic)
    {
      // choosing mode
      mode=1;
    }
    else if(e.getSource() == miClassic)
    {
      // switching mode
      mode=1;
      gameSudoku.resetCells(1);
      gameSudoku.gameMode = 1;
      resetButtons();
    }
    else if(e.getSource() == btnAlpha)
    {
      // choosing mode
      mode=2;
    }
    else if(e.getSource() == miAlpha)
    {
      // choosing mode
      mode=2;
      gameSudoku.resetCells(2);
      gameSudoku.gameMode = 2;
      resetButtons();
    }
    else if(e.getSource() == btnColours)
    {
      // choosing mode
      mode=3;
    }
    else if(e.getSource() == miColours)
    {
      // choosing mode
      mode = 3;
      gameSudoku.resetCells(3);
      gameSudoku.gameMode = 3;
      resetButtons();
    }
    else if(e.getSource() == btnEasy)
    {
      // choosing difficulty
      diff=1;
    }
    else if(e.getSource() == btnMed)
    {
      // choosing difficulty
      diff=2;
    }
    else if(e.getSource() == btnHard)
    {
      // choosing difficulty
      diff=3;
    }
    else if(e.getSource()==miSaveQuit)
    {
      // saves and quits game
      timer.setIsStopped(true); 
      System.out.println(timer.getTimeElapsed());
      gameSudoku.time = timer.getTimeElapsed(); 
      System.out.println("game time " + gameSudoku.time);
      save(gameFrame, gameSudoku); 
      System.exit(0); 
    }
    else if(e.getSource()==miSaveNewGame)
    {
      // saves game and starts up window to start making new game
      timer.setIsStopped(true);
      gameSudoku.time = timer.getTimeElapsed(); 
      save(gameFrame, gameSudoku);
      gameFrame.setVisible(false);
      resumeFrame.setVisible(true);
    }
    else if(e.getSource()==miNewGame)
    {
      // quits current game w/o saving and starts setting up new games
      gameFrame.setVisible(false);
      resumeFrame.setVisible(true);
    }
    else if(e.getSource()==miQuit || e.getSource()==btnQuit)
    {
      // quits
      System.exit(0);
    }
    else if(e.getSource() == miAutofill)
    {
      // autofills solution
      gameSudoku.autofill();
      certain = true;
      btnCheck.setEnabled(true);
    }
    else if(e.getSource() == miShowSol)
    {
      // shows solution
      gameSudoku.showSolution();
    }
    
    // if the game has started
    if(play)
    {
      // checks if any of the cells have been clicked
      for(int r = 0; r < gameSudoku.gameCells.length; r++)
      {
        for(int c = 0; c < gameSudoku.gameCells[r].length; c++)
        {
          if(e.getSource()==gameSudoku.gameCells[r][c])
          {
            gameSudoku.gameCells[x][y].setBorder(null);
            for(int i = 0; i < btnsInput.length; i++)
            {
              for(int j = 0; j < btnsInput[i].length; j++)
              {
                btnsInput[i][j].setEnabled(true);
              }//end nested for
            }//end for
            
            // if it has, then sets x, y to coordinates and creates border so user knows which cell they picked
            x = r;
            y = c;  
            gameSudoku.gameCells[x][y].setBorder(BorderFactory.createLineBorder(tealGreen, 3));
            
          }//end if
        }//end nested for
      }//end for
      
      //checks buttons for input
      for(int r = 0; r < btnsInput.length; r++)
      {
        for(int c = 0; c < btnsInput[r].length; c++)
        {
          // if there is input
          if(e.getSource()==btnsInput[r][c])
          {       
            // gets action command
            actCom = Integer.valueOf(btnsInput[r][c].getActionCommand());
            
            if(certain)
            {
              // if certain is true, then it is drawing the big numbers
              gameSudoku.gameCells[x][y].num = actCom;
              gameSudoku.userSol[x][y] = actCom;
            }
            else
            {
              // if certain is false, then it is drawing the small suggestions
              if(gameSudoku.gameCells[x][y].sugg[actCom])
                gameSudoku.gameCells[x][y].sugg[actCom] = false;
              else
                gameSudoku.gameCells[x][y].sugg[actCom] = true;
            }//end if
            
            // sets certainty of cell and repaints
            gameSudoku.gameCells[x][y].certain = certain;
            gameSudoku.gameCells[x][y].repaint();
            
          }//end if
          
          if(gameSudoku.userSol[r][c]==0)
            filled = false; // to check when to enable check btn
        }//end for
      }//end for
    }//end if
    
    // changes certainty buttons
    if(e.getSource()==btnCertain)
    {
      certain = true;
    }
    else if(e.getSource() == btnSugg)
    {
      certain = false;
    }
    // if the user is done
    else if(e.getSource() == btnCheck)
    {
      //stops timer
      timer.setIsStopped(true); 
      gameSudoku.time = timer.getTimeElapsed(); 
      
      // adds time to player
      player.addTime(gameSudoku.time); 
      player.setAverageTime(player.calulateAverageTime()); 
      player.setFastestTime(player.findFastestTime()); 
       
      for(int p = 0; p < playerArr.size(); p++) 
      { 
        if(playerArr.get(p).getUsername().equals(player.getUsername())) 
          playerArr.set(p, player); 
      } 
       
      // writes player to file
      Player.writeToStringToFile(player); 
      Player.writePlayerToFile(playerArr); 
      
      // checks solution
      gameSudoku.checkSolution();
      
      // sets up end frame
      pnlEndMain.removeAll();
      pnlEndMain.repaint();
      
      pnlEndMain.setLayout(new BorderLayout());
      
      //#object
      highScores = new HighScorePanel(timer.getTimeElapsed(), player);
      highScores.setPreferredSize(new Dimension(600,550));
      pnlEndMain.setBackground(Color.white);
      pnlEndMain.add(highScores, BorderLayout.CENTER);
      pnlEndMain.add(pnlRestart, BorderLayout.SOUTH);
      endFrame.add(pnlEndMain);
      endFrame.pack();
      endFrame.setVisible(true);
      endFrame.setResizable(false);
    }
    else if(e.getSource() == btnRestart)
    {
      // if user want to play again, start from the top
      endFrame.setVisible(false);
      gameFrame.setVisible(false);
      startFrame.setVisible(true);
    }
    
    // to check when to enable the check button
    if(filled)
      btnCheck.setEnabled(true);
  }//end actionPerformed
  
  /**
   * gameSetup method
   * setups upt game input buttons and panels
   * @param none
   * @return void
   * */
  //#instance
  public void gameSetup()
  {
    btnSugg.addActionListener(this);
    btnCertain.addActionListener(this);
    btnCheck.addActionListener(this);
    btnCheck.setEnabled(false);
    btnSugg.setBackground(Color.white);
    btnCertain.setBackground(Color.white);
    btnCheck.setBackground(Color.white);
    btnSugg.setBorder(BorderFactory.createLineBorder(Color.black));
    btnCertain.setBorder(BorderFactory.createLineBorder(Color.black));
    btnCheck.setBorder(BorderFactory.createLineBorder(Color.black));
    
    // to make sure it doesn't just keep adding buttons when creating new games
    pnlInModeBtns.removeAll();
    pnlInModeBtns.repaint();
    
    pnlInModeBtns.add(btnSugg);
    pnlInModeBtns.add(btnCertain);
    pnlInModeBtns.add(btnCheck);
    
    pnlInputBtns.setLayout(new GridLayout(3,3,5,5));
    pnlInputBtns.removeAll();
    pnlInputBtns.repaint();
    setButtons();
    
    pnlInputBtns.setPreferredSize(new Dimension(240,240));
    pnlInputBtns.setMaximumSize(new Dimension(240,240));
    pnlInputBtns.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    
    pnlGameBtns.removeAll();
    pnlGameBtns.repaint();
    
    pnlGameBtns.setLayout(new BoxLayout(pnlGameBtns, BoxLayout.PAGE_AXIS));
    pnlGameBtns.setBorder(BorderFactory.createMatteBorder(5,5,5,5,navy));
    pnlGameBtns.setPreferredSize(new Dimension(240,590));
    pnlGameBtns.add(Box.createRigidArea(new Dimension(1, 50)));
    /*ADDED TIMER TO THE PANEL*/
    pnlGameBtns.add(timer);
    timer.setPreferredSize(new Dimension(240,30));
    timer.setMaximumSize(new Dimension(240,30));
    pnlGameBtns.add(pnlInputBtns);
    pnlGameBtns.add(pnlInModeBtns);
     
    // adds all the acitonlisteners
    for(int r = 0; r < gameSudoku.gameCells.length; r++)
    {
      for(int c = 0; c < gameSudoku.gameCells[r].length; c++)
      {
        if(!gameSudoku.prefilled[r][c])
          gameSudoku.gameCells[r][c].addActionListener(this);
      }//end for
    }// end for
    
    play = true;
  }//end gameSetup
  
  /**
   * setButtons method
   * sets up the input buttons, colour, text, colour,etc.
   * @param none
   * @return void
   * */
  //#instance
  public void setButtons()
  {
    int num = 1;
    for(int r = 0; r < btnsInput.length; r++)
    {
      for(int c = 0; c < btnsInput[r].length; c++)
      {
        if(mode==1)
        {
          btnsInput[r][c] = new JButton(""+num);
          btnsInput[r][c].setBackground(Color.white);
        }
        else if(mode==2)
        {
          btnsInput[r][c] = new JButton(inputLet[r][c]);
          btnsInput[r][c].setBackground(Color.white);
        }
        else if(mode == 3)
        {
          btnsInput[r][c] = new JButton();
          btnsInput[r][c].setBackground(inputColours[r][c]);
        }
        
        // sets action command because it'll be easier to do input for letters and colours later
        btnsInput[r][c].setActionCommand(""+num);
        
        btnsInput[r][c].setFont(new Font("Garamond", Font.PLAIN, 30));
        btnsInput[r][c].setPreferredSize(new Dimension(70,70));
        btnsInput[r][c].addActionListener(this);
        btnsInput[r][c].setEnabled(false);
        btnsInput[r][c].setBorder(BorderFactory.createLineBorder(Color.black));
        pnlInputBtns.add(btnsInput[r][c]);
        num++;
      }//end for
    }//end for 
  }//end setButtons
  
  /**
   * resetButtons method
   * resets the input buttons, colour, text, colour,etc. when mode is changed
   * @param none
   * @return void
   * */
  //#instance
  public void resetButtons()
  {
    int num = 1;
    for(int r = 0; r < btnsInput.length; r++)
    {
      for(int c = 0; c < btnsInput[r].length; c++)
      {
        if(mode==1)
        {
          btnsInput[r][c].setText(""+num);
          btnsInput[r][c].setBackground(Color.white);
        }
        else if(mode==2)
        {
          btnsInput[r][c].setText(inputLet[r][c]);
          btnsInput[r][c].setBackground(Color.white);
        }
        else if(mode == 3)
        {
          btnsInput[r][c].setText(null);
          btnsInput[r][c].setBackground(inputColours[r][c]);
        }
        num++;
      }//end for
    }//end for
  }//end resetButtons

  //#instance
    //#save
//#Caleb
  public void save(JFrame frame, Game g)
  {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Specify A File To Save The Game");   
    File fileToSave;
    int userSelection = fileChooser.showSaveDialog(frame);
    
    if (userSelection == JFileChooser.APPROVE_OPTION)
    {
      fileToSave = fileChooser.getSelectedFile();
      writeGameToFile(fileToSave, g);
    }//end if 
  }//end save 
  
  
  //#instance
    //#object
  public void open(JFrame frame, Game g)
  {
    //open new file 
    JFileChooser fileChooser = new JFileChooser();
    File openFile;
    fileChooser.setDialogTitle("Select A File To Open The Game");
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    
    int result = fileChooser.showOpenDialog(frame);
    
    if (result == JFileChooser.APPROVE_OPTION) 
    {
      openFile = fileChooser.getSelectedFile();
      readGameFromFile(openFile, g);
    }//end if 
  }//end open
  
  /**
   * Write the Gmae object to a file
   * 
   * @parm File fileName - the file to write the object
   * @param Games g - the game onject to be writen to the file
   * @return void
   */ 
  //#instance
    //#save
//#Caleb
    //#Caleb
  public void writeGameToFile(File fileName, Game g)
  {
    //#error
    try
    {
      //Create an fileName file with an ObjectOutputStream
      FileOutputStream fileOut = new FileOutputStream(fileName.getAbsolutePath());
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      
      //Write the Game to the file
      objectOut.writeObject(g);
      
      //flush the Game output stream
      objectOut.flush();
      
      //close the objcet output stream
      objectOut.close();
    }
    catch(IOException ioe)
    {
      System.out.println("The following problem writing to a file occurred:\n" + ioe);
    }//end try  
  }//end writeObjectToFile
  
  /**
   * Read the game form a file 
   * 
   * @param File fileName - file to read form 
   * @param Game g - the Game that is read
   */
  //#instance
    //#object
  public void readGameFromFile(File fileName, Game g)
  {
    //#error
    try
    {
      // create an ObjectInputStream for the files we created before
      ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));
      
      g = (Game) objectIn.readObject();
      //#object
      gameSudoku = new Game(g);
      player = gameSudoku.gamePlayer;
//      System.out.println(gameSudoku.toString());
      //close the object output stream
      objectIn.close();
    }
    catch(IOException ioe)
    {
      System.out.println("The following problem reading from a file occurred:\n" + ioe);
    } 
    catch(ClassNotFoundException c)
    {
      System.out.println("Class not found");
      c.printStackTrace();
    }//end try 
  }//end readObjectToFile
  
  // main
  //#main
  public static void main(String[] args)
  {
    new Sudoku();    
  }//end main
  
}//end class Sudoku

// cell class for drawing the cells of the game
//#define
  //#Stella
class Cell extends JButton implements Serializable
{
  // layout colours
  Color navy = new Color(11,72,107);
  Color tealBlue = new Color(59,134,134);
  Color tealGreen = new Color(121,189,154);
  Color jade = new Color(168,219,168);
  Color grass = new Color(207,240,158);
  
  //input pad colours
  Color darkGreen = new Color(0, 102, 102);
  Color medGreen = new Color(0, 204, 153);
  Color lightGreen = new Color(0, 255, 153);
  Color darkRed = new Color(153, 0, 51);
  Color medRed = new Color(204, 0, 102);
  Color lightRed = new Color(198, 83, 140);
  Color darkBlue = new Color(41, 82, 163);
  Color medBlue = new Color(77, 166, 255);
  Color lightBlue = new Color(77, 219, 255);
  
  Color[] colours = {new Color(255,255,255), darkGreen, medGreen, lightGreen, darkRed, medRed, lightRed, darkBlue, medBlue, lightBlue};
  
  char[] letters = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
  
  /**
   * instance variables
   * */
  int num; // number to draw in big font
  int cellMode; // mode of game
  boolean[] sugg = new boolean[10]; // to know which suggestions have been made
  boolean certain = false; // whether to draw big or small
  
  /**
   * default constructor
   * creates new cell and sets mode
   * @param int newMode
   * */
  public Cell(int newMode)
  {
    this.cellMode = newMode;
    repaint();
  }//end default constructor
  
  /**
   * paintComponent
   * paints the cell
   * @param Graphics g
   * @return none
   * */
  //#instance
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    // paints background
    g.setColor(Color.white);
    g.fillRect(0,0,60,60);
    
    g.setColor(Color.black);
    
    // if certain, then draws number/letter/colour that fills the square
    if(certain)
    {
      g.setFont(new Font("Garamond", Font.PLAIN, 45)); 
      // makes sure if the cell is empty, no zero's get drawn
      if(num >0)
      {
        if(cellMode==1)
          g.drawString("" + num,18,45);
        else if(cellMode==2)
        {
          g.drawString(""+letters[num],19,40);
        }
        else if(cellMode==3)
        {
          g.setColor(colours[num]);
          g.fillRect(0,0,60,60);
        }//end if
      }//end if
    }
    // otherwise, draws the suggestions
    else
    {
      // checks the suggestions list and draws each element which is true
      for(int i = 1; i < sugg.length; i++)
      {
        if(sugg[i]==true)
        { 
          if(cellMode==1)
            drawSugg(i, g);
          else if(cellMode==2)
            drawSugg(letters[i], g);
          else
            drawSugg(colours[i], g);
        }//end ifs
      }//end nested for
    }//end if
  }//end paintComponent
  
  /**
   * drawSugg for integers
   * draws suggestions
   * @param int num - the number to draw
   * @param Graphics g = the graphics object
   * */
  //#instance
  public void drawSugg(int num, Graphics g)
  {
    g.setFont(new Font("Garamond", Font.PLAIN, 14));
    if(num==1)
      g.drawString("1",9,18);
    else if(num==2)
      g.drawString("2",25,18);
    else if(num==3)
      g.drawString("3",41,18);
    else if(num==4)
      g.drawString("4",9,34);
    else if(num==5)
      g.drawString("5",25,34);
    else if(num==6)
      g.drawString("6",41,34);
    else if(num==7)
      g.drawString("7",9,50);
    else if(num==8)
      g.drawString("8",25,50);
    else if(num==9)        
      g.drawString("9",41,50);
  }//end drawSugg(int)
  
  /**
   * drawSugg for letters
   * draws suggestions
   * @param char let - the letter to draw
   * @param Graphics g = the graphics object
   * */
  //#instance
  public void drawSugg(char let, Graphics g)
  {
    g.setFont(new Font("Garamond", Font.PLAIN, 14));
    if(let == 'a')
      g.drawString("a",9,17);
    else if(let == 'b')
      g.drawString("b",26,17);
    else if(let == 'c')
      g.drawString("c",43,17);
    else if(let == 'd')
      g.drawString("d",9,35);
    else if(let == 'e')
      g.drawString("e",26,35);
    else if(let == 'f')
      g.drawString("f",43,35);
    else if(let == 'g')
      g.drawString("g",9,50);
    else if(let == 'h')
      g.drawString("h",26,50);
    else if(let == 'i')        
      g.drawString("i",43,50);
  }//end drawSugg(char)
  
  /**
   * drawSugg for colours
   * draws suggestions
   * @param Color col - the colours to draw
   * @param Graphics g = the graphics object
   * */
  //#instance
  public void drawSugg(Color col, Graphics g)
  {
    g.setColor(col);
    if(col.equals(darkGreen))
      g.fillRect(6,6,13,13);
    else if(col.equals(medGreen))
      g.fillRect(23,6,13,13);
    else if(col.equals(lightGreen))
      g.fillRect(40,6,13,13);
    else if(col.equals(darkRed))
      g.fillRect(6,23,13,13);
    else if(col.equals(medRed))
      g.fillRect(23,23,13,13);
    else if(col.equals(lightRed))
      g.fillRect(40,23,13,13);
    else if(col.equals(darkBlue))
      g.fillRect(6,40,13,13);
    else if(col.equals(medBlue))
      g.fillRect(23,40,13,13);
    else if(col.equals(lightBlue)) 
      g.fillRect(40,40,13,13);
  }//end drawSugg(Color)
}//end class Cell

// game class
//#define
  //#Stella
class Game extends JPanel implements Serializable
{
  // layout colours
  Color navy = new Color(11,72,107);
  Color tealBlue = new Color(59,134,134);
  Color tealGreen = new Color(121,189,154);
  Color jade = new Color(168,219,168);
  Color grass = new Color(207,240,158);
  
  //input pad colours
  Color darkGreen = new Color(0, 102, 102);
  Color medGreen = new Color(0, 204, 153);
  Color lightGreen = new Color(0, 255, 153);
  Color darkRed = new Color(153, 0, 51);
  Color medRed = new Color(204, 0, 102);
  Color lightRed = new Color(198, 83, 140);
  Color darkBlue = new Color(41, 82, 163);
  Color medBlue = new Color(77, 166, 255);
  Color lightBlue = new Color(77, 219, 255);
  
  JFrame solFrame = new JFrame();
  JPanel pnlSol = new JPanel();
  JPanel gameboard = new JPanel();
  ShowSolution drawSol;
  Color[] inputColours = {new Color(255,255,255),darkGreen, medGreen, lightGreen,darkRed, medRed, lightRed,darkBlue, medBlue, lightBlue};
  String[] inputLet = {" ","a", "b", "c","d", "e", "f","g", "h", "i"};
  
  boolean[][] prefillEasy = {{true, false, false, false, false, false, false, true, true},
    {true, true, false, false, false, false, true, true, false},
    {false, false, false, true, false, false, false, false, false},
    {false, true, false, false, true, false, true, false, true},
    {false, false, true, true, false, true, true, false, false},
    {true, false, true, false, true, false, false, true, false},
    {false, false, false, false, false, true, false, false, false},
    {false, true, true, false, false, false, false, true, true},
    {true, true, false, false, false, false, false, false, true}};
  boolean[][] prefillMedium = {{false, false, false, false, false, false, true, false, true},
    {false, false, false, true, false, false, true, true, true},
    {true, false, false, true, false, false, false, false, false},
    {false, false, false, false, true, false, true, true, false},
    {false, false, true, true, false, true, true, false, false},
    {false, true, true, false, true, false, false, false, false},
    {false, false, false, false, false, true, false, false, true},
    {true, true, true, false, false, true, false, false, false},
    {true, false, true, false, false, false, false, false, false}};
  boolean[][] prefillHard = {{false, false, false, true, false, false, false, true, false},
    {false, true, false, false, true, false, false, false, true},
    {true, false, false, true, true, false, true, false, false},
    {false, false, true, false, true, false, true, false, false},
    {false, false, false, true, false, true, false, false, false},
    {false, false, true, false, true, false, true, false, false},
    {false, false, true, false, true, true, false, false, true},
    {true, false, false, false, true, false, false, true, false},
    {false, true, false, false, false,true, false, false, false,}};
  
  /**
   * instance variables
   * */
  int[][]  sol;
  int[][] userSol;
  boolean[][] prefilled;
  Cell[][] gameCells; 
  int gameMode;
  int difficulty;
  long time;
  Player gamePlayer;
  
  /**
   * default constructor
   * creates new game
   * @param int newMode - the mode
   * @param newPlayer - the player playing
   * @param newDifficulty - the difficulty
   * */
  public Game(int newMode, Player newPlayer, int newDifficulty)
  {
    this(newPlayer, newMode, /*#object*/ new Solution().solution, new int[9][9], newDifficulty,new Cell[9][9], 0, true);
  }//end default constructor
  
  /**
   * (Player , int , int[][], int[][] , int , Cell[][] , long, boolean) constructor
   * creates game
   * @param Player newPlayer - the player
   * @param int newMode - the mode
   * @param int[][] sol - the solution
   * @param newUserSol - the user's solution
   * @param newDifficulty - the difficulty
   * @param Cell[][] newGameCells - the game cells
   * @param long newTime - the time
   * @param boolean isNew - is it a new game
   * */
  public Game(Player newPlayer, int newMode, int[][] newSol, int[][] newUserSol, int newDifficulty, Cell[][] newGameCells, long newTime, boolean isNew)
  {
    // sets variables
    this.gamePlayer = newPlayer;
    this.gameMode = newMode;
    this.sol = newSol;
    this.userSol = newUserSol;
    this.difficulty = newDifficulty;
    this.gameCells = newGameCells;
    this.time = newTime;
    
    // sets difficulty
    if(this.difficulty==1)
      this.prefilled = prefillEasy;
    else if(this.difficulty==2)
      this.prefilled = prefillMedium;
    else if(this.difficulty==3)
      this.prefilled = prefillHard;
    
    // sets upt game
    gameboard.setLayout(new GridLayout(9,9,6,6));
    gameboard.setBorder(BorderFactory.createMatteBorder(5,5,5,5,navy));
    gameboard.setPreferredSize(new Dimension(590,590));
    gameboard.setMaximumSize(new Dimension(590,590));   
    
    // sets up game cells
    for(int r = 0; r < gameCells.length; r++)
    {
      for(int c = 0; c < gameCells[r].length; c++)
      {
        if(isNew)
          gameCells[r][c] = new Cell(gameMode);
        if(prefilled[r][c]==true)
        {
          userSol[r][c] = sol[r][c];
          gameCells[r][c].certain = true;
          gameCells[r][c].num = sol[r][c];
          gameCells[r][c].repaint();
        }//end if
        
        gameCells[r][c].setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
        gameCells[r][c].setRolloverEnabled(false);
        gameCells[r][c].setBorder(null);
        gameboard.add(gameCells[r][c]);
      }//end for
    }//end for
    
    gameboard.setOpaque(false);
    
    this.setLayout(new GridLayout(1,1));
    this.add(gameboard);
  }//end (Player , int , int[][], int[][] , int , Cell[][] , long, boolean) constructor
  
  /**
   * (Game) constructor
   * creates a game based off of a previous game
   * @param Game g - the game to copy
   * */
  public Game(Game g)
  {
    this(g.gamePlayer, g.gameMode, g.sol, g.userSol, g.difficulty, g.gameCells, g.time, false);
  }//end (Game) constructor
  
  /**
   * resetCells
   * resets cells after mode changes
   * @param int newMode - the new mode
   * @return void
   * */
  //#instance
  public void resetCells(int newMode)
  {
    // changes mode in each cell
    for(int i = 0; i < gameCells.length; i++)
    {
      for(int j = 0; j < gameCells[i].length; j++)
      {
        gameCells[i][j].cellMode = newMode;
      }//end for
    }//end for
  }//end resetCells
  
  /**
   * checkSolution
   * checks the user's solution
   * @param none
   * @return void
   * */
  //#instance
  public void checkSolution()
  {
    // goes through the solution array
    for(int i = 0; i < sol.length; i++)
    {
      for(int j = 0; j < sol[i].length; j++)
      {
        // if the user is wrong, highlights the box for them
        if(userSol[i][j]!=sol[i][j])
        {
          gameCells[i][j].setBorder(BorderFactory.createLineBorder(inputColours[6], 3));
        }//end if
      }//end for
    }//end for
  }//end checkSolution
  
  /**
   * toString method
   * converts object to string
   * @param none
   * @return String
   * */
  public String toString()
  {
    return "mdoe: " +this.gameMode + " sol: " + Arrays.toString(this.sol[0])+" userSol: " + Arrays.toString(this.userSol[0]) + " time:"+this.time;
  }//end toString
  
  /**
   * showSolution
   * shows the solution in a new window
   * @param none
   * @return void
   * */
  //#instance
  public void showSolution()
  {
    pnlSol.removeAll();
    pnlSol.repaint(); 
    
    // creates object
    //#object
    drawSol = new ShowSolution(gameMode, sol, inputColours, inputLet);
    drawSol.setPreferredSize(new Dimension(260,250));
    pnlSol.add(drawSol);
    
    // adds to panel
    solFrame.add(pnlSol);
    solFrame.pack();
    solFrame.setResizable(false);
    solFrame.setVisible(true);
  }
  
  /**
   * autofill
   * automatically fills the gameboard
   * @param none
   * @return void
   * */
  //#instance
  public void autofill()
  {
    for(int m = 0; m < gameCells.length; m++)
    {
      for(int n = 0; n < gameCells[m].length; n++)
      {
        userSol[m][n] = sol[m][n];
        gameCells[m][n].certain = true;
        gameCells[m][n].num = sol[m][n];        
        gameCells[m][n].repaint();
      }//end for
    }//end for
  }//end autofill

  /**
   * paintComponent
   * paints the board
   * @param Graphics g
   * @return void
   * */  
  //#instance
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    
    // thickens lines
    Graphics2D g2D = (Graphics2D) g;
    g2D.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    
    int h = this.getHeight();
    int w = this.getWidth();
    
    // cell dividers
    g.setColor(new Color(164, 193, 193));
    
    // verticals
    g.drawLine((int)(w*0.114),0,(int)(w*0.114),850);
    g.drawLine((int)(w*0.224),0,(int)(w*0.224),850);
    g.drawLine((int)(w*0.444),0,(int)(w*0.444),850);
    g.drawLine((int)(w*0.554),0,(int)(w*0.554),850);
    g.drawLine((int)(w*0.774),0,(int)(w*0.774),850);
    g.drawLine((int)(w*0.884),0,(int)(w*0.884),850);
    
    //horizontals      
    g.drawLine(0,(int)(w*0.115),850,(int)(w*0.115));
    g.drawLine(0,(int)(w*0.225),850,(int)(w*0.225));
    g.drawLine(0,(int)(w*0.445),850,(int)(w*0.445));
    g.drawLine(0,(int)(w*0.555),850,(int)(w*0.555));
    g.drawLine(0,(int)(w*0.775),850,(int)(w*0.775));
    g.drawLine(0,(int)(w*0.885),850,(int)(w*0.885));
    
    // section dividers 
    g.setColor(navy); 
    
    // verticals      
    g.drawLine((int)(h*0.335),0,(int)(h*0.335),850);
    g.drawLine((int)(w*0.663),0,(int)(w*0.663),850);
    
    // horizontals      
    g.drawLine(0,(int)(h*0.335),850,(int)(h*0.335));
    g.drawLine(0,(int)(h*0.665),850,(int)(h*0.665));
  }//end paintComponent
}//end class Game




