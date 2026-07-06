/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;
import koneksi.koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

/**
 *
 * @author Syahra
 */
public class AbsensiSiswa extends javax.swing.JFrame {
    Connection conn = koneksi.getConnection();

DefaultTableModel model = new DefaultTableModel();

ButtonGroup bgStatus = new ButtonGroup();
private javax.swing.JComboBox<String> cmbJurusan;
private boolean updatingStudentFields;

    /**
     * Creates new form AbsensiSiswa
     */
    public AbsensiSiswa() {
        initComponents();
        buildAttendanceLayout();
        bgStatus.add(rbHadir);
    bgStatus.add(rbIzin);
    bgStatus.add(rbSakit);
    bgStatus.add(rbAlpha);

    rbHadir.setSelected(true);

    txtIdAbsensi.setEditable(false);
    txtNamaSiswa.setEditable(true);
    pasangNamaListener();

    spJamMasuk.setEditor(new JSpinner.DateEditor(spJamMasuk, "HH:mm"));
    spJamKeluar.setEditor(new JSpinner.DateEditor(spJamKeluar, "HH:mm"));

    tanggalOtomatis();
    autoID();
    headerTabel();
    tampilKelas();
    tampilJurusan();
    tampilNIS();
    tampilData();

    }

    private void buildAttendanceLayout() {
        setTitle("Absensi Siswa");
        tblAbsensi.setRowHeight(28);
        tblAbsensi.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new dashboard().setVisible(true);
                dispose();
            }
        });

        javax.swing.JPanel form = new javax.swing.JPanel(new java.awt.GridBagLayout());
        form.setBackground(UiHelper.PALE_YELLOW);
        form.setBorder(javax.swing.BorderFactory.createEmptyBorder(18, 24, 18, 24));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(4, 8, 4, 8);
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        addAttendanceRow(form, gbc, 0, jLabel2, txtIdAbsensi);
        addAttendanceRow(form, gbc, 1, jLabel3, txtTanggal);
        addAttendanceRow(form, gbc, 2, jLabel4, cmbKelas);
        cmbJurusan = new javax.swing.JComboBox<>();
        cmbJurusan.addItemListener(evt -> {
            if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                tampilNIS();
            }
        });
        addAttendanceRow(form, gbc, 3, new JLabel("Jurusan"), cmbJurusan);
        addAttendanceRow(form, gbc, 4, jLabel7, txtNamaSiswa);
        addAttendanceRow(form, gbc, 5, jLabel6, cmbNIS);
        addAttendanceRow(form, gbc, 6, jLabel10, spJamMasuk);
        addAttendanceRow(form, gbc, 7, jLabel11, spJamKeluar);

        javax.swing.JPanel status = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 8, 4));
        status.setOpaque(false);
        rbHadir.setOpaque(false);
        rbIzin.setOpaque(false);
        rbSakit.setOpaque(false);
        rbAlpha.setOpaque(false);
        status.add(rbHadir);
        status.add(rbIzin);
        status.add(rbSakit);
        status.add(rbAlpha);
        addAttendanceRow(form, gbc, 8, jLabel8, status);
        addAttendanceRow(form, gbc, 9, jLabel9, txtKeterangan);

        javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 16, 0));
        actions.setOpaque(false);
        UiHelper.styleButton(btnHapus);
        UiHelper.styleButton(btnSimpan);
        UiHelper.styleButton(btnUbah);
        java.awt.Dimension actionSize = new java.awt.Dimension(118, 44);
        btnHapus.setPreferredSize(actionSize);
        btnSimpan.setPreferredSize(actionSize);
        btnUbah.setPreferredSize(actionSize);
        actions.add(btnSimpan);
        actions.add(btnUbah);
        actions.add(btnHapus);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        form.add(actions, gbc);

        javax.swing.JPanel tablePanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        tablePanel.setBackground(UiHelper.PALE_YELLOW);
        tablePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 22, 22, 22));
        tablePanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.JPanel body = new javax.swing.JPanel(new java.awt.GridBagLayout());
        body.setBackground(UiHelper.SOFT_GREEN);
        body.setBorder(javax.swing.BorderFactory.createEmptyBorder(26, 26, 26, 26));
        java.awt.GridBagConstraints bodyGbc = new java.awt.GridBagConstraints();
        bodyGbc.gridy = 0;
        bodyGbc.fill = java.awt.GridBagConstraints.BOTH;
        bodyGbc.weighty = 1;
        bodyGbc.gridx = 0;
        bodyGbc.weightx = 0.40;
        bodyGbc.insets = new java.awt.Insets(0, 0, 0, 24);
        body.add(form, bodyGbc);
        bodyGbc.gridx = 1;
        bodyGbc.weightx = 0.60;
        bodyGbc.insets = new java.awt.Insets(0, 0, 0, 0);
        body.add(tablePanel, bodyGbc);

        javax.swing.JPanel root = new javax.swing.JPanel(new java.awt.BorderLayout());
        root.add(UiHelper.pageHeader(getClass(), "Absensi Siswa", jButton4), java.awt.BorderLayout.NORTH);
        root.add(body, java.awt.BorderLayout.CENTER);

        setContentPane(root);
        UiHelper.prepareFrame(this);
    }

    private void addAttendanceRow(javax.swing.JPanel form, java.awt.GridBagConstraints gbc, int row, javax.swing.JLabel label, java.awt.Component input) {
        label.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        form.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        input.setPreferredSize(new java.awt.Dimension(300, 30));
        form.add(input, gbc);
    }
