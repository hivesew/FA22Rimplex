package gui;

import static gui.LanguageSelectScreen.*;
import java.awt.*;
import javax.swing.*;

/**
 * RimplexAboutDialog contains instructions on creating the About menu.
 * @author Fa22TeamA
 */

public class RimplexAboutDialog extends JDialog implements LanguageChangeObserver
{
  private static Container contentPane;

  private static final long serialVersionUID = 1L;
  
  private static final String NEWLINE = "\n";
  private static final String ABOUT_RIMPLEX = "ABOUT_RIMPLEX";
  private static final String ABOUT_INFO = "ABOUT_INFO";
  private static final String VERSION = "VERSION";
  private static final String COPYRIGHT = "COPYRIGHT";
  private static final String TRADEMARK = "TRADEMARK";
  private static final String ALTERATION = "ALTERATION";
  private static final String LOGO_PROVIDED = "LOGO_PROVIDED";
  private static final String WEBSITE = "https://w3.cs.jmu.edu/bernstdh/web/common/help/"
      + "aboutSagaciousMedia.php.";
  private static final String HEADER = "------------------------------------------------   "
      + "RIMPLEX   ------------------------------------------------ ";

  private static String PRINT_ON_SCREEN = NEWLINE
      + HEADER + NEWLINE + NEWLINE
      + STRINGS.getString(ABOUT_INFO) + NEWLINE + NEWLINE
      + STRINGS.getString(VERSION) + NEWLINE 
      + STRINGS.getString(COPYRIGHT) + NEWLINE + NEWLINE
      + STRINGS.getString(TRADEMARK) + NEWLINE 
      + WEBSITE + NEWLINE + NEWLINE
      + STRINGS.getString(ALTERATION) + NEWLINE
      + STRINGS.getString(LOGO_PROVIDED) + NEWLINE;
  
  private JTextArea messageArea;

  /**
   * RimplexAboutDialog constructor.
   * @param owner
   */
  
  public RimplexAboutDialog(final Frame owner)
  {
    super(owner);
    setResizable(false);
    setupLayout();
    
  }

  private void setupLayout()
  {
    contentPane = this.getContentPane();
    BorderLayout bLayout = new BorderLayout();
    contentPane.setLayout(bLayout);

    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    this.setTitle(STRINGS.getString(ABOUT_RIMPLEX));

    messageArea = new JTextArea(PRINT_ON_SCREEN);
    messageArea.setFocusable(false);
    messageArea.setEditable(false);
    contentPane.add(messageArea);

    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    // Make the JFrame as small as necessary
    this.pack();

  }

  @Override
  public void changeLanguage()
  {
    setTitle(STRINGS.getString(ABOUT_RIMPLEX));
    PRINT_ON_SCREEN = NEWLINE
        + HEADER + NEWLINE + NEWLINE
        + STRINGS.getString(ABOUT_INFO) + NEWLINE + NEWLINE
        + STRINGS.getString(VERSION) + NEWLINE 
        + STRINGS.getString(COPYRIGHT) + NEWLINE + NEWLINE
        + STRINGS.getString(TRADEMARK) + NEWLINE 
        + WEBSITE + NEWLINE + NEWLINE
        + STRINGS.getString(ALTERATION) + NEWLINE
        + STRINGS.getString(LOGO_PROVIDED) + NEWLINE;
    messageArea.setText(PRINT_ON_SCREEN);
  }

}
