package org.iMage.iGen.model;

import org.iMage.iGen.observer.Subject;
import org.iMage.screengen.base.Keying;
import org.iMage.screengen.base.ScreenImage;
import org.iMage.screengen.base.ScreenImageEnhancement;

/**
 * The {@link Model} interface uses the screengen dependency internally
 * to provide the required functionality.<br>
 * It is independent from the {@link org.iMage.iGen.view.View}
 * and the {@link org.iMage.iGen.controller.Controller}.<br>
 * In addition, it notifies {@link org.iMage.iGen.observer.Observer}
 * if the state of the model changes.
 *
 * @author Sara
 * @version 1.0
 */
public interface Model extends Subject {

  /**
   * Apply the keying to a given image.<br>
   * The result will be stored as output image.
   *
   * @param image  screen image
   * @param keying keying process
   */
  void applyKeying(ScreenImage image, Keying keying);

  /**
   * Apply the enhancement to the latest output image.<br>
   * The result will be stored as latest output image.
   *
   * @param enhancement enhancement process
   */
  void applyEnhancement(ScreenImageEnhancement enhancement);

  /**
   * Revert the last changes, e.g. keying or enhancement.
   *
   * @return <code>true</code> if there is an operation to revert, otherwise <code>false</code>
   */
  boolean revert();

  /**
   * Get the current output image.
   *
   * @return output image, may be <code>null</code>
   */
  ScreenImage getOutputImage();

  /**
   * Create the default model implementation based on the screengen library.
   *
   * @return model implementation
   */
  static Model create() {
    return new DefaultModel();
  }
}