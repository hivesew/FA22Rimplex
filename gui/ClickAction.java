package gui;

import java.awt.event.ActionEvent;
import javax.swing.*;
/**
 * ClickAction manages clicks on JButtons.
 * @author valdezoa
 */

public class ClickAction extends AbstractAction
{

  private static final long serialVersionUID = 1L;
  private JButton button;
  
  /**
   * S.
   * @param button
   */
  
  public ClickAction(final AbstractButton button)
  {
    super();
    this.button = (JButton) button;
  }
  
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    button.grabFocus();
    button.doClick(50);
  }

}
