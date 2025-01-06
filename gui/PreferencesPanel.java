package gui;

import static gui.LanguageSelectScreen.*;
import javax.swing.*;
import java.awt.*;

/**
 * The visual panel for the Preferences window. 
 * @author F22TeamA
 */

public class PreferencesPanel extends JPanel
{
  public static JCheckBox thousandsSeparatorCheck = new JCheckBox();
  
  private static final long serialVersionUID = 1L;
  private static final String NUMBER_OF_DECIMALS = "NUMBER_OF_DECIMALS";
  private static final String NUMBER_OF_TRAILING_ZEROS = "NUMBER_OF_TRAILING_ZEROS";
  private static final String THOUSANDS_SEPARATOR = "THOUSANDS_SEPARATOR";
  private static JTextField trailingZerosText = new JTextField();
  private static JTextField numZerosText = new JTextField();
  private JLabel numZeros = new JLabel(STRINGS.getString(NUMBER_OF_DECIMALS));
  private JLabel trailingZeros = new JLabel(STRINGS.getString(NUMBER_OF_TRAILING_ZEROS));
  private JLabel thousandsSeparator = new JLabel(STRINGS.getString(THOUSANDS_SEPARATOR));

  /**
   * Create the panel.
   */
  public PreferencesPanel()
  {
    JPanel panel = new JPanel();
    add(panel);
    panel.setLayout(new GridLayout(4, 2));
    panel.add(numZeros, 0);
    panel.add(numZerosText, 1);
    panel.add(trailingZeros, 2);
    panel.add(trailingZerosText, 3);
    panel.add(thousandsSeparator, 4);
    if (RimplexWindow.preferences.isThousandsSeparator())
    {
      thousandsSeparatorCheck.setSelected(true);
    }
    panel.add(thousandsSeparatorCheck, 5);
    panel.setVisible(true);
  }
  
  /**
   * Returns the number of decimal places the user wants.
   * @return the number of decimal places the user wants
   */
  public static int getNumOfDecimalPlaces()
  {
    try 
    {
      return Integer.parseInt(numZerosText.getText());
    } catch (NumberFormatException e) 
    {
      return RimplexWindow.preferences.getNumberOfDecimalPlaces();
    }
  }
  
  /**
   * Returns the number of trailing zeros the user wants.
   * @return the number of trailing zeros the user wants
   */
  public static int getTrailingZeros()
  {
    try 
    {
      return Integer.parseInt(trailingZerosText.getText());
    } catch (NumberFormatException e)
    {
      return RimplexWindow.preferences.getTrailingZeros();
    }
  }
  
  /**
   * Returns if the user wants thousands separators.
   * @return if the user wants thousands separators
   */
  public static boolean getThousandsSeparator()
  {
    return thousandsSeparatorCheck.isSelected();
  }

}
