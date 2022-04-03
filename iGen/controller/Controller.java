package org.iMage.iGen.controller;

import org.iMage.iGen.controller.construction.EnhancementConstruction;
import org.iMage.iGen.controller.construction.KeyingConstruction;
import org.iMage.iGen.model.EnhancementModel;
import org.iMage.iGen.model.KeyingModel;
import org.iMage.iGen.model.Model;
import org.iMage.iGen.observer.Observer;
import org.iMage.iGen.view.View;

import java.awt.image.BufferedImage;

/**
 * The {@link Controller} interface handles the user interactions with the {@link View}.<br>
 * In addition, the controller will be notified if the {@link Model} sends updates.<br>
 * Every image operation such as {@link #setInputImage(BufferedImage)} or {@link #applyKeying()}
 * will be performed asynchronously to the UI thread.
 * To ensure consistency, every UI component will be locked during the operation.
 *
 * @author Sara
 * @version 1.0
 * @see Observer#update()
 */
public interface Controller extends Observer {

  /**
   * Set and display the given image as input.<br>
   *
   * @param input input image, may be <code>null</code>
   */
  void setInputImage(BufferedImage input);

  /**
   * Construct the selected keying process with the given configuration options.
   * The keying will be applied to the input image.
   * The result is the latest output image and will be shown in the view.
   *
   * @see #setInputImage(BufferedImage)
   */
  void applyKeying();

  /**
   * Construct the selected enhancement process with the given configuration options.
   * The enhancement will be applied to latest output image.
   * The result will be set to the latest output image and will be shown in the view.
   *
   * @see #setInputImage(BufferedImage)
   */
  void applyEnhancement();

  /**
   * Request the user to save the latest output image.<br>
   * The user has to provide a path that will be used to store the image.
   */
  void requestImageSave();

  /**
   * Revert the last output image changes.<br>
   * For instance, if the user applies two enhancements, the latest will be removed.
   */
  void revertLastChanges();

  /**
   * Get the construction for a given keying model.<br>
   * The construction determines how a new instance based on the options will be created.
   *
   * @param keyingModel keying model
   * @return mapped keying construction based on the keying model
   */
  KeyingConstruction<?> getConstruction(KeyingModel keyingModel);

  /**
   * Get the construction for a given enhancement model.<br>
   * The construction determines how a new instance based on the options will be created.
   *
   * @param enhancementModel enhancement model
   * @return mapped enhancement construction based on the enhancement model
   */
  EnhancementConstruction<?> getConstruction(EnhancementModel enhancementModel);

  /**
   * Create the default controller implementation.
   *
   * @param model model
   * @param view  view
   * @return controller
   */
  static Controller create(Model model, View view) {
    return new DefaultController(model, view);
  }
}