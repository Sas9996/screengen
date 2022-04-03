package org.iMage.iGen.view.util;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * The card selector defines a {@link JComboBox} with items that refer to cards.
 * The cards are used in the {@link CardLayout} which allows to show different panels.<br>
 * If the user selects another item in the combo box, the according panel will be shown.
 *
 * @param <E> item and card element type
 * @author Sara
 * @version 1.0
 */
public class CardSelector<E> implements ItemListener {

  private final JPanel cardPanel;
  private final Map<E, JPanel> cards;

  /**
   * Create a new card selector with the card panel and a mapping from key to the cards.
   *
   * @param cardPanel panel with the card layout
   * @param cards     map of elements and panels
   */
  public CardSelector(JPanel cardPanel, Map<E, JPanel> cards) {
    if (!(cardPanel.getLayout() instanceof CardLayout)) {
      throw new IllegalArgumentException(
          "The card panel has to have the CardLayout as layout manager.");
    }

    this.cardPanel = cardPanel;
    this.cards = cards;
  }

  @Override
  public void itemStateChanged(ItemEvent event) {
    if (event.getStateChange() == ItemEvent.SELECTED) {
      CardLayout layout = (CardLayout) (cardPanel.getLayout());
      layout.show(cardPanel, event.getItem().toString());
    }
  }

  /**
   * Add the cards to the panel.
   */
  public void addCardsToPanel() {
    for (Map.Entry<E, JPanel> entry : cards.entrySet()) {
      cardPanel.add(entry.getKey().toString(), entry.getValue());
    }
  }

  /**
   * Create a new combo box.<br>
   * Every item refers to a panel that will be shown by changing the item.
   *
   * @return combo box component
   */
  public JComboBox<E> createComboBox() {
    JComboBox<E> comboBox = new JComboBox<>(new Vector<>(cards.keySet()));
    comboBox.setSelectedItem(comboBox.getItemAt(0));
    comboBox.addItemListener(this);

    return comboBox;
  }

  /**
   * Get an unmodifiable list of all card panels.
   *
   * @return unmodifiable list
   */
  public List<JPanel> getCards() {
    return List.copyOf(cards.values());
  }
}