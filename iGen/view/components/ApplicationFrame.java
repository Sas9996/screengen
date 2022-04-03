package org.iMage.iGen.view.components;

import org.iMage.iGen.controller.Controller;
import org.iMage.iGen.model.EnhancementModel;
import org.iMage.iGen.model.KeyingModel;
import org.iMage.iGen.view.View;
import org.iMage.iGen.view.util.FilePathChooser;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.image.BufferedImage;

/**
 * The default {@link View} implementation using Java Swing.
 *
 * @author Sara
 * @version 1.0
 */
public class ApplicationFrame extends JFrame implements View {

  private static final int WIDTH = 1240;
  private static final int HEIGHT = 620;

  private InputSidePanel inputPanel;
  private OutputSidePanel outputPanel;

  @Override
  public void initialize(Controller controller) {
    // Frame settings
    this.setTitle("iGen");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setBounds(0, 0, WIDTH, HEIGHT);
    this.setLocationRelativeTo(null);

    JPanel rootPanel = new JPanel();
    rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.X_AXIS));

    // Add panels
    this.inputPanel = new InputSidePanel(controller);
    rootPanel.add(inputPanel);

    this.outputPanel = new OutputSidePanel(controller);
    rootPanel.add(outputPanel);

    add(rootPanel);
  }

  @Override
  public void start() {
    setVisible(true);
  }

  @Override
  public void showInfoMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "An error occurred.", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setInputImage(BufferedImage image) {
    inputPanel.setImage(image);
  }

  @Override
  public void setOutputImage(BufferedImage image) {
    outputPanel.setImage(image);
  }

  @Override
  public String requestFilePath() {
    FilePathChooser directoryFileChooser = new FilePathChooser(this);
    return directoryFileChooser.request();
  }

  @Override
  public KeyingModel getSelectedKeying() {
    return inputPanel.getSelectedKeying();
  }

  @Override
  public EnhancementModel getSelectedEnhancement() {
    return outputPanel.getSelectedEnhancement();
  }

  @Override
  public void lock() {
    inputPanel.disableComponents();
    outputPanel.disableComponents();
  }

  @Override
  public void unlock() {
    inputPanel.enableComponents();
    outputPanel.enableComponents();
  }
}