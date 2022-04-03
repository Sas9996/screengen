package org.iMage.iGen.view.util;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The {@link ImageFileChooser} defines a {@link JFileChooser} that allows the user
 * to select an image.
 *
 * @author Sara
 * @version 1.0
 */
public class ImageFileChooser {

  private static final String[] IMAGE_EXTENSIONS = { "png", "jpg", "jpeg" };

  private final Component parent;
  private final JFileChooser fileChooser;

  /**
   * Create a new image file chooser with its parent component.
   *
   * @param parent parent of the file chooser
   */
  public ImageFileChooser(Component parent) {
    this.parent = parent;

    this.fileChooser = new JFileChooser();

    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(new FileNameExtensionFilter("Images only", IMAGE_EXTENSIONS));
  }

  /**
   * Show the dialog to the user and let they pick an image.
   *
   * @return loaded buffered image or <code>null</code> if the user cancels the dialog or
   * the selected image cannot be read as image (should never happen)
   */
  public BufferedImage request() {
    int result = this.fileChooser.showOpenDialog(parent);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selected = fileChooser.getSelectedFile();

      if (selected != null && selected.exists()) {
        try {
          return ImageIO.read(selected);
        } catch (IOException e) {
          return null;
        }
      }
    }

    return null;
  }
}