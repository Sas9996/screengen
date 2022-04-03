package org.iMage.iGen.controller.construction;

import org.iMage.iGen.view.options.ColorOption;
import org.iMage.iGen.view.options.ConfigurationOption;
import org.iMage.iGen.view.options.DoubleOption;
import org.iMage.screengen.ChromaKeying;

import java.awt.Color;
import java.util.List;

/**
 * This class constructs a {@link ChromaKeying}.<br>
 * It requires a key color and a distance to construct it.
 *
 * @author Sara
 * @version 1.0
 */
public class ChromaConstruction implements KeyingConstruction<ChromaKeying> {

  private final ConfigurationOption<Color> colorOption;
  private final ConfigurationOption<Double> distanceOption;

  /**
   * Create a new chroma construction with color green and distance 100.0 as default values.
   */
  public ChromaConstruction() {
    this.colorOption = new ColorOption(Color.GREEN);
    this.distanceOption = new DoubleOption("Distance", 100.0);
  }

  @Override
  public List<ConfigurationOption<?>> getOptions() {
    return List.of(colorOption, distanceOption);
  }

  @Override
  public ChromaKeying constructFromOptions() throws IllegalOptionValueException {
    if (distanceOption.getValue() == null) {
      throw new IllegalOptionValueException("The distance is not a valid number.");
    }
    if (distanceOption.getValue() <= 0) {
      throw new IllegalOptionValueException("The distance has to be non-negative.");
    }

    return new ChromaKeying(colorOption.getValue(), distanceOption.getValue());
  }
}