package org.iMage.iGen.view.util;

import org.iMage.screengen.base.ImageUtils;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * The panel contains a title and an image panel.<br>
 * The image panel shows a {@link BufferedImage}, keeping the ratio.
 * If the image has transparent pixels, a default transparent background will be drawn.
 *
 * @author Sara
 * @version 1.0
 */
public class TitledImagePanel extends JPanel {

  private static final int MIN_WIDTH = 450;
  private static final int MIN_HEIGHT = 300;

  private static final String NO_IMAGE_AVAILABLE_MESSAGE = "No image to display.";
  private static final int SQUARE_LENGTH = 10;

  private final JPanel imagePanel;

  private BufferedImage image;

  /**
   * Create a new titled image panel with a title and an initial image.
   *
   * @param title        title
   * @param initialImage initial image
   */
  public TitledImagePanel(String title, BufferedImage initialImage) {
    setImage(initialImage);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // Title
    JLabel label = new JLabel(title);
    label.setFont(new Font("Arial", Font.PLAIN, 24));
    label.setAlignmentX(LEFT_ALIGNMENT);
    add(label);

    add(Box.createRigidArea(new Dimension(0, 10)));

    // Image panel
    this.imagePanel = new JPanel() {

      @Override
      public void paint(Graphics graphics) {
        int x = 0;
        int y = 0;
        int width = getWidth();
        int height = getHeight();

        if (image == null) {
          drawMessage(graphics);
        } else {
          x = (int) ((imagePanel.getWidth() - image.getWidth()) / 2.0);
          y = (int) ((imagePanel.getHeight() - image.getHeight()) / 2.0);
          width = image.getWidth();
          height = image.getHeight();

          drawTransparentBackground(graphics, x, y, width, height);

          graphics.drawImage(image, x, y, this);
        }

        // Draw border
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width - 1, height - 1);
      }
    };

    imagePanel.setSize(MIN_WIDTH, MIN_HEIGHT);
    imagePanel.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    imagePanel.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    imagePanel.setAlignmentX(LEFT_ALIGNMENT);
    add(imagePanel);
  }

  /**
   * Create a new titled image panel with a title.<br>
   * The image panel does not show an image but an informative message instead.
   *
   * @param title title
   */
  public TitledImagePanel(String title) {
    this(title, null);
  }

  /**
   * Set the image for the image panel.
   * If the image is too large, the shown image will be scaled.<br>
   * If the image is <code>null</code>, no image will be shown.
   *
   * @param image image, may be <code>null</code>
   */
  public void setImage(BufferedImage image) {
    if (image == null) {
      this.image = null;
    } else {
      this.image = image;

      if (image.getWidth() > imagePanel.getWidth()) {
        this.image = ImageUtils.scaleWidth(image, imagePanel.getWidth());
      }
      if (image.getHeight() > imagePanel.getHeight()) {
        this.image = ImageUtils.scaleHeight(image, imagePanel.getHeight());
      }
    }

    repaint();
  }

  /**
   * Draw a transparent background (white and gray squares) on the image panel.
   *
   * @param graphics image panel graphics
   * @param x        x coordinate of the background at the panel
   * @param y        y coordinate of the background at the panel
   * @param width    background width
   * @param height   background height
   */
  private void drawTransparentBackground(Graphics graphics, int x, int y, int width, int height) {
    boolean white = true;

    for (int i = 0; i < width / SQUARE_LENGTH; i++) {
      white = !white;
      for (int j = 0; j < height / SQUARE_LENGTH; j++) {
        graphics.setColor(white ? Color.WHITE : Color.LIGHT_GRAY);
        graphics.fillRect(x + i * SQUARE_LENGTH, y + j * SQUARE_LENGTH,
            SQUARE_LENGTH, SQUARE_LENGTH);

        white = !white;
      }
    }

    graphics.drawRect(x + width / SQUARE_LENGTH, y + height / SQUARE_LENGTH,
        width % SQUARE_LENGTH, height % SQUARE_LENGTH);
  }

  /**
   * Draw the {@link #NO_IMAGE_AVAILABLE_MESSAGE} message centered to the image panel.
   *
   * @param graphics image panel graphics
   */
  private void drawMessage(Graphics graphics) {
    Rectangle rectangle = imagePanel.getVisibleRect();

    Font font = new Font("Arial", Font.PLAIN, 17);
    FontMetrics metrics = graphics.getFontMetrics(font);

    int x = rectangle.x + (rectangle.width - metrics.stringWidth(NO_IMAGE_AVAILABLE_MESSAGE)) / 2;
    int y = rectangle.y + ((rectangle.height - metrics.getHeight()) / 2) + metrics.getAscent();

    graphics.setFont(font);
    graphics.drawString(NO_IMAGE_AVAILABLE_MESSAGE, x, y);
  }
}