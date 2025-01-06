package gui;

/**
 * Interface for the language change subject for the observer pattern.
 * @author F22TeamA
 */

public interface LanguageChangeSubject
{

  /**
   * Add an observer to the subject's list of observers. 
   * @param lang s
   */
  
  void addObserver(final Object lang);
  
  /**
   * Notify the observers.
   */
  void notifyObserver();
  
}
