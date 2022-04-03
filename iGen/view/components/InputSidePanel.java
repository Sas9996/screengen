package org.iMage.iGen.view.components;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.model.KeyingModel;
import org.iMage.iGen.view.options.ConfigurationPanel;
import org.iMage.iGen.view.util.CardSelector;
import org.iMage.iGen.view.util.ComponentRow;
import org.iMage.iGen.view.util.ImageFileChooser;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This side panel represents the input side panel (left).
 * It contains the configuration panel for keying processes.
 *
 * @author Sara
 * @version 1.0
 */
public class InputSidePanel extends SidePanel<KeyingModel> {

  private JComboBox<KeyingModel> keyingComboBox;

  /**
   * Create a new input side panel.
   *
   * @param controller MVC controller
   */
  public InputSidePanel(Controller controller) {
    super("Input", "Keying", controller);
  }

  @Override
  protected CardSelector<KeyingModel> createCardSelector(JPanel cardPanel) {
    Map<KeyingModel, JPanel> cards = new LinkedHashMap<>();
    for (KeyingModel keying : KeyingModel.values()) {
      ConfigurationPanel panel = new ConfigurationPanel(
          getController().getConstruction(keying).getOptions());
      cards.put(keying, panel);
    }

    return new CardSelector<>(cardPanel, cards);
  }

  @Override
  protected ComponentRow createComponentRow() {
    JButton loadInputButton = new JButton("Load input...");
    loadInputButton.addActionListener(event -> {
      ImageFileChooser imageFileChooser = new ImageFileChooser(this);
      getController().setInputImage(imageFileChooser.request());
    });

    this.keyingComboBox = getCardSelector().createComboBox();

    JButton applyButton = new JButton("Apply");
    applyButton.addActionListener(event -> {
      getController().applyKeying();
    });

    return new ComponentRow(loadInputButton, keyingComboBox, applyButton);
  }

  /**
   * Get the selected keying model.
   *
   * @return selected keying model
   */
  public KeyingModel getSelectedKeying() {
    return (KeyingModel) keyingComboBox.getSelectedItem();
  }
}