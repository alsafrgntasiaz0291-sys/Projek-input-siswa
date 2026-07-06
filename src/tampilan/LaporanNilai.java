package tampilan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class LaporanNilai extends javax.swing.JFrame {

    public LaporanNilai() {
        initComponents();
        LaporanSupport.susunLaporan(this, "Laporan Nilai", txtCari, table, btnHome, btnGuru, btnSiswa, btnJadwal, btnNilai, btnCari, btnRefresh, btnCetak);
        tampilData("");
    }

    private void tampilData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("NIS");
        model.addColumn("Nama");
        model.addColumn("Kelas");
        model.addColumn("Jurusan");
        model.addColumn("Mapel");
        model.addColumn("KKM");
        model.addColumn("UH");
        model.addColumn("UTS");
        model.addColumn("UAS");
        model.addColumn("Rata-rata");
        model.addColumn("Predikat");
        model.addColumn("Rata Keseluruhan");
        model.addColumn("Predikat Akhir");
        model.addColumn("Keterangan");

        try {
            Connection conn = koneksi.getConnection();
            String sql = "SELECT s.nis, s.nama, s.kelas, s.jurusan, "
                    + "COALESCE(m.nama_mapel, n.mata_plajaran) AS mata_pelajaran, "
                    + "m.kkm, n.uh, n.uts, n.uas, "
                    + "COALESCE(n.rata_rata, (n.uh + n.uts + n.uas) / 3) AS rata_rata, "
                    + "n.predikat, n.keterangan, "
                    + "rekap.rata_keseluruhan "
                    + "FROM nilai n "
                    + "JOIN siswa s ON n.id_siswa = s.id "
                    + "LEFT JOIN mapel m ON n.id_mapel = m.id_mapel "
                    + "LEFT JOIN ("
                    + "SELECT id_siswa, AVG(COALESCE(rata_rata, (uh + uts + uas) / 3)) AS rata_keseluruhan "
                    + "FROM nilai GROUP BY id_siswa"
                    + ") rekap ON rekap.id_siswa = s.id";
            if (!keyword.trim().isEmpty()) {
                sql += " WHERE s.nis LIKE ? OR s.nama LIKE ? OR s.kelas LIKE ? OR s.jurusan LIKE ? "
                        + "OR n.mata_plajaran LIKE ? OR m.nama_mapel LIKE ?";
            }
            sql += " ORDER BY s.nama, mata_pelajaran";

            PreparedStatement pst = conn.prepareStatement(sql);
            if (!keyword.trim().isEmpty()) {
                String cari = "%" + keyword.trim() + "%";
                for (int i = 1; i <= 6; i++) {
                    pst.setString(i, cari);
                }
            }

            ResultSet rs = pst.executeQuery();
            int no = 1;
            while (rs.next()) {
                double rataKeseluruhan = rs.getDouble("rata_keseluruhan");
                model.addRow(new Object[]{
                    no++,
                    rs.getString("nis"),
                    rs.getString("nama"),
                    rs.getString("kelas"),
                    rs.getString("jurusan"),
                    rs.getString("mata_pelajaran"),
                    rs.getDouble("kkm"),
                    rs.getDouble("uh"),
                    rs.getDouble("uts"),
                    rs.getDouble("uas"),
                    String.format("%.2f", rs.getDouble("rata_rata")),
                    rs.getString("predikat"),
                    String.format("%.2f", rataKeseluruhan),
                    predikatDariRata(rataKeseluruhan),
                    rs.getString("keterangan")
                });
            }
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private String predikatDariRata(double rata) {
        if (rata >= 85) {
            return "A";
        } else if (rata >= 75) {
            return "B";
        } else if (rata >= 65) {
            return "C";
        }
        return "D";
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
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"No", "NIS", "Nama", "Kelas", "Jurusan", "Mapel", "KKM", "UH", "UTS", "UAS", "Rata-rata", "Predikat", "Rata Keseluruhan", "Predikat Akhir", "Keterangan"}));
        jPanel1.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
        setContentPane(jPanel1);
        pack();

        btnHome.addActionListener(evt -> LaporanSupport.buka(this, new dashboard()));
        btnGuru.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataGuru()));
        btnSiswa.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataSiswa()));
        btnJadwal.addActionListener(evt -> LaporanSupport.buka(this, new LaporanJadwal()));
        btnNilai.addActionListener(evt -> tampilData(txtCari.getText()));
        btnCari.addActionListener(evt -> tampilData(txtCari.getText()));
        btnRefresh.addActionListener(evt -> { txtCari.setText(""); tampilData(""); });
        btnCetak.addActionListener(evt -> LaporanSupport.cetakLaporan(this, table, "Laporan Nilai"));
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

