-- DATABASE SETUP UNTUK APLIKASI MANAJEMEN KOPERASI
-- Import file ini ke MySQL untuk setup database

-- 1. Buat Database
CREATE DATABASE IF NOT EXISTS db_koperasi;
USE db_koperasi;

-- 2. Buat Tabel Anggota
CREATE TABLE IF NOT EXISTS anggota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    alamat VARCHAR(200) NOT NULL,
    telepon VARCHAR(15) NOT NULL,
    simpanan_awal DOUBLE NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Insert Data Sample
INSERT INTO anggota (nama, alamat, telepon, simpanan_awal) VALUES
('Budi Santoso', 'Jl. Merdeka No. 10, Bandung', '081234567890', 500000.00),
('Siti Nurhaliza', 'Jl. Ahmad Yani No. 5, Jakarta', '082345678901', 750000.00),
('Ahmad Wijaya', 'Jl. Sudirman No. 20, Surabaya', '083456789012', 600000.00),
('Rini Putri', 'Jl. Gatot Subroto No. 15, Medan', '084567890123', 450000.00),
('Hendra Gunawan', 'Jl. Diponegoro No. 30, Yogyakarta', '085678901234', 550000.00);

-- 4. Verifikasi Data
SELECT * FROM anggota;

-- 5. Lihat struktur tabel
DESCRIBE anggota;

-- Catatan:
-- - Jika ingin reset data, gunakan: DELETE FROM anggota;
-- - Jika ingin drop tabel: DROP TABLE anggota;
-- - Jika ingin drop database: DROP DATABASE db_koperasi;
