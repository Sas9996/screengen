package org.iMage.iGen.model;

/**
 * The enhancement model enumerates the enhancement processes that will be provided by the model.
 *
 * @author Sara
 * @version 1.0
 */
public enum EnhancementModel {

  /**
   * Background enhancement.<br>
   * The image will be enhanced by a given background image.
   */
  BACKGROUND("Background"),

  /**
   * Text enhancement.<br>
   * A text with a given font and size will be applied to a given image.
   */
  TEXT("Text");

  private final String name;

  /**
   * Create a new enhancement model.
   *
   * @param name readable name of the process
   */
  EnhancementModel(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}