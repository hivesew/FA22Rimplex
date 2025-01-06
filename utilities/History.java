package utilities;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;

/**
 * This class controls the History of the RimplexWindow. It allows for the History to be printed.
 * 
 * @author FA22TeamA
 */

public class History implements Printable
{
  public static JTextArea history;

  /**
   * The History constructor.
   */
  public History()
  {
    this.setHistory(new JTextArea());
    this.getHistory().setEditable(false);
  }

  /**
   * Gets the History JTextArea.
   * 
   * @return the History JTextArea
   */

  public JTextArea getHistory()
  {
    return history;
  }

  /**
   * Sets the History JTextArea.
   * 
   * @param history
   *          the JTextArea to set the History to
   */
  public void setHistory(final JTextArea history)

  {
    History.history = history;
  }

  @Override
  public int print(final Graphics graphics, final PageFormat pageFormat, final int pageIndex)
      throws PrinterException
  {

    if (pageIndex > 0)

      if (pageIndex > 0)

      {
        return NO_SUCH_PAGE;
      }
    graphics.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
    graphics.drawString(this.getHistory().getText(), 100, 100);
    return PAGE_EXISTS;
  }
}
