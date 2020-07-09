/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Farmacia;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego Sullon
 */
public class Inventario {

    int cod = 1;
    private ArrayList<Medicamento> medicamentos = new ArrayList<Medicamento>();
    private ArrayList<Medicamento> enPromocion = new ArrayList<Medicamento>();

    public Inventario() {
        actualizarLista();
    }

    /**
     * Almacena en memoria(ArrayList)
     */
    public void añadirMedicamento_array(String nombre, String codigo, float precio, String marca, int cantidad, Date fVencimiento) {
        boolean t = true;
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getCodigo().equals(codigo)) {
                medicamentos.get(i).setCantidad(medicamentos.get(i).getCantidad() + cantidad);
                t = false;
            }
        }
        if (t) {
            Medicamento e = new Medicamento(nombre, codigo, precio, marca, cantidad, fVencimiento);
            medicamentos.add(e);
        }
    }

    /**
     * Almacena base de datos conectada
     */
    public void añadirMedicamento(String nombre, String codigo, float precio, String marca, int cantidad, Date fVencimiento, Date fIngreso) {
        boolean t = true;
        try {
            //Se crea la conexión con la base de datos
            Connection miConexion=Farmacia.getConexion();
            //modelo de sentencia
            PreparedStatement miSentencia = miConexion.prepareStatement("SELECT * FROM medicamentos "
                    + "WHERE cod_medicamento=?");
            //formato para la sentencia
            miSentencia.setString(1, codigo);
            //almacena resultado
            ResultSet rs = miSentencia.executeQuery();
            if (rs.next()) {
                PreparedStatement update = miConexion.prepareStatement("UPDATE `medicamentos` SET `cantidad` = ? WHERE `medicamentos`.`cod_medicamento` = ?");
                update.setInt(1, rs.getInt("cantidad") + cantidad);
                update.setString(2, codigo);
                update.executeUpdate();
                t = false;
            }
            rs.close();
            if (t) {
                PreparedStatement sIngreso = miConexion.prepareStatement("INSERT INTO medicamentos VALUES (?,?,?,?,?,?,?)");
                sIngreso.setString(1, codigo);
                sIngreso.setString(2, nombre);
                sIngreso.setString(3, marca);
                sIngreso.setFloat(4, precio);
                Date fecha = new Date();
                java.sql.Date fi = new java.sql.Date(fecha.getTime());
                java.sql.Date fv = new java.sql.Date(fVencimiento.getTime());
                sIngreso.setInt(5, cantidad);
                sIngreso.setDate(6, fi);
                sIngreso.setDate(7, fv);
                sIngreso.executeUpdate();
            }
            actualizarLista();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }

    /**
     * Elimina en memoria(ArrayList)
     */
    public void eliminarMedicamento_array(String med) {
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getCodigo().equals(med)) {
                medicamentos.get(i).setCantidad(medicamentos.get(i).getCantidad() - 1);
                if (medicamentos.get(i).getCantidad() <= 0) {
                    medicamentos.remove(i);
                }
            }
        }

    }

    /**
     * Elimina de base de datos conectada
     */
    public void eliminarMedicamento(String codigo) {
        boolean t = true;
        try {
            Connection conexion = Farmacia.getConexion();
            PreparedStatement miSentencia = conexion.prepareStatement("SELECT * FROM medicamentos "
                    + "WHERE cod_medicamento=?");
            //formato para la sentencia
            miSentencia.setString(1, codigo);
            //almacena resultado
            ResultSet rs = miSentencia.executeQuery();
            if (rs.next()) {
                t=true;
                if (rs.getInt("cantidad") > 1) {
                    PreparedStatement update = conexion.prepareStatement("UPDATE `medicamentos` SET `cantidad` = ? WHERE `medicamentos`.`cod_medicamento` = ?");
                    update.setInt(1, rs.getInt("cantidad") - 1);
                    update.setString(2, codigo);
                    update.executeUpdate();
                    t = false;
                }
            }
            if (t) {
                PreparedStatement delete = conexion.prepareStatement("DELETE FROM `medicamentos` WHERE `medicamentos`.`cod_medicamento` = ?");
                delete.setString(1, codigo);
                delete.executeUpdate();
            }
            rs.close();
            actualizarLista();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión");
        }
    }

    public void actualizarLista() {
        try {
            medicamentos.clear();
            Connection conexion = Farmacia.getConexion();
            PreparedStatement sentencia = conexion.prepareStatement("SELECT * FROM medicamentos");
            ResultSet rs = sentencia.executeQuery();
            
            while (rs.next()) {
                Date fecha= new Date(rs.getDate("fecha_vencimiento").getTime());
                Medicamento e = new Medicamento(rs.getString("nombre"), rs.getString("cod_medicamento"), rs.getFloat("precio"), rs.getString("marca"), rs.getInt("cantidad"), fecha);
                medicamentos.add(e);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Fallo en conexión333");
        }
    }

    public Medicamento buscarMedicamento(String med) {
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getCodigo().equals(med)) {
                return medicamentos.get(i);
            }
        }
        return null;
    }

    public void actualizarMedicamento() {
    }

    private void ponerEnPromocion() {
        Date fecha = new Date();
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getNombre().equals(fecha.getMonth())) {
                medicamentos.get(i).setCantidad(medicamentos.get(i).getCantidad() - 1);
                if (medicamentos.get(i).getCantidad() == 0) {
                    medicamentos.remove(i);
                }
            }
        }
    }

    public String codigoP() {
        String codC = "000";
        int length;
        length = String.valueOf(cod).length();
        switch (length) {
            case 1:
                codC = "00" + String.valueOf(cod);
                break;
            case 2:
                codC = "0" + String.valueOf(cod);
                break;
            case 3:
                codC = String.valueOf(cod);
                break;
        }
        return codC;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setEnPromocion(ArrayList<Medicamento> enPromocion) {
        this.enPromocion = enPromocion;
    }

}
