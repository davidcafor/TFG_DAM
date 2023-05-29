/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author David
 */
public class Inventario {
    int id;
    Producto producto;
    Tienda tienda;
    int cantidad;
    Date ultima_actualizacion;

    public Inventario() {
    }

    public Inventario(int id, Producto producto, Tienda tienda, int cantidad, Date ultima_actualizacion) {
        this.id = id;
        this.producto = producto;
        this.tienda = tienda;
        this.cantidad = cantidad;
        this.ultima_actualizacion = ultima_actualizacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getUltima_actualizacion() {
        return ultima_actualizacion;
    }

    public void setUltima_actualizacion(Date ultima_actualizacion) {
        this.ultima_actualizacion = ultima_actualizacion;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Producto: " + producto.nombre + " - Tienda: " + tienda.nombre + " - Cantidad: " + cantidad + " - Ult. Actualización: " + ultima_actualizacion;
    }
}


