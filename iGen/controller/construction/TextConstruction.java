package org.iMage.iGen.controller.construction;

import org.iMage.iGen.view.options.BoundedIntegerOption;
import org.iMage.iGen.view.options.ConfigurationOption;
import org.iMage.iGen.view.options.FontOption;
import org.iMage.iGen.view.options.PositionOption;
import org.iMage.iGen.view.options.StringOption;
import org.iMage.screengen.DefaultScreenGenerator;
import org.iMage.screengen.TextEnhancement;
import org.iMage.screengen.base.Position;

import java.awt.Font;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class constructs a {@link TextEnhancement}.<br>
 * It requires a text, a font, a font size and a position to construct it.
 *
 * @author Sara
 * @version 1.0
 */
public class TextConstruction implements EnhancementConstruction<TextEnhancement> {

  private final ConfigurationOption<String> textOption;
  private final ConfigurationOption<String> fontOption;
  private final ConfigurationOption<Integer> sizeOption;
  private final ConfigurationOption<Position> positionOption;

  /**
   * Create a new text construction with default text "Hello World" and size 12.
   * It also contains a font option and position option.
   */
  public TextConstruction() {
    this.textOption = new StringOption("Text", "Hello World");
    this.fontOption = new FontOption();
    this.sizeOption = new BoundedIntegerOption("Size", 12, 1, 50);

    List<Position> positions = DefaultScreenGenerator.POSITIONS.stream()
        .sorted(Comparator.comparing(Position::getDescription))
        .collect(Collectors.toList());
    this.positionOption = new PositionOption(positions);
  }

  @Override
  public List<ConfigurationOption<?>> getOptions() {
    return List.of(textOption, fontOption, sizeOption, positionOption);
  }

  @Override
  public TextEnhancement constructFromOptions() throws IllegalOptionValueException {
    String text = textOption.getValue();
    String fontName = fontOption.getValue();
    int size = sizeOption.getValue();
    Position position = positionOption.getValue();

    if (text.isBlank()) {
      throw new IllegalOptionValueException("Please enter a non-blank text.");
    }

    return new TextEnhancement(text, new Font(fontName, Font.PLAIN, size), position);
  }
}