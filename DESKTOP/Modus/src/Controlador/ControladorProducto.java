/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author David
 */
public class ControladorProducto {
    
    public static void insertarProducto(String nombre, String descripcion, double precio, String imagen){
        //CONSULTA BASE DATOS de Inserccion de datos
        String consulta = "INSERT INTO productos (nombre, descripcion, precio, imagen) VALUES (?,?,?,?)";

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, descripcion);
            sentencia.setDouble(3, precio);
            sentencia.setString(4, imagen);
           
            sentencia.executeUpdate();

            JOptionPane.showMessageDialog(null, "Producto " + nombre + " añadido correctamente");

        } catch (SQLException ex) {
            System.out.println("Error SQL alta producto" + ex);
        } catch (Exception e) {
            System.out.println("Error Genérico dando de alta un producto: " + e);
        }
    }
    
    
    
    /**
     * METODO PARA SUBIR LA IMAGEN AL FTP
     * @param imgName
     * @param imgPath
     * @param ip
     * @param puertoFTP
     * @param usuarioFTP
     * @param claveFTP
     * @param ftpFolder 
     */
    public static void subirImgFTP(String imgName, String imgPath, String ip, int puertoFTP, String usuarioFTP, String claveFTP, String ftpFolder){

        if(imgName != null) {
            
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
    
}
