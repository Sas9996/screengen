package org.iMage.iGen.controller.construction;

import org.iMage.iGen.view.options.ConfigurationOption;
import org.iMage.iGen.view.options.ImageOption;
import org.iMage.iGen.view.options.PositionOption;
import org.iMage.screengen.BackgroundEnhancement;
import org.iMage.screengen.DefaultScreenGenerator;
import org.iMage.screengen.base.BufferedScreenImage;
import org.iMage.screengen.base.Position;

import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class constructs a {@link BackgroundEnhancement}.<br>
 * It requires an image and a position to construct it.
 *
 * @author Sara
 * @version 1.0
 */
public class BackgroundConstruction implements EnhancementConstruction<BackgroundEnhancement> {

  private final ConfigurationOption<BufferedImage> imageOption;
  private final ConfigurationOption<Position> positionOption;

  /**
   * Create a new background construction with an image option and a position option.
   *
   * @see DefaultScreenGenerator#POSITIONS
   */
  public BackgroundConstruction() {
    this.imageOption = new ImageOption("Background Image");

    List<Position> positions = DefaultScreenGenerator.POSITIONS.stream()
        .sorted(Comparator.comparing(Position::getDescription))
        .collect(Collectors.toList());
    this.positionOption = new PositionOption(positions);
  }

  @Override
  public List<ConfigurationOption<?>> getOptions() {
    return List.of(imageOption, positionOption);
  }

  @Override
  public BackgroundEnhancement constructFromOptions() throws IllegalOptionValueException {
    if (imageOption.getValue() == null) {
      throw new IllegalOptionValueException("No background image selected.");
    }

    return new BackgroundEnhancement(new BufferedScreenImage(imageOption.getValue()),
        positionOption.getValue());
  }
}