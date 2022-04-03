package org.iMage.iGen.view.util;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.io.File;

/**
 * The {@link FilePathChooser} defines a {@link JFileChooser} that allows the user
 * to select a path.<br>
 * This can be used to get the path of a file the user wants to save.
 *
 * @author Sara
 * @version 1.0
 */
public class FilePathChooser {

  private final Component parent;
  private final JFileChooser fileChooser;

  /**
   * Create a new file path chooser with its parent component.
   *
   * @param parent parent of the file chooser
   */
  public FilePathChooser(Component parent) {
    this.parent = parent;

    this.fileChooser = new JFileChooser();
  }

  /**
   * Show the dialog to the user and let they pick a path.
   *
   * @return absolute file path or <code>null</code> if the user cancels the dialog
   */
  public String request() {
    int result = this.fileChooser.showSaveDialog(parent);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();

      if (selectedFile != null) {
        return selectedFile.getAbsolutePath();
      }
    }

    return null;
  }
}