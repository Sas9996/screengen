package org.iMage.iGen.controller.construction;

import org.iMage.iGen.view.options.ConfigurationOption;

import java.util.List;

/**
 * This interface is used to construct objects of type <code>T</code>
 * using {@link ConfigurationOption}.
 *
 * @param <T> type to construct
 * @author Sara
 * @version 1.0
 */
public interface Construction<T> {

  /**
   * Get a list of configuration options that will be displayed to the user.<br>
   * They specify the parameters that will be passed to the type <code>T</code>.
   *
   * @return list of options
   */
  List<ConfigurationOption<?>> getOptions();

  /**
   * Construct an instance of <code>T</code> by using the values of the specified options.
   *
   * @return a new instance of type <code>T</code>
   * @throws IllegalOptionValueException if the value of an option is not valid
   *                                     for the constructions
   * @see Construction#getOptions()
   * @see ConfigurationOption#getValue()
   */
  T constructFromOptions() throws IllegalOptionValueException;
}