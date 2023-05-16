/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Conexion {
    
    public static Connection con;
    
    public static Connection conectarBD(String url,String puerto,String usuario,String bd,String clave){       
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            System.out.println("jdbc:mysql://"+url+":"+puerto+"/"+bd+"?serverTimezone=UTC");
            con = DriverManager.getConnection("jdbc:mysql://"+url+":"+puerto+"/"+bd+"?serverTimezone=UTC",usuario,clave);
            return con;
            
        } catch (SQLException ex) { 
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Error en conexion: " + e);
        }
        return null;
    }
    
    public static Connection getCon(){
        return con;
    }
    
    public static void cerrarBD(){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
