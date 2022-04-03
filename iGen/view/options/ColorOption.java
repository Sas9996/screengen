package org.iMage.iGen.view.options;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import java.awt.Color;

/**
 * This option represents a color value.<br>
 * The button opens a {@link JColorChooser} to select the color.
 * If a color got selected, the button will be colored in the same color.
 *
 * @author Sara
 * @version 1.0
 */
public class ColorOption implements ConfigurationOption<Color> {

  private Color color;

  /**
   * Create a new color option with a given color as initial color.
   *
   * @param color initial color
   */
  public ColorOption(Color color) {
    this.color = color;
  }

  /**
   * Create a new color option with {@link Color#GREEN} as initial color.
   */
  public ColorOption() {
    this(Color.GREEN);
  }

  @Override
  public String description() {
    return "Color";
  }

  @Override
  public JComponent createComponent() {
    JButton button = new JButton("Open color chooser");
    button.setOpaque(true);
    button.setBackground(color);

    button.addActionListener(event -> {
      Color selectedColor = JColorChooser.showDialog(button,
          "Select background greenscreen color", color);

      if (selectedColor != null) {
        color = selectedColor;
      }

      button.setBackground(color);
    });

    return button;
  }

  @Override
  public Color getValue() {
    return color;
  }
}