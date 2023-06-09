/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Controlador.Conexion;
import Controlador.ControladorInventario;
import Controlador.ControladorTienda;
import Modelo.Tienda;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class BajaTienda extends javax.swing.JDialog {

    /**
     * Creates new form BajaTienda
     */
    public BajaTienda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ControladorTienda.cargarComboTiendas(cmbTiendas);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTiendas = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Baja Tienda");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(cmbTiendas, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTiendas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(605, 162));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        if (cmbTiendas.getSelectedItem() != null) {
            Tienda t = (Tienda) cmbTiendas.getSelectedItem();
            try {
                //COMPRUEBO SI HAY INVENTARIO ASOCIADO
                boolean isRelated = ControladorInventario.existeTienda(t.getId());

                if (isRelated) {
                    int choice = JOptionPane.showConfirmDialog(this, "La tienda tiene inventario asociado. ¿Deseas eliminarla junto con el inventario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {

                        Connection connection = Conexion.getCon();
                        connection.setAutoCommit(false);

                        try {

                            //BORRO INVENTARIO ASOCIADO
                            ControladorInventario.eliminarInventarioWhereTienda(t.getId());

                            //BORRO TIENDA
                            int elementosEliminados = ControladorTienda.eliminarTienda(t.getId());
                            if (elementosEliminados > 0) {
                                connection.commit();

                                JOptionPane.showMessageDialog(this, "Tienda eliminado correctamente");
                                ControladorTienda.cargarComboTiendas(cmbTiendas);
                                ControladorTienda.cargarComboTiendas(((FrmPPal) getOwner()).getComboTiendas());
                            }
                        } catch (SQLException ex) {
                            System.out.println("Error borrando tienda: " + ex);
                            connection.rollback();
                        } finally {
                            // No cerrar conexion, sino no cargan combos/tablas
                            //connection.close();
                            connection.setAutoCommit(true);
                        }
                    }
                } else {
                    int elementosEliminados = ControladorTienda.eliminarTienda(t.getId());
                    if (elementosEliminados > 0) {
                        JOptionPane.showMessageDialog(this, "Tienda eliminado correctamente");
                        ControladorTienda.cargarComboTiendas(cmbTiendas);
                        ControladorTienda.cargarComboTiendas(((FrmPPal) getOwner()).getComboTiendas());
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BajaProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes elegir una tienda a eliminar");
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(BajaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BajaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BajaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BajaTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BajaTienda dialog = new BajaTienda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<Tienda> cmbTiendas;
    // End of variables declaration//GEN-END:variables
}
