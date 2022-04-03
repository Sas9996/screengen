package org.iMage.iGen.view.options;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

/**
 * The configuration panel shows the {@link ConfigurationOption}s.
 * It will be used with a {@link java.awt.CardLayout} to change the panels keeping the panel size.
 *
 * @author Sara
 * @version 1.0
 * @see ConfigurationOption#description()
 * @see ConfigurationOption#createComponent()
 */
public class ConfigurationPanel extends JPanel {

  private final List<ConfigurationOption<?>> options;

  /**
   * Create a new configuration panel with a list of configuration options.
   *
   * @param options list of configuration options that will be shown in the panel
   */
  public ConfigurationPanel(List<ConfigurationOption<?>> options) {
    this.options = options;

    setAlignmentX(LEFT_ALIGNMENT);

    placeOptions();

    setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Configuration"),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
  }

  /**
   * Place the options on the panel using a {@link GridBagLayout}.
   * The layout ensures that the components have the same size.
   */
  private void placeOptions() {
    GridBagLayout layout = new GridBagLayout();
    setLayout(layout);

    GridBagConstraints constraints = new GridBagConstraints();

    // Init constraints
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.ipadx = 0;
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.fill = GridBagConstraints.HORIZONTAL;

    for (int i = 0; i < options.size(); i++) {
      ConfigurationOption<?> option = options.get(i);

      // Label
      constraints.weightx = 1;
      constraints.weighty = 1;

      constraints.gridx = 0;
      constraints.gridy = i;

      JLabel label = new JLabel(option.description());
      layout.setConstraints(label, constraints);
      add(label, constraints);

      // Option component
      constraints.weightx = 0.1;
      constraints.weighty = 0.1;

      constraints.gridx = 1;
      constraints.gridy = i;
      JComponent component = option.createComponent();
      add(component, constraints);
      layout.setConstraints(component, constraints);
    }
  }
}