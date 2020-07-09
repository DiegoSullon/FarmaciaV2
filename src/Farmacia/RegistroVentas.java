/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Diego Sullon
 */
public class RegistroVentas {

    private ArrayList<Venta> ventas = new ArrayList<Venta>();

    public RegistroVentas() {
        actualizarLista();
    }

    
    public void registra(Venta venta) {
        ventas.add(venta);
    }

    public void registrar(Venta venta) {
        String comprobante = venta.getTipoDePago()?"Factura":"Boleta";
        try {
            //Se crea la conexión con la base de datos
            Connection conexion = Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement ingreso = conexion.prepareStatement("INSERT INTO ventas VALUES (?,?,?,?,?,?)");
            //formato para la sentencia
            ingreso.setString(1, venta.getCodigo());ingreso.setString(2, venta.getPedido().getNombreC());ingreso.setString(3, venta.getPedido().getDocC());
//            java.sql.Date fv = new java.sql.Date(tiempo.getTime());
            ingreso.setFloat(4, venta.getPedido().getTotal());ingreso.setDate(5, null);ingreso.setString(6, comprobante);
            ingreso.executeUpdate();
            //cierre de conexión
            actualizarLista();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }
    public void actualizarLista() {
        try {
            ventas.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM ventas");
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                boolean c=(rs.getString("comprobante").equalsIgnoreCase("Boleta"));
                Venta e = new Venta(null,rs.getString("nombre_cliente"),rs.getString("documento_cliente"),rs.getFloat("total"),0,c);
                ventas.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

}
