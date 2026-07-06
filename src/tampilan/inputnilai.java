/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.HeadlessException;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import koneksi.koneksi;
/**
 *
 * @author rafi
 */
public class inputnilai extends javax.swing.JFrame {
 Connection conn = koneksi.getConnection();
 private javax.swing.JButton hitung;
    /**
     * Creates new form inputnilai
     */
    public inputnilai() {
 initComponents();
 buildInputNilaiLayout();

    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);

    table.getTableHeader().setDefaultRenderer(centerRenderer);

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Nama");
    model.addColumn("NIS");
    model.addColumn("Kelas");
    model.addColumn("Jurusan");

    table.setModel(model);
}

private void buildInputNilaiLayout() {
    setTitle("Input Nilai");
    UiHelper.styleButton(jButton1);
    UiHelper.styleButton(cari);
    UiHelper.styleButton(reset);
    UiHelper.styleButton(simpan);
    UiHelper.styleButton(batal);
    hitung = new javax.swing.JButton("Hitung");
    UiHelper.styleButton(hitung);
    java.awt.Dimension actionButtonSize = new java.awt.Dimension(126, 52);
    reset.setPreferredSize(actionButtonSize);
    simpan.setPreferredSize(actionButtonSize);
    batal.setPreferredSize(actionButtonSize);
    hitung.setPreferredSize(actionButtonSize);
    hitung.addActionListener(evt -> {
        try {
            hitungRataRata();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Isi nilai UH, UTS, dan UAS dengan angka.");
        }
    });
    batal.addActionListener(evt -> {
        new dashboard().setVisible(true);
        dispose();
    });
    ratarata.setEditable(false);

    javax.swing.JPanel searchPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
    searchPanel.setBackground(UiHelper.PALE_YELLOW);
    searchPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1. Cari Siswa",
            javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 22)));

    java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
    gbc.insets = new java.awt.Insets(8, 10, 8, 10);
    gbc.anchor = java.awt.GridBagConstraints.WEST;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    addInputRow(searchPanel, gbc, 0, jLabel2, nama, 0);
    addInputRow(searchPanel, gbc, 1, jLabel3, nis, 0);
    gbc.gridx = 2;
    gbc.gridy = 1;
    gbc.weightx = 0;
    searchPanel.add(cari, gbc);

    javax.swing.JPanel studentPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
    studentPanel.setBackground(UiHelper.PALE_YELLOW);
    studentPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
    studentPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    javax.swing.JPanel classPanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
    classPanel.setBackground(UiHelper.PALE_YELLOW);
    classPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2. Pilih Jurusan",
            javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 22)));
    addInputRow(classPanel, gbc, 0, jLabel4, jurusan, 0);
    addInputRow(classPanel, gbc, 1, jLabel5, kelas, 0);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    classPanel.add(jLabel7, gbc);

    javax.swing.JPanel scorePanel = new javax.swing.JPanel(new java.awt.GridBagLayout());
    scorePanel.setBackground(UiHelper.PALE_YELLOW);
    scorePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3. Input Nilai",
            javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP,
            new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 22)));

    gbc.insets = new java.awt.Insets(22, 10, 8, 10);
    addInputRow(scorePanel, gbc, 0, jLabel10, mapel, 0);
    gbc.insets = new java.awt.Insets(8, 10, 8, 10);
    addInputRow(scorePanel, gbc, 1, jLabel11, kkm, 0);
    addInputRow(scorePanel, gbc, 2, jLabel12, uh, 0);
    addInputRow(scorePanel, gbc, 3, jLabel13, uts, 0);
    addInputRow(scorePanel, gbc, 4, jLabel14, uas, 0);
    addInputRow(scorePanel, gbc, 5, jLabel15, ratarata, 0);

    javax.swing.JPanel actions = new javax.swing.JPanel(new java.awt.GridLayout(2, 2, 12, 12));
    actions.setOpaque(false);
    actions.add(hitung);
    actions.add(reset);
    actions.add(simpan);
    actions.add(batal);
    gbc.gridx = 2;
    gbc.gridy = 0;
    gbc.gridheight = 6;
    gbc.insets = new java.awt.Insets(22, 12, 8, 10);
    gbc.fill = java.awt.GridBagConstraints.NONE;
    gbc.anchor = java.awt.GridBagConstraints.NORTH;
    scorePanel.add(actions, gbc);

    javax.swing.JPanel topRow = new javax.swing.JPanel(new java.awt.GridLayout(1, 2, 18, 0));
    topRow.setOpaque(false);
    topRow.add(searchPanel);
    topRow.add(classPanel);

    javax.swing.JPanel bottomRow = new javax.swing.JPanel(new java.awt.GridBagLayout());
    bottomRow.setOpaque(false);
    java.awt.GridBagConstraints bottomGbc = new java.awt.GridBagConstraints();
    bottomGbc.gridy = 0;
    bottomGbc.insets = new java.awt.Insets(0, 0, 0, 18);
    bottomGbc.fill = java.awt.GridBagConstraints.BOTH;
    bottomGbc.weighty = 1;
    bottomGbc.gridx = 0;
    bottomGbc.weightx = 0.52;
    bottomRow.add(studentPanel, bottomGbc);
    bottomGbc.gridx = 1;
    bottomGbc.weightx = 0.48;
    bottomGbc.insets = new java.awt.Insets(0, 0, 0, 0);
    bottomRow.add(scorePanel, bottomGbc);

    javax.swing.JPanel center = new javax.swing.JPanel(new java.awt.GridBagLayout());
    center.setBackground(UiHelper.SOFT_GREEN);
    center.setBorder(javax.swing.BorderFactory.createEmptyBorder(24, 24, 24, 24));
    java.awt.GridBagConstraints centerGbc = new java.awt.GridBagConstraints();
    centerGbc.gridx = 0;
    centerGbc.gridy = 0;
    centerGbc.weightx = 1;
    centerGbc.weighty = 0.42;
    centerGbc.fill = java.awt.GridBagConstraints.BOTH;
    centerGbc.insets = new java.awt.Insets(0, 0, 18, 0);
    center.add(topRow, centerGbc);
    centerGbc.gridy = 1;
    centerGbc.weighty = 0.58;
    centerGbc.insets = new java.awt.Insets(0, 0, 0, 0);
    center.add(bottomRow, centerGbc);

    javax.swing.JPanel root = new javax.swing.JPanel(new java.awt.BorderLayout());
    root.add(UiHelper.pageHeader(getClass(), "Input Nilai", jButton1), java.awt.BorderLayout.NORTH);
    root.add(center, java.awt.BorderLayout.CENTER);

    setContentPane(root);
    UiHelper.prepareFrame(this);
}

