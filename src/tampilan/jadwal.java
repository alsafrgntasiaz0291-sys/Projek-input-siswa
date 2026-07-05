/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;

/**
 *
 * @author rafi
 */
public class jadwal extends javax.swing.JPanel {
    public static void main(String args[]) {
    javax.swing.JFrame frame = new javax.swing.JFrame("Jadwal");

    frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    frame.setSize(1200, 700);
    frame.setLocationRelativeTo(null);

    frame.setContentPane(new jadwal());
    frame.setVisible(true);
}
public jadwal() {
        initComponents();
        isiComboJurusan();
        isiComboKelas();
    }

    // ==========================
    // TARUH METHOD DI SINI
    // ==========================

    private void isiComboJurusan() {

        cbjurusan.removeAllItems();

        try {

            Connection conn = koneksi.getConnection(); // atau getKoneksi()
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT DISTINCT jurusan FROM jadwal");

            while (rs.next()) {
                cbjurusan.addItem(rs.getString("jurusan"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void isiComboKelas() {

        cbkelas.removeAllItems();

        try {

            Connection conn = koneksi.getConnection(); // atau getKoneksi()
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(
                "SELECT DISTINCT kelas FROM jadwal");

            while (rs.next()) {
                cbkelas.addItem(rs.getString("kelas"));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ==========================
    // BARU DI BAWAHNYA initComponents()
    // ==========================

// TARUH METHOD DI SINI
private void tampilTabel() {

    DefaultTableModel model = new DefaultTableModel();

    model.addColumn("No");
    model.addColumn("Hari");
    model.addColumn("Jam");
    model.addColumn("Mata Pelajaran");
    model.addColumn("Guru");
    model.addColumn("Ruangan");
    model.addColumn("Keterangan");

    try {

        Connection conn = koneksi.getConnection();
        Statement st = conn.createStatement();

        String sql = "SELECT * FROM jadwal WHERE jurusan='"
                + cbjurusan.getSelectedItem()
                + "' AND kelas='"
                + cbkelas.getSelectedItem()
                + "'";

        ResultSet rs = st.executeQuery(sql);

        int no = 1;

        while (rs.next()) {

            model.addRow(new Object[]{
                no++,
                rs.getString("hari"),
                rs.getString("jam"),
                rs.getString("mapel"),
                rs.getString("guru"),
                rs.getString("ruangan"),
                rs.getString("keterangan")
            });

        }

        tbljadwal.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnhome = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbjurusan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbkelas = new javax.swing.JComboBox<>();
        btntampilan = new javax.swing.JButton();
        btnJadwal = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btncetak = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbljadwal = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        jPanel2.setBackground(new java.awt.Color(0, 102, 0));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo (1).png"))); // NOI18N

        btnhome.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnhome.setText("Home");
        btnhome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhomeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("JADWAL");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnhome)
                        .addGap(490, 490, 490))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(69, 69, 69))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnhome)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Pilih Jurusan");

        cbjurusan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbjurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjurusanActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Pilih Kelas");

        cbkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btntampilan.setText("Tampilkan");
        btntampilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntampilanActionPerformed(evt);
            }
        });

        btnJadwal.setText("Tambah Jadwal");
        btnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJadwalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(27, 27, 27)
                .addComponent(cbjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(cbkelas, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btntampilan, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnJadwal)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbjurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntampilan)
                    .addComponent(btnJadwal))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jLabel1.setText("JADWAL PERKELAS");

        tbljadwal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Hari", "Jam", "Mata Pelajaran", "Guru", "Ruangan", "Keterangan"
            }
        ));
        tbljadwal.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tbljadwalAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane1.setViewportView(tbljadwal);

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btncetakLayout = new javax.swing.GroupLayout(btncetak);
        btncetak.setLayout(btncetakLayout);
        btncetakLayout.setHorizontalGroup(
            btncetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btncetakLayout.createSequentialGroup()
                .addGroup(btncetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btncetakLayout.createSequentialGroup()
                        .addGap(757, 757, 757)
                        .addComponent(jButton1))
                    .addGroup(btncetakLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        btncetakLayout.setVerticalGroup(
            btncetakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btncetakLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(0, 173, Short.MAX_VALUE))
        );

        jLabel5.setText("DAFTAR JADWAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(btncetak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(462, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 174, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btntampilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntampilanActionPerformed
    tampilTabel();

    }//GEN-LAST:event_btntampilanActionPerformed

    private void btnhomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhomeActionPerformed
    dashboard form = new dashboard();  
    form.setVisible(true);

    java.awt.Window window =
        javax.swing.SwingUtilities.getWindowAncestor(this);

    if(window != null){
    window.dispose();
}
    }//GEN-LAST:event_btnhomeActionPerformed

    private void cbjurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjurusanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbjurusanActionPerformed

    private void btnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJadwalActionPerformed
    frmInputJadwal form = new frmInputJadwal();
form.setVisible(true);

java.awt.Window window =
        javax.swing.SwingUtilities.getWindowAncestor(this);

if(window != null){
    window.dispose();
}
    }//GEN-LAST:event_btnJadwalActionPerformed

    private void tbljadwalAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tbljadwalAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tbljadwalAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJadwal;
    private javax.swing.JPanel btncetak;
    private javax.swing.JButton btnhome;
    private javax.swing.JButton btntampilan;
    private javax.swing.JComboBox<String> cbjurusan;
    private javax.swing.JComboBox<String> cbkelas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbljadwal;
    // End of variables declaration//GEN-END:variables


   
}


