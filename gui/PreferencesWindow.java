package gui;

import static gui.LanguageSelectScreen.*;
import javax.swing.*;
import java.awt.*;

/**
 * The popup for the preferences of the system. 
 * @author F22TeamA
 */

public class PreferencesWindow extends JDialog
{
  private static final long serialVersionUID = 1L;
  private static final String PREFERENCES = "PREFERENCES";
  private PreferencesPanel preferencesPanel;
  
  /**
   * The PreferencesWindow constructor.
   * @param owner the JDialog owner 
   */
  public PreferencesWindow(final Frame owner)
  {
    super(owner);
    setTitle(STRINGS.getString(PREFERENCES));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    getContentPane().setLayout(new BorderLayout());
    this.add(setPreferencePanel(new PreferencesPanel()), BorderLayout.CENTER);
    this.add (new PreferencesButtonsPanel(), BorderLayout.SOUTH);
    pack();
    setVisible(true);
  }
  
  /**
   * Disposes of the window.
   */
  public void disposeWindow() 
  {
    this.dispose();
  }
  
  /**
   * Returns the PreferencesPanel.
   * @return the PreferencesPanel
   */
  public PreferencesPanel getPreferencePanel()
  {
    return preferencesPanel;
  }
  
  /**
   * Sets the PreferencesPanel.
   * @param preferencePanel is the PreferencesPanel to set.
   * @return the new PreferencesPanel
   */
  public PreferencesPanel setPreferencePanel(final PreferencesPanel preferencePanel)
  {
    this.preferencesPanel = preferencePanel;
    return preferencePanel;
  }

}
