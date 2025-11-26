
package Koperasi;

import javax.swing.*;

/**
  Entry Point Aplikasi Manajemen Koperasi
 */
public class UtsProject {
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainForm frame = new MainForm();
            frame.setVisible(true);
        });
    }
}
