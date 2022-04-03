package org.iMage.iGen.view.components;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.view.util.CardSelector;
import org.iMage.iGen.view.util.ComponentRow;
import org.iMage.iGen.view.util.TitledImagePanel;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**
 * This abstract panel is the base for the input and output side panel.<br>
 * It contains a {@link TitledImagePanel} and a {@link ComponentRow}
 * containing a {@link CardSelector}.
 *
 * @param <T> combobox item type
 * @author Sara
 * @version 1.0
 */
public abstract class SidePanel<T> extends JPanel {

  private final Controller controller;

  private final TitledImagePanel imagePanel;
  private final CardSelector<T> cardSelector;
  private final ComponentRow componentRow;

  /**
   * Create a new side panel.
   *
   * @param title      title of the {@link TitledImagePanel}
   * @param process    process description
   * @param controller MVC controller
   */
  public SidePanel(String title, String process, Controller controller) {
    this.controller = controller;

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

    this.imagePanel = new TitledImagePanel(title);

    // Process panel
    JPanel processPanel = new JPanel();
    processPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder(process),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    processPanel.setLayout(new BoxLayout(processPanel, BoxLayout.Y_AXIS));

    // Configuration panel
    JPanel configurationPanel = new JPanel();

    CardLayout layout = new CardLayout();
    configurationPanel.setLayout(layout);
    configurationPanel.setAlignmentX(LEFT_ALIGNMENT);

    this.cardSelector = createCardSelector(configurationPanel);
    cardSelector.addCardsToPanel();

    this.componentRow = createComponentRow();
    componentRow.setAlignmentX(LEFT_ALIGNMENT);
    processPanel.add(componentRow);

    processPanel.add(Box.createRigidArea(new Dimension(10, 0)));

    processPanel.add(configurationPanel);

    add(imagePanel);
    add(processPanel);
  }

  /**
   * Create a new card selector for the side panel.
   *
   * @param cardPanel card panel
   * @return new card selector
   */
  protected abstract CardSelector<T> createCardSelector(JPanel cardPanel);

  /**
   * Create a new component row for the side panel.
   *
   * @return component row, containing the card selector
   */
  protected abstract ComponentRow createComponentRow();

  /**
   * Disable all row components and card selector components.
   *
   * @see javax.swing.JComponent#setEnabled(boolean)
   */
  public void disableComponents() {
    for (Component component : componentRow.getComponents()) {
      disableComponent(component);
    }
    for (JPanel card : cardSelector.getCards()) {
      for (Component component : card.getComponents()) {
        disableComponent(component);
      }
    }
  }

  /**
   * Enable all row components and card selector components.
   *
   * @see javax.swing.JComponent#setEnabled(boolean)
   */
  public void enableComponents() {
    for (Component component : componentRow.getComponents()) {
      component.setEnabled(true);
    }
    for (JPanel card : cardSelector.getCards()) {
      for (Component component : card.getComponents()) {
        component.setEnabled(true);
      }
    }
  }

  /**
   * Set the {@link TitledImagePanel} panel.
   *
   * @param image image to set
   */
  public void setImage(BufferedImage image) {
    imagePanel.setImage(image);
  }

  /**
   * Get the MVC controller.
   *
   * @return controller
   */
  protected Controller getController() {
    return controller;
  }

  /**
   * Get the card selector.
   *
   * @return card selector
   */
  protected CardSelector<T> getCardSelector() {
    return cardSelector;
  }

  /**
   * Disable a single component if it is not a label.
   *
   * @param component component to disable
   */
  private void disableComponent(Component component) {
    if (component instanceof JLabel) {
      return;
    }

    component.setEnabled(false);
  }
}