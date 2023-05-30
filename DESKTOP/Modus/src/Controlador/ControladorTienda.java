/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tienda;
import Vista.BajaProducto;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ControladorTienda {

    public static void insertarTienda(String nombre, String direccion, String ciudad, String telefono) {
        //CONSULTA BASE DATOS de Inserccion de datos
        String consulta = "INSERT INTO tiendas (nombre, direccion, ciudad, telefono) VALUES (?,?,?,?)";

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {

            sentencia.setString(1, nombre);
            sentencia.setString(2, direccion);
            sentencia.setString(3, ciudad);
            sentencia.setString(4, telefono);

            sentencia.executeUpdate();

            JOptionPane.showMessageDialog(null, "Tienda " + nombre + " añadida correctamente");

        } catch (SQLException ex) {
            System.out.println("Error SQL alta tienda " + ex);
        } catch (Exception e) {
            System.out.println("Error Genérico dando de alta una tienda: " + e);
        }
    }

    public static int eliminarTienda(int id) throws SQLException {
        String consulta = "DELETE FROM tiendas WHERE id = ?";
        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();

        } 
    }

    public static void actualizarTienda(int idTienda, String nombre, String direccion, String ciudad, String telefono) {
        // Consulta SQL para la actualización de datos
        String consulta = "UPDATE tiendas SET nombre = ?, direccion = ?, ciudad = ?, telefono = ? WHERE id = ?";

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setString(1, nombre);
            sentencia.setString(2, direccion);
            sentencia.setString(3, ciudad);
            sentencia.setString(4, telefono);
            sentencia.setInt(5, idTienda);

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Tienda actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la tienda con ID: " + idTienda);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar tienda: " + ex);
        } catch (Exception e) {
            System.out.println("Error genérico al actualizar una tienda: " + e);
        }
    }

    public static Tienda cargarTiendaID(int id) {

        Statement sentencia;
        Tienda t = null;

        try {
            sentencia = Conexion.getCon().createStatement();
            String consulta = "SELECT * FROM tiendas WHERE id = " + id;
            ResultSet rs = sentencia.executeQuery(consulta);
            while (rs.next()) {
                t = new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }

        } catch (SQLException e) {
            System.out.println("Error Cargando Producto por ID: " + e);
        } catch (Exception e) {
            System.out.println("Error Generico Cargando Producto por ID: " + e);
        }

        return t;

    }

    /**
     * METODO PARA CARGAR EL COMBO TIENDAS
     *
     * @param cmbTiendas
     */
    public static void cargarComboTiendas(JComboBox cmbTiendas) {

        Statement sentencia;

        try {

            cmbTiendas.removeAllItems();

            sentencia = Conexion.getCon().createStatement();
            String consulta = "SELECT * FROM tiendas";
            ResultSet rs = sentencia.executeQuery(consulta);
            while (rs.next()) {
                Tienda t = new Tienda(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                cmbTiendas.addItem(t);
            }

        } catch (SQLException e) {
            System.out.println("Error cargando combo tiendas");
        } catch (Exception e) {
            System.out.println("Error generico cargando combo tiendas");
        }

    }

}
