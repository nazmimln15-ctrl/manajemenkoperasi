# PANDUAN SETUP APLIKASI MANAJEMEN KOPERASI

## LANGKAH 1: Install Prerequisites

### A. Java Development Kit (JDK)
1. Download JDK dari [oracle.com](https://www.oracle.com/java/technologies/downloads/)
2. Install JDK 8 atau lebih tinggi
3. Verifikasi instalasi:
   ```bash
   java -version
   javac -version
   ```

### B. MySQL Server
1. Download MySQL dari [mysql.com](https://dev.mysql.com/downloads/mysql/)
2. Install MySQL Community Server
3. Jalankan MySQL Service/Server
4. Verifikasi dengan:
   ```bash
   mysql --version
   ```

### C. MySQL JDBC Driver
1. Download dari [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
2. Extract file JAR
3. Letakkan di folder project atau tambahkan ke classpath

---

## LANGKAH 2: Setup Database MySQL

### Opsi A: Menggunakan MySQL Command Line
```bash
# Buka MySQL Command Line
mysql -u root -p

# Ketik password MySQL Anda (atau Enter jika tidak ada password)
```

Kemudian jalankan script berikut:
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

-- Insert data sample
INSERT INTO anggota (nama, alamat, telepon, simpanan_awal) VALUES
('Budi Santoso', 'Jl. Merdeka No. 10', '081234567890', 500000),
('Siti Nurhaliza', 'Jl. Ahmad Yani No. 5', '082345678901', 750000),
('Ahmad Wijaya', 'Jl. Sudirman No. 20', '083456789012', 600000);
```

### Opsi B: Menggunakan MySQL Workbench
1. Buka MySQL Workbench
2. Buat koneksi baru ke MySQL Server lokal
3. Klik `File` → `Open SQL Script`
4. Pilih file `db_koperasi.sql`
5. Klik Execute (⚡) untuk jalankan script

### Opsi C: Menggunakan phpMyAdmin (Jika Tersedia)
1. Buka phpMyAdmin (biasanya di `http://localhost/phpmyadmin`)
2. Klik "Import"
3. Pilih file `db_koperasi.sql`
4. Klik "Import"

---

## LANGKAH 3: Konfigurasi Koneksi Database

Buka file: `src/Koperasi/Koneksi.java`

Pastikan konfigurasi sesuai dengan setup MySQL Anda:
```java
String url = "jdbc:mysql://localhost:3306/db_koperasi";
String user = "root";          // Ubah sesuai username MySQL
String pass = "";              // Ubah sesuai password MySQL (jika ada)
```

**Contoh dengan password:**
```java
String pass = "password123";
```

---

## LANGKAH 4: Persiapan Project di IDE

### Jika Menggunakan NetBeans
1. Buka NetBeans
2. Klik `File` → `Open Project`
3. Pilih folder `utsProject`
4. Klik "Open Project"
5. Tambahkan MySQL JDBC Driver:
   - Klik kanan project → `Properties`
   - Pilih `Libraries`
   - Klik `Add JAR/Folder`
   - Pilih file `mysql-connector-java-x.x.xx.jar`
   - Klik OK

### Jika Menggunakan Eclipse
1. Buka Eclipse
2. Klik `File` → `Import...`
3. Pilih `General` → `Existing Projects into Workspace`
4. Browse ke folder `utsProject`
5. Klik Finish
6. Tambahkan JAR ke Build Path:
   - Klik kanan project → `Build Path` → `Configure Build Path`
   - Tab `Libraries`
   - Klik `Add External JARs`
   - Pilih file MySQL JDBC Driver

### Jika Menggunakan IntelliJ IDEA
1. Buka IntelliJ IDEA
2. Klik `File` → `Open`
3. Pilih folder `utsProject`
4. Klik `Open`
5. Tambahkan JAR:
   - Klik `File` → `Project Structure`
   - Pilih `Libraries`
   - Klik `+` → `Java`
   - Pilih file MySQL JDBC Driver

---

## LANGKAH 5: Jalankan Aplikasi

### Opsi A: Dari IDE (Recommended)
- **NetBeans**: Klik kanan project → `Run`
- **Eclipse**: Klik kanan `UtsProject.java` → `Run As` → `Java Application`
- **IntelliJ**: Klik kanan `UtsProject.java` → `Run 'UtsProject.main()'`

### Opsi B: Dari Command Line
```bash
# Navigasi ke folder project
cd utsProject

# Compile
javac -cp .;lib/mysql-connector-java-8.0.xx.jar src/Koperasi/*.java -d build/classes/

# Run
java -cp .;lib/mysql-connector-java-8.0.xx.jar:build/classes Koperasi.UtsProject
```

**Untuk Linux/Mac (gunakan `:` bukan `;`):**
```bash
java -cp .:lib/mysql-connector-java-8.0.xx.jar:build/classes Koperasi.UtsProject
```

---

## LANGKAH 6: Verifikasi Instalasi

Jika aplikasi berhasil:
1. ✅ Window aplikasi terbuka dengan judul "Manajemen Koperasi - CRUD Anggota"
2. ✅ Tabel menampilkan data awal (jika sudah insert sample data)
3. ✅ Form input kosong dan siap diisi
4. ✅ Semua tombol (Tambah, Update, Delete, Clear) responsif

Jika ada error:
- Lihat console untuk pesan error detail
- Cek konfigurasi database di `Koneksi.java`
- Pastikan MySQL Server sedang berjalan
- Pastikan database dan tabel sudah dibuat

---

## Test Aplikasi

### 1. Test INSERT (Tambah)
```
Isi form:
- Nama: Ahmad Fadhel
- Alamat: Jl. Gatot Subroto No. 25
- No. Telepon: 081987654321
- Simpanan: 400000

Klik "Tambah" → Data harus muncul di tabel
```

### 2. Test READ (Lihat)
- Aplikasi harus menampilkan semua data dari database di tabel
- Setiap baris harus menampilkan: ID, Nama, Alamat, Telepon, Simpanan

### 3. Test UPDATE (Edit)
```
1. Klik salah satu baris di tabel
2. Data harus muncul di form
3. Ubah salah satu field (misal nama)
4. Klik "Update"
5. Data di tabel harus berubah
```

### 4. Test DELETE (Hapus)
```
1. Klik salah satu baris di tabel
2. Klik "Delete"
3. Konfirmasi "Yes"
4. Data harus hilang dari tabel
```

### 5. Test CLEAR
```
1. Isi form dengan data sembarang
2. Klik "Clear"
3. Form harus kosong semua
```

### 6. Test Validasi
```
- Coba submit dengan field kosong → Harus ada pesan error
- Coba input telepon dengan huruf → Harus ada pesan error
- Coba input simpanan dengan huruf → Harus ada pesan error
- Coba input nama 1 karakter → Harus ada pesan error
```

---

## Troubleshooting

### Error: "Cannot find symbol: class Koneksi"
**Solusi:**
- Pastikan semua file `.java` di folder `src/Koperasi/`
- Compile terlebih dahulu sebelum run
- Cek classpath sudah benar

### Error: "No suitable driver found for jdbc:mysql://..."
**Solusi:**
- Download MySQL JDBC Driver
- Tambahkan ke classpath/Libraries project
- Pastikan nama file JAR benar di classpath

### Error: "Communications link failure"
**Solusi:**
- Pastikan MySQL Server sedang berjalan
  - Windows: Cek Services (MySQL80 harus Running)
  - Linux: `sudo service mysql start`
  - Mac: Gunakan System Preferences
- Cek port 3306 tidak digunakan program lain
- Restart MySQL Server

### Error: "Unknown database 'db_koperasi'"
**Solusi:**
- Jalankan script `db_koperasi.sql` untuk buat database
- Verifikasi database sudah dibuat:
  ```sql
  SHOW DATABASES;
  ```

### Error: "Access denied for user 'root'@'localhost'"
**Solusi:**
- Cek username dan password di `Koneksi.java`
- Test koneksi manual:
  ```bash
  mysql -u root -p
  ```
- Update password jika berubah

### Tabel Kosong / Data Tidak Muncul
**Solusi:**
- Pastikan sudah insert sample data atau tambah data baru
- Cek database benar dengan:
  ```sql
  SELECT * FROM anggota;
  ```
- Refresh tabel dengan menutup dan membuka aplikasi lagi

### Tombol Tidak Responsive
**Solusi:**
- Cek error message di console
- Pastikan koneksi database masih aktif
- Restart aplikasi

---

## Catatan Penting

1. **Username/Password MySQL**: Sesuaikan dengan konfigurasi MySQL Anda
2. **Port Database**: Default MySQL adalah 3306
3. **Character Encoding**: Aplikasi menggunakan UTF-8 untuk support karakter Indonesia
4. **Auto-increment ID**: ID anggota otomatis bertambah dari database
5. **Data Persistence**: Semua data disimpan permanent di database MySQL

---

## Bantuan Lebih Lanjut

Jika masih mengalami masalah:
1. Cek pesan error di console aplikasi
2. Lihat log MySQL: `C:\ProgramData\MySQL\MySQL Server X.X\Data\<computername>.err` (Windows)
3. Buka issue atau hubungi developer

---

**Selamat! Aplikasi Manajemen Koperasi sudah siap digunakan!** 🎉
