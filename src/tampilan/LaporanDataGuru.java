package tampilan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class LaporanDataGuru extends javax.swing.JFrame {

    public LaporanDataGuru() {
        initComponents();
        LaporanSupport.susunLaporan(this, "Laporan Data Guru", txtCari, table, btnHome, btnGuru, btnSiswa, btnJadwal, btnNilai, btnCari, btnRefresh, btnCetak);
        tampilData("");
    }

    private void tampilData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIP");
        model.addColumn("Nama");
        model.addColumn("No. Telp");
        model.addColumn("Email");
        model.addColumn("Agama");
        model.addColumn("JK");

        try {
            Connection conn = koneksi.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM guru";
            if (!keyword.trim().isEmpty()) {
                sql += " WHERE nip LIKE '%" + keyword + "%' OR nama_guru LIKE '%" + keyword + "%'";
            }
            ResultSet rs = st.executeQuery(sql);
            int no = 1;
            while (rs.next()) {
               model.addRow(new Object[]{
                    no++,
                    rs.getString("nip"),
                    rs.getString("nama_guru"),
                    rs.getString("notelp"),
                    rs.getString("email"),
                    rs.getString("agama"),
                    rs.getString("jk")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCari = new javax.swing.JTextField();
        table = new javax.swing.JTable();
        btnHome = new javax.swing.JButton("HOME");
        btnGuru = new javax.swing.JButton("Laporan Guru");
        btnSiswa = new javax.swing.JButton("Laporan Siswa");
        btnJadwal = new javax.swing.JButton("Laporan Jadwal");
        btnNilai = new javax.swing.JButton("Laporan Nilai");
        btnCari = new javax.swing.JButton("Cari");
        btnRefresh = new javax.swing.JButton("Refresh");
        btnCetak = new javax.swing.JButton("Cetak");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laporan Data Guru");

        jPanel1.setBackground(UiHelper.SOFT_GREEN);
        jPanel1.setLayout(new java.awt.BorderLayout());
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"No", "NIP", "Nama", "No. Telp", "Email", "Agama", "JK"}));
        jPanel1.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        btnHome.addActionListener(evt -> LaporanSupport.buka(this, new dashboard()));
        btnGuru.addActionListener(evt -> tampilData(txtCari.getText()));
        btnSiswa.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataSiswa()));
        btnJadwal.addActionListener(evt -> LaporanSupport.buka(this, new LaporanJadwal()));
        btnNilai.addActionListener(evt -> LaporanSupport.buka(this, new LaporanNilai()));
        btnCari.addActionListener(evt -> tampilData(txtCari.getText()));
        btnRefresh.addActionListener(evt -> { txtCari.setText(""); tampilData(""); });
        btnCetak.addActionListener(evt -> LaporanSupport.cetakLaporan(this, table, "Laporan Data Guru"));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnGuru;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnJadwal;
    private javax.swing.JButton btnNilai;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSiswa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}

