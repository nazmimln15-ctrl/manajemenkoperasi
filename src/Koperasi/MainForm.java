/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koperasi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;

/**
 * Form Manajemen Koperasi - CRUD Anggota
 * @author LENOVO
 */
public class MainForm extends JFrame {
    private JTextField txtNama, txtAlamat, txtTelepon, txtSimpanan;
    private JButton btnTambah, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel model;
    private int selectedId = -1;

    public MainForm() {
        setTitle("Manajemen Koperasi - CRUD Anggota");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Panel Input
        JPanel panelInput = new JPanel();
        panelInput.setBounds(20, 20, 850, 150);
        panelInput.setBorder(BorderFactory.createTitledBorder("Data Anggota"));
        panelInput.setLayout(null);

        JLabel lblNama = new JLabel("Nama Anggota:");
        JLabel lblAlamat = new JLabel("Alamat:");
        JLabel lblTelepon = new JLabel("No. Telepon:");
        JLabel lblSimpanan = new JLabel("Simpanan Awal (Rp):");

        txtNama = new JTextField();
        txtAlamat = new JTextField();
        txtTelepon = new JTextField();
        txtSimpanan = new JTextField();

        // Set font
        Font font = new Font("Arial", Font.PLAIN, 12);
        lblNama.setFont(font);
        lblAlamat.setFont(font);
        lblTelepon.setFont(font);
        lblSimpanan.setFont(font);
        txtNama.setFont(font);
        txtAlamat.setFont(font);
        txtTelepon.setFont(font);
        txtSimpanan.setFont(font);

        // Posisi label dan textfield
        lblNama.setBounds(20, 25, 120, 25);
        txtNama.setBounds(150, 25, 250, 25);

        lblAlamat.setBounds(420, 25, 120, 25);
        txtAlamat.setBounds(550, 25, 250, 25);

        lblTelepon.setBounds(20, 65, 120, 25);
        txtTelepon.setBounds(150, 65, 250, 25);

        lblSimpanan.setBounds(420, 65, 120, 25);
        txtSimpanan.setBounds(550, 65, 250, 25);

        panelInput.add(lblNama);
        panelInput.add(txtNama);
        panelInput.add(lblAlamat);
        panelInput.add(txtAlamat);
        panelInput.add(lblTelepon);
        panelInput.add(txtTelepon);
        panelInput.add(lblSimpanan);
        panelInput.add(txtSimpanan);

        // Panel Tombol
        JPanel panelButton = new JPanel();
        panelButton.setBounds(20, 170, 850, 50);
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        // Set warna dan font tombol
        btnTambah.setBackground(new Color(76, 175, 80));
        btnTambah.setForeground(Color.WHITE);
        btnTambah.setFont(new Font("Arial", Font.BOLD, 12));

        btnUpdate.setBackground(new Color(33, 150, 243));
        btnUpdate.setForeground(Color.WHITE);
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 12));

        btnDelete.setBackground(new Color(244, 67, 54));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 12));

        btnClear.setBackground(new Color(158, 158, 158));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));

        btnTambah.setPreferredSize(new Dimension(100, 35));
        btnUpdate.setPreferredSize(new Dimension(100, 35));
        btnDelete.setPreferredSize(new Dimension(100, 35));
        btnClear.setPreferredSize(new Dimension(100, 35));

        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnDelete);
        panelButton.add(btnClear);

        // Tabel Data
        model = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "Telepon", "Simpanan (Rp)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabel tidak bisa diedit langsung
            }
        };
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 11));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(33, 150, 243));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 230, 850, 300);

        add(panelInput);
        add(panelButton);
        add(scrollPane);

        // Event Listeners
        btnTambah.addActionListener(e -> tambahData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> hapusData());
        btnClear.addActionListener(e -> clearForm());

        // Event klik pada tabel
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtAlamat.setText(model.getValueAt(row, 2).toString());
                    txtTelepon.setText(model.getValueAt(row, 3).toString());
                    txtSimpanan.setText(model.getValueAt(row, 4).toString().replaceAll("[^0-9.]", ""));
                }
            }
        });

        // Load data
        tampilkanData();
    }

    /**
     * Menampilkan semua data anggota dari database ke tabel
     */
    private void tampilkanData() {
        try {
            model.setRowCount(0);
            AnggotaDAO dao = new AnggotaDAO();
            List<Anggota> list = dao.getAll();
            for (Anggota a : list) {
                model.addRow(new Object[]{
                    a.getId(),
                    a.getNama(),
                    a.getAlamat(),
                    a.getTelepon(),
                    formatRupiah(a.getSimpananAwal())
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal tampilkan data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Menambahkan data anggota baru
     */
    private void tambahData() {
        if (!validasiInput()) return;

        try {
            Anggota a = new Anggota(
                txtNama.getText().trim(),
                txtAlamat.getText().trim(),
                txtTelepon.getText().trim(),
                Double.parseDouble(txtSimpanan.getText())
            );
            new AnggotaDAO().insert(a);
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkanData();
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Mengupdate data anggota yang dipilih
     */
    private void updateData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin diupdate terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validasiInput()) return;

        try {
            Anggota a = new Anggota(
                selectedId,
                txtNama.getText().trim(),
                txtAlamat.getText().trim(),
                txtTelepon.getText().trim(),
                Double.parseDouble(txtSimpanan.getText())
            );
            new AnggotaDAO().update(a);
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            tampilkanData();
            clearForm();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal update data: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Menghapus data anggota yang dipilih
     */
    private void hapusData() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus data ini?",
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                new AnggotaDAO().delete(selectedId);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                tampilkanData();
                clearForm();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal hapus data: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    /**
     * Membersihkan form input
     */
    private void clearForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtTelepon.setText("");
        txtSimpanan.setText("");
        selectedId = -1;
        table.clearSelection();
        txtNama.requestFocus();
    }

    /**
     * Validasi input form
     * @return true jika semua input valid, false jika ada yang kosong atau tidak sesuai format
     */
    private boolean validasiInput() {
        // Cek field kosong
        if (txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama Anggota tidak boleh kosong!", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return false;
        }

        if (txtAlamat.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Alamat tidak boleh kosong!", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtAlamat.requestFocus();
            return false;
        }

        if (txtTelepon.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No. Telepon tidak boleh kosong!", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtTelepon.requestFocus();
            return false;
        }

        if (txtSimpanan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Simpanan Awal tidak boleh kosong!", "Validasi", JOptionPane.WARNING_MESSAGE);
            txtSimpanan.requestFocus();
            return false;
        }

        // Validasi format nomor telepon (hanya angka, minimal 10 digit, maksimal 15)
        String telepon = txtTelepon.getText().trim();
        if (!telepon.matches("\\d{10,15}")) {
            JOptionPane.showMessageDialog(this, "No. Telepon harus berupa angka dengan panjang 10-15 digit!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            txtTelepon.requestFocus();
            return false;
        }

        // Validasi format simpanan (harus angka, boleh desimal)
        try {
            double simpanan = Double.parseDouble(txtSimpanan.getText());
            if (simpanan < 0) {
                JOptionPane.showMessageDialog(this, "Simpanan Awal tidak boleh negatif!",
                        "Validasi", JOptionPane.WARNING_MESSAGE);
                txtSimpanan.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Simpanan Awal harus berupa angka!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            txtSimpanan.requestFocus();
            return false;
        }

        // Validasi panjang nama (minimal 3 karakter)
        if (txtNama.getText().trim().length() < 3) {
            JOptionPane.showMessageDialog(this, "Nama Anggota minimal 3 karakter!",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            txtNama.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * Format angka menjadi format Rupiah
     * @param value Nilai angka
     * @return String dengan format Rupiah
     */
    private String formatRupiah(double value) {
        return "Rp " + String.format("%,.0f", value);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainForm().setVisible(true);
        });
    }
}
