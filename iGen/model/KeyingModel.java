package org.iMage.iGen.model;

/**
 * The keying model enumerates the keying processes that will be provided by the model.
 *
 * @author Sara
 * @version 1.0
 */
public enum KeyingModel {

  /**
   * Chroma keying process.<br>
   * It sets pixels transparent if the color distance to a key color is
   * smaller than or equal to a given threshold.
   */
  CHROMA("Chroma"),

  /**
   * Luma keying process.<br>
   * If sets pixels transparent if the brightness value is in a given range.
   */
  LUMA("Luma");

  private final String name;

  /**
   * Create a new keying model.
   *
   * @param name readable name of the process
   */
  KeyingModel(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}