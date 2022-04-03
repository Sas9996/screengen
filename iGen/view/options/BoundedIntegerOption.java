package org.iMage.iGen.view.options;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * This option represents an integer value.<br>
 * The value can be selected by a {@link JSpinner} and has a lower and upper bound.
 *
 * @author Sara
 * @version 1.0
 */
public class BoundedIntegerOption implements ConfigurationOption<Integer> {

  private final String label;
  private final int startValue;
  private final int minValue;
  private final int maxValue;

  private JSpinner spinner;

  /**
   * Create a new bounded integer option with a label, start, min and max value.
   *
   * @param label      option description
   * @param startValue start value
   * @param minValue   lower bound
   * @param maxValue   upper bound
   */
  public BoundedIntegerOption(String label, int startValue, int minValue, int maxValue) {
    this.label = label;
    this.startValue = startValue;
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  @Override
  public String description() {
    return label;
  }

  @Override
  public JComponent createComponent() {
    this.spinner = new JSpinner(new SpinnerNumberModel(startValue, minValue, maxValue, 1));
    spinner.setEditor(new JSpinner.DefaultEditor(spinner));
    return spinner;
  }

  @Override
  public Integer getValue() {
    return (int) spinner.getValue();
  }
}