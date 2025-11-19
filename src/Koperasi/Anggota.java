/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koperasi;

/**
 * Model class untuk Anggota Koperasi
 * @author LENOVO
 */
public class Anggota {
    private int id;
    private String nama;
    private String alamat;
    private String telepon;
    private double simpananAwal;

    // Constructor default
    public Anggota() {
    }

    // Constructor dengan parameter
    public Anggota(int id, String nama, String alamat, String telepon, double simpananAwal) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.simpananAwal = simpananAwal;
    }

    // Constructor tanpa id (untuk insert)
    public Anggota(String nama, String alamat, String telepon, double simpananAwal) {
        this.nama = nama;
        this.alamat = alamat;
        this.telepon = telepon;
        this.simpananAwal = simpananAwal;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public double getSimpananAwal() {
        return simpananAwal;
    }

    public void setSimpananAwal(double simpananAwal) {
        this.simpananAwal = simpananAwal;
    }

    @Override
    public String toString() {
        return "Anggota{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", telepon='" + telepon + '\'' +
                ", simpananAwal=" + simpananAwal +
                '}';
    }
}