public void tanggalOtomatis() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    txtTanggal.setText(sdf.format(new Date()));
}
public void autoID() {
    txtIdAbsensi.setText("Otomatis");
}

public void bersih() {
    tanggalOtomatis();
    autoID();
    if (cmbKelas.getItemCount() > 0) {
        cmbKelas.setSelectedIndex(0);
    } else {
        cmbNIS.removeAllItems();
        txtNamaSiswa.setText("");
    }
    if (cmbJurusan != null && cmbJurusan.getItemCount() > 0) {
        cmbJurusan.setSelectedIndex(0);
    }
    if (cmbNIS.getItemCount() > 0) {
        cmbNIS.setSelectedIndex(0);
    }
    spJamMasuk.setValue(new Date());
    spJamKeluar.setValue(new Date());
    rbHadir.setSelected(true);
    txtKeterangan.setText("");
    tblAbsensi.clearSelection();
}

public void headerTabel() {

    model = new DefaultTableModel();

    model.addColumn("ID Absensi");
    model.addColumn("Tanggal");
    model.addColumn("Kelas");
    model.addColumn("Jurusan");
    model.addColumn("NIS");
    model.addColumn("Nama Siswa");
    model.addColumn("Jam Masuk");
    model.addColumn("Jam Keluar");
    model.addColumn("Status");
    model.addColumn("Keterangan");

    tblAbsensi.setModel(model);
    aturLebarTabel();
}
public void tampilKelas() {

    try {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT DISTINCT nama_kelas AS kelas FROM kelas WHERE nama_kelas IS NOT NULL "
                + "UNION SELECT DISTINCT kelas FROM siswa WHERE kelas IS NOT NULL ORDER BY kelas");

        cmbKelas.removeAllItems();

        while (rs.next()) {

            cmbKelas.addItem(rs.getString("kelas"));

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }
}
public void tampilJurusan() {

    if (cmbJurusan == null) {
        return;
    }

    try {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
                "SELECT DISTINCT nama_jurusan AS jurusan FROM jurusan WHERE nama_jurusan IS NOT NULL "
                + "UNION SELECT DISTINCT jurusan FROM siswa WHERE jurusan IS NOT NULL ORDER BY jurusan");

        cmbJurusan.removeAllItems();

        while (rs.next()) {

            cmbJurusan.addItem(rs.getString("jurusan"));

        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this, e.getMessage());

    }
}
public void tampilNIS() {

    try {
        if (cmbKelas.getSelectedItem() == null || cmbJurusan == null || cmbJurusan.getSelectedItem() == null) {
            cmbNIS.removeAllItems();
            return;
        }

        PreparedStatement ps = conn.prepareStatement(
                "SELECT nis FROM siswa WHERE kelas=? AND jurusan=? "
                + "AND (?='' OR nama LIKE ?) ORDER BY nama, nis");
        ps.setString(1, cmbKelas.getSelectedItem().toString());
        ps.setString(2, cmbJurusan.getSelectedItem().toString());
        String nama = txtNamaSiswa.getText().trim();
        ps.setString(3, nama);
        ps.setString(4, "%" + nama + "%");
        ResultSet rs = ps.executeQuery();

        updatingStudentFields = true;
        cmbNIS.removeAllItems();

        while (rs.next()) {

            cmbNIS.addItem(rs.getString("nis"));

        }
        if (cmbNIS.getItemCount() > 0) {
            cmbNIS.setSelectedIndex(0);
        }
        updatingStudentFields = false;

    } catch (Exception e) {
        updatingStudentFields = false;

        JOptionPane.showMessageDialog(this, e.getMessage());

    }
}
    public void tampilNamaSiswa() {

    try {
        if (updatingStudentFields) {
            return;
        }
        if (cmbNIS.getSelectedItem() == null) {
            return;
        }

        PreparedStatement ps = conn.prepareStatement("SELECT nama, kelas, jurusan FROM siswa WHERE nis=?");
        ps.setString(1, cmbNIS.getSelectedItem().toString());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            updatingStudentFields = true;
            txtNamaSiswa.setText(rs.getString("nama"));
            cmbKelas.setSelectedItem(rs.getString("kelas"));
            if (cmbJurusan != null) {
                cmbJurusan.setSelectedItem(rs.getString("jurusan"));
            }
            updatingStudentFields = false;
        } else {
            txtNamaSiswa.setText("");

        }

    } catch (Exception e) {
        updatingStudentFields = false;

        JOptionPane.showMessageDialog(this, e.getMessage());

    }
 }
