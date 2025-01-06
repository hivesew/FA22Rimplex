package gui;

/**
 * This class helps to Record a sequence of expressions. 
 * @author F22TeamA
 */

public class Recording
{

  private static String contents = "";
  private static boolean recording = false;
  
  /**
   * Starts the recording.
   */
  
  public static void start()
  {
    if (!recording)
    {
      clearContents();
    }
    recording = true;
  }
  
  /**
   * Pauses the recording.
   * @param s the contents of display before pausing
   */
  
  public static void pause(final String s)
  {
    recording = true;
  }
  
  /**
   * Stops the recording.
   */
  
  public static void stop()
  {
    clearContents();
    recording = false;
  }
  
  /**
   * Adds the content from the Display to the recording.
   * @param s the string to add to the recording
   */
  
  public static void add(final String s)
  {
    contents+="\n" + s;
    System.out.println(contents);
  }

  /**
   * Returns the current contents of the recording.
   * @return the contents of the recording
   */
  
  public static String getContents()
  {
    return contents;
  }
  
  /**
   * Clears the contents of the Recording.
   */
  
  private static void clearContents()
  {
    contents = "";
  }
  
}
