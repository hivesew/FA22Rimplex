package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * The Language Select Screen for Rimplex.
 * @author F22TeamA
 */

public class LanguageSelectScreen extends JDialog implements ActionListener, LanguageChangeSubject
{
  
  // package visibility 
  private static final String STRINGS_PATH = "gui.Strings";
  static ResourceBundle STRINGS = ResourceBundle.getBundle(STRINGS_PATH);
  
  private static final long serialVersionUID = 1L;
  private static final String ENGLISH = "ENGLISH";
  private static final String FRENCH = "FRENCH";
  private static final String SPANISH = "SPANISH";
  private static final String OK = "OK";
  private static final String CANCEL = "CANCEL";
  private static final String LANGUAGE_SELECTION = "LANGUAGE_SELECTION";
  
  private Collection<Object> observers;
  private String[] strings;
  private JComboBox<String> languageBox;
  

  
  /**
   * Constructor for the LanguageSelectScreen.
   * @param frame the owner of the JDialog
   */
  
  public LanguageSelectScreen(final RimplexWindow frame)
  {
    super(frame, true);
    strings = new String[] {STRINGS.getString(ENGLISH), STRINGS.getString(FRENCH),
        STRINGS.getString(SPANISH)};
    languageBox = new JComboBox<String>(strings);
    observers = new HashSet<Object>();
    observers.add(frame);
    observers.add(frame.getAboutDialog());
    setFocusable(false);
    setupLayout();
    this.setVisible(true);
  }
  
  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ae = e.getActionCommand();
    
    if (ae.equals(STRINGS.getString(OK)))
    {
      languageChange(languageBox.getSelectedItem().toString());
      dispose();
    } else if (ae.equals(STRINGS.getString(CANCEL)))
    {
      dispose();
    }
  }
  
  private void setupLayout()
  {
    setTitle(STRINGS.getString(LANGUAGE_SELECTION));
    setBackground(Color.GRAY);
    setResizable(false);
    setSize(300, 100);
    setLayout(new BorderLayout());
    add(languageBox, BorderLayout.NORTH);
    addButtonPanel();
  }
  
  private void addButtonPanel()
  {
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(createButton(STRINGS.getString(OK)));
    buttonPanel.add(createButton(STRINGS.getString(CANCEL)));
    add(buttonPanel, BorderLayout.SOUTH);
  }
  
  private JButton createButton(final String label)
  {
    JButton button = new JButton(label);
    button.addActionListener(this);
    return button;
  }
  
  private void languageChange(final String text)
  {
    if (text.equals(strings[0]))
    {
      Locale.setDefault(new Locale("en"));
    }
    else if (text.equals(strings[1]))
    {
      Locale.setDefault(new Locale("fr"));
    }
    else if (text.equals(strings[2]))
    {
      Locale.setDefault(new Locale("es"));
    }
    ResourceBundle.clearCache();
    STRINGS = ResourceBundle.getBundle(STRINGS_PATH, Locale.getDefault());
    notifyObserver();
    changeLanguage();
  }

  private void changeLanguage()
  {
    strings = new String[] {STRINGS.getString(ENGLISH), STRINGS.getString(FRENCH),
        STRINGS.getString(SPANISH)};
    languageBox = new JComboBox<String>(strings);
    setTitle(STRINGS.getString(LANGUAGE_SELECTION));
  }
  
  @Override
  public void addObserver(final Object lang)
  {
    observers.add(lang);
  }

  @Override
  public void notifyObserver()
  {
    for (Object lang: observers)
    {
      ((LanguageChangeObserver) lang).changeLanguage();
    }
  }
  
}