private void addInputRow(javax.swing.JPanel panel, java.awt.GridBagConstraints gbc, int row, javax.swing.JLabel label, java.awt.Component input, int gridWidth) {
    label.setFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 18));
    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.weightx = 0;
    gbc.fill = java.awt.GridBagConstraints.NONE;
    panel.add(label, gbc);

    gbc.gridx = 1;
    gbc.gridwidth = gridWidth <= 0 ? 1 : gridWidth;
    gbc.weightx = 1;
    gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
    input.setPreferredSize(new java.awt.Dimension(280, 34));
    panel.add(input, gbc);
}

private double hitungRataRata() {
    double nilaiUH = Double.parseDouble(uh.getText().trim());
    double nilaiUTS = Double.parseDouble(uts.getText().trim());
    double nilaiUAS = Double.parseDouble(uas.getText().trim());
    double rata = (nilaiUH + nilaiUTS + nilaiUAS) / 3;
    ratarata.setText(String.format("%.2f", rata));
    return rata;
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
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nama = new javax.swing.JTextField();
        nis = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jurusan = new javax.swing.JComboBox<>();
        kelas = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        reset = new javax.swing.JButton();
        batal = new javax.swing.JButton();
        simpan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        ratarata = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        kkm = new javax.swing.JTextField();
        uh = new javax.swing.JTextField();
        uts = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        uas = new javax.swing.JTextField();
        mapel = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 153));

        jPanel2.setBackground(new java.awt.Color(51, 153, 0));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo (1).png"))); // NOI18N

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setText("HOME");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setText("1. Cari Siswa");

        jLabel2.setText("Nama Siswa");

        jLabel3.setText("NIS");

        cari.setText("CARI");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(204, 255, 153));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Nama", "NIS", "Kelas", "Jurusan"
            }
        ));
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(nis, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(cari))))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setText("2. Pilih Jurusan");

        jLabel5.setText("Jurusan");

        jLabel6.setText("Kelas");

        jurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TKJ", "Perkantoran", "Akuntansi", "Pemasaran" }));
        jurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jurusanActionPerformed(evt);
            }
        });

        kelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "11", "12" }));

        jLabel7.setText("Mata pelajaran akan menyesuaikan dengan jurusan dan kelas yg di pilih");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 1200, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(179, 179, 179)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel7)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 204));

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel8.setText("3. Input Nilai");

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        batal.setText("Batal");

        simpan.setText("Simpan");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel10.setText("Mata Pelajaran");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel11.setText("KKM");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Ujian Harian");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel13.setText("UTS");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel14.setText("UAS");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("Rata-Rata");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel15)
                        .addGap(51, 51, 51)
                        .addComponent(ratarata, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel11)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kkm, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uh, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(uts, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(simpan, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(230, 230, 230))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(kkm, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uts, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mapel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(ratarata, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(uh, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(uas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(117, 117, 117))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
    nama.setText("");
    nis.setText("");
    mapel.setText("");
    kkm.setText("");
    uh.setText("");
    uts.setText("");
    uas.setText("");
    ratarata.setText("");

    table.clearSelection();        // TODO add your handling code here:
    }//GEN-LAST:event_resetActionPerformed

    private void jurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jurusanActionPerformed

try {
        Connection conn = koneksi.getConnection();

        String sql = "SELECT nama_mapel, kkm FROM mapel LIMIT 1";

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            mapel.setText(rs.getString("nama_mapel"));
            kkm.setText(rs.getString("kkm"));
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    }//GEN-LAST:event_jurusanActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed


    try {

        Connection con = koneksi.getConnection();

        DefaultTableModel model =
                (DefaultTableModel) table.getModel();

        model.setRowCount(0);

        String sql = "SELECT * FROM siswa WHERE nis=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, nis.getText());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

           Object[] data = {
    rs.getString("nama"),
    rs.getString("nis"),
    rs.getString("kelas"),
    rs.getString("jurusan")
   };

    model.addRow(data);

     nama.setText(rs.getString("nama"));
     kelas.setSelectedItem(rs.getString("kelas"));
     jurusan.setSelectedItem(rs.getString("jurusan"));

            
        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Data siswa tidak ditemukan"
            );
        }

    } catch (HeadlessException | SQLException e) {

        JOptionPane.showMessageDialog(
                null,
                e.getMessage()
        );
    }


        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
dashboard form = new dashboard();
    form.setVisible(true);
    this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed

    try {

        Connection conn = koneksi.getConnection();

        // Ambil ID Siswa
        String sqlSiswa = "SELECT id FROM siswa WHERE nis=?";
        PreparedStatement ps1 = conn.prepareStatement(sqlSiswa);
        ps1.setString(1, nis.getText());

        ResultSet rs1 = ps1.executeQuery();

        if (!rs1.next()) {
            JOptionPane.showMessageDialog(null, "Data siswa tidak ditemukan");
            return;
        }

        int idSiswa = rs1.getInt("id");

        // Ambil ID Mapel
        String sqlMapel = "SELECT id_mapel FROM mapel WHERE nama_mapel=?";
        PreparedStatement ps2 = conn.prepareStatement(sqlMapel);
        ps2.setString(1, mapel.getText());

        ResultSet rs2 = ps2.executeQuery();

        if (!rs2.next()) {
            JOptionPane.showMessageDialog(null, "Mata Pelajaran tidak ditemukan");
            return;
        }

        int idMapel = rs2.getInt("id_mapel");

        double nilaiUH = Double.parseDouble(uh.getText().trim());
        double nilaiUTS = Double.parseDouble(uts.getText().trim());
        double nilaiUAS = Double.parseDouble(uas.getText().trim());
        double rata = hitungRataRata();

        String predikat;
        if (rata >= 85)
            predikat = "A";
        else if (rata >= 75)
            predikat = "B";
        else if (rata >= 65)
            predikat = "C";
        else
            predikat = "D";

        String keterangan;

        if (rata >= Double.parseDouble(kkm.getText().trim()))
            keterangan = "LULUS";
        else
            keterangan = "TIDAK LULUS";

      String sql =
"INSERT INTO nilai(id_siswa,id_mapel,mata_plajaran,uh,uts,uas,rata_rata,predikat,keterangan) "
+ "VALUES(?,?,?,?,?,?,?,?,?)";

       PreparedStatement pst = conn.prepareStatement(sql);

pst.setInt(1, idSiswa);
pst.setInt(2, idMapel);
pst.setString(3, mapel.getText());
pst.setDouble(4, nilaiUH);
pst.setDouble(5, nilaiUTS);
pst.setDouble(6, nilaiUAS);
pst.setDouble(7, rata);
pst.setString(8, predikat);
pst.setString(9, keterangan);

pst.executeUpdate();

        JOptionPane.showMessageDialog(null, "Data nilai berhasil disimpan.");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }

// TODO add your handling code here:
    }//GEN-LAST:event_simpanActionPerformed

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
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputnilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputnilai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batal;
    private javax.swing.JButton cari;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jurusan;
    private javax.swing.JComboBox<String> kelas;
    private javax.swing.JTextField kkm;
    private javax.swing.JTextField mapel;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nis;
    private javax.swing.JTextField ratarata;
    private javax.swing.JButton reset;
    private javax.swing.JButton simpan;
    private javax.swing.JTable table;
    private javax.swing.JTextField uas;
    private javax.swing.JTextField uh;
    private javax.swing.JTextField uts;
    // End of variables declaration//GEN-END:variables
}
