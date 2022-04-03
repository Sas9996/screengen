package org.iMage.iGen.view.util;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Dimension;

/**
 * The component row is a {@link JPanel} that lists components in a row with the same distance
 * to each other.
 *
 * @author Sara
 * @version 1.0
 */
public class ComponentRow extends JPanel {

  private static final int MIN_WIDTH = 500;
  private static final int MIN_HEIGHT = 25;

  /**
   * Create a new component row with an array of components.
   *
   * @param components array of components
   */
  public ComponentRow(JComponent... components) {
    placeComponents(components);

    setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
    setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));
  }

  /**
   * Place the components on the panel using the {@link BoxLayout}.
   *
   * @param components array of components
   */
  private void placeComponents(JComponent[] components) {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    if (components == null || components.length == 0) {
      return;
    }

    add(components[0]);

    for (int i = 1; i < components.length; i++) {
      add(Box.createHorizontalGlue());
      add(components[i]);
    }
  }
}