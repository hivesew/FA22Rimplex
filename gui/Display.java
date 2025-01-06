package gui;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

/**
 * The Display for the CalculatorGUI that automatically italicizes i.
 * @author F22TeamA
 */

public class Display extends JTextPane 
{
  
  private static final long serialVersionUID = 1L;
  private static final Color BORDER_COLOR = new Color(88, 139, 168);
  private static final Color BACKGROUND_COLOR = new Color(221, 228, 244);
  private StyledDocument doc;
  private SimpleAttributeSet italicSet = new SimpleAttributeSet();
  private SimpleAttributeSet paragraphSet = new SimpleAttributeSet();
  
  /**
   * The Constructor of the Display.
   */
  
  public Display()
  {
    super();
    
    // Overrides Nimbus default background color
    UIDefaults defaults = new UIDefaults();
    defaults.put("TextPane[Enabled].backgroundPainter", BACKGROUND_COLOR);
    this.putClientProperty("Nimbus.Overrides", defaults);
    this.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
    this.setBackground(BACKGROUND_COLOR);
    
    StyleConstants.setItalic(italicSet, true);
    StyleConstants.setFontFamily(italicSet, "MONOSPACED");
    StyleConstants.setSpaceAbove(paragraphSet, 8);
    doc = this.getStyledDocument();
    this.setParagraphAttributes(paragraphSet, false);
    
    Border border = BorderFactory.createLineBorder(BORDER_COLOR, 2);
    
    this.setPreferredSize(new Dimension(300, 50));
    this.setBorder(border);
    this.setEditable(false);
    this.setAlignmentY(SwingConstants.CENTER);
    
  }

  @Override
  public void setText(final String text)
  {
    super.setText(text);
    for (int i = 0; i < text.length(); i++)
    {
      if (this.getText().charAt(i) == 'i')
      {
        doc.setCharacterAttributes(i, 1, italicSet, true);
      }
    }
  }
  
}
