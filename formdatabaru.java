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
public class formdatabaru extends javax.swing.JFrame {
private final Connection c = new pendaftaran.Koneksi().connect();
ResultSet r;
Statement s;
private Object [][] tbl_addpetugas=null;
private String[] label={"Id Karyawan","Nama Karyawan","Username","Password"};

    /**
     * Creates new form formdatabaru
     */
    public formdatabaru() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x,y);        
        setResizable(false);
        setLocationRelativeTo(null);
        bacatabel();
        BersihField();
        getdata();
        getkode();
        cbidkaryawan.setVisible(true);
        cbidkaryawan.requestFocus();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    
    
    
    private void bacatabel(){
    try{
        s=c.createStatement();
        String sql="select * from login ";
        r=s.executeQuery(sql);
        ResultSetMetaData m=r.getMetaData();
        int kolom=m.getColumnCount();
        int baris=0;
        while(r.next()){
            baris=r.getRow();
        }
 
        tbl_addpetugas=new Object[baris][kolom];
        int x=0;
        r.beforeFirst();
        while(r.next()){
            tbl_addpetugas[x][0]=r.getString("idkaryawan"); 
            tbl_addpetugas[x][1]=r.getString("namakaryawan"); 
            tbl_addpetugas[x][2]=r.getString("username");
            tbl_addpetugas[x][3]=r.getString("password");
            
            x++;
        }
        tabeladdpetugas.setModel(new DefaultTableModel(tbl_addpetugas,label));
    }
    catch(SQLException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void BersihField(){
    cbidkaryawan.setSelectedItem(0);
    txtnamakaryawan.setText("");
    txtusername.setText("");
    txtpassword.setText("");
    }
    
    public void aktif(){
    btnsave.setEnabled(true);
    btncancel.setEnabled(true);

    }
    
    private void save(){
   
    String idkaryawan=(String) this.cbidkaryawan.getSelectedItem();
    String namakaryawan=this.txtnamakaryawan.getText();
    String username=this.txtusername.getText();
    String password=this.txtpassword.getText();
    try {
        String sql = "Insert into login values (?,?,?,?)";
            PreparedStatement p=(PreparedStatement) c.prepareStatement(sql);
            p.setString(1, idkaryawan);
            p.setString(2, namakaryawan);
            p.setString(3, username);
            p.setString(4, password);
            p.executeUpdate();
            p.close();
            bacatabel();                                       
            BersihField(); cbidkaryawan.requestFocus();
            JOptionPane.showMessageDialog(this, "Sukses Tambah data");
        }catch(SQLException e){
            System.out.println(e);
        }finally{
                       
        }
}
    
    private void EditData(){
        try{
        String sql="Update login Set idkaryawan='"+cbidkaryawan.getSelectedItem()+
                "',namakaryawan='"+txtnamakaryawan.getText()+
                "',username='"+txtusername.getText()+
                "',password='"+txtpassword.getText()+
                "'Where idkaryawan='"+cbidkaryawan.getSelectedItem()+"'";     
        s.executeUpdate(sql);
        //s.close();
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
        String sql="Delete from login Where idkaryawan='"+cbidkaryawan.getSelectedItem()+"'";
        s.executeUpdate(sql);
        s.close();
        JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
        BersihField();
        bacatabel();
        }
        catch(SQLException e){ 
        JOptionPane.showMessageDialog(null,e);
        aktif();
        }
  }

public void getdata(){
try{ 
   txtnamakaryawan.setEnabled(false);  
    String sql = "SELECT namapegawai FROM data_pegawai where id='" + cbidkaryawan.getSelectedItem() + "'";
    s=c.createStatement();
    ResultSet rs = s.executeQuery(sql);
    while(rs.next())
       {
            String a = rs.getString("namapegawai");
            txtnamakaryawan.setText(a);
        }
}
catch (Exception e){
            JOptionPane.showMessageDialog(this, "ERROR: \n" + e.toString(),
                            "Kesalahan", JOptionPane.WARNING_MESSAGE);

        }
}

public void getkode(){
try{ 
        
    String sql = "SELECT id FROM data_pegawai";
    ResultSet r = s.executeQuery(sql);
        while(r.next())
        {
            String a = r.getString("id");
           cbidkaryawan.addItem(a.trim());
        }}
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
        cbidkaryawan = new javax.swing.JComboBox<>();
        txtnamakaryawan = new javax.swing.JTextField();
        txtusername = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeladdpetugas = new javax.swing.JTable();
        bttmenuutama = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("ID Karyawan");

        jLabel2.setText("Nama Karyawan");

        jLabel3.setText("Username");

        jLabel4.setText("Password");

        cbidkaryawan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select" }));
        cbidkaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbidkaryawanActionPerformed(evt);
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

        tabeladdpetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeladdpetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeladdpetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeladdpetugas);

        bttmenuutama.setText("Menu Utama");
        bttmenuutama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttmenuutamaActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Logo CHAMPS.jpg"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel6.setText("DATA PETUGAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bttmenuutama)
                        .addGap(72, 72, 72))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btnsave)))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnedit)
                                .addGap(42, 42, 42)
                                .addComponent(btndelete)
                                .addGap(55, 55, 55)
                                .addComponent(btncancel))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtnamakaryawan)
                                .addComponent(txtusername)
                                .addComponent(txtpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                .addComponent(cbidkaryawan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(bttmenuutama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbidkaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnamakaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnedit)
                    .addComponent(btndelete)
                    .addComponent(btncancel))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttmenuutamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttmenuutamaActionPerformed
        // TODO add your handling code here:
        new Menubar().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bttmenuutamaActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        HapusData();
        aktif();
        getkode();
        getdata();
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        EditData();
        aktif();
        cbidkaryawan.setEnabled(true);
        cbidkaryawan.setSelectedItem(0);
       // getkode();
        //getdata(); 
    }//GEN-LAST:event_btneditActionPerformed

    private void tabeladdpetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeladdpetugasMouseClicked
        // TODO add your handling code here:
        int table = tabeladdpetugas.getSelectedRow();

        String a = tabeladdpetugas.getValueAt(table, 0).toString();
        String b = tabeladdpetugas.getValueAt(table, 1).toString();
        String c = tabeladdpetugas.getValueAt(table, 2).toString();
        String d = tabeladdpetugas.getValueAt(table, 3).toString();

        cbidkaryawan.setSelectedItem(a);
        txtnamakaryawan.setText(b);
        txtusername.setText(c);
        txtpassword.setText(d);

        cbidkaryawan.setEnabled(false);
        txtpassword.requestFocus();
        btnsave.setEnabled(false);
    }//GEN-LAST:event_tabeladdpetugasMouseClicked

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
         save();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void cbidkaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbidkaryawanActionPerformed
        // TODO add your handling code here:
        getdata();
    }//GEN-LAST:event_cbidkaryawanActionPerformed

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
            java.util.logging.Logger.getLogger(formdatabaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formdatabaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formdatabaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formdatabaru.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formdatabaru().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton bttmenuutama;
    private javax.swing.JComboBox<String> cbidkaryawan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabeladdpetugas;
    private javax.swing.JTextField txtnamakaryawan;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
