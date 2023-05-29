/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Inventario;
import Modelo.Producto;
import Modelo.Tienda;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ControladorInventario {
    
    //SELECT cantidad FROM modus.inventario where id_producto = 4 and id_tienda = 1;
    public static int obtenerStock(int idProducto, int idTienda) {
        
        int cantidad = 0;
        String consulta = "SELECT cantidad FROM inventario WHERE id_producto = ? AND id_tienda = ?";
        
        try (PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)){
            
            sentencia.setInt(1, idProducto);
            sentencia.setInt(2, idTienda);
            
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                cantidad = rs.getInt(1);
            }

            return cantidad;
            
        } catch (SQLException e) {
            System.out.println("Error obteniendoStock: " + e);
        }
        
        return cantidad;
    }
    
    
    public static void insertarInventario(int idProducto, int idTienda, int cantidad) {
        //CONSULTA BASE DATOS de Inserccion de datos
        String consulta = "INSERT INTO inventario (id_producto, id_tienda, cantidad, ultima_actualizacion) VALUES (?,?,?,?)";
        //LocalDate fechaActual = LocalDate.now();
        Date ultimaActualizacion = Date.valueOf(LocalDate.now());

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {

            sentencia.setInt(1, idProducto);
            sentencia.setInt(2, idTienda);
            sentencia.setInt(3, cantidad);
            sentencia.setDate(4, ultimaActualizacion);

            sentencia.executeUpdate();

            JOptionPane.showMessageDialog(null, "Inventario creado");

        } catch (SQLException ex) {
            System.out.println("Error SQL alta inventario " + ex);
        } catch (Exception e) {
            System.out.println("Error Genérico dando de alta un inventario: " + e);
        }
    }
    
    public static void cargarComboInventario(JComboBox cmbInventario) {

        Statement sentencia;

        try {
            cmbInventario.removeAllItems();

            sentencia = Conexion.getCon().createStatement();
            String consulta = "SELECT * FROM inventario";
            ResultSet rs = sentencia.executeQuery(consulta);
            while (rs.next()) {
                Producto p = ControladorProducto.cargarProductoID(rs.getInt(2));
                Tienda t = ControladorTienda.cargarTiendaID(rs.getInt(3));
                Date date = rs.getDate(5);
                Inventario i = new Inventario(rs.getInt(1), p, t, rs.getInt(4), date);
                cmbInventario.addItem(i);
            }

        } catch (SQLException e) {
            System.out.println("Error cargando combo inventario");
        } catch (Exception e) {
            System.out.println("Error generico cargando combo inventario: " + e);
        }

    }
    
    public static int eliminarInventario(int id) {
        String consulta = "DELETE FROM inventario WHERE id = ?";
        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar inventario");
        }
        return 0;
    }
    
    public static void actualizarInventario(int id, int id_producto, int id_tienda, int cantidad) {
        // Consulta SQL para la actualización de datos
        String consulta = "UPDATE inventario SET id_producto = ?, id_tienda = ?, cantidad = ?, ultima_actualizacion = ? WHERE id = ?";
        
        //LocalDate fechaActual = LocalDate.now();
        Date ultimaActualizacion = Date.valueOf(LocalDate.now());

        try ( PreparedStatement sentencia = Conexion.getCon().prepareStatement(consulta)) {
            sentencia.setInt(1, id_producto);
            sentencia.setInt(2, id_tienda);
            sentencia.setInt(3, cantidad);
            sentencia.setDate(4, ultimaActualizacion);
            sentencia.setInt(5, id);

            int filasActualizadas = sentencia.executeUpdate();

            if (filasActualizadas > 0) {
                JOptionPane.showMessageDialog(null, "Inventario actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el inventario con ID: " + id);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar inventario: " + ex);
        } catch (Exception e) {
            System.out.println("Error genérico al actualizar el inventario: " + e);
        }
    }
    
}
