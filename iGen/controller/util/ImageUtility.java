package org.iMage.iGen.controller.util;

import org.iMage.screengen.base.ScreenImage;

import java.awt.image.BufferedImage;

/**
 * The utility class is used to deal with buffered images and screen images.
 *
 * @author Sara
 * @version 1.0
 */
public final class ImageUtility {

  /**
   * Prevent instances.
   */
  private ImageUtility() {

  }

  /**
   * Create a {@link BufferedImage} based on a given {@link ScreenImage}.
   *
   * @param screenImage screen image, may be <code>null</code>
   * @return new buffered image with type {@link BufferedImage#TYPE_INT_ARGB} or <code>null</code>
   * if the input is <code>null</code>
   */
  public static BufferedImage toBufferedImage(ScreenImage screenImage) {
    if (screenImage == null) {
      return null;
    }

    BufferedImage bufferedImage = new BufferedImage(screenImage.getWidth(), screenImage.getHeight(),
        BufferedImage.TYPE_INT_ARGB);

    for (int x = 0; x < bufferedImage.getWidth(); x++) {
      for (int y = 0; y < bufferedImage.getHeight(); y++) {
        bufferedImage.setRGB(x, y, screenImage.getColor(x, y));
      }
    }

    return bufferedImage;
  }
}