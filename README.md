# Aplikasi Manajemen Koperasi - CRUD Lengkap

Aplikasi desktop Java dengan Swing GUI untuk mengelola data anggota koperasi menggunakan database MySQL.

## Fitur Utama

✅ **Create** - Tambah data anggota baru
✅ **Read** - Tampilkan semua data anggota dalam tabel
✅ **Update** - Edit data anggota yang sudah ada
✅ **Delete** - Hapus data anggota
✅ **Validasi Input** - Validasi lengkap untuk setiap field
✅ **GUI Modern** - Interface Swing dengan styling yang baik

## Requirement

- Java Development Kit (JDK) 8 atau lebih tinggi
- MySQL Server
- MySQL JDBC Driver (mysql-connector-java)
- NetBeans IDE (opsional, bisa menggunakan IDE lain)

## Database Setup

### 1. Buat Database MySQL

Buka MySQL Command Line atau MySQL Workbench, kemudian jalankan script berikut:

```sql
CREATE DATABASE IF NOT EXISTS db_koperasi;
USE db_koperasi;

CREATE TABLE IF NOT EXISTS anggota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    alamat VARCHAR(200) NOT NULL,
    telepon VARCHAR(15) NOT NULL,
    simpanan_awal DOUBLE NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert data sample (opsional)
INSERT INTO anggota (nama, alamat, telepon, simpanan_awal) VALUES
('Budi Santoso', 'Jl. Merdeka No. 10', '081234567890', 500000),
('Siti Nurhaliza', 'Jl. Ahmad Yani No. 5', '082345678901', 750000),
('Ahmad Wijaya', 'Jl. Sudirman No. 20', '083456789012', 600000);
```

Atau buka file `db_koperasi.sql` dan jalankan scriptnya di MySQL.

### 2. Konfigurasi Koneksi Database

Edit file `src/Koperasi/Koneksi.java` dan sesuaikan parameter koneksi:

```java
String url = "jdbc:mysql://localhost:3306/db_koperasi";
String user = "root";  // Sesuaikan username MySQL Anda
String pass = "";      // Sesuaikan password MySQL Anda
```

## Struktur Proyek

```
utsProject/
├── src/
│   └── Koperasi/
│       ├── UtsProject.java      # Entry point aplikasi
│       ├── MainForm.java         # GUI utama aplikasi
│       ├── Anggota.java          # Model class
│       ├── AnggotaDAO.java       # Data Access Object (CRUD)
│       └── Koneksi.java          # Koneksi database
├── build/                        # Output kompilasi
├── nbproject/                    # NetBeans project files
├── db_koperasi.sql              # Script database
├── build.xml                    # Build configuration
└── README.md                    # Dokumentasi ini
```

## Cara Menggunakan Aplikasi

### 1. Jalankan Aplikasi
```bash
java -cp .;lib/mysql-connector-java-8.0.xx.jar Koperasi.UtsProject
```

Atau di NetBeans: Klik kanan project → Run

### 2. Operasi CRUD

#### **TAMBAH DATA**
1. Isi semua field form (Nama, Alamat, No. Telepon, Simpanan Awal)
2. Klik tombol **Tambah**
3. Data akan ditambahkan ke tabel

#### **LIHAT DATA**
- Semua data akan ditampilkan otomatis di tabel saat aplikasi dibuka
- Tabel menampilkan: ID, Nama, Alamat, No. Telepon, dan Simpanan Awal

#### **UPDATE DATA**
1. Klik baris pada tabel yang ingin diupdate
2. Data akan ditampilkan di form
3. Edit data yang ingin diubah
4. Klik tombol **Update**
5. Data akan diperbarui

#### **DELETE DATA**
1. Klik baris pada tabel yang ingin dihapus
2. Klik tombol **Delete**
3. Konfirmasi penghapusan
4. Data akan dihapus dari database

#### **CLEAR FORM**
1. Klik tombol **Clear** untuk membersihkan semua field form
2. Form akan kosong dan siap untuk input baru

