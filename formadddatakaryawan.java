/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendaftaran;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import pendaftaran.Koneksi;

/**
 *
 * @author Anatasya Triana
 */
public class formadddatakaryawan extends javax.swing.JFrame {
private final Connection c = new pendaftaran.Koneksi().connect();
ResultSet r;
Statement s;
private Object [][] tabel_addpegawai=null;
private String[] label={"Id Karyawan","Nama Karyawan","Jabatan","Alamat","No Telp"};

    /**
     * Creates new form formdatabaru
     */
    public formadddatakaryawan() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x,y);        
        setResizable(false);
        setLocationRelativeTo(null);
        bacatabel();
        BersihField();
        autokode();
        txtidkaryawan.setVisible(true);
        txtidkaryawan.requestFocus();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    
    
    private void bacatabel(){
    try{
        s=c.createStatement();
        String sql="select * from data_pegawai ";
        r=s.executeQuery(sql);
        ResultSetMetaData m=r.getMetaData();
        int kolom=m.getColumnCount();
        int baris=0;
        while(r.next()){
            baris=r.getRow();
        }
 
        tabel_addpegawai=new Object[baris][kolom];
        int x=0;
        r.beforeFirst();
        while(r.next()){
            tabel_addpegawai[x][0]=r.getString("id"); 
            tabel_addpegawai[x][1]=r.getString("namapegawai"); 
            tabel_addpegawai[x][2]=r.getString("jabatan");
            tabel_addpegawai[x][3]=r.getString("alamat");
            tabel_addpegawai[x][4]=r.getString("telp");
            x++;
        }
        tabeladdpegawai.setModel(new DefaultTableModel(tabel_addpegawai,label));
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void BersihField(){
    txtidkaryawan.setText("");
    txtnamakaryawan.setText("");
    tjabatan.setText("");
    txtalamat.setText("");
    ttelp.setText("");
    autokode();
    }
    
    public void aktif(){
    btnsave.setEnabled(true);
    btncancel.setEnabled(true);

    }
    
    private void save(){
   
    String idkaryawan=this.txtidkaryawan.getText();
    String namakaryawan=this.txtnamakaryawan.getText();
    String jabatan=this.tjabatan.getText();
    String alamat=this.txtalamat.getText();
    String telp=this.ttelp.getText();
    try {
        String sql = "Insert into data_pegawai values (?,?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, idkaryawan);
            p.setString(2, namakaryawan);
            p.setString(3, jabatan);
            p.setString(4, alamat);
            p.setString(5, telp);
            p.executeUpdate();
            p.close();
            bacatabel();
            autokode();
            BersihField(); txtidkaryawan.requestFocus();
            JOptionPane.showMessageDialog(this, "Sukses Tambah data");
        }catch(SQLException e){
            System.out.println(e);
        }finally{
                       
        }
}
    
    private void EditData(){
        try{
        String sql="Update data_pegawai Set id='"+txtidkaryawan.getText()+
                "',namapegawai='"+txtnamakaryawan.getText()+
                "',jabatan='"+tjabatan.getText()+
                "',alamat='"+txtalamat.getText()+
                "',telp='"+ttelp.getText()+
                "'Where id='"+txtidkaryawan.getText()+"'";     
        s.executeUpdate(sql);
        s.close();
        JOptionPane.showMessageDialog(null,"Data berhasil diedit");
        BersihField();
        bacatabel();
        }
        catch(SQLException e){ 
        JOptionPane.showMessageDialog(null,e);
        }
  }
    
private void HapusData(){
        try{
        String sql="Delete from data_pegawai Where id='"+txtidkaryawan.getText()+"'";
        s.executeUpdate(sql);
       // s.close();
        JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
        BersihField();
        bacatabel();
        }
        catch(SQLException e){ 
        JOptionPane.showMessageDialog(null,e);
        aktif();
        }
  }
private void autokode(){
try{ 
    
    txtidkaryawan.setEnabled(false);
    String sql = "SELECT MAX(right(id,5)) AS kode FROM data_pegawai";
    ResultSet r =  s.executeQuery(sql);
        while(r.next())
            {
                if(r.first() == false)
                {
                    txtidkaryawan.setText("EMP000001");     
                    }
                else
                {
                   r.last();
                   int auto_id = r.getInt(1) + 1;
                   String no = String.valueOf(auto_id);
                   int noLong = no.length();
                   //MENGATUR jumlah 0
                    for(int a=0;a<6-noLong;a++)
                    {
                        no = "0" + no;
                    }
                   txtidkaryawan.setText("EMP" + no);
               
                 }       
            }
      
       r.close();      
}   
catch (Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                            "Kesalahan", JOptionPane.WARNING_MESSAGE);

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtidkaryawan = new javax.swing.JTextField();
        tjabatan = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeladdpegawai = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ttelp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JTextArea();
        txtnamakaryawan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("ID Karyawan");

        jLabel2.setText("Nama Karyawan");

        jLabel3.setText("Jabatan");

        jLabel4.setText("Alamat");

        txtidkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidkaryawanActionPerformed(evt);
            }
        });

        btnsave.setText("SAVE");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnedit.setText("EDIT");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btncancel.setText("CANCEL");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        tabeladdpegawai.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeladdpegawai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeladdpegawaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeladdpegawai);

        jButton5.setText("Menu Utama");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo CHAMPS.jpg"))); // NOI18N

        jLabel6.setText("No Telp");

        ttelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ttelpActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel7.setText("DATA KARYAWAN");

        txtalamat.setColumns(20);
        txtalamat.setRows(5);
        txtalamat.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtalamatAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(txtalamat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtnamakaryawan, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                    .addComponent(tjabatan)
                                    .addComponent(txtidkaryawan)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                    .addComponent(ttelp)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnsave)
                                .addGap(74, 74, 74)
                                .addComponent(btnedit)
                                .addGap(84, 84, 84)
                                .addComponent(btndelete)
                                .addGap(61, 61, 61)
                                .addComponent(btncancel)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtidkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnamakaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(tjabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(ttelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnsave)
                        .addComponent(btnedit))
                    .addComponent(btndelete)
                    .addComponent(btncancel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        new Menubar().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        HapusData();
        aktif();
        
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        EditData();
        aktif();
        autokode();
       
    }//GEN-LAST:event_btneditActionPerformed

    private void tabeladdpegawaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeladdpegawaiMouseClicked
        // TODO add your handling code here:
        int table = tabeladdpegawai.getSelectedRow();

        String a = tabeladdpegawai.getValueAt(table, 0).toString();
        String b = tabeladdpegawai.getValueAt(table, 1).toString();
        String c = tabeladdpegawai.getValueAt(table, 2).toString();
        String d = tabeladdpegawai.getValueAt(table, 3).toString();
        String e = tabeladdpegawai.getValueAt(table, 4).toString();

        txtidkaryawan.setText(a);
        txtnamakaryawan.setText(b);
        tjabatan.setText(c);
        txtalamat.setText(d);
        ttelp.setText(e);

        txtidkaryawan.setEnabled(false);
        ttelp.requestFocus();
        btnsave.setEnabled(false);
        
        
    }//GEN-LAST:event_tabeladdpegawaiMouseClicked

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
         save();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void ttelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ttelpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ttelpActionPerformed

    private void txtalamatAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtalamatAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_txtalamatAncestorAdded

    private void txtidkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidkaryawanActionPerformed
        // TODO add your handling code here:
        autokode();
    }//GEN-LAST:event_txtidkaryawanActionPerformed

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
            java.util.logging.Logger.getLogger(formadddatakaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formadddatakaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formadddatakaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formadddatakaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formadddatakaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabeladdpegawai;
    private javax.swing.JTextField tjabatan;
    private javax.swing.JTextField ttelp;
    private javax.swing.JTextArea txtalamat;
    private javax.swing.JTextField txtidkaryawan;
    private javax.swing.JTextField txtnamakaryawan;
    // End of variables declaration//GEN-END:variables
}
