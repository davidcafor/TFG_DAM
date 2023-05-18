/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
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
        LocalDate fechaActual = LocalDate.now();
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
    
}
