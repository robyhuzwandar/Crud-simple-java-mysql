package inputan;

import java.sql.*;
import java.text.SimpleDateFormat;
import koneksi.koneksi1;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class input extends javax.swing.JFrame {

    koneksi1 con;
    private Object[][] tbl_input = null;
    private String[] label = {"Nama", "Nim", "Tanggal Lahir", "Jenis Kelamain", "Alamat"};

    public input() {
        initComponents();
        con = new koneksi1();
        con.Class();
        BacaTabel();
    }

    public final void tglskrg() {
        Date skrg = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String tanggal = format.format(skrg);
    }

    private void BacaTabel() {
        try {
            con.ss = (Statement) con.cc.createStatement();
            String sql = "Select * From Mahasiswa Order By Nim";
            con.rr = con.ss.executeQuery(sql);
            ResultSetMetaData m = con.rr.getMetaData();
            int kolom = m.getColumnCount();
            int baris = 0;
            while (con.rr.next()) {
                baris = con.rr.getRow();
            }

            tbl_input = new Object[baris][kolom];
            int x = 0;
            con.rr.beforeFirst();
            while (con.rr.next()) {
                tbl_input[x][0] = con.rr.getString("Nama");
                tbl_input[x][1] = con.rr.getString("Nim");
                tbl_input[x][2] = con.rr.getDate("Tgl_Lahir");
                tbl_input[x][3] = con.rr.getString("jenis_kelamin");
                tbl_input[x][4] = con.rr.getString("alamat");
                x++;
            }
            Ftabel.setModel(new DefaultTableModel(tbl_input, label));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void SetTabel() {
        int row = Ftabel.getSelectedRow();
        Fnama.setText((String) Ftabel.getValueAt(row, 0));
        Fid.setText((String) Ftabel.getValueAt(row, 1));
        Ftgl.setDate((Date) Ftabel.getValueAt(row, 2));
        Fjk.setSelectedItem((String) Ftabel.getValueAt(row, 3));
        Falamat.setText((String) Ftabel.getValueAt(row, 4));
    }

    
    private void simpan() {
        String nama = this.Fnama.getText();
        String Nim = this.Fid.getText();
        java.util.Date tanggal = (java.util.Date) this.Ftgl.getDate();
        String jk = (String) this.Fjk.getSelectedItem();
        String alamat = this.Falamat.getText();

        try {
            String sql = "Insert into Mahasiswa values (?,?,?,?,?)";
            PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(1, Nim);
            p.setString(2, nama);
            p.setDate(3, new java.sql.Date(tanggal.getTime()));
            p.setString(4, jk);
            p.setString(5, alamat);
            p.executeUpdate();

            BacaTabel();
            JOptionPane.showMessageDialog(this, " Data Berhasil di masukkan");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void edit() {
        String nama = this.Fnama.getText();
        String Nim = this.Fid.getText();
        java.util.Date tanggal = (java.util.Date) this.Ftgl.getDate();
        String jk = (String) this.Fjk.getSelectedItem();
        String alamat = this.Falamat.getText();

        try {
            String sql = "Update Mahasiswa set Nama =?, Tgl_Lahir=?, Jenis_kelamin=?, Alamat=? Where Nim=?";
            PreparedStatement p = (PreparedStatement) con.cc.prepareStatement(sql);
            p.setString(5, Nim);
            p.setString(1, nama);
            p.setDate(2, new java.sql.Date(tanggal.getTime()));
            p.setString(3, jk);
            p.setString(4, alamat);
            p.executeUpdate();

            BacaTabel();
            JOptionPane.showMessageDialog(this, " Data Berhasil di Perbarui");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private void hapus() {
        try {
            String sql = "Delete from Mahasiswa Where Nim='" + Fid.getText() + "'";
            con.ss.executeUpdate(sql);
            con.ss.close();
            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
            BacaTabel();
            Fid.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void baru() {
        Fnama.setText("");
        Fid.setText("");
        Ftgl.setDate(null);
        Fjk.setSelectedItem(null);
        Falamat.setText("");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ftabel = new javax.swing.JTable();
        Fnama = new javax.swing.JTextField();
        Fid = new javax.swing.JTextField();
        Fjk = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        Falamat = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        Fsimpan = new javax.swing.JButton();
        Fedit = new javax.swing.JButton();
        Fhapus = new javax.swing.JButton();
        Fbaru = new javax.swing.JButton();
        Fkeluar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Yu Mincho", 1, 14)); // NOI18N
        jLabel1.setText("DAFTAR ANGGOTA");

        jLabel3.setText("Nama              ");

        jLabel4.setText("Nim                   ");

        jLabel5.setText("Tgl Lahir         ");

        jLabel6.setText("Jenis Kelamin ");

        Ftabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nim", "Nama", "Tgl Lahir", "Jenis Kelamin", "Alamat"
            }
        ));
        Ftabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FtabelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Ftabel);

        Fnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FnamaActionPerformed(evt);
            }
        });

        Fid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FidActionPerformed(evt);
            }
        });

        Fjk.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih -", "Laki - Laki", "Perempuan" }));
        Fjk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FjkActionPerformed(evt);
            }
        });

        Falamat.setColumns(20);
        Falamat.setRows(5);
        jScrollPane2.setViewportView(Falamat);

        jLabel2.setText("Alamat           ");

        Fsimpan.setText("SIMPAN");
        Fsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FsimpanActionPerformed(evt);
            }
        });

        Fedit.setText("EDIT");
        Fedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FeditActionPerformed(evt);
            }
        });

        Fhapus.setText("HAPUS");
        Fhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FhapusActionPerformed(evt);
            }
        });

        Fbaru.setText("BARU");
        Fbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FbaruActionPerformed(evt);
            }
        });

        Fkeluar.setText("KELUAR");
        Fkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FkeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Fsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Fedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Fhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Fbaru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Fkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(13, 13, 13)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Fjk, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Fnama))
                        .addGap(48, 48, 48)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(743, 743, 743))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Fnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Fid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(Fjk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Fsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fhapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fbaru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fkeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FjkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FjkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FjkActionPerformed

    private void FhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FhapusActionPerformed
        hapus();
    }//GEN-LAST:event_FhapusActionPerformed

    private void FbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FbaruActionPerformed
        baru();
    }//GEN-LAST:event_FbaruActionPerformed

    private void FkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FkeluarActionPerformed
        dispose();
    }//GEN-LAST:event_FkeluarActionPerformed

    private void FsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FsimpanActionPerformed
        simpan();
    }//GEN-LAST:event_FsimpanActionPerformed

    private void FeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FeditActionPerformed
        edit();
    }//GEN-LAST:event_FeditActionPerformed

    private void FnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FnamaActionPerformed

    private void FidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FidActionPerformed

    private void FtabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FtabelMouseClicked
        SetTabel();
    }//GEN-LAST:event_FtabelMouseClicked

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
            java.util.logging.Logger.getLogger(input.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(input.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(input.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(input.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new input().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Falamat;
    private javax.swing.JButton Fbaru;
    private javax.swing.JButton Fedit;
    private javax.swing.JButton Fhapus;
    private javax.swing.JTextField Fid;
    private javax.swing.JComboBox<String> Fjk;
    private javax.swing.JButton Fkeluar;
    private javax.swing.JTextField Fnama;
    private javax.swing.JButton Fsimpan;
    private javax.swing.JTable Ftabel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
