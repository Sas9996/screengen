package org.iMage.iGen.view.options;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.GraphicsEnvironment;

/**
 * This option represents a font value.<br>
 * The font can be selected by a combo box that contains every available font name.
 *
 * @author Sara
 * @version 1.0
 * @see GraphicsEnvironment#getLocalGraphicsEnvironment()
 */
public class FontOption implements ConfigurationOption<String> {

  private JComboBox<String> comboBox;

  @Override
  public String description() {
    return "Font";
  }

  @Override
  public JComponent createComponent() {
    String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getAvailableFontFamilyNames();

    this.comboBox = new JComboBox<>(fontNames);
    comboBox.setSelectedItem(comboBox.getItemAt(0));
    return comboBox;
  }

  @Override
  public String getValue() {
    return (String) comboBox.getSelectedItem();
  }
}