package koneksi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class koneksi {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Mencoba memuat driver MySQL baru
                //Class.forName("com.mysql.cj.jdbc.Driver");
                
                String url = "jdbc:mysql://localhost:3306/project";
                String user = "root";
                String pass = "";
                
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi ke Database Berhasil!");
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal Koneksi ke Server MySQL! Pastikan XAMPP aktif dan database 'project' sudah dibuat.\nError: " + e.getMessage());
            }
        }
        return conn;
    }
}