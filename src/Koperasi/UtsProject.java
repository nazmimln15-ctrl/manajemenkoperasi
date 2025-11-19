/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koperasi;

import javax.swing.*;

/**
 * Entry Point Aplikasi Manajemen Koperasi
 * @author LENOVO
 */
public class UtsProject {

    /**
     * Metode utama untuk menjalankan aplikasi
     * @param args Argumen command line (tidak digunakan)
     */
    public static void main(String[] args) {
        // Jalankan MainForm di Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainForm frame = new MainForm();
            frame.setVisible(true);
        });
    }
}
