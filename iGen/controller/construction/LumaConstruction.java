package org.iMage.iGen.controller.construction;

import org.iMage.iGen.view.options.BoundedFloatOption;
import org.iMage.iGen.view.options.ConfigurationOption;
import org.iMage.screengen.LumaKeying;

import java.util.List;

/**
 * This class constructs a {@link LumaKeying}.<br>
 * It requires two float values between 0 and 1 (minimum brightness and maximum brightness)
 * to construct it.
 *
 * @author Sara
 * @version 1.0
 */
public class LumaConstruction implements KeyingConstruction<LumaKeying> {

  private final ConfigurationOption<Float> minValueOption;
  private final ConfigurationOption<Float> maxValueOption;

  /**
   * Create a new luma construction with two float options.
   */
  public LumaConstruction() {
    this.minValueOption = new BoundedFloatOption("Brightness (minimum)");
    this.maxValueOption = new BoundedFloatOption("Brightness (maximum)");
  }

  @Override
  public List<ConfigurationOption<?>> getOptions() {
    return List.of(minValueOption, maxValueOption);
  }

  @Override
  public LumaKeying constructFromOptions() throws IllegalOptionValueException {
    if (minValueOption.getValue() > maxValueOption.getValue()) {
      throw new IllegalOptionValueException(
          "The minimum value has to be smaller than or equal to the maximum value.");
    }

    return new LumaKeying(minValueOption.getValue(), maxValueOption.getValue());
  }
}