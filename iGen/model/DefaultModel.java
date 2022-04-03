package org.iMage.iGen.model;

import org.iMage.iGen.observer.Observer;
import org.iMage.screengen.base.Keying;
import org.iMage.screengen.base.ScreenImage;
import org.iMage.screengen.base.ScreenImageEnhancement;

import java.util.LinkedList;
import java.util.List;

/**
 * The default {@link Model} implementation using screengen.
 *
 * @author Sara
 * @version 1.0
 */
public class DefaultModel implements Model {

  private final List<ScreenImage> outputs;
  private final List<Observer> observers;

  /**
   * Create a new default model with an empty list of outputs and observers.
   */
  public DefaultModel() {
    this.observers = new LinkedList<>();
    this.outputs = new LinkedList<>();
  }

  @Override
  public void applyKeying(ScreenImage image, Keying keying) {
    outputs.clear();
    outputs.add(keying.process(image));
  }

  @Override
  public void applyEnhancement(ScreenImageEnhancement enhancement) {
    outputs.add(enhancement.enhance(getOutputImage()));
  }

  @Override
  public boolean revert() {
    if (outputs.isEmpty()) {
      return false;
    }

    outputs.remove(outputs.size() - 1);
    notifyObservers();
    return true;
  }

  @Override
  public ScreenImage getOutputImage() {
    if (!outputs.isEmpty()) {
      return outputs.get(outputs.size() - 1);
    }

    return null;
  }

  @Override
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  @Override
  public void notifyObservers() {
    for (Observer observer : observers) {
      observer.update();
    }
  }
}