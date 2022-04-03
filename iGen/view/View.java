package org.iMage.iGen.view;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.model.EnhancementModel;
import org.iMage.iGen.model.KeyingModel;
import org.iMage.iGen.view.components.ApplicationFrame;

import java.awt.image.BufferedImage;

/**
 * The {@link View} interface shows the input and output image and provides components
 * the user can interact with.
 *
 * @author Sara
 * @version 1.0
 */
public interface View {

  /**
   * Initialize the view and load all components.<br>
   * The method should be called before {@link #start()}.
   *
   * @param controller MVC controller
   */
  void initialize(Controller controller);

  /**
   * Start the UI and show it to the user.
   */
  void start();

  /**
   * Show an info message to the user.
   *
   * @param message message to display
   */
  void showInfoMessage(String message);

  /**
   * Show an error message to the user.
   *
   * @param message message to display
   */
  void showErrorMessage(String message);

  /**
   * Display the given image as input image.
   *
   * @param image input image, may be <code>null</code>
   */
  void setInputImage(BufferedImage image);

  /**
   * Display the given image as output image.
   *
   * @param image output image, may be <code>null</code>
   */
  void setOutputImage(BufferedImage image);

  /**
   * Request the user to enter a file path.
   *
   * @return absolute file path
   */
  String requestFilePath();

  /**
   * Get the current selected keying model.
   *
   * @return keying model
   */
  KeyingModel getSelectedKeying();

  /**
   * Get the current selected enhancement model.
   *
   * @return enhancement model
   */
  EnhancementModel getSelectedEnhancement();

  /**
   * Lock all UI components.
   */
  void lock();

  /**
   * Unlock all UI components.
   */
  void unlock();

  /**
   * Create the default view implementation.
   *
   * @return view
   */
  static View create() {
    return new ApplicationFrame();
  }
}