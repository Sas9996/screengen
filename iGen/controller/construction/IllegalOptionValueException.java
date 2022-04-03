package org.iMage.iGen.controller.construction;

/**
 * The exception is thrown if an option contains an invalid value for a given construction.
 *
 * @author Sara
 * @version 1.0
 */
public class IllegalOptionValueException extends Exception {

  /**
   * Create a new illegal option value exception.
   *
   * @param message reason for the invalid value
   */
  public IllegalOptionValueException(String message) {
    super(message);
  }
}