## Validasi Input

Aplikasi memiliki validasi lengkap:

| Field | Validasi |
|-------|----------|
| **Nama Anggota** | Tidak boleh kosong, minimal 3 karakter |
| **Alamat** | Tidak boleh kosong |
| **No. Telepon** | Tidak boleh kosong, 10-15 digit angka |
| **Simpanan Awal** | Tidak boleh kosong, harus berupa angka, tidak negatif |

## Deskripsi Class

### 1. **UtsProject.java** (Entry Point)
- Titik awal aplikasi
- Membuka MainForm saat aplikasi dijalankan

### 2. **MainForm.java** (GUI Utama)
- Membuat interface aplikasi dengan Swing
- Menangani event dari tombol dan tabel
- Menampilkan dan memvalidasi input
- Memanggil DAO untuk operasi database

**Komponen GUI:**
- **Panel Input**: 4 JTextField untuk input data
- **Panel Tombol**: 4 JButton (Tambah, Update, Delete, Clear)
- **JTable**: Menampilkan data anggota
- **JScrollPane**: Wrapper untuk JTable

**Event Handlers:**
- Double-click tabel → Populate form dengan data terpilih
- Tombol Tambah → Insert data baru
- Tombol Update → Update data terpilih
- Tombol Delete → Delete data terpilih
- Tombol Clear → Reset form

### 3. **Anggota.java** (Model Class)
- Merepresentasikan data anggota
- Properties: id, nama, alamat, telepon, simpananAwal
- Constructor overload untuk berbagai use case
- Getter dan setter untuk semua property

### 4. **AnggotaDAO.java** (Data Access Object)
- Menangani semua komunikasi dengan database
- **Method insert()**: Tambah data baru
- **Method getAll()**: Ambil semua data
- **Method update()**: Update data berdasarkan ID
- **Method delete()**: Hapus data berdasarkan ID
- **Method getById()**: Cari data berdasarkan ID

Menggunakan PreparedStatement untuk keamanan SQL Injection.

### 5. **Koneksi.java** (Database Connection)
- Mengelola koneksi ke database MySQL
- Static method `getConnection()` untuk mendapatkan koneksi
- Menangani exception koneksi

## Tips Penggunaan

1. **Selalu Pilih Baris untuk Update/Delete**: Pastikan sudah memilih baris tabel sebelum klik Update atau Delete
2. **Validasi Nomor Telepon**: Format hanya angka, panjang 10-15 digit
3. **Simpanan Awal**: Boleh angka desimal (contoh: 500000 atau 500000.50)
4. **Auto-Refresh**: Tabel otomatis refresh setelah operasi CRUD
5. **Konfirmasi Delete**: Aplikasi akan minta konfirmasi sebelum hapus data

## Troubleshooting

### Error: "Koneksi gagal"
- Pastikan MySQL Server berjalan
- Cek username dan password di `Koneksi.java`
- Pastikan database `db_koperasi` sudah dibuat

### Error: "Class not found com.mysql.cj.jdbc.Driver"
- Download MySQL JDBC Driver dari [mysql.com](https://dev.mysql.com/downloads/connector/j/)
- Tambahkan JAR ke classpath project

### Error: "Table 'db_koperasi.anggota' doesn't exist"
- Jalankan script SQL untuk membuat tabel
- Pastikan menggunakan database `db_koperasi`

### Data Tidak Muncul di Tabel
- Periksa koneksi database
- Pastikan ada data di tabel `anggota`
- Lihat error message di console

## Pengembangan Lebih Lanjut

Fitur yang bisa ditambahkan:
- Export data ke Excel/PDF
- Laporan keuangan koperasi
- Manajemen transaksi (setoran/penarikan)
- Authentication/Login
- Multi-user system
- Backup/Restore database

## License

Aplikasi ini dibuat untuk keperluan UTS/Tugas Akhir.

## Contact

Hubungi developer untuk pertanyaan lebih lanjut.

---

**Dibuat dengan ❤️ menggunakan Java Swing dan MySQL**
