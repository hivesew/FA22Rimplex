package rimplex;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.RimplexWindow;

/**
 * Runs the Rimplex application.
 * 
 * @author F22TeamA
 */

public class Rimplex implements Runnable
{

  /**
   * Attempts to start the Rimplex application.
   * 
   * @param args
   */

  public static void main(final String[] args)
  {
    try
    {
      SwingUtilities.invokeAndWait(new Rimplex());
    }
    catch (InterruptedException | InvocationTargetException e)
    {
      System.out.println("Unable to start the GUI.");
    }
  }

  // Test comment. V2
  private void setLookAndFeel()
  {
    // Setup the look and feel
    boolean done = false;
    try
    {
      // Use Nimbus if it is available
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (int i = 0; i < lfs.length && !done; i++)
      {
        if ("Nimbus".equals(lfs[i].getName()))
        {
          UIManager.setLookAndFeel(lfs[i].getClassName());
          done = true;
        }
      }

      // If Nimbus isn't available, use the system look and feel
      if (!done)
      {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
      }
    }
    catch (ClassNotFoundException cnfe)
    {
      // Use the default look and feel
    }
    catch (IllegalAccessException iae)
    {
      // Use the default look and feel
    }
    catch (InstantiationException ie)
    {
      // Use the default look and feel
    }
    catch (UnsupportedLookAndFeelException ulale)
    {
      // Use the default look and feel
    }
  }

  /**
   * Sets up the look and feel and starts the CalculatorGUI.
   */
  public void run()
  {
    setLookAndFeel();
    new RimplexWindow();
  }
}
