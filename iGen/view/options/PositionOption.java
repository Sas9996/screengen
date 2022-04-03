package org.iMage.iGen.view.options;

import org.iMage.screengen.base.Position;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * This option represents a {@link Position} value.<br>
 * The value can be selected by a {@link JComboBox}.
 *
 * @author Sara
 * @version 1.0
 */
public class PositionOption implements ConfigurationOption<Position> {

  private final List<Position> positions;

  private JComboBox<Position> comboBox;

  /**
   * Create a new position option with a list of possible positions.
   *
   * @param positions list of positions
   */
  public PositionOption(List<Position> positions) {
    this.positions = new ArrayList<>(positions);
  }

  @Override
  public String description() {
    return "Position";
  }

  @Override
  public JComponent createComponent() {
    this.comboBox = new JComboBox<>(new Vector<>(positions));
    comboBox.setSelectedItem(comboBox.getItemAt(0));
    return comboBox;
  }

  @Override
  public Position getValue() {
    return (Position) comboBox.getSelectedItem();
  }
}