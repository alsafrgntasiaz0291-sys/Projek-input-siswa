import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class koneksi {
    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/project",
                "root",
                ""
            );

            System.out.println("Koneksi berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
        }

        return conn;
    }
}

