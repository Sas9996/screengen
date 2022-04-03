package org.iMage.iGen.observer;

/**
 * The {@link Subject} interface represents the subject in the observer pattern.
 *
 * @author Sara
 * @version 1.0
 */
public interface Subject {

  /**
   * Add an observer to the subject.<br>
   *
   * @param observer observer
   */
  void addObserver(Observer observer);

  /**
   * Notify all added observers.
   *
   * @see Observer#update()
   */
  void notifyObservers();
}