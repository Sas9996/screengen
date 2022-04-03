package org.iMage.iGen.view.options;

import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * This option represents a String value without any further restrictions.
 *
 * @author Sara
 * @version 1.0
 */
public class StringOption implements ConfigurationOption<String> {

  private final String label;
  private final String startValue;

  private JTextField textField;

  /**
   * Create a new string option with a label and an initial value.
   *
   * @param label      option description
   * @param startValue initial start value
   */
  public StringOption(String label, String startValue) {
    this.label = label;
    this.startValue = startValue;
  }

  @Override
  public String description() {
    return label;
  }

  @Override
  public JComponent createComponent() {
    this.textField = new JTextField(startValue);
    return textField;
  }

  @Override
  public String getValue() {
    return textField.getText();
  }
}