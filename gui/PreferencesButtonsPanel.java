package gui;

import static gui.LanguageSelectScreen.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import utilities.Preferences;

/**
 * SOMETHING.
 * @author F22TeamA
 */

public class PreferencesButtonsPanel extends JPanel implements ActionListener
{

  private static final long serialVersionUID = 1L;
  private static final String SAVE = "SAVE";
  private static final String CANCEL = "CANCEL";
  private static final String RESET = "RESET";
  private JButton saveButton = new JButton(STRINGS.getString(SAVE));
  private JButton cancelButton = new JButton(STRINGS.getString(CANCEL));
  private JButton resetButton = new JButton(STRINGS.getString(RESET));

  /**
   * Create the panel for the preferences buttons.
   */
  
  public PreferencesButtonsPanel()
  {
    // add a cancel button and a save button to the panel
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(cancelButton, BorderLayout.EAST);
    cancelButton.addActionListener(this);
    panel.add(saveButton, BorderLayout.WEST);
    saveButton.addActionListener(this);
    panel.add(resetButton, BorderLayout.CENTER);
    resetButton.addActionListener(this);
    add(panel);
    // create a listener for the save button
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    if (e.getSource() == saveButton)
    {
      RimplexWindow.preferences.setNumberOfDecimalPlaces(PreferencesPanel.getNumOfDecimalPlaces());
      RimplexWindow.preferences.setTrailingZeros(PreferencesPanel.getTrailingZeros());
      RimplexWindow.preferences.setThousandsSeparator(PreferencesPanel.getThousandsSeparator());
      try
      {
        RimplexWindow.preferences.savePreferences(RimplexWindow.preferences);
      }
      catch (IOException ex)
      {
        throw new RuntimeException(ex);
      }
      ((PreferencesWindow) this.getParent().getParent().getParent().getParent()).disposeWindow();
      try
      {
        RimplexWindow.preferences.savePreferences(RimplexWindow.preferences);
      }
      catch (IOException ex)
      {
        throw new RuntimeException(ex);
      }
    }
    else if (e.getSource() == cancelButton)
    {
      // dispose this window.
      ((PreferencesWindow) this.getParent().getParent().getParent().getParent()).disposeWindow();
    }
    else if (e.getSource() == resetButton)
    {

      RimplexWindow.preferences
          .setNumberOfDecimalPlaces(Preferences.DEFAULT_NUMBER_OF_DECIMAL_PLACES);

      RimplexWindow.preferences.setNumberOfDecimalPlaces(
          Preferences.DEFAULT_NUMBER_OF_DECIMAL_PLACES);

      RimplexWindow.preferences.setTrailingZeros(Preferences.DEFAULT_TRAILING_ZEROS);
      RimplexWindow.preferences.setThousandsSeparator(Preferences.DEFAULT_THOUSANDS_SEPARATOR);
      try
      {
        RimplexWindow.preferences.savePreferences(RimplexWindow.preferences);
      }
      catch (IOException ex)
      {
        throw new RuntimeException(ex);
      }
      ((PreferencesWindow) this.getParent().getParent().getParent().getParent()).disposeWindow();
    }
  }

}