private void pasangNamaListener() {
    txtNamaSiswa.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            cariNisDariNama();
        }

        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            cariNisDariNama();
        }

        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            cariNisDariNama();
        }
    });
}

private void cariNisDariNama() {
    if (updatingStudentFields || cmbJurusan == null) {
        return;
    }
    javax.swing.SwingUtilities.invokeLater(() -> tampilNIS());
}

private void aturLebarTabel() {
    int[] widths = {90, 110, 80, 130, 110, 180, 100, 100, 95, 190};
    for (int i = 0; i < widths.length && i < tblAbsensi.getColumnModel().getColumnCount(); i++) {
        tblAbsensi.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
    }
}

    public int getIdSiswa() {

    int id = 0;

    try {
        if (cmbNIS.getSelectedItem() == null) {
            return 0;
        }

        PreparedStatement ps = conn.prepareStatement("SELECT id FROM siswa WHERE nis=?");
        ps.setString(1, cmbNIS.getSelectedItem().toString());
        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            id = rs.getInt("id");

        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this, e.getMessage());

    }

    return id;

}
    public void tampilData() {

    model.setRowCount(0);

    try {

        Statement st = conn.createStatement();

        String sql = "SELECT a.id_absensi, a.tanggal, s.kelas, s.jurusan, s.nis, s.nama, "
                   + "a.jam_masuk, a.jam_keluar, a.status, a.keterangan "
                   + "FROM absensi a "
                   + "JOIN siswa s ON a.id_siswa = s.id";

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id_absensi"),
                rs.getDate("tanggal"),
                rs.getString("kelas"),
                rs.getString("jurusan"),
                rs.getString("nis"),
                rs.getString("nama"),
                rs.getTime("jam_masuk"),
                rs.getTime("jam_keluar"),
                rs.getString("status"),
                rs.getString("keterangan")
            });

        }

    } catch (Exception e) {

        System.out.println(e.getMessage());

    }

}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtIdAbsensi = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtNamaSiswa = new javax.swing.JTextField();
        rbHadir = new javax.swing.JRadioButton();
        rbIzin = new javax.swing.JRadioButton();
        rbSakit = new javax.swing.JRadioButton();
        rbAlpha = new javax.swing.JRadioButton();
        btnHapus = new javax.swing.JButton();
        txtKeterangan = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        cmbKelas = new javax.swing.JComboBox<>();
        cmbNIS = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        spJamMasuk = new javax.swing.JSpinner();
        spJamKeluar = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAbsensi = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(1205, 720));

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo (1).png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton4.setText("HOME");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("ID. Absensi");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Kelas");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("NIS");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Nama");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Status");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Keterangan");

        txtIdAbsensi.setEditable(false);

        txtNamaSiswa.setEditable(false);

        rbHadir.setBackground(new java.awt.Color(255, 255, 204));
        rbHadir.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbHadir.setText("Hadir");

        rbIzin.setBackground(new java.awt.Color(255, 255, 204));
        rbIzin.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbIzin.setText("Izin");

        rbSakit.setBackground(new java.awt.Color(255, 255, 204));
        rbSakit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbSakit.setText("Sakit");

        rbAlpha.setBackground(new java.awt.Color(255, 255, 204));
        rbAlpha.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        rbAlpha.setText("Alpha");

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setBackground(new java.awt.Color(255, 255, 255));
        btnSimpan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setBackground(new java.awt.Color(255, 255, 255));
        btnUbah.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        cmbKelas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbKelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Akutansi", "Perkantoran", "TKJ" }));
        cmbKelas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbKelasItemStateChanged(evt);
            }
        });

        cmbNIS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbNIS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNISItemStateChanged(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Jam Masuk");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("Jam Keluar");

        spJamMasuk.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        spJamKeluar.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.MINUTE));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel5))
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbIzin)
                            .addComponent(rbSakit)
                            .addComponent(rbAlpha)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(spJamKeluar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                                .addComponent(spJamMasuk, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbNIS, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbKelas, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbHadir, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIdAbsensi, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNamaSiswa, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addContainerGap(21, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIdAbsensi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbNIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNamaSiswa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(spJamMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(spJamKeluar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rbHadir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbIzin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbSakit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAlpha)
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        tblAbsensi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tblAbsensi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblAbsensi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAbsensiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAbsensi);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1175, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbKelasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbKelasItemStateChanged
if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
    tampilNIS();
}        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKelasItemStateChanged

    private void cmbNISItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNISItemStateChanged
if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
    tampilNamaSiswa();
}        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNISItemStateChanged

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
  

    try {

        String status = "";

        if(rbHadir.isSelected()){
            status = "Hadir";
        }else if(rbIzin.isSelected()){
            status = "Izin";
        }else if(rbSakit.isSelected()){
            status = "Sakit";
        }else{
            status = "Alpha";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        int idSiswa = getIdSiswa();
        if (idSiswa == 0) {
            JOptionPane.showMessageDialog(this, "Pilih NIS siswa terlebih dahulu.");
            return;
        }

        PreparedStatement ps = conn.prepareStatement(
        "INSERT INTO absensi(id_siswa,tanggal,jam_masuk,jam_keluar,status,keterangan) VALUES (?,?,?,?,?,?)");

        ps.setInt(1, idSiswa);
        ps.setString(2, txtTanggal.getText());
        ps.setString(3, sdf.format(spJamMasuk.getValue()));
        ps.setString(4, sdf.format(spJamKeluar.getValue()));
        ps.setString(5, status);
        ps.setString(6, txtKeterangan.getText());

        ps.executeUpdate();

        javax.swing.JOptionPane.showMessageDialog(this,"Data berhasil disimpan");
        tampilData();
        bersih();

    } catch(Exception e){

        javax.swing.JOptionPane.showMessageDialog(this,e.getMessage());

    }

     // TODO add your handling code here:
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void tblAbsensiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAbsensiMouseClicked

    int baris = tblAbsensi.getSelectedRow();

    txtIdAbsensi.setText(model.getValueAt(baris, 0).toString());
    txtTanggal.setText(model.getValueAt(baris, 1).toString());

    cmbKelas.setSelectedItem(model.getValueAt(baris, 2).toString());
    if (cmbJurusan != null) {
        cmbJurusan.setSelectedItem(model.getValueAt(baris, 3).toString());
    }
    cmbNIS.setSelectedItem(model.getValueAt(baris, 4).toString());

    txtNamaSiswa.setText(model.getValueAt(baris, 5).toString());

    spJamMasuk.setValue(java.sql.Time.valueOf(model.getValueAt(baris, 6).toString()));
    spJamKeluar.setValue(java.sql.Time.valueOf(model.getValueAt(baris, 7).toString()));

    String status = model.getValueAt(baris, 8).toString();

    if(status.equals("Hadir")){
        rbHadir.setSelected(true);
    }else if(status.equals("Izin")){
        rbIzin.setSelected(true);
    }else if(status.equals("Sakit")){
        rbSakit.setSelected(true);
    }else{
        rbAlpha.setSelected(true);
    }

    txtKeterangan.setText(model.getValueAt(baris, 9).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_tblAbsensiMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed

    try {
        if (txtIdAbsensi.getText().trim().isEmpty() || txtIdAbsensi.getText().equals("Otomatis")) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel yang mau diubah.");
            return;
        }

        String status = "";

        if(rbHadir.isSelected()){
            status="Hadir";
        }else if(rbIzin.isSelected()){
            status="Izin";
        }else if(rbSakit.isSelected()){
            status="Sakit";
        }else{
            status="Alpha";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        int idSiswa = getIdSiswa();
        if (idSiswa == 0) {
            JOptionPane.showMessageDialog(this, "Pilih NIS siswa terlebih dahulu.");
            return;
        }

        PreparedStatement ps = conn.prepareStatement(
        "UPDATE absensi SET id_siswa=?, tanggal=?, jam_masuk=?, jam_keluar=?, status=?, keterangan=? WHERE id_absensi=?");

        ps.setInt(1, idSiswa);
        ps.setString(2, txtTanggal.getText());
        ps.setString(3, sdf.format(spJamMasuk.getValue()));
        ps.setString(4, sdf.format(spJamKeluar.getValue()));
        ps.setString(5, status);
        ps.setString(6, txtKeterangan.getText());
        ps.setInt(7, Integer.parseInt(txtIdAbsensi.getText()));

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this,"Data berhasil diubah");

        tampilData();
        bersih();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,e.getMessage());

    }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed


    try {
        if (txtIdAbsensi.getText().trim().isEmpty() || txtIdAbsensi.getText().equals("Otomatis")) {
            JOptionPane.showMessageDialog(this, "Pilih data di tabel yang mau dihapus.");
            return;
        }

        PreparedStatement ps = conn.prepareStatement(
        "DELETE FROM absensi WHERE id_absensi=?");

        ps.setInt(1, Integer.parseInt(txtIdAbsensi.getText()));

        ps.executeUpdate();

        JOptionPane.showMessageDialog(this,"Data berhasil dihapus");

        tampilData();
        bersih();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,e.getMessage());

    }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AbsensiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AbsensiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AbsensiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AbsensiSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AbsensiSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cmbKelas;
    private javax.swing.JComboBox<String> cmbNIS;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbAlpha;
    private javax.swing.JRadioButton rbHadir;
    private javax.swing.JRadioButton rbIzin;
    private javax.swing.JRadioButton rbSakit;
    private javax.swing.JSpinner spJamKeluar;
    private javax.swing.JSpinner spJamMasuk;
    private javax.swing.JTable tblAbsensi;
    private javax.swing.JTextField txtIdAbsensi;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtNamaSiswa;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables
}
