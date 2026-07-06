/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tampilan;

/**
 *
 * @author rafi
 */
public class dashboard extends javax.swing.JFrame {
    private javax.swing.JButton laporanGuru;
    private javax.swing.JButton laporanSiswa;
    private javax.swing.JButton laporanJadwal;
    private javax.swing.JButton laporanNilai;
    private javax.swing.JButton laporanAbsenGuru;
    private javax.swing.JButton laporanAbsenSiswa;

    /**
     * Creates new form dashboard
     */
    public dashboard() {
        initComponents();
        buildDashboardLayout();
      
    }

    private void buildDashboardLayout() {
        setTitle("Dashboard");

        setMenuButton(inputsiswa, "Siswa/Siswi");
        setMenuButton(inputjurusan, "Jurusan");
        setMenuButton(inputkelas, "Kelas");
        setMenuButton(jButton3, "Absen Siswa");
        setMenuButton(btnJadwal, "Jadwal");
        setMenuButton(inputguru, "Guru");
        setMenuButton(jButton2, "Raport");
        setMenuButton(jButton1, "Nilai");
        setMenuButton(jButton4, "Absen Guru");
        laporanGuru = buatTombolLaporan("Lap. Guru");
        laporanSiswa = buatTombolLaporan("Lap. Siswa");
        laporanJadwal = buatTombolLaporan("Lap. Jadwal");
        laporanNilai = buatTombolLaporan("Lap. Nilai");
        laporanAbsenGuru = buatTombolLaporan("Lap. Absen Guru");
        laporanAbsenSiswa = buatTombolLaporan("Lap. Absen Siswa");

        resetMenuActions();
        inputsiswa.addActionListener(evt -> bukaFrame(new inputsiswa()));
        inputjurusan.addActionListener(evt -> bukaFrame(new inputjurusan()));
        inputkelas.addActionListener(evt -> bukaFrame(new inputkelas()));
        jButton3.addActionListener(evt -> bukaFrame(new AbsensiSiswa()));
        btnJadwal.addActionListener(evt -> bukaPanel("Jadwal", new jadwal()));
        inputguru.addActionListener(evt -> bukaFrame(new inputguru()));
        jButton2.addActionListener(evt -> bukaFrame(new raport()));
        jButton1.addActionListener(evt -> bukaFrame(new inputnilai()));
        jButton4.addActionListener(evt -> bukaPanel("Absensi Guru", new AbsensiGuru()));
        laporanGuru.addActionListener(evt -> {
            new LaporanDataGuru().setVisible(true);
            dispose();
        });
        laporanSiswa.addActionListener(evt -> {
            new LaporanDataSiswa().setVisible(true);
            dispose();
        });
        laporanJadwal.addActionListener(evt -> {
            new LaporanJadwal().setVisible(true);
            dispose();
        });
        laporanNilai.addActionListener(evt -> {
            new LaporanNilai().setVisible(true);
            dispose();
        });
        laporanAbsenGuru.addActionListener(evt -> {
            new LaporanAbsensiGuru().setVisible(true);
            dispose();
        });
        laporanAbsenSiswa.addActionListener(evt -> {
            new LaporanAbsensiSiswa().setVisible(true);
            dispose();
        });

        javax.swing.JPanel menu = new javax.swing.JPanel(new java.awt.BorderLayout());
        menu.setBackground(UiHelper.SOFT_GREEN);
        menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 18, 12, 18));

        javax.swing.JPanel buttons = new javax.swing.JPanel(new java.awt.GridLayout(0, 2, 10, 10));
        buttons.setOpaque(false);
        buttons.add(inputsiswa);
        buttons.add(inputjurusan);
        buttons.add(inputkelas);
        buttons.add(jButton3);
        buttons.add(btnJadwal);
        buttons.add(inputguru);
        buttons.add(jButton2);
        buttons.add(jButton1);
        buttons.add(jButton4);
        buttons.add(laporanGuru);
        buttons.add(laporanSiswa);
        buttons.add(laporanJadwal);
        buttons.add(laporanNilai);
        buttons.add(laporanAbsenGuru);
        buttons.add(laporanAbsenSiswa);

        menu.add(UiHelper.imageLabel(getClass(), "/images/logo (1).png", 130, 130), java.awt.BorderLayout.NORTH);
        menu.add(buttons, java.awt.BorderLayout.CENTER);

        javax.swing.JPanel content = new javax.swing.JPanel(new java.awt.BorderLayout());
        content.setBackground(java.awt.Color.BLACK);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        content.add(UiHelper.filledImagePanel(getClass(), "/images/sekolah.png"), java.awt.BorderLayout.CENTER);

        javax.swing.JPanel root = new javax.swing.JPanel(new java.awt.BorderLayout());
        root.add(menu, java.awt.BorderLayout.WEST);
        root.add(content, java.awt.BorderLayout.CENTER);

        setContentPane(root);
        UiHelper.prepareFrame(this);
    }

    private void setMenuButton(javax.swing.JButton button, String text) {
        button.setText(text);
        button.setIcon(null);
        UiHelper.styleButton(button);
        button.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18));
        button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        button.setPreferredSize(new java.awt.Dimension(190, 56));
    }

    private void resetMenuActions() {
        javax.swing.JButton[] buttons = {
            inputsiswa, inputjurusan, inputkelas, jButton3, btnJadwal,
            inputguru, jButton2, jButton1, jButton4
        };
        for (javax.swing.JButton button : buttons) {
            for (java.awt.event.ActionListener listener : button.getActionListeners()) {
                button.removeActionListener(listener);
            }
        }
    }

    private void bukaFrame(javax.swing.JFrame frame) {
        frame.setVisible(true);
        dispose();
    }

    private void bukaPanel(String title, javax.swing.JPanel panel) {
        javax.swing.JFrame frame = new javax.swing.JFrame(title);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        UiHelper.prepareFrame(frame);
        frame.setVisible(true);
        dispose();
    }

    private javax.swing.JButton buatTombolLaporan(String text) {
        javax.swing.JButton button = new javax.swing.JButton(text);
        UiHelper.styleButton(button);
        button.setFont(new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 13));
        button.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        button.setMargin(new java.awt.Insets(2, 6, 2, 6));
        button.setPreferredSize(new java.awt.Dimension(190, 56));
        return button;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        inputsiswa = new javax.swing.JButton();
        inputjurusan = new javax.swing.JButton();
        inputkelas = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnJadwal = new javax.swing.JButton();
        inputguru = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(204, 255, 153));

        inputsiswa.setBackground(new java.awt.Color(255, 255, 255));
        inputsiswa.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        inputsiswa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SiswaSisiwi.png"))); // NOI18N
        inputsiswa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputsiswaActionPerformed(evt);
            }
        });

        inputjurusan.setBackground(new java.awt.Color(255, 255, 255));
        inputjurusan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jurusan.png"))); // NOI18N
        inputjurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputjurusanActionPerformed(evt);
            }
        });

        inputkelas.setBackground(new java.awt.Color(255, 255, 255));
        inputkelas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kelas.png"))); // NOI18N
        inputkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputkelasActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/absensiswi.png"))); // NOI18N

        btnJadwal.setBackground(new java.awt.Color(255, 255, 255));
        btnJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AbsenSiswa.png"))); // NOI18N
        btnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJadwalActionPerformed(evt);
            }
        });

        inputguru.setBackground(new java.awt.Color(255, 255, 255));
        inputguru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Guru.png"))); // NOI18N
        inputguru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputguruActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Jadwal.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Nilai.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AbsenGuru.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(164, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputguru, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputkelas)
                            .addComponent(inputjurusan)
                            .addComponent(inputsiswa))
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(139, 139, 139))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(inputsiswa)
                .addGap(33, 33, 33)
                .addComponent(inputjurusan)
                .addGap(27, 27, 27)
                .addComponent(inputkelas)
                .addGap(33, 33, 33)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(inputguru, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sekolah.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputsiswaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputsiswaActionPerformed
      inputsiswa form = new inputsiswa();
    form.setVisible(true);
    this.dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_inputsiswaActionPerformed

    private void inputguruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputguruActionPerformed
inputguru form = new inputguru();
    form.setVisible(true);
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_inputguruActionPerformed

    private void inputkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputkelasActionPerformed
        inputkelas form = new inputkelas();
        form.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_inputkelasActionPerformed

    private void inputjurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputjurusanActionPerformed
        inputjurusan form = new inputjurusan();
        form.setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_inputjurusanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
inputnilai form = new inputnilai();
    form.setVisible(true);
    this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
LaporanJadwal form = new LaporanJadwal();
    form.setVisible(true);
    this.dispose();          // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJadwalActionPerformed
    }//GEN-LAST:event_btnJadwalActionPerformed

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
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new dashboard().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJadwal;
    private javax.swing.JButton inputguru;
    private javax.swing.JButton inputjurusan;
    private javax.swing.JButton inputkelas;
    private javax.swing.JButton inputsiswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
