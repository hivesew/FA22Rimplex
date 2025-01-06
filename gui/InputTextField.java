package gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.*;

/**
 * The InputTextField being used in the Calculator. It limits the possible characters that can 
 * be entered and limits the maximum length. It also automatically italicizes i. 
 * @author F22TeamA
 */

public class InputTextField extends JTextPane 
{
    
  private static final long serialVersionUID = 1L;
  private static final String ACCEPTABLE_CHARACTERS = "0123456789i.+-";
  
  /**
   * The Constructor for the InputTextField.
   */
  
  public InputTextField()
  {
    super();
   
    SimpleAttributeSet rightAlign = new SimpleAttributeSet();
    SimpleAttributeSet italicSet = new SimpleAttributeSet();
    SimpleAttributeSet plainSet = new SimpleAttributeSet();
    StyleConstants.setItalic(italicSet, true);
    StyleConstants.setAlignment(rightAlign, StyleConstants.ALIGN_RIGHT);
    this.setParagraphAttributes(rightAlign, true);
    
    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
    this.setBorder(border);
    this.addKeyListener((KeyListener) new KeyAdapter() 
    {
      public void keyPressed(final KeyEvent ke) 
      {
        String value = getText();
        int length = value.length();
        if (ACCEPTABLE_CHARACTERS.indexOf(ke.getKeyChar()) != -1 && length < 20) 
        {
          if (ke.getKeyChar() == 'i')
          {
            setCharacterAttributes(italicSet, true);
          } else 
          {
            setCharacterAttributes(plainSet, true);
          }
          setEditable(true);
        } else if (ke.getKeyChar() == KeyEvent.VK_BACK_SPACE) 
        {
          setEditable(true);
        } else 
        {
          setEditable(false);
        }
      }
    });

  }
    
}
