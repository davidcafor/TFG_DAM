/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import Controlador.Conexion;
import Controlador.ControladorProducto;
import Controlador.ControladorTienda;
import Modelo.Inventario;
import Modelo.Producto;
import Modelo.Tienda;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class ListadoTiendas extends javax.swing.JDialog {

    /**
     * Creates new form ListadoProducto
     */
    public ListadoTiendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //ASIGNO MODELOTABLA A LA TABLA DE LIBROS
        modeloTabla = (DefaultTableModel) tblTiendas.getModel();
        cargarTablaTiendas();
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
        tblTiendas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado Tiendas");

        tblTiendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Dirección", "Ciudad", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblTiendas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(831, 663));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ListadoTiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoTiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoTiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoTiendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoTiendas dialog = new ListadoTiendas(new javax.swing.JFrame(), true);
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
    
    //Metodo para cargar la tabla con los datos de una consulta a la BD
    private DefaultTableModel modeloTabla = new DefaultTableModel();

    public void cargarTablaTiendas() {

         Statement sentencia;

            try {

                modeloTabla.setRowCount(0);

                sentencia = Conexion.getCon().createStatement();
                String consulta = "SELECT * FROM tiendas";
                ResultSet rs = sentencia.executeQuery(consulta);
                while (rs.next()) {
                    Tienda t = new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                    
                    //AÑADO A TABLA
                    int numFilas = modeloTabla.getRowCount();
                    modeloTabla.setRowCount(numFilas + 1);
                    modeloTabla.setValueAt(t.getId(), numFilas, 0);
                    modeloTabla.setValueAt(t.getNombre(), modeloTabla.getRowCount() - 1, 1);
                    modeloTabla.setValueAt(t.getDireccion(), modeloTabla.getRowCount() - 1, 2);
                    modeloTabla.setValueAt(t.getCiudad(), modeloTabla.getRowCount() - 1, 3);
                    modeloTabla.setValueAt(t.getTelefono(), modeloTabla.getRowCount() - 1, 4);
                }

            } catch (SQLException e) {
                System.out.println("Error Cargando Tabla Tienda: " + e);
            } catch (Exception e) {
                System.out.println("Error Generico Cargando Tabla Tienda: " + e);
            }
        

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTiendas;
    // End of variables declaration//GEN-END:variables
}