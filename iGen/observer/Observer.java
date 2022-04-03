package org.iMage.iGen.observer;

/**
 * The {@link Observer} interface represents the observer in the observer pattern.
 *
 * @author Sara
 * @version 1.0
 */
public interface Observer {

  /**
   * Called when the {@link Subject} notifies the observer.
   */
  void update();
}