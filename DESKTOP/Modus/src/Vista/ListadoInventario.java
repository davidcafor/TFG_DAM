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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class ListadoInventario extends javax.swing.JDialog {

    /**
     * Creates new form ListadoProducto
     */
    public ListadoInventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //ASIGNO MODELOTABLA A LA TABLA DE LIBROS
        modeloTabla = (DefaultTableModel) tblInventario.getModel();
        cargarTablaInventario();
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
        tblInventario = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado Inventario");

        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Tienda", "Cantidad", "Ult. Actualización"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInventario);

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
            java.util.logging.Logger.getLogger(ListadoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoInventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoInventario dialog = new ListadoInventario(new javax.swing.JFrame(), true);
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

    public void cargarTablaInventario() {


            Statement sentencia;

            try {

                modeloTabla.setRowCount(0);

                sentencia = Conexion.getCon().createStatement();
                String consulta = "SELECT * FROM inventario";
                ResultSet rs = sentencia.executeQuery(consulta);
                while (rs.next()) {
                    //CONSTRUYO OBJETOS PARA GUARDAR COMPLETOS
                    Producto p = ControladorProducto.cargarProductoID(rs.getInt(2));
                    Tienda t = ControladorTienda.cargarTiendaID(rs.getInt(3));
                    Inventario i = new Inventario(rs.getInt(1), p, t, rs.getInt(4), rs.getDate(5));

                    //AÑADO A TABLA
                    int numFilas = modeloTabla.getRowCount();
                    modeloTabla.setRowCount(numFilas + 1);
                    modeloTabla.setValueAt(i.getId(), numFilas, 0);
                    modeloTabla.setValueAt(i.getProducto().getNombre(), modeloTabla.getRowCount() - 1, 1);
                    modeloTabla.setValueAt(i.getTienda().getNombre(), modeloTabla.getRowCount() - 1, 2);
                    modeloTabla.setValueAt(i.getCantidad(), modeloTabla.getRowCount() - 1, 3);
                    modeloTabla.setValueAt(i.getUltima_actualizacion(), modeloTabla.getRowCount() - 1, 4);
                }

            } catch (SQLException e) {
                System.out.println("Error Cargando Tabla Inventario: " + e);
            } catch (Exception e) {
                System.out.println("Error Generico Cargando Tabla Inventario: " + e);
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventario;
    // End of variables declaration//GEN-END:variables
}
