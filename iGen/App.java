package org.iMage.iGen;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.model.Model;
import org.iMage.iGen.view.View;

import javax.swing.SwingUtilities;

/**
 * The class is the main entry point and starts the GUI.
 *
 * @author Sara
 * @version 1.0
 */
public final class App {

  /**
   * Prevent instances.
   */
  private App() {

  }

  /**
   * Initialize model, view and controller and start the {@link View}.
   *
   * @param args command line arguments, ignored
   */
  public static void main(String[] args) {
    Model model = Model.create();
    View view = View.create();
    Controller controller = Controller.create(model, view);

    model.addObserver(controller);

    SwingUtilities.invokeLater(() -> {
      view.initialize(controller);
      view.start();
    });
  }
}