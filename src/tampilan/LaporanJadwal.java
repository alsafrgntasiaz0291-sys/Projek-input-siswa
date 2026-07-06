package tampilan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class LaporanJadwal extends javax.swing.JFrame {

    public LaporanJadwal() {
        initComponents();
        LaporanSupport.susunLaporan(this, "Laporan Jadwal", txtCari, table, btnHome, btnGuru, btnSiswa, btnJadwal, btnNilai, btnCari, btnRefresh, btnCetak);
        tampilData("");
    }

    private void tampilData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Jurusan");
        model.addColumn("Kelas");
        model.addColumn("Hari");
        model.addColumn("Jam");
        model.addColumn("Mapel");
        model.addColumn("Guru");
        model.addColumn("Ruangan");
        model.addColumn("Keterangan");

        try {
            Connection conn = koneksi.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM jadwal";
            if (!keyword.trim().isEmpty()) {
                sql += " WHERE jurusan LIKE '%" + keyword + "%' OR kelas LIKE '%" + keyword + "%' OR guru LIKE '%" + keyword + "%' OR mapel LIKE '%" + keyword + "%'";
            }
            ResultSet rs = st.executeQuery(sql);
            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{no++, rs.getString("jurusan"), rs.getString("kelas"), rs.getString("hari"), rs.getString("jam"), rs.getString("mapel"), rs.getString("guru"), rs.getString("ruangan"), rs.getString("keterangan")});
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
        jPanel1.setBackground(UiHelper.SOFT_GREEN);
        jPanel1.setLayout(new java.awt.BorderLayout());
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"No", "Jurusan", "Kelas", "Hari", "Jam", "Mapel", "Guru", "Ruangan", "Keterangan"}));
        jPanel1.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
        setContentPane(jPanel1);
        pack();

        btnHome.addActionListener(evt -> LaporanSupport.buka(this, new dashboard()));
        btnGuru.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataGuru()));
        btnSiswa.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataSiswa()));
        btnJadwal.addActionListener(evt -> tampilData(txtCari.getText()));
        btnNilai.addActionListener(evt -> LaporanSupport.buka(this, new LaporanNilai()));
        btnCari.addActionListener(evt -> tampilData(txtCari.getText()));
        btnRefresh.addActionListener(evt -> { txtCari.setText(""); tampilData(""); });
        btnCetak.addActionListener(evt -> LaporanSupport.cetakLaporan(this, table, "Laporan Jadwal"));
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

