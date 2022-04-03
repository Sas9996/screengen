package org.iMage.iGen.view.options;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.util.Hashtable;

/**
 * This option represents a bounded float value between 0 and 1.<br>
 * It can be modified with a slider.
 *
 * @author Sara
 * @version 1.0
 */
public class BoundedFloatOption implements ConfigurationOption<Float> {

  private static final int FACTOR = 100;

  private final String label;

  private JSlider slider;

  /**
   * Create a new bounded float option with a given label.
   *
   * @param label option label
   */
  public BoundedFloatOption(String label) {
    this.label = label;
  }

  @Override
  public String description() {
    return label;
  }

  @Override
  public JComponent createComponent() {
    this.slider = new JSlider(SwingConstants.HORIZONTAL, 0, FACTOR, FACTOR / 2);

    Hashtable<Integer, JLabel> valueLabels = new Hashtable<>();
    valueLabels.put(0, new JLabel("0.0"));
    valueLabels.put(FACTOR / 2, new JLabel("0.5"));
    valueLabels.put(FACTOR, new JLabel("1.0"));
    slider.setLabelTable(valueLabels);
    slider.setPaintLabels(true);

    return slider;
  }

  @Override
  public Float getValue() {
    return slider.getValue() / FACTOR * 1.0F;
  }
}