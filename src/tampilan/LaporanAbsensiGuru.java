package tampilan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

public class LaporanAbsensiGuru extends javax.swing.JFrame {

    public LaporanAbsensiGuru() {
        initComponents();
        LaporanSupport.susunLaporan(this, "Laporan Absensi Guru", txtCari, table,
                btnHome, btnGuru, btnSiswa, btnJadwal, btnNilai, btnCari, btnRefresh, btnCetak);
        tampilData("");
    }

    private void tampilData(String keyword) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("ID Absensi");
        model.addColumn("Tanggal");
        model.addColumn("NIP");
        model.addColumn("Nama Guru");
        model.addColumn("Jam Masuk");
        model.addColumn("Jam Keluar");
        model.addColumn("Status");
        model.addColumn("Keterangan");

        try {
            Connection conn = koneksi.getConnection();
            String sql = "SELECT a.id_absensi, a.tanggal, g.nip, g.nama_guru, "
                    + "a.jam_masuk, a.jam_keluar, a.status, a.keterangan "
                    + "FROM absensi a "
                    + "JOIN guru g ON a.id_guru = g.id_guru";
            if (!keyword.trim().isEmpty()) {
                sql += " WHERE a.tanggal LIKE ? OR g.nip LIKE ? OR g.nama_guru LIKE ? "
                        + "OR a.status LIKE ? OR a.keterangan LIKE ?";
            }
            sql += " ORDER BY a.tanggal DESC, g.nama_guru";

            PreparedStatement pst = conn.prepareStatement(sql);
            if (!keyword.trim().isEmpty()) {
                String cari = "%" + keyword.trim() + "%";
                for (int i = 1; i <= 5; i++) {
                    pst.setString(i, cari);
                }
            }

            ResultSet rs = pst.executeQuery();
            int no = 1;
            while (rs.next()) {
                model.addRow(new Object[]{
                    no++,
                    rs.getInt("id_absensi"),
                    rs.getDate("tanggal"),
                    rs.getString("nip"),
                    rs.getString("nama_guru"),
                    rs.getTime("jam_masuk"),
                    rs.getTime("jam_keluar"),
                    rs.getString("status"),
                    rs.getString("keterangan")
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
        jPanel1.setBackground(UiHelper.SOFT_GREEN);
        jPanel1.setLayout(new java.awt.BorderLayout());
        table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{
            "No", "ID Absensi", "Tanggal", "NIP", "Nama Guru",
            "Jam Masuk", "Jam Keluar", "Status", "Keterangan"
        }));
        jPanel1.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
        setContentPane(jPanel1);
        pack();

        btnHome.addActionListener(evt -> LaporanSupport.buka(this, new dashboard()));
        btnGuru.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataGuru()));
        btnSiswa.addActionListener(evt -> LaporanSupport.buka(this, new LaporanDataSiswa()));
        btnJadwal.addActionListener(evt -> LaporanSupport.buka(this, new LaporanJadwal()));
        btnNilai.addActionListener(evt -> LaporanSupport.buka(this, new LaporanNilai()));
        btnCari.addActionListener(evt -> tampilData(txtCari.getText()));
        btnRefresh.addActionListener(evt -> {
            txtCari.setText("");
            tampilData("");
        });
        btnCetak.addActionListener(evt -> LaporanSupport.cetakLaporan(this, table, "Laporan Absensi Guru"));
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

