/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koperasi;
import java.sql.*;
import java.util.*;

/**
 * Data Access Object untuk Anggota
 * Menangani semua operasi CRUD ke database
 * @author LENOVO
 */
public class AnggotaDAO {
    
    /**
     * Menambahkan data anggota baru ke database
     * @param a Objek Anggota yang akan ditambahkan
     * @throws SQLException Jika terjadi error database
     */
    public void insert(Anggota a) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            throw new SQLException("Koneksi database gagal");
        }
        
        String sql = "INSERT INTO anggota (nama, alamat, telepon, simpanan_awal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNama());
            ps.setString(2, a.getAlamat());
            ps.setString(3, a.getTelepon());
            ps.setDouble(4, a.getSimpananAwal());
            ps.executeUpdate();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Mengambil semua data anggota dari database
     * @return List berisi semua data anggota
     * @throws SQLException Jika terjadi error database
     */
    public List<Anggota> getAll() throws SQLException {
        List<Anggota> list = new ArrayList<>();
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            throw new SQLException("Koneksi database gagal");
        }
        
        String sql = "SELECT * FROM anggota ORDER BY id DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Anggota a = new Anggota(
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("telepon"),
                    rs.getDouble("simpanan_awal")
                );
                list.add(a);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    /**
     * Mengupdate data anggota berdasarkan ID
     * @param a Objek Anggota dengan data terbaru (harus memiliki ID)
     * @throws SQLException Jika terjadi error database
     */
    public void update(Anggota a) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            throw new SQLException("Koneksi database gagal");
        }
        
        String sql = "UPDATE anggota SET nama=?, alamat=?, telepon=?, simpanan_awal=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, a.getNama());
            ps.setString(2, a.getAlamat());
            ps.setString(3, a.getTelepon());
            ps.setDouble(4, a.getSimpananAwal());
            ps.setInt(5, a.getId());
            ps.executeUpdate();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Menghapus data anggota berdasarkan ID
     * @param id ID anggota yang akan dihapus
     * @throws SQLException Jika terjadi error database
     */
    public void delete(int id) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            throw new SQLException("Koneksi database gagal");
        }
        
        String sql = "DELETE FROM anggota WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Mencari data anggota berdasarkan ID
     * @param id ID anggota yang dicari
     * @return Objek Anggota jika ditemukan, null jika tidak ditemukan
     * @throws SQLException Jika terjadi error database
     */
    public Anggota getById(int id) throws SQLException {
        Connection conn = Koneksi.getConnection();
        if (conn == null) {
            throw new SQLException("Koneksi database gagal");
        }
        
        String sql = "SELECT * FROM anggota WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Anggota(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("telepon"),
                        rs.getDouble("simpanan_awal")
                    );
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

