# QUICK REFERENCE GUIDE
## Aplikasi Manajemen Koperasi

---

## 🎯 QUICK LINKS

| Item | File |
|------|------|
| **Dokumentasi Lengkap** | README.md |
| **Panduan Setup** | SETUP_GUIDE.md |
| **Checklist Requirement** | CHECKLIST.md |
| **Database Script** | db_koperasi.sql |
| **Entry Point** | src/Koperasi/UtsProject.java |
| **GUI Main** | src/Koperasi/MainForm.java |
| **Model Data** | src/Koperasi/Anggota.java |
| **Database Access** | src/Koperasi/AnggotaDAO.java |
| **Koneksi DB** | src/Koperasi/Koneksi.java |

---

## ⚡ QUICK START (5 Menit)

### 1. Import SQL Script ke MySQL
```bash
mysql -u root -p < db_koperasi.sql
```

### 2. Update Koneksi (jika perlu)
Edit `src/Koperasi/Koneksi.java`:
```java
String user = "root";       // ubah sesuai username
String pass = "";           // ubah sesuai password
```

### 3. Jalankan di NetBeans
- Buka project → Klik kanan → Run

### 4. Done! ✅

---

## 🔧 KONFIGURASI DATABASE

File: `src/Koperasi/Koneksi.java`

```java
public class Koneksi {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_koperasi";
            String user = "root";        // ← UBAH SESUAI CONFIG MYSQL
            String pass = "";            // ← UBAH SESUAI PASSWORD
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}
```

**Format URL untuk berbagai kasus:**
```java
// Local standard
String url = "jdbc:mysql://localhost:3306/db_koperasi";

// Custom port
String url = "jdbc:mysql://localhost:3307/db_koperasi";

// Remote server
String url = "jdbc:mysql://192.168.1.100:3306/db_koperasi";

// Dengan timezone
String url = "jdbc:mysql://localhost:3306/db_koperasi?serverTimezone=UTC";
```

---

## 📊 STRUKTUR DATABASE

```sql
TABLE: anggota

Column              Type         Constraint
─────────────────────────────────────────────
id                  INT          PRIMARY KEY, AUTO_INCREMENT
nama                VARCHAR(100) NOT NULL
alamat              VARCHAR(200) NOT NULL
telepon             VARCHAR(15)  NOT NULL
simpanan_awal       DOUBLE       NOT NULL DEFAULT 0
created_at          TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
```

---

## 🖥️ STRUKTUR GUI

```
┌─ MainForm (900x600) ─────────────────────────────────┐
│                                                       │
│  ┌─ Panel Input ──────────────────────────────────┐  │
│  │                                                │  │
│  │  Nama Anggota: [_______________]              │  │
│  │  Alamat:       [_______________]              │  │
│  │                                                │  │
│  │  No. Telepon:  [_______________]              │  │
│  │  Simpanan:     [_______________]              │  │
│  │                                                │  │
│  └────────────────────────────────────────────────┘  │
│                                                       │
│  ┌─ Panel Button ─────────────────────────────────┐  │
│  │  [Tambah] [Update] [Delete] [Clear]           │  │
│  └────────────────────────────────────────────────┘  │
│                                                       │
│  ┌─ JTable ───────────────────────────────────────┐  │
│  │ ID │ Nama │ Alamat │ Telepon │ Simpanan       │  │
│  ├────┼──────┼────────┼─────────┼────────────────┤  │
│  │ 1  │ Budi │ Jl. A  │ 08123.. │ Rp 500.000   │  │
│  │ 2  │ Siti │ Jl. B  │ 08234.. │ Rp 750.000   │  │
│  │ 3  │ Ahm  │ Jl. C  │ 08345.. │ Rp 600.000   │  │
│  └────┴──────┴────────┴─────────┴────────────────┘  │
│                                                       │
└───────────────────────────────────────────────────────┘
```

---

## 📝 FLOW DIAGRAM OPERASI

### CREATE
```
User Input Form → Validasi → Insert ke Database → Refresh Tabel
```

### READ
```
Load Aplikasi → Query Database → Populate Tabel
```

### UPDATE
```
Klik Baris Tabel → Form Terisi → Edit Form → Update DB → Refresh Tabel
```

### DELETE
```
Klik Baris Tabel → Klik Delete → Konfirmasi → Delete DB → Refresh Tabel
```

---

## ✅ VALIDASI RULES

