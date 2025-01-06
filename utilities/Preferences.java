package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import gui.PreferencesPanel;

/**
 * Preferences class.
 * @author FaTeam22A
 *
 */

public class Preferences implements Serializable
{
  public static final int DEFAULT_NUMBER_OF_DECIMAL_PLACES =  4;
  public static final int DEFAULT_TRAILING_ZEROS = 3;
  public static final boolean DEFAULT_THOUSANDS_SEPARATOR = false;
  
  private static final long serialVersionUID = 1L;
  private static final String SER_FILE = "preferences.ser";
  
  private int numberOfDecimalPlaces;
  private int trailingZeros;
  private  boolean thousandsSeparator;
  /**
   * Default constructor for preferences object.
   */
  public Preferences()
  {
    numberOfDecimalPlaces = DEFAULT_NUMBER_OF_DECIMAL_PLACES;
    trailingZeros = DEFAULT_TRAILING_ZEROS;
    thousandsSeparator = DEFAULT_THOUSANDS_SEPARATOR;
  }
  /**
   * Constructor for preferences object.
   * @param numberOfZeros The number of zeros to display.
   * @param trailingZeros The number of trailing zeros to display.
   * @param thousandsSeparator Whether to display the thousands' separator.
   */
  public Preferences(final int numberOfZeros, final int trailingZeros,
      final boolean thousandsSeparator)
  {
    this.numberOfDecimalPlaces = numberOfZeros;
    this.trailingZeros = trailingZeros;
    this.thousandsSeparator = thousandsSeparator;
  }
  /**
   * Returns the number of zeros to display.
   * @return the numberOfZeros
   */
  public int getNumberOfDecimalPlaces()
  {
    return numberOfDecimalPlaces;
  }
  /**
   * Sets the number of zeros to display.
   * @param numberOfZeros the numberOfZeros to set
   */
  public void setNumberOfDecimalPlaces(final int numberOfZeros)
  {
    this.numberOfDecimalPlaces = numberOfZeros;

  }
  /**
   * Returns the number of trailing zeros to display.
   * @return the trailingZeros
   */
  public int getTrailingZeros()
  {
    return trailingZeros;
  }
  /**
   * Sets the number of trailing zeros to display.
   * @param trailingZeros the trailingZeros to set
   */
  public void setTrailingZeros(final int trailingZeros)
  {
    this.trailingZeros = trailingZeros;
  }
  /**
   * Returns whether to display the thousands' separator.
   * @return the thousandsSeparator
   */
  public boolean isThousandsSeparator()
  {
    return thousandsSeparator;
  }
  /**
   * Sets whether to display the thousands' separator.
   * @param thousandsSeparator the thousandsSeparator to set
   */
  public void setThousandsSeparator(final boolean thousandsSeparator)
  {
    this.thousandsSeparator = thousandsSeparator;
    PreferencesPanel.thousandsSeparatorCheck.setSelected(thousandsSeparator);
  }
  
  /**
   * Saves the preferences to a .ser file.
   * @param preferences the preferences being saved
   * @throws IOException if there is an error with the output stream
   */
  public void savePreferences(final Preferences preferences) throws IOException
  {
    // save the preferences object to a file using the serialization API
    // serialize the preferences object
    FileOutputStream fileOut = new FileOutputStream(SER_FILE);
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    out.writeObject(preferences);
    out.close();
    fileOut.close();
  }
  
  /**
   * Loads the preferences from a .ser file.
   * @throws FileNotFoundException if the file is not found
   * @throws IOException if there is an error with reading the file
   * @throws ClassNotFoundException if the class cannot be found
   */
  public void loadPreferences() throws FileNotFoundException, IOException, ClassNotFoundException
  {
    // load the preferences object from a file using the serialization API
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SER_FILE))) 
    {
      Preferences preferences = (Preferences) in.readObject();
      this.numberOfDecimalPlaces = preferences.numberOfDecimalPlaces;
      this.trailingZeros = preferences.trailingZeros;
      this.thousandsSeparator = preferences.thousandsSeparator;
    }
  }



}
