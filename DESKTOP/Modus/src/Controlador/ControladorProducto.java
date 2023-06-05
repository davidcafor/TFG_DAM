/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.awt.Image;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author David
 */
public class ControladorProducto {

    public static Producto cargarProductoID(int id) {

        Statement sentencia;
        Producto p = null;

        try {
            sentencia = Conexion.getCon().createStatement();
            String consulta = "SELECT * FROM productos WHERE id = " + id;
            ResultSet rs = sentencia.executeQuery(consulta);
            while (rs.next()) {
                double valorDouble = rs.getDouble(4);
                BigDecimal valorBigDecimal = new BigDecimal(valorDouble);

                p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), valorBigDecimal, rs.getString(5));
            }

        } catch (SQLException e) {
            System.out.println("Error Cargando Producto por ID: " + e);
        } catch (Exception e) {
            System.out.println("Error Generico Cargando Producto por ID: " + e);
        }

        return p;

    }

    /**
     * Metodo Alta Producto a BBDD
     *
     * @param nombre
     * @param descripcion
     * @param precio
     * @param imagen
     */
    public static void insertarProducto(String nombre, String descripcion, double precio, String imagen) {
        //CONSULTA BASE DATOS de Inserccion de datos
        String consulta = "INSERT INTO productos (nombre, descripcion, precio, imagen) VALUES (?,?,?,?)";

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setDouble(3, precio);
            sentencia.setString(4, imagen);

            sentencia.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto " + nombre + " añadido correctamente");

        } catch (MysqlDataTruncation ex) {
            System.out.println("Error alta producto, nombre imagen demasiado largo");
        } catch (SQLException ex) {
            System.out.println("Error SQL alta producto " + ex);
        } catch (Exception e) {
            System.out.println("Error Genérico dando de alta un producto: " + e);
        }
    }

    public static int eliminarProducto(int id) throws SQLException {
        String consulta = "DELETE FROM productos WHERE id = ?";
        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        }
    }

    public static void actualizarProducto(int idProducto, String nombre, String descripcion, double precio, String imagen) {
        // Consulta SQL para la actualización de datos
        String consulta = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ? WHERE id = ?";

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setDouble(3, precio);
            sentencia.setString(4, imagen);
            sentencia.setInt(5, idProducto);

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el producto con ID: " + idProducto);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar producto: " + ex);
        } catch (Exception e) {
            System.out.println("Error genérico al actualizar un producto: " + e);
        }
    }

    /**
     * METODO PARA SUBIR LA IMAGEN AL FTP
     *
     * @param imgName
     * @param imgPath
     * @param ip
     * @param puertoFTP
     * @param usuarioFTP
     * @param claveFTP
     * @param ftpFolder
     */
    public static void subirImgFTP(String imgName, String imgPath, String ip, int puertoFTP, String usuarioFTP, String claveFTP, String ftpFolder) {

        if (imgName != null) {

            Thread ftpThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    FTPClient ftpClient = new FTPClient();
                    try {
                        ftpClient.connect(ip, puertoFTP);
                        ftpClient.login(usuarioFTP, claveFTP);
                        ftpClient.enterLocalPassiveMode();
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                        File localFile = new File(imgPath);
                        InputStream inputStream = new FileInputStream(localFile);

                        System.out.println("Inicio de subida de archivo al servidor FTP");
                        boolean done = ftpClient.storeFile(ftpFolder + imgName, inputStream);
                        inputStream.close();
                        if (done) {
                            System.out.println("El archivo ha sido subido correctamente");
                            //guarda la ruta en la base de datos
                        } else {
                            System.out.println("No se ha podido subir el archivo");
                        }
                    } catch (IOException ex) {
                        System.out.println("Error al conectar al servidor FTP: " + ex.getMessage());
                        ex.printStackTrace();
                    } finally {
                        try {
                            if (ftpClient.isConnected()) {
                                ftpClient.logout();
                                ftpClient.disconnect();
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            ftpThread.start();
        }
    }

    public static void descargarImgFTP(String ip, String usuarioFTP, String claveFTP, String rutaImagenFTP, JLabel lblImagen) throws IOException {
        FTPClient ftpClient = new FTPClient();

        String imgName = rutaImagenFTP.substring("/imagesApp/".length());
        String rutaDescarga;

        if (Configuraciones.ConfiguracionEjecucion.SISTEMA_EN_PRODUCCION) {
            rutaDescarga = "." + File.separator + imgName;
        } else {
            rutaDescarga = ("Descargas" + File.separator + imgName);
        }
        
        String servFTP = ip;
        ftpClient.connect(servFTP);
        ftpClient.enterLocalPassiveMode();
        ftpClient.login(usuarioFTP, claveFTP);
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        FileOutputStream fos = new FileOutputStream(rutaDescarga);
        Runnable run = () -> {
            try {
                boolean descargaOK = ftpClient.retrieveFile(rutaImagenFTP, fos);

                if (descargaOK) {
                    System.out.println("Imagen descargada correctamente del FTP");
                } else {
                    System.out.println("Error bajando imagen del FTP");
                }

            } catch (IOException ex) {
                System.out.println("Error retrieving file FTP");
            }

            //mostrar imagen en label
            ImageIcon preview = new ImageIcon(rutaDescarga);

            int width = lblImagen.getWidth();
            int height = lblImagen.getHeight();
            if (width != 0 && height != 0) {
                lblImagen.setIcon(new ImageIcon(preview.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
            }

        };
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        Thread hilo = new Thread(run);
        hilo.start();
    }

    /**
     * METODO MOSTRAR IMAGEN EN JLABEL
     *
     * @param rutaImagen
     */
    public static void mostrarImagenEnJLabel(String rutaImagen, JLabel lblImagen) {
        ImageIcon imageIcon = new ImageIcon(rutaImagen);
        Image image = imageIcon.getImage();

        Image imagenEscalada = image.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);

        JLabel jLabel = new JLabel(new ImageIcon(imagenEscalada));

        JFrame frame = new JFrame("Imagen Descargada");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(jLabel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * METODO PARA CARGAR EL COMBO PRODUCTOS
     *
     * @param cmbProductos
     */
    public static void cargarComboProductos(JComboBox cmbProductos) {

        Statement sentencia;

        try {
            cmbProductos.removeAllItems();

            sentencia = Conexion.getCon().createStatement();
            String consulta = "SELECT * FROM productos";
            ResultSet rs = sentencia.executeQuery(consulta);
            while (rs.next()) {
                Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBigDecimal(4), rs.getString(5));
                cmbProductos.addItem(p);
            }

        } catch (SQLException e) {
            System.out.println("Error cargando combo productos");
        } catch (Exception e) {
            System.out.println("Error generico cargando combo productos");
        }

    }
}
