/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koperasi;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author LENOVO
 */
public class Koneksi {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/db_koneksi";
            String user = "root";
            String pass = ""; // sesuaikan password MySQL kamu
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}

