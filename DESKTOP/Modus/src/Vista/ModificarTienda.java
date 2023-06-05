/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Controlador.ControladorTienda;
import Modelo.Tienda;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ModificarTienda extends javax.swing.JDialog {

    /**
     * Creates new form ModificarProducto
     */
    public ModificarTienda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ControladorTienda.cargarComboTiendas(cmbTienda);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTienda = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnModTienda = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Tienda");

        cmbTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTiendaActionPerformed(evt);
            }
        });

        jLabel5.setText("Ciudad:");

        jLabel6.setText("Teléfono:");

        btnModTienda.setText("Modificar Tienda");
        btnModTienda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModTiendaActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre:");

        jLabel8.setText("Dirección:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(213, 213, 213)
                        .addComponent(btnModTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCiudad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(cmbTienda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(btnModTienda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(592, 479));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModTiendaActionPerformed
        // TODO add your handling code here:

        //COMPROBACIONES
        StringBuilder sb = new StringBuilder("Se han producidos los siguientes errores:\n");
        boolean formularioOK = true;

        if (txtNombre.getText().trim().isEmpty()) {
            sb.append("- Falta indicar el nombre\n");
            formularioOK = false;
        }
        
        if(txtNombre.getText().length()>45) {
            sb.append("- Longitud de nombre demasiado grande");
            formularioOK = false;
        }

        if (txtDireccion.getText().trim().isEmpty()) {
            sb.append("- Falta indicar la dirección\n");
            formularioOK = false;
        }
        
        if(txtDireccion.getText().length()>100) {
            sb.append("- Longitud de la dirección demasiado grande");
            formularioOK = false;
        }
        
        if (txtCiudad.getText().trim().isEmpty()) {
            sb.append("- Falta indicar la ciudad\n");
            formularioOK = false;
        }
        
        if(txtCiudad.getText().length()>45) {
            sb.append("- Longitud de la ciudad demasiado grande");
            formularioOK = false;
        }
        
        if (txtTelefono.getText().trim().isEmpty()) {
            sb.append("- Falta indicar el teléfono\n");
            formularioOK = false;
        }
        
        if(!txtTelefono.getText().trim().matches("^[6789]\\d{8}$")){
            sb.append("- Formato de teléfono no válido\n");
            formularioOK = false;
        }

        if (formularioOK == false) {
            JOptionPane.showMessageDialog(this, sb);
            return;
        }

        //MODIFICO TIENDA
        Tienda t = (Tienda) cmbTienda.getSelectedItem();
        ControladorTienda.actualizarTienda(t.getId(), txtNombre.getText(), txtDireccion.getText(), txtCiudad.getText(), txtTelefono.getText());
        ControladorTienda.cargarComboTiendas(((FrmPPal)getOwner()).getComboTiendas());
        
        this.dispose();

    }//GEN-LAST:event_btnModTiendaActionPerformed

    private void cmbTiendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTiendaActionPerformed
        // TODO add your handling code here:

        if (cmbTienda.getSelectedItem() != null) {

            //CARGAR CAMPOS CON DATOS
            Tienda t = (Tienda) cmbTienda.getSelectedItem();
            
            txtNombre.setText(t.getNombre());
            txtDireccion.setText(t.getDireccion());
            txtCiudad.setText(t.getCiudad());
            txtTelefono.setText(t.getTelefono());    
        }

    }//GEN-LAST:event_cmbTiendaActionPerformed

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
            java.util.logging.Logger.getLogger(ModificarTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarTienda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificarTienda dialog = new ModificarTienda(new javax.swing.JFrame(), true);
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
    
    public void cargarConfiguracionFTP(){
        //CARGAR ARCHIVO CONFIGURACION
        Properties archivoProperties = new Properties();
        InputStream ficheroConfiguracionBD = null;

        try {
            if (Configuraciones.ConfiguracionEjecucion.SISTEMA_EN_PRODUCCION) {
                System.out.println("Producción");
                ficheroConfiguracionBD = new FileInputStream("./configuracion.properties");

            } else {
                System.out.println("Desarrollo");
                ficheroConfiguracionBD = this.getClass().getResourceAsStream("/Configuraciones/configuracion.properties");
            }
            archivoProperties.load(ficheroConfiguracionBD);

            ip = archivoProperties.getProperty("URL");
            puertoFTP = Integer.parseInt(archivoProperties.getProperty("PUERTOFTP"));
            usuarioFTP = archivoProperties.getProperty("USUARIOFTP");
            claveFTP = archivoProperties.getProperty("CLAVEFTP");
            ftpFolder = archivoProperties.getProperty("FTPFOLDER");

        } catch (IOException e) {
            System.out.println("Error cargando archivo de configuración de FTP. El programa se cerrará");
        } catch (NullPointerException e) {
            System.out.println("No se encuentra el archivo configuracion");
        }
    }

    //variables para leer archivo configuracion
    private String ip;
    private int puertoFTP;
    private String usuarioFTP;
    private String claveFTP;
    private String ftpFolder;

    private String imgPath = null;
    private String imgName = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModTienda;
    private javax.swing.JComboBox<Tienda> cmbTienda;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
