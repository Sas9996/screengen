package org.iMage.iGen.view.options;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Color;

/**
 * This option represents a non-negative double value.<br>
 * If the user does not provide a valid double value, the text color changes to red.
 *
 * @author Sara
 * @version 1.0
 * @see #isValidValue()
 */
public class DoubleOption implements ConfigurationOption<Double> {

  private final String label;
  private final double startValue;

  private JTextField textField;

  /**
   * Create a new double option with a label and an initial value.
   *
   * @param label      option label
   * @param startValue initial start value
   */
  public DoubleOption(String label, double startValue) {
    this.label = label;
    this.startValue = startValue;
  }

  @Override
  public String description() {
    return label;
  }

  @Override
  public JComponent createComponent() {
    this.textField = new JTextField(Double.toString(startValue));

    textField.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void changedUpdate(DocumentEvent e) {
        handleInput();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        handleInput();
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        handleInput();
      }
    });

    return textField;
  }

  @Override
  public Double getValue() {
    if (isValidValue()) {
      return Double.parseDouble(textField.getText());
    } else {
      return null;
    }
  }

  /**
   * Set the text field color based on the input validation.
   */
  private void handleInput() {
    if (isValidValue()) {
      textField.setForeground(Color.BLACK);
    } else {
      textField.setForeground(Color.RED);
    }
  }

  /**
   * Check if the content of the text field is a valid double.<br>
   * A double is valid if it is a non-negative number.
   * <code>NaN</code> and <code>Infinity</code> is not valid.
   *
   * @return <code>true</code> if it is valid, otherwise <code>false</code>
   */
  private boolean isValidValue() {
    try {
      double parsed = Double.parseDouble(textField.getText());

      return parsed >= 0 && !Double.isInfinite(parsed) && !Double.isNaN(parsed);
    } catch (NumberFormatException e) {
      return false;
    }
  }
}