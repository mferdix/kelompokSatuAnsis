/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendaftaran;
import java.sql.*;

/**
 *
 * @author Anatasya Triana
 */
public class Koneksi {
    private Connection koneksi;
    public Connection connect(){
    try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
        } 
        catch (ClassNotFoundException ex) 
        {
            System.out.println("Gagal Koneksi"+ex);
        }
    
    String url = "jdbc:mysql://localhost/pendaftaran";
    
    try 
        {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil koneksi Database");
        } 
        catch (SQLException ex) 
        {
            System.out.println("Gagal Koneksi Database"+ex);
        }
    return koneksi;
    }
    
}
