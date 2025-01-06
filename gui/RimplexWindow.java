package gui;

import static gui.LanguageSelectScreen.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.print.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Path;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import utilities.ComplexNumber;
import utilities.Graphing;
import utilities.Preferences;
import utilities.ResourceCopier;

/**
 * Rimplex Window.
 *
 * @author F22TeamA
 * @version 1.0
 */

public class RimplexWindow extends JFrame
    implements ActionListener, LanguageChangeObserver, KeyListener
{

  public static Preferences preferences;
  
  static Color scheme;
  
  private static final long serialVersionUID = 1L;
  private static final String NEW = "NEW";
  private static final String USER_MANUAL = "USER_MANUAL";
  private static final String PLUS_SYMBOL = "+";
  private static final String SUBTRACT_SYMBOL = "-";
  private static final String MULTIPLY_SYMBOL = "x";
  private static final String DIVIDE_SYMBOL = "\u00f7";
  private static final String EQUAL_SYMBOL = "=";
  private static final String CLEAR_SYMBOL = "C";
  private static final String RESET_SYMBOL = "R";
  private static final String OPEN_PARENTHESES = "(";
  private static final String CLOSE_PARENTHESES = ")";
  private static final String I_SYMBOL = "i";
  private static final String PLUS_OR_MINUS = "\u00B1";
  private static final String EXIT = "EXIT";
  private static final String PRINT = "PRINT";
  private static final String HELP = "HELP";
  private static final String ABOUT = "ABOUT";
  private static final String FILE = "FILE";
  private static final String PREFERENCES = "PREFERENCES";
  private static final String BACKSPACE = "\u2190";
  private static final String DECIMAL = ".";
  private static final String CONJUGATE = "z*";
  private static final String LOGARITHM = "log";
  private static final String SQRT = "sqrt";
  private static final String INVERSE = "Inv";
  private static final String POWER = "^";
  private static final String EXPONENT = "exp";
  private static final String REAL_PART_OP = "Re";
  private static final String IMAGINARY_PART_OP = "Imag";
  private static final String SPACE = " ";
  private static final String NEWLINE = "\n";
  private static final String SETTINGS = "SETTINGS";
  private static final String LANGUAGE = "LANGUAGE";
  private static final String GRAPH = "GRAPH";
  private static final String POLAR = "POLAR";
  private static final String OPEN = "OPEN";
  private static final String PLAY = "PLAY";
  private static final String PAUSE = "PAUSE";
  private static final String START = "START";
  private static final String PAUSERECORD = "PAUSE_RECORDING"; // TODO need to change
  private static final String STOP = "STOP";
  private static final String RESUME = "RESUME";
  private static final String PLAYBACK = "PLAYBACK";
  private static final String RECORD = "RECORD";
  private static final String ACCEPTABLE_CHARS = "0123456789.()i";
  private static final String ACCEPTABLE_OPERATORS = "+-x\\u00f7";
  private static final String RECORDINGS_PATH = "src/recordings"; // TODO need to change
  
  private JTextPane display = new Display();
  private JLayeredPane layeredPane = new JLayeredPane();
  private ComplexNumber result = null;

  private JLabel imagePanel = new JLabel();

  private JPanel panel = new JPanel();
  private int flag = 1;
  private boolean visGraph = false;
  private boolean polarForm = false;
  private boolean recording = false;
  private boolean playbacking = false;
  private ImageIcon icon;
  private JMenuBar menuBar = new JMenuBar();
  private JMenu fileMenu = new JMenu(STRINGS.getString(FILE));
  private JMenuItem itemPrint = new JMenuItem(STRINGS.getString(PRINT));
  private JMenuItem itemExit = new JMenuItem(STRINGS.getString(EXIT));
  // TODO add preferences to String file.
  private JMenuItem itemPreferences = new JMenuItem(STRINGS.getString(PREFERENCES));
  private JMenu settingsMenu = new JMenu(STRINGS.getString(SETTINGS));
  private JMenuItem langSel = new JMenuItem(STRINGS.getString(LANGUAGE));
  private JMenu helpMenu = new JMenu(STRINGS.getString(HELP));
  private JMenuItem helpAbout = new JMenuItem(STRINGS.getString(ABOUT));
  private JMenu playbackMenu = new JMenu(STRINGS.getString(PLAYBACK));
  private JMenuItem playbackOpen = new JMenuItem(STRINGS.getString(OPEN));
  private JMenuItem playbackPlay = new JMenuItem(STRINGS.getString(PLAY));
  private JMenuItem playbackPause = new JMenuItem(STRINGS.getString(PAUSE));
  private JMenu recordMenu = new JMenu(STRINGS.getString(RECORD));
  private JMenuItem recordStart = new JMenuItem(STRINGS.getString(START));
  private JMenuItem recordPause = new JMenuItem(STRINGS.getString(PAUSERECORD));
  private JMenuItem recordStop = new JMenuItem(STRINGS.getString(STOP));
  private JMenuItem newWindow = new JMenuItem(STRINGS.getString(NEW));
  private JMenuItem openManual = new JMenuItem(STRINGS.getString(USER_MANUAL));
  private JButton graphing;
  private JButton polar;
  private File playbackFile;
  private String save = "";
  
  private HistoryPanel historyPanel = new HistoryPanel();
  private JPanel buttonsPanel;
  private JPanel graphPanel;
  private RimplexAboutDialog aboutDialog = new RimplexAboutDialog(this);
  private JFileChooser playbackChooser;
  private PlayBack playback;

  
  /**
   * Default constructor.
   */

  public RimplexWindow()
  {
    super();
    setupLayout();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    try (InputStream prefIn = new FileInputStream("preferences.ser")) 
    {
      preferences = (Preferences) new ObjectInputStream(prefIn).readObject();
    } catch (IOException | ClassNotFoundException e) 
    {
      preferences = new Preferences();
    }
    pack();

    setResizable(false);
    setTitle("Rimplex");
    InputStream imageInputStream = this.getClass().getResourceAsStream("/iconRimplex.png");

    BufferedImage bufferedImage;

    try
    {
      bufferedImage = ImageIO.read(imageInputStream);
      this.setIconImage(bufferedImage);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    setVisible(true);
  }

  /**
   * Set up the layout for the GUI.
   */
  private void setupLayout()
  {
    Container contentPane;

    // Place buttons and text areas on display.
    contentPane = getContentPane();
    contentPane.setFocusable(false);
    contentPane.setLayout(new BorderLayout());

    // contentPane.setBackground(Color.GRAY);
    createButtonsPanel();

    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    historyPanel.setFocusable(false);
    menuBar.setFocusable(false);
    aboutDialog.setFocusable(false);
    langSel.setFocusable(false);
    fileMenu.setFocusable(false);
    itemPrint.setFocusable(false);
    itemExit.setFocusable(false);
    settingsMenu.setFocusable(false);
    helpMenu.setFocusable(false);
    helpAbout.setFocusable(false);
    layeredPane.setFocusable(false);
    display.setFocusable(false);

    InputStream imageInputStream2 = this.getClass().getResourceAsStream("/logo.png");
    try
    {
      icon = new ImageIcon(ImageIO.read((imageInputStream2)));
      imagePanel.setIcon(icon);

    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    String text = "";
    try 
    {
      InputStream in = getClass().getResourceAsStream("/colorconfig.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      text = br.readLine();
      br.close();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException ioe)
    {
      
    }
    String[] rgb = text.split(SPACE);
    int r, g, b;
    r = Integer.parseInt(rgb[0]);
    g = Integer.parseInt(rgb[1]);
    b = Integer.parseInt(rgb[2]);
    scheme = new Color(r, g, b);
    
    panel.setPreferredSize(new Dimension(600, 400));
    panel.setLayout(new BorderLayout());
    panel.add(display, BorderLayout.NORTH);
    panel.add(buttonsPanel, BorderLayout.CENTER);
    panel.setFocusable(false);
    buttonsPanel.setFocusable(false);
    panel.setBorder(new LineBorder(scheme, 2));

    // Create a layered pane to hold both the history tab and the Calculator GUI.
    layeredPane.setPreferredSize(new Dimension(800, 400));
    layeredPane.add(historyPanel, JLayeredPane.DEFAULT_LAYER);
    layeredPane.add(panel, JLayeredPane.PALETTE_LAYER);
    panel.setSize(new Dimension(400, 400));
    historyPanel.setSize(new Dimension(400, 400));
    historyPanel.setLocation(30, 0);
    historyPanel.setBackground(scheme);
    layeredPane.setVisible(true);

    graphPanel = new JPanel();
    graphPanel.setLayout(new BorderLayout());
    graphPanel.setBorder(BorderFactory.createTitledBorder("Complex Plane Visualization "));
    Dimension graphDim = graphPanel.getPreferredSize();
    int graphWidth = graphDim.width;
    int graphHeight = graphDim.height;
    Dimension graphSize = new Dimension(graphWidth + 272, graphHeight + 272);
    graphPanel.setPreferredSize(graphSize);
    graphPanel.setVisible(visGraph);
    contentPane.add(graphPanel, BorderLayout.WEST);
    contentPane.add(layeredPane, BorderLayout.CENTER);

    itemExit.addActionListener(this);
    itemPreferences.addActionListener(this);
    itemPrint.addActionListener(this);
    // playback menu items
    playbackOpen.addActionListener(this);
    playbackPlay.addActionListener(this);
    playbackPause.addActionListener(this);
    playbackPlay.setEnabled(false);
    playbackPause.setEnabled(false);
    playbackMenu.add(playbackOpen);
    playbackMenu.add(playbackPlay);
    playbackMenu.add(playbackPause);
    // record menu items
    recordStart.addActionListener(this);
    recordPause.addActionListener(this);
    recordStop.addActionListener(this);
    recordPause.setEnabled(false);
    recordStop.setEnabled(false);
    recordMenu.add(recordStart);
    recordMenu.add(recordPause);
    recordMenu.add(recordStop);
    newWindow.addActionListener(this);

    openManual.addActionListener(this);
    helpMenu.add(openManual);

    fileMenu.add(newWindow);
    fileMenu.add(itemPreferences);
    fileMenu.add(playbackMenu);
    fileMenu.add(recordMenu);
    fileMenu.add(itemPrint);
    fileMenu.add(itemExit);
    menuBar.add(fileMenu);

    langSel.addActionListener(this);
    settingsMenu.add(langSel);
    menuBar.add(settingsMenu);

    helpAbout.addActionListener(this);
    helpMenu.add(helpAbout);
    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    panel.add(imagePanel, BorderLayout.SOUTH);
  }

  /**
   * Create the panel that will hold the buttons.
   */

  private void createButtonsPanel()
  {
    buttonsPanel = new JPanel();
    GridLayout grid = new GridLayout(6, 6, 10, 10);
    buttonsPanel.setLayout(grid);
    buttonsPanel.setBorder(null);

    // Create buttons.
    JButton reset = createButton(RESET_SYMBOL);
    JButton real = createButton(REAL_PART_OP);
    JButton imaginary = createButton(IMAGINARY_PART_OP);
    JButton exponent = createButton(EXPONENT);
    JButton conjugate = createButton(CONJUGATE);
    JButton logarithm = createButton(LOGARITHM);
    JButton sqrt = createButton(SQRT);
    JButton inverse = createButton(INVERSE);
    JButton power = createButton(POWER);
    JButton clear = createButton(CLEAR_SYMBOL);
    JButton decimal = createButton(DECIMAL);
    JButton backspace = createButton(BACKSPACE);
    JButton plus = createButton(PLUS_SYMBOL);
    JButton minus = createButton(SUBTRACT_SYMBOL);
    JButton divide = createButton(DIVIDE_SYMBOL);
    JButton multiply = createButton(MULTIPLY_SYMBOL);
    JButton equals = createButton(EQUAL_SYMBOL);
    JButton iSymbol = createButton(I_SYMBOL);
    graphing = createButton(STRINGS.getString(GRAPH));
    polar = createButton(STRINGS.getString(POLAR));
    // commented out code JButton plusOrMinus = createButton(PLUS_OR_MINUS);
    JButton openParentheses = createButton(OPEN_PARENTHESES);
    JButton closeParentheses = createButton(CLOSE_PARENTHESES);
    JButton[] numbers = new JButton[10];
    for (int i = 0; i < 10; i++)
    {
      numbers[i] = createButton("" + i);
    }

    // Change colors of Reset and Clear.
    Color buttonTextColor = new Color(239, 199, 0);
    backspace.setForeground(buttonTextColor);
    reset.setForeground(Color.blue);
    clear.setForeground(buttonTextColor);

    // Place buttons on panel
    // buttonsPanel.setBackground(GREY);
    // buttonsPanel.add(plusOrMinus);
    buttonsPanel.add(clear);
    buttonsPanel.add(backspace);
    buttonsPanel.add(reset);
    buttonsPanel.add(plus);
    buttonsPanel.add(power);
    buttonsPanel.add(real);
    buttonsPanel.add(numbers[7]);
    buttonsPanel.add(numbers[8]);
    buttonsPanel.add(numbers[9]);
    buttonsPanel.add(minus);
    buttonsPanel.add(inverse);
    buttonsPanel.add(imaginary);
    buttonsPanel.add(numbers[4]);
    buttonsPanel.add(numbers[5]);
    buttonsPanel.add(numbers[6]);
    buttonsPanel.add(multiply);
    buttonsPanel.add(openParentheses);
    buttonsPanel.add(conjugate);
    buttonsPanel.add(numbers[1]);
    buttonsPanel.add(numbers[2]);
    buttonsPanel.add(numbers[3]);
    buttonsPanel.add(divide);
    buttonsPanel.add(closeParentheses);
    buttonsPanel.add(logarithm);
    buttonsPanel.add(iSymbol);
    buttonsPanel.add(numbers[0]);
    buttonsPanel.add(equals);
    buttonsPanel.add(decimal);
    buttonsPanel.add(sqrt);
    buttonsPanel.add(exponent);
    buttonsPanel.add(graphing);
    buttonsPanel.add(polar);
  }

  /**
   * Creates a JButton with the correct label and adds an ActionListener.
   *
   * @param buttonLabel
   *          the label of the button
   * @return the JButton
   */

  private JButton createButton(final String buttonLabel)
  {
    JButton item = new JButton(buttonLabel);
    item.setBorder(new LineBorder(Color.BLACK));
    item.addActionListener(this);
    item.setFocusable(false);
    item.setBorder(null);
    item.setBackground(new Color(160, 160, 160));
    return item;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ae = e.getActionCommand();

    if (ae.equals(STRINGS.getString(USER_MANUAL)))
    {

      URI uri;

      try
      {
        Path p = ResourceCopier.copyResourcesToTemp("rimplex", "html");

        Desktop desktop = Desktop.getDesktop();
        uri = p.resolve("index.html").toUri();
        desktop.browse(uri);

      }
      catch (URISyntaxException e1)
      {
        // TODO Auto-generated catch block

        e1.printStackTrace();
      }

      catch (IOException e1)
      {

        // TODO Auto-generated catch block
        e1.printStackTrace();
      }

    }

    if (ae.equals(STRINGS.getString(NEW)))
    {
      new RimplexWindow();
    }

    if (ae.equals(STRINGS.getString(PRINT)))
    {
      // print the contents of the JTextArea inside of the History object.
      try
      {
        History.history.print();
      }
      catch (PrinterException ex)
      {
        throw new RuntimeException(ex);
      }
    }
    // TODO Preferences to STRINGS file.
    if (ae.equals(STRINGS.getString(PREFERENCES))) 
    {
      new PreferencesWindow(this);
    }
    if (ae.equals(STRINGS.getString(LANGUAGE)))
    {
      new LanguageSelectScreen(this);
    }

    if (ae.equals(STRINGS.getString(ABOUT)))
    {
      aboutDialog.setVisible(true);
    }

    if (ae.equals(STRINGS.getString(EXIT)))

    {

      WindowEvent closingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
      Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);

    }
    if (!playbacking) // disables all numberpad buttons during playbacking
    {
      if (ae.equals(BACKSPACE))
      {
        backspaceAction();
      }
      if (ae.equals(STRINGS.getString(GRAPH)))
      {
        graphComplex();
      }
      if (ae.equals(STRINGS.getString(POLAR)))
      {
        polarForm = !polarForm;
      }
      if (ae.equals(PLUS_SYMBOL))
      {
        appendOperator(PLUS_SYMBOL);
      }
      if (ae.equals(SUBTRACT_SYMBOL))
      {
        appendOperator(SUBTRACT_SYMBOL);
      }
      if (ae.equals(MULTIPLY_SYMBOL))
      {
        appendOperator(MULTIPLY_SYMBOL);
      }
      if (ae.equals(DIVIDE_SYMBOL))
      {
        appendOperator(DIVIDE_SYMBOL);
      }
      if (ae.equals(POWER))
      {
        appendOperator(POWER);
      }
      if (ae.equals(LOGARITHM))
      {
        appendOperator(LOGARITHM);
      }
      if (ae.equals(SQRT))
      {
        appendOperator(SQRT);
      }
      if (ae.equals(CONJUGATE))
      {
        appendOperator(CONJUGATE);
      }
      if (ae.equals(INVERSE))
      {
        appendOperator(INVERSE);
      }
      if (ae.equals(EXPONENT))
      {
        appendOperator(EXPONENT);
      }
      if (ae.equals(REAL_PART_OP))
      {
        appendOperator(REAL_PART_OP);
      }
      if (ae.equals(IMAGINARY_PART_OP))
      {
        appendOperator(IMAGINARY_PART_OP);
      }
      if (ae.equals(CLEAR_SYMBOL))
      {
        if (display.getText().indexOf(SPACE) == -1)
        {
          display.setText("");
        }
        else if (display.getText().lastIndexOf(SPACE) != -1)
        {
          display.setText(display.getText().substring(0, display.getText().lastIndexOf(SPACE) + 1));
        }
      }
      if (ae.equals(RESET_SYMBOL))
      {
        hardReset();
      }
      if (ae.equals(EQUAL_SYMBOL))
      {
        if (recording)
        {
          Recording.add(display.getText() + EQUAL_SYMBOL);
        }
        int lastEqualsIndex = this.display.getText().lastIndexOf(EQUAL_SYMBOL);
        if (lastEqualsIndex != -1)
        {
          evaluateExpression(this.display.getText().substring(lastEqualsIndex + 1));
          if (polarForm)
          {
            int index = display.getText().lastIndexOf(EQUAL_SYMBOL);
            ComplexNumber norm;
            norm = ComplexNumber.parseComplexNumber(display.getText().substring(index + 1));
            String polarString = this.display.getText().substring(0, index + 1);
            polarString += norm.polarToString();
            this.display.setText(polarString);
          }
        }
        else
        {
          evaluateExpression(this.display.getText());
          if (polarForm)
          {
            int index = display.getText().lastIndexOf(EQUAL_SYMBOL);
            ComplexNumber norm;
            norm = ComplexNumber.parseComplexNumber(display.getText().substring(index + 1));
            String polarString = this.display.getText().substring(0, index + 1);
            polarString += norm.polarToString();
            this.display.setText(polarString);
          }
        }
      }
      for (int i = 0; i < 10; i++)
      {
        if (ae.equals("" + i))
        {
          appendString("" + i);
        }
      }
      if (ae.equals(I_SYMBOL))
      {
        appendString(I_SYMBOL);
      }
      if (ae.equals(OPEN_PARENTHESES))
      {
        appendString(OPEN_PARENTHESES);
      }
      if (ae.equals(CLOSE_PARENTHESES))
      {
        appendString(CLOSE_PARENTHESES);
      }
      if (ae.equals(PLUS_OR_MINUS))
      {
        if (display.getText().indexOf(SPACE) == -1)
        {
          if (display.getText().indexOf(OPEN_PARENTHESES) == -1)
          {
            if (flag == 1)
            {
              this.display.setText(SUBTRACT_SYMBOL + this.display.getText());
              flag = -1;
            }
            else if (flag == -1)
            {
              String removeNeg = display.getText().substring(1);
              display.setText(removeNeg);
              flag = 1;
            }
          }
          else
          {
            if (flag == 1)
            {
              this.display.setText(OPEN_PARENTHESES + SUBTRACT_SYMBOL 
                  + this.display.getText().substring(1));
              flag = -1;
            }
            else if (flag == -1)
            {
              String removeNeg = display.getText().substring(2);
              display.setText(OPEN_PARENTHESES + removeNeg);
              flag = 1;
            }
          }
        }
        else if (display.getText().indexOf(EQUAL_SYMBOL) == -1)
        {
          int lastSpaceIndex = display.getText().lastIndexOf(SPACE) + 1;
          String beforeLastOperand = display.getText().substring(0, lastSpaceIndex);
          if (display.getText().lastIndexOf(OPEN_PARENTHESES) == -1)
          {
            if (flag == 1)
            {
              this.display.setText(beforeLastOperand + SUBTRACT_SYMBOL
                  + this.display.getText().substring(lastSpaceIndex));
              flag = -1;
            }
            else if (flag == -1)
            {
              String removeNeg = display.getText().substring(lastSpaceIndex + 1);
              display.setText(beforeLastOperand + removeNeg);
              flag = 1;
            }
          }
          else
          {
            if (flag == 1)
            {
              this.display.setText(beforeLastOperand + OPEN_PARENTHESES + SUBTRACT_SYMBOL
                  + this.display.getText().substring(lastSpaceIndex + 1));
              flag = -1;
            }
            else if (flag == -1)
            {
              String removeNeg = display.getText().substring(lastSpaceIndex + 2);
              display.setText(beforeLastOperand + OPEN_PARENTHESES + removeNeg);
              flag = 1;
            }
          }
        }
      }
      if (ae.equals(DECIMAL))
      {
        appendString(DECIMAL);
      }
    }
    if (ae.equals(STRINGS.getString(OPEN)))
    {
      playbackChooser = new JFileChooser();
      // TODO directory needs to change for .jar file
      playbackChooser.setCurrentDirectory(new File(RECORDINGS_PATH));
      // TODO restrict to only this directory
      int option = playbackChooser.showOpenDialog(this);
      if (option == JFileChooser.APPROVE_OPTION)
      {
        playbackFile = playbackChooser.getSelectedFile();
        playbackPlay.setEnabled(true);
        if (playback != null)
        {
          playback.reset();
        }
      }
    }
    if (ae.equals(STRINGS.getString(PLAY)))
    {
      playback = new PlayBack(this);
      playbacking = true;
      playbackOpen.setEnabled(false);
      playbackPlay.setEnabled(false);
      playbackPause.setEnabled(true);
      display.setText(Recording.getContents());
      playback.start();
    }
    if (ae.equals(STRINGS.getString(PAUSE)))
    {
      playbackOpen.setEnabled(true);
      playbackPlay.setEnabled(true);
      playbackPause.setEnabled(false);
      playback.pause();
      playbacking = false;
    }
    if (ae.equals(STRINGS.getString(START)))
    {
      Recording.start();
      hardReset();
      recording = true;
      recordStart.setText(STRINGS.getString(RESUME));
      recordStart.setEnabled(false);
      recordPause.setEnabled(true);
      recordStop.setEnabled(true);
    }
    if (ae.equals(STRINGS.getString(RESUME)))
    {
      Recording.start();
      resume();
      recordStart.setEnabled(false);
      recordPause.setEnabled(true);
      recordStop.setEnabled(true);
    }
    if (ae.equals(STRINGS.getString(PAUSERECORD)))
    {
      pause();
      recordStart.setEnabled(true);
      recordPause.setEnabled(false);
      recordStop.setEnabled(false); // maybe?
    }
    if (ae.equals(STRINGS.getString(STOP)))
    {
      JFileChooser jfc = new JFileChooser();
      // TODO directory needs to change for .jar file
      jfc.setCurrentDirectory(new File(RECORDINGS_PATH));
      int option = jfc.showSaveDialog(this);
      if (option == JFileChooser.APPROVE_OPTION)
      {
        try
        {
          FileWriter fw = new FileWriter(jfc.getSelectedFile()+".txt");
          fw.write(Recording.getContents().toString());
          fw.close();
        }
        catch (IOException e1)
        {
          e1.printStackTrace();
        }
      }
      recording = false;
      Recording.stop();
      recordStart.setText(STRINGS.getString(START));
      recordStart.setEnabled(true);
      recordPause.setEnabled(false);
      recordStop.setEnabled(false);
    }
  }

  /**
   * 
   */
  public void graphComplex()
  {
    if (graphPanel.isVisible())
    {
      graphPanel.setVisible(false);
      graphPanel.removeAll();
      visGraph = false;
      return;
    }
    else
    {
      // attempt to graph if the display holds a valid complex number
      ComplexNumber toGraph;
      try
      {
        toGraph = ComplexNumber.parseComplexNumber(display.getText());
      }
      catch (NumberFormatException nfe)
      {
        System.out.println("Invalid input");
        return;
      }
      System.out.println("Graphing: " + toGraph);
      Dimension graphDims = graphPanel.getPreferredSize();
      int graphWidth = graphDims.width;
      Graphing g = new Graphing(toGraph, scheme, graphWidth, graphWidth);
      this.graphPanel.add(g);
      graphPanel.setVisible(true);

      visGraph = true;
    }

  }

  /**
   * Resets the entire system.
   */
  public void hardReset()
  {
    result = null;
    display.setText("");
  }

  /**
   * Evaluates the expressions after the equal button is clicked.
   * @param expr string to evaluate
   */
  public void evaluateExpression(final String expr)
  {
    int eqCount = 0;
    String stripped = expr.stripLeading();
    result = ComplexNumber.evaluateExpressionString(stripped);
    if (result == null && display.getText().length() != 0)
    {
      display.setText(display.getText() + EQUAL_SYMBOL + "UNDEFINED");
      String temp = display.getText();
      String formatted = temp.substring(temp.indexOf(EQUAL_SYMBOL + 1));
      historyPanel.historyArea.getHistory().append(formatted + EQUAL_SYMBOL + "UNDEFINED\n");
      result = null;
    }
    else
    {
      display.setText(display.getText() + EQUAL_SYMBOL + result.toString());
      String temp = display.getText();
      for (int i = 0; i < temp.length(); i++)
      {
        if (temp.charAt(i) == '=')
        {
          eqCount++;
        }
      }
      if (eqCount > 1)
      {
        String formatted = temp.substring(temp.indexOf(EQUAL_SYMBOL) + 1);
        historyPanel.historyArea.getHistory().append(formatted + NEWLINE);
        display.setText(formatted);

      }
      else
      {
        if (polarForm)
        {
          historyPanel.historyArea.getHistory().append(
              temp.substring(0, temp.indexOf(EQUAL_SYMBOL) + 1) + result.polarToString() + NEWLINE);
        }
        else
        {
          historyPanel.historyArea.getHistory().append(temp + NEWLINE);
          display.setText(temp);
        }

      }
    }
    flag = 1;
  }

  /**
   * Appends an operator to the display in the correct format for parsing.
   * @param op the operator being appended
   */
  private void appendOperator(final String op)
  {
    if (result != null) // running calculations
    {
      display.setText(result.toString());
      result = null;
    }
    String expression = this.display.getText();
    // check if the expression string is within a parenthesized complex number
    boolean withinParenthesis = false;
    int openParenthesisCount = 0;
    int closeParenthesisCount = 0;
    for (int i = 0; i < expression.length(); i++)
    {
      if (expression.charAt(i) == '(')
      {
        openParenthesisCount++;
      }
      else if (expression.charAt(i) == ')')
      {
        closeParenthesisCount++;
      }
    }
    if (openParenthesisCount > closeParenthesisCount)
    {
      withinParenthesis = true;
    }
    if (withinParenthesis)
    {
      this.display.setText(expression + op);
    }
    else
    {
      this.display.setText(expression + SPACE + op + SPACE);
    }
    flag = 1;
  }

  /**
   * Setter method to add string to display.
   *
   * @param s
   *          String to add
   */
  public void appendString(final String s)
  {
    this.display.setText(this.display.getText() + s);
  }

  /**
   * S.
   * 
   * @return s
   */
  public RimplexAboutDialog getAboutDialog()
  {
    return this.aboutDialog;
  }

  @Override
  public void changeLanguage()
  {
    fileMenu.setText(STRINGS.getString(FILE));
    itemPrint.setText(STRINGS.getString(PRINT));
    itemExit.setText(STRINGS.getString(EXIT));
    settingsMenu.setText(STRINGS.getString(SETTINGS));
    langSel.setText(STRINGS.getString(LANGUAGE));
    helpMenu.setText(STRINGS.getString(HELP));
    helpAbout.setText(STRINGS.getString(ABOUT));
    itemPreferences.setText(STRINGS.getString(PREFERENCES));
    playbackMenu.setText(STRINGS.getString(PLAYBACK));
    playbackOpen.setText(STRINGS.getString(OPEN));
    playbackPlay.setText(STRINGS.getString(PLAY));
    playbackPause.setText(STRINGS.getString(PAUSE));
    recordMenu.setText(STRINGS.getString(RECORD));
    recordStart.setText(STRINGS.getString(START));
    recordPause.setText(STRINGS.getString(PAUSERECORD));
    recordStop.setText(STRINGS.getString(STOP));
    newWindow.setText(STRINGS.getString(NEW));
    openManual.setText(STRINGS.getString(USER_MANUAL));
    polar.setText(STRINGS.getString(POLAR));
    graphing.setText(STRINGS.getString(GRAPH));
  }

  private void backspaceAction()
  {
    if (display.getText().length() != 0)
    {
      if (display.getText().charAt(display.getText().length() - 1) == ' ')
      {
        display.setText(display.getText().substring(0, display.getText().length() - 3));
      } else if (display.getText().charAt(display.getText().length() - 1) == 'D')
      {
        display.setText(display.getText().substring(0, display.getText().length() - 9));
      } else
      {
        if (display.getText().charAt(display.getText().length() - 1) == '=')
        {
          result = null;
        }
        display.setText(display.getText().substring(0, display.getText().length() - 1));     
      }
    }
  }

  /**
   * Returns the current playback file of the RimplexWindow.
   * 
   * @return the current playback file
   */
  public File getFile()
  {
    return this.playbackFile;
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    // does nothing
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    // does nothing
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    char ke = e.getKeyChar();
    if (!playbacking)
    {
      if (ACCEPTABLE_CHARS.indexOf(ke) != -1)
      {
        appendString("" + ke);
      }
      else if (ACCEPTABLE_OPERATORS.indexOf(ke) != -1)
      {
        appendOperator("" + ke);
      }
      else if (ke == KeyEvent.VK_SLASH)
      {
        appendOperator(DIVIDE_SYMBOL);
      }
      else if (ke == '=' || ke == KeyEvent.VK_ENTER)
      {
        int lastEqualsIndex = this.display.getText().lastIndexOf(EQUAL_SYMBOL);
        if (lastEqualsIndex != -1)
        {
          evaluateExpression(this.display.getText().substring(lastEqualsIndex + 1));
        }
        else
        {
          evaluateExpression(this.display.getText());
        }
      }
      else if (ke == KeyEvent.VK_BACK_SPACE)
      {
        backspaceAction();
      }
    }
  }

  /**
   * Returns the current text in the Display.
   * 
   * @return the text in the Display
   */

  public String getDisplayText()
  {
    return this.display.getText();
  }

  /**
   * Resets the buttons for playback.
   */
  public void playbackEnded()
  {
    playbackOpen.setEnabled(true);
    playbackPlay.setEnabled(true);
    playbackPause.setEnabled(false);
    playbacking = false;
  }
  
  /**
   * Pauses the recording.
   */
  private void pause()
  {
    save = this.display.getText();
    recording = false;
  }
  
  /**
   * Resumes the recording.
   */
  private void resume()
  {
    this.display.setText(save);
    save = "";
    recording = true;
  }
  

}
