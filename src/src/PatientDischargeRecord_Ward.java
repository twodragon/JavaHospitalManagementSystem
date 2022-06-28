
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seher
 */
public class PatientDischargeRecord_Ward extends javax.swing.JFrame {
Connection con=null;
ResultSet rs=null;
PreparedStatement pst=null;
    /**
     * Creates new form PatientDischargeRecord_Room
     */
    public PatientDischargeRecord_Ward() {
        initComponents();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
        }
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
    } catch (SQLException ex) {
        Logger.getLogger(PatientDischargeRecord_Ward.class.getName()).log(Level.SEVERE, null, ex);
    }
        Get_Data();
        setLocationRelativeTo(null);
    }
 private void Get_Data(){
         try{
               String sql="Select ID as 'Discharge ID', AdmitPatient_Ward.AdmitID as 'Admit ID',"
                       + "patientregistration.PatientID as 'Patient ID',patientregistration.PatientName as "
                       + "'Patient Name',patientregistration.Gen as 'Gender',patientregistration.BG as"
                       + " 'Blood Group',Disease,AdmitDate as 'Admit Date',Ward_table.Wardname as 'Ward Name',"
                       + "Doctor_table.DoctorID as 'Doctor ID',DoctorName as 'Doctor Name',DischargeDate as"
                       + " 'Discharge Date',DP_Remarks as 'Remarks' from Ward_table,Doctor_table,patientregistration,"
                       + "AdmitPatient_Ward,DischargePatient_Ward where Ward_table.Wardname=AdmitPatient_Ward.Wardname "
                       + "and Doctor_table.DoctorID=AdmitPatient_Ward.DoctorID and patientregistration.PatientID="
                       + "AdmitPatient_Ward.PatientID and AdmitPatient_Ward.admitID= DischargePatient_Ward.admitID"
                       + " order by Dischargedate";
               pst=con.prepareStatement(sql);
         rs= pst.executeQuery();
         jTable1.setModel(DbUtils.resultSetToTableModel(rs));
         }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
          
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Hasta Tahliye Bilgileri");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management", "root", "");
            int row= jTable1.getSelectedRow();
            String table_click= jTable1.getModel().getValueAt(row, 0).toString();
             String sql="Select * from ward_table,Doctor_table,patientregistration,AdmitPatient_Ward,DischargePatient_Ward where"
                     + " ward_table.Wardname=AdmitPatient_Ward.Wardname and doctor_table.DoctorID="
                     + "AdmitPatient_Ward.DoctorID and patientregistration.PatientID=AdmitPatient_Ward.PatientID and"
                     + " AdmitPatient_Ward.AdmitID=DischargePatient_Ward.admitID and ID=" + table_click + "";   
             pst=con.prepareStatement(sql);
            rs=  pst.executeQuery();
            if(rs.next()){
                this.hide();
                PatientDischarge_Ward frm = new PatientDischarge_Ward();
                frm.setVisible(true);
                String add1=rs.getString("DoctorID");
                frm.txtDoctorID.setText(add1);
                String add2=rs.getString("Doctorname");
                frm.txtDoctorName.setText(add2);
                String add3=rs.getString("PatientID");
                frm.PatientID.setText(add3);
                String add5=rs.getString("PatientName");
                frm.txtPatientName.setText(add5);
                String add6=rs.getString("Gen");
                frm.txtGender.setText(add6);
                String add7=rs.getString("BG");
                frm.txtBloodGroup.setText(add7);
                String add9=rs.getString("Disease");
                frm.txtDisease.setText(add9);
                String add11=rs.getString("AdmitDate");
                frm.txtAdmitDate.setText(add11);
                String add14=rs.getString("WardName");
                frm.txtWardname.setText(add14);
                int add16 = rs.getInt("AdmitID");
                String add17= Integer.toString(add16);
                frm.txtAdmitID.setText(add17);
                String add18=rs.getString("DischargeDate");
                frm.txtDischargeDate.setText(add18);
                String add19=rs.getString("DP_Remarks");
                 frm.txtRemarks.setText(add19);
                 int add20 = rs.getInt("ID");
                String add21= Integer.toString(add20);
                frm.txtDischargeID.setText(add21);
               
                frm.btnDelete.setEnabled(true);
                frm.btnSave.setEnabled(false);
                frm.btnUpdate.setEnabled(true);
                
                      }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         this.hide();
       PatientDischarge_Ward frm = new PatientDischarge_Ward();
       frm.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(PatientDischargeRecord_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientDischargeRecord_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientDischargeRecord_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientDischargeRecord_Room.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientDischargeRecord_Room().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
