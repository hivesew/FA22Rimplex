package gui;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

/**
 * History manages the display of equations that a user runs into an extendable window.
 * @author FATeam22A
 */

public class History implements Printable
{
  public static JTextArea history;

  /**
   * History constructor.
   */

  public History()
  {
    this.setHistory(new JTextArea());
    this.getHistory().setEditable(false);
  }

  /**
   * getHistory.
   * 
   * @return the history text.
   */
  public JTextArea getHistory()
  {
    return history;
  }

  /**
   * setHistory takes input JTextArea and sets a new history.
   * 
   * @param history
   */

  public void setHistory(final JTextArea history)
  {
    History.history = history;
    history.setFocusable(false);
    history.setBackground(RimplexWindow.scheme);
  }

  /**
   * 
   * Prints a page containing the text from the history of the current document.
   * 
   * @param graphics
   *          the graphics context to use for printing
   * @param pageFormat
   *          the page format to use
   * @param pageIndex
   *          the index of the page to print (zero-based)
   * @return int
   */

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {
    if (pageIndex > 0)
    {
      return NO_SUCH_PAGE;
    }
    graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
    graphics.drawString(this.getHistory().getText(), 100, 100);
    return PAGE_EXISTS;
  }
}
