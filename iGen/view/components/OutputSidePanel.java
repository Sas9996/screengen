package org.iMage.iGen.view.components;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.model.EnhancementModel;
import org.iMage.iGen.view.options.ConfigurationPanel;
import org.iMage.iGen.view.util.CardSelector;
import org.iMage.iGen.view.util.ComponentRow;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This side panel represents the output side panel (right).
 * It contains the configuration panel for enhancement processes.
 *
 * @author Sara
 * @version 1.0
 */
public class OutputSidePanel extends SidePanel<EnhancementModel> {

  private JComboBox<EnhancementModel> enhancementComboBox;

  /**
   * Create a new output side panel.
   *
   * @param controller MVC controller
   */
  public OutputSidePanel(Controller controller) {
    super("Output", "Enhancement", controller);
  }

  @Override
  protected CardSelector<EnhancementModel> createCardSelector(JPanel cardPanel) {
    Map<EnhancementModel, JPanel> cards = new LinkedHashMap<>();
    for (EnhancementModel enhancement : EnhancementModel.values()) {
      ConfigurationPanel panel = new ConfigurationPanel(
          getController().getConstruction(enhancement).getOptions());
      cards.put(enhancement, panel);
    }

    return new CardSelector<>(cardPanel, cards);
  }

  @Override
  protected ComponentRow createComponentRow() {
    this.enhancementComboBox = getCardSelector().createComboBox();

    JButton revertButton = new JButton("Revert");
    revertButton.addActionListener(event -> {
      getController().revertLastChanges();
    });

    JButton applyButton = new JButton("Apply");
    applyButton.addActionListener(event -> {
      getController().applyEnhancement();
    });

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(event -> {
      getController().requestImageSave();
    });

    return new ComponentRow(enhancementComboBox, revertButton, applyButton,
        saveButton);
  }

  /**
   * Get the selected enhancement model.
   *
   * @return selected enhancement model
   */
  public EnhancementModel getSelectedEnhancement() {
    return (EnhancementModel) enhancementComboBox.getSelectedItem();
  }
}