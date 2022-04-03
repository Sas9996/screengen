package org.iMage.iGen.view.options;

import javax.swing.JComponent;

/**
 * The {@link ConfigurationOption} interface represents a setting
 * that can be changed by the user.<br>
 * It will be used to represent the keying and enhancement parameters used in their constructions.
 *
 * @param <T> configuration value type
 * @author Sara
 * @version 1.0
 */
public interface ConfigurationOption<T> {

  /**
   * Get the configuration description.<br>
   * It should be short and state the configuration option.
   *
   * @return short description
   */
  String description();

  /**
   * Create the configuration component, i.e. {@link javax.swing.JComboBox}
   * or {@link javax.swing.JTextField}.
   *
   * @return configuration component
   */
  JComponent createComponent();

  /**
   * Get the current configuration value.<br>
   * The value is determined by the configuration component.
   *
   * @return configured value
   * @see #createComponent()
   */
  T getValue();
}