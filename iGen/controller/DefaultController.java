package org.iMage.iGen.controller;

import org.iMage.iGen.controller.construction.BackgroundConstruction;
import org.iMage.iGen.controller.construction.ChromaConstruction;
import org.iMage.iGen.controller.construction.EnhancementConstruction;
import org.iMage.iGen.controller.construction.IllegalOptionValueException;
import org.iMage.iGen.controller.construction.KeyingConstruction;
import org.iMage.iGen.controller.construction.LumaConstruction;
import org.iMage.iGen.controller.construction.TextConstruction;
import org.iMage.iGen.controller.util.ImageUtility;
import org.iMage.iGen.model.EnhancementModel;
import org.iMage.iGen.model.KeyingModel;
import org.iMage.iGen.model.Model;
import org.iMage.iGen.view.View;
import org.iMage.screengen.base.BufferedScreenImage;
import org.iMage.screengen.base.Keying;
import org.iMage.screengen.base.ScreenImage;
import org.iMage.screengen.base.ScreenImageEnhancement;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@link DefaultController} is the default controller implementation.
 *
 * @author Sara
 * @version 1.0
 */
public class DefaultController implements Controller {

  private final Model model;
  private final View view;

  private Map<KeyingModel, KeyingConstruction<?>> keyings;
  private Map<EnhancementModel, EnhancementConstruction<?>> enhancements;

  private ScreenImage inputImage;

  /**
   * Create a new default controller with the model and the view.<br>
   * It loads the constructions.
   *
   * @param model model of MVC
   * @param view  view of MVC
   */
  public DefaultController(Model model, View view) {
    this.model = model;
    this.view = view;

    addConstructions();
  }

  /**
   * Add all available constructions and map it to the keying and enhancement models.
   * If a new keying process or enhancement should be added, this method has to be edited.
   */
  private void addConstructions() {
    this.keyings = new HashMap<>();
    keyings.put(KeyingModel.CHROMA, new ChromaConstruction());
    keyings.put(KeyingModel.LUMA, new LumaConstruction());

    this.enhancements = new HashMap<>();
    enhancements.put(EnhancementModel.BACKGROUND, new BackgroundConstruction());
    enhancements.put(EnhancementModel.TEXT, new TextConstruction());
  }

  @Override
  public void setInputImage(BufferedImage input) {
    if (input == null) {
      return;
    }

    performAsyncOperation(() -> {
      this.inputImage = new BufferedScreenImage(input);
      view.setInputImage(input);
    });
  }

  @Override
  public void applyKeying() {
    if (inputImage == null) {
      view.showErrorMessage("Please select an input image.");
      return;
    }

    KeyingModel keyingModel = view.getSelectedKeying();
    Keying keying;
    try {
      keying = getConstruction(keyingModel).constructFromOptions();
    } catch (IllegalOptionValueException e) {
      view.showErrorMessage(e.getMessage());
      return;
    }

    performAsyncOperation(() -> {
      model.applyKeying(inputImage, keying);
      model.notifyObservers();
    });
  }

  @Override
  public void applyEnhancement() {
    if (model.getOutputImage() == null) {
      view.showErrorMessage("There is no output image to enhance.");
      return;
    }

    EnhancementModel enhancementModel = view.getSelectedEnhancement();
    ScreenImageEnhancement enhancement;
    try {
      enhancement = getConstruction(enhancementModel).constructFromOptions();
    } catch (IllegalOptionValueException e) {
      view.showErrorMessage(e.getMessage());
      return;
    }

    performAsyncOperation(() -> {
      model.applyEnhancement(enhancement);
      model.notifyObservers();
    });
  }

  @Override
  public void requestImageSave() {
    if (model.getOutputImage() == null) {
      view.showErrorMessage("There is no output image to save.");
      return;
    }

    String path = view.requestFilePath();
    if (path == null) {
      return;
    }

    performAsyncOperation(() -> {
      try {
        model.getOutputImage().save(path);
        view.showInfoMessage("The image was written to " + path + ".");
      } catch (IOException e) {
        view.showErrorMessage("Failed to store image: " + e.getMessage());
      }
    });
  }

  @Override
  public void revertLastChanges() {
    if (!model.revert()) {
      view.showErrorMessage("Cannot revert no changes.");
    }
  }

  @Override
  public KeyingConstruction<?> getConstruction(KeyingModel keyingModel) {
    return keyings.get(keyingModel);
  }

  @Override
  public EnhancementConstruction<?> getConstruction(EnhancementModel enhancementModel) {
    return enhancements.get(enhancementModel);
  }

  @Override
  public void update() {
    ScreenImage screenImage = model.getOutputImage();
    view.setOutputImage(ImageUtility.toBufferedImage(screenImage));
  }

  /**
   * Perform an operation asynchronously to the UI thread.
   * During the operation, the view will be locked.
   * This avoids an UI freeze due to large operations.
   *
   * @param operation operation that will be performed asynchronously
   */
  private void performAsyncOperation(Runnable operation) {
    view.lock();

    EventQueue.invokeLater(() -> {
      operation.run();
      view.unlock();
    });
  }
}