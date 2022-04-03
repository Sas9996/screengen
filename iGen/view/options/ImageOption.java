package org.iMage.iGen.view.options;

import org.iMage.iGen.view.util.ImageFileChooser;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;

/**
 * This option represents an image value.<br>
 * The button opens a {@link ImageFileChooser} to select an image.
 *
 * @author Sara
 * @version 1.0
 */
public class ImageOption implements ConfigurationOption<BufferedImage> {

  private BufferedImage image;

  private final String description;

  /**
   * Create a new image option with given description.
   *
   * @param description option description
   */
  public ImageOption(String description) {
    this.description = description;
  }

  @Override
  public String description() {
    return description;
  }

  @Override
  public JComponent createComponent() {
    JButton button = new JButton("Select image...");
    button.addActionListener(event -> {
      ImageFileChooser imageFileChooser = new ImageFileChooser(button);
      this.image = imageFileChooser.request();
    });

    return button;
  }

  @Override
  public BufferedImage getValue() {
    return image;
  }
}