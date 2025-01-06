package gui;
import java.io.*;

/**
 * This class creates a thread that plays back a recorded set of expressions.
 * @author F22TeamA
 */

public class PlayBack extends Thread
{

  private int currentIndex = 0;
  private boolean exit = false;
  private RimplexWindow owner;
  
  /**
   * Constructs the PlayBack thread.
   * @param owner the specific RimplexWindow that owns the thread
   */
  
  public PlayBack(final RimplexWindow owner)
  {
    super();
    this.owner = owner;
  }
  
  /**
   * Starts the playback.
   * @param f the file being read
   * @throws IOException if the readers find an issue
   * @throws InterruptedException if the thread gets interrupted
   */
  
  public void play(final File f) throws IOException, InterruptedException
  {
    FileReader reader = new FileReader(f);
    BufferedReader buffReader = new BufferedReader(reader);
    buffReader.skip(currentIndex); // skips to the current index for pause/play
    // TODO reset to current line
    int i = buffReader.read(); // reads the char as an int
    while (i != -1 && !exit) // make sure there are still chars too read and that we aren't paused
    {
      char c = (char) i; // convert the int into its char
      i = buffReader.read(); // move to the next char
      if (c == '=') // if the char is =, evaluate the expression
      {
        owner.evaluateExpression(owner.getDisplayText());
        Thread.sleep(1000);
      } else if (c != '\n') 
      {
        owner.appendString(c + "");
        System.out.print(c); //TODO remove after debugging
      } else if (c == '\n')
      {
        owner.hardReset();
        System.out.println(); //TODO remove after debugging
      }
      Thread.sleep(150);
      currentIndex++;
    }
    
    if (i == -1) // when done reading, reset currentIndex & RimplexWindow
    {
      currentIndex = 0;
      owner.playbackEnded();
    }
    buffReader.close();
  }
  
  /**
   * Pauses the playback.
   */
  
  public void pause()
  {
    exit = true;
  }

  @Override
  public void run()
  {
    try
    {
      exit = false;
      play(owner.getFile());
    }
    catch (IOException | InterruptedException e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Resets the various flag variables for the playback.
   */
  
  public void reset()
  {
    currentIndex = 0;
    exit = false;
  }

}