| Field | Rule | Example |
|-------|------|---------|
| Nama | Min 3 karakter, no empty | "Budi Santoso" ✅ |
| Alamat | No empty | "Jl. Merdeka" ✅ |
| Telepon | 10-15 digit | "081234567890" ✅ |
| Simpanan | Angka positif | "500000" ✅ |

**Error Cases:**
```
Nama kosong → "Nama Anggota tidak boleh kosong!"
Telepon "abc" → "No. Telepon harus berupa angka dengan panjang 10-15 digit!"
Simpanan "abc" → "Simpanan Awal harus berupa angka!"
Nama "AB" → "Nama Anggota minimal 3 karakter!"
Simpanan "-100" → "Simpanan Awal tidak boleh negatif!"
```

---

## 🔗 JAVA CLASSES

### UtsProject.java (Main Entry)
```java
public static void main(String[] args) {
    // Jalankan MainForm di Event Dispatch Thread
    SwingUtilities.invokeLater(() -> {
        new MainForm().setVisible(true);
    });
}
```

### Anggota.java (Model)
```java
public class Anggota {
    private int id;
    private String nama;
    private String alamat;
    private String telepon;
    private double simpananAwal;
    
    // Constructors, Getters, Setters...
}
```

### AnggotaDAO.java (CRUD Operations)
```java
public void insert(Anggota a) throws SQLException { }
public List<Anggota> getAll() throws SQLException { }
public void update(Anggota a) throws SQLException { }
public void delete(int id) throws SQLException { }
public Anggota getById(int id) throws SQLException { }
```

### MainForm.java (GUI & Logic)
```java
public class MainForm extends JFrame {
    // Components
    private JTextField txtNama, txtAlamat, txtTelepon, txtSimpanan;
    private JButton btnTambah, btnUpdate, btnDelete, btnClear;
    private JTable table;
    private DefaultTableModel model;
    
    // Methods
    private void tambahData() { }
    private void updateData() { }
    private void hapusData() { }
    private void clearForm() { }
    private boolean validasiInput() { }
}
```

---

## 🐛 COMMON ISSUES & FIXES

| Issue | Cause | Fix |
|-------|-------|-----|
| "Koneksi gagal" | MySQL not running | Start MySQL Service |
| "Unknown database" | DB not created | Import db_koperasi.sql |
| "Access denied" | Wrong password | Update password di Koneksi.java |
| "No suitable driver" | Missing JDBC JAR | Add mysql-connector-java JAR |
| "Table not found" | Wrong table name | Check db_koperasi.sql |
| Tombol tidak aktif | Data not selected | Click table row first |
| Tabel kosong | No data in DB | Click "Tambah" to insert |

---

## 🧪 TEST COMMANDS

### MySQL Test
```bash
# Login
mysql -u root -p

# Check database
SHOW DATABASES;

# Use database
USE db_koperasi;

# Check table
SHOW TABLES;

# Check data
SELECT * FROM anggota;

# Check structure
DESCRIBE anggota;
```

### Java Compile & Run
```bash
# Compile
javac -cp .;lib/mysql-connector-java-8.0.33.jar src/Koperasi/*.java -d build/classes/

# Run
java -cp .;lib/mysql-connector-java-8.0.33.jar;build/classes Koperasi.UtsProject
```

---

## 📋 CHECKLIST BEFORE SUBMISSION

- [ ] Database db_koperasi sudah dibuat
- [ ] Tabel anggota sudah dibuat dengan struktur benar
- [ ] Koneksi database sudah dikonfigurasi
- [ ] Semua file .java ada dan compile tanpa error
- [ ] GUI muncul dengan benar
- [ ] Semua tombol responsif
- [ ] Data dapat ditampilkan di tabel
- [ ] Tombol Tambah, Update, Delete, Clear berfungsi
- [ ] Validasi input bekerja
- [ ] Error message muncul saat ada error
- [ ] Format Rupiah ditampilkan di tabel
- [ ] Dokumentasi lengkap (README, SETUP_GUIDE, CHECKLIST)

---

## 📞 SUPPORT

Jika ada masalah:
1. Cek console output untuk error message
2. Lihat SETUP_GUIDE.md untuk troubleshooting
3. Verifikasi database connection
4. Pastikan MySQL Server running
5. Check MySQL JDBC Driver in classpath

---

**Version**: 1.0
**Last Updated**: November 19, 2025
**Status**: Production Ready ✅
