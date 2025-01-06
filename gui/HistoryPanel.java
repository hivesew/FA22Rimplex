package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is the panel that displays the history of the game. It is a JPanel that contains a
 * JTextPane that is not editable. It also contains a button that can be used to extend the panel.
 * The panel is initially hidden and can be shown by clicking the button. (in theory)
 */
public class HistoryPanel extends JPanel implements ActionListener
{
  private static final long serialVersionUID = 1L;
  private static final String EXTEND = ">";
  private static final String SHRINK = "<";
  
  boolean isOpen = false;
  Image image;
  History historyArea = new History();

  private final JButton shrinkButton = new JButton(SHRINK);
  private final JButton extendButton = new JButton(EXTEND);
  private final int width = 400;
  private final int height = 400;
  private Timer timer;
  private String ae;
  private int y = 0;

  /**
   * This is the constructor for the HistoryPanel class. It creates a new HistoryPanel object.
   */
  public HistoryPanel()
  {
    this.setPreferredSize(new Dimension(width, height));
    this.setLayout(new BorderLayout());
    this.setBackground(RimplexWindow.scheme);
    shrinkButton.setFocusable(false);
    extendButton.setFocusable(false);
    this.add(historyArea.getHistory(), BorderLayout.CENTER);
    this.add(extendButton, BorderLayout.EAST);
    extendButton.setBackground(RimplexWindow.scheme);
    this.setVisible(true);

    extendButton.addActionListener(this);
    shrinkButton.addActionListener(this);
    timer = new Timer(10, this);
  }

  /**
   * addButton.
   * @param button
   */
  
  public void addButton(final JButton button)
  {
    this.add(button, BorderLayout.EAST);
  }

  /**
   * This method is called when the timer ticks. It moves the panel to the right.
   *
   * @param e
   *          the ActionEvent that triggered this method.
   */
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e != null)
    {
      setAe(e.getActionCommand());
    }

    if (!isOpen)
    {
      timer.start();
      if (this.getX() >= 400)
      {
        this.remove(extendButton);
        add(shrinkButton, BorderLayout.EAST);
        shrinkButton.setBackground(RimplexWindow.scheme);
        this.revalidate();
        this.repaint();
        timer.stop();
        isOpen = true;
        return;
      }
      this.setLocation(this.getX() + 5, y);
      repaint();
    }
    else if (isOpen)
    {
      timer.start();
      if (this.getX() <= 30)
      {
        this.remove(shrinkButton);
        repaint();
        add(extendButton, BorderLayout.EAST);
        extendButton.setBackground(RimplexWindow.scheme);
        extendButton.setVisible(true);
        this.revalidate();
        this.repaint();
        timer.stop();
        isOpen = false;
        return;
      }
      this.setLocation(this.getX() - 5, y);
      repaint();
    }

  }

  /**
   * Returns the action command as a String.
   * @return the String of the Action Command
   */
  
  public String getAe()
  {
    return ae;
  }
  
  /**
   * Sets the action command. 
   * @param ae the action command to set
   */

  public void setAe(final String ae)
  {
    this.ae = ae;
  }
}